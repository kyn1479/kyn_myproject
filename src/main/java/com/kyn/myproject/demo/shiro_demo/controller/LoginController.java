package com.kyn.myproject.demo.shiro_demo.controller;

import com.kyn.myproject.demo.common.entity.ProjectResult;
import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.exception.ProjectException;
import com.kyn.myproject.demo.common.request.UserRequest;
import com.kyn.myproject.demo.common.util.StringUtils;
import com.kyn.myproject.demo.shiro_demo.config.ProjectProperties;
import com.kyn.myproject.demo.shiro_demo.domain.User;
import com.kyn.myproject.demo.shiro_demo.service.RoleService;
import com.kyn.myproject.demo.shiro_demo.utils.CaptchaUtil;
import com.kyn.myproject.demo.shiro_demo.utils.ResultVoUtil;
import com.kyn.myproject.demo.shiro_demo.utils.SpringContextUtil;
import com.kyn.myproject.demo.shiro_demo.utils.URL;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Kangyanan
 * @Description: 用户操作Controller
 * @date 2021/1/28 17:42
 */
@RestController
public class LoginController implements ErrorController {

    @Autowired
    private RoleService roleService;


    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String toLogin(Model model) {
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        model.addAttribute("isCaptcha", properties.isCaptchaOpen());
        return "/login";
    }

    /**
     * 实现登录
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public ProjectResult login(@RequestBody UserRequest userRequest) {
        String username=userRequest.getUserName();
        String password=userRequest.getPassword();
        String captcha=userRequest.getCaptcha();
        String rememberMe=userRequest.getRememberMe();
        // 判断账号密码是否为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ProjectException(SystemErrorCode.USER_NAME_PWD_NULL);
        }
        // 判断验证码是否正确
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        if (properties.isCaptchaOpen()) {
            Session session = SecurityUtils.getSubject().getSession();
            String sessionCaptcha = (String) session.getAttribute("captcha");
            if (StringUtils.isEmpty(captcha) || StringUtils.isEmpty(sessionCaptcha)
                    || !captcha.toUpperCase().equals(sessionCaptcha.toUpperCase())) {
                throw new ProjectException(SystemErrorCode.USER_CAPTCHA_ERROR);
            }
            session.removeAttribute("captcha");
        }
        // 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();

        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 3.执行登录，进入自定义Realm类中
        try {
            // 判断是否记住我
            if (rememberMe != null) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            subject.login(token);

            // 判断是否拥有后台角色
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (roleService.existsUserOk(user.getId())) {
                return ResultVoUtil.success("登录成功", new URL("/"));
            } else {
                SecurityUtils.getSubject().logout();
                return ResultVoUtil.error("未分配权限！");
            }
        } catch (LockedAccountException e) {
            return ResultVoUtil.error("该账号已被冻结");
        } catch (AuthenticationException e) {
            return ResultVoUtil.error("用户名或密码错误");
        }
    }

    /**
     * 验证码图片
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头信息，通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        response.setContentType("image/jpeg");
        // 获取验证码
        String code = CaptchaUtil.getRandomCode();
        // 将验证码输入到session中，用来验证
        request.getSession().setAttribute("captcha", code);
        // 输出到web页面
        ImageIO.write(CaptchaUtil.genCaptcha(code), "jpg", response.getOutputStream());
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    /**
     * 权限不足页面
     */
    @GetMapping("/noAuth")
    public String noAuth() {
        return "/system/main/noAuth";
    }

    /**
     * 自定义错误页面
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * 处理错误页面
     */
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMsg = "好像出错了呢！";
        if (statusCode == 404) {
            errorMsg = "页面找不到了！好像是去火星了~";
        }

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("msg", errorMsg);
        return "/system/main/error";
    }
}

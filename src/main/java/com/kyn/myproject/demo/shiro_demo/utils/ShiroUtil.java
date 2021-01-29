package com.kyn.myproject.demo.shiro_demo.utils;


import com.kyn.myproject.demo.shiro_demo.domain.Role;
import com.kyn.myproject.demo.shiro_demo.domain.User;
import com.kyn.myproject.demo.shiro_demo.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:18
 */
public class ShiroUtil {
    /**
     * 加密算法
     */
    public final static String HASH_ALGORITHM_NAME = EncryptUtil.HASH_ALGORITHM_NAME;

    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = EncryptUtil.HASH_ITERATIONS;

    /**
     * 加密处理（64位字符）
     * 备注：采用自定义的密码加密方式，其原理与SimpleHash一致，
     * 为的是在多个模块间可以使用同一套加密方式，方便共用系统用户。
     *
     * @param password 密码
     * @param salt     密码盐
     */
    public static String encrypt(String password, String salt) {
        return EncryptUtil.encrypt(password, salt, HASH_ALGORITHM_NAME, HASH_ITERATIONS);
    }

    public static void main(String[] args) {
        String str=encrypt("123456","jljfoiure");
        System.out.println(str);
    }
    /**
     * 获取随机盐值
     */
    public static String getRandomSalt() {
        return EncryptUtil.getRandomSalt();
    }

    /**
     * 获取当前用户角色列表
     */
    public static Set<Role> getSubjectRoles() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 如果用户为空，则返回空列表
        if (user == null) {
            user = new User();
        }
        RoleService roleService = SpringContextUtil.getBean(RoleService.class);

        Set<Role> set=roleService.getUserOkRoleList(user.getId());
        user.setRoles(set);

        return user.getRoles();
    }

    /**
     * 获取用户IP地址
     */
    public static String getIp() {
        HttpServletRequest request = HttpServletUtil.getRequest();
        // 反向代理时获取真实ip
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

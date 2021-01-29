package com.kyn.myproject.demo.shiro_demo.config;

import com.kyn.myproject.demo.common.util.StringUtils;
import com.kyn.myproject.demo.shiro_demo.constant.AdminConst;
import com.kyn.myproject.demo.shiro_demo.domain.Role;
import com.kyn.myproject.demo.shiro_demo.domain.User;
import com.kyn.myproject.demo.shiro_demo.enums.StatusEnum;
import com.kyn.myproject.demo.shiro_demo.service.UserService;
import com.kyn.myproject.demo.shiro_demo.utils.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 14:22
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 根据登录用户账号获取数据库中的用户名密码
        User user = userService.getByName(token.getUsername());

        // 判断用户名是否存在/冻结
        if (user == null) {
            throw new UnknownAccountException();
        } else if (user.getStatus().equals(StatusEnum.FREEZED.getCode())) {
            throw new LockedAccountException();
        }

        // 对盐进行转换
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());

        /* 传入密码自动判断是否正确
         * 参数1：传入对象给Principal
         * 参数2：正确的用户密码
         * 参数3：加盐处理
         * 参数4：固定写法
         */
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }
    /**
     * 授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户Principal对象
        User user = (User) principal.getPrimaryPrincipal();

        //管理员拥有所有权限
        if (user.getId().equals(AdminConst.ADMIN_ID)) {
            info.addRole(AdminConst.ADMIN_ROLE_NAME);
            info.addStringPermission("*:*:*");
            return info;
        }

        //赋予角色拥有的权限
        Set<Role> roles = ShiroUtil.getSubjectRoles();
        roles.forEach(role -> {
            info.addRole(role.getName());
            role.getMenus().forEach(menu -> {
                String perms = menu.getPerms();
                if (menu.getStatus().equals(StatusEnum.OK.getCode())
                        && !StringUtils.isEmpty(perms) && !perms.contains("*")) {
                    info.addStringPermission(perms);
                }
            });
        });
        return info;
    }

    /**
     * 自定义密码验证匹配器
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new SimpleCredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
                SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authenticationInfo;
                // 获取明文密码及密码盐
                String password = String.valueOf(token.getPassword());
                String salt = CodecSupport.toString(info.getCredentialsSalt().getBytes());

                return equals(ShiroUtil.encrypt(password, salt), info.getCredentials());
            }
        });
    }
}


package com.kyn.myproject.demo.shiro_demo.config;

import com.kyn.myproject.demo.shiro_demo.UserAuthFilter;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Kangyanan
 * @Description: shiro配置类
 *  过滤规则（注意优先级）
 *  —anon 无需认证(登录)可访问
 * 	—authc 必须认证才可访问
 * 	—perms[标识] 拥有资源权限才可访问
 * 	—role 拥有角色权限才可访问
 * 	—user 认证和自动登录可访问
 * @date 2021/1/29 10:24
 */
@Configuration
public class ShiroConfig {

    /**
     * 过滤规则配置
     * @param securityManager
     * @param properties
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, ShiroProjectProperties properties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 添加自定义拦截器，重写user认证方式，处理session超时问题
         */
        HashMap<String, Filter> myFilters = new HashMap<>(16);
        myFilters.put("userAuth", new UserAuthFilter());
        shiroFilterFactoryBean.setFilters(myFilters);

        /**
         * 过滤规则
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/noAuth", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/lib/**", "anon");
        filterMap.put("/favicon.ico", "anon");

        //拦截根目录下所有路径，需要放行的路径必须在之前添加
        filterMap.put("/**", "userAuth");
        //设置过滤规则链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权错误页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        return shiroFilterFactoryBean;
    }

    /**
     * 自定义默认WebSecurityManager
     * @param myRealm
     * @param rememberMeManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm,
                                                                  CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * 自定义的Realm
     */
    @Bean
    public MyRealm getRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    /**
     * rememberMe管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        RememberMeManager manager = new RememberMeManager();
        manager.setCipherKey(Base64.decode("TWKwerShsdhflsdfsdfd=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 创建一个简单的Cookie对象
     */
    @Bean
    public SimpleCookie rememberMeCookie(ShiroProjectProperties properties) {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        // cookie记住登录信息时间，默认7天
        simpleCookie.setMaxAge(properties.getRememberMeTimeout() * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * 开启shrio授权注解使用方式
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}


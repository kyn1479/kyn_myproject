package com.kyn.myproject.demo.common.request;

import lombok.Data;

/**
 * @author Kangyanan
 * @Description: 用户登录请求对象
 * @date 2021/1/29 10:11
 */
@Data
public class UserRequest implements ProjectRequest{
    private String userName;
    private String password;
    private String rememberMe;
    private String captcha;
}

package com.kyn.myproject.demo.shiro_demo.service;

import com.kyn.myproject.demo.shiro_demo.domain.User;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 15:22
 */
public interface UserService {
    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    User getByName(String username);

}

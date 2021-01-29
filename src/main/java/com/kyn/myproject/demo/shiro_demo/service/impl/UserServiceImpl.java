package com.kyn.myproject.demo.shiro_demo.service.impl;

import com.kyn.myproject.demo.shiro_demo.domain.User;
import com.kyn.myproject.demo.shiro_demo.repository.UserRepository;
import com.kyn.myproject.demo.shiro_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByName(String username) {
        return userRepository.findByUsername(username);
    }
}

package com.kyn.myproject.demo.shiro_demo.repository;

import com.kyn.myproject.demo.shiro_demo.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:38
 */
public interface UserRepository extends BaseRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    public User findByUsername(String username);
}

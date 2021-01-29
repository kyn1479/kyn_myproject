package com.kyn.myproject.demo.shiro_demo.service;

import com.kyn.myproject.demo.shiro_demo.domain.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:25
 */
public interface RoleService {
    /**
     * 获取用户角色列表
     * @param id 用户ID
     * @return 角色列表
     */
    @Transactional
    Set<Role> getUserOkRoleList(Long id);

    /**
     * 判断指定的用户是否存在角色
     * @param id 用户ID
     * @return 是否存在角色
     */
    Boolean existsUserOk(Long id);
}

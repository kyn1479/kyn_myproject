package com.kyn.myproject.demo.shiro_demo.repository;

import com.kyn.myproject.demo.shiro_demo.domain.Role;

import java.util.Set;


/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 18:12
 */

public interface RoleRepository extends BaseRepository<Role,Long> {
    /**
     * 查询指定用户的角色列表
     * @param id 用户ID
     * @param status 角色状态
     * @return 角色列表
     */
    public Set<Role> findByUsers_IdAndStatus(Long id, Byte status);

    /**
     * 判断指定的用户是否存在角色
     * @param id 用户ID
     * @param status 角色状态
     * @return 是否存在角色
     */
    public Boolean existsByUsers_IdAndStatus(Long id, Byte status);
}

package com.kyn.myproject.demo.shiro_demo.service.impl;

import com.kyn.myproject.demo.shiro_demo.domain.Role;
import com.kyn.myproject.demo.shiro_demo.enums.StatusEnum;
import com.kyn.myproject.demo.shiro_demo.repository.RoleRepository;
import com.kyn.myproject.demo.shiro_demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:27
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<Role> getUserOkRoleList(Long id) {
        Byte status = StatusEnum.OK.getCode();
        return roleRepository.findByUsers_IdAndStatus(id, status);
    }

    /**
     * 判断指定的用户是否存在角色
     * @param id 用户ID
     */
    @Override
    public Boolean existsUserOk(Long id) {
        Byte status = StatusEnum.OK.getCode();
        return roleRepository.existsByUsers_IdAndStatus(id, status);
    }
}

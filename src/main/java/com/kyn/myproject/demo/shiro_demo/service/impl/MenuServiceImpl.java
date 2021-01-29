package com.kyn.myproject.demo.shiro_demo.service.impl;

import com.kyn.myproject.demo.shiro_demo.domain.Menu;
import com.kyn.myproject.demo.shiro_demo.repository.MenuRepository;
import com.kyn.myproject.demo.shiro_demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/29 16:20
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    /**
     * 获取菜单列表数据
     * @param example 查询实例
     */
    @Override
    public List<Menu> getListByExample(Example<Menu> example) {
        return menuRepository.findAll(example);
    }
}

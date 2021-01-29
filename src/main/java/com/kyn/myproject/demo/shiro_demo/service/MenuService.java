package com.kyn.myproject.demo.shiro_demo.service;

import com.kyn.myproject.demo.shiro_demo.domain.Menu;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/29 16:20
 */
public interface MenuService {
    /**
     * 获取菜单列表数据
     * @param example 查询实例
     * @return 菜单列表
     */
    List<Menu> getListByExample(Example<Menu> example);
}

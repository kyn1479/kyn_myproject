package com.kyn.myproject.demo.shiro_demo.controller;

import com.kyn.myproject.demo.common.entity.ProjectResult;
import com.kyn.myproject.demo.shiro_demo.domain.Menu;
import com.kyn.myproject.demo.shiro_demo.service.MenuService;
import com.kyn.myproject.demo.shiro_demo.utils.ResultVoUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/29 15:16
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    /**
     * 菜单数据列表
     */
    @GetMapping("/list")
    @RequiresPermissions("system:menu:index")
    @ResponseBody
    public ProjectResult list(Menu menu) {
        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("title", match -> match.contains());
        // 获取菜单列表
        Example<Menu> example = Example.of(menu, matcher);
        List<Menu> list = menuService.getListByExample(example);
        return ResultVoUtil.success(list);
    }
}

package com.kyn.myproject.demo.common.template;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 业务流程模板
 * @date 2021/1/22 9:56
 */
public interface Template {
    void execute(ProjectContext context);
}
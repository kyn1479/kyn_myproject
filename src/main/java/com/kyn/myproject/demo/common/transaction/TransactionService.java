package com.kyn.myproject.demo.common.transaction;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 处理服务基类
 * @date 2021/1/22 17:14
 */
public interface TransactionService {
    void execute(ProjectContext context);
}

package com.kyn.myproject.demo.message_parse_engine.service;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 消息解析引擎 使用groovy脚本进行解析
 * @date 2021/1/19 17:43
 */
public interface MessageParseEngine {

    void messageParse(ProjectContext context);

}

package com.kyn.myproject.demo.message_parse_engine.service;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 消息解析器 用groovy脚本来实现
 * @date 2021/1/19 18:15
 */
public interface MessageParser {
    /**
     * 解析
     * @param context
     * @param message
     * @return
     */
    Object parse(ProjectContext context, Object message);
}

package com.kyn.myproject.demo.message_parse_engine.service;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 消息解析引擎 使用groovy脚本进行解析 （解析输入报文、通道返回报文）
 * @date 2021/1/19 17:43
 */
public interface MessageParseEngine {

    /**
     * 消息解析
     * @param context
     */
    void messageParse(ProjectContext context);

}

package com.kyn.myproject.demo.message_parse_engine.service;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 消息接收器
 * @date 2021/1/19 22:36
 */
public interface MessageReceiver {

    /**
     * 消息接收
     * @param context
     */
    void receive(ProjectContext context);
}

package com.kyn.myproject.demo.message_parse_engine.service.impl;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.message_parse_engine.service.MessageParseEngine;
import com.kyn.myproject.demo.message_parse_engine.service.MessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 消息接收器实现类
 * @date 2021/1/19 22:38
 */
@Component
public class MessageReceiverImpl implements MessageReceiver {

    /**
     * 消息解析引擎
     */
    @Autowired
    private MessageParseEngine messageParseEngine;

    @Override
    public void receive(ProjectContext context) {
        //1、报文解析
        messageParseEngine.messageParse(context);
    }
}

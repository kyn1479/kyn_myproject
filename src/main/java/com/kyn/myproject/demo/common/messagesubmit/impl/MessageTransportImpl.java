package com.kyn.myproject.demo.common.messagesubmit.impl;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.CsFlagEnum;
import com.kyn.myproject.demo.common.messagesubmit.MessageTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 报文提交实现
 * @date 2021/1/22 18:23
 */
@Component
public class MessageTransportImpl implements MessageTransport {
    private static Logger logger = LoggerFactory.getLogger(MessageTransportImpl.class);


    @Override
    public void submit(ProjectContext context) {
        submit(context, CsFlagEnum.SERVER);
    }

    @Override
    public void submit(ProjectContext context, CsFlagEnum csFlagEnum) {

    }
}

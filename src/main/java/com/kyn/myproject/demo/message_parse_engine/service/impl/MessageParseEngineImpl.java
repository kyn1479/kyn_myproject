package com.kyn.myproject.demo.message_parse_engine.service.impl;

import com.kyn.myproject.demo.common.cache.GroovyScriptCache;
import com.kyn.myproject.demo.common.entity.MessageDescription;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.ProcessPhaseEnum;
import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.exception.ProjectException;
import com.kyn.myproject.demo.common.util.BeanUtils;
import com.kyn.myproject.demo.message_parse_engine.service.MessageParseEngine;
import com.kyn.myproject.demo.message_parse_engine.service.MessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 消息解析引擎 实现类
 * @date 2021/1/19 22:39
 */
@Component
public class MessageParseEngineImpl implements MessageParseEngine {
    private static final Logger logger = LoggerFactory.getLogger(MessageParseEngineImpl.class);

    @Autowired
    private GroovyScriptCache groovyScriptCache;

    @Override
    public void messageParse(ProjectContext context) {
        logger.info("交易-开始报文解析");
        MessageDescription messageDescription = context.getMessageDescription();
        //1.获取待解析报文
        Object parseMessage = getParseMessage(messageDescription);

        //2.获取报文解析器(从缓存中加载groovy脚本,脚本统一实现MessageParser接口)
        MessageParser messageParser = findParser(context);

        //3.进行报文解析
        Object obj = messageParser.parse(context, parseMessage);
        logger.info("交易-报文解析结果：{}",BeanUtils.beanToMap(obj));
        messageDescription.putDatas(BeanUtils.beanToMap(obj));
    }

    /**
     * 获取待解析报文
     * @param messageDescription
     * @return
     */
    public Object getParseMessage(MessageDescription messageDescription) {
        Object message = null;
        ProcessPhaseEnum processPhase = messageDescription.getProcessPhase();
        switch (processPhase) {
            case CLIENT_REQUEST_RECEIVE://客户端请求
                message = messageDescription.getClientRequestMessageEnvelope().getContent();
                break;
            case SERVER_RESPONSE_RECEIVE://服务端响应
                message = messageDescription.getServerResponseMessageEnvelope().getContent();
                break;
            default:
                logger.warn("processPhase = {} 没有加 switch.", processPhase);
                throw new ProjectException(SystemErrorCode.SYSTEM_ERROR);
        }
        return message;
    }

    /**
     * 获取报文解析器
     * @param context
     * @return
     */
    private MessageParser findParser(ProjectContext context) {
        String parserName = getParserName(context);
        logger.info("交易-报文解析器({})", parserName);
        MessageParser messageParser = groovyScriptCache.get(parserName);
        if (messageParser == null) {
            logger.error("未找到交易的报文解析器({})", parserName);
            throw new ProjectException(SystemErrorCode.SYSTEM_ERROR);
        }
        return messageParser;
    }

    /**
     * 获取报文解析器名称
     * @param context
     * @return
     */
    private String getParserName(ProjectContext context) {
        MessageDescription messageDescription = context.getMessageDescription();
        return messageDescription.getCommunicationEntity().getMessageParserId();
    }

}

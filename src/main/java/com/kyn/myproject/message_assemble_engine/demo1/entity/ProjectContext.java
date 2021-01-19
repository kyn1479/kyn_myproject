package com.kyn.myproject.message_assemble_engine.demo1.entity;


import com.kyn.myproject.message_assemble_engine.demo1.enums.ParamType;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 请求上下文
 * @date 2021/1/18 15:12
 */
public class ProjectContext {

    /**
     * 默认构造方法
     */
    public ProjectContext() {
        super();
    }
    /**
     * 存储参数的map
     */
    private final Map<ParamType, Object> paramMap = new EnumMap<ParamType, Object>(ParamType.class);

    /**
     * 设置请求消息
     * @param messageDescription
     */
    public void setMessageDescription(MessageDescription messageDescription) {
        paramMap.put(ParamType.MESSAGE_DESCRIPTION, messageDescription);
    }

    /**
     * 获取请求消息
     * @return
     */
    public MessageDescription getMessageDescription() {
        return (MessageDescription) paramMap.get(ParamType.MESSAGE_DESCRIPTION);
    }
}

package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 协议处理服务
 * @date 2021/1/20 17:36
 */
public interface ProtocolService {
    /**
     * @Description 转换进来消息
     * @Params
     * @Return MessageDescription 消息描述
     * @Exceptions
     */
    void convertInMessage(ProjectContext context);

    /**
     * @Description 转换出去消息
     * @Params
     * @Return Object 转换出去的消息
     * @Exceptions
     */
    void convertOutMessage(ProjectContext context);

}

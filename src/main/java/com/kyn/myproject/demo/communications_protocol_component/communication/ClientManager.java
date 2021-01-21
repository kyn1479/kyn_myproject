package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 客户端管理接口
 * @date 2021/1/20 16:14
 */
public interface ClientManager {

    /**
     * 消息发送
     * @param context
     */
    void messageSend(ProjectContext context);
}

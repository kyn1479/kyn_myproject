package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 17:37
 */
public abstract class ProtocolClientService implements ProtocolService {
    /**
     * @Description 消息处理
     * @Params
     * @Return void
     * @Exceptions
     */
    public void hand(ProjectContext context) {

        //1、转换需要发送（出去）的消息；
        convertOutMessage(context);

        //2、消息发送；
        messageSend(context);

        //3、转换返回（进来）的消息；
        convertInMessage(context);
    }

    /**
     * @Description 消息发送
     * @Params
     * @Return void
     * @Exceptions
     */
    protected abstract void messageSend(ProjectContext context);
}

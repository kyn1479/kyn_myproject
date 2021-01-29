package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.template.TemplateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description: 协议处理服务抽象类-服务端
 * @date 2021/1/22 9:45
 */
@Service("protocolServerService")
public abstract class ProtocolServerService implements ProtocolService {

    /**
     * 交易业务模板管理
     */
    @Autowired
    private TemplateManager templateManager;

    /**
     * 消息处理，抽象为三个阶段：
     * 1、消息读取，读取成object，供后面脚本进行具体内容解析；
     * 2、消息接收到逻辑处理，包括解析，发送，更多的交由效果引擎处理；
     * 3、需要返回的消息处理，转换返回的消息格式；
     * @param context
     */
    public void hand(ProjectContext context) {

        //1、转换接收到（进来）的消息；
        convertInMessage(context);

        //2、消息发送；
        messageReceive(context);

        //3、转换需要返回消息；
        convertOutMessage(context);
    }


    protected void messageReceive(ProjectContext context) {
        templateManager.getTemplate(context.getClientTransCode()).execute(context);
    }

}

package com.kyn.myproject.message_assemble_engine.demo1.service.message;


import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageEnvelope;
import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageTemplate;
import com.kyn.myproject.message_assemble_engine.demo1.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 模板引擎接口类
 * @date 2021/1/18 15:05
 */
public interface MessageAssembleEngine {

    /**
     * @Description 报文组装
     * @Params
     * @Return void
     * @Exceptions
     */
    MessageEnvelope messageAssemble(MessageTemplate messageTemplate, ProjectContext projectContext);
}

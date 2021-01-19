package com.kyn.myproject.demo.message_assemble_engine.service;


import com.kyn.myproject.demo.common.entity.MessageEnvelope;
import com.kyn.myproject.demo.common.entity.MessageTemplate;
import com.kyn.myproject.demo.common.entity.ProjectContext;

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

package com.kyn.myproject.message_assemble_engine.demo1.service.message.impl;

import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageEnvelope;
import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageTemplate;
import com.kyn.myproject.message_assemble_engine.demo1.entity.ProjectContext;
import com.kyn.myproject.message_assemble_engine.demo1.service.message.MessageAssembleEngine;
import com.kyn.myproject.message_assemble_engine.demo1.service.message.VelocityContextHelper;
import com.kyn.myproject.message_assemble_engine.demo1.util.MapUtils;
import com.kyn.myproject.message_assemble_engine.demo1.util.StringUtils;
import com.kyn.myproject.message_assemble_engine.demo1.util.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 模板引擎实现类
 * @date 2021/1/18 15:07
 */
@Component
public class MessageAssembleEngineImpl implements MessageAssembleEngine {

    /**
     * velocity上下文生成辅助类
     */
    @Autowired
    private VelocityContextHelper velocityContextHelper;

    @Override
    public MessageEnvelope messageAssemble(MessageTemplate messageTemplate, ProjectContext projectContext) {
        MessageEnvelope messageEnvelope = new MessageEnvelope();
        VelocityContext context = velocityContextHelper.fillContext(messageTemplate, projectContext);
        //报文体组装
        if (StringUtils.isNotBlank(messageTemplate.getMainTemplate())) {
            String messageBody = VelocityUtil.evaluate(context, messageTemplate.getMainTemplate());
            messageEnvelope.setContent(messageBody);
        }
        //报文头组装
        if (StringUtils.isNotBlank(messageTemplate.getHeaderTemplate())) {
            String headerPrototype = VelocityUtil.evaluate(context, messageTemplate.getHeaderTemplate());
            Map<String, String> headers = MapUtils.covertText2MapByRule(headerPrototype);
            messageEnvelope.setExtraContent(headers);
        }
        return messageEnvelope;
    }
}

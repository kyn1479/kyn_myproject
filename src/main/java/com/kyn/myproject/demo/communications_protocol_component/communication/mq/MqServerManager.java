package com.kyn.myproject.demo.communications_protocol_component.communication.mq;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.communications_protocol_component.communication.ServerManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 消息队列服务端管理
 * @date 2021/1/23 16:16
 */
@Component
public class MqServerManager implements ServerManager, InitializingBean {
    @Override
    public void accept(ProjectContext context) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

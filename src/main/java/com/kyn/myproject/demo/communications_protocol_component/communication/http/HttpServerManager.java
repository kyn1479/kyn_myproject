package com.kyn.myproject.demo.communications_protocol_component.communication.http;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.ProtocolTypeEnum;
import com.kyn.myproject.demo.communications_protocol_component.communication.ServerManager;
import com.kyn.myproject.demo.communications_protocol_component.communication.ServiceManagerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: http服务端管理类
 * @date 2021/1/22 10:31
 */
@Component
public class HttpServerManager implements ServerManager, InitializingBean {
    @Autowired
    private HttpServerService httpServerService;


    @Override
    public void accept(ProjectContext context) {
        httpServerService.hand(context);
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceManagerFactory.registerServerManager(ProtocolTypeEnum.HTTP, this);
    }
}

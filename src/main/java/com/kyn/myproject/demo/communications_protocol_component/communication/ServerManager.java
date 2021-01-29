package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.entity.ProjectContext;

/**
 * @author Kangyanan
 * @Description: 服务端管理工具接口
 * @date 2021/1/20 16:31
 */
public interface ServerManager {
    /**
     * 接收客户端请求
     * @param context
     */
    void accept(ProjectContext context);
}

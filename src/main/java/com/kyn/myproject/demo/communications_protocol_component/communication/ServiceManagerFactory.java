package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.enums.ProtocolTypeEnum;
import com.kyn.myproject.demo.communications_protocol_component.communication.ClientManager;
import com.kyn.myproject.demo.communications_protocol_component.communication.ServerManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: Client和Server的管理工厂
 * @date 2021/1/20 16:30
 */
public class ServiceManagerFactory {
    /**
     * 客户端管理，根据协议（http,mq,tcp...进行缓存）
     */
    private static Map<String, ClientManager> clientManagers = new HashMap<String, ClientManager>();

    /**
     * 服务端管理，根据协议（http,mq,tcp...进行缓存）
     */
    private static Map<String, ServerManager> serverManagers = new HashMap<String, ServerManager>();

    /**
     * 根据协议获取客户端管理
     * @param protocol
     * @return
     */
    public static ClientManager getClientManager(ProtocolTypeEnum protocol) {
        return clientManagers.get(protocol.getCode());
    }

    /**
     * 注册客户端协议
     * @param protocol
     * @param clientManager
     */
    public static void registerClientManager(ProtocolTypeEnum protocol, ClientManager clientManager) {
        clientManagers.put(protocol.getCode(), clientManager);
    }

    /**
     * 根据协议获取服务端管理
     * @param protocol
     * @return
     */
    public static ServerManager getServerManager(ProtocolTypeEnum protocol) {
        return serverManagers.get(protocol.getCode());
    }

    /**
     * 注册客户端协议
     * @param protocol
     * @param serverManager
     */
    public static void registerServerManager(ProtocolTypeEnum protocol, ServerManager serverManager) {
        serverManagers.put(protocol.getCode(), serverManager);
    }
}

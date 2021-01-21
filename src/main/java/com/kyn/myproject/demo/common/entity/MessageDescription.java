package com.kyn.myproject.demo.common.entity;


import com.kyn.myproject.demo.common.enums.ProcessPhaseEnum;
import com.kyn.myproject.demo.common.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description:  请求消息信息，包含一次请求所有基本信息
 * @date 2021/1/18 15:18
 */
public class MessageDescription {
    /**
     * 支付渠道编码
     */
    private String instCode;

    /**
     * 交易码
     */
    private String transCode;

    /**
     * 客户端通信通道
     */
    private CommunicationInstitution clientInstitution;

    /**
     * 服务端通信通道
     */
    private CommunicationInstitution serverInstitution;

    /**
     * 接收到客户端的请求报文
     */
    private MessageEnvelope clientRequestMessageEnvelope;

    /**
     * 返回到客户端请求报文
     */
    private MessageEnvelope clientResponseMessageEnvelope;

    /**
     * 发送到服务端的请求报文
     */
    private MessageEnvelope serverRequestMessageEnvelope;

    /**
     * 接收到服务端返回的请求报文
     */
    private MessageEnvelope serverResponseMessageEnvelope;

    /**
     * 所有运行时KV数据，包括接收到的数据和返回的数据；
     */
    private Map<String, Object> datas = new HashMap<>();

    /**
     * 通用结果
     */
    private ProjectResult projectResult;

    /**
     * 当前处理阶段
     */
    private ProcessPhaseEnum processPhase;

    /**
     * 当前处理的通讯（区分客户端、服务端）
     */
    private CommunicationEntity communicationEntity;


    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public CommunicationInstitution getClientInstitution() {
        return clientInstitution;
    }

    public void setClientInstitution(CommunicationInstitution clientInstitution) {
        this.clientInstitution = clientInstitution;
    }

    public CommunicationInstitution getServerInstitution() {
        return serverInstitution;
    }

    public void setServerInstitution(CommunicationInstitution serverInstitution) {
        this.serverInstitution = serverInstitution;
    }

    public MessageEnvelope getClientRequestMessageEnvelope() {
        return clientRequestMessageEnvelope;
    }

    public void setClientRequestMessageEnvelope(MessageEnvelope clientRequestMessageEnvelope) {
        this.clientRequestMessageEnvelope = clientRequestMessageEnvelope;
    }

    public MessageEnvelope getClientResponseMessageEnvelope() {
        return clientResponseMessageEnvelope;
    }

    public void setClientResponseMessageEnvelope(MessageEnvelope clientResponseMessageEnvelope) {
        this.clientResponseMessageEnvelope = clientResponseMessageEnvelope;
    }

    public MessageEnvelope getServerRequestMessageEnvelope() {
        return serverRequestMessageEnvelope;
    }

    public void setServerRequestMessageEnvelope(MessageEnvelope serverRequestMessageEnvelope) {
        this.serverRequestMessageEnvelope = serverRequestMessageEnvelope;
    }

    public MessageEnvelope getServerResponseMessageEnvelope() {
        return serverResponseMessageEnvelope;
    }

    public void setServerResponseMessageEnvelope(MessageEnvelope serverResponseMessageEnvelope) {
        this.serverResponseMessageEnvelope = serverResponseMessageEnvelope;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public ProjectResult getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(ProjectResult projectResult) {
        this.projectResult = projectResult;
    }

    public ProcessPhaseEnum getProcessPhase() {
        return processPhase;
    }

    public void setProcessPhase(ProcessPhaseEnum processPhase) {
        this.processPhase = processPhase;
    }

    public CommunicationEntity getCommunicationEntity() {
        return communicationEntity;
    }

    public void setCommunicationEntity(CommunicationEntity communicationEntity) {
        this.communicationEntity = communicationEntity;
    }

    /**
     * 将数据存入data
     * @param datas
     */
    public void putDatas(Map<String, Object> datas) {
        for (Map.Entry<String, Object> entry : datas.entrySet()) {
            if (entry.getValue() instanceof Map) {
                this.datas.putAll((Map) entry.getValue());
            } else {
                Object value = entry.getValue();
                Object oldValue = this.datas.get(entry.getKey());
                if (StringUtils.isNotBlank(value) || StringUtils.isBlank(oldValue)) {
                    this.datas.put(entry.getKey(), value);
                }
            }
        }
    }
}

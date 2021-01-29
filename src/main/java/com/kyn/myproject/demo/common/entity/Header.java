package com.kyn.myproject.demo.common.entity;

import java.io.Serializable;

/**
 * @author Kangyanan
 * @Description: 报文头
 * @date 2021/1/20 15:13
 */
public class Header implements Serializable {

    /**
     * 场景码:用于区分交易码相同、场景不同的 特例
     */
    private String sceneCode;

    /**
     * 交易编码
     */
    private String transCode;
    /**
     * 交易类型
     */
    private String transType;
    /**
     * 渠道系统编码
     */
    private String channelCode;
    /**
     * 渠道系统流水号(渠道系统唯一)
     */
    private String channelTransNo;
    /**
     * 渠道系统交易时间(yyyyMMddHHmmss)
     */
    private String channelDateTime;
    /**
     * 交易处理状态
     */
    private boolean success;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    private String systemId;
    /**
     * 默认构造函数
     */
    public Header(){
    }

    public Header(String transCode, String transType){
        this.transCode = transCode;
        this.transType = transType;
        this.channelCode = "paycommon-starter";
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelTransNo() {
        return channelTransNo;
    }

    public void setChannelTransNo(String channelTransNo) {
        this.channelTransNo = channelTransNo;
    }

    public String getChannelDateTime() {
        return channelDateTime;
    }

    public void setChannelDateTime(String channelDateTime) {
        this.channelDateTime = channelDateTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}


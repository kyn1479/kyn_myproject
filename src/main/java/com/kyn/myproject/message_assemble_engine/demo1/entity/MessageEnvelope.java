package com.kyn.myproject.message_assemble_engine.demo1.entity;


import com.kyn.myproject.message_assemble_engine.demo1.enums.EncodeEnum;
import com.kyn.myproject.message_assemble_engine.demo1.enums.MessageFormatEnum;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 报文载体 请求、返回报文
 * @date 2021/1/18 15:19
 */
public class MessageEnvelope {
    /**
     * 报文正文格式
     */
    private MessageFormatEnum messageFormat;

    /**
     * 消息编码
     */
    private EncodeEnum encode;

    /**
     * 报文正文信息
     */
    private Object content;

    /**
     * 报文附加信息，例如HTTP报文头的附加信息
     */
    private Map<String, String> extraContent;

    /**
     * 网关返回码
     */
    private String code;

    /**
     * 网关返回码描述
     */
    private String message;

    public MessageFormatEnum getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(MessageFormatEnum messageFormat) {
        this.messageFormat = messageFormat;
    }

    public EncodeEnum getEncode() {
        return encode;
    }

    public void setEncode(EncodeEnum encode) {
        this.encode = encode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Map<String, String> getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(Map<String, String> extraContent) {
        this.extraContent = extraContent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.kyn.myproject.message_assemble_engine.demo1.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 报文类型枚举类
 * @date 2021/1/18 15:19
 */
public enum MessageFormatEnum {
    BYTE("byte", ""),
    TEXT("text", "text/plain"),
    FORM("form", "application/x-www-form-urlencoded"),
    JSONTOFORM("jsontoform", "application/x-www-form-urlencoded"),
    JSON("json", "application/json"),
    HTML("html", "text/html"),
    XML("xml", "text/xml"),
    FILE("file",""),;

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>MessageFormatEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private MessageFormatEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return MessageFormatEnum
     */
    public static MessageFormatEnum getByCode(String code) {
        for (MessageFormatEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<MessageFormatEnum>
     */
    public List<MessageFormatEnum> getAllEnum() {
        List<MessageFormatEnum> list = new ArrayList<MessageFormatEnum>();
        for (MessageFormatEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (MessageFormatEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}

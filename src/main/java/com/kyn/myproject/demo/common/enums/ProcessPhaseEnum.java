package com.kyn.myproject.demo.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 当前处理阶段
 * @date 2021/1/18 15:25
 */
public enum ProcessPhaseEnum {
    CLIENT_REQUEST_RECEIVE("CLIENT_REQUEST_RECEIVE", "接收客户端请求阶段"),

    SERVER_REQUEST_SEND("SERVER_REQUEST_SEND", "服务端消息发送阶段"),

    SERVER_RESPONSE_RECEIVE("SERVER_RESPONSE_RECEIVE", "接收服务端返回报文"),

    CLIENT_RESPONSE_SEND("CLIENT_RESPONSE_SEND", "客户端请求返回阶段"),

    ;

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>ProcessPhaseEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private ProcessPhaseEnum(String code, String message) {
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
     * @return ProcessPhaseEnum
     */
    public static ProcessPhaseEnum getByCode(String code) {
        for (ProcessPhaseEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ProcessPhaseEnum>
     */
    public List<ProcessPhaseEnum> getAllEnum() {
        List<ProcessPhaseEnum> list = new ArrayList<ProcessPhaseEnum>();
        for (ProcessPhaseEnum _enum : values()) {
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
        for (ProcessPhaseEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}

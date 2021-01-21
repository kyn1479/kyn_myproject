package com.kyn.myproject.demo.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 通信协议枚举类
 * @date 2021/1/20 16:32
 */
public enum ProtocolTypeEnum {
    HTTP("01", "HTTP协议"),
    HTTPS("02", "HTTPS协议"),
    FTP("03", "FTP协议"),
    SFTP("04", "SFTP协议"),
    WEBSERVICE("05", "WEBSERVICE协议"),
    HESSIAN("06", "HESSIAN协议"),
    TCP("07", "TCP协议"),
    MQ("08", "MQ协议");

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>ProtocolTypeEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private ProtocolTypeEnum(String code, String message) {
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
     * @return ProtocolTypeEnum
     */
    public static ProtocolTypeEnum getByCode(String code) {
        for (ProtocolTypeEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ProtocolTypeEnum>
     */
    public List<ProtocolTypeEnum> getAllEnum() {
        List<ProtocolTypeEnum> list = new ArrayList<ProtocolTypeEnum>();
        for (ProtocolTypeEnum _enum : values()) {
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
        for (ProtocolTypeEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}

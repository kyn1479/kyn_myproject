package com.kyn.myproject.demo.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: http method枚举
 * @date 2021/1/20 17:32
 */
public enum HttpMethodEnum {
    ALL("00", "全部"),
    GET("01", "get方式"),
    POST("02", "post方式");

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
     * @param code
     * @param message
     */
    private HttpMethodEnum(String code, String message) {
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
     * @param code
     * @return ProtocolTypeEnum
     */
    public static HttpMethodEnum getByCode(String code) {
        for (HttpMethodEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     * @return List<ProtocolTypeEnum>
     */
    public List<HttpMethodEnum> getAllEnum() {
        List<HttpMethodEnum> list = new ArrayList<HttpMethodEnum>();
        for (HttpMethodEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (HttpMethodEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}

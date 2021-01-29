package com.kyn.myproject.demo.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 客户端/服务端标识
 * @date 2021/1/22 18:22
 */
public enum CsFlagEnum {
    CLIENT("01", "客户端"),
    SERVER("02", "服务端");

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>DictEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private CsFlagEnum(String code, String message) {
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
     * @return DictEnum
     */
    public static CsFlagEnum getByCode(String code) {
        for (CsFlagEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<DictEnum>
     */
    public List<CsFlagEnum> getAllEnum() {
        List<CsFlagEnum> list = new ArrayList<CsFlagEnum>();
        for (CsFlagEnum _enum : values()) {
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
        for (CsFlagEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}

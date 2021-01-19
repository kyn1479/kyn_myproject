package com.kyn.myproject.demo.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 消息编码
 * @date 2021/1/18 15:20
 */
public enum EncodeEnum {
    UTF8("01", "UTF-8"),
    GBK("02", "GBK"),
    GB2312("03", "GB2312"),
    ISO8859("04", "ISO8859-1");

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>EncodeEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private EncodeEnum(String code, String message) {
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
     * @return EncodeEnum
     */
    public static EncodeEnum getByCode(String code) {
        for (EncodeEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<EncodeEnum>
     */
    public List<EncodeEnum> getAllEnum() {
        List<EncodeEnum> list = new ArrayList<EncodeEnum>();
        for (EncodeEnum _enum : values()) {
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
        for (EncodeEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

}

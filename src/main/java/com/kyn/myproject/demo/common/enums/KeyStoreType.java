package com.kyn.myproject.demo.common.enums;

/**
 * @author Kangyanan
 * @Description: keystore类型
 * @date 2021/1/20 17:13
 */
public enum KeyStoreType {

    JKS("JKS"),

    PKCS12("PKCS12"),;

    /**
     * 枚举值
     */
    private final String value;


    private KeyStoreType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    /**
     * 通过枚举<code>code</code>获得枚举
     * @param value
     * @return NoteEnum
     */
    public static KeyStoreType getByCode(String value) {
        for (KeyStoreType _enum : values()) {
            if (_enum.getValue().equals(value)) {
                return _enum;
            }
        }
        return null;
    }
}

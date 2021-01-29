package com.kyn.myproject.demo.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kangyanan
 * @Description: 易类型枚举
 * @date 2021/1/22 9:59
 */
public enum TransactionEnum {
    ACCT_BALANCE_QRY("acctBalanceQry", "对公帐户-余额查询"),
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
     * 构造一个<code>DictEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private TransactionEnum(String code, String message) {
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
     * @return DictEnum
     */
    public static TransactionEnum getByCode(String code) {
        for (TransactionEnum _enum : values()) {
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
    public static List<TransactionEnum> getAllEnum() {
        List<TransactionEnum> list = new ArrayList<TransactionEnum>();
        for (TransactionEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     * @return List<String>
     */
    public static List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (TransactionEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}

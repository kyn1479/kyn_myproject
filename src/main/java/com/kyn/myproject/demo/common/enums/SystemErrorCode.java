package com.kyn.myproject.demo.common.enums;



import com.kyn.myproject.demo.common.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanan.kang
 * @description ： 系统错误码
 * @date 2020-03-26 21:42
 */
public enum SystemErrorCode implements ErrorCode {
    BIZ_PARAM_NULL("G101", "业务参数为空异常"),
    SYSTEM_ERROR("G102", "系统内部异常"),
    SEQUENCE_GEN_ERR("G103", "流水号生成异常"),
    TYPE_VALIDATE_ERROR("G104", "TYPE参数校验异常{}"),
    OPTION_VALIDATE_ERROR("G105", "参数可选校验异常{}"),
    LENGTH_VALIDATE_ERROR("G106", "参数错误：{0}长度不正确"),
    REGEX_VALIDATE_ERROR("G107", "参数正则匹配校验异常{}"),
    COMMUNICATION_EXCEPTION("G108", "通讯异常"),


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
     * 构造枚举对象
     * @param code
     * @param message
     */
    private SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    @Override
    public ExceptionTypeEnum getExceptionType() {
        return ExceptionTypeEnum.SYSTEM_ERROR;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     * @param code
     * @return
     */
    public static SystemErrorCode getByCode(String code) {
        for (SystemErrorCode _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }
    /**
     * 获取全部枚举
     * @return
     */
    public static List<SystemErrorCode> getAllEnum() {
        List<SystemErrorCode> list = new ArrayList<SystemErrorCode>();
        for (SystemErrorCode _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     * @return
     */
    public  static List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (SystemErrorCode _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

}

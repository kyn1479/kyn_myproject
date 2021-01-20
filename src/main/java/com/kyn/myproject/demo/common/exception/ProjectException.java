/**
 * msxf.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.kyn.myproject.demo.common.exception;


import java.text.MessageFormat;

/**
 * @author Kangyanan
 * @Description: 业务异常
 * @date 2021/02/19 15:19
 */
public class ProjectException extends RuntimeException {

    /**
     * 错误码
     */
    private ErrorCode errorCode;

    /**
     * 异常参数，用于传参给最终的错误码
     */
    private Object[] args;

    public ProjectException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }

    public ProjectException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }


    public ProjectException(ErrorCode errorCode, Object... args) {
        super((errorCode.getCode()));
        this.errorCode = errorCode;
        this.args = args;
    }

    public ProjectException(ErrorCode errorCode, String args) {
        super((errorCode.getCode()));
        this.errorCode = errorCode;
        String [] temp = {args};
        this.args = temp;
    }


    public ProjectException(String message, ErrorCode errorCode, Object[] args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }


    public ProjectException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    public ProjectException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }


    public ProjectException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }


    public String format(ErrorCode errorCode, Object[] args) {
        return MessageFormat.format(errorCode.getMessage(), args);
    }

    @Override
    public String getMessage() {
        return getErrorMsg();
    }

    public String getErrorMsg() {
        if (args != null) {
            return format(errorCode, args);
        }
        return errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}

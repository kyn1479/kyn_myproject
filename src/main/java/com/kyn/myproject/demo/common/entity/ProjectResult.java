package com.kyn.myproject.demo.common.entity;

import java.io.Serializable;

/**
 * @author Kangyanan
 * @Description: 通用返回结果实体类
 * @date 2021/1/18 15:24
 */
public class ProjectResult implements Serializable {
    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 消息内容
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

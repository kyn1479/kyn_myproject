package com.kyn.myproject.demo.common.entity;

import java.io.Serializable;

/**
 * @author Kangyanan
 * @Description: 报文实体：包含报文头部和报文包体
 * @date 2021/1/20 15:12
 */
public class MessageEntity<T> implements Serializable {
    /**
     * 请求报文头
     */
    private Header header;

    /**
     * 请求报文体
     */
    private T body;

    /**
     * @Description 默认构造方法
     * @Params
     * @Return
     * @Exceptions
     */
    public MessageEntity() {
        super();
    }

    /**
     * @Description 构造方法
     * @Params header
     * @Return
     * @Exceptions
     */
    public MessageEntity(Header header) {
        this.header = header;
    }

    /**
     * @Description 构造方法
     * @Params body
     * @Return
     * @Exceptions
     */
    public MessageEntity(T body) {
        this.body = body;
    }

    /**
     * @Description 构造方法
     * @Params header body
     * @Return
     * @Exceptions
     */
    public MessageEntity(Header header, T body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}

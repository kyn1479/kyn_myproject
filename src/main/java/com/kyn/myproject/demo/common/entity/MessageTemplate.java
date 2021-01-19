package com.kyn.myproject.demo.common.entity;

/**
 * @author Kangyanan
 * @Description: 报文模板封装类
 * @date 2021/1/18 15:10
 */
public class MessageTemplate {
    /**
     * 主模板，基础配置，必填
     */
    private String mainTemplate;

    /**
     * 子模版一，可以为待签名数据模板
     */
    private String subTemplate1;

    /**
     * 子模版二，可以为待加密数据模板
     */
    private String subTemplate2;

    /**
     * 报文头模板
     */
    private String headerTemplate;

    public String getMainTemplate() {
        return mainTemplate;
    }

    public void setMainTemplate(String mainTemplate) {
        this.mainTemplate = mainTemplate;
    }

    public String getSubTemplate1() {
        return subTemplate1;
    }

    public void setSubTemplate1(String subTemplate1) {
        this.subTemplate1 = subTemplate1;
    }

    public String getSubTemplate2() {
        return subTemplate2;
    }

    public void setSubTemplate2(String subTemplate2) {
        this.subTemplate2 = subTemplate2;
    }

    public String getHeaderTemplate() {
        return headerTemplate;
    }

    public void setHeaderTemplate(String headerTemplate) {
        this.headerTemplate = headerTemplate;
    }

    @Override
    public String toString() {
        return "MessageTemplate{" +
                "mainTemplate='" + mainTemplate + '\'' +
                ", subTemplate1='" + subTemplate1 + '\'' +
                ", subTemplate2='" + subTemplate2 + '\'' +
                ", headerTemplate='" + headerTemplate + '\'' +
                '}';
    }
}

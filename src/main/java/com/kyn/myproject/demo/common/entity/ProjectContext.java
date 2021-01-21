package com.kyn.myproject.demo.common.entity;


import com.kyn.myproject.demo.common.enums.ParamType;
import com.kyn.myproject.demo.common.util.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 请求上下文
 * @date 2021/1/18 15:12
 */
public class ProjectContext {

    /**
     * 默认构造方法
     */
    public ProjectContext() {
        super();
    }
    /**
     * 存储参数的map
     */
    private final Map<ParamType, Object> paramMap = new EnumMap<ParamType, Object>(ParamType.class);

    /**
     * 按类型获取参数
     *
     * @param type
     * @return
     */
    public Object getParam(ParamType type) {
        return this.paramMap.get(type);
    }

    /**
     * 设置请求消息
     * @param messageDescription
     */
    public void setMessageDescription(MessageDescription messageDescription) {
        paramMap.put(ParamType.MESSAGE_DESCRIPTION, messageDescription);
    }

    /**
     * 获取请求消息
     * @return
     */
    public MessageDescription getMessageDescription() {
        return (MessageDescription) paramMap.get(ParamType.MESSAGE_DESCRIPTION);
    }

    /**
     * 参数添加
     * @param type
     * @param value
     */
    public void addParam(ParamType type, Object value) {
        this.paramMap.put(type, value);
    }

    /**
     * 获取http_method
     * @return
     */
    public HttpUriRequest getHttpMethod() {
        return (HttpUriRequest) paramMap.get(ParamType.HTTP_METHOD);
    }
    /**
     * @Description 获取HTTPCLIENT
     * @Params
     * @Return HttpClient
     * @Exceptions
     */
    public HttpClient getHttpClient() {
        return (HttpClient) paramMap.get(ParamType.HTTP_CLIENT);
    }


    /**
     * @Description 设置客户端渠道编码
     * @Params
     * @Return
     * @Exceptions
     */
    public void setClientInstCode(String instCode) {
        addParam(ParamType.CLIENT_INST_CODE, instCode);
    }

    /**
     * @Description 获取客户端渠道编码
     * @Params
     * @Return
     * @Exceptions
     */
    public String getClientInstCode() {
        return StringUtils.valueOf(getParam(ParamType.CLIENT_INST_CODE));
    }

    /**
     * @Description 设置服务端渠道编码
     * @Params
     * @Return
     * @Exceptions
     */
    public void setServerInstCode(String instCode) {
        addParam(ParamType.SERVER_INST_CODE, instCode);
    }

    /**
     * @Description 获取服务端渠道编码
     * @Params
     * @Return
     * @Exceptions
     */
    public String getServerInstCode() {
        return StringUtils.valueOf(getParam(ParamType.SERVER_INST_CODE));
    }

    /**
     * @Description 设置客户端交易码
     * @Params
     * @Return
     * @Exceptions
     */
    public void setClientTransCode(String transCode) {
        addParam(ParamType.CLIENT_TRANS_CODE, transCode);
    }

    /**
     * @Description 获取客户端交易码
     * @Params
     * @Return
     * @Exceptions
     */
    public String getClientTransCode() {
        return StringUtils.valueOf(getParam(ParamType.CLIENT_TRANS_CODE));
    }
    /**
     * @Description 设置服务端交易码
     * @Params
     * @Return
     * @Exceptions
     */
    public void setServerTransCode(String transCode) {
        addParam(ParamType.SERVER_TRANS_CODE, transCode);
    }

    /**
     * @Description 获取服务端交易码
     * @Params
     * @Return
     * @Exceptions
     */
    public String getServerTransCode() {
        return StringUtils.valueOf(getParam(ParamType.SERVER_TRANS_CODE));
    }

    /**
     * @Description 获取客户端返回
     * @Params
     * @Return
     * @Exceptions
     */
    public HttpResponse getHttpResponse() {
        return (HttpResponse) paramMap.get(ParamType.HTTP_CLIENT_RESPONSE);
    }
}

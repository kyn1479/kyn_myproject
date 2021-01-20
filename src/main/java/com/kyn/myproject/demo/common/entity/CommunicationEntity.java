package com.kyn.myproject.demo.common.entity;

import com.kyn.myproject.demo.common.enums.EncodeEnum;
import com.kyn.myproject.demo.common.enums.MessageFormatEnum;
import com.kyn.myproject.demo.common.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 通讯模型
 * @date 2021/1/19 22:51
 */
public class CommunicationEntity extends EntityBase implements Comparable<CommunicationEntity> {
    //主键
    private Long id;
    //支付渠道交易类型id
    private Long instTransTypeId;
    //通讯编码
    private String commuCode;
    //通讯名称
    private String commuName;
    //通讯url
    private String reqUrl;
    //通讯版本
    private String commuVersion;
    //指令终端码
    private String terminalCode;
    //协议类型 01-http 02-https 03-ftp 04-sftp 05-webservice 06-hessian 07-tcp 08-mq
    private String protocolType;
    //异步通知协议类型01-http 08-mq
    private String asyNotifyProtocol;
    //http协议方式 0-all 01-get 02-post
    private String httpMethod;
    //连接超时时间(ms)
    private Integer connectTimeout;
    //读超时时间(ms)
    private Integer readTimeout;
    //最大并发数
    private Integer maxOccurs;
    //流水号位数
    private Integer maxNoLength;
    //上送报文类型 01-form 02-json 03-xml
    private String sendTransformType;
    //接收报文类型 01-form 02-json 03-xml
    private String receiveTransformType;
    //上送报文编码 01-utf-8 02-gbk 03-gb2312 04-iso8859-1
    private String sendTransformEncode;
    //接收报文编码 01-utf-8 02-gbk 03-gb2312 04-iso8859-1
    private String receiveTransformEncode;
    //https客户端证书编码
    private String httpsCertCode;
    //https可信任的服务端证书编码
    private String httpsTrustCertCode;
    //通讯顺序
    private Integer commuOrder;
    //客户端/服务端交易标识(01-客户端  02-服务端)
    private String csFlag;
    //扩展属性
    private String properties;
    //状态  启用  ENABLE 停用 DISABLE  初始化 INIT
    private String status;
    //是否删除 YES NO
    private String isDelete;
    //扩展一
    private String extend1;
    //扩展二
    private String extend2;
    //创建时间
    private java.util.Date createTime;
    //更新时间
    private java.util.Date updateTime;

    private Map<String, Object> propertyMap;

    /**
     * 报文模板
     */
    private MessageTemplate messageTemplate;

    /**
     * 消息解析器在spring上下文中的ID
     */
    private String messageParserId;

    /**
     * 支付渠道数据补全脚本在spring上下文中的bean name
     */
    private String instDataCompleterBeanName;

    @Override
    protected String getEntityId() {
        return StringUtils.valueOf(id);
    }

    @Override
    public int compareTo(CommunicationEntity communicationEntity) {
        return this.commuOrder.compareTo(communicationEntity.getCommuOrder());
    }

    /**
     * 获取支付机构交易类型接收消息格式
     * @return
     */
    public MessageFormatEnum getReceiveMessageFormat() {
        return MessageFormatEnum.getByCode(receiveTransformType);
    }

    /**
     * 获取支付机构交易类型接收消息格式
     * @return
     */
    public EncodeEnum getReceiveMessageEncode() {
        return EncodeEnum.getByCode(receiveTransformEncode);
    }

    /**
     * 获取返回的消息格式
     * @return
     */
    public MessageFormatEnum getSendMessageFormat() {
        return MessageFormatEnum.getByCode(sendTransformType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstTransTypeId() {
        return instTransTypeId;
    }

    public void setInstTransTypeId(Long instTransTypeId) {
        this.instTransTypeId = instTransTypeId;
    }

    public String getCommuCode() {
        return commuCode;
    }

    public void setCommuCode(String commuCode) {
        this.commuCode = commuCode;
    }

    public String getCommuName() {
        return commuName;
    }

    public void setCommuName(String commuName) {
        this.commuName = commuName;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getCommuVersion() {
        return commuVersion;
    }

    public void setCommuVersion(String commuVersion) {
        this.commuVersion = commuVersion;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getAsyNotifyProtocol() {
        return asyNotifyProtocol;
    }

    public void setAsyNotifyProtocol(String asyNotifyProtocol) {
        this.asyNotifyProtocol = asyNotifyProtocol;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Integer getMaxOccurs() {
        return maxOccurs;
    }

    public void setMaxOccurs(Integer maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    public Integer getMaxNoLength() {
        return maxNoLength;
    }

    public void setMaxNoLength(Integer maxNoLength) {
        this.maxNoLength = maxNoLength;
    }

    public String getSendTransformType() {
        return sendTransformType;
    }

    public void setSendTransformType(String sendTransformType) {
        this.sendTransformType = sendTransformType;
    }

    public String getReceiveTransformType() {
        return receiveTransformType;
    }

    public void setReceiveTransformType(String receiveTransformType) {
        this.receiveTransformType = receiveTransformType;
    }

    public String getSendTransformEncode() {
        return sendTransformEncode;
    }

    public void setSendTransformEncode(String sendTransformEncode) {
        this.sendTransformEncode = sendTransformEncode;
    }

    public String getReceiveTransformEncode() {
        return receiveTransformEncode;
    }

    public void setReceiveTransformEncode(String receiveTransformEncode) {
        this.receiveTransformEncode = receiveTransformEncode;
    }

    public String getHttpsCertCode() {
        return httpsCertCode;
    }

    public void setHttpsCertCode(String httpsCertCode) {
        this.httpsCertCode = httpsCertCode;
    }

    public String getHttpsTrustCertCode() {
        return httpsTrustCertCode;
    }

    public void setHttpsTrustCertCode(String httpsTrustCertCode) {
        this.httpsTrustCertCode = httpsTrustCertCode;
    }

    public Integer getCommuOrder() {
        return commuOrder;
    }

    public void setCommuOrder(Integer commuOrder) {
        this.commuOrder = commuOrder;
    }

    public String getCsFlag() {
        return csFlag;
    }

    public void setCsFlag(String csFlag) {
        this.csFlag = csFlag;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, Object> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, Object> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public MessageTemplate getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(MessageTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageParserId() {
        return messageParserId;
    }

    public void setMessageParserId(String messageParserId) {
        this.messageParserId = messageParserId;
    }

    public String getInstDataCompleterBeanName() {
        return instDataCompleterBeanName;
    }

    public void setInstDataCompleterBeanName(String instDataCompleterBeanName) {
        this.instDataCompleterBeanName = instDataCompleterBeanName;
    }
}

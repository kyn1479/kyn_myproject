package com.kyn.myproject.demo.common.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Kangyanan
 * @Description: 通道机构信息模型
 * @date 2021/1/20 16:49
 */
public class CommunicationInstitution {
    private static final Logger logger = LoggerFactory.getLogger(CommunicationInstitution.class);

    /**
     * 主键
     */
    private Long id;

    /**
     * 支付渠道编码
     */
    private String instCode;

    /**
     * 支付渠道名称
     */
    private String instName;

    /**
     * 支付渠道类型  01-银联 02-银行 03-第三方支付
     */
    private String instType;

    /**
     * 支付渠道LOGO
     */
    private String instLogo;

    /**
     * 状态  启用  ENABLE 停用 DISABLE  初始化 INIT
     */
    private String status;

    /**
     * 是否删除 YES NO
     */
    private String isDelete;

    /**
     * 扩展一
     */
    private String extend1;

    /**
     * 扩展二
     */
    private String extend2;

    /**
     * 创建时间
     */
    private java.util.Date createTime;

    /**
     * 更新时间
     */
    private java.util.Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getInstLogo() {
        return instLogo;
    }

    public void setInstLogo(String instLogo) {
        this.instLogo = instLogo;
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
}

package com.kyn.myproject.demo.common.dto;

import java.util.Date;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 17:21
 */
public class Certificate {
    private Long id;
    private String certCode;
    private String certName;
    private String algorithm;
    private String certStoreType;
    private String certPwd;
    private String privateKeyPwd;
    private String certAlias;
    private String certDesc;
    private Date validBegin;
    private Date validEnd;
    private String status;
    private String isDelete;
    private String extend1;
    private String extend2;
    private Date createTime;
    private Date updateTime;
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getCertStoreType() {
        return certStoreType;
    }

    public void setCertStoreType(String certStoreType) {
        this.certStoreType = certStoreType;
    }

    public String getCertPwd() {
        return certPwd;
    }

    public void setCertPwd(String certPwd) {
        this.certPwd = certPwd;
    }

    public String getPrivateKeyPwd() {
        return privateKeyPwd;
    }

    public void setPrivateKeyPwd(String privateKeyPwd) {
        this.privateKeyPwd = privateKeyPwd;
    }

    public String getCertAlias() {
        return certAlias;
    }

    public void setCertAlias(String certAlias) {
        this.certAlias = certAlias;
    }

    public String getCertDesc() {
        return certDesc;
    }

    public void setCertDesc(String certDesc) {
        this.certDesc = certDesc;
    }

    public Date getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(Date validBegin) {
        this.validBegin = validBegin;
    }

    public Date getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(Date validEnd) {
        this.validEnd = validEnd;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

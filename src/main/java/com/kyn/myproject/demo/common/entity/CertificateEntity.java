package com.kyn.myproject.demo.common.entity;

import com.kyn.myproject.demo.common.dto.Certificate;
import com.kyn.myproject.demo.common.dto.FileContent;
import com.kyn.myproject.demo.common.security.Crypt;

/**
 * @author Kangyanan
 * @Description: 证书实体类
 * @date 2021/1/20 17:19
 */
public class CertificateEntity extends EntityBase{
    /**
     *证书文件
     *
     */
    public FileContent fileContent;

    /**
     * 证书基本信息
     */
    private Certificate certificate;

    /**
     * 加解密器
     */
    private Crypt crypt;

    public CertificateEntity(Certificate certificate) {
        this.certificate = certificate;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public FileContent getFileContent() {
        return fileContent;
    }

    public void setFileContent(FileContent fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    protected String getEntityId() {
        return null;
    }

    public Crypt getCrypt() {
        return crypt;
    }

    public void setCrypt(Crypt crypt) {
        this.crypt = crypt;
    }
}

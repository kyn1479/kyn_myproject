package com.kyn.myproject.demo.common.security;

import com.kyn.myproject.demo.common.enums.CertificateType;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 17:09
 */
public class KeyObj {
    /**
     * 证书类型
     */
    private CertificateType type;

    /**
     * 证书内容
     */
    private byte [] keyBytes;

    /**
     * 证书加密算法
     */
    private String algorithm;

    /**
     * @Description 获取证书内容
     *
     * @Params
     * @Return
     * @Exceptions
     */
    public byte[] getKeyBytes() {
        return keyBytes;
    }

    public void setKeyBytes(byte[] keyBytes) {
        this.keyBytes = keyBytes;
    }

    /**
     * @Description 获取证书类型
     *
     * @Params
     * @Return
     * @Exceptions
     */
    public CertificateType getType() {
        return type;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    /**
     * @Description 获取加密算法
     *
     * @Params
     * @Return
     * @Exceptions
     */
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}

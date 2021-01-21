package com.kyn.myproject.demo.common.security;

import com.kyn.myproject.demo.common.enums.KeyStoreType;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 17:09
 */
public class KeyStoreObj extends KeyObj{
    /**
     * keystore密码
     */
    private String stroePass;

    /**
     * 证书别名
     */
    private String keyAliase;

    /**
     * 证书密码
     */
    private String keyPass;

    /**
     * keystore类型
     */
    private KeyStoreType keyStoreType;
    /**
     * 证书扩展属性
     */
    private String extend1;

    public KeyStoreType getKeyStoreType() {
        return keyStoreType;
    }

    public void setKeyStoreType(KeyStoreType keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public String getStroePass() {
        return stroePass;
    }

    public void setStroePass(String stroePass) {
        this.stroePass = stroePass;
    }

    public String getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(String keyPass) {
        this.keyPass = keyPass;
    }

    public String getKeyAliase() {
        return keyAliase;
    }

    public void setKeyAliase(String keyAliase) {
        this.keyAliase = keyAliase;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }
}

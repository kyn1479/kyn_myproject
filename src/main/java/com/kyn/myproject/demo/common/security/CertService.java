package com.kyn.myproject.demo.common.security;

import com.kyn.myproject.demo.common.entity.CertificateEntity;

/**
 * @author Kangyanan
 * @Description: 证书服务接口
 * @date 2021/1/20 17:17
 */
public interface CertService {
    CertificateEntity getCertEnity(String certCode);
}

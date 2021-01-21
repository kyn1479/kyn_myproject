package com.kyn.myproject.demo.common.security;

import com.kyn.myproject.demo.common.cache.CertificateCacheManager;
import com.kyn.myproject.demo.common.entity.CertificateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description: 证书服务接口实现类
 * @date 2021/1/20 17:25
 */
@Service("certService")
public class CertServiceImpl implements CertService {

    @Autowired
    private CertificateCacheManager certificateCacheManager;

    @Override
    public CertificateEntity getCertEnity(String certCode) {
//        return certificateCacheManager.get(certCode);
        return null;
    }
}

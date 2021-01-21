package com.kyn.myproject.demo.common.cache;

import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 证书缓存管理类
 * @date 2021/1/20 17:55
 */
@Component
public class CertificateCacheManager implements CacheManager {


    @Override
    public void init() {

    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void refresh() {

    }
}

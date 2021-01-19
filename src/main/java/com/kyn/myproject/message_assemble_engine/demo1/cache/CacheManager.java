package com.kyn.myproject.message_assemble_engine.demo1.cache;

/**
 * @author Kangyanan
 * @Description: 缓存管理类
 * @date 2021/1/18 17:46
 */
public interface CacheManager {
    void init();

    Object get(String key);

    void remove(String key);

    void clear();

    void refresh();
}

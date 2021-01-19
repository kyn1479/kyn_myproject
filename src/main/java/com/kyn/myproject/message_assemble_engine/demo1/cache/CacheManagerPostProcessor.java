package com.kyn.myproject.message_assemble_engine.demo1.cache;

import com.kyn.myproject.message_assemble_engine.demo1.util.ApplicationContextUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 缓存后置处理器
 * @date 2021/1/18 17:51
 */
@Component
public class CacheManagerPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
            Map<String, CacheManager> cacheManagers = ApplicationContextUtil.getBeans(CacheManager.class);
            if (cacheManagers != null && cacheManagers.size() > 0) {
                for (CacheManager cacheManager : cacheManagers.values()) {
                        cacheManager.init();
                }
            }
    }
}

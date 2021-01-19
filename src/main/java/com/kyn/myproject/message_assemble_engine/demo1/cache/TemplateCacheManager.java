package com.kyn.myproject.message_assemble_engine.demo1.cache;

import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageTemplate;
import com.kyn.myproject.message_assemble_engine.demo1.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 模板缓存管理类
 * @date 2021/1/18 17:49
 */
@Component
public class TemplateCacheManager implements CacheManager{
    private static final Logger logger = LoggerFactory.getLogger(TemplateCacheManager.class);

    private Map<String, MessageTemplate> messageTemplateCache = new HashMap();

    @Override
    public void init() {
        try {
            logger.info("TemplateCacheManager-开始加载模板");
            FileUtils.readFiles("classpath:templates", messageTemplateCache,null);
        } catch (Exception e) {
            logger.info("TemplateCacheManager-加载模板异常！");
        }
        messageTemplateCache.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + messageTemplateCache.get(key)));
    }

    @Override
    public MessageTemplate get(String key) {
        return messageTemplateCache.get(key);
    }

    @Override
    public void remove(String key) {
        messageTemplateCache.remove(key);
    }

    @Override
    public void clear() {
        messageTemplateCache.clear();
    }

    @Override
    public void refresh() {

    }
}

package com.kyn.myproject.message_assemble_engine.demo1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author Kangyanan
 * @Description: 容器工具类
 * @date 2021/1/18 17:55
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

    /**
     * spring上下文
     */
    private static ApplicationContext applicationContext;


    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        T t = null;
        try {
            t = applicationContext.getBean(name, requiredType);
        } catch (Exception e) {
            logger.error("从spring上下文中未获取到bean({})", name);
        }
        return t;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> Map<String , T> getBeans(Class<T> type){
        return applicationContext.getBeansOfType(type);
    }
}

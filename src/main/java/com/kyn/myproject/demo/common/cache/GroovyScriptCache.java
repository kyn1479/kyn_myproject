package com.kyn.myproject.demo.common.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kyn.myproject.demo.common.util.ApplicationContextUtil;
import com.kyn.myproject.demo.message_parse_engine.service.MessageParser;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/19 18:12
 */
@Component
public class GroovyScriptCache implements CacheManager {
    private static final Logger logger = LoggerFactory.getLogger(GroovyScriptCache.class);

    private Map<String, MessageParser> parserMap = new HashMap<>();

    @Override
    public void init() {
        try {
            initMessageParser();
        }catch (Exception ex){
            logger.warn("-----GroovyScriptCache初始化异常-----");
        }
    }

    /**
     * 加载解析脚本
     */
    private void initMessageParser() {
        logger.info("-----GroovyScriptCache开始加载报文解析脚本-----");
        long startTime = System.currentTimeMillis();
        Map<String, MessageParser> tempParserMap = new HashMap<>();
        //获取该路径下所有类
        Reflections reflections = new Reflections("com.kyn.myproject.demo.message_parse_engine.script.messageparsegroovy");
        //获取继承了MessageParser的所有类
        Set<Class<? extends MessageParser>> classSet = reflections.getSubTypesOf(MessageParser.class);
        for (Class<? extends MessageParser> obj : classSet) {
            System.out.println("======" + obj.getName());
            MessageParser  groovyObject = null;
            try {
                AutowireCapableBeanFactory autowireCapableBeanFactory = ApplicationContextUtil.getApplicationContext().getAutowireCapableBeanFactory();
                groovyObject = (MessageParser ) GroovyScriptCache.class.getClassLoader().loadClass(obj.getName()).newInstance();
                autowireCapableBeanFactory.autowireBean(groovyObject);
                tempParserMap.put(obj.getSimpleName(), groovyObject);
                logger.info("报文解析脚本加载:" + groovyObject.getClass().getName());
            } catch (Exception e) {
                logger.error("报文解析脚本加载异常：{}",e);
            }
        }
        parserMap = tempParserMap;
        logger.info("-----GroovyScriptCache结束加载报文解析脚本 size={},cost-Time={}", parserMap.size(), System.currentTimeMillis() - startTime);
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

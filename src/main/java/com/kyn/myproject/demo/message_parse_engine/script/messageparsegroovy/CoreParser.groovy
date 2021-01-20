package com.kyn.myproject.demo.message_parse_engine.script.messageparsegroovy

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.kyn.myproject.demo.common.entity.ProjectContext
import com.kyn.myproject.demo.message_parse_engine.service.MessageParser
import com.kyn.myproject.demo.common.util.StringUtils;
/**
 * @author Kangyanan
 * @Description: 消息解析器实现类
 * @date 2021/1/19 18:15
 */
public class CoreParser implements MessageParser {
    @Override
    public Object parse(ProjectContext context, Object message) {
        Map<String, Object> map = JSON.parseObject(StringUtils.valueOf(message), new TypeReference<HashMap<String, Object>>() {});
        // 获取extend1并解析其中的json，并将其放入map中
        def body = (HashMap<String, Object>)map.get("body")
        def extend1 = body.get("extend1")
        if(StringUtils.isNotBlank(extend1)){
            def propertyMap = JSON.parseObject(StringUtils.valueOf(extend1))
            map.get("body").putAll(propertyMap)
        }
        return map;
    }
}
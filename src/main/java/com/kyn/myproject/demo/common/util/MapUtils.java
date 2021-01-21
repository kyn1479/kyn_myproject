package com.kyn.myproject.demo.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/18 16:02
 */
public class MapUtils {
    private static Logger logger = LoggerFactory.getLogger(MapUtils.class);
    /**
     * @Description 根据固定规则将默认配置的文本格式转换成map格式
     * @Params 待转换内容(map的toString格式)
     * @Return
     * @Exceptions
     */
    public static Map<String, String> covertText2MapByRule(String text) {
        //为了满足某些渠道对地址栏参数也有排序要求,故使用LinkedHashMap
        Map<String, String> kvMap = new LinkedHashMap<>();
        if (StringUtils.isNotBlank(text)) {
            //去除两边的"{}"
            text = text.substring(1, text.length() - 1);
            String[] entry = text.split(",", -1);
            if (entry.length > 0) {
                //记录上一次的key值
                String orgKey = null;
                for (String kv : entry) {
                    //考虑value自身有等号的情况
                    int index = kv.indexOf("=");
                    if (-1 == index) {
                        //兼容值里面带有逗号的情况，如果不存在"="号，则将逗号加值追加到上一个值。
                        if (orgKey != null) {
                            kvMap.put(orgKey, kvMap.get(orgKey) + "," + kv);
                        }
                        continue;
                    }
                    orgKey = kv.substring(0, index).trim();
                    kvMap.put(orgKey, kv.substring(index + 1).trim());
                }
            }
        }
        return kvMap;
    }

    /**
     * 过滤map中的空值的entry和指定key的entry
     * @param map
     * @param filtKeys
     * @return
     */
    public static Map<String, Object> paraFilter(Map<String, Object> map, String... filtKeys) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (map == null || map.size() <= 0) {
            return result;
        }
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (StringUtils.isNotBlank(value) && !Arrays.asList(filtKeys).contains(key)) {
                result.put(key, value);
            }
        }
        return result;
    }

    public static Map<String, String> covertToJSON(String text) {
        Map<String, String> kvMap = new HashMap<>();
        try {
            kvMap = JSON.parseObject(text, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            logger.info("covertToJSON解析异常，异常信息：{}", e);
        }
        return kvMap;
    }
}

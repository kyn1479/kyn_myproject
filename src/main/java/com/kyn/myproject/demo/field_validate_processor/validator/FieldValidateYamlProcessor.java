package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.constant.ValidatorConstant;
import com.kyn.myproject.demo.common.util.MapUtils;
import com.kyn.myproject.demo.common.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 10:34
 */
@Component
public class FieldValidateYamlProcessor extends YamlProcessor implements InitializingBean {

    /**
     * 应用环境
     */
    @Autowired
    private Environment env;

    private static final String STYLE = "style";
    private static final String FIELD = "field";
    private static final String FIELD_NAME = "fieldName";
    private static final String TYPE = "type";
    private static final String OPTION = "option";

    /**
     * style校验器缓存(key为styleCode,value为Style对象)
     */
    private Map<String, Style> styleMap;

    /**
     * 字段策略缓存(key为交易码/交易码+交易类型，value为field策略列表)
     */
    private Map<String, List<FieldPolicy>> fieldPolicyListMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(env.getProperty(ValidatorConstant.FIELD_VALIDATE_YAML_CONFIG_PATH));
        super.setResources(resources);
        Map<String, Object> yamlMap = createMap();//yaml配置
        this.styleMap = initStyleMap(yamlMap);
        this.fieldPolicyListMap = initFieldPolicyListMap(yamlMap);
    }

    /**
     * 获取style
     * @param styleName
     * @return
     */
    public Style getStyle(String styleName) {
        if (CollectionUtils.isEmpty(this.styleMap)) {
            return null;
        }
        return this.styleMap.get(styleName);
    }

    /**
     * 解析yaml为map
     * @return
     */
    private Map<String, Object> createMap(){
        final Map<String, Object> result = new LinkedHashMap<String, Object>();
        process(new MatchCallback() {
            @Override
            public void process(Properties properties, Map<String, Object> map) {
                merge(result, map);
            }
        });
        return result;
    }

    /**
     * 合并yaml中相同的key
     * @param output
     * @param map
     */
    private void merge(Map<String, Object> output, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object existing = output.get(key);
            if (value instanceof Map && existing instanceof Map) {
                Map<String, Object> result = new LinkedHashMap<String, Object>((Map) existing);
                merge(result, (Map) value);
                output.put(key, result);
            } else {
                output.put(key, value);
            }
        }
    }

    /**
     * 初始化style
     * @param yamlMap
     * @return
     */
    private Map<String, Style> initStyleMap(Map<String, Object> yamlMap) {
        Map<String, Style> returnMap = new HashMap<String, Style>();
        if (!CollectionUtils.isEmpty(yamlMap)) {
            Map<String, Map<String, Object>> styleMap = (Map<String, Map<String, Object>>) yamlMap.get(STYLE);
            if (!CollectionUtils.isEmpty(styleMap)) {
                for (Map.Entry<String, Map<String, Object>> entry : styleMap.entrySet()) {
                    returnMap.put(entry.getKey(), initStyle(entry.getValue()));
                }
            }
        }
        return returnMap;
    }

    /**
     * 初始化style
     * @param validateMap
     * @return
     */
    private Style initStyle(Map<String, Object> validateMap) {
        Map<String, Validate> validateMapWithStyle = initValidateMap(validateMap);
        Style style = new Style();
        style.setValidateMap(validateMapWithStyle);
        return style;
    }

    /**
     * 初始化校验器
     * @param map
     * @return
     */
    private Map<String, Validate> initValidateMap(Map<String, Object> map) {
        Map<String, Validate> extendValidateMap = new LinkedHashMap<String, Validate>();
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String validateName = entry.getKey();
                if (StringUtils.equals(TYPE, validateName)) {
                    validateName = StringUtils.valueOf(entry.getValue());
                }
                Validate validate = ValidatorFactory.getValidator(validateName).init(StringUtils.valueOf(entry.getValue()));
                extendValidateMap.put(entry.getKey(), validate);
            }
        }
        return extendValidateMap;
    }

    /**
     * @Description 初始化字段策略缓存
     * @Params
     * @Return
     * @Exceptions
     */
    private Map<String, List<FieldPolicy>> initFieldPolicyListMap(Map<String, Object> yamlMap) {
        Map<String, List<FieldPolicy>> fieldPolicyListMap = new LinkedHashMap<String, List<FieldPolicy>>();
        if (!CollectionUtils.isEmpty(yamlMap)) {
            for (Map.Entry<String, Object> entry : yamlMap.entrySet()) {
                if (StringUtils.equals(STYLE, entry.getKey())) {
                    continue;
                }
                List<Map<String, Object>> fieldMapList = (List<Map<String, Object>>) entry.getValue();//字段列表
                if (!CollectionUtils.isEmpty(fieldMapList)) {
                    List<FieldPolicy> fieldPolicyList = new ArrayList<FieldPolicy>();
                    for (Map<String, Object> fieldMap : fieldMapList) {
                        String field = StringUtils.valueOf(fieldMap.get(FIELD));
                        String fieldName = StringUtils.valueOf(fieldMap.get(FIELD_NAME));
                        Style style = this.styleMap.get(StringUtils.valueOf(fieldMap.get(STYLE)));
                        Map<String, Validate> extendValidatorMap = getExtendValidatorMap(fieldMap);
                        fieldPolicyList.add(new FieldPolicy(field, fieldName, style, extendValidatorMap));
                    }
                    fieldPolicyListMap.put(entry.getKey(), fieldPolicyList);
                }
            }
        }
        return fieldPolicyListMap;
    }

    /**
     * 获取扩展验证器
     *
     * @param fieldMap
     * @return
     */
    private Map<String, Validate> getExtendValidatorMap(Map<String, Object> fieldMap) {
        Map<String, Object> map = MapUtils.paraFilter(fieldMap, FIELD, FIELD_NAME, STYLE);//扩展验证器
        return initValidateMap(map);
    }

    public Map<String, List<FieldPolicy>> getFieldPolicyListMap() {
        return fieldPolicyListMap;
    }
}

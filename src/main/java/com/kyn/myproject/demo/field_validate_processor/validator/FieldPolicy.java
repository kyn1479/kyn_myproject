package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.exception.ProjectException;
import com.kyn.myproject.demo.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 10:40
 */
public class FieldPolicy {
    private static final Logger logger = LoggerFactory.getLogger(FieldPolicy.class);

    /**
     * 字段编码
     */
    private String field;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 验证器
     */
    private Map<String, Validate> validateMap;

    /**
     * 构造方法
     * @param field
     * @param fieldName
     * @param style
     * @param extendValidatorMap
     */
    public FieldPolicy(String field, String fieldName, Style style, Map<String, Validate> extendValidatorMap) {
        this.field = field;
        this.fieldName = fieldName;
        this.validateMap = new LinkedHashMap<String, Validate>();
        if (style != null) {//style验证器
            Map<String, Validate> validateMapWithStyle = style.getValidateMap();
            if (!CollectionUtils.isEmpty(validateMapWithStyle)) {
                for (Map.Entry<String, Validate> entry : validateMapWithStyle.entrySet()) {
                    this.validateMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (!CollectionUtils.isEmpty(extendValidatorMap)) {//扩展验证器
            for (Map.Entry<String, Validate> entry : extendValidatorMap.entrySet()) {
                this.validateMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 字段参数校验
     *
     * @param value 参数值
     */
    public void validate(String value){
        if(StringUtils.isBlank(value)){//空值
            Validate validator = getOptionValidator();
            if(!validator.validate(value)){
                throw new ProjectException(validator.getMessage(), new Object[]{fieldName});
            }
        }else{//非空值
            if (!CollectionUtils.isEmpty(this.validateMap)) {
                for (Map.Entry<String, Validate> entry : this.validateMap.entrySet()) {
                    Validate validator = entry.getValue();
                    if (!validator.validate(value)) {
                        throw new ProjectException(validator.getMessage(), new Object[]{fieldName});
                    }
                }
            }
        }
    }

    /**
     * 获取option
     *
     * @return
     */
    private Validate getOptionValidator() {
        for (Map.Entry<String, Validate> entry : this.validateMap.entrySet()) {
            if (entry.getValue() instanceof OptionValidator) {
                return entry.getValue();
            }
        }
        return new OptionValidator();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Map<String, Validate> getValidateMap() {
        return validateMap;
    }

    public void setValidateMap(Map<String, Validate> validateMap) {
        this.validateMap = validateMap;
    }
}

package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.exception.ProjectException;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: style校验器
 * @date 2021/1/20 10:38
 */
public class Style {

    private Map<String, Validate> validateMap;

    /**
     * style校验
     * @param field
     * @param fieldName
     * @param value
     */
    public void validate(String field, String fieldName, String value) {
        if (!CollectionUtils.isEmpty(this.validateMap)) {
            for (Map.Entry<String, Validate> entry : this.validateMap.entrySet()) {
                Validate validator = entry.getValue();
                if (!validator.validate(value)) {
                    throw new ProjectException(validator.getMessage(), new Object[]{fieldName});
                }
            }
        }
    }


    public Map<String, Validate> getValidateMap() {
        return validateMap;
    }

    public void setValidateMap(Map<String, Validate> validateMap) {
        this.validateMap = validateMap;
    }
}

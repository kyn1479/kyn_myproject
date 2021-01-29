package com.kyn.myproject.demo.field_validate_processor.service;

import com.kyn.myproject.demo.common.util.StringUtils;
import com.kyn.myproject.demo.field_validate_processor.validator.FieldPolicy;
import com.kyn.myproject.demo.field_validate_processor.validator.FieldValidateYamlProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 参数校验执行器
 * @date 2021/1/20 14:06
 */
@Component
public class FieldValidateExecutor {
    /**
     * 字段验证处理器
     */
    @Autowired
    private FieldValidateYamlProcessor fieldValidateYamlProcessor;

    /**
     * 参数校验
     *
     * @param transCode
     * @param data
     */
    public void validate(String transCode, Map<String, Object> data) {
        Map<String, List<FieldPolicy>> fieldPolicyListMap = this.fieldValidateYamlProcessor.getFieldPolicyListMap();
        if (!CollectionUtils.isEmpty(fieldPolicyListMap)) {
            List<FieldPolicy> fieldPolicyList = fieldPolicyListMap.get(transCode);
            if (!CollectionUtils.isEmpty(fieldPolicyList)) {
                for (FieldPolicy fieldPolicy : fieldPolicyList) {
                    fieldPolicy.validate(StringUtils.valueOf(data.get(fieldPolicy.getField())));
                }
            }
        }
    }
}

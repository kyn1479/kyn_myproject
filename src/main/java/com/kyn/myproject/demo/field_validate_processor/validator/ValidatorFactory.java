package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.exception.ProjectException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 11:01
 */
public class ValidatorFactory {
    /**
     * 校验器容器
     */
    private static Map<String, Class<? extends Validate>> validatorMap = new HashMap<String, Class<? extends Validate>>();

    static {
        ValidatorFactory.regist("option", OptionValidator.class);
        ValidatorFactory.regist("length", LengthValidator.class);
        ValidatorFactory.regist("amount", AmountValidator.class);
        ValidatorFactory.regist("date", DateValidator.class);
        ValidatorFactory.regist("regex", RegexValidator.class);
    }

    private ValidatorFactory() {
        super();
    }

    /**
     * 注册校验器
     *
     * @param validatorName
     * @param validateClass
     */
    public static void regist(String validatorName, Class<? extends Validate> validateClass) {
        validatorMap.put(validatorName, validateClass);
    }

    /**
     * 获取校验器
     *
     * @param validatorName
     * @return
     */
    public static Validate getValidator(String validatorName) {
        try {
            return validatorMap.get(validatorName).newInstance();
        } catch (Exception e) {
            throw new ProjectException(SystemErrorCode.SYSTEM_ERROR);
        }
    }
}

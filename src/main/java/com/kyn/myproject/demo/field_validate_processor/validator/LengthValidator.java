package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.util.StringUtils;

/**
 * @author Kangyanan
 * @Description: 长度校验器
 * @date 2021/1/20 13:44
 */
public class LengthValidator extends AbstractValidator {
    /**
     * 长度
     */
    private String length;

    @Override
    public boolean validate(String value) {
        return StringUtils.valueOf(value).matches("[\\s\\S]{" + length + "}");
    }

    @Override
    public SystemErrorCode getMessage() {
        return SystemErrorCode.LENGTH_VALIDATE_ERROR;
    }

    @Override
    public Validate init(String value) {
        this.length = value;
        return this;
    }
}

package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.util.StringUtils;

/**
 * @author Kangyanan
 * @Description: 正则表达式校验器
 * @date 2021/1/20 13:45
 */
public class RegexValidator extends AbstractValidator {
    /**
     * 正则表达式
     */
    private String regex;

    @Override
    public boolean validate(String value) {
        return StringUtils.valueOf(value).matches(this.regex);
    }

    @Override
    public SystemErrorCode getMessage() {
        return SystemErrorCode.REGEX_VALIDATE_ERROR;
    }

    @Override
    public Validate init(String value) {
        this.regex = value;
        return this;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}

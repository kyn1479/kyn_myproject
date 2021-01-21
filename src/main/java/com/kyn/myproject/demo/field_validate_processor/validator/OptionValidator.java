package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.util.StringUtils;

/**
 * @author Kangyanan
 * @Description: 选项(是否)校验器
 * @date 2021/1/20 11:21
 */
public class OptionValidator extends AbstractValidator {
    /**
     * 是否可选(true:可选  false:必输)  默认为可选
     */
    private boolean option;

    public OptionValidator() {
        this.option = true;
    }

    public OptionValidator(String value) {
        if (StringUtils.isBlank(value)) {
            this.option = true;
        } else {
            this.option = new Boolean(value);
        }
    }

    @Override
    public boolean validate(String value) {
        if (!this.option) {
            return StringUtils.isNotBlank(value);
        }
        return true;
    }

    @Override
    public SystemErrorCode getMessage() {
        return SystemErrorCode.OPTION_VALIDATE_ERROR;
    }

    @Override
    public Validate init(String value) {
        if (StringUtils.isBlank(value)) {
            this.option = true;
        } else {
            this.option = new Boolean(value);
        }
        return this;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }
}

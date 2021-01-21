package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.util.StringUtils;

import java.math.BigDecimal;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/20 11:06
 */
public class AmountValidator extends TypeValidator {
    @Override
    public boolean validate(String value) {
        value = StringUtils.valueOf(value);
        return value.matches("^\\d{1,15}\\.?\\d{0,2}$") && (new BigDecimal(value).compareTo(BigDecimal.ZERO) >= 0);
    }

    @Override
    public SystemErrorCode getMessage() {
        return SystemErrorCode.TYPE_VALIDATE_ERROR;
    }

    @Override
    public Validate init(String value) {
        return this;
    }
}

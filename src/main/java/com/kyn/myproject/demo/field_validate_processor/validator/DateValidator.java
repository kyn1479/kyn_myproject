package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.util.DateUtil;
import com.kyn.myproject.demo.common.util.StringUtils;

/**
 * @author Kangyanan
 * @Description: 日期校验器
 * @date 2021/1/20 13:47
 */
public class DateValidator extends TypeValidator {
    @Override
    public boolean validate(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        value = value.replace("-", "").replace(":", "").replace(" ", "");
        if (value.length() == 8) {
            try {
                DateUtil.parseDate(value, "yyyyMMdd");
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (value.length() == 14) {
            try {
                DateUtil.parseDate(value, "yyyyMMddHHmmss");
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
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

package com.kyn.myproject.demo.field_validate_processor.validator;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;

/**
 * @author Kangyanan
 * @Description: 字段验证器接口
 * @date 2021/1/20 10:39
 */
public interface Validate {
    /**
     * 字段验证
     * @param value
     * @return
     */
    boolean validate(String value);

    /**
     * 获取提示信息
     * @return
     */
    SystemErrorCode getMessage();

    /**
     * 初始化验证器
     * @param value
     * @return
     */
    Validate init(String value);
}

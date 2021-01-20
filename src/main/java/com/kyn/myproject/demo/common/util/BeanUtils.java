package com.kyn.myproject.demo.common.util;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.exception.ProjectException;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: Bean工具类
 * @date 2021/1/19 22:41
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {


    /**
     * bean to map
     * @param bean
     * @return
     */
    public static Map beanToMap(Object bean) {
        if (bean instanceof Map) {
            return ((Map) bean);
        } else {
            return describe(bean);
        }
    }

    /**
     * bean转map
     * @param bean
     * @return
     */
    public static Map describe(Object bean) {
        try {
            return PropertyUtils.describe(bean);
        } catch (Exception e) {
            throw new ProjectException(SystemErrorCode.SYSTEM_ERROR);
        }
    }
}

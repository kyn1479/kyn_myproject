package com.kyn.myproject.demo.common.util;

import static java.lang.String.valueOf;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/18 15:53
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{

    /**
     * 判断空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return obj == null || valueOf(obj).length() == 0;
    }

    /**
     * 是否为空白
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        return isBlank(valueOf(obj));
    }

    /**
     * 是否不是空白
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj) {
        return isNotBlank(valueOf(obj));
    }

    /**
     * 对象转String
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}

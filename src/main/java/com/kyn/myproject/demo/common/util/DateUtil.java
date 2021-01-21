package com.kyn.myproject.demo.common.util;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author Kangyanan
 * @Description: 时间工具类
 * @date 2021/1/20 13:48
 */
public class DateUtil {
    private final static String pattern1 = "yyyyMMdd";
    private final static String pattern2 = "yyyy-MM-dd";
    private final static String pattern3 = "yyyy-MM-dd HH:mm:ss";
    private final static String pattern4 = "yyyyMMddHHmmss";
    private final static String pattern5 = "yyyyMMddHHmmssSSS";


    public static java.sql.Date parseDate(String date, String pattren) {
        return new java.sql.Date(DateTimeFormat.forPattern(pattren).parseDateTime(date).toDate().getTime());
    }

    /**
     * yyyyMMdd
     * @return
     */
    public static String getCurrentDateStr() {
        return getCurrentDateStr(pattern1);
    }

    /**
     * 获取当前日期/时间
     * @param pattern
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        return DateTime.now().toString(pattern);
    }

}

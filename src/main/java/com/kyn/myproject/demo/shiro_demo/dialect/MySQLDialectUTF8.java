package com.kyn.myproject.demo.shiro_demo.dialect;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * @author Kangyanan
 * @Description: 重写数据库方言，设置默认字符集为utf8
 * @date 2021/1/28 18:40
 */
public class MySQLDialectUTF8 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}

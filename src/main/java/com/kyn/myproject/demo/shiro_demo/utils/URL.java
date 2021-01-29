package com.kyn.myproject.demo.shiro_demo.utils;

import lombok.Data;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 18:08
 */
@Data
public class URL {

    //URL地址
    private String url;

    public URL() {

    }

    /**
     * 封装URL地址，自动添加应用上下文路径
     *
     * @param url URL地址
     */
    public URL(String url) {
        this.url = HttpServletUtil.getRequest().getContextPath() + url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}

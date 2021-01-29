package com.kyn.myproject.demo.shiro_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 17:47
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {

    /** 是否开启验证码 */
    private boolean captchaOpen = false;

    /** 是否开启Swagger数据接口文档 */
    private boolean swaggerEnabled = true;

    /** xss防护设置 */
    private ProjectProperties.Xxs xxs = new ProjectProperties.Xxs();

    /**
     * xss防护设置
     */
    @Data
    public static class Xxs {
        /** xss防护开关 */
        private boolean enabled = true;

        /** 拦截规则，可通过“,”隔开多个 */
        private String urlPatterns = "/*";

        /** 默认忽略规则（无需修改） */
        private String defaultExcludes = "/favicon.ico,/img/*,/js/*,/css/*,/lib/*";

        /** 忽略规则，可通过“,”隔开多个 */
        private String excludes = "";

        /**
         * 拼接忽略规则
         */
        public String getExcludes() {
            if (!StringUtils.isEmpty(excludes.trim())) {
                return defaultExcludes + "," + excludes;
            }
            return defaultExcludes;
        }
    }
}

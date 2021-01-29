package com.kyn.myproject.demo.shiro_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: shiro会话配置项
 * @date 2021/1/29 10:25
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.shiro")
public class ShiroProjectProperties {

    /** cookie记住登录信息时间，默认7天 */
    private Integer rememberMeTimeout = 7;

    /** Session会话超时时间，默认30分钟 */
    private Integer globalSessionTimeout = 1800;

    /** Session会话检测间隔时间，默认15分钟 */
    private Integer sessionValidationInterval = 900;
}

package com.kyn.myproject.demo.common.template;

import com.kyn.myproject.demo.common.cache.TransactionManager;
import com.kyn.myproject.demo.common.util.ApplicationContextUtil;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/22 9:55
 */
@Component
public class TemplateManager {
    /**
     * @Description 根据交易码获取模板
     * @Params
     * @Return
     * @Exceptions
     */
    public Template getTemplate(String transCode) {
        String templateName = TransactionManager.getTemplate(transCode);
        return ApplicationContextUtil.getBean(templateName, Template.class);
    }
}

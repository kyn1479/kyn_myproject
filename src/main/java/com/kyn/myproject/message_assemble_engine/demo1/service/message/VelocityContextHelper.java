package com.kyn.myproject.message_assemble_engine.demo1.service.message;

import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageTemplate;
import com.kyn.myproject.message_assemble_engine.demo1.entity.ProjectContext;
import com.kyn.myproject.message_assemble_engine.demo1.util.StringUtils;
import com.kyn.myproject.message_assemble_engine.demo1.util.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Kangyanan
 * @Description: 模板引擎辅助类
 * @date 2021/1/18 15:42
 */
@Component
public class VelocityContextHelper implements InitializingBean {
    /**
     * 需要注入到上下文的工具类集合
     */
    private Map<String, Object> utilBeansMap = new HashMap<String, Object>();

    public VelocityContextHelper() {
    }

    /**
     * 模板填充方法
     * @param messageTemplate
     * @param projectContext
     * @return
     */
    public VelocityContext fillContext(MessageTemplate messageTemplate, ProjectContext projectContext) {
        VelocityContext context = new VelocityContext();
        //填充脚本
        fillTemplate(context, messageTemplate);
        //填充工具类
        fillTools(context);
        //填充数据
        fillData(context, projectContext);
        return context;
    }


    /**
     * 模板填充
     * @param context
     * @param messageTemplate
     */
    private void fillTemplate(VelocityContext context, MessageTemplate messageTemplate) {
        context.put("sub1", messageTemplate.getSubTemplate1());
        context.put("sub2", messageTemplate.getSubTemplate2());
        context.put("main", messageTemplate.getMainTemplate());
        context.put("header", messageTemplate.getHeaderTemplate());
        context.put("sensitive", false);
    }

    /**
     * 工具类填充
     * @param context
     */
    private void fillTools(VelocityContext context) {
        Set<Map.Entry<String, Object>> entrySet = utilBeansMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            context.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 数据填充
     * @param context
     * @param projectContext
     */
    private void fillData(VelocityContext context, ProjectContext projectContext) {
        //从上下文获取解析的参数；
        Map<String, Object> requestParams = projectContext.getMessageDescription().getDatas();
        context.put("data", requestParams);
        //实现模板嵌套的数据共享
        context.put("context", context);
    }




    @Override
    public void afterPropertiesSet() throws Exception {
        utilBeansMap.put("StringUtils", StringUtils.class);
        utilBeansMap.put("VelocityUtil", VelocityUtil.class);
        utilBeansMap.put("JSON", JSON.class);
    }
}

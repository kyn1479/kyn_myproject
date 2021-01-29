package com.kyn.myproject.demo.common.template;

import com.kyn.myproject.demo.common.constant.MessageTransportConstant;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 单笔查询交易模板
 * @date 2021/1/22 10:26
 */
@Component("simpleQueryTemplate")
public class SimpleQueryTemplate extends AbstractTemplate {
    private static final Logger logger = LoggerFactory.getLogger(SimpleQueryTemplate.class);
    @Override
    public void execute(ProjectContext context) {
        StopWatch stopWatch = new StopWatch("simpleQueryTemplate");
        //1、解析处理接收到的报文 使用消息解析引擎解析（groovy脚本实现）
        try {
            stopWatch.start("messageReceiver");
            messageReceiver.receive(context);
        } finally {
            stopWatch.stop();
        }

        //2、参数验证 参数校验执行器（YML配置文件实现）
        try {
            stopWatch.start("paramValidateService");
            paramValidateService.execute(context);
        } finally {
            stopWatch.stop();
        }

        //3、获取渠道编码 即此请求通过本系统要请求的服务端- TODO 此处后期路由实现（Drools规则引擎实现）
        try {
            stopWatch.start("routerService");
            Map<String, Object>  paramMap=context.getMessageDescription().getDatas();
            paramMap.put(MessageTransportConstant.INST_CODE, "inst_pay_cmb");
        } finally {
            stopWatch.stop();
        }

        //4、业务处理
        try {
            stopWatch.start("businessServiceFactory");
            businessServiceFactory.getBusinessService(context.getClientTransCode()).execute(context);
        } finally {
            stopWatch.stop();
        }
    }
}

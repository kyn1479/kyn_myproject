package com.kyn.myproject.demo.common.template;

import com.kyn.myproject.demo.common.factory.BusinessServiceFactory;
import com.kyn.myproject.demo.common.transaction.TransactionService;
import com.kyn.myproject.demo.message_parse_engine.service.MessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/22 10:22
 */
@Component
public abstract class AbstractTemplate implements Template {
    private static Logger logger = LoggerFactory.getLogger(AbstractTemplate.class);

    /**
     * 消息接收器
     */
    @Autowired
    protected MessageReceiver messageReceiver;

    /**
     * 参数验证服务
     */
    @Resource(name = "paramValidateService")
    protected TransactionService paramValidateService;

    /**
     * 业务处理服务工厂
     */
    @Autowired
    protected BusinessServiceFactory businessServiceFactory;
}

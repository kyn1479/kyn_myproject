package com.kyn.myproject.demo.common.transaction;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description: 业务服务抽象类
 * @date 2021/1/22 17:58
 */
@Service
public abstract class AbstractBusinessService implements BusinessService {
    protected Logger logger = LoggerFactory.getLogger(AbstractBusinessService.class);

    /**
     * 指令提交
     * @param context
     */
    protected void submit(ProjectContext context) {
        String transCode = context.getServerTransCode();
        logger.info( "交易({})-请求支付渠道-开始", transCode);
//        messageTransport.submit(context);
        logger.info( "交易({})-请求支付渠道-结束", transCode);
    }
}

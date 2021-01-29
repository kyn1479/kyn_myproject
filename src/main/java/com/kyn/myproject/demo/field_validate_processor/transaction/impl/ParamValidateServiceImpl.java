package com.kyn.myproject.demo.field_validate_processor.transaction.impl;

import com.kyn.myproject.demo.common.entity.MessageDescription;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.transaction.AbstractTransactionService;
import com.kyn.myproject.demo.field_validate_processor.service.FieldValidateExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kangyanan
 * @Description: 参数校验服务
 * @date 2021/1/22 17:17
 */
@Service("paramValidateService")
public class ParamValidateServiceImpl extends AbstractTransactionService {
    private static final Logger logger = LoggerFactory.getLogger(ParamValidateServiceImpl.class);
    /**
     * 参数验证器
     */
    @Autowired
    private FieldValidateExecutor fieldValidateExecutor;

    @Override
    public void execute(ProjectContext context) {
        logger.info("进入参数校验服务transCode：{}",context.getClientTransCode());
        MessageDescription messageDescription = context.getMessageDescription();
        //1、交易码验证
        String transCode = context.getClientTransCode();
        //5、交易接口字段验证
        fieldValidateExecutor.validate(transCode, messageDescription.getDatas());
    }
}

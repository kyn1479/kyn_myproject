package com.kyn.myproject.field_validate_processor;

import com.kyn.myproject.KynMyprojectApplication;
import com.kyn.myproject.demo.common.constant.ValidatorConstant;
import com.kyn.myproject.demo.field_validate_processor.service.FieldValidateExecutor;
import com.kyn.myproject.demo.field_validate_processor.validator.FieldValidateYamlProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 参数校验测试类
 * @date 2021/1/20 14:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KynMyprojectApplication.class)
@WebAppConfiguration
public class FieldValidateTest {
    private static final Logger logger = LoggerFactory.getLogger(FieldValidateTest.class);

    /**
     * 字段验证处理器
     */
    @Autowired
    private FieldValidateYamlProcessor fieldValidateYamlProcessor;

    /**
     * 参数验证器
     */
    @Autowired
    private FieldValidateExecutor fieldValidateExecutor;

    /**
     * 脚本缓存加载测试
     */
    @Test
    public void testFieldValidate1(){
        String transCode="acctBalanceQry";
        Map<String, Object> map=new HashMap<>();
        map.put("channelCode","unicom");
        map.put("channelTransNo","20210120231230888811");
        map.put("channelDateTime","20210120090000");
        map.put("acctNo","62000231242343243211111133333333333333333333311111111111111111111111111");
        fieldValidateYamlProcessor.getStyle("acctNoStyle").validate(ValidatorConstant.ACCT_NO_STYLE, "银行卡号", "622000111111");
        fieldValidateExecutor.validate(transCode, map);
    }
}

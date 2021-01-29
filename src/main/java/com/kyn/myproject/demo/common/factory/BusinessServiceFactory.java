package com.kyn.myproject.demo.common.factory;

import com.kyn.myproject.demo.common.transaction.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author Kangyanan
 * @Description: 业务服务工厂类
 * @date 2021/1/22 17:54
 */
@Component
public class BusinessServiceFactory {
    private static final Logger logger = LoggerFactory.getLogger(BusinessServiceFactory.class);
    /**
     * 业务service bean 后缀
     */
    private final static String BUSINESSSERVICE_SUFF = "BusinessServiceImpl";

    @Autowired
    private Map<String, BusinessService> businessServiceMap;



    public BusinessService getBusinessService(String transCode) {
        String key = new StringBuilder(transCode).append(BUSINESSSERVICE_SUFF).toString();
        BusinessService businessService = businessServiceMap.get(key);
        return businessService;
    }
}

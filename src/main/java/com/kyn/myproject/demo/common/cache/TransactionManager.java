package com.kyn.myproject.demo.common.cache;

import com.kyn.myproject.demo.common.enums.TransactionEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 交易管理
 * @date 2021/1/22 9:58
 */
public class TransactionManager {
    /**
     * 交易-模板映射
     */
    private static final Map<TransactionEnum, String> templateMapping = new HashMap<TransactionEnum, String>();
    static {
        initTemplateMapping();
    }
    /**
     * 初始化交易-模板映射
     */
    private static void initTemplateMapping() {
        templateMapping.put(TransactionEnum.ACCT_BALANCE_QRY, "simpleQueryTemplate");
    }

    /**
     * 根据交易码获取模板
     * @param transCode
     * @return
     */
    public static String getTemplate(String transCode) {
        TransactionEnum transaction = TransactionEnum.getByCode(transCode);
        return templateMapping.get(transaction);
    }
}

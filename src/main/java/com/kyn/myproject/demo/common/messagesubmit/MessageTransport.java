package com.kyn.myproject.demo.common.messagesubmit;

import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.CsFlagEnum;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/22 18:21
 */
public interface MessageTransport {
    /**
     * @Description 报文提交
     * @Params
     * @Return
     * @Exceptions
     */
    void submit(ProjectContext context);

    /**
     * @Description 报文提交
     * @Params context 交易上下文
     * @Params csFlagEnum 客户端服务端机构标识
     * @Return
     * @Exceptions
     */
    void submit(ProjectContext context, CsFlagEnum csFlagEnum);
}

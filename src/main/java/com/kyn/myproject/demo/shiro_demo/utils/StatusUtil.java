package com.kyn.myproject.demo.shiro_demo.utils;

import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.exception.ProjectException;
import com.kyn.myproject.demo.shiro_demo.constant.StatusConst;
import com.kyn.myproject.demo.shiro_demo.enums.StatusEnum;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/29 13:54
 */
public class StatusUtil {

    /** 逻辑删除语句 */
    public static final String SLICE_DELETE = " set status=" + StatusConst.DELETE + " WHERE id=?";

    /** 不等于逻辑删除条件语句 */
    public static final String NOT_DELETE = "status != " + StatusConst.DELETE;

    /**
     * 获取状态StatusEnum对象
     * @param param 状态字符参数
     */
    public static StatusEnum getStatusEnum(String param){
        try {
            return StatusEnum.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ProjectException(SystemErrorCode.STATUS_ERROR);
        }
    }
}

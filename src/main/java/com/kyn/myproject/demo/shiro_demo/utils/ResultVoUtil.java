package com.kyn.myproject.demo.shiro_demo.utils;

import com.kyn.myproject.demo.common.entity.ProjectResult;
import com.kyn.myproject.demo.common.enums.SystemErrorCode;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/28 18:16
 */
public class ResultVoUtil {
    public static ProjectResult SAVE_SUCCESS = success("保存成功");

    /**
     * 操作成功
     *
     * @param msg    提示信息
     * @param object 对象
     */
    public static ProjectResult success(String msg, Object object) {
        ProjectResult resultVo = new ProjectResult();
        resultVo.setMessage(msg);
        resultVo.setCode(SystemErrorCode.SUCCESS.getCode());
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * 操作成功，使用默认的提示信息
     *
     * @param object 对象
     */
    public static ProjectResult success(Object object) {
        String message = SystemErrorCode.SUCCESS.getMessage();
        return success(message, object);
    }

    /**
     * 操作成功，返回提示信息，不返回数据
     */
    public static  ProjectResult success(String msg) {
        return success(msg, null);
    }

    /**
     * 操作成功，不返回数据
     */
    public static ProjectResult success() {
        return success(null);
    }

    /**
     * 操作有误
     *
     * @param code 错误码
     * @param msg  提示信息
     */
    public static ProjectResult error(String code, String msg) {
        ProjectResult resultVo = new ProjectResult();
        resultVo.setMessage(msg);
        resultVo.setCode(code);
        return resultVo;
    }

    /**
     * 操作有误，使用默认400错误码
     *
     * @param msg 提示信息
     */
    public static ProjectResult error(String msg) {
        String code = SystemErrorCode.ERROR.getCode();
        return error(code, msg);
    }

    /**
     * 操作有误，只返回默认错误状态码
     */
    public static ProjectResult error() {
        return error(null);
    }

}


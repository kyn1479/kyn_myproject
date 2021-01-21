//package com.kyn.myproject.demo.common.exception;
//
//import com.kyn.myproject.demo.common.entity.Header;
//import com.kyn.myproject.demo.common.entity.MessageEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Kangyanan
// * @Description:
// * @date 2021/1/20 15:07
// */
//@ControllerAdvice
//public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
//    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
//
//    /**
//     * @Description 支付网关异常处理
//     * @Params
//     * @Return
//     * @Exceptions
//     */
//    @ExceptionHandler(ProjectException.class)
//    public void handlePayGwException(HttpServletRequest request, HttpServletResponse response, ProjectException e) throws Exception {
//        logger.error("系统异常：异常码({})-异常信息({})", e.getErrorCode(),e.getErrorMsg());
//        MessageEntity messageEntity = new MessageEntity<Map<String, Object>>(new HashMap<String, Object>());
//        Header header = new Header();
//        header.setSuccess(false);
//        header.setErrorCode(e.getErrorCode());
//        header.setErrorMsg(e.getErrorMsg());
//        messageEntity.setHeader(header);
//
//        render(request, response, messageEntity);
//    }
//
//
//
//}

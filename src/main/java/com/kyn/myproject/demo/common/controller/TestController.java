package com.kyn.myproject.demo.common.controller;

import com.kyn.myproject.demo.common.entity.CommunicationEntity;
import com.kyn.myproject.demo.common.entity.CommunicationInstitution;
import com.kyn.myproject.demo.common.entity.MessageDescription;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.ParamType;
import com.kyn.myproject.demo.common.enums.ProcessPhaseEnum;
import com.kyn.myproject.demo.common.enums.ProtocolTypeEnum;
import com.kyn.myproject.demo.communications_protocol_component.communication.ServiceManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kangyanan
 * @Description: 测试客户端请求
 * @date 2021/1/22 11:23
 */
@RequestMapping(path="/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/{instCode}/{transCode}")
    public void accessHtml(HttpServletRequest request, HttpServletResponse response, @PathVariable("instCode") String instCode, @PathVariable("transCode") String transCode) {
        logger.info("支付网关内部交易请求-客户端渠道编码({})-客户端交易码({})", instCode, transCode);
        ProjectContext context=new ProjectContext();
        //1、构建context
        context.addParam(ParamType.HTTP_SERVER_REQUEST, request);
        context.addParam(ParamType.HTTP_SERVER_RESPONSE, response);
        context.setClientInstCode(instCode);//客户端渠道编码
        context.setClientTransCode(transCode);//客户端交易码
        context.addParam(ParamType.PROTOCOL, ProtocolTypeEnum.HTTP);//http协议
        //1、初始化客户端信息 （后期改成配置，根据客户端请求编码instCode、交易类型transCode从数据库中读取加载）
        initMessageDescription(context);

        //2、根据通信协议类型获取服务端管理器
        ServiceManagerFactory.getServerManager((ProtocolTypeEnum) context.getParam(ParamType.PROTOCOL)).accept(context);

    }
    /**
     * @Description 初始化客户端消息描述信息
     * @Params context
     * @Return MessageDescription
     * @Exceptions
     */
    private MessageDescription initMessageDescription(ProjectContext context) {
        MessageDescription messageDescription = new MessageDescription();

        //客户端交易码
        String transCode = context.getClientTransCode();
        //客户端通道编码
        String instCode=context.getClientInstCode();

        messageDescription.setInstCode(instCode);
        messageDescription.setTransCode(transCode);

        //构建请求方通道基本信息
        CommunicationInstitution CommunicationInstitution=CreateCommunicationInstitution();
        messageDescription.setClientInstitution(CommunicationInstitution);

        //构建通讯配置信息
        CommunicationEntity communicationEntity =CreateCommunicationEntity();
        messageDescription.setCommunicationEntity(communicationEntity);

        //当前处理阶段
        messageDescription.setProcessPhase(ProcessPhaseEnum.CLIENT_REQUEST_RECEIVE);
        context.addParam(ParamType.MESSAGE_DESCRIPTION, messageDescription);

        return messageDescription;
    }

    /**
     * 构建请求端通道信息
     * @return
     */
    public CommunicationInstitution CreateCommunicationInstitution(){
        CommunicationInstitution communicationInstitution=new CommunicationInstitution();
        communicationInstitution.setId(Long.valueOf("1"));
        communicationInstitution.setInstName("请求机构名称");
        communicationInstitution.setInstCode("payDemoInstCode");
        communicationInstitution.setInstType("03");
        return communicationInstitution;
    }
    /**
     * 构建通讯配置信息（如：报文格式、编码、等）
     * @return
     */
    public CommunicationEntity CreateCommunicationEntity(){
        CommunicationEntity communicationEntity=new CommunicationEntity();
        communicationEntity.setInstTransTypeId(1L);
        communicationEntity.setCommuName("招商银行余额查询");
        communicationEntity.setCommuCode("payCmb_acctBalanceQuery");
        communicationEntity.setCsFlag("01");
        communicationEntity.setReceiveTransformType("json");
        communicationEntity.setReceiveTransformEncode("01");
        communicationEntity.setMessageParserId("DefaultParser");
        return communicationEntity;
    }
}

package com.kyn.myproject.communications_protocol_component;

import com.kyn.myproject.KynMyprojectApplication;
import com.kyn.myproject.demo.common.cache.TemplateCacheManager;
import com.kyn.myproject.demo.common.entity.*;
import com.kyn.myproject.demo.common.enums.ProcessPhaseEnum;
import com.kyn.myproject.demo.communications_protocol_component.communication.http.HttpClientManager;
import com.kyn.myproject.demo.message_assemble_engine.service.MessageAssembleEngine;
import org.junit.Before;
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
 * @Description: 协议测试类 for HTTP Server
 * @date 2021/1/21 10:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KynMyprojectApplication.class)
@WebAppConfiguration
public class ProtocolServiceTest_HTTP_Client {
    private static final Logger logger = LoggerFactory.getLogger(ProtocolServiceTest_HTTP_Client.class);
    /**
     * http客户端管理类
     */
    @Autowired
    HttpClientManager httpClientManager;
    /**
     * 模板引擎接口类
     */
    @Autowired
    private MessageAssembleEngine messageAssembleEngine;
    /**
     * 模板缓存管理类
     */
    @Autowired
    private TemplateCacheManager templateCacheManager;

    //上下文
    ProjectContext context=new ProjectContext();
    @Before
    public void testBefore(){
        MessageDescription messageDescription =new MessageDescription();
        context.setMessageDescription(messageDescription);

        //生成通信信息
        CommunicationEntity communicationEntity=createCommunicationEntity();
        messageDescription.setCommunicationEntity(communicationEntity);
        //构建请求报文信息
        MessageEnvelope serverRequestMessageEnvelope=createMessageAssemble(context);
        CommunicationInstitution communicationInstitution =createCommunicationInstitution();

        messageDescription.setInstCode("cmb");//支付渠道编码
        messageDescription.setTransCode("acctBalance");//交易码
        messageDescription.setProcessPhase(ProcessPhaseEnum.SERVER_REQUEST_SEND);//服务端消息发送阶段
        messageDescription.setServerRequestMessageEnvelope(serverRequestMessageEnvelope);//发送到服务端的请求报文
        //通信信息 此处为请求出去的信息
        messageDescription.setServerInstitution(communicationInstitution);
        context.setServerInstCode("cib");
        context.setServerTransCode("acctBalanceQuery");
    }

    /**
     * 测试http请求
     */
    @Test
    public void testProtocolService1(){
        httpClientManager.messageSend(context);
        Object obj=context.getMessageDescription().getServerResponseMessageEnvelope().getContent();
        logger.info("查询结果：{}",obj);
    }

    /**
     * 创建通信通道信息
     * @return
     */
    public CommunicationInstitution createCommunicationInstitution(){
        CommunicationInstitution serverInstitution=new CommunicationInstitution();
        serverInstitution.setId(Long.valueOf("100"));
        serverInstitution.setInstCode("cmb");
        serverInstitution.setInstName("招商银行");
        return serverInstitution;
    }

    /**
     * 生成通信信息 （以招商银行余额查询为测试）
     * @return
     */
    public CommunicationEntity createCommunicationEntity(){
        //通信信息(此测试服务端通信，即请求通道报文，下面配置为服务端通信信息，此时当前系统为客户端，请求方为客户端、被请求方为服务端)
        CommunicationEntity communicationEntity=new CommunicationEntity();
        communicationEntity.setCommuCode("cmb_acctBalanceQuery");//通讯编码
        communicationEntity.setCommuName("招商银行余额查询");//通讯名称
        communicationEntity.setCsFlag("01");// 客户端/服务端交易标识(01-客户端  02-服务端)
        communicationEntity.setMessageParserId("DefaultParser");//消息解析器在spring上下文中的ID 解析通道返回的报文
        communicationEntity.setReqUrl("http://172.18.171.233:8015/VirtualBank/b2e/cmb/test");
        communicationEntity.setProtocolType("01");
        communicationEntity.setConnectTimeout(60000);
        communicationEntity.setReadTimeout(6000);
        communicationEntity.setReadTimeout(6000);
        communicationEntity.setSendTransformType("xml");
        communicationEntity.setReceiveTransformType("xml");
        communicationEntity.setReceiveTransformEncode("02");
        communicationEntity.setSendTransformEncode("02");
        communicationEntity.setHttpMethod("02");
        communicationEntity.setCsFlag("01");
        return communicationEntity;
    }
    /**
     * 报文模板生成请求报文
     */
    public MessageEnvelope createMessageAssemble(ProjectContext projectContext){
        Map<String, Object> datas =new HashMap<>();
        datas.put("cmbUser","登录用户名");
        datas.put("acctNo","62224445556666");
        projectContext.getMessageDescription().putDatas(datas);

        MessageTemplate messageTemplate=templateCacheManager.get("CMB_01");

        MessageEnvelope messageEnvelope = messageAssembleEngine.messageAssemble(messageTemplate , projectContext);

        System.out.println("组装后报文====="+messageEnvelope.getContent());
        return messageEnvelope;
    }



}

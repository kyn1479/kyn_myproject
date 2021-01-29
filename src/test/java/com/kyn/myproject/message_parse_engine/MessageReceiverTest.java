package com.kyn.myproject.message_parse_engine;

import com.kyn.myproject.KynMyprojectApplication;
import com.kyn.myproject.demo.common.entity.CommunicationEntity;
import com.kyn.myproject.demo.common.entity.MessageDescription;
import com.kyn.myproject.demo.common.entity.MessageEnvelope;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.EncodeEnum;
import com.kyn.myproject.demo.common.enums.MessageFormatEnum;
import com.kyn.myproject.demo.common.enums.ProcessPhaseEnum;
import com.kyn.myproject.demo.message_parse_engine.service.MessageReceiver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/19 23:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KynMyprojectApplication.class)
@WebAppConfiguration
public class MessageReceiverTest {

    @Autowired
    MessageReceiver messageReceiver;

    ProjectContext context=new ProjectContext();

    String content="{\n" +
            "    \"body\":{\n" +
            " \"charset\": \"UTF-8\",\n" +
            "    \"amount\": \"1\",\n" +
            "    \"callBackUrl\": \"http://www.baidu.com\",\n" +
            "    \"merExtend\": \"商户扩展字段\",\n" +
            "    \"merOrderDate\": \"20201230\",\n" +
            "    \"merOrderNo\": \"39413357759004633418\",\n" +
            "    \"reqTime\": \"20201230172537\",\n" +
            "    \"terminalId\": \"00000001\",\n" +
            "    \"merNo\": \"301100910043045\",\n" +
            "    \"signType\": \"RSA-SHA256\",\n" +
            "    \"tradeMode\": \"0001\",\n" +
            "    \"goodsName\": \"商品名称\",\n" +
            "    \"goodsDesc\": \"商品描述\"\n" +
            "    }\n" +
            "}";
    @Before
    public void testBefore(){
        MessageDescription messageDescription =new MessageDescription();
        //请求/返回报文信息
        MessageEnvelope clientRequestMessageEnvelope=new MessageEnvelope();
        clientRequestMessageEnvelope.setContent(content);
        clientRequestMessageEnvelope.setEncode(EncodeEnum.UTF8);
        clientRequestMessageEnvelope.setMessageFormat(MessageFormatEnum.TEXT);
        //通信信息
        CommunicationEntity communicationEntity=new CommunicationEntity();
        communicationEntity.setCommuCode("CommuCode");//通讯编码
        communicationEntity.setCommuName("通讯名称");//通讯名称
        communicationEntity.setCsFlag("01");// 客户端/服务端交易标识(01-客户端  02-服务端)
        communicationEntity.setMessageParserId("DefaultParser");//消息解析器在spring上下文中的ID

        messageDescription.setProcessPhase(ProcessPhaseEnum.CLIENT_REQUEST_RECEIVE);//接收客户端请求阶段
        messageDescription.setClientRequestMessageEnvelope(clientRequestMessageEnvelope);
        messageDescription.setCommunicationEntity(communicationEntity);
        context.setMessageDescription(messageDescription);
    }


    /**
     * 脚本缓存加载测试
     */
    @Test
    public void testMessageReceiver(){
        messageReceiver.receive(context);
    }
}

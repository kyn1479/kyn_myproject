package com.kyn.myproject.communications_protocol_component;

import com.kyn.myproject.KynMyprojectApplication;
import com.kyn.myproject.demo.common.entity.Header;
import com.kyn.myproject.demo.common.entity.MessageEntity;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 协议测试类 for HTTP Client
 * @date 2021/1/22 10:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KynMyprojectApplication.class)
@WebAppConfiguration
public class ProtocolServiceTest_HTTP_Server {


    MessageEntity<Map<String, Object>> mapMessageEntity=new MessageEntity<>();
    String url="http://localhost:8081/test/paycore/acctBalanceQry";

    @Test
    public void testProtocolService1(){
        Map<String, Object> body = new HashMap<>();
        body.put("deptCode","0308");
        body.put("instCode","payCmb");
        body.put("acctNo","62200112233445566");
        mapMessageEntity=buildMessageEntity(body);
        httpSend(url,mapMessageEntity);
    }


    /**
     * 发送请求
     * @param url
     * @param messageEntity
     * @return
     */
    protected static MessageEntity httpSend(String url, MessageEntity<Map<String, Object>> messageEntity) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<MessageEntity<Map<String, Object>>> entity = new HttpEntity<MessageEntity<Map<String, Object>>>(messageEntity, headers);
        MessageEntity<Map<String, Object>> resp = new RestTemplate().postForObject(url, entity, MessageEntity.class);
        return resp;
    }

    /**
     * 构建请求实体 MessageEntity
     * @param body
     * @return
     */
    protected static MessageEntity<Map<String, Object>> buildMessageEntity(Map<String, Object> body) {
        Header header = new Header();
        header.setChannelCode("paycore");
        header.setChannelDateTime(DateTime.now().toString("yyyyMMddHHmmss"));
        header.setTransCode("baseCache");
        header.setTransType("modify");
        header.setChannelTransNo(System.currentTimeMillis() + "");

        MessageEntity<Map<String, Object>> messageEntity = new MessageEntity<>();
        messageEntity.setHeader(header);
        messageEntity.setBody(body);
        return messageEntity;
    }
}

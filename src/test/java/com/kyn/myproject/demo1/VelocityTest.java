package com.kyn.myproject.demo1;

import com.kyn.myproject.KynMyprojectApplication;
import com.kyn.myproject.message_assemble_engine.demo1.cache.TemplateCacheManager;
import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageDescription;
import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageEnvelope;
import com.kyn.myproject.message_assemble_engine.demo1.entity.MessageTemplate;
import com.kyn.myproject.message_assemble_engine.demo1.entity.ProjectContext;
import com.kyn.myproject.message_assemble_engine.demo1.service.message.MessageAssembleEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/18 16:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KynMyprojectApplication.class)
@WebAppConfiguration
public class VelocityTest {
    @Autowired
    private MessageAssembleEngine messageAssembleEngine;

    @Autowired
    private TemplateCacheManager templateCacheManager;

    /**
     * 报文JSON格式
     */
    @Test
    public void testVelocity1(){
        MessageDescription messageDescription =new MessageDescription();
        Map<String, Object> datas =new HashMap<>();
        datas.put("main_template_key","main_template_value_123");
        datas.put("sub1_template_key","sub1_template_key_123");
        datas.put("sub2_template_key","sub2_template_key_123");
        messageDescription.putDatas(datas);
        ProjectContext projectContext=new ProjectContext();
        projectContext.setMessageDescription(messageDescription);

        MessageTemplate messageTemplate=templateCacheManager.get("CIB_0115");
        MessageEnvelope env = messageAssembleEngine.messageAssemble(messageTemplate , projectContext);
        System.out.println("组装后报文====="+env.getContent());
    }
    /**
     * 报文XML格式
     */
    @Test
    public void testVelocity2(){
        MessageDescription messageDescription =new MessageDescription();
        Map<String, Object> datas =new HashMap<>();
        datas.put("cmbUser","前置机登录用户名");
        datas.put("acctNo","62200111999000111");
        messageDescription.putDatas(datas);
        ProjectContext projectContext=new ProjectContext();
        projectContext.setMessageDescription(messageDescription);
        MessageTemplate messageTemplate=templateCacheManager.get("CMB_01");
        MessageEnvelope env = messageAssembleEngine.messageAssemble(messageTemplate , projectContext);
        System.out.println("XML格式组装后报文====="+env.getContent());
    }

    //测试嵌套模板渲染
    @Test
    public void testVelocity3(){
        MessageDescription messageDescription =new MessageDescription();
        Map<String, Object> datas =new HashMap<>();
        datas.put("main_template_key","main_template_value_123");
        datas.put("sub1_template_key","sub1_template_key_123");
        datas.put("sub2_template_key","sub2_template_key_123");
        messageDescription.putDatas(datas);
        ProjectContext projectContext=new ProjectContext();
        projectContext.setMessageDescription(messageDescription);
        //主模板
        String template = "#set($main_template_key=$data.main_template_key)\n" +
                "$JSON.toJSONString($main_template_key)-----$VelocityUtil.evaluate($context , $header)----------->$StringUtils.isBlank(100);-------->$VelocityUtil.evaluate($context , $sub1)-------->$VelocityUtil.evaluate($context , $sub2)";

        //子模板sub1
        String sub1="#set($sub1_template_key = $data.sub1_template_key)\n" +
                "$JSON.toJSONString($sub1_template_key)";

        //子模板sub2
        String sub2="#set($sub2_template_key = $data.sub2_template_key)\n" +
                "$JSON.toJSONString($sub2_template_key)";

        //头模板
        String header="#set($map =\n" +
                "{\n" +
                "    \"Content-Type\":\"application/json;charset=UTF-8\"\n" +
                "})\n" +
                "$map";
        MessageTemplate mt = new MessageTemplate();
        mt.setMainTemplate(template);
        mt.setHeaderTemplate(header); //头模板
        mt.setSubTemplate1(sub1);         //子模板1
        mt.setSubTemplate2(sub2);         //子模板2
        MessageEnvelope env = messageAssembleEngine.messageAssemble(mt , projectContext);
        System.out.println("----------------------------------");
        System.out.println(env.getContent());
    }



}

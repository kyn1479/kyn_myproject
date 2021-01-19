package com.kyn.myproject.message_parse_engine;

import com.kyn.myproject.KynMyprojectApplication;
import com.kyn.myproject.demo.common.cache.GroovyScriptCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/1/19 19:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KynMyprojectApplication.class)
@WebAppConfiguration
public class MessageParserTest {
    @Autowired
    GroovyScriptCache groovyScriptCache;
    /**
     * 脚本缓存加载测试
     */
    @Test
    public void testMessageParser1(){
        groovyScriptCache.init();
    }
}

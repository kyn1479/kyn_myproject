package com.kyn.myproject.message_assemble_engine.demo1.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Kangyanan
 * @Description: VELICITY模板渲染
 * @date 2021/1/18 15:57
 */
public class VelocityUtil {

    /**
     * VELICITY模板渲染
     * @param vContext
     * @param template
     * @return
     */
    public static String evaluate(VelocityContext vContext, String template) {
        Writer writer = new StringWriter();
        try {
            Velocity.evaluate(vContext, writer, "VelocityUtil.evaluate", template);
            return writer.toString();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
}

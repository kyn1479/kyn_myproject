package com.kyn.myproject.demo.communications_protocol_component.communication.http;

import com.kyn.myproject.demo.common.entity.CommunicationEntity;
import com.kyn.myproject.demo.common.entity.MessageDescription;
import com.kyn.myproject.demo.common.entity.MessageEnvelope;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.EncodeEnum;
import com.kyn.myproject.demo.common.enums.MessageFormatEnum;
import com.kyn.myproject.demo.communications_protocol_component.communication.ProtocolServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
/**
 * @author Kangyanan
 * @Description: http协议处理服务实现类-服务端（处理请求此系统的报文）
 * @date 2021/1/22 9:44
 */
@Service("httpServerService")
public class HttpServerService extends ProtocolServerService {
    private static final Logger logger = LoggerFactory.getLogger(HttpServerService.class);

    @Override
    public void convertInMessage(ProjectContext context) {
        logger.info( "交易({})-读取报文-开始", context.getClientTransCode());
        MessageDescription messageDescription = context.getMessageDescription();

        //1、获取支付机构配置的接收消息的类型
        CommunicationEntity communicationEntity = messageDescription.getCommunicationEntity();
        MessageFormatEnum messageFormatEnum = communicationEntity.getReceiveMessageFormat();//报文格式
        EncodeEnum encodeEnum = communicationEntity.getReceiveMessageEncode();//报文编码

        //2、根据不同的消息类型进行不同的读取
        Object message = messageRead(context.getHttpServletRequest(), messageFormatEnum, encodeEnum.getMessage());

        logger.info( "交易({})-读取报文-报文内容:\n" + context.getClientTransCode(),message);
        MessageEnvelope clientRequestMessageEnvelope = new MessageEnvelope();
        clientRequestMessageEnvelope.setMessageFormat(messageFormatEnum);
        clientRequestMessageEnvelope.setEncode(encodeEnum);
        clientRequestMessageEnvelope.setContent(message);
        messageDescription.setClientRequestMessageEnvelope(clientRequestMessageEnvelope);
        logger.info("交易({})-读取报文-结束", context.getClientTransCode());
    }


    @Override
    public void convertOutMessage(ProjectContext context) {

    }

    /**
     * @Description 读取消息
     * @Params
     * @Return Object
     * @Exceptions
     */
    private Object messageRead(HttpServletRequest request, MessageFormatEnum messageType, String charSet) {
        switch (messageType) {
            case FORM:
                return readMap(request);
            case BYTE:
                return readByte(request);
            default:
                return readText(request, charSet);
        }
    }

    /**
     * @Description 读取map格式数据
     * @Params
     * @Return Object
     * @Exceptions
     */
    private Object readMap(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, Object> result = new HashMap<String, Object>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * @Description 读取byte格式数据
     * @Params
     * @Return Object
     * @Exceptions
     */
    private Object readByte(HttpServletRequest request) {
        Object content = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            byte[] buf = new byte[8 * 1024];
            int offset = -1;
            while ((offset = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, offset);
            }
            content = outputStream.toByteArray();
        } catch (Exception e) {

        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }

        return content;
    }

    /**
     * @Description 读取文本格式
     * @Params
     * @Return Object
     * @Exceptions
     */
    private Object readText(HttpServletRequest request, String charset) {
        Object content = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(),
                    Charset.forName(charset)));
            StringBuilder contentString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                contentString.append(line);
            }
            content = contentString.toString();
        } catch (Exception ex) {

        } finally {
            IOUtils.closeQuietly(reader);
        }
        return content;
    }

}

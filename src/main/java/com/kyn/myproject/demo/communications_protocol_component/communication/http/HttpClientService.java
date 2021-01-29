package com.kyn.myproject.demo.communications_protocol_component.communication.http;

import com.kyn.myproject.demo.common.constant.MessageTransportConstant;
import com.kyn.myproject.demo.common.entity.CommunicationEntity;
import com.kyn.myproject.demo.common.entity.MessageDescription;
import com.kyn.myproject.demo.common.entity.MessageEnvelope;
import com.kyn.myproject.demo.common.entity.ProjectContext;
import com.kyn.myproject.demo.common.enums.*;
import com.kyn.myproject.demo.common.exception.ProjectException;
import com.kyn.myproject.demo.common.util.*;
import com.kyn.myproject.demo.communications_protocol_component.communication.ProtocolClientService;
import org.apache.commons.lang.BooleanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kangyanan
 * @Description: http协议处理服务实现类-客户端
 * @date 2021/1/20 17:38
 */
@Service
public class HttpClientService extends ProtocolClientService {

    @Override
    protected void messageSend(ProjectContext context) {
        HttpClient httpClient = context.getHttpClient();
        HttpUriRequest httpMethod = context.getHttpMethod();
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpMethod);
        } catch (IOException e) {
            throw new ProjectException(SystemErrorCode.COMMUNICATION_EXCEPTION, e);
        }
        context.addParam(ParamType.HTTP_CLIENT_RESPONSE, httpResponse);
    }

    @Override
    public void convertInMessage(ProjectContext context) {
        MessageDescription messageDescription = context.getMessageDescription();
        messageDescription.setProcessPhase(ProcessPhaseEnum.SERVER_RESPONSE_RECEIVE);
        HttpResponse httpResponse = context.getHttpResponse();

        MessageEnvelope messageEnvelope = new MessageEnvelope();
        Object content = null;
        try {
            CommunicationEntity communicationEntity = messageDescription.getCommunicationEntity();
            MessageFormatEnum messageFormat = communicationEntity.getReceiveMessageFormat();
            messageEnvelope.setMessageFormat(messageFormat);
            if (messageFormat == MessageFormatEnum.BYTE) {
                content = EntityUtils.toByteArray(httpResponse.getEntity());
            } else if (messageFormat == MessageFormatEnum.FILE) {
                content = readResponse(httpResponse, communicationEntity);
            } else {
                String charset = EncodeEnum.getByCode(communicationEntity.getReceiveTransformEncode()).getMessage();
                content = EntityUtils.toString(httpResponse.getEntity(), charset);
            }
            messageEnvelope.setContent(content);
        } catch (IOException e) {
            throw new ProjectException(SystemErrorCode.COMMUNICATION_EXCEPTION, e);
        }
        messageDescription.setServerResponseMessageEnvelope(messageEnvelope);
    }

    @Override
    public void convertOutMessage(ProjectContext context) {
        MessageDescription messageDescription = context.getMessageDescription();
        CommunicationEntity communicationEntity = messageDescription.getCommunicationEntity();
        //主要是构造参数
        HttpUriRequest httpUriRequest = context.getHttpMethod();
        MessageEnvelope messageEnvelope = messageDescription.getServerRequestMessageEnvelope();
        messageEnvelope.setMessageFormat(communicationEntity.getSendMessageFormat());
        //根据GET/POST来采用不同的构造方法
        try {
            contructParams(messageEnvelope, httpUriRequest, communicationEntity);
        } catch (Exception e) {
            throw new ProjectException(SystemErrorCode.COMMUNICATION_EXCEPTION, e);
        }
    }


    private String readResponse(HttpResponse httpResponse, CommunicationEntity communicationEntity) throws IOException {

        InputStream inputStream = null;

        try {
            // 从http响应信息中获取输入流
            HttpEntity entity = httpResponse.getEntity();
            inputStream = entity.getContent();

            // 获取本地文件保存目录，如果本地目录为空则直接读取文件内容返回（文件内容为十六进制转码后的字符）
            String localFilePath = StringUtils.valueOf(communicationEntity.getProperty(MessageTransportConstant.COMMUNICATION_LOCAL_FILE_PATH));
            if (StringUtils.isBlank(localFilePath)) {
                ByteArrayOutputStream output = new ByteArrayOutputStream((int) entity.getContentLength());

                IOUtils.copy(inputStream, output);

                return HexConvertorUtil.byte2Hex(output.toByteArray());
            }

            // 是否需要在路径后面加一个日期
            String localFilePathDate = StringUtils.valueOf(communicationEntity.getProperty(MessageTransportConstant.COMMUNICATION_LOCAL_FILE_PATH_DATE));
            if (BooleanUtils.toBoolean(localFilePathDate)) {
                localFilePath += File.separator + DateUtil.getCurrentDateStr();
            }

            // 获取本地文件名，如果本地文件为空，则产生UUID为文件名
            String localFileName = StringUtils.valueOf(communicationEntity.getProperty(MessageTransportConstant.COMMUNICATION_LOCAL_FILE_NAME));
            if (StringUtils.isBlank(localFileName)) {
                localFileName = UUID.randomUUID().toString() + StringUtils.valueOf(communicationEntity.getProperty(MessageTransportConstant.COMMUNICATION_LOCAL_FILE_NAME_SUFFIX));
            }

            localFilePath += File.separator + localFileName;

            boolean result = FileUtils.copyInputStreamToFile(inputStream, localFilePath);
            if (result) {
                return localFilePath;
            } else {
                return null;
            }

        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
    /**
     * 构建请求报文
     * @param outMessage
     * @param httpUriRequest
     * @param communicationEntity
     * @throws IOException
     * @throws URISyntaxException
     */
    private void contructParams(MessageEnvelope outMessage, HttpUriRequest httpUriRequest, CommunicationEntity communicationEntity) throws IOException, URISyntaxException {
        Map<String, String> headers = outMessage.getExtraContent();
        Map<String, String> params = null;
        String content = null;
        switch (outMessage.getMessageFormat()) {
            case FORM: {
                params = MapUtils.covertText2MapByRule(StringUtils.valueOf(outMessage.getContent()));
                break;
            }
            case JSONTOFORM: {
                params = MapUtils.covertToJSON(StringUtils.valueOf(outMessage.getContent()));
                break;
            }
            default: {
                content = (String) outMessage.getContent();
                break;
            }
        }
        String encode = EncodeEnum.getByCode(communicationEntity.getSendTransformEncode()).getMessage();
        String contentType = MessageFormatEnum.getByCode(communicationEntity.getSendTransformType()).getMessage();
        if (httpUriRequest instanceof HttpPost) {
            HttpPost httpPost = (HttpPost) httpUriRequest;
            addRequestHeader(headers, httpPost);
            addParameter4Post(params, httpPost, encode);
            addContent(content, httpPost, encode, contentType);
        } else {
            HttpGet httpGet = (HttpGet) httpUriRequest;
            addRequestHeader(headers, httpGet);
            addParameter4Get(params, httpGet, encode);
        }
    }

    /**
     * @Description 向post请求中添加header
     * @Params
     * @Return
     * @Exceptions
     */
    private void addRequestHeader(Map<String, String> headers, HttpUriRequest httpUriRequest) {
        if (CollectionUtils.isEmpty(headers)) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpUriRequest.addHeader(entry.getKey(), entry.getValue());
        }
    }
    /**
     * 向post请求添加参数
     *
     * @param params
     * @param httpPost
     * @param encode
     * @throws UnsupportedEncodingException
     */
    private void addParameter4Post(Map<String, String> params, HttpPost httpPost, String encode) throws UnsupportedEncodingException {
        List<NameValuePair> pairs = getNameValuePair(params);
        HttpEntity httpEntity = new UrlEncodedFormEntity(pairs, encode);
        httpPost.setEntity(httpEntity);
    }

    private List<NameValuePair> getNameValuePair(Map<String, String> params) {
        List<NameValuePair> pairs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return pairs;
    }

    /**
     * 添加post请求内容
     * @param content
     * @param httpPost
     * @param encode
     * @param contentType
     * @throws UnsupportedEncodingException
     */
    private void addContent(String content, HttpPost httpPost, String encode, String contentType) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(content)) {
            return;
        }
        StringEntity stringEntity = new StringEntity(content, encode);
        stringEntity.setContentType(contentType);
        stringEntity.setContentEncoding(encode);
        httpPost.setEntity(stringEntity);
    }

    /**
     * 向get请求添加参数
     *
     * @param params
     * @param httpGet
     * @param encode
     * @throws IOException
     * @throws URISyntaxException
     */
    private void addParameter4Get(Map<String, String> params, HttpGet httpGet, String encode) throws IOException, URISyntaxException {
        List<NameValuePair> pairs = getNameValuePair(params);
        String str = EntityUtils.toString(new UrlEncodedFormEntity(pairs, encode));
        httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
    }
}

package com.kyn.myproject.demo.communications_protocol_component.communication;

import com.kyn.myproject.demo.common.constant.MessageTransportConstant;
import com.kyn.myproject.demo.common.entity.*;
import com.kyn.myproject.demo.common.enums.*;
import com.kyn.myproject.demo.common.exception.ProjectException;
import com.kyn.myproject.demo.common.util.StringUtils;
import com.kyn.myproject.demo.communications_protocol_component.communication.ssl.HttpClientBuilder;
import com.kyn.myproject.demo.common.security.CertService;
import com.kyn.myproject.demo.common.security.KeyStoreObj;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: http客户端管理类
 * @date 2021/1/20 16:16
 */
@Component
public class HttpClientManager implements ClientManager, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(HttpClientManager.class);

    @Autowired
    private CertService certService;
    /**
     * 客户端服务执行器
     */
    @Autowired
    private HttpClientService httpClientService;
    /**
     * HTTP客户端缓存
     */
    private Map<String, HttpClient> clients = new HashMap<String, HttpClient>();

    @Override
    public void messageSend(ProjectContext context) {
        MessageDescription messageDescription = context.getMessageDescription();
        CommunicationEntity communicationEntity = messageDescription.getCommunicationEntity();

        //1、通过通道机构号key进行客户端获取；
        CommunicationInstitution communicationInstitution = messageDescription.getServerInstitution();

        if (StringUtils.isBlank(communicationInstitution.getId())) {
            throw new ProjectException(SystemErrorCode.OPTION_VALIDATE_ERROR,new Object[]{"通讯key->支付机构ID"});
        }

        //key->服务端通道方ID
        StringBuilder key = new StringBuilder(StringUtils.valueOf(communicationInstitution.getId()));
        Map<String, Object> datas = messageDescription.getDatas();

        //2、获取HttpClient,如果获取不到进行初始化,初始化后加入缓存；
        HttpClient httpClient = getHttpClient(key.toString(), communicationEntity, ProtocolTypeEnum.getByCode(communicationEntity.getProtocolType()), datas);
        context.addParam(ParamType.HTTP_CLIENT, httpClient);

        //3、生成调用参数；
        //根据GET/POST创建不同的请求(HttpGet/HttpPost)
        HttpUriRequest request = createHttpUriRequest(communicationEntity);
        context.addParam(ParamType.HTTP_METHOD, request);

        //4、获取协议处理客户端服务进行服务处理；
        httpClientService.hand(context);
    }

    /**
     * 创建HTTP请求方法
     * @param communicationEntity
     * @return
     */
    private HttpUriRequest createHttpUriRequest(CommunicationEntity communicationEntity) {
        HttpUriRequest httpUriRequest = null;
        String url = communicationEntity.getReqUrl();

        String httpMethod = communicationEntity.getHttpMethod();
        if (StringUtils.equalsIgnoreCase(httpMethod, HttpMethodEnum.ALL.getCode())
                || StringUtils.equalsIgnoreCase(httpMethod, HttpMethodEnum.POST.getCode())) {
            httpUriRequest = new HttpPost(url);
        } else if (StringUtils.equalsIgnoreCase(httpMethod, HttpMethodEnum.GET.getCode())) {
            httpUriRequest = new HttpGet(url);
        } else {
            throw new RuntimeException(SystemErrorCode.SYSTEM_ERROR.getCode());
        }
        logger.info("http请求url: {}", httpUriRequest.getURI().toString());

        Integer readTimeOut=(communicationEntity.getReadTimeout()==null? MessageTransportConstant.SYS_DEFAULT_READ_TIMEOUT:communicationEntity.getReadTimeout());
        Integer connectTimeOut=(communicationEntity.getConnectTimeout()==null? MessageTransportConstant.SYS_DEFAULT_CONNECT_TIMEOUT:communicationEntity.getConnectTimeout());


        //设置通讯参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                .setConnectionRequestTimeout(connectTimeOut)
                .setSocketTimeout(readTimeOut).build();
        ((HttpRequestBase) httpUriRequest).setConfig(requestConfig);
        return httpUriRequest;
    }

    /**
     * 获取通信客户端
     * @param key
     * @param communicationEntity
     * @param protocolType
     * @param datas
     * @return
     */
    private HttpClient getHttpClient(String key, CommunicationEntity communicationEntity, ProtocolTypeEnum protocolType, Map<String, Object> datas) {
        HttpClient client = clients.get(key);
        if (client == null) {
            synchronized (clients) {
                if ((client = clients.get(key)) == null) {
                    //创建客户端
                    HttpClientBuilder hb = HttpClientBuilder.create();
//                    String httpsCert = communicationEntity.getHttpsCertCode();
//                    String httpsTrustCert = communicationEntity.getHttpsTrustCertCode();
                    //暂时忽略ssl
                    String httpsCert =null;
                    String httpsTrustCert =null;

                    //客户端SSL证书
                    if (StringUtils.isNotEmpty(httpsCert)) {
                        KeyStoreObj ks = createKeyStoreObj(httpsCert);
                        hb.setKeyStore(ks);
                    }

                    //可信任的服务端证书
                    if (StringUtils.isNotEmpty(httpsTrustCert)) {
                        KeyStoreObj ks = createKeyStoreObj(httpsTrustCert);
                        hb.setTrustStore(ks);
                    }

                    client = hb.build(communicationEntity.getKeepAlive());
                    clients.put(key, client);
                }
            }
        }
        return client;
    }


    private KeyStoreObj createKeyStoreObj(String certCode) {
        CertificateEntity ce = certService.getCertEnity(certCode);
        KeyStoreObj ks = new KeyStoreObj();
        ks.setKeyBytes(ce.getFileContent().getContent());
        ks.setStroePass(ce.getCertificate().getCertPwd());
        ks.setKeyAliase(ce.getCertificate().getCertAlias());
        ks.setKeyPass(ce.getCertificate().getPrivateKeyPwd());
        ks.setType(CertificateType.getByCode(ce.getCertificate().getCertStoreType()));
        ks.setKeyStoreType(KeyStoreType.getByCode(ks.getType().message()));
        return ks;
    }




    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceManagerFactory.registerClientManager(ProtocolTypeEnum.HTTP, this);
        ServiceManagerFactory.registerClientManager(ProtocolTypeEnum.HTTPS, this);
    }


}

package com.kyn.myproject.demo.communications_protocol_component.communication.ssl;

import com.kyn.myproject.demo.common.util.StringUtils;
import com.kyn.myproject.demo.common.security.KeyStoreObj;
import org.apache.http.client.HttpClient;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Kangyanan
 * @Description: HttpClient构建器
 * @date 2021/1/20 17:05
 */
public class HttpClientBuilder {
    private static Logger logger = LoggerFactory.getLogger(HttpClientBuilder.class);

    private KeyStoreObj keyStore;
    private KeyStoreObj trustStore;

    /**
     * 最大连接数
     */
    private static final int MAX_TOTAL_CONNECTIONS = 200;
    /**
     * 默认的每个路由的最大连接数
     */
    private static final int DEFAULT_MAX_PERROUTE = 100;


    public static HttpClientBuilder create() {
        return new HttpClientBuilder();
    }

    public HttpClientBuilder setTrustStore(KeyStoreObj trustStore) {
        this.trustStore = trustStore;
        return this;
    }

    public HttpClientBuilder setKeyStore(KeyStoreObj keyStore) {
        this.keyStore = keyStore;
        return this;
    }

    public HttpClient build() {
        return build(true);
    }
    public HttpClient build(boolean isKeepAlive) {
        RegistryBuilder registryBuilder = RegistryBuilder.create();
        registryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
        registryBuilder.register("https", buildSSLConnectionSocketFactory());
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registryBuilder.build());
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PERROUTE);
        if (isKeepAlive) {
            return HttpClients.custom().setConnectionManager(cm).disableAutomaticRetries().build();
        } else {
            return  HttpClients.custom().setConnectionManager(cm)
                    .setConnectionReuseStrategy((response, context) -> {
                        // 设置KeepAlive策略为不重用
                        return false;
                    }).disableAutomaticRetries().build();
        }
    }

    private SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
        SSLContext sslContext = null;
        SSLContextBuilder builder = SSLContexts.custom();
        if (null != keyStore) {  //服务端ssl证书
            KeyStore keyMaterial = null;
            try {
                keyMaterial = KeyStore.getInstance(null!=keyStore.getKeyStoreType() ? keyStore.getKeyStoreType().getValue() : KeyStore.getDefaultType());
                String storePass = StringUtils.isNotEmpty(keyStore.getStroePass()) ? keyStore.getStroePass() : "";
                String keyPass = StringUtils.isNotEmpty(keyStore.getKeyPass()) ? keyStore.getKeyPass() : "";
                keyMaterial.load(new ByteArrayInputStream(keyStore.getKeyBytes()), storePass.toCharArray());
                builder.loadKeyMaterial(keyMaterial, keyPass.toCharArray());
            } catch (Exception e) {
                logger.error("获取客户端ssl证书异常，异常信息：", e);
            }
        }
        try {
            if (trustStore != null) {
                KeyStore trustMaterial = KeyStore.getInstance(null!=trustStore.getKeyStoreType() ? trustStore.getKeyStoreType().getValue() : KeyStore.getDefaultType());
                String storePass = StringUtils.isNotEmpty(trustStore.getStroePass()) ? trustStore.getStroePass() : "";
                //  String keyPass = StringUtils.isNotEmpty(trustStore.getKeyPass())?trustStore.getKeyPass():"";
                trustMaterial.load(new ByteArrayInputStream(trustStore.getKeyBytes()), storePass.toCharArray());
                builder.loadTrustMaterial(trustMaterial, new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                        return true; //始终信任 ，绕过检查
                    }
                });
            }
            sslContext = builder.build();
        } catch (Exception e) {
            logger.error("获取服务端ssl证书异常，异常信息：", e);
        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"SSLv3","TLSv1","TLSv1.1","TLSv1.2"},
                null,
                new HostnameVerifier() {
                    //验证访问地址
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
        return sslsf;
    }
}

package com.xxx.wechat.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author team-lab
 * @date 2009-8-13
 */

public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final String SDF_YMDHMS = "yyyy-MM-dd hh:mm:ss";

    private static CloseableHttpClient createCloseableHttpClient(String url) {
        try {
        	SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());
            
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslsf)
                    .build();

            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(connManager)
                    .setConnectionManagerShared(true)
                    .build();
            return httpclient;
        } catch (Exception e) {
            LOGGER.error("设置信任签名证书失败", e);
        }

        return null;
    }

    public static ResponseResult sendFormPost(String url, Map<String, Object> params) throws Exception {
        return sendPost(url, params, "form");
    }

    public static ResponseResult sendJsonPost(String url, Map<String, Object> params) throws Exception {
        return sendPost(url, params, "json");
    }

    public static ResponseResult sendPost(String url, Map<String, Object> params, String contentType) throws Exception {
        // ログ出力
        params = null == params ? Maps.newHashMap() : params;
        String time;

        // 创建自定义的httpclient对象
        CloseableHttpClient client = createCloseableHttpClient(url);

        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        // 设置参数到请求对象中
        if (StringUtils.isNotBlank(contentType) && "json".equals(contentType)) {
            String jsonParams = new JSONObject(params).toString();
            httpPost.setEntity(new StringEntity(jsonParams, Charseter.UTF_8));

            if (LOGGER.isInfoEnabled()) {
                time = new DateTime(new Date()).toString(SDF_YMDHMS);
                LOGGER.info("[" + time + "] Access HttpClient START: [POST] " + url + " [" + jsonParams + "]");
            }

            httpPost.setHeader("Content-type", "application/json");
        } else {
            //装填参数
            List<NameValuePair> nvps = Lists.newArrayList();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charseter.UTF_8));

            if (LOGGER.isInfoEnabled()) {
                time = new DateTime(new Date()).toString(SDF_YMDHMS);
                LOGGER.info("[" + time + "] Access HttpClient START: [POST] " + url + " [" + nvps.toString() + "]");
            }

            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        }

        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);

        //获取结果实体
        String result = "";
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(entity, Charseter.UTF_8);
        }
        EntityUtils.consume(entity);

        ResponseResult responseResult = new ResponseResult(result, response.getStatusLine().getStatusCode());

        // 释放链接
        response.close();
        return responseResult;
    }

    private interface Charseter {

        String UTF_8 = "utf-8";

    }

    public static class ResponseResult {

        private String result;

        private Integer responseStatus;

        public String getResult() {
            return result;
        }

        public ResponseResult() {
        }

        public ResponseResult(String result, Integer responseStatus) {
            this.result = result;
            this.responseStatus = responseStatus;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }

        public void setResult(String result) {
            this.result = result;
        }

        public Integer getResponseStatus() {
            return responseStatus;
        }

        public void setResponseStatus(Integer responseStatus) {
            this.responseStatus = responseStatus;
        }

    }

}

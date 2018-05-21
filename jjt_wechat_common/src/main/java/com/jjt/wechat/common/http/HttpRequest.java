package com.jjt.wechat.common.http;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
/**
 * 通过连接池发送Http请求
 *
 */
public class HttpRequest {
	/**
	 * 发送http Get 请求
	 * @param requestUrl
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpResponseNullException 
	 */
	public static String httpGetRequest(String requestUrl) throws ClientProtocolException, IOException, HttpResponseNullException{
		String result = null;
		//获取http连接
		CloseableHttpClient httpClient = HttpPoolConnManager.httpClientInstance();
		//准备Get请求发送
		HttpGet get = new HttpGet(requestUrl);
		//接收响应
		CloseableHttpResponse response = httpClient.execute(get);
		//处理相应
		StatusLine statusLine = response.getStatusLine();
		if(statusLine.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES){
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}else if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if(entity == null){
				throw new ClientProtocolException("Response contains no content");
			}
			result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
			if(result == null){
				throw new HttpResponseNullException();
			}
		}
		return result;
	}
	/**
	 * 发送Http post请求
	 * @param requestUrl
	 * @param param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpResponseNullException 
	 */
	public static String httpPostRequest(String requestUrl, String param) throws ClientProtocolException, IOException, HttpResponseNullException{
		CloseableHttpClient httpClient = HttpPoolConnManager.httpClientInstance();
		String result = null;
		StringEntity requestEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
		HttpPost post = new HttpPost(requestUrl);
		post.setEntity(requestEntity);
		// 获取http响应
		HttpResponse response = httpClient.execute(post);
		StatusLine statusLine = response.getStatusLine();
		if(statusLine.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES){
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, Consts.UTF_8);
			if(result == null){
				throw new HttpResponseNullException();
			}
		}
		return result;
	}
	
}

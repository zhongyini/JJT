package com.xxx.wechat.common.wechat.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.response.GetJsApiTicketResponse;

public class JsApiTicketApi extends BaseApi {

	public JsApiTicketApi(String accessToken) {
		super(accessToken);
	}
	
	/**
	 * 获取JsApiTicket
	 * @return
	 * @throws HttpResponseNullException
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public GetJsApiTicketResponse getJsApiTicket() throws ClientProtocolException, IOException, HttpResponseNullException{
		String method = "getJsApiTicket";
		String requestUrl = String.format(Constant.WechatUrl.GET_JSAPI_TICKET, accessToken);
		
		String result = HttpRequest.httpGetRequest(requestUrl);
		
		logInfoResult(method, result);
		
		GetJsApiTicketResponse response = JsonUtils.parseObject(result, GetJsApiTicketResponse.class);
		// 若errcode为0（请求成功）时，返回response
		if(CheckUtils.isNullOrEmpty(response.getErrcode()) || response.getErrcode().equals(Constant.Str.STR_ZERO)){
			return response;
		}
		return null;
	}
	
}

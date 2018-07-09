package com.xxx.wechat.common.wechat.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.response.GetApiTicketResponse;

public class ApiTicketApi extends BaseApi {

	/**
	 * 获取apiTicket
	 * @param accessToken
	 * @return
	 * @throws HttpResponseNullException
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public GetApiTicketResponse getApiTicket(String accessToken) throws ClientProtocolException, IOException, HttpResponseNullException{
		String method = "getApiTicket";
		String requestUrl = String.format(Constant.WechatUrl.GET_API_TICKET, accessToken);
		
		String result = HttpRequest.httpGetRequest(requestUrl);
		
		logInfoResult(method, result);
		
		GetApiTicketResponse response = JsonUtils.parseObject(result, GetApiTicketResponse.class);
		// 若errcode为0（请求成功）时，返回response
		if(CheckUtils.isNullOrEmpty(response.getErrcode()) || response.getErrcode().equals(Constant.Str.STR_ZERO)){
			return response;
		}
		return null;
	}
	
}

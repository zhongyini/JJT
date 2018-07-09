package com.xxx.wechat.common.wechat.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.entity.Menu;
import com.xxx.wechat.common.wechat.api.enums.ResultType;
import com.xxx.wechat.common.wechat.api.response.BaseResponse;

public class MenuApi extends BaseApi{

	public MenuApi(String accessToken) {
		super(accessToken);
	}
	
	public ResultType createMenu(Menu menu) throws ClientProtocolException, IOException, HttpResponseNullException {
		String method = "createMenu";
		
		CheckUtils.requireNonNull(menu, "menu is null");
		
		String requestUrl = "";
		if(CheckUtils.isNull(menu.getMatchrule())){
			requestUrl = String.format(Constant.WechatUrl.CREATE_MENU_POST, accessToken);
		}else{
			requestUrl = String.format(Constant.WechatUrl.CREATE_MATCHRULE_MENU_POST, accessToken);
		}
		logInfoParam(method, menu.toJsonString());
		String result = HttpRequest.httpPostRequest(requestUrl, menu.toJsonString());
		
		logInfoParam(method, result);
		BaseResponse baseResponse = JsonUtils.parseObject(result, BaseResponse.class);
		return ResultType.get(baseResponse.getErrcode());
	}

}

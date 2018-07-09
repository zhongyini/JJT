package com.xxx.wechat.common.wechat.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.response.CardCordResponse;
import com.xxx.wechat.common.wechat.api.response.CardResponse;

/**
 * 卡券相关API
 *
 */
public class CardApi extends BaseApi {

	public CardApi(String accessToken) {
		super(accessToken);
	}
	
	public CardResponse getCardList(String openid, String cardId) throws ClientProtocolException, IOException, HttpResponseNullException {
		String method = "getCardList";
		String requestUrl = String.format(Constant.WechatUrl.CARD_USER_GETCARDLIST, accessToken);
		Map<String, String> param = new HashMap<String, String>();
		param.put("openid", openid);
		param.put("card_id", cardId);
		String result = HttpRequest.httpPostRequest(requestUrl, JsonUtils.toJson(param));
		logger.info(result);
		CardResponse response = new CardResponse();
		List<CardCordResponse> cardAndCodeList = new ArrayList<CardCordResponse>();
		response.setHasShareCard(JsonUtils.getStringFromJSONObject(result, "has_share_card") == "true"?true:false);
		JSONObject jsonObj = JSONObject.parseObject(result);
		JSONArray array = jsonObj.getJSONArray("card_list");
		int length = array.size();
		for (int index = 0; index < length; index++) {
			CardCordResponse cardAndCode = new CardCordResponse();
			cardAndCode.setCardId(JsonUtils.getStringFromJSONObject(array.getString(index), "card_id").toString());
			cardAndCode.setCode(JsonUtils.getStringFromJSONObject(array.getString(index), "code").toString());
			cardAndCodeList.add(cardAndCode);
		}
		response.setCardList(cardAndCodeList);
		logInfoResult(method, result);
		// 若errcode为空（请求成功）时，返回response
		if(CheckUtils.isNullOrEmpty(response.getErrcode()) || isSuccess(response.getErrcode())){
			return response;
		}
		return null;
	}
	
	public String decryptCode(String encryptCode) throws ClientProtocolException, IOException, HttpResponseNullException {
		String method = "getCardList";
		String requestUrl = String.format(Constant.WechatUrl.CARD_CODE_DECRYPT, accessToken);
		Map<String, String> param = new HashMap<String, String>();
		param.put("encrypt_code", encryptCode);
		String result = HttpRequest.httpPostRequest(requestUrl, JsonUtils.toJson(param));
		String errcode = JsonUtils.getStringFromJSONObject(result, "errcode").toString();
		String code = JsonUtils.getStringFromJSONObject(result, "code").toString();
		logInfoResult(method, result);
		if(CheckUtils.isNullOrEmpty(errcode) || isSuccess(errcode)){
			return code;
		}
		return Constant.EMPTY;
		
	}
}

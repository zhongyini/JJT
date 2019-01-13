package com.xxx.wechat.common.lottery.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.lottery.api.response.LotteryDltHistoryResponse;
import com.xxx.wechat.common.lottery.api.response.LotteryDltHistoryTermResponse;
import com.xxx.wechat.common.utils.CheckUtils;

public class LotteryDltHistoryApi {

	public LotteryDltHistoryTermResponse getLotteryDltTerm(String url) throws Exception {
		LotteryDltHistoryTermResponse response = null;
		String result = HttpRequest.httpGetRequest(url);
		JSONArray array = JSONObject.parseArray(result);
		if (!CheckUtils.isNull(array) && array.size() > 0) {
			response = JSONObject.parseObject(array.getString(0), LotteryDltHistoryTermResponse.class);
		}
		return response;
	}
	
	public LotteryDltHistoryResponse getLotteryDltHistory(String url) throws Exception {
		LotteryDltHistoryResponse response = null;
		String result = HttpRequest.httpGetRequest(url);
		JSONArray array = JSONObject.parseArray(result);
		if (!CheckUtils.isNull(array) && array.size() > 0) {
			response = JSONObject.parseObject(array.getString(0), LotteryDltHistoryResponse.class);
		}
		return response;
	}
	
}

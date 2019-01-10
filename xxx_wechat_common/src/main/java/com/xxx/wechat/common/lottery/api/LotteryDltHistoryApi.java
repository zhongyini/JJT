package com.xxx.wechat.common.lottery.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.lottery.api.response.LotteryDltHistoryResponse;
import com.xxx.wechat.common.utils.CheckUtils;

public class LotteryDltHistoryApi {

	String lotteryDltUrl = "http://www.lottery.gov.cn/api/lottery_kj_detail_new.jspx?_ltype=4";
	
	public LotteryDltHistoryResponse getLotteryDltHistory() throws Exception {
		LotteryDltHistoryResponse response = null;
		String result = HttpRequest.httpGetRequest(lotteryDltUrl);
		JSONArray array = JSONObject.parseArray(result);
		if (!CheckUtils.isNull(array) && array.size() > 0) {
			response = JSONObject.parseObject(array.getString(0), LotteryDltHistoryResponse.class);
		}
		return response;
	}
	
}

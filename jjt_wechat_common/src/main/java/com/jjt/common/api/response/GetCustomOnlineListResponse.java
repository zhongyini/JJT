package com.jjt.common.api.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.jjt.common.api.entity.CustomOnline;

/**
 */
public class GetCustomOnlineListResponse extends BaseResponse {

    @JSONField(name = "kf_online_list")
    private List<CustomOnline> customOnlineList;

	public List<CustomOnline> getCustomOnlineList() {
		return customOnlineList;
	}

	public void setCustomOnlineList(List<CustomOnline> customOnlineList) {
		this.customOnlineList = customOnlineList;
	}

}

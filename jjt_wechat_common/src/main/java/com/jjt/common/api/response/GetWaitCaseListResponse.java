package com.jjt.common.api.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.jjt.common.api.entity.WaitCase;

/**
 * 获取未接入的客服会话
 */
public class GetWaitCaseListResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "count")
    private Integer count;
	
    @JSONField(name = "waitcaselist")
    private List<WaitCase> waitcaselist;

	public List<WaitCase> getWaitcaselist() {
		return waitcaselist;
	}

	public void setWaitcaselist(List<WaitCase> waitcaselist) {
		this.waitcaselist = waitcaselist;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}

package com.xxx.wechat.common.wechat.api.entity;

import com.xxx.wechat.common.utils.JsonUtils;

public abstract class BaseModel implements Model{

	private static final long serialVersionUID = 2683212428439902580L;

	@Override
	public String toJsonString() {
		
		return JsonUtils.toJsonString(this);
	}
	
	

	
	
}

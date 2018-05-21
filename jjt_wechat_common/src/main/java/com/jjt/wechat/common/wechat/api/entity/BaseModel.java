package com.jjt.wechat.common.wechat.api.entity;

import com.jjt.wechat.common.utils.JsonUtils;

public abstract class BaseModel implements Model{

	private static final long serialVersionUID = 2683212428439902580L;

	@Override
	public String toJsonString() {
		
		return JsonUtils.toJsonString(this);
	}
	
	

	
	
}

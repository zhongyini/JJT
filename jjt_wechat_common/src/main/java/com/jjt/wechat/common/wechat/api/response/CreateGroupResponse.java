package com.jjt.wechat.common.wechat.api.response;

import com.jjt.wechat.common.wechat.api.entity.Group;

public class CreateGroupResponse extends BaseResponse {

	private static final long serialVersionUID = -8292563529259522640L;
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}

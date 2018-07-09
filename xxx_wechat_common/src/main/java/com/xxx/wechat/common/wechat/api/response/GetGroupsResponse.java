package com.xxx.wechat.common.wechat.api.response;

import java.util.List;

import com.xxx.wechat.common.wechat.api.entity.Group;

/**
 * 新建实体类Group，将id，name，count属性移动到Group实体中。本实体采用List封装Groups信息
 *
 */
public class GetGroupsResponse extends BaseResponse {

	private static final long serialVersionUID = -464549783977334516L;
	
	private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}

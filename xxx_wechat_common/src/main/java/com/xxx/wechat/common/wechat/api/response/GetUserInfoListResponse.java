package com.xxx.wechat.common.wechat.api.response;

import java.util.List;

public class GetUserInfoListResponse extends  BaseResponse{
	
	private static final long serialVersionUID = -2556654743037782786L;
	
	private List<GetUserInfoResponse> user_info_list;

    public List<GetUserInfoResponse> getUser_info_list() {
        return user_info_list;
    }

    public void setUser_info_list(List<GetUserInfoResponse> user_info_list) {
        this.user_info_list = user_info_list;
    }
}

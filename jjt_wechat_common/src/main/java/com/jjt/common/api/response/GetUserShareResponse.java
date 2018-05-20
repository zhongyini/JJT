package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UserShare;

/**
 */
public class GetUserShareResponse extends BaseResponse {

    private List<UserShare> list;

    public List<UserShare> getList() {
        return list;
    }

    public void setList(List<UserShare> list) {
        this.list = list;
    }
}

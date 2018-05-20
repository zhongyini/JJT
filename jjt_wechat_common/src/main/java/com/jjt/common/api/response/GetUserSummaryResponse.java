package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UserSummary;

/**
 */
public class GetUserSummaryResponse extends BaseResponse {

    private List<UserSummary> list;

    public List<UserSummary> getList() {
        return list;
    }

    public void setList(List<UserSummary> list) {
        this.list = list;
    }
}

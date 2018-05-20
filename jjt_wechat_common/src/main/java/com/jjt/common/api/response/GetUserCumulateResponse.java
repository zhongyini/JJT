package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UserCumulate;

/**
 */
public class GetUserCumulateResponse extends BaseResponse {

    private List<UserCumulate> list;

    public List<UserCumulate> getList() {
        return list;
    }

    public void setList(List<UserCumulate> list) {
        this.list = list;
    }
}

package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UserShareHour;

/**
 */
public class GetUserShareHourResponse extends BaseResponse {

    private List<UserShareHour> list;

    public List<UserShareHour> getList() {
        return list;
    }

    public void setList(List<UserShareHour> list) {
        this.list = list;
    }
}

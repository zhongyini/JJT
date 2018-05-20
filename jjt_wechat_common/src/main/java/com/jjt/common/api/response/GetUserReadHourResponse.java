package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UserReadHour;

/**
 */
public class GetUserReadHourResponse extends BaseResponse {

    private List<UserReadHour> list;

    public List<UserReadHour> getList() {
        return list;
    }

    public void setList(List<UserReadHour> list) {
        this.list = list;
    }
}

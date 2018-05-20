package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UserRead;

/**
 */
public class GetUserReadResponse extends BaseResponse {

    private List<UserRead> list;

    public List<UserRead> getList() {
        return list;
    }

    public void setList(List<UserRead> list) {
        this.list = list;
    }
}

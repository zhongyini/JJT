package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UpstreamMsgWeek;

/**
 */
public class GetUpstreamMsgWeekResponse extends BaseResponse {

    private List<UpstreamMsgWeek> list;

    public List<UpstreamMsgWeek> getList() {
        return list;
    }

    public void setList(List<UpstreamMsgWeek> list) {
        this.list = list;
    }
}

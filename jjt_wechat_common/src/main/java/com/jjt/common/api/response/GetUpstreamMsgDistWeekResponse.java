package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UpstreamMsgDistWeek;

/**
 */
public class GetUpstreamMsgDistWeekResponse extends BaseResponse {

    private List<UpstreamMsgDistWeek> list;

    public List<UpstreamMsgDistWeek> getList() {
        return list;
    }

    public void setList(List<UpstreamMsgDistWeek> list) {
        this.list = list;
    }
}

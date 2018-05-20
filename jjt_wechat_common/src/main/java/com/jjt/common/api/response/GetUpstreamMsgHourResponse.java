package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UpstreamMsgHour;

/**
 */
public class GetUpstreamMsgHourResponse extends BaseResponse {

    private List<UpstreamMsgHour> list;

    public List<UpstreamMsgHour> getList() {
        return list;
    }

    public void setList(List<UpstreamMsgHour> list) {
        this.list = list;
    }
}

package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UpstreamMsgMonth;

/**
 */
public class GetUpstreamMsgMonthResponse extends BaseResponse {

    private List<UpstreamMsgMonth> list;

    public List<UpstreamMsgMonth> getList() {
        return list;
    }

    public void setList(List<UpstreamMsgMonth> list) {
        this.list = list;
    }
}

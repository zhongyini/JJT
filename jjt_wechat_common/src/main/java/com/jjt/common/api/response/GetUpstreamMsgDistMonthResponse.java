package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UpstreamMsgDistMonth;

/**
 */
public class GetUpstreamMsgDistMonthResponse extends BaseResponse {

    private List<UpstreamMsgDistMonth> list;

    public List<UpstreamMsgDistMonth> getList() {
        return list;
    }

    public void setList(List<UpstreamMsgDistMonth> list) {
        this.list = list;
    }
}

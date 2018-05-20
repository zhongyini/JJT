package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.UpstreamMsg;

/**
 */
public class GetUpstreamMsgResponse extends BaseResponse {

    private List<UpstreamMsg> list;

    public List<UpstreamMsg> getList() {
        return list;
    }

    public void setList(List<UpstreamMsg> list) {
        this.list = list;
    }
}

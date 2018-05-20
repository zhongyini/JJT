package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.InterfaceSummary;

/**
 */
public class GetInterfaceSummaryResponse extends BaseResponse {

    private List<InterfaceSummary> list;

    public List<InterfaceSummary> getList() {
        return list;
    }

    public void setList(List<InterfaceSummary> list) {
        this.list = list;
    }
}

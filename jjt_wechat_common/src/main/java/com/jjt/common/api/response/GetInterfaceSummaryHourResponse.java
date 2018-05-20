package com.jjt.common.api.response;

import java.util.List;

import com.jjt.common.api.entity.InterfaceSummaryHour;

/**
 */
public class GetInterfaceSummaryHourResponse extends BaseResponse {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<InterfaceSummaryHour> list;

    public List<InterfaceSummaryHour> getList() {
        return list;
    }

    public void setList(List<InterfaceSummaryHour> list) {
        this.list = list;
    }
}

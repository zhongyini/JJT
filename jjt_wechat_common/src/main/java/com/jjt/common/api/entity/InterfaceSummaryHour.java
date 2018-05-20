package com.jjt.common.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class InterfaceSummaryHour extends InterfaceSummary {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4601766967139352820L;
	@JSONField(name = "ref_hour")
    private Integer refHour;

    public Integer getRefHour() {
        return refHour;
    }

    public void setRefHour(Integer refHour) {
        this.refHour = refHour;
    }
}

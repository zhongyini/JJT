package com.jjt.common.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class BaseDataCube extends BaseModel {

	private static final long serialVersionUID = 1L;
	@JSONField(name = "ref_date", format = "yyyy-MM-dd")
    private Date refDate;

    public Date getRefDate() {
        return refDate;
    }

    public void setRefDate(Date refDate) {
        this.refDate = refDate;
    }
}

package com.jjt.common.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 累计用户数据
 *
 */
public class UserCumulate extends BaseDataCube {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8285225947916677891L;
	@JSONField(name = "cumulate_user")
    private Integer cumulateUser;

    public Integer getCumulateUser() {
        return cumulateUser;
    }

    public void setCumulateUser(Integer cumulateUser) {
        this.cumulateUser = cumulateUser;
    }
}

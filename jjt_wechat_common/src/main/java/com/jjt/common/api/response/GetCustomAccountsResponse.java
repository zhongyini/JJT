package com.jjt.common.api.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.jjt.common.api.entity.CustomAccount;

/**
 */
public class GetCustomAccountsResponse extends BaseResponse {

    @JSONField(name = "kf_list")
    private List<CustomAccount> customAccountList;

    public List<CustomAccount> getCustomAccountList() {
        return customAccountList;
    }

    public void setCustomAccountList(List<CustomAccount> customAccountList) {
        this.customAccountList = customAccountList;
    }
}

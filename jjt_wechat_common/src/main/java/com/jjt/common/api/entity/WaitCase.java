package com.jjt.common.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 未接入对象
 *
 */
public class WaitCase extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "createtime")
    private Long createtime;

    @JSONField(name = "kf_account")
    private String kfAccount;

    @JSONField(name = "openid")
    private String openid;

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getKfAccount() {
		return kfAccount;
	}

	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}

package com.jjt.common.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 客服在线接待对象
 *
 */
public class CustomOnline extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "kf_account")
    private String accountName;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "kf_id")
    private String id;

    @JSONField(name = "auto_accept")
    private Integer autoAccept;
    
    @JSONField(name = "accepted_case")
    private Integer acceptedCase;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAutoAccept() {
		return autoAccept;
	}

	public void setAutoAccept(Integer autoAccept) {
		this.autoAccept = autoAccept;
	}

	public Integer getAcceptedCase() {
		return acceptedCase;
	}

	public void setAcceptedCase(Integer acceptedCase) {
		this.acceptedCase = acceptedCase;
	}

}

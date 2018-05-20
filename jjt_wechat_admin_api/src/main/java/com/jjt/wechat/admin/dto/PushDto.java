package com.jjt.wechat.admin.dto;

import java.io.Serializable;

public class PushDto implements Serializable {
	
	private static final long serialVersionUID = -190936127851777153L;
	private String type;
	private String materialId;
	private String groupId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}

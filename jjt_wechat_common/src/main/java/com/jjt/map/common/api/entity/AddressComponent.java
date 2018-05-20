package com.jjt.map.common.api.entity;

import java.io.Serializable;

public class AddressComponent implements Serializable{
	private static final long serialVersionUID = -8955270670265733177L;
	
	private String nation;
	private String province;
	private String city;
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}

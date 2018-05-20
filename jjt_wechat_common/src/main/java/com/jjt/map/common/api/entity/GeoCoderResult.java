package com.jjt.map.common.api.entity;

import java.io.Serializable;

public class GeoCoderResult implements Serializable{
	private static final long serialVersionUID = 2201415008104624464L;
	private AddressComponent address_component;

	public AddressComponent getAddress_component() {
		return address_component;
	}

	public void setAddress_component(AddressComponent address_component) {
		this.address_component = address_component;
	}
	
	
}

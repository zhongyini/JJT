package com.jjt.wechat.common.wechat.api.entity;

import java.io.Serializable;

/**
 * 所有微信的实体类都实现该接口
 * @author TungS
 *
 */
public interface Model extends Serializable{
	
	String toJsonString();
	
	
}

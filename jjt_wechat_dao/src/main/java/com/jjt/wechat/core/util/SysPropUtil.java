package com.jjt.wechat.core.util;

import org.springframework.util.StringUtils;

import com.jjt.wechat.core.config.SysConfig;


public class SysPropUtil {
	
	private static final String BOOL_TRUE_VALUE = "1";	

	public static String getProperty(String item) {
		return SysConfig.getInstance().getProperty(item);
	}
	
	public static String getString(String item) {
		return getProperty(item);
	}
	
	public static String getString(String item, boolean isInit) {
		return SysConfig.getInstance().getProperty(item, isInit);
	}
	
	public static Integer getInt(String item) {
		String prop = getProperty(item);
		if (StringUtils.isEmpty(prop))
			return null;
		
		try {
			return Integer.parseInt(prop);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Boolean getBoolean(String item) {
		return getBoolean(item, BOOL_TRUE_VALUE);
	}
	
	public static Boolean getBoolean(String item, String trueValue) {
		String prop = getProperty(item);
		if (StringUtils.isEmpty(prop))
			return Boolean.FALSE;
		
		try {
			return trueValue.equals(prop);
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
}

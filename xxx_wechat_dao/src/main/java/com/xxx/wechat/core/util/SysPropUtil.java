package com.xxx.wechat.core.util;

import org.springframework.util.StringUtils;

import com.xxx.wechat.core.config.SystemConfig;


public class SysPropUtil {
	
	private static final String BOOL_TRUE_VALUE = "1";	

	public static String getProperty(String item) {
		return SystemConfig.getInstance().getProperty(item);
	}
	
	public static String getString(String item) {
		return SystemConfig.getInstance().getProperty(item);
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

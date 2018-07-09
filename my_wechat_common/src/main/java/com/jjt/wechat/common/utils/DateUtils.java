package com.jjt.wechat.common.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 时间的工具类集合
 *
 */
public class DateUtils {
	
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	
	/**
	 * 获取当前的时间戳
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		return new Timestamp(new Date().getTime());
	}
}

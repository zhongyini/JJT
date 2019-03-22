package com.xxx.wechat.admin.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;

public class DateUtil {
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String YMDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd 00:00:00
	 */
	public static final String YMDHHMMSS1 = "yyyy-MM-dd 00:00:00";

	/**
	 * yyyy-MM-dd 23:59:59
	 */
	public static final String YMDHHMMSS2 = "yyyy-MM-dd 23:59:59";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String YMDHHMMSS3 = "yyyyMMddHHmmss";
	/**
	 * yyyyMMdd
	 */
	public static final String YYYYMMDD = "yyyyMMdd";

	/**
	 * yyyy-MM-dd
	 */
	public static final String YYYYMMDD1 = "yyyy-MM-dd";

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * 获取两个字符串之间的天数endStr应该大于或等于startStr 日期格式应为format
	 * 
	 * @param str1
	 * @param str2
	 * @param str3
	 * @return
	 */
	public static int daysBetweenStr(String startStr, String endStr, String format) {

		return getdaysBetweenDates(parse(startStr, format),
				parse(endStr, format));
	}
	
	/**
	 * 获取两个字符串之间的天数endStr应该大于或等于startStr 日期格式应为yyyyMMdd
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int daysBetweenStr(String startStr, String endStr) {

		return daysBetweenStr(startStr, endStr, YYYYMMDD);
	}

	/**
	 * 字符串转日期格式
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 日期转字符串格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String date2String(Date date, String format) {
		SimpleDateFormat dformat = new SimpleDateFormat(format);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return dformat.format(calendar.getTime());
	}
	
	public static String formatNow(String format) {
		return date2String(new Date(), format);
	}
	
	/**
	 * 将字符串转换为指定格式的日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parse(String dateStr, String format) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			sdf = null;
			return null;
		}
	}

	/**
	 * 获取两个日期之间的天数endDate应该大于或等于startDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getdaysBetweenDates(Date startDate, Date endDate) {

		if (CheckUtils.isNull(startDate) || CheckUtils.isNull(endDate)) {
			return -1;
		}
		long between_days = 0;
		try {
			between_days = (endDate.getTime() - startDate.getTime())
					/ (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {

			return -1;
		}

	}

	public static Timestamp getStartDate(Timestamp startTime) {
		long st = DateUtils.parse(
				DateFormatUtils.format(new Date(startTime.getTime()),
						YMDHHMMSS1), YMDHHMMSS).getTime();
		return new Timestamp(st);
	}

	public static Timestamp getEndDate(Timestamp endTime) {
		long st = DateUtils
				.parse(DateFormatUtils.format(new Date(endTime.getTime()),
						YMDHHMMSS2), YMDHHMMSS).getTime();
		return new Timestamp(st);
	}

	public static Date getStartDate(Date date) {
		return new Date(DateUtils.parse(
				DateFormatUtils.format(date, YMDHHMMSS1), YMDHHMMSS)
				.getTime());
	}

	public static Date getEndDate(Date date) {
		return new Date(DateUtils.parse(
				DateFormatUtils.format(date, YMDHHMMSS2), YMDHHMMSS)
				.getTime());
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (!CheckUtils.isNull(date)) {
			cal.setTime(date);
		}
		return cal.get(Calendar.MONTH) + 1;
	}
}

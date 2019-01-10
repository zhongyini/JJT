package com.xxx.wechat.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	 * 日期格式：yyyy-MM-dd
	 */
	public static final String yyyyMMdd = "yyyyMMdd";
	
	/**
	 * 获取当前的时间戳
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 获取时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getTimestamp(Long time) {
		Timestamp timestamp = new Timestamp(time);
		return timestamp;

	}
	
	/**
	 * 获取时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getTimestamp(Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;

	}
	
	/**
	 * 获取当前日期，时间格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getYMDHMS() {
		SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return format.format(new Date());

	}
	
	/**
	 * 获取日期，时间格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDHMS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHMMSS);
		return format.format(date);

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
	 * 比较日期
	 */
	public static boolean compareTimestamp(Timestamp a, Timestamp b) {
		return true;
	}
	
	public static String getStringDate(Timestamp ts){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		Date date = new Date(ts.getTime());
		return sdf.format(date);
	}
	
	/**
	 * 判断access_token最新获取时间是否超过一个半小时
	 * @param now
	 * @param createDate
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkTokenDate(Timestamp now, Timestamp createDate) throws ParseException {
		long longNow = dateToStamp(now.toString());
		long longCreateDate = dateToStamp(createDate.toString());
		longCreateDate = longCreateDate+60*6*15;
		if (longNow >= longCreateDate) {
			return false;
		}
		return true;
	}
	
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }
    
	/* 
     * 将时间转换为时间戳
     */    
    public static long dateToStamp(String s) throws ParseException{
    	long res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = ts/1000;
        return res;
    }
}

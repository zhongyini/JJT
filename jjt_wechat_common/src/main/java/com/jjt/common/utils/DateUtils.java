package com.jjt.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtils {
	public static final String YMDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String YMDZH_CN = "yyyy年MM月dd日";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMM = "yyyyMM";
	public static final String YMZH_CN = "yyyy年MM月";
	public static final String YYYYMM_REGULAR = "[1-9]{1}[0-9]{3}[0-9]{2}";
	public static final String CSV_YYYYMMDD = "yyyy/MM/dd";
	/**
	 * yyyy-MM-dd 23:59:59
	 */
	public static final String YMDHHMMSS2 = "yyyy-MM-dd 23:59:59";
	
	/**
	 * MM-dd
	 */
	public static final String MM_DD = "MM-dd";
	
	/**
	 * yyyy-MM-dd 00:00:00
	 */
	public static final String YMDHHMMSS1 = "yyyy-MM-dd 00:00:00";
	
	public static Timestamp getNowTimestamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	public static Date getNow() {
		return new Date();
	}

	//yyyyMMdd
	 public static long getNowLong() {
		 return Long.valueOf(getYMDAfterDays(0));
	 }
	
	public static String getNowFormat(String format) {
		SimpleDateFormat dformat = new SimpleDateFormat(format);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return dformat.format(calendar.getTime());
	}

	public static Date parse(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取N天后的日期，时间格式为yyyyMMdd
	 * 
	 * @param days
	 * @return
	 */
	public static String getYMDAfterDays(Integer days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
		return format.format(calendar.getTime());
	}

	public static Date getDateAfterDays(Integer days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 获取自今天之后的多少天日期
	 * @param _format
	 * @param days
	 * @return
	 */
	public static String getDateAfterDays(String _format,Integer days){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(_format);
		return format.format(calendar.getTime());
	}
	/**
	 * 获取指定日期之后的天数的时间
	 * 
	 * @param date
	 * @param _format
	 * @param days
	 * @return
	 */
	public static String getDateAfterDays(String date, String _format,
			Integer days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(parse(date, _format));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(_format);
		return format.format(calendar.getTime());
	}

	/**
	 * 获取N天前的日期，时间格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param num
	 * @return
	 */
	public static String getYMDHMSBeforeNum(Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -num);
		SimpleDateFormat format = new SimpleDateFormat(YMDHHMMSS);
		return format.format(calendar.getTime());
	}
	
	/**
	 * 获取N天前的日期，时间格式为yyyyMMdd
	 * @param num
	 * @return
	 */
	public static String getYYYYMMDDBeforeNum(Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -num);
		SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
		return format.format(calendar.getTime());
	}
	
	/**
	 * 获取N天前的日期，时间格式为
	 * 
	 * @param _format
	 * @param num
	 * @return
	 */
	public static String getDateBeforeNum(String _format, Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -num);
		SimpleDateFormat format = new SimpleDateFormat(_format);
		return format.format(calendar.getTime());
	}

	/**
	 * 获取指定日期N天前的日期，时间格式为
	 * 
	 * @param date
	 * @param _format
	 * @param num
	 * @return
	 */
	public static String getDateBeforeNum(String date, String _format,
			Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(parse(date, _format));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -num);
		SimpleDateFormat format = new SimpleDateFormat(_format);
		return format.format(calendar.getTime());
	}
	
	
	/*public static void main(String[] args) {
		String date = getDateBeforeNum("20171123","yyyyMMdd", 1);
		System.out.println(date);
	}*/
	
	
	/**
	 * 获取指定日期N天前的日期，时间格式为
	 * 
	 * @param date
	 * @param _format
	 * @param num
	 * @return
	 */
	public static String getDateBeforeNum(Date date, String _format,
			Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -num);
		SimpleDateFormat format = new SimpleDateFormat(_format);
		return format.format(calendar.getTime());
	}
	/**
	 * 获取N天前的日期，日期格式为long类型
	 * 
	 * @param num
	 * @return
	 */
	public static long getLongTime(Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -num);
		return calendar.getTimeInMillis();
	}

	/**
	 * 格式化日期输出，输出格式yyyy年MM月dd日
	 * 
	 * @throws ParseException
	 */
	public static String format(Date time, String _format) {
		SimpleDateFormat dest = new SimpleDateFormat(_format);
		return dest.format(time);

	}

	/**
	 * 格式化日期输出，输出格式yyyy年MM月dd日
	 * 
	 * @throws ParseException
	 */
	public static String format(long time, String _format) {
		SimpleDateFormat dest = new SimpleDateFormat(_format);
		return dest.format(new Date(time));

	}

	/**
	 * 获取日期，时间格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDHMS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(YMDHHMMSS);
		return format.format(date);

	}

	/**
	 * 判断时间是否在时间段内
	 * 
	 * @param date
	 *            当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin
	 *            开始时间 00:00:00
	 * @param strDateEnd
	 *            结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(Date date, String strDateBegin,
			String strDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date); // 2016-12-16 11:53:54
		// 截取当前时间时分秒 转成整型
		int tempDate = Integer.parseInt(strDate.substring(11, 13)
				+ strDate.substring(14, 16) + strDate.substring(17, 19));
		// 截取开始时间时分秒 转成整型
		int tempDateBegin = Integer.parseInt(strDateBegin.substring(0, 2)
				+ strDateBegin.substring(3, 5) + strDateBegin.substring(6, 8));
		// 截取结束时间时分秒 转成整型
		int tempDateEnd = Integer.parseInt(strDateEnd.substring(0, 2)
				+ strDateEnd.substring(3, 5) + strDateEnd.substring(6, 8));

		if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 两个时间比较 返回天数差 日期格式yyyyMMdd
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		Calendar cal = Calendar.getInstance();
		long time1 = 0;
		long time2 = 0;
		try {
			cal.setTime(sdf.parse(date1));
			time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(date2));
			time2 = cal.getTimeInMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 格式化日期输出，输出格式yyyy年MM月dd日
	 * 
	 * @throws ParseException
	 */
	public static String formatZHCNDate(String time) throws ParseException {
		SimpleDateFormat source = new SimpleDateFormat(yyyyMMdd);
		SimpleDateFormat dest = new SimpleDateFormat(YMDZH_CN);
		return dest.format(source.parse(time));
	}

	public static String formatZHCNDate(String time, String _format)
			throws ParseException {
		SimpleDateFormat source = new SimpleDateFormat(yyyyMMdd);
		SimpleDateFormat dest = new SimpleDateFormat(_format);
		return dest.format(source.parse(time));
	}

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static boolean comparable(Timestamp startTime, Timestamp endTime) {
		if (CheckUtils.isNull(startTime) || CheckUtils.isNull(endTime)) {
			return true;
		}

		if (endTime.getTime() > startTime.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean comparable(Date startTime, Date endTime) {
		if (CheckUtils.isNull(startTime) || CheckUtils.isNull(endTime)) {
			return true;
		}

		if (endTime.getTime() >= startTime.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public static String dateFormat(String src_format, String datetime,
			String dest_format) throws ParseException {
		SimpleDateFormat srcFormat = new SimpleDateFormat(src_format);
		SimpleDateFormat destFormat = new SimpleDateFormat(dest_format);
		String result = "";
		result = destFormat.format(srcFormat.parse(datetime));
		return result;
	}

	public static boolean dateYYYYMMCheck(String date) {
		if (CheckUtils.isNullOrEmpty(date)) {
			return false;
		}
		if (date.length() != 6) {
			return false;
		}
		Pattern pattern = Pattern.compile(YYYYMM_REGULAR);
		Matcher matcher = pattern.matcher(date);
		return matcher.find();
	}

	/**
	 * 判断两个日期是否是同一周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeek(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setFirstDayOfWeek(Calendar.SUNDAY);// 西方周日为一周的第一天，咱得将周一设为一周第一天
		cal2.setFirstDayOfWeek(Calendar.SUNDAY);
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (subYear == 0)// subYear==0,说明是同一年
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) // subYear==1,说明cal比cal2大一年;java的一月用"0"标识，那么12月用"11"
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)// subYear==-1,说明cal比cal2小一年
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
	
	/**
	 * 如何时间是在周日的0点0分0秒到2点59分59秒时的判断
	 * @param date
	 * @return
	 */
	public static boolean isUnSignDate(String date){
		SimpleDateFormat format = new SimpleDateFormat(DateUtils.YMDHHMMSS);
		Date d1 = null;//已签到日期
		try {
			d1 = format.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long signingLontTime = d1.getTime();
		Calendar calPre = Calendar.getInstance();
		calPre.setTime(d1);
		calPre.setFirstDayOfWeek(Calendar.SUNDAY);
		//不是周日就返回false
		if(calPre.get(Calendar.DAY_OF_WEEK) != 1){
			return false;
		}
		//设置周日的开始时间
		calPre.set(Calendar.HOUR_OF_DAY, 0);
		calPre.set(Calendar.MINUTE, 0);
		calPre.set(Calendar.SECOND, 0);
		System.out.println(calPre.getTime());
		long preTimeLong = calPre.getTimeInMillis();
		
		//设置周日凌晨2点59分59秒
		Calendar calNext = Calendar.getInstance();
		calNext.setTime(calPre.getTime());
		calNext.setFirstDayOfWeek(Calendar.SUNDAY);
		calNext.set(Calendar.HOUR_OF_DAY, 2);
		calNext.set(Calendar.MINUTE, 59);
		calNext.set(Calendar.SECOND, 59);
		System.out.println(calNext.getTime());
		long nextTimeLong = calNext.getTimeInMillis();

		return signingLontTime <= nextTimeLong && signingLontTime >= preTimeLong;
	}
	
	/**
	 * 判断是否是同一周的，判断以及从
	 * 【周日03:00:00 点 ~下周六 23:59:59】 之间，只能签到一次
	 * @param date1
	 * @param date2
	 * 在同一周内@return true
	 * 不在同一周内@return false;
	 */
	public static boolean isSameWeekByTimestamp(String date1, String date2){
		SimpleDateFormat format = new SimpleDateFormat(DateUtils.YMDHHMMSS);
		Date d1 = null;//已签到日期
		Date d2 = null;//签到日期
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long signedLongTime = d1.getTime();
		long signingLontTime = d2.getTime();
		//获取已签到日期的时间段，从本周日03:00:00 点 ~下周日 02:59:59
		
		//获取已签到时间的上一个周日的早上3点整
		Calendar calPre = Calendar.getInstance();
		calPre.setTime(d1);
		calPre.setFirstDayOfWeek(Calendar.SUNDAY);
		while(calPre.get(Calendar.DAY_OF_WEEK) != 1){
			calPre.add(Calendar.DATE, -1);
		}
		calPre.set(Calendar.HOUR_OF_DAY, 3);
		calPre.set(Calendar.MINUTE, 0);
		calPre.set(Calendar.SECOND, 0);
		long preTimeLong = calPre.getTimeInMillis();
		
		//获取已签到时间的下一个周六的早上23:59:59点整
		Calendar calNext = Calendar.getInstance();
		calNext.setTime(calPre.getTime());
		calNext.setFirstDayOfWeek(Calendar.SUNDAY);
		do{
			calNext.add(Calendar.DATE, 1);
		}
		while(calNext.get(Calendar.DAY_OF_WEEK) != 7);
		
		calNext.set(Calendar.HOUR_OF_DAY, 23);
		calNext.set(Calendar.MINUTE, 59);
		calNext.set(Calendar.SECOND, 59);
		long nextTimeLong = calNext.getTimeInMillis();
		
		//考虑周日晚上凌晨0点到3点之间签到的情况
		//出现这种情况，往前推7天
		if(signedLongTime < preTimeLong){
			calPre.add(Calendar.DATE, -7);
			calNext.add(Calendar.DATE, -7);
			preTimeLong = calPre.getTimeInMillis();
			nextTimeLong = calNext.getTimeInMillis();
		}
		return signingLontTime <= nextTimeLong && signingLontTime >= preTimeLong;
	}

	/**
	 * 判断 参数 @param date 是否符合@param format的格式
	 * 
	 * @return
	 */
	public static boolean isDateFormat(String format, String date) {
		if (CheckUtils.isNullOrEmpty(format) || CheckUtils.isNullOrEmpty(date)) {
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			dateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getDaysToNow(String dateTime) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMdd);
		long time = 0L;
		Date date = dateFormat.parse(dateTime);
		Date now = dateFormat.parse(dateFormat.format(new Date()));
		time = now.getTime() - date.getTime();
		time = time / 1000 / 60 / 60 / 24;
		return (int) time;
	}
	
	//add a method of Timestamp convert String data start at 2017/08/08 by bob
	/**
	 * Timestamp convert String data
	 * @param ts
	 * @return String str
	 */
	public static String getStringTime(Timestamp ts){
		SimpleDateFormat sdf = new SimpleDateFormat(YMDHHMMSS);
		Date date = new Date(ts.getTime());
		return sdf.format(date);
	}
	
	public static String getStringTimeTwo(Timestamp ts){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);
		Date date = new Date(ts.getTime());
		return sdf.format(date);
	}
	public static String getStringDate(Timestamp ts){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		Date date = new Date(ts.getTime());
		return sdf.format(date);
	}
	//add a method of Timestamp convert String data end at 2017/08/08 by bob
	
	//add a method of Timestamp  start at 2017/08/09 by bob
	public static Timestamp getStartDate(Timestamp startTime) {
		long st = DateUtils.parse(
				DateFormatUtils.format(new Date(startTime.getTime()),
						YMDHHMMSS1), YMDHHMMSS).getTime();
		return new Timestamp(st);
	}
	
	/**
	 * 
	 * @param endTime
	 * @return
	 */
	public static Timestamp getEndDate(Timestamp endTime) {
		long st = DateUtils
				.parse(DateFormatUtils.format(new Date(endTime.getTime()),
						YMDHHMMSS2), YMDHHMMSS).getTime();
		return new Timestamp(st);
	}
	
	public static Timestamp getCreateDate(Timestamp createDate){
		String str = DateUtils.getStringTime(createDate);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		try {
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}
	
	public static java.sql.Date getSqlDate(int day){
		long millis = day * 1000L * 60 * 60 * 24;
		return new java.sql.Date(System.currentTimeMillis() + millis) ;
	}
	
	/**
	 * 获取下一个月的日期
	 * @return
	 */
	public static java.sql.Date getDateNextMonth(){
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
		return new java.sql.Date(now.getTimeInMillis());
	}
	
	
	//add a method of Timestamp  end at 2017/08/09 by bob
	/**
	 * 判断客服的休息时间
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isCustomerRest(String startDate, String endDate){
		int start = 0;
		int end = 0;
		int now = 0;
		try {
			start = Integer.parseInt(startDate);
		} catch (Exception e) {
		}
		
		try {
			end = Integer.parseInt(endDate);
		} catch (Exception e) {
		}
		
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		try {
			now = Integer.parseInt(sdf.format(nowDate));
		} catch (Exception e) {
		}
		return now <= end && now >= start;
	}
	
	public static String getNowCombatTime(){
		SimpleDateFormat sdf = new SimpleDateFormat(MM_DD);
		return sdf.format(new Date());
	}
	
	/**
	 * date-->String
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(Date currentTime) {
		SimpleDateFormat formatter = new SimpleDateFormat(YMDHHMMSS);
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/*public static void main(String[] args) {
		System.out.println(getDateBeforeNum(YYYY_MM_DD, 1));
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		Date date =null;
		try {
			date = sdf.parse(getDateBeforeNum(YYYY_MM_DD, 1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
	}*/
}

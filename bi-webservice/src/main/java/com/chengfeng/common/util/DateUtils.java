package com.chengfeng.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DateUtils.class);

	/**
	 * 获取增加几个月的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateBeforeMonth(Date date, int month) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.MONTH, +month);
		return dateTime.getTime();
	}

	/**
	 * @desc:获取减少m个月的时间
	 * @author Clyde
	 * @date:2014-3-4
	 * @param date
	 * @param m
	 * @return
	 */
	public static Date getDateAfterMonth(Date date, int m) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.MONTH, -m);
		return dateTime.getTime();
	}

	/**
	 * 获取增加几天的时间
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDateBeforeDay(Date date, int day) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.DATE, +day);
		return dateTime.getTime();
	}

	/**
	 * @desc:获取减少day天的时间
	 * @author Clyde
	 * @date:2014-3-4
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDateAfterDay(Date date, int day) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.DATE, -day);
		return dateTime.getTime();
	}

	/**
	 * 获取增加几小时的时间
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date getDateAfterHour(Date date, int hour) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.HOUR, +hour);
		return dateTime.getTime();
	}

	/**
	 * 获取增加几分钟的时间
	 */
	public static Date getDateAfterMinute(Date date, int minute) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.MINUTE, +minute);
		return dateTime.getTime();
	}

	/**
	 * 获取增加几秒的时间
	 */
	public static Date getDateAfterSeconds(Date date, int second) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.SECOND, +second);
		return dateTime.getTime();
	}

	/**
	 * 获取减少几秒的时间
	 */
	public static Date getDateBeforeSeconds(Date date, int second) {
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.SECOND, -second);
		return dateTime.getTime();
	}

	/**
	 * 获取上个月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getEndDayOfUpMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取上一个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfUpMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * @desc:计算相差多少天
	 * @author Clyde
	 * @date:2014-3-4
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysDiffer(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(date2);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;
	}

	/**
	 * @desc:计算相差多少天
	 * @author Clyde
	 * @date:2014-3-4
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getIntervalDays(Date date1, Date date2) {
		if (null == date1 || null == date2) {
			return -1;
		}
		long intervalMilli = date2.getTime() - date1.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	/**
	 * @desc:计算相差的long值
	 * @author Clyde
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getIntervalTimes(Date date1, Date date2) {
		if (null == date1 || null == date2) {
			return -1;
		}
		return date2.getTime() - date1.getTime();
	}

	/**
	 * 计算相差的hour值
	 * @time 2014-12-23上午11:04:12
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static double getIntervalHours(Date date1, Date date2){
		return getIntervalTimes(date1, date2)/(3600.0 * 1000);
	}
	
	/**
	 * 设置日期的时间
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date setDateTime(Date date, int hour, int minute, int second) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.set(Calendar.HOUR, hour);
		dateTime.set(Calendar.MINUTE, minute);
		dateTime.set(Calendar.SECOND, second);
		return dateTime.getTime();
	}

	/**
	 * 把分钟格式化为时间格式（HH:mm）
	 * @time 2014-12-19上午9:51:44
	 * @param minute
	 * @return
	 */
	public static String getTimeFromMinute(int minute){
		return getTimeFromMinute(minute, "HH:mm");
	}
	
	private static String getTimeFromMinute(int minute,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date=setDateTime(new Date(), 0, 0, 0);
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.add(Calendar.MINUTE, minute);
		return sdf.format(dateTime.getTime());
	}
	
	/**
	 * 根据分钟获取完整时间显示
	 * @param minute
	 * @return
	 */
	public static String getDetailTimeByDate(Integer minute){
		StringBuffer timeString=new StringBuffer();
		if (minute==null) {
			return "";
		}
		if (minute >= 60*24) {
			//大于一天
			int day=minute/(24*60);
			int nextMin=minute-day*(24*60);
			int hour=nextMin/60;
			int min=nextMin-hour*60;
			timeString.append(day+"天");
			if (hour!=0||min!=0) {
				timeString.append(hour+"小时");
			}
			if (min!=0){
				timeString.append(min+"分钟");
			}
		}else if(minute >= 60){
			//大于一小时
			int hour=minute/60;
			int min=minute-hour*60;
			timeString.append(hour+"小时");
			if (min!=0) {
				timeString.append(min+"分钟");
			}
		}else{
			//一小时内
			timeString.append(minute+"分钟");
		}
		return timeString.toString();
	}
	
	/**
	 * 格式化時間 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = "";
		if (date != null) {
			dateStr = format.format(date);
		}
		return dateStr;
	}

	/**
	 * @desc:格式化time
	 * @author Clyde
	 * @date:2014-5-13
	 * @param date
	 * @return
	 */
	public static String formatTimeToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String dateStr = "";
		if (date != null) {
			dateStr = format.format(date);
		}
		return dateStr;
	}

	/**
	 * @desc:格式化日期
	 * @author Clyde
	 * @date:2014-2-23
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		String customDate = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			customDate = sdf.format(date);
		}
		return customDate;
	}

	/**
	 * @desc:返回简化的时间
	 * @author Clyde
	 * @date:2014-3-16
	 * @param date
	 * @return
	 */
	public static String formatDateToSimple(Date date) {
		String customDate = "";
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		customDate = sdf.format(date);
		return customDate;
	}

	/**
	 * @desc:String转date
	 * @author Clyde
	 * @date:2014-3-16
	 * @param strDate
	 * @param format
	 * @return 若失败则返回当前时间
	 */
	public static Date parseToDate(String strDate, String format) {
		try {
			if (strDate == null || strDate.trim().length() == 0) {
				return new Date();
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(strDate);
		} catch (ParseException e) {
			logger.error("[ERROR：时间转换失败;]", e);
			return new Date();
		}
	}
	
	public static Date parseToDate(String strDate) {
		return parseToDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取date的日期和time的时间 组成的新的日期对象
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date getDateAddTime(Date date, Date time) {
		String d1 = formatDate(date, "yyyy/MM/dd");
		String d2 = formatDate(time, "HH:mm:ss");
		Date allDate = parseToDate(d1 + " " + d2, "yyyy/MM/dd HH:mm:ss");
		return allDate;
	}

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 判断两个时间差是否超过一个月
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isContainOneMonth(Date start, Date end,
			boolean isContainTime) {
		if (isContainTime) {
			return DateUtils.getDateAfterSeconds(start, -1).getTime() < DateUtils
					.getDateBeforeMonth(end, -1).getTime();
		} else {
			return start.getTime() < DateUtils.getDateBeforeMonth(end, -1)
					.getTime();
		}
	}
	
	/**
	 * 获取系统时间
	 * @param pattern
	 * @return
	 *
	 * 2015-8-4
	 */
	public static String getCurrentDate(String pattern) {
        Calendar calendar = Calendar. getInstance();
        Date date = calendar.getTime();
        return dateToString(date,pattern);
    }
	
	/**
	 * DATE转String
	 * @param date
	 * @param pattern
	 * @return
	 *
	 * 2015-8-4
	 */
	public static String dateToString(Date date,String pattern) {
        if(date == null) return null;
        String format = pattern;
        if ( (format == null) || (format != null && format.equals(""))) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}

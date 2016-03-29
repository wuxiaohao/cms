package org.wxh.basic.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public abstract class DateUtils {
	public static final String ISO_DATE_FORMAT = "yyyyMMdd";
	public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";
	public static final String ISO_TIME_FORMAT = "HHmmssSSSzzz";
	public static final String ISO_EXPANDED_TIME_FORMAT = "HH:mm:ss,SSSzzz";
	public static final String ISO_DATE_TIME_FORMAT = "yyyyMMdd'T'HHmmssSSSzzz";
	public static final String ISO_EXPANDED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss,SSSzzz";
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final long ONE_SECOND = 1000L;
	public static final long ONE_MINUTE = 60000L;
	public static final long ONE_HOUR = 3600000L;
	public static final long ONE_DAY = 86400000L;
	public static final long ONE_WEEK = 604800000L;
	public static final long ONE_YEAR = 31536000000L;

	/** 一个很久很久以前的时间(格林威治的起始时间. 1970-01-01 00:00:00) */
	public static final Date LONG_BEFORE_TIME = string2Date("1970-01-01 00:00:00", DEFAULT_DATE_TIME_PATTERN);
	/** 一个很久很久以后的时间(该框架可能被遗弃的时间. 2048-01-01 00:00:00) */
	public static final Date LONG_AFTER_TIME = string2Date("2048-01-01 00:00:00", DEFAULT_DATE_TIME_PATTERN);

	public static Date afterMilliseconds(long time, long milliseconds) {
		return new Date(time + milliseconds);
	}

	public static Date afterSeconds(long time, long seconds) {
		return new Date(time + 1000L * seconds);
	}

	public static Date afterSeconds(long seconds) {
		return new Date(System.currentTimeMillis() + 1000L * seconds);
	}

	public static Date afterMinutes(long time, long minutes) {
		return new Date(time + 60000L * minutes);
	}

	public static Date afterMinutes(long minutes) {
		return new Date(System.currentTimeMillis() + 60000L * minutes);
	}

	public static Date afterHours(long time, long hours) {
		return new Date(time + 3600000L * hours);
	}

	public static Date afterHours(long hours) {
		return new Date(System.currentTimeMillis() + 3600000L * hours);
	}

	public static Date afterDays(Date date, long days) {
		return new Date(date.getTime() + 86400000L * days);
	}

	public static Date afterDays(String date, long days) {
		Date dateTmp = parseDate(date);
		return afterDays(dateTmp, days);
	}

	public static Date afterDays(long time, long days) {
		return new Date(time + 86400000L * days);
	}

	public static Date afterDays(long days) {
		return new Date(System.currentTimeMillis() + 86400000L * days);
	}

	public static Date afterYears(long time, long years) {
		return new Date(time + 31536000000L * years);
	}

	public static Date afterYears(long years) {
		return new Date(System.currentTimeMillis() + 31536000000L * years);
	}

	public static Date parseDate(String text) {
		return parseDate(text, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
	}

	public static Date parseDate(String text, TimeZone zone) {
		return parseDate(text, zone, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
	}

	public static Date parseDate(String text, String[] dateFormatPatterns) {
		return parseDate(text, null, dateFormatPatterns);
	}

	public static Date parseDate(String text, TimeZone zone, String[] dateFormatPatterns) {
		if (dateFormatPatterns != null) {
			String[] arrayOfString;
			int j = (arrayOfString = dateFormatPatterns).length;
			for (int i = 0; i < j;) {
				String pattern = arrayOfString[i];
				try {
					SimpleDateFormat parser = new SimpleDateFormat(pattern);
					if (zone != null)
						parser.setTimeZone(zone);
					return parser.parse(text);
				} catch (Exception localException) {
					i++;
				}

			}

		}

		throw new RuntimeException("Unparseable date: " + text);
	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, TimeZone zone) {
		return formatDate(date, zone, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String dateFormatPattern) {
		return formatDate(date, null, dateFormatPattern);
	}

	public static String formatDate(Date date, TimeZone zone, String dateFormatPattern) {
		DateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat(dateFormatPattern);
		} catch (Exception ignore) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		if (zone != null)
			dateFormat.setTimeZone(zone);
		return formatDate(date, dateFormat);
	}

	public static String formatDate(Date date, DateFormat dateFormat) {
		if (date == null) {
			throw new IllegalArgumentException("date can not be null");
		}
		if (dateFormat == null) {
			throw new IllegalArgumentException("dateFormat can not be null");
		}
		return dateFormat.format(date);
	}

	public static Timestamp[] oneDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date != null ? date : new Date());
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);

		Timestamp[] array = new Timestamp[2];
		array[0] = new Timestamp(calendar.getTimeInMillis());

		calendar.add(5, 1);
		calendar.add(14, -1);
		array[1] = new Timestamp(calendar.getTimeInMillis());

		return array;
	}

	public static Timestamp[] oneDay(String date) {
		Date d = null;
		if ((date != null) && (date.length() == 8)) {
			d = parseDate(date, new String[] { "yyyyMMdd" });
		}
		return oneDay(d);
	}

	public static Date onlyDate(Date time) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(time);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	/*
	 * public static Date contact(Date date, String time) { if
	 * (StringUtils.isEmpty(time)) return date; time = time.replace(":", "");
	 * String hour = null; String minute = null; String second = null; int len =
	 * time.length(); if (len >= 2) { hour = time.substring(0, 2); } if (len >=
	 * 4) { minute = time.substring(2, 4); } if (len >= 6) { second =
	 * time.substring(4, 6); }
	 * 
	 * Calendar calendar = new GregorianCalendar(); calendar.setTime(date);
	 * calendar.set(11, TypeConverter.toInt(hour, 0)); calendar.set(12,
	 * TypeConverter.toInt(minute, 0)); calendar.set(13,
	 * TypeConverter.toInt(second, 0)); return calendar.getTime(); }
	 */

	public static String dateString(String d) {
		if ((StringUtils.isEmpty(d)) || (d.length() != 8))
			return d;
		char[] array = d.toCharArray();
		char[] buff = new char[10];
		System.arraycopy(array, 0, buff, 0, 4);
		System.arraycopy(array, 4, buff, 5, 2);
		System.arraycopy(array, 6, buff, 8, 2);
		buff[7] = 45;
		buff[4] = 45;
		return new String(buff);
	}

	public static int getDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(7);
	}

	/*
	 * public static int getDayOfWeek(String date) { if
	 * (StringUtils.isEmpty(date)) return -1; String input = date.replace("-",
	 * ""); int len = input.length(); if ((len != 4) && (len != 8)) return -1;
	 * int year = 0; int month = 0; int day = 0; if (len == 8) { year =
	 * TypeConverter.toInt(input.substring(0, 4), 0); input =
	 * input.substring(4); } month = TypeConverter.toInt(input.substring(0, 2),
	 * 1); day = TypeConverter.toInt(input.substring(2, 4), 0); try { Calendar
	 * calendar = new GregorianCalendar(); calendar.setTime(new Date()); if
	 * (year > 0) calendar.set(1, year); if (month > 0) calendar.set(2, month -
	 * 1); if (day > 0) calendar.set(5, day); int week = calendar.get(7); return
	 * week; } catch (Exception localException) { } return -1; }
	 */

	public static long getDaySeparate(Date date1, Date date2) {
		return (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 字符串转换成日期格式
	 * 
	 * @param string 待转换的日期字符串
	 * @param pattern 日期格式
	 * @return {@link Date} 转换后的日期
	 */
	public static Date string2Date(String string, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(string);
		} catch (ParseException e) {
			throw new IllegalArgumentException("无法将字符串[" + string + "]按格式[" + pattern + "]转换为日期", e);
		}
	}
}
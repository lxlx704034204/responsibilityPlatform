package com.orbit.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateTimeUtils {

	private static Log log = LogFactory.getLog(DateTimeUtils.class);

	public static String ISO_FORMAT_DATE = "yyyy-MM-dd";
	public static String ISO_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

	public static String DOUBLE_DIGITS = "00";

	/**
	 * FORMAT: yyyy-MM-dd
	 * */
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT_DATE);
	/**
	 * FORMAT: yyyy-MM-dd HH:mm:ss
	 * */
	public static SimpleDateFormat datetimeFormat  = new SimpleDateFormat(ISO_FORMAT_DATETIME);

	public static DecimalFormat doubleDigits = new DecimalFormat(DOUBLE_DIGITS);

	private static String[] halfyearCnNames = new String[]{"上半年","下半年"};
	private static String[] halfyearEnNames = new String[]{"1st half year","2nd half year"};
	private static String[] quarterCnNames = new String[]{"一季度","二季度","三季度","四季度"};
	private static String[] quarterEnNames = new String[]{"Q1","Q2","Q3","Q4"};
	private static String[] monthCnNames = new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
	private static String[] monthEnNames = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
	private static String[] weekCnNames = new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
	private static String[] weekEnNames = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

	/**
	 * 以ISO格式解析时间
	 * @throws ParseException
	 * */
	public static Date parseISODate(String datestr) {
		Date d = null;
		try{
			d = dateFormat.parse(datestr);
		} catch(ParseException e){
			log.error(e.getMessage(), e);
		}
		return d;
	}

	public static Date parseISODatetime(String datetimestr) throws ParseException{
		Date d = null;
		try{
			d = datetimeFormat.parse(datetimestr);
		} catch(ParseException e){
			log.error(e.getMessage(), e);
		}
		return d;
	}
	
	public static String formatToISODate(Date date){
		if(date == null){
			return null;
		}
		String s = null;
		try{
			s = dateFormat.format(date);
		} catch (Exception e){
			log.error(e.getMessage(), e);
		}
		return s;
	}
	
	public static String formatToISODatetime(Date date){
		if(date == null){
			return null;
		}
		String s = null;
		try{
			s = datetimeFormat.format(date);
		} catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return s;
	}

	public static int getYear(Calendar calendar){
		return calendar.get(Calendar.YEAR);
	}

	public static String getYearCnName(Calendar calendar){
		return getYear(calendar) + "年";
	}

	public static String getYearEnName(Calendar calendar){
		return getYear(calendar) + "Year";
	}

	public static int getMonth(Calendar calendar){
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static String getMonthFormatted(Calendar calendar){
		return doubleDigits.format(getMonth(calendar));
	}

	public static String getMonthCnName(Calendar calendar){
		return monthCnNames[getMonth(calendar) - 1];
	}

	public static String getMonthEnName(Calendar calendar){
		return monthEnNames[getMonth(calendar) - 1];
	}

	public static int getHalfYear(Calendar calendar){
		int month = getMonth(calendar);
		return month <= 6 ? 1 : 2;
	}

	public static String getHalfYearCnName(Calendar calendar){
		return halfyearCnNames[getHalfYear(calendar) - 1];
	}

	public static String getHalfYearEnName(Calendar calendar){
		return halfyearEnNames[getHalfYear(calendar) - 1];
	}

	public static int getQuarter(Calendar calendar){
		int quarter = -1;
		int month = getMonth(calendar);
		switch(month){
		case 1:
		case 2:
		case 3:
			quarter = 1;
			break;
		case 4:
		case 5:
		case 6:
			quarter = 2;
			break;
		case 7:
		case 8:
		case 9:
			quarter = 3;
			break;
		case 10:
		case 11:
		case 12:
			quarter = 4;
			break;
		}
		return quarter;
	}

	public static String getQuarterCnName(Calendar calendar){
		return quarterCnNames[getQuarter(calendar) - 1];
	}

	public static String getQuarterEnName(Calendar calendar){
		return quarterEnNames[getQuarter(calendar) - 1];
	}

	public static int getWeekOfYear(Calendar calendar){
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getWeekOfYearCnName(Calendar calendar){
		return getWeekOfYear(calendar) + "周";
	}

	public static String getWeekOfYearEnName(Calendar calendar){
		return getWeekOfYear(calendar) + "Week";
	}

	public static String getWeekOfYearFormatted(Calendar calendar){
		return doubleDigits.format(getWeekOfYear(calendar));
	}

	public static int getDayOfMonth(Calendar calendar){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static String getDayOfMonthCnName(Calendar calendar){
		return getDayOfMonth(calendar) + "日";
	}

	public static String getDayOfMonthEnName(Calendar calendar){
		return getDayOfMonth(calendar) + "Day";
	}

	public static String getDayOfMonthFormatted(Calendar calendar){
		return doubleDigits.format(getDayOfMonth(calendar));
	}

	public static int getDayOfWeek(Calendar calendar){
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		dayofweek = dayofweek == 0 ? 7 : dayofweek;
		return dayofweek;
	}

	public static String getDayOfWeekCnName(Calendar calendar){
		return weekCnNames[getDayOfWeek(calendar) - 1];
	}

	public static String getDayOfWeekEnName(Calendar calendar){
		return weekEnNames[getDayOfWeek(calendar) - 1];
	}
}

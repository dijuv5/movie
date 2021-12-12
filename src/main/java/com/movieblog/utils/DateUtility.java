package com.movieblog.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
	public static String datetoString(Date date,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
       	return df.format(date);
		
	}
	
	public static Calendar getDateToCalendar(Date date) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date getCalendarToDate(Calendar calendar) {
		return calendar.getTime();
	}
	public static Date getDateForString(String dateString, String format)
    {
    	SimpleDateFormat df = new SimpleDateFormat(format);
    	ParsePosition pp = new ParsePosition(0);
    	return df.parse(dateString,pp);
    }

}

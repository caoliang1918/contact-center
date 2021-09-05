package org.cti.cc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by caoliang on 2021/8/14
 */
public class DateTimeUtil {

    public final static String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYYMMDD = "yyyy-MM-dd";
    private static final String YYYYMM = "yyyyMM";


    public static Long addday(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime().getTime();
    }

    public static Long getNowDay() {
        Calendar calendar = getCalendar();
        Long nowDay = calendar.getTimeInMillis();
        return nowDay;
    }

    /**
     * 获取某一天的开始时间
     *
     * @param day
     * @return
     */
    public static Integer getDayStartTime(String day) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD);
        try {
            Date date = format.parse(day);
            return (int) (date.getTime() / 1000);
        } catch (ParseException e) {

        }
        return null;
    }

    /**
     * 获取前一天的0点0分0秒时间戳
     *
     * @return
     */
    public static Long getBeforDay() {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Long beforDay = calendar.getTimeInMillis();
        return beforDay;
    }

    public static Long getBeforeDay(int n) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        Long beforDay = calendar.getTimeInMillis();
        return beforDay;
    }

    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public static String getBeforeMonth() {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMM);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        String accDate = format.format(date);
        return accDate;
    }
}

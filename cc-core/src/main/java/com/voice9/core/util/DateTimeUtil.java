package com.voice9.core.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * Created by caoliang on 2021/8/14
 */
public class DateTimeUtil {

    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";


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
    public static Long getDayStartTime(String day) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date date = format.parse(day);
            return date.getTime();
        } catch (Exception e) {

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

    /**
     * 获取上个月的当前时间（时间往前推一个月）
     * @param n
     * @return
     */
    public static Long getBeforeMonth(int n) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.MONTH, -n);
        Long beforDay = calendar.getTimeInMillis();
        return beforDay;
    }

    /**
     * 获取当天0点
     *
     * @return
     */
    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    /**
     * 上个月开始时间
     *
     * @return
     * @throws Exception
     */
    public static Long getLastMonthStartTime() {
        Long currentTime = Instant.now().toEpochMilli();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, -1);
        //第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 上个月结束时间
     *
     * @return
     */
    public static Long getLastMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, -1);
        // 获取当前月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取当月凌晨
     *
     * @return
     */
    public static Calendar getMonthCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
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

    /**
     * 此方法用于按月分表查询月份，如果是当前月份的时间，则返回空的字符串
     *
     * @param time
     * @return
     */
    public static String getMonth(Long time) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMM);
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        date = calendar.getTime();
        String accDate = format.format(date);
        return "_" + accDate;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getNowMonth() {
        String time = DateTimeUtil.format(Instant.now().toEpochMilli(), DateTimeUtil.YYYYMM);
        return "_" + time.substring(0, 6);
    }

    /**
     * 获取今天开始时间和结束时间
     *
     * @return
     */
    public static Map getTodayTime() {
        Long startTime = getStartTime();
        Long endTime = getEndTime();
        Map<String, Long> map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return map;
    }


    /**
     * 获取今天开始时间
     */
    public static Long getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }

    public static boolean isToday(Long time) {
        Long todat = getStartTime();
        if (time > todat && time - todat < 86400000L) {
            return true;
        }
        return false;
    }

    /**
     * 获取今天结束时间
     */
    public static Long getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 获取当天时间 格式: 20201212
     *
     * @return
     */
    public static String format() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        return sdf.format(new Date());
    }

    public static String format(Long timestame) {
        String time = StringUtils.EMPTY;
        if (timestame == null || timestame == 0) {
            return time;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        time = sdf.format(new Date(timestame));
        return time;
    }

    public static String format(Long timestame, String format) {
        String time = StringUtils.EMPTY;
        if (timestame == null || timestame == 0) {
            return time;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        time = sdf.format(new Date(timestame));
        return time;
    }

    /**
     * callId获取拨打时间
     *
     * @param callId
     * @return
     */
    public static Long getCallTime(Long callId) {
        String binaryString = Long.toBinaryString(callId);
        int timeStart = binaryString.length() < 22 ? 0 : binaryString.length() - 22;
        String time = timeStart == 0 ? "0" : binaryString.substring(0, timeStart);
        return Long.parseLong(time, 2) + SnowflakeIdWorker.WORK_START;
    }
}

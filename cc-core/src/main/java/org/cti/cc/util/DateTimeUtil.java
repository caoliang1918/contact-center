package org.cti.cc.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by caoliang on 2021/8/14
 */
public class DateTimeUtil {

    public final static String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";


    public static Long addday(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime().getTime();
    }
}

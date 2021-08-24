package com.deyun.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2020/10/24.
 */
public class TimeUtil {
       public static String getTimeNow(String pattern,int num){
        final LocalDate localDate =LocalDate.now().plusDays(num);
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern(pattern);
       return localDate.format(dateTimeFormatter);
    }
}

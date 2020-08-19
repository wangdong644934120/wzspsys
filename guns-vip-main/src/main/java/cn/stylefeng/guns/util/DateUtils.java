package cn.stylefeng.guns.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    public static String[] month = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一", "十二"};

    static SimpleDateFormat formatter;

    public static String getCurrentDate() {
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    public static String getCurrentDate(String fmt) {
        formatter = new SimpleDateFormat(fmt);
        String dateString = formatter.format(new Date());
        return dateString;
    }


    public static String getMonth() {
        String currentDate = getCurrentDate();
        return getMonth(currentDate);
    }

    public static String getMonth(String formatDate) {
        if (TextUtils.isNotEmpty(formatDate) && formatDate.length() > 7) {
            return formatDate.substring(0, 7);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String dateString = formatter.format(new Date());
        return formatter.format(dateString);
    }

    public static String getYear() {
        String currentDate = getCurrentDate();
        return getYear(currentDate);
    }

    public static String getYear(String formatDate) {
        if (TextUtils.isNotEmpty(formatDate) && formatDate.length() > 4) {
            return formatDate.substring(0, 4);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String dateString = formatter.format(new Date());
        return formatter.format(dateString);
    }
}

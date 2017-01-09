package com.graypn.cmmon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间处理的工具类
 *
 * @author Grapn
 */
public class DateUtils {

    /**
     * 获取现在时间
     */
    public static String getDefaultStringDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
                .format(new Date());
    }

    /**
     * 时间戳变成时间
     */
    public static String timeStampToDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
                .format(new Date(timestamp));
    }
}

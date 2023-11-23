package org.emoration.lifeedge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author czh
 * @Description 日期工具类
 * @Date 2023/11/23
 */
public class DateUtil {

    public static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd";

    /**
     * @Description 获取当天是星期几
     * @Date 2023/11/23
     */
    public static String getDayOfWeek() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = simpleDateFormat.format(new Date());
        return dayOfWeek;

    }

    /**
     * @Description 以参数date为第一周, 计算当天是第几周(默认date为星期一)
     * @Date 2023/11/23
     */
    public static Long getWeekNumber(Date date) {

        Date now = new Date();
        return (now.getTime() - date.getTime()) / (24 * 3600 * 1000) / 7 + 1;

    }

    /**
     * @Description 计算date到现在的秒数
     * @Date 2023/11/23
     */
    public static Long getDurationSecondsUntilNow(Date date) {
        Date now = new Date();
        return (now.getTime() - date.getTime()) / 1000;
    }

    /**
     * @Description 计算date到现在的秒数
     * @Date 2023/11/23
     */
    public static Long getDurationSecondsUntilNow(String dateAccurateToSecondString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATA_TIME_FORMAT);
        sdf.setLenient(false);
        Date now = new Date();
        return (now.getTime() - sdf.parse(dateAccurateToSecondString).getTime()) / 1000;
    }

    /**
     * @Description 获取当前时间到秒
     * @Date 2023/11/23
     */
    public static String getDateTime() {

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATA_TIME_FORMAT); //设置时间格式
        Date now = new Date();//获取当前时间
        String date = sdf.format(now);
        return date;

    }

    /**
     * @Author czh
     * @Description 判断日期是否符合格式，目前用于搜索商品
     * @Date 2023/11/23
     */
    public static boolean isNotValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATA_FORMAT);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

}

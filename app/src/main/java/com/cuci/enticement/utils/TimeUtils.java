package com.cuci.enticement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    private TimeUtils() {
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static long getLongTime() {
        return new Date().getTime() / 1000L;
    }

    /**
     * 获取当前时间，格式 20180617
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间，格式 20180617
     *
     * @return
     */
    public static String getTodayWithSplit() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(new Date());
    }

    public static String getTimeWithSplit(String time) {
        long t = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        return sdf.format(new Date(t));
    }

    public static String getTimeWithSplit(long time) {
      //  long t = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.CHINA);
        return sdf.format(new Date(time));
    }

    /**
     * 获取PendingIntent的ID
     *
     * @return
     */
    public static int getPendingIntentId() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss", Locale.CHINA);
        return Integer.parseInt(sdf.format(new Date()));
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime    需要判断的时间 如10:00
     * @return 结果
     * @throws IllegalArgumentException
     */
    public static boolean isInTime(String sourceTime, String curTime) {

        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

        if (curTime == null || !curTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
        }

        String[] args = sourceTime.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        try {

            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();

            if (args[1].equals("00:00")) {
                args[1] = "24:00";
            }

            if (end < start) {
                return now < end || now >= start;
            } else {
                return now >= start && now < end;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

    }

}

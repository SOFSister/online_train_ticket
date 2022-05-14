package cn.feedsheep.online_train_ticket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : FeedSheep
 * @version : 1.0
 * @Project : online_train_ticket
 * @Package : cn.feedsheep.online_train_ticket.utils
 * @ClassName : TimeFormatUtils.java
 * @createTime : 2022/5/11 22:52
 * @Email : 874280179@qq.com
 * @Description :
 */
public class TimeFormatUtils {

    /**
     * 时间戳转为日期格式字符串
     * @param timestamp
     * @return
     */
    public static String timestampParseString(Long timestamp){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前系统时间戳
        String dateString = sdf.format(timestamp);
        return dateString;

    }


    /**
     * 日期格式字符串转为时间戳
     * @param dateString
     * @return
     */
    public static Long stringParseTimestamp(String dateString){
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timestamp = date.getTime();
        return timestamp;
    }

    /**
     * 日期类型转为字符串类型
     * @param date
     * @return
     */
    public String dateParseString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     * 日期格式字符串转为日期类
     * @param dateString
     * @return
     */
    public static Date stringParseDate(String dateString){
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 时间戳转换为日期类
     * @param timestamp
     * @return
     */
    public static Date timestampParseDate(Long timestamp) {
        Date date = new Date(timestamp);
        return date;
    }

    /**
     * 校验时间字符串格式是否为 yyyy-MM-dd HH:mm:ss
     * @param dateString
     * @return
     */
    public static Boolean isDateFormat(String dateString) {
        //用于指定 日期/时间 模式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boolean flag = true;
        try {
            //Java 8 新添API 用于解析日期和时间
            LocalDateTime.parse(dateString, dtf);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

}

package com.jingcai.jc_11x5.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangsen on 2016/7/20.
 */
public class DateUtil {

    /**
     * 获取当前时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime(){
        return formatterToDateAndTimeByHms(System.currentTimeMillis());
    }

    /**
     * yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate(){
        return formatterToDateByHms(System.currentTimeMillis());
    }

    /**
     * yyyy-MM-dd HH:mm
     * @return
     */
    public static String getCurrentDateAndTime(){
        return formatterToDateAndTime(System.currentTimeMillis());
    }

    public static String getAfterDate(int day){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        Date time=cal.getTime();
        return formatToStr(time.getTime());
    }

    public static String getMingtianDate(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date time=cal.getTime();
        return formatToStr(time.getTime());
    }

    public static String getZuotianDate(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        return formatToStr(time.getTime());
    }

    /**
     * 获取前天
     * @return
     */
    public static String getQiantianDate(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-2);
        Date time=cal.getTime();
        return formatToStr(time.getTime());
    }

    /**
     * 获取几天之前
     * @param days
     * @return
     */
    public static String getBeforDate(int days){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-days);
        Date time=cal.getTime();
        return formatToStr(time.getTime());
    }

    /**
     * 获取当前时间的毫秒数
     * @return
     */
    public static String getsNowMills() {
        //暂时为获取本地时间
        //若需要为网络时间 在此获取即可 以线程阻塞来实现  否则 时间则使用回传
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取当前时间的毫秒数
     * @return
     */
    public static long getNowMills() {
        //暂时为获取本地时间
        //若需要为网络时间 在此获取即可 以线程阻塞来实现  否则 时间则使用回传
        return System.currentTimeMillis();
    }

    public static String formatToStr(long time){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String result=format.format(new Date(time));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String formatToStr1(long time){
        try {
            SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
            String result=format.format(new Date(time));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static long parseDateToMillis(String date){
        try {
            SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
            Date d = parser.parse(date);
            return d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long parseTimeToMillis(String time){
        try {
            if (time == null ) return 0;
            SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = parser.parse(time);
            return d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long parseTimeToMillis1(String time){
        try {
            SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date d = parser.parse(time);
            return d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String formatterToDateAndTimeByHms(long time){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result=format.format(new Date(time));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatterToDateByHms(long time){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String result=format.format(new Date(time));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatterToDateHour(long time) {
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHH");
            if(time==0){
                return format.format(new Date(getNowMills()));
            }
            String result=format.format(new Date(time));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatterToDateAndTime(long time){
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String result=format.format(new Date(time));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getJsTime(String kssj, String qs){
        String ksrq = getCurrentDate() + " " + kssj;
        long ks = parseTimeToMillis(ksrq);
        String js = formatterToDateAndTimeByHms(ks + 10*60*1000*(Integer.parseInt(qs)-1));
        js = js.substring(js.lastIndexOf(" ")+1, js.length());
        return js;
    }

    public static boolean checkToday(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(time==null || "".equals(time)){
            return false;
        }
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set( Calendar.HOUR_OF_DAY, 0);
        today.set( Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        current.setTime(date);
        if(current.after(today)){
            return true;
        }
        return false;
    }



    public static JSONObject getCurrentAndAgoDate(int days){
        JSONObject jsonObject = new JSONObject();
        Calendar current = Calendar.getInstance();
        JSONObject currentJson = new JSONObject();
        currentJson.put("year", current.get(Calendar.YEAR));
        currentJson.put("month", current.get(Calendar.MONTH) + 1);
        currentJson.put("day", current.get(Calendar.DATE));
        jsonObject.put("current", currentJson);
        current.set(Calendar.DATE, current.get(Calendar.DATE) - days);
        JSONObject agoJson = new JSONObject();
        agoJson.put("year", current.get(Calendar.YEAR));
        agoJson.put("month", current.get(Calendar.MONTH) + 1);
        agoJson.put("day", current.get(Calendar.DATE));
        jsonObject.put("ago", agoJson);
        return jsonObject;
    }

    /**
     * 判断time是否在from，to之内
     *
     * @param time 指定日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean belongCalendar(Date time, int days) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar current = Calendar.getInstance();

        Calendar before = Calendar.getInstance();
        before.set(Calendar.DATE, current.get(Calendar.DATE) - days - 1);

        if (date.after(before) && date.before(current)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 毫秒转化
     * @param ms
     * @return
     */
    public static String formatTime(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        if(!TextUtils.isEmpty(strHour) && Integer.parseInt(strHour)!=0){
            return strHour + " 小时 " + strMinute + " 分钟 " + strSecond + " 秒";
        }
        return strMinute + " 分钟 " + strSecond + " 秒";
    }

    /**
     * 判断时间是否在时间段内
     * @param date yyyy-MM-dd HH:mm:ss
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd 结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}

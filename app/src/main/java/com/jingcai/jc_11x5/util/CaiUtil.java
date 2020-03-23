package com.jingcai.jc_11x5.util;

import android.text.TextUtils;

import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.entity.CaiZhong;
import com.jingcai.jc_11x5.entity.KaiJiangTime;
import com.jingcai.jc_11x5.entity.Lottery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class CaiUtil {

    public static List<CaiZhong> getCaiZhongList(){
        List<CaiZhong> lists = new ArrayList<>();
        lists.add(new CaiZhong("gd", "广东11选5"));//_11x5
        lists.add(new CaiZhong("sd", "山东11选5"));
        lists.add(new CaiZhong("jx", "江西11选5"));
        lists.add(new CaiZhong("sh", "上海11选5"));
        lists.add(new CaiZhong("ah", "安徽11选5"));
        return lists;
    }

    public static List<CaiZhong> getPlanCaiList(){
        List<CaiZhong> lists = new ArrayList<>();
        lists.add(new CaiZhong("1", "任选五单式"));//_11x5
        lists.add(new CaiZhong("2", "前三直选单式"));
        lists.add(new CaiZhong("3", "前三组选单式"));
        lists.add(new CaiZhong("4", "前二直选单式"));
        lists.add(new CaiZhong("5", "前二组选单式"));
        return lists;
    }

    public static String getCaiSf(String caiBm){
        if(caiBm == null || TextUtils.isEmpty(caiBm)){
            return "";
        }
        String caiMc = "";
        switch (caiBm){
            case "广东11选5":
                caiMc = "广东 ";
                break;
            case "山东11选5":
                caiMc = "山东 ";
                break;
            case "江西11选5":
                caiMc = "江西 ";
                break;
            case "上海11选5":
                caiMc = "上海";
                break;
            case "安徽11选5":
                caiMc = "安徽";
                break;
            default:
                break;
        }
        /*switch (caiBm){
            case "guangdong":
                caiMc = "广东 ";
                break;
            case "shandong":
                caiMc = "山东 ";
                break;
            case "jiangxi":
                caiMc = "江西 ";
                break;
            case "shanghai":
                caiMc = "上海";
                break;
            case "anhui":
                caiMc = "安徽";
                break;
            default:
                break;
        }*/
        return caiMc;
    }

    public static String getCaiMc(String caiBm){
        if(caiBm == null || TextUtils.isEmpty(caiBm)){
            return "";
        }
        String caiMc = "";
        switch (caiBm){
            case "guangdong":
                caiMc = "广东11选5";
                break;
            case "shandong":
                caiMc = "山东11选5";
                break;
            case "jiangxi":
                caiMc = "江西11选5";
                break;
            case "shanghai":
                caiMc = "上海11选5";
                break;
            case "anhui":
                caiMc = "安徽11选5";
                break;
            default:
                break;
        }
        return caiMc;
    }

    public static String getCaiBm(String caiMc){
        if(caiMc == null || TextUtils.isEmpty(caiMc)){
            return "";
        }
        String caiBm = "";
        switch (caiMc){
            case "广东11选5":
                caiBm = "gd";
                break;
            case "山东11选5":
                caiBm = "sd";
                break;
            case "江西11选5":
                caiBm = "jx";
                break;
            case "上海11选5":
                caiBm = "sh";
                break;
            case "安徽11选5":
                caiBm = "ah";
                break;
            case "gd":
                caiBm = "gd";
                break;
            case "sd":
                caiBm = "sd";
                break;
            case "jx":
                caiBm = "jx";
                break;
            case "sh":
                caiBm = "sh";
                break;
            case "ah":
                caiBm = "ah";
                break;
            default:
                break;
        }
        return caiBm;
    }

    public static String getCaiMcShort(String CaiBm){
        if(CaiBm == null || TextUtils.isEmpty(CaiBm)){
            return "";
        }
        String caiMc = "";
        switch (CaiBm){
            case "gd":
                caiMc = "广东";
                break;
            case "sd":
                caiMc = "山东";
                break;
            case "jx":
                caiMc = "江西";
                break;
            case "sh":
                caiMc = "上海";
                break;
            case "ah":
                caiMc = "安徽";
                break;
            default:
                break;
        }
        return caiMc;
    }

    public static String getCaiMcForShort(String CaiBm){
        if(CaiBm == null || TextUtils.isEmpty(CaiBm)){
            return "";
        }
        String caiMc = "";
        switch (CaiBm){
            case "广东11选5":
                caiMc = "广东";
                break;
            case "山东11选5":
                caiMc = "山东";
                break;
            case "江西11选5":
                caiMc = "江西";
                break;
            case "上海11选5":
                caiMc = "上海";
                break;
            case "安徽11选5":
                caiMc = "安徽";
                break;
            default:
                break;
        }
        return caiMc;
    }

    public static int getCaiCount(String caiBm){
        Lottery lottery = App.getInstance().getLottery();
        if(lottery != null){
            return Integer.parseInt(lottery.getCount());
        }
        int count = 0;
        switch (caiBm){
            case "gd":
                count = Constants.GUANGDONG_COUNT;
                break;
            case "sd":
                count = Constants.SHANDONG_COUNT;
                break;
            case "jx":
                count = Constants.JIANGXI_COUNT;
                break;
            default:
                break;
        }
        return count;
    }

    public static int getCurrentPeriod(){
        Lottery lottery = App.getInstance().getLottery();
        if(lottery == null){
            return 0;
        }
        long ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate()+" "+ lottery.getKjsj());//第一期开始时间
        long jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate()+" "+ lottery.getJssj());//最后一期开始时间
        long ctime = DateUtil.getNowMills();//当前时间
        if(ctime > jsTime){
            return Integer.parseInt(lottery.getCount());
        }
        if(ctime < ksTime){
            return 0;
        }
        int qs = (int)(ctime - ksTime) / (20*60*1000) + 1;
        return qs;
    }

    public static String getNextPeriod(){
        Lottery lottery = App.getInstance().getLottery();
        if(lottery == null){
            return "";
        }
        long ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate()+" "+ lottery.getKjsj());//第一期开始时间
        long jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate()+" "+ lottery.getJssj());//最后一期开始时间
        long ctime = DateUtil.getNowMills();//当前时间
        if(ctime > jsTime){
            String riqi = DateUtil.getMingtianDate().replaceAll("-", "");
            String nextQ = riqi.substring(2);
            return nextQ + "01";
        }
        /*if(ctime < ksTime){
            String riqi = DateUtil.getCurrentDate().replaceAll("-", "");
            String currentQ = riqi.substring(2);
            return currentQ + "01";
        }*/
        int qs = Integer.parseInt(lottery.getCount()) - (int)((jsTime - ctime) / (20*60*1000));
        if(qs == Integer.parseInt(lottery.getCount())){
            String riqi = DateUtil.getMingtianDate().replaceAll("-", "");
            String nextQ = riqi.substring(2);
            return nextQ + "01";
        }
        String riqi = DateUtil.getCurrentDate().replaceAll("-", "");
        String nextQ = riqi.substring(2);
        StringBuilder  nextorder = new StringBuilder();
        if(qs<10){
            nextorder.append(nextQ).append("0").append(qs+1);
        }else{
            nextorder.append(nextQ).append(qs);
        }
        return nextorder.toString();
    }

    public static long getCurrentDaojishi(){
        Lottery lottery = App.getInstance().getLottery();
        long ctime = DateUtil.getNowMills();//当前时间
        long ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
        long jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间
        if (ctime > jsTime) {
            return -1;
        }
        if (ctime < ksTime) {
            long t = ksTime - ctime;
            if(t > 20*60*1000){
                return -1;
            }
            return ksTime - ctime;
        } else {
            long kjsj = (ctime - ksTime) % (20 * 60 * 1000);
            return  20 * 60 * 1000 - kjsj;
        }
    }

    public static List<String> getRen5haoma() {
        List<String> lists = new ArrayList<>();
        lists.add("01 02 03 04 05");
        lists.add("01 02 03 04 06");
        lists.add("01 02 03 04 07");
        lists.add("01 02 03 04 08");
        lists.add("01 02 03 04 09");
        lists.add("01 02 03 04 10");
        lists.add("01 02 03 04 11");
        lists.add("01 02 03 05 06");
        lists.add("01 02 03 05 07");
        lists.add("01 02 03 05 08");
        lists.add("01 02 03 05 09");
        lists.add("01 02 03 05 10");
        lists.add("01 02 03 05 11");
        lists.add("01 02 03 06 07");
        lists.add("01 02 03 06 08");
        lists.add("01 02 03 06 09");
        lists.add("01 02 03 06 10");
        lists.add("01 02 03 06 11");
        lists.add("01 02 03 07 08");
        lists.add("01 02 03 07 09");
        lists.add("01 02 03 07 10");
        lists.add("01 02 03 07 11");
        lists.add("01 02 03 08 09");
        lists.add("01 02 03 08 10");
        lists.add("01 02 03 08 11");
        lists.add("01 02 03 09 10");
        lists.add("01 02 03 09 11");
        lists.add("01 02 03 10 11");
        lists.add("01 02 04 05 06");
        lists.add("01 02 04 05 07");
        lists.add("01 02 04 05 08");
        lists.add("01 02 04 05 09");
        lists.add("01 02 04 05 10");
        lists.add("01 02 04 05 11");
        lists.add("01 02 04 06 07");
        lists.add("01 02 04 06 08");
        lists.add("01 02 04 06 09");
        lists.add("01 02 04 06 10");
        lists.add("01 02 04 06 11");
        lists.add("01 02 04 07 08");
        lists.add("01 02 04 07 09");
        lists.add("01 02 04 07 10");
        lists.add("01 02 04 07 11");
        lists.add("01 02 04 08 09");
        lists.add("01 02 04 08 10");
        lists.add("01 02 04 08 11");
        lists.add("01 02 04 09 10");
        lists.add("01 02 04 09 11");
        lists.add("01 02 04 10 11");
        lists.add("01 02 05 06 07");
        lists.add("01 02 05 06 08");
        lists.add("01 02 05 06 09");
        lists.add("01 02 05 06 10");
        lists.add("01 02 05 06 11");
        lists.add("01 02 05 07 08");
        lists.add("01 02 05 07 09");
        lists.add("01 02 05 07 10");
        lists.add("01 02 05 07 11");
        lists.add("01 02 05 08 09");
        lists.add("01 02 05 08 10");
        lists.add("01 02 05 08 11");
        lists.add("01 02 05 09 10");
        lists.add("01 02 05 09 11");
        lists.add("01 02 05 10 11");
        lists.add("01 02 06 07 08");
        lists.add("01 02 06 07 09");
        lists.add("01 02 06 07 10");
        lists.add("01 02 06 07 11");
        lists.add("01 02 06 08 09");
        lists.add("01 02 06 08 10");
        lists.add("01 02 06 08 11");
        lists.add("01 02 06 09 10");
        lists.add("01 02 06 09 11");
        lists.add("01 02 06 10 11");
        lists.add("01 02 07 08 09");
        lists.add("01 02 07 08 10");
        lists.add("01 02 07 08 11");
        lists.add("01 02 07 09 10");
        lists.add("01 02 07 09 11");
        lists.add("01 02 07 10 11");
        lists.add("01 02 08 09 10");
        lists.add("01 02 08 09 11");
        lists.add("01 02 08 10 11");
        lists.add("01 02 09 10 11");
        lists.add("01 03 04 05 06");
        lists.add("01 03 04 05 07");
        lists.add("01 03 04 05 08");
        lists.add("01 03 04 05 09");
        lists.add("01 03 04 05 10");
        lists.add("01 03 04 05 11");
        lists.add("01 03 04 06 07");
        lists.add("01 03 04 06 08");
        lists.add("01 03 04 06 09");
        lists.add("01 03 04 06 10");
        lists.add("01 03 04 06 11");
        lists.add("01 03 04 07 08");
        lists.add("01 03 04 07 09");
        lists.add("01 03 04 07 10");
        lists.add("01 03 04 07 11");
        lists.add("01 03 04 08 09");
        lists.add("01 03 04 08 10");
        lists.add("01 03 04 08 11");
        lists.add("01 03 04 09 10");
        lists.add("01 03 04 09 11");
        lists.add("01 03 04 10 11");
        lists.add("01 03 05 06 07");
        lists.add("01 03 05 06 08");
        lists.add("01 03 05 06 09");
        lists.add("01 03 05 06 10");
        lists.add("01 03 05 06 11");
        lists.add("01 03 05 07 08");
        lists.add("01 03 05 07 09");
        lists.add("01 03 05 07 10");
        lists.add("01 03 05 07 11");
        lists.add("01 03 05 08 09");
        lists.add("01 03 05 08 10");
        lists.add("01 03 05 08 11");
        lists.add("01 03 05 09 10");
        lists.add("01 03 05 09 11");
        lists.add("01 03 05 10 11");
        lists.add("01 03 06 07 08");
        lists.add("01 03 06 07 09");
        lists.add("01 03 06 07 10");
        lists.add("01 03 06 07 11");
        lists.add("01 03 06 08 09");
        lists.add("01 03 06 08 10");
        lists.add("01 03 06 08 11");
        lists.add("01 03 06 09 10");
        lists.add("01 03 06 09 11");
        lists.add("01 03 06 10 11");
        lists.add("01 03 07 08 09");
        lists.add("01 03 07 08 10");
        lists.add("01 03 07 08 11");
        lists.add("01 03 07 09 10");
        lists.add("01 03 07 09 11");
        lists.add("01 03 07 10 11");
        lists.add("01 03 08 09 10");
        lists.add("01 03 08 09 11");
        lists.add("01 03 08 10 11");
        lists.add("01 03 09 10 11");
        lists.add("01 04 05 06 07");
        lists.add("01 04 05 06 08");
        lists.add("01 04 05 06 09");
        lists.add("01 04 05 06 10");
        lists.add("01 04 05 06 11");
        lists.add("01 04 05 07 08");
        lists.add("01 04 05 07 09");
        lists.add("01 04 05 07 10");
        lists.add("01 04 05 07 11");
        lists.add("01 04 05 08 09");
        lists.add("01 04 05 08 10");
        lists.add("01 04 05 08 11");
        lists.add("01 04 05 09 10");
        lists.add("01 04 05 09 11");
        lists.add("01 04 05 10 11");
        lists.add("01 04 06 07 08");
        lists.add("01 04 06 07 09");
        lists.add("01 04 06 07 10");
        lists.add("01 04 06 07 11");
        lists.add("01 04 06 08 09");
        lists.add("01 04 06 08 10");
        lists.add("01 04 06 08 11");
        lists.add("01 04 06 09 10");
        lists.add("01 04 06 09 11");
        lists.add("01 04 06 10 11");
        lists.add("01 04 07 08 09");
        lists.add("01 04 07 08 10");
        lists.add("01 04 07 08 11");
        lists.add("01 04 07 09 10");
        lists.add("01 04 07 09 11");
        lists.add("01 04 07 10 11");
        lists.add("01 04 08 09 10");
        lists.add("01 04 08 09 11");
        lists.add("01 04 08 10 11");
        lists.add("01 04 09 10 11");
        lists.add("01 05 06 07 08");
        lists.add("01 05 06 07 09");
        lists.add("01 05 06 07 10");
        lists.add("01 05 06 07 11");
        lists.add("01 05 06 08 09");
        lists.add("01 05 06 08 10");
        lists.add("01 05 06 08 11");
        lists.add("01 05 06 09 10");
        lists.add("01 05 06 09 11");
        lists.add("01 05 06 10 11");
        lists.add("01 05 07 08 09");
        lists.add("01 05 07 08 10");
        lists.add("01 05 07 08 11");
        lists.add("01 05 07 09 10");
        lists.add("01 05 07 09 11");
        lists.add("01 05 07 10 11");
        lists.add("01 05 08 09 10");
        lists.add("01 05 08 09 11");
        lists.add("01 05 08 10 11");
        lists.add("01 05 09 10 11");
        lists.add("01 06 07 08 09");
        lists.add("01 06 07 08 10");
        lists.add("01 06 07 08 11");
        lists.add("01 06 07 09 10");
        lists.add("01 06 07 09 11");
        lists.add("01 06 07 10 11");
        lists.add("01 06 08 09 10");
        lists.add("01 06 08 09 11");
        lists.add("01 06 08 10 11");
        lists.add("01 06 09 10 11");
        lists.add("01 07 08 09 10");
        lists.add("01 07 08 09 11");
        lists.add("01 07 08 10 11");
        lists.add("01 07 09 10 11");
        lists.add("01 08 09 10 11");
        lists.add("02 03 04 05 06");
        lists.add("02 03 04 05 07");
        lists.add("02 03 04 05 08");
        lists.add("02 03 04 05 09");
        lists.add("02 03 04 05 10");
        lists.add("02 03 04 05 11");
        lists.add("02 03 04 06 07");
        lists.add("02 03 04 06 08");
        lists.add("02 03 04 06 09");
        lists.add("02 03 04 06 10");
        lists.add("02 03 04 06 11");
        lists.add("02 03 04 07 08");
        lists.add("02 03 04 07 09");
        lists.add("02 03 04 07 10");
        lists.add("02 03 04 07 11");
        lists.add("02 03 04 08 09");
        lists.add("02 03 04 08 10");
        lists.add("02 03 04 08 11");
        lists.add("02 03 04 09 10");
        lists.add("02 03 04 09 11");
        lists.add("02 03 04 10 11");
        lists.add("02 03 05 06 07");
        lists.add("02 03 05 06 08");
        lists.add("02 03 05 06 09");
        lists.add("02 03 05 06 10");
        lists.add("02 03 05 06 11");
        lists.add("02 03 05 07 08");
        lists.add("02 03 05 07 09");
        lists.add("02 03 05 07 10");
        lists.add("02 03 05 07 11");
        lists.add("02 03 05 08 09");
        lists.add("02 03 05 08 10");
        lists.add("02 03 05 08 11");
        lists.add("02 03 05 09 10");
        lists.add("02 03 05 09 11");
        lists.add("02 03 05 10 11");
        lists.add("02 03 06 07 08");
        lists.add("02 03 06 07 09");
        lists.add("02 03 06 07 10");
        lists.add("02 03 06 07 11");
        lists.add("02 03 06 08 09");
        lists.add("02 03 06 08 10");
        lists.add("02 03 06 08 11");
        lists.add("02 03 06 09 10");
        lists.add("02 03 06 09 11");
        lists.add("02 03 06 10 11");
        lists.add("02 03 07 08 09");
        lists.add("02 03 07 08 10");
        lists.add("02 03 07 08 11");
        lists.add("02 03 07 09 10");
        lists.add("02 03 07 09 11");
        lists.add("02 03 07 10 11");
        lists.add("02 03 08 09 10");
        lists.add("02 03 08 09 11");
        lists.add("02 03 08 10 11");
        lists.add("02 03 09 10 11");
        lists.add("02 04 05 06 07");
        lists.add("02 04 05 06 08");
        lists.add("02 04 05 06 09");
        lists.add("02 04 05 06 10");
        lists.add("02 04 05 06 11");
        lists.add("02 04 05 07 08");
        lists.add("02 04 05 07 09");
        lists.add("02 04 05 07 10");
        lists.add("02 04 05 07 11");
        lists.add("02 04 05 08 09");
        lists.add("02 04 05 08 10");
        lists.add("02 04 05 08 11");
        lists.add("02 04 05 09 10");
        lists.add("02 04 05 09 11");
        lists.add("02 04 05 10 11");
        lists.add("02 04 06 07 08");
        lists.add("02 04 06 07 09");
        lists.add("02 04 06 07 10");
        lists.add("02 04 06 07 11");
        lists.add("02 04 06 08 09");
        lists.add("02 04 06 08 10");
        lists.add("02 04 06 08 11");
        lists.add("02 04 06 09 10");
        lists.add("02 04 06 09 11");
        lists.add("02 04 06 10 11");
        lists.add("02 04 07 08 09");
        lists.add("02 04 07 08 10");
        lists.add("02 04 07 08 11");
        lists.add("02 04 07 09 10");
        lists.add("02 04 07 09 11");
        lists.add("02 04 07 10 11");
        lists.add("02 04 08 09 10");
        lists.add("02 04 08 09 11");
        lists.add("02 04 08 10 11");
        lists.add("02 04 09 10 11");
        lists.add("02 05 06 07 08");
        lists.add("02 05 06 07 09");
        lists.add("02 05 06 07 10");
        lists.add("02 05 06 07 11");
        lists.add("02 05 06 08 09");
        lists.add("02 05 06 08 10");
        lists.add("02 05 06 08 11");
        lists.add("02 05 06 09 10");
        lists.add("02 05 06 09 11");
        lists.add("02 05 06 10 11");
        lists.add("02 05 07 08 09");
        lists.add("02 05 07 08 10");
        lists.add("02 05 07 08 11");
        lists.add("02 05 07 09 10");
        lists.add("02 05 07 09 11");
        lists.add("02 05 07 10 11");
        lists.add("02 05 08 09 10");
        lists.add("02 05 08 09 11");
        lists.add("02 05 08 10 11");
        lists.add("02 05 09 10 11");
        lists.add("02 06 07 08 09");
        lists.add("02 06 07 08 10");
        lists.add("02 06 07 08 11");
        lists.add("02 06 07 09 10");
        lists.add("02 06 07 09 11");
        lists.add("02 06 07 10 11");
        lists.add("02 06 08 09 10");
        lists.add("02 06 08 09 11");
        lists.add("02 06 08 10 11");
        lists.add("02 06 09 10 11");
        lists.add("02 07 08 09 10");
        lists.add("02 07 08 09 11");
        lists.add("02 07 08 10 11");
        lists.add("02 07 09 10 11");
        lists.add("02 08 09 10 11");
        lists.add("03 04 05 06 07");
        lists.add("03 04 05 06 08");
        lists.add("03 04 05 06 09");
        lists.add("03 04 05 06 10");
        lists.add("03 04 05 06 11");
        lists.add("03 04 05 07 08");
        lists.add("03 04 05 07 09");
        lists.add("03 04 05 07 10");
        lists.add("03 04 05 07 11");
        lists.add("03 04 05 08 09");
        lists.add("03 04 05 08 10");
        lists.add("03 04 05 08 11");
        lists.add("03 04 05 09 10");
        lists.add("03 04 05 09 11");
        lists.add("03 04 05 10 11");
        lists.add("03 04 06 07 08");
        lists.add("03 04 06 07 09");
        lists.add("03 04 06 07 10");
        lists.add("03 04 06 07 11");
        lists.add("03 04 06 08 09");
        lists.add("03 04 06 08 10");
        lists.add("03 04 06 08 11");
        lists.add("03 04 06 09 10");
        lists.add("03 04 06 09 11");
        lists.add("03 04 06 10 11");
        lists.add("03 04 07 08 09");
        lists.add("03 04 07 08 10");
        lists.add("03 04 07 08 11");
        lists.add("03 04 07 09 10");
        lists.add("03 04 07 09 11");
        lists.add("03 04 07 10 11");
        lists.add("03 04 08 09 10");
        lists.add("03 04 08 09 11");
        lists.add("03 04 08 10 11");
        lists.add("03 04 09 10 11");
        lists.add("03 05 06 07 08");
        lists.add("03 05 06 07 09");
        lists.add("03 05 06 07 10");
        lists.add("03 05 06 07 11");
        lists.add("03 05 06 08 09");
        lists.add("03 05 06 08 10");
        lists.add("03 05 06 08 11");
        lists.add("03 05 06 09 10");
        lists.add("03 05 06 09 11");
        lists.add("03 05 06 10 11");
        lists.add("03 05 07 08 09");
        lists.add("03 05 07 08 10");
        lists.add("03 05 07 08 11");
        lists.add("03 05 07 09 10");
        lists.add("03 05 07 09 11");
        lists.add("03 05 07 10 11");
        lists.add("03 05 08 09 10");
        lists.add("03 05 08 09 11");
        lists.add("03 05 08 10 11");
        lists.add("03 05 09 10 11");
        lists.add("03 06 07 08 09");
        lists.add("03 06 07 08 10");
        lists.add("03 06 07 08 11");
        lists.add("03 06 07 09 10");
        lists.add("03 06 07 09 11");
        lists.add("03 06 07 10 11");
        lists.add("03 06 08 09 10");
        lists.add("03 06 08 09 11");
        lists.add("03 06 08 10 11");
        lists.add("03 06 09 10 11");
        lists.add("03 07 08 09 10");
        lists.add("03 07 08 09 11");
        lists.add("03 07 08 10 11");
        lists.add("03 07 09 10 11");
        lists.add("03 08 09 10 11");
        lists.add("04 05 06 07 08");
        lists.add("04 05 06 07 09");
        lists.add("04 05 06 07 10");
        lists.add("04 05 06 07 11");
        lists.add("04 05 06 08 09");
        lists.add("04 05 06 08 10");
        lists.add("04 05 06 08 11");
        lists.add("04 05 06 09 10");
        lists.add("04 05 06 09 11");
        lists.add("04 05 06 10 11");
        lists.add("04 05 07 08 09");
        lists.add("04 05 07 08 10");
        lists.add("04 05 07 08 11");
        lists.add("04 05 07 09 10");
        lists.add("04 05 07 09 11");
        lists.add("04 05 07 10 11");
        lists.add("04 05 08 09 10");
        lists.add("04 05 08 09 11");
        lists.add("04 05 08 10 11");
        lists.add("04 05 09 10 11");
        lists.add("04 06 07 08 09");
        lists.add("04 06 07 08 10");
        lists.add("04 06 07 08 11");
        lists.add("04 06 07 09 10");
        lists.add("04 06 07 09 11");
        lists.add("04 06 07 10 11");
        lists.add("04 06 08 09 10");
        lists.add("04 06 08 09 11");
        lists.add("04 06 08 10 11");
        lists.add("04 06 09 10 11");
        lists.add("04 07 08 09 10");
        lists.add("04 07 08 09 11");
        lists.add("04 07 08 10 11");
        lists.add("04 07 09 10 11");
        lists.add("04 08 09 10 11");
        lists.add("05 06 07 08 09");
        lists.add("05 06 07 08 10");
        lists.add("05 06 07 08 11");
        lists.add("05 06 07 09 10");
        lists.add("05 06 07 09 11");
        lists.add("05 06 07 10 11");
        lists.add("05 06 08 09 10");
        lists.add("05 06 08 09 11");
        lists.add("05 06 08 10 11");
        lists.add("05 06 09 10 11");
        lists.add("05 07 08 09 10");
        lists.add("05 07 08 09 11");
        lists.add("05 07 08 10 11");
        lists.add("05 07 09 10 11");
        lists.add("05 08 09 10 11");
        lists.add("06 07 08 09 10");
        lists.add("06 07 08 09 11");
        lists.add("06 07 08 10 11");
        lists.add("06 07 09 10 11");
        lists.add("06 08 09 10 11");
        lists.add("07 08 09 10 11");
        return lists;
    }

}

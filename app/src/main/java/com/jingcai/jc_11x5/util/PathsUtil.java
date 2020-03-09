package com.jingcai.jc_11x5.util;

/**
 * Created by yangsen on 2016/8/10.
 */
public class PathsUtil {

    public static String getAppRoot(){
        StringBuffer sbPath = new StringBuffer();
        sbPath.append("/");
        sbPath.append("jingcaiapp");
        sbPath.append("/");
        return sbPath.toString();
    }

    public static String getLogRoot(){
        StringBuffer sbPath = new StringBuffer();
        sbPath.append("/");
        sbPath.append("jingcaiapp");
        sbPath.append("/");
        sbPath.append("log");
        sbPath.append("/");
        return sbPath.toString();
    }



}

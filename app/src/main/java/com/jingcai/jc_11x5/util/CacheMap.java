package com.jingcai.jc_11x5.util;

import java.util.HashMap;
import java.util.Map;

public class CacheMap {

    private static Map<String, Object> cacheMap = new HashMap();

    public static void setMap(String key, Object value){
        cacheMap.put(key, value);
    }

    public static Object getMap(String key){
        return cacheMap.get(key);
    }
}

package com.jingcai.jc_11x5.util;

import com.jingcai.jc_11x5.consts.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangsen on 2016/7/22.
 */
public class JSONUtil {

    public static JSONObject mkPaging(int pagerIndex){
        JSONObject json=null;
        try {
            json = new JSONObject();
            json.put("current_page", pagerIndex);
            json.put("page_count", Constants.PAGE_COUNT);
        } catch (JSONException e) {
            json=null;
        }
        return json;
    }
}

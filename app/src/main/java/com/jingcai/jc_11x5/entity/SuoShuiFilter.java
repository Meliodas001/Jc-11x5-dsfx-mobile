package com.jingcai.jc_11x5.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangsen on 2017/6/8.
 */

public class SuoShuiFilter implements Serializable {

    private int type;
    private String text;
    private Map<Integer, String> map;
    private String jieshi;
    private String anli;

    public SuoShuiFilter() {
        map = new HashMap<>();
    }

    public SuoShuiFilter(int type, String text) {
        this.type = type;
        this.text = text;
        this.map = new HashMap<>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public String getJieshi() {
        return jieshi;
    }

    public void setJieshi(String jieshi) {
        this.jieshi = jieshi;
    }

    public String getAnli() {
        return anli;
    }

    public void setAnli(String anli) {
        this.anli = anli;
    }
}

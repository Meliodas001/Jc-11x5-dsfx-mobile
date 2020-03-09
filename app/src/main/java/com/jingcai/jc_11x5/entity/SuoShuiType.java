package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * Created by yangsen on 2017/6/25.
 */

public class SuoShuiType implements Serializable {

    private int type;
    private String bigTitle;
    private String littleTitle;

    public SuoShuiType(int type, String bigTitle, String littleTitle) {
        this.type = type;
        this.bigTitle = bigTitle;
        this.littleTitle = littleTitle;
    }

    public SuoShuiType() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle;
    }

    public String getLittleTitle() {
        return littleTitle;
    }

    public void setLittleTitle(String littleTitle) {
        this.littleTitle = littleTitle;
    }
}

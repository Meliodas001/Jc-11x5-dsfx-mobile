package com.jingcai.jc_11x5.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by yangsen on 2017/5/28.
 */
@DatabaseTable(tableName="jc-11x5_sys_kaijiangTime")
public class KaiJiangTime implements Serializable {

    @DatabaseField(id=true, columnName="caizhong")
    private String caizhong;

    @DatabaseField(columnName="ksTime")
    private String ksTime;

    @DatabaseField(columnName="jsTime")
    private String jsTime;

    @DatabaseField(columnName="count")
    private String count;

    public KaiJiangTime(){}

    public KaiJiangTime(String caizhong, String ksTime, String jsTime, String count) {
        this.caizhong = caizhong;
        this.ksTime = ksTime;
        this.jsTime = jsTime;
        this.count = count;
    }

    public String getCaizhong() {
        return caizhong;
    }

    public void setCaizhong(String caizhong) {
        this.caizhong = caizhong;
    }

    public String getKsTime() {
        return ksTime;
    }

    public void setKsTime(String ksTime) {
        this.ksTime = ksTime;
    }

    public String getJsTime() {
        return jsTime;
    }

    public void setJsTime(String jsTime) {
        this.jsTime = jsTime;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

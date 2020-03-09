package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * Created by yangsen on 2018/1/16.
 */

public class SystemSetting implements Serializable {

    private String id; //
    private String initCoin; //初始化赠送积分数量
    private String platRatio; //计划分润比例
    private String reserved1; //预留1
    private String reserved2; //预留2

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitCoin() {
        return initCoin;
    }

    public void setInitCoin(String initCoin) {
        this.initCoin = initCoin;
    }

    public String getPlatRatio() {
        return platRatio;
    }

    public void setPlatRatio(String platRatio) {
        this.platRatio = platRatio;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }
}

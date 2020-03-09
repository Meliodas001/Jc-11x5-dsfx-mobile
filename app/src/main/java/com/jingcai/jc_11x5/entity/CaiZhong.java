package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/7.
 */

public class CaiZhong implements Serializable {

    private String caiBm;
    private String caiMc;
    private String caiProvince;

    public CaiZhong(){}

    public CaiZhong(String caiBm, String caiMc) {
        this.caiBm = caiBm;
        this.caiMc = caiMc;
    }

    public String getCaiBm() {
        return caiBm;
    }

    public void setCaiBm(String caiBm) {
        this.caiBm = caiBm;
    }

    public String getCaiMc() {
        return caiMc;
    }

    public void setCaiMc(String caiMc) {
        this.caiMc = caiMc;
    }

    public String getCaiProvince() {
        return caiProvince;
    }

    public void setCaiProvince(String caiProvince) {
        this.caiProvince = caiProvince;
    }
}

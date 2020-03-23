package com.jingcai.jc_11x5.entity;

import android.text.TextUtils;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.DateUtil;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yangsen on 2018/1/19.
 */

public class Lottery implements Serializable {

    private String caiType; //例如：广东11x5
    private String caiTypeMc;//例如：广东
    private String kjsj; //开奖时间
    private String jssj; //开奖结束时间
    private String count; //开奖总期数
    private String caiQishu; //当前期数
    private String caiNumber; //彩票号码
    private String[] caiNumArray; //
    private String jiFen; //积分
    private String dianBi; //点币
    private String yue; //余额
    private Boolean isNew; //是否为新对象


    public Lottery() {
    }

    public Lottery(String caiType, String kjsj, String jssj, String count) {
        this.caiType = caiType;
        this.kjsj = kjsj;
        this.jssj = jssj;
        this.count = count;
    }

    public Lottery(Map map, String caiType) {
        this.caiQishu = map.get("issue").toString();
        this.kjsj = (DateUtil.formatToStr1(map.get("endtime").toString()));
        this.jssj = (DateUtil.formatToStr1(map.get("createTime").toString()));
        this.count = map.get("number").toString();
        this.caiType = caiType;
        this.caiTypeMc = CaiUtil.getCaiMcShort(caiType);
        this.caiNumber = map.get("code").toString();
        this.caiNumArray = map.get("code").toString().split(" ");
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getYue() {
        return yue;
    }

    public void setYue(String yue) {
        this.yue = yue;
    }

    public String getCaiType() {
        return caiType;
    }

    public void setCaiType(String caiType) {
        this.caiType = caiType;
    }

    public String getCaiTypeMc() {
        return caiTypeMc;
    }

    public void setCaiTypeMc(String caiTypeMc) {
        this.caiTypeMc = caiTypeMc;
    }

    public String getKjsj() {
        return kjsj;
    }

    public void setKjsj(String kjsj) {
        this.kjsj = kjsj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCaiQishu() {
        return caiQishu;
    }

    public void setCaiQishu(String caiQishu) {
        this.caiQishu = caiQishu;
    }

    public String getCaiNumber() {
        return caiNumber;
    }

    public void setCaiNumber(String caiNumber) {
        this.caiNumber = caiNumber;
    }

    public String[] getCaiNumArray() {
        return caiNumArray;
    }

    public void setCaiNumArray(String[] caiNumArray) {
        this.caiNumArray = caiNumArray;
    }

    public String getJiFen() {
        return jiFen;
    }

    public void setJiFen(String jiFen) {
        this.jiFen = jiFen;
    }

    public String getDianBi() {
        return dianBi;
    }

    public void setDianBi(String dianBi) {
        this.dianBi = dianBi;
    }
}

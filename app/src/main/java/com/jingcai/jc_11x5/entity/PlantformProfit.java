package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 平台分润记录表
 * Created by yangsen on 2018/1/15.
 */

public class PlantformProfit implements Serializable {

    private String id; //
    private String payUserId; //支付者用户ID
    private String planId; //计划编号
    private String payTime; //支付时间
    private String payBean; //支付点币
    private String ptProfit; //平台收益
    private String planProfit; //方案员收益
    private Integer payType; //收益类型 1： 计划分润收益  2： 积分兑换收益

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayBean() {
        return payBean;
    }

    public void setPayBean(String payBean) {
        this.payBean = payBean;
    }

    public String getPtProfit() {
        return ptProfit;
    }

    public void setPtProfit(String ptProfit) {
        this.ptProfit = ptProfit;
    }

    public String getPlanProfit() {
        return planProfit;
    }

    public void setPlanProfit(String planProfit) {
        this.planProfit = planProfit;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}

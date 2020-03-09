package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * Created by yangsen on 2018/1/15.
 */

public class BuyPlanRecord implements Serializable {

    private String id; //
    private String userId; //用户ID
    private String planId; //
    private String PayTime; //支付时间
    private String PayBean; //支付积分
    private String PayMoney; //支付点币
    private Integer PayType; //支付方式 1 模拟币  2 点币

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String payTime) {
        PayTime = payTime;
    }

    public String getPayBean() {
        return PayBean;
    }

    public void setPayBean(String payBean) {
        PayBean = payBean;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String payMoney) {
        PayMoney = payMoney;
    }

    public Integer getPayType() {
        return PayType;
    }

    public void setPayType(Integer payType) {
        PayType = payType;
    }
}

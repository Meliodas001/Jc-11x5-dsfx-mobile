package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 用户点币购买记录表
 * Created by yangsen on 2018/1/15.
 */

public class BuyBeanRecord implements Serializable {

    private String id; //主键
    private String userId; //用户ID
    private String payTime; //支付时间
    private String payMoney; //增加点币数量
    private Integer payType; //支付方式:  默认1 ， 支付方式后期扩展
    private String payBalance; //充值后点币余额

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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayBalance() {
        return payBalance;
    }

    public void setPayBalance(String payBalance) {
        this.payBalance = payBalance;
    }
}

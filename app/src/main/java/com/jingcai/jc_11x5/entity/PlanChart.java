package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 用户方案统计表
 * Created by yangsen on 2018/1/15.
 */

public class PlanChart implements Serializable {

    private String id; //
    private String userId; //
    private String planId; //计划编号
    private String planTime; //计划完成时间
    private String cost; //成本
    private String bonus; //中奖金额
    private String profit; //利润
    private Integer state; //状态1：中奖2：未中奖

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

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

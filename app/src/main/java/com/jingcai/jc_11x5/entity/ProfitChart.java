package com.jingcai.jc_11x5.entity;

/**
 * Created by yangsen on 2018/1/19.
 */

public class ProfitChart {

    private String NickName;
    private Integer Bonus; // 利润
    private Integer PlanCount; // 方案数
    private Integer Failure; // 失败数
    private double hitRate;//命中率

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Integer getBonus() {
        return Bonus;
    }

    public void setBonus(Integer bonus) {
        Bonus = bonus;
    }

    public Integer getPlanCount() {
        return PlanCount;
    }

    public void setPlanCount(Integer planCount) {
        PlanCount = planCount;
    }

    public Integer getFailure() {
        return Failure;
    }

    public void setFailure(Integer failure) {
        Failure = failure;
    }

    public double getHitRate() {
        return hitRate;
    }

    public void setHitRate(double hitRate) {
        this.hitRate = hitRate;
    }
}

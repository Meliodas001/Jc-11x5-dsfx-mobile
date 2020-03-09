package com.jingcai.jc_11x5.entity;

import java.math.BigDecimal;

/**
 * Created by yangsen on 2018/1/22.
 */

public class PlanBeiInfo {

    // 序号
    private int SlNub;
    // 期号
    private String issue;
    // 当前倍数
    private Integer Multiple;
    // 当前花费
    private BigDecimal CurrentAmount;
    // 累计消耗
    private BigDecimal TotalAmount;
    // 利润
    private BigDecimal Profit;
    // 盈利率
    private String ProfitRate;

    public int getSlNub() {
        return SlNub;
    }

    public void setSlNub(int slNub) {
        SlNub = slNub;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Integer getMultiple() {
        return Multiple;
    }

    public void setMultiple(Integer multiple) {
        Multiple = multiple;
    }

    public BigDecimal getCurrentAmount() {
        return CurrentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        CurrentAmount = currentAmount;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        TotalAmount = totalAmount;
    }

    public BigDecimal getProfit() {
        return Profit;
    }

    public void setProfit(BigDecimal profit) {
        Profit = profit;
    }

    public String getProfitRate() {
        return ProfitRate;
    }

    public void setProfitRate(String profitRate) {
        ProfitRate = profitRate;
    }
}

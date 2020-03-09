package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 倍投
 */

public class CalculateInfo implements Serializable {

    /// <summary>
    /// 倍数
    /// </summary>
    private int multiple;
    /// <summary>
    /// 当期成本
    /// </summary>
    private int cost;

    /// <summary>
    /// 累计成本
    /// </summary>
    private int totalCost;

    /// <summary>
    /// 盈利
    /// </summary>
    private int profit;

    /// <summary>
    /// 盈利率
    /// </summary>
    private double profitPer;

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public double getProfitPer() {
        //return profitPer * 100;
        return profitPer;
    }

    public void setProfitPer(double profitPer) {
        this.profitPer = profitPer;
    }
}

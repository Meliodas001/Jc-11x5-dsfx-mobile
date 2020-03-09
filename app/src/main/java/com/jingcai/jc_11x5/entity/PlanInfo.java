package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 用户计划表
 * Created by yangsen on 2018/1/15.
 */

public class PlanInfo implements Serializable {

    private String id; //
    private String caiType; //彩种
    private String userId; //用户ID
    private String planName; //计划名称
    private String planContent; //计划签名
    private Integer visType; //显示级别 1完全公开  2：任意支付公开  3：只允许点币支付  4永远隐藏，5已支付, 6.包月
    private String beginOrder; //计划起始期号
    private String endOrder; //计划结束期号
    private Integer orderTotal; //计划总期数
    private Integer currentOrder; //当前进度
    private Integer state; //中奖状态 1中奖 2未中3未开
    private String multiples; //倍数情况 按照 1-1-2-3格式
    private Integer planType; //计划类型 1任选二  2任选三  3 任选四 4 任选五 5任选六 6任选七 7任选八 8前二直选 9前二组选 10前三直选 11前三组选
    private Integer zhuCount; //注数
    private String finishTime; //计划完成时间
    private Integer minIncome; //单注奖金金额
    private boolean planFinish; //计划是否完成
    private Integer totalCost; //总成本
    private Integer checkPrice; //查看费用
    private String nickName;
    private boolean isBuy; //是否购买

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaiType() {
        return caiType;
    }

    public void setCaiType(String caiType) {
        this.caiType = caiType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public Integer getVisType() {
        return visType;
    }

    public void setVisType(Integer visType) {
        this.visType = visType;
    }

    public String getBeginOrder() {
        return beginOrder;
    }

    public void setBeginOrder(String beginOrder) {
        this.beginOrder = beginOrder;
    }

    public String getEndOrder() {
        return endOrder;
    }

    public void setEndOrder(String endOrder) {
        this.endOrder = endOrder;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Integer currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMultiples() {
        return multiples;
    }

    public void setMultiples(String multiples) {
        this.multiples = multiples;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public Integer getZhuCount() {
        return zhuCount;
    }

    public void setZhuCount(Integer zhuCount) {
        this.zhuCount = zhuCount;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getMinIncome() {
        return minIncome;
    }

    public void setMinIncome(Integer minIncome) {
        this.minIncome = minIncome;
    }

    public boolean isPlanFinish() {
        return planFinish;
    }

    public void setPlanFinish(boolean planFinish) {
        this.planFinish = planFinish;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(Integer checkPrice) {
        this.checkPrice = checkPrice;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

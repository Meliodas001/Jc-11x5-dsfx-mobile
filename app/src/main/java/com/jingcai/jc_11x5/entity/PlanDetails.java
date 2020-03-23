package com.jingcai.jc_11x5.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * 用户方案详情
 * Created by yangsen on 2018/1/15.
 */

public class PlanDetails implements Serializable{

    private Integer Id; //
    private String lotteryType; //彩种
    private String PlanId; //方案编号
    private String beginOrder; //期号
    private Integer Multiple; //倍数
    private Integer Bonus; //奖金
    private Integer Cost; //成本
    private Integer LeiCost; //累计成本
    private Integer TotalCost; //总成本
    private Integer PlanType; //方案类型
    private Integer DanBeiCount; //单倍注数
    private String PlanContent; //方案内容
    private String State; //正确情况
    private Integer OneBonus; //积分奖励
    private Integer Sequence; //顺序
    private boolean IsEnd; //是最后一期
    private String LuckyNum; //开奖奖号
    private String UserId;
    private String PayMoney;
    private String PayTime;

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getBeginOrder() {
        return beginOrder;
    }

    public void setBeginOrder(String beginOrder) {
        this.beginOrder = beginOrder;
    }

    public String getZhuCount() {
        return zhuCount;
    }

    public void setZhuCount(String zhuCount) {
        this.zhuCount = zhuCount;
    }

    private String zhuCount;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String payMoney) {
        PayMoney = payMoney;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String payTime) {
        PayTime = payTime;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }


    public String getPlanId() {
        return PlanId;
    }

    public void setPlanId(String planId) {
        PlanId = planId;
    }

    public Integer getMultiple() {
        return Multiple;
    }

    public void setMultiple(Integer multiple) {
        Multiple = multiple;
    }

    public Integer getBonus() {
        return Bonus;
    }

    public void setBonus(Integer bonus) {
        Bonus = bonus;
    }

    public Integer getCost() {
        return Cost;
    }

    public void setCost(Integer cost) {
        Cost = cost;
    }

    public Integer getLeiCost() {
        return LeiCost;
    }

    public void setLeiCost(Integer leiCost) {
        LeiCost = leiCost;
    }

    public Integer getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(Integer totalCost) {
        TotalCost = totalCost;
    }

    public Integer getPlanType() {
        return PlanType;
    }

    public void setPlanType(Integer planType) {
        PlanType = planType;
    }

    public Integer getDanBeiCount() {
        return DanBeiCount;
    }

    public void setDanBeiCount(Integer danBeiCount) {
        DanBeiCount = danBeiCount;
    }

    public String getPlanContent() {
        return PlanContent;
    }

    public void setPlanContent(String planContent) {
        PlanContent = planContent;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Integer getOneBonus() {
        return OneBonus;
    }

    public void setOneBonus(Integer oneBonus) {
        OneBonus = oneBonus;
    }

    public Integer getSequence() {
        return Sequence;
    }

    public void setSequence(Integer sequence) {
        Sequence = sequence;
    }

    public boolean isEnd() {
        return IsEnd;
    }

    public void setEnd(boolean end) {
        IsEnd = end;
    }

    public String getLuckyNum() {
        return LuckyNum;
    }

    public void setLuckyNum(String luckyNum) {
        LuckyNum = luckyNum;
    }

}

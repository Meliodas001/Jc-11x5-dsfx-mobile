package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 用户积分账变记录表
 * Created by yangsen on 2018/1/15.
 */

public class CoinChangeRecord implements Serializable {

    private String Id; //主键
    private String userId; //用户ID
    private Integer changeType; //
    private String changeVal; //账变金额
    private String createTime; //账变时间
    private String details; //记录信息-附带计划ID
    private String lastVal;
    private String finalVal;
    private String isOut;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getChangeVal() {
        return changeVal;
    }

    public void setChangeVal(String changeVal) {
        this.changeVal = changeVal;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLastVal() {
        return lastVal;
    }

    public void setLastVal(String lastVal) {
        this.lastVal = lastVal;
    }

    public String getFinalVal() {
        return finalVal;
    }

    public void setFinalVal(String finalVal) {
        this.finalVal = finalVal;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }
}

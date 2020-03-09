package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

public class JobDistribute implements Serializable {

    private String Id;
    private String UserId;
    private String UserName;
    private String ExpireTime;

    private String TargetUserId;
    private String PlanNickName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getExpireTime() {
        return ExpireTime;
    }

    public void setExpireTime(String expireTime) {
        ExpireTime = expireTime;
    }

    public String getTargetUserId() {
        return TargetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.TargetUserId = targetUserId;
    }

    public String getPlanNickName() {
        return PlanNickName;
    }

    public void setPlanNickName(String planNickName) {
        this.PlanNickName = planNickName;
    }
}

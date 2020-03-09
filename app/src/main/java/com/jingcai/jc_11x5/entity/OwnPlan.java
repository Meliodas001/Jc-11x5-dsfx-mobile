package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 用户已付费计划表（此表用于查询用）
 * Created by yangsen on 2018/1/15.
 */

public class OwnPlan implements Serializable {

    private String id; //
    private String userId; //用户ID
    private String planId; //

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
}

package com.jingcai.jc_11x5.entity;

import java.io.Serializable;

/**
 * 用户点币账变记录表
 * Created by yangsen on 2018/1/15.
 */

public class BalanceChangeRecord implements Serializable {

    private String Id; //主键
    private String UserId; //用户ID
    private Integer ChangeType; //1-充值 2-积分兑换 3.方案收入4-赠送
    private String ChangeVal; //账变金额
    private String CreateTime; //账变时间
    private String Details; //记录信息-附带方案ID

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

    public Integer getChangeType() {
        return ChangeType;
    }

    public void setChangeType(Integer changeType) {
        ChangeType = changeType;
    }

    public String getChangeVal() {
        return ChangeVal;
    }

    public void setChangeVal(String changeVal) {
        ChangeVal = changeVal;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}

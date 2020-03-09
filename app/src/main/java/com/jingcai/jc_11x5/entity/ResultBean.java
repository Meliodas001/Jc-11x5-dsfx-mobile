package com.jingcai.jc_11x5.entity;

import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.db.dao.KaiJiangTimeDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsen on 2018/1/14.
 */

public class ResultBean implements Serializable {

    private Lottery lottery;
    private List<ImageInfo> imgLists;
    private List<NoticeInfo> noticeLists;
    private List<Channel> channelLists;
    private List<LotteryPlan> lotteryPlanList;

    public ResultBean() {
        this.lottery = App.getInstance().getLottery();
        this.channelLists = new ArrayList<>();
        this.lotteryPlanList = new ArrayList<>();
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public List<ImageInfo> getImgLists() {
        return imgLists;
    }

    public void setImgLists(List<ImageInfo> imgLists) {
        this.imgLists = imgLists;
    }

    public List<NoticeInfo> getNoticeLists() {
        return noticeLists;
    }

    public void setNoticeLists(List<NoticeInfo> noticeLists) {
        this.noticeLists = noticeLists;
    }

    public List<Channel> getChannelLists() {
        return channelLists;
    }

    public void setChannelLists(List<Channel> channelLists) {
        this.channelLists = channelLists;
    }

    public List<LotteryPlan> getLotteryPlanList() {
        return lotteryPlanList;
    }

    public void setLotteryPlanList(List<LotteryPlan> lotteryPlanList) {
        this.lotteryPlanList = lotteryPlanList;
    }

    public class Channel{
        private int id;
        private int imgId;
        private String name;

        public Channel() {
        }

        public Channel(int id, int imgId, String name) {
            this.id = id;
            this.imgId = imgId;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class LotteryPlan{

        private String UserName;
        private String NickName;
        private String Id;
        private String CaiType;
        private String UserId;
        private String PlanName;
        private String Signature;
        private Integer VisType;
        private String BeginOrder;
        private String EndOrder;
        private Integer OrderTotal;
        private Integer CurrentOrder;
        private Integer State;
        private String Multiples;
        private Integer PlanType;
        private Integer ZhuCount;
        private String FinishTime;
        private String Bonus;
        private boolean PlanFinish;
        private Integer TotalCost;
        private Integer CheckPrice;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String nickName) {
            NickName = nickName;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getCaiType() {
            return CaiType;
        }

        public void setCaiType(String caiType) {
            CaiType = caiType;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getPlanName() {
            return PlanName;
        }

        public void setPlanName(String planName) {
            PlanName = planName;
        }

        public String getSignature() {
            return Signature;
        }

        public void setSignature(String signature) {
            Signature = signature;
        }

        public Integer getVisType() {
            return VisType;
        }

        public void setVisType(Integer visType) {
            VisType = visType;
        }

        public String getBeginOrder() {
            return BeginOrder;
        }

        public void setBeginOrder(String beginOrder) {
            BeginOrder = beginOrder;
        }

        public String getEndOrder() {
            return EndOrder;
        }

        public void setEndOrder(String endOrder) {
            EndOrder = endOrder;
        }

        public Integer getOrderTotal() {
            return OrderTotal;
        }

        public void setOrderTotal(Integer orderTotal) {
            OrderTotal = orderTotal;
        }

        public Integer getCurrentOrder() {
            return CurrentOrder;
        }

        public void setCurrentOrder(Integer currentOrder) {
            CurrentOrder = currentOrder;
        }

        public Integer getState() {
            return State;
        }

        public void setState(Integer state) {
            State = state;
        }

        public String getMultiples() {
            return Multiples;
        }

        public void setMultiples(String multiples) {
            Multiples = multiples;
        }

        public Integer getPlanType() {
            return PlanType;
        }

        public void setPlanType(Integer planType) {
            PlanType = planType;
        }

        public Integer getZhuCount() {
            return ZhuCount;
        }

        public void setZhuCount(Integer zhuCount) {
            ZhuCount = zhuCount;
        }

        public String getFinishTime() {
            return FinishTime;
        }

        public void setFinishTime(String finishTime) {
            FinishTime = finishTime;
        }

        public String getBonus() {
            return Bonus;
        }

        public void setBonus(String bonus) {
            Bonus = bonus;
        }

        public boolean isPlanFinish() {
            return PlanFinish;
        }

        public void setPlanFinish(boolean planFinish) {
            PlanFinish = planFinish;
        }

        public Integer getTotalCost() {
            return TotalCost;
        }

        public void setTotalCost(Integer totalCost) {
            TotalCost = totalCost;
        }

        public Integer getCheckPrice() {
            return CheckPrice;
        }

        public void setCheckPrice(Integer checkPrice) {
            CheckPrice = checkPrice;
        }
    }

}

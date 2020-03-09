package com.jingcai.jc_11x5.business;

import android.os.Handler;

import com.alibaba.fastjson.JSONObject;
import com.jingcai.jc_11x5.entity.JobPrice;
import com.jingcai.jc_11x5.entity.PlanBeiInfo;
import com.jingcai.jc_11x5.entity.PlanDetails;
import com.jingcai.jc_11x5.entity.PlanInfo;
import com.jingcai.jc_11x5.entity.SuoShuiFilter;
import com.jingcai.jc_11x5.entity.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public interface Jc11x5Interface {

    /**
     * 注册
     * @param handler
     * @param user
     * @return
     */
    void register(Handler handler, UserInfo user);

    void getMassgeCode(Handler handler, String phone);

    /**
     * 用户登录
     * @param handler
     * @param user
     */
    void login(Handler handler, UserInfo user, boolean isFirstRun);

    void getUserInfo(Handler handler, String id);

    /**
     * 根据用户名获取个人信息
     * @param handler
     * @param username
     */
    void getUserInfoByUserName(Handler handler, String username);

    /**
     * 获取版本号
     * @return
     */
    void getBanben(Handler handler);

    /**
     * 修改昵称
     * @param handler
     * @param userId
     * @param newNickName
     */
    void UpdateNickName(Handler handler, String userId, String newNickName);

    /**
     * 修改密码
     * @param handler
     * @param user
     */
    void updatePassWord(Handler handler, UserInfo user, String code);
    void updatePassWord(Handler handler, String etOldPwd, String newPass);

    void verifyComputer(Handler handler, UserInfo user);

    /**
     * 获取彩票开奖时间和每天期数
     * @param handler
     * @param caiType 彩票类型
     */
    void getBeginTime(Handler handler, String caiType);

    /**
     * 根据起始期号和结束期号获取投注记录 //查询当天期号例如 orderNo =180101  ，endOrder=180199
     * @param handler
     * @param caiType 彩票类型
     * @param userId
     * @param orderNo 起始期号
     * @param endOrder 结束期号
     */
    void getTodayDetailsByUserId(Handler handler, String caiType, String userId, String orderNo, String endOrder);

    /**
     * 获取通知列表
     * @param handler
     */
    void getNewsList(Handler handler);

    /**
     * 修改通知状态
     * @param handler
     */
    void updateNewsSate(Handler handler);

    /**
     * 退出
     * @param handler
     */
    void loginOut(Handler handler);

    /**
     * 获取盈利周榜
     * @param handler
     */
    void getWeekTopProfit(Handler handler);

    /**
     * 获取盈利月榜
     * @param handler
     */
    void getMonthTopProfit(Handler handler);

    /**
     * 获取盈利季度榜
     * @param handler
     */
    void getSeasonTopProfit(Handler handler);

    /**
     * 发布计划
     * @param handler
     * @param planInfo
     * @param planDetil
     */
    void addPlanInfo(Handler handler, PlanInfo planInfo, PlanDetails planDetil);

    /**
     * 查看计划
     * @param handler
     * @param planId 计划编号
     * @param userId 用户编号
     */
    void getPlanInfo(Handler handler, String planId, String userId);

    /**
     * 根据计划编号获取第一期的计划明细
     * @param handler
     * @param planInfo
     */
    void getTopOneByPlanId(Handler handler, PlanInfo planInfo);

    /**
     * 获取最新计划（首页显示列表）
     * @param handler
     * @param caiType 彩种
     */
    void newPlanList(Handler handler, String caiType);

    void newPlanListByNickName(Handler handler, String caiType, String nickName);

    /**
     * 支付点币查看计划
     * @param handler
     * @param planId 计划编号
     */
    void addOwnPlan(Handler handler, String planId, String checkPrice);

    /**
     * 最近top80积分账变记录
     * @param handler
     * @param uid 用户编号
     */
    void getCoinChangeRecordTop80(Handler handler, String uid);

    /**
     * 最近top80点币账变记录
     * @param handler
     * @param uid 用户编号
     */
    void getBalanceChangeRecordTop80(Handler handler, String uid);

    /**
     * 用户包月
     * @param handler
     */
    void getJobPriceList(Handler handler);

    /**
     * 全包计划员
     * @param handler
     */
    void getAllJobList(Handler handler);

    /**
     * 部分包计划员
     * @param handler
     */
    void getSingleJobList(Handler handler);

    void getAllJobDistribute(Handler handler, String userId);

    void getSingleJobDistribute(Handler handler, String userId);


    void payAllJob(Handler handler, JobPrice jobPrice);

    void paySingleJob(Handler handler, String userName, int dayType, String planUserId);

    /**
     * 点币兑换积分
     * @param handler
     * @param uerId 用户编号
     * @param price 点币数量
     */
    void coinConversion(Handler handler, String uerId, String price);

    void updateNewsStats(Handler handler);

    /**
     * 余额兑换点币
     * @param handler
     * @param uerId 用户编号
     * @param price 点币数量
     */
    void moneyConversion(Handler handler, String uerId, String price);

    /**
     * 无忧币转赠
     * @param handler
     * @param originalUser
     * @param targetUser
     * @param amount
     */
    void transferBalance(Handler handler, String originalUser, String targetUser, String amount);

    /**
     * 实时开奖
     * @param handler
     * @param caiType
     */
    void getLuckyNumber(Handler handler, String caiType);

    /**
     * 最近百期开奖>100期
     * @param handler
     * @param caiType 彩种
     */
    void getLuckyNumberList(Handler handler, String caiType);

    /**
     * 获取首页数据
     * @param handler
     * @param caiType
     */
    void getHomeData(Handler handler, String userId, String caiType);

    /**
     * 查询缩水结果
     * @param handler
     * @param lists
     */
    void getJisuanSsglLists(Handler handler, List<SuoShuiFilter> lists);

    /**
     * 方案生成
     * @param handler 消息处理
     * @param beginCount 倍数
     * @param danBeiCount 单倍注数
     * @param sliderBonus 单倍奖金
     * @param minRate 盈利率
     * @param planPostCout 即将开奖的期数
     */
    void createPlan(Handler handler,double beginCount, double danBeiCount, double sliderBonus, double minRate, int planPostCout);

    /**
     * 投注生成
     * @param handler
     * @param json
     * @param planBeiInfos
     */
    void okPlanPost(Handler handler, JSONObject json, List<PlanBeiInfo> planBeiInfos);

    /**
     * 根据最低盈利率
     * @param handler
     * @param count 注数
     * @param orderCount 计划期数
     * @param multiple 起始倍数
     * @param bonus 单注奖金
     * @param profitPer 最低盈利率
     */
    void getCalcProfitPer(Handler handler, int count, int orderCount, int multiple, int bonus, double profitPer);

    /**
     * 根据最低盈利金额
     * @param handler
     * @param count 注数
     * @param orderCount 计划期数
     * @param multiple 起始倍数
     * @param bonus 单注奖金
     * @param profit 最低盈利
     */
    void getCalcProfit(Handler handler, int count, int orderCount, int multiple, int bonus, int profit);


    void calculationJiao(Handler handler, String haomaA, String haomaB);


    void calculationBing(Handler handler, String haomaA, String haomaB);


    void calculationApaiB(Handler handler, String haomaA, String haomaB);


    void calculationBpaiA(Handler handler, String haomaA, String haomaB);

}

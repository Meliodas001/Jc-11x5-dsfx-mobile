package com.jingcai.jc_11x5.consts;

/**
 * Created by Administrator on 2017/4/6.
 */

public class InterfaceConsts {
    /*服务器连接地址*/
//    public static final String WEB_SERVER_URL = "http://app.b9b.top:8732/Service1/";
    public static final String WEB_SERVER_URL = "http://94.191.70.88/";
    //public static final String WEB_SERVER_URL = "http://192.168.0.102:8732/Service1/";
    public static final String ROOT = "http://39.108.153.232:7777/";
//    public static final String ROOT = "http://e8vmaa.natappfree.cc/";
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String WS_URL = "ws://39.108.153.232:9999//socketServer/";
    //public static final String NAMESPACE = "http://39.108.153.232:8732/Service1/";

    /* 调用HTTP方法的URL */
    public static final String REGISTER_URL = ROOT + "customer/doRegister";
    public static final String LOGIN_URL = ROOT + "customer/login";
    public static final String REGISTER_PWD_URL = ROOT + "/customer/updatePassword";
    public static final String REGISTER_PWD2_URL = ROOT + "/customer/forgetPassword";
    public static final String PLAY_PLAN_URL = ROOT + "pay/addBuyPlan";
    public static final String PLAY_VIP_URL = ROOT + "pay/payVip";
    public static final String RECORD_URL = ROOT + "userIntegral/getIntegralRecord";
    public static final String CURRENCY_RECORD_URL = ROOT + "userCurrency/getCurrencyRecord";
    public static final String PLANT_RECODE_URL = ROOT + "/userPlan/getUserPlanList";
    public static final String RELEASE_PLAN_URL = ROOT + "planInfo/addReleasePlan";
    public static final String RELEASE_PLAN_DETAILS_URL = ROOT + "planInfo/getPlanInfoDetails";
    public static final String VIP_ALL_URL = ROOT + "vip/getVipList";
    public static final String TREND_ALL_URL = ROOT + "trend/getTrendList";
    public static final String PLAN_ALL_URL = ROOT + "planInfo/getPlanInfoList";
    public static final String REDEEM_POINTS_URL = ROOT + "customer/redeemPoints";
    public static final String DETAIL_URL = ROOT + "customer/detail";
    public static final String EXCHANGE_INTEGRAL_URL = ROOT + "customer/exchangeIntegral";
    public static final String MANAGE_IMAGE_URL = ROOT + "manageAd/getList";
    public static final String NEWS_LIST_URL = ROOT + "manageNotice/getManageNoticeList";
    public static final String NEWS_STATE_URL = ROOT + "manageNotice/updateStatus";
    public static final String GET_PHONE_CODE = ROOT + "customer/sendVerCode";
    public static final String GET_LUCKY_NUMBER_CODE = ROOT + "/trend/getNumbers";
    public static final String UPDATE_USER_NAME_URL = ROOT + "/customer/updateNickname";
    public static final String LOGIN_OUT_URl = ROOT + "/customer/loginOut";

    /* 以下为调用接口的方法名称 webservice */
    public static final String GetBanben = "GetBanben";
    public static final String Login = "Login";
    public static final String CreateUserInfo = "CreateUserInfo";
    public static final String GetCoinById = "GetCoinById";
    public static final String GetBalanceById = "GetBalanceById";
    public static final String UpdateNickName = "UpdateNickName";
    public static final String UpdatePassWord = "UpdatePassWord";
    public static final String VerifyComputer = "VerifyComputer";
    public static final String GetBeginTime = "GetBeginTime";
    public static final String GetTodayDetailsByUserId = "GetTodayDetailsByUserId";
    public static final String GetWeekTopProfit = "GetWeekTopProfit";
    public static final String GetMonthTopProfit = "GetMonthTopProfit";
    public static final String GetSeasonTopProfit = "GetSeasonTopProfit";
    public static final String AddPlanInfo = "AddPlanInfo";
    public static final String AddPlanInfoByJson = "AddPlanInfoByJson";
    public static final String GetPlanInfo = "GetPlanInfo";
    public static final String GetTopOneByPlanId = "GetTopOneByPlanId";
    public static final String NewPlanList = "NewPlanList";
    public static final String NewPlanListByNickName = "NewPlanListByNickName";
    public static final String AddOwnPlan = "AddOwnPlan";
    public static final String GetCoinChangeRecordTop80 = "GetCoinChangeRecordTop80";
    public static final String GetBalanceChangeRecordTop80 = "GetBalanceChangeRecordTop80";
    public static final String CoinConversion = "CoinConversion";
    public static final String TransferBalance = "TransferBalance";
    public static final String GetLuckyNumber = "GetLuckyNumber";
    public static final String GetLuckyNumberList = "GetLuckyNumberList";
    public static final String GetUserInfo = "GetUserInfo";
    public static final String GetUserInfoByUserName = "GetUserInfoByUserName";
    public static final String GetImgList = "GetImgList";
    public static final String GetNoticeList = "GetNoticeList";

    public static final String GetJobPriceList = "GetJobPriceList";

    public static final String GetAllJobList = "GetAllJobList";
    public static final String GetSingleJobList = "GetSingleJobList";

    public static final String PayAllJob = "PayAllJob";
    public static final String PaySingleJob = "PaySingleJob";

    public static final String GetAllJobDistribute = "GetAllJobDistribute";
    public static final String GetSingleJobDistribute = "GetSingleJobDistribute";



    /*遗漏统计----表名*/
    public static final String YLTJ_GD_11X5 = "guangdong-11x5";
    public static final String YLTJ_SD_11X5 = "shandong-11x5";
    public static final String YLTJ_JX_11X5 = "jiangxi-11x5";




}

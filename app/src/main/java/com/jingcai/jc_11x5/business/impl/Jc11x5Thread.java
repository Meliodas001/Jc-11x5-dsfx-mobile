package com.jingcai.jc_11x5.business.impl;

import android.content.Context;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Interface;
import com.jingcai.jc_11x5.business.OkHttpRequester;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.InterfaceConsts;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.db.dao.KaiJiangTimeDao;
import com.jingcai.jc_11x5.db.dao.KaiJiang_11x5Dao;
import com.jingcai.jc_11x5.entity.*;
import com.jingcai.jc_11x5.util.*;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.ksoap2.serialization.SoapObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import static android.content.Context.TELEPHONY_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;
import static com.jingcai.jc_11x5.business.HandlerSendMsg.handlerSendBundleMsg;
import static com.jingcai.jc_11x5.business.HandlerSendMsg.handlerSendMsg;
import static com.jingcai.jc_11x5.business.WebServiceRequester.DealParam;
import static com.jingcai.jc_11x5.business.WebServiceRequester.callWebService;

/**
 * Created by Administrator on 2017/4/2.
 */
public class Jc11x5Thread implements Jc11x5Interface {

    @Override
    public void getBanben(final Handler handler) {
        new Thread(new Runnable() {
           @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetBanben, new DealParam() {
                                @Override
                                public SoapObject dealparam(SoapObject soapObject) {
                                    soapObject.addProperty("Id", "1000");
                                    return soapObject;
                                }
                            });
                            if (json.getIntValue("ztdm") == 0) {
                                handlerSendMsg(handler, HandlerWhat.GET_BANBEN_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                                return;
                            }
                            Version version = new Version();
                            String banbenInfo = json.getString("result");
                            if (banbenInfo != null && !TextUtils.isEmpty(banbenInfo)) {
                                String[] array = banbenInfo.split("\\|");
                                version.setVersionCode(Integer.parseInt(array[0]));
                                version.setUpdateUrl(array[1]);
                                if (Integer.parseInt(array[2]) == 0) {
                                    version.setIsForceUpdate(false);
                                } else {
                                    version.setIsForceUpdate(false);
                                }
                            }
                            String url = "http://39.108.153.232/download/" + version.getVersionCode() + ".html";
                            boolean isKeyong = checkURL(url);
                            String updateInfo = "软件更新！";
                            if (isKeyong) {
                                updateInfo = OkHttpRequester.getStringFromServer1(url);
                            }
                            version.setUpgradeInfo(updateInfo);
                            handlerSendMsg(handler, HandlerWhat.GET_BANBEN_SUCCESS, version);
                        } catch (Exception e) {
                            handlerSendMsg(handler, HandlerWhat.GET_BANBEN_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                        }
                    }
                }).start();
            }
        }).start();
    }

    @Override
    public void register(final Handler handler, final UserInfo user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("phone", user.getUserName())
                            .add("password", user.getPassWord())
                            .add("Password2", user.getPassWord())
                            .add("nickName", user.getUserName())
                            .add("userCover", user.getShareNo())
                            .add("verCode", user.getMsgCode())
                            .add("mac", user.getMac())
                            .build();
                    String result = OkHttpRequester.postStringFromServer(InterfaceConsts.REGISTER_URL, requestBody, false);
                    map = new Gson().fromJson(result, HashMap.class);
                    if (!map.get("code").equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.REGISTER_FALIURE, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.REGISTER_SUCCESS, map.get("message"));
                    }
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.REGISTER_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void getMassgeCode(final Handler handler, final String phone, final int flag) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    RequestBody requestBody = new FormBody.Builder().build();
                    String result = OkHttpRequester.postStringFromServer(InterfaceConsts.GET_PHONE_CODE + "?flag=" + flag + "&phone=" + phone, requestBody,false);
                    map = new Gson().fromJson(result, HashMap.class);
                    if (!map.get("code").equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.GET_PHONE_CODE_FALIURE, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_PHONE_CODE_SUCCESS, map.get("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_PHONE_CODE_TIMEOUT, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void login(final Handler handler, final UserInfo user, final boolean isFirstRun) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    String loginUrl = InterfaceConsts.LOGIN_URL;
                    loginUrl += "?phone=" + user.getUserName() + "&password=" + user.getPassWord() + "&mac=" + user.getMac();
                    RequestBody requestBody = new FormBody.Builder().build();
                    String result = OkHttpRequester.postStringFromServer(loginUrl, requestBody, false);
                    map = JSON.parseObject(result, HashMap.class);
                    if (!map.get("code").equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.LOGIN_FALIURE, map.get("message"));
                        return;
                    }

                    Map userMap = (Map) map.get("data");
                    if (userMap.get("id") != null)
                        user.setId(String.valueOf(userMap.get("id")));
                    if (userMap.get("nickname") != null)
                        user.setNickName(userMap.get("nickname").toString());
                    if (userMap.get("userCover") != null)
                        user.setUserCover(userMap.get("userCover").toString());
                    if (userMap.get("money") != null)
                        user.setMoney(userMap.get("money").toString());
                    if (userMap.get("balance") != null)
                        user.setBalance(userMap.get("balance").toString());
                    if (userMap.get("coin") != null)
                        user.setCoin(userMap.get("coin").toString());
                    if (userMap.get("shareCode") != null)
                        user.setShareNo(userMap.get("shareCode").toString());
                    if (userMap.get("createTime") != null)
                        user.setCreateTime(userMap.get("createTime").toString());
                    if (userMap.get("token") != null)
                        user.setToken(userMap.get("token").toString());
                    if (userMap.get("vip") != null)
                        user.setVip(Boolean.getBoolean(userMap.get("vip").toString()));
                    if (userMap.get("vipEndData") != null)
                        user.setVipEndData(userMap.get("vipEndData").toString());
                    if (userMap.get("news") != null)
                        user.setNews(Integer.parseInt(userMap.get("news").toString()));
                    if (userMap.get("integral") != null)
                        user.setIntegral(userMap.get("integral").toString());
                    if (userMap.get("game") != null)
                        user.setGame(userMap.get("game").toString());
                    App.getInstance().setUser(user);

                    TokenEntity entity = new TokenEntity();
                    entity.setId(userMap.get("id").toString());
                    entity.setToken(userMap.get("token").toString());

                    handlerSendMsg(handler, HandlerWhat.LOGIN_SUCCESS, user);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.LOGIN_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void getUserInfo(final Handler handler, final String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json_jforDb = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetUserInfo, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("Id", id);
                            return soapObject;
                        }
                    });
                    if (json_jforDb.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_USERINFOBYID_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                        return;
                    }
                    String result = json_jforDb.getString("result");
                    UserInfo userInfo = new Gson().fromJson(result, UserInfo.class);
                    handlerSendMsg(handler, HandlerWhat.GET_USERINFOBYID_SUCCESS, userInfo);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_USERINFOBYID_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getUserInfoByUserName(final Handler handler, final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    result = OkHttpRequester.getStringFromServer(InterfaceConsts.DETAIL_URL, true);
                    UserInfo user = JSON.parseObject(JSON.parseObject(result).getString("data"), UserInfo.class);
                    App app = App.getInstance();
                    Lottery lottery = app.getLottery();
                    lottery.setDianBi(user.getMoney());
                    lottery.setJiFen(user.getCoin());
                    UserInfo userInfo = app.getUser();
                    userInfo.setMoney(user.getMoney());
                    userInfo.setCoin(user.getCoin());
                    userInfo.setBalance(user.getBalance());
                    userInfo.setVip(user.getVip());
                    if (user.getVipEndData() != null)
                        userInfo.setVipEndData(user.getVipEndData());
                    app.setUser(userInfo);
                    handlerSendMsg(handler, HandlerWhat.GET_USERINFOBYNAME_SUCCESS, user);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_USERINFOBYNAME_FALIURE, JSON.parseObject(result).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void UpdateNickName(final Handler handler, final String userId, final String newNickName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = OkHttpRequester.postStringFromServer(InterfaceConsts.UPDATE_USER_NAME_URL + "?nickname=" + newNickName, new FormBody.Builder().build(), true);
                    UserInfo u = JSON.parseObject(JSON.parseObject(result).getString("data"), UserInfo.class);
                    if (u != null) {
                        UserInfo userInfo = App.getInstance().getUser();
                        userInfo.setNickName(newNickName);
                        App.getInstance().setUser(userInfo);
                        handlerSendMsg(handler, HandlerWhat.UPDATE_NC_SUCCESS, "昵称修改成功！");
                        return;
                    }
                    handlerSendMsg(handler, HandlerWhat.UPDATE_NC_FALIURE, JSON.parseObject(result).getString("message"));
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.UPDATE_NC_FALIURE, JSON.parseObject(result).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void updatePassWord(final Handler handler, final String etOldPwd, final String newPass) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    UserInfo userInfo = App.getInstance().getUser();
                    RequestBody requestBody = new FormBody.Builder().build();
                    String result = OkHttpRequester.postStringFromServer(InterfaceConsts.REGISTER_PWD_URL + "?oldPass=" + etOldPwd + "&newPass=" + newPass, requestBody, true);
                    map = JSON.parseObject(result);
                    if (map.get("code").equals("1")) {
                        userInfo.setPassWord(newPass);
                        App.getInstance().setUser(userInfo);
                        handlerSendMsg(handler, HandlerWhat.UPDATE_PWD_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.UPDATE_PWD_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.UPDATE_PWD_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void verifyComputer(final Handler handler, final UserInfo user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.VerifyComputer, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("phone", user.getUserName());
                            String mac1 = user.getMac();
                            String mac2 = AppTool.getImei(App.getInstance().getApplicationContext());
                            if (mac2 != null && !TextUtils.isEmpty(mac2)) {
                                mac1 = mac2;
                            }
                            soapObject.addProperty("mac", mac1);
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.VERIFY_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    handlerSendMsg(handler, HandlerWhat.VERIFY_SUCCESS, result);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.VERIFY_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void updatePassWord(final Handler handler, final UserInfo user, final String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try{
                    String requestUrl = InterfaceConsts.REGISTER_PWD2_URL + "?phone=" + user.getUserName() + "&verCode=" + code + "&password=" + user.getPassWord();
                    String result = OkHttpRequester.postStringFromServer(requestUrl, new FormBody.Builder().build(), false);
                    map = JSON.parseObject(result);
                    if (map.get("code").equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.UPDATE_FORGET_PWD_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.UPDATE_FORGET_PWD_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.UPDATE_FORGET_PWD_FALIURE, map.get("message"));
                }
            }
        }).start();
    }


    @Override
    public void getBeginTime(final Handler handler, final String caiType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetBeginTime, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("caiType", caiType);
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_KJSJ_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    if (!TextUtils.isEmpty(result)) {
                        String[] array = result.split("\\|");
                        String qishu = "";
                        String kssj = "";
                        String jssj = "";
                        if (array.length == 2) {
                            qishu = array[0];
                            String sj = array[1];
                            String[] sjarray = sj.split(":");
                            String h = sjarray[0];
                            if (h.length() == 1) {
                                h = "0" + h;
                            }
                            String m = sjarray[1];
                            if (m.length() == 1) {
                                m = "0" + m;
                            }
                            kssj = h + ":" + m + ":00";
                            jssj = DateUtil.getJsTime(kssj, qishu);
                        }
                        KaiJiangTime kaiJiangTime = new KaiJiangTime(CaiUtil.getCaiMc(caiType), kssj, jssj, qishu);
                        boolean isCg = new KaiJiangTimeDao().createOrUpdate(kaiJiangTime);
                        App app = App.getInstance();
                        Lottery lottery = app.getLottery();
                        lottery.setKjsj(kssj);
                        lottery.setJssj(jssj);
                        lottery.setCount(qishu);
                        lottery.setCaiType(caiType);
                        app.setLottery(lottery);
                        UserInfo userInfo = app.getUser();
                        userInfo.setCaizhongMc(caiType);
                        userInfo.setCaizhong(CaiUtil.getCaiBm(caiType));
                        app.setUser(userInfo);
                    }
                    handlerSendMsg(handler, HandlerWhat.GET_KJSJ_SUCCESS, "");
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_KJSJ_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getTodayDetailsByUserId(final Handler handler, final String caiType, final String userId, final String orderNo, final String endOrder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = OkHttpRequester.getStringFromServer(InterfaceConsts.PLANT_RECODE_URL, true);
                    if (JSON.parseObject(result).get("code").toString().equals("1")) {
                        String json = JSON.parseObject(result).getString("data");
                        List<PlanDetails> lists = JSON.parseArray(JSON.parseObject(json).getString("records"), PlanDetails.class);
                        handlerSendBundleMsg(handler, HandlerWhat.GET_TZJL_SUCCESS, ReturnStatus.KEY_GET_TOUZHU_LIST, lists);
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_TZJL_FALIURE, JSON.parseObject(result).getString("message").toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_TZJL_FALIURE, JSON.parseObject(result).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void getNewsList(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resutl = "";
                try{
                    resutl = OkHttpRequester.getStringFromServer(InterfaceConsts.NEWS_LIST_URL + "?page=1&limit=1000", true);
                    String json = JSON.parseObject(resutl).getString("data");
                    List<News> list = JSON.parseArray(JSON.parseObject(json).getString("records"), News.class);
                    handlerSendBundleMsg(handler, HandlerWhat.GET_NEWS_LIST_SUCCESS, ReturnStatus.KEY_GET_NES_LIST, list);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_NEWS_LIST_FALIURE, JSON.parseObject(resutl).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void updateNewsSate(Handler handler) {
        try{
//            String resutl = OkHttpRequester.postStringFromServer(InterfaceConsts.NEWS_STATE_URL, new FormBody.Builder().build(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginOut(Handler handler) {
        try{
            String resutl = OkHttpRequester.getStringFromServer(InterfaceConsts.LOGIN_OUT_URl, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeekTopProfit(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetWeekTopProfit, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_WEEK_YL_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<ProfitChart> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSONObject.parseArray(result);
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            ProfitChart profitChart = new Gson().fromJson(jsonArray.get(i).toString(), ProfitChart.class);
                            profitChart.setHitRate(Double.parseDouble(decimalFormat.format((float) (profitChart.getPlanCount() - profitChart.getFailure()) * 100 / profitChart.getPlanCount())));
                            list.add(profitChart);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_WEEK_YL_SUCCESS, ReturnStatus.KEY_GET_PROFIT_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_WEEK_YL_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getMonthTopProfit(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetMonthTopProfit, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_MONTH_YL_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<ProfitChart> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSONObject.parseArray(result);
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            ProfitChart profitChart = new Gson().fromJson(jsonArray.get(i).toString(), ProfitChart.class);
                            profitChart.setHitRate(Double.parseDouble(decimalFormat.format((float) (profitChart.getPlanCount() - profitChart.getFailure()) * 100 / profitChart.getPlanCount())));
                            list.add(profitChart);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_MONTH_YL_SUCCESS, ReturnStatus.KEY_GET_PROFIT_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_MONTH_YL_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getSeasonTopProfit(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetSeasonTopProfit, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_SEASON_YL_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<ProfitChart> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSONObject.parseArray(result);
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            ProfitChart profitChart = new Gson().fromJson(jsonArray.get(i).toString(), ProfitChart.class);
                            profitChart.setHitRate(Double.parseDouble(decimalFormat.format((float) (profitChart.getPlanCount() - profitChart.getFailure()) * 100 / profitChart.getPlanCount())));
                            list.add(profitChart);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_SEASON_YL_SUCCESS, ReturnStatus.KEY_GET_PROFIT_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_SEASON_YL_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void addPlanInfo(final Handler handler, final PlanInfo planInfo, final PlanDetails planDetil) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.AddPlanInfo, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("planInfo", planInfo);
                            soapObject.addProperty("planDetil", planDetil);
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.ADD_PLAN_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    handlerSendMsg(handler, HandlerWhat.ADD_PLAN_SUCCESS, result);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.ADD_PLAN_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void  getPlanInfo(final Handler handler, final String planId, final String userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    String url = InterfaceConsts.RELEASE_PLAN_DETAILS_URL;
                    url += "?id=" + planId + "&regId=" + userId;
                    String result = OkHttpRequester.getStringFromServer(url, true);

                    map = JSON.parseObject(JSON.parseObject(result).getString("data"), HashMap.class);
                    PlanInfo plan = new PlanInfo();
                    plan.setId(planId);
                    if (map.get("lotteryType") != null)
                        plan.setCaiType(map.get("lotteryType").toString());
                    plan.setUserId(userId);
                    if (map.get("nickName") != null)
                        plan.setNickName(map.get("nickName").toString());
                    if (map.get("planName") != null)
                        plan.setPlanName(map.get("planName").toString());
                    if (map.get("visType") != null)
                        plan.setVisType(Integer.parseInt(map.get("visType").toString()));
                    if (map.get("beginOrder") != null)
                        plan.setBeginOrder(map.get("beginOrder").toString());
                    if (map.get("endOrder") != null)
                        plan.setEndOrder(map.get("endOrder").toString());
                    if (map.get("currentOrder") != null)
                        plan.setCurrentOrder((int) Math.round(Double.parseDouble(map.get("currentOrder").toString())));
                    if (map.get("totalCount") != null)
                        plan.setOrderTotal(Integer.parseInt(map.get("totalCount").toString()));
                    if (map.get("multiples") != null)
                        plan.setMultiples(map.get("multiples").toString());
                    if (map.get("planType") != null)
                        plan.setPlanType((int) Math.round(Double.parseDouble(map.get("planType").toString())));
                    else
                        plan.setPlanType(1);
                    if (map.get("zhuCount") != null)
                        plan.setZhuCount(Integer.parseInt(map.get("zhuCount").toString()));
                    if (map.get("finishTime") != null)
                        plan.setFinishTime(map.get("finishTime").toString());
                    if (map.get("totalCost") != null)
                        plan.setTotalCost(Integer.parseInt(map.get("totalCost").toString()));
                    if (map.get("checkPrice") != null)
                        plan.setCheckPrice(Integer.parseInt(map.get("checkPrice").toString()));
                    if (map.get("minIncome") != null)
                        plan.setMinIncome(Integer.parseInt(map.get("minIncome").toString()));
                    if (map.get("planContent") != null)
                        plan.setPlanContent(map.get("planContent").toString());
                    if (map.get("state") != null)
                        plan.setState(Integer.parseInt(map.get("state").toString()));

                    if (map.get("planContent") != null) {
                        plan.setBuy(true);
                        plan.setPlanContent(map.get("planContent").toString());
                    }
                    handlerSendMsg(handler, HandlerWhat.GET_PLANINFO_SUCCESS, plan);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_PLANINFO_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void getTopOneByPlanId(final Handler handler, final PlanInfo planInfo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PlanDetails planDetails = new PlanDetails();
                    planDetails.setId(Integer.parseInt(planInfo.getId()));
                    planDetails.setBonus(planInfo.getMinIncome());
                    planDetails.setLotteryType(planInfo.getCaiType());
                    planDetails.setCost(planInfo.getTotalCost());
                    planDetails.setState(planInfo.getState().toString());
                    planDetails.setPlanContent(planInfo.getPlanContent());
                    handlerSendMsg(handler, HandlerWhat.GET_TOPONEBYPLANID_SUCCESS, planDetails);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_TOPONEBYPLANID_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void newPlanList(final Handler handler, final String caiType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String strPlan = OkHttpRequester.getStringFromServer(InterfaceConsts.PLAN_ALL_URL, false);
                    List<ResultBean.LotteryPlan> lotteryPlanList = JSON.parseArray(JSON.parseObject(strPlan).getString("data"), ResultBean.LotteryPlan.class);

                    handlerSendBundleMsg(handler, HandlerWhat.GET_NEWPLANLIST_SUCCESS, ReturnStatus.KEY_GET_NEWPLAN_LIST, lotteryPlanList);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_NEWPLANLIST_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void newPlanListByNickName(final Handler handler, final String caiType, final String nickName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = InterfaceConsts.PLAN_ALL_URL;
                    if (!caiType.equals("全部方案"))
                        url += "?province=" + caiType;
                    if (nickName != null && !nickName.equals("")) {
                        if (url.indexOf("?") > 0)
                            url += "&search=" + nickName;
                        else
                            url += "?search=" + nickName;
                    }
                    String strPlan = OkHttpRequester.getStringFromServer(url, false);
                    List<ResultBean.LotteryPlan> lotteryPlanList = JSON.parseArray(JSON.parseObject(strPlan).getString("data"), ResultBean.LotteryPlan.class);

                    handlerSendBundleMsg(handler, HandlerWhat.GET_NEWPLANLISTBYNAME_SUCCESS, ReturnStatus.KEY_GET_NEWPLANBYNAME_LIST, lotteryPlanList);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_NEWPLANLISTBYNAME_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void addOwnPlan(final Handler handler, final String planId, final String checkPrice) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    String url = InterfaceConsts.PLAY_PLAN_URL + "?planId=" + planId;
                    String result = OkHttpRequester.postStringFromServer(url, new FormBody.Builder().build(), true);
                    map = JSON.parseObject(result);
                    if (map.get("code").equals("1")) {
                        UserInfo u = App.getInstance().getUser();
                        u.setMoney(String.valueOf(Double.parseDouble(u.getMoney()) - Double.parseDouble(checkPrice)));
                        handlerSendMsg(handler, HandlerWhat.ADD_OWNPLAN_SUCCESS, map.get("data"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.ADD_OWNPLAN_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.ADD_OWNPLAN_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void getCoinChangeRecordTop80(final Handler handler, final String uid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = OkHttpRequester.getStringFromServer(InterfaceConsts.RECORD_URL + "?page=1&limit=1000", true);
                    String jsonObject = JSON.parseObject(result).getString("data");
                    List<CoinChangeRecord> list = JSON.parseArray(JSON.parseObject(jsonObject).getString("records"), CoinChangeRecord.class);
                    handlerSendBundleMsg(handler, HandlerWhat.GET_COINCHANGE_TOP80_SUCCESS, ReturnStatus.KEY_GET_COIN_LIST, list);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_COINCHANGE_TOP80_FALIURE, JSON.parseObject(result).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void getBalanceChangeRecordTop80(final Handler handler, final String uid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = OkHttpRequester.getStringFromServer(InterfaceConsts.CURRENCY_RECORD_URL + "?page=1&limit=1000", true);
                    String jsonObject = JSON.parseObject(result).getString("data");
                    List<BalanceChangeRecord> list = JSON.parseArray(JSON.parseObject(jsonObject).getString("records"), BalanceChangeRecord.class);
                    handlerSendBundleMsg(handler, HandlerWhat.GET_BALANCECHANGE_TOP80_SUCCESS, ReturnStatus.KEY_GET_BALABCE_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_BALANCECHANGE_TOP80_FALIURE, JSON.parseObject(result).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void getJobPriceList(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    result = OkHttpRequester.getStringFromServer(InterfaceConsts.VIP_ALL_URL, false);
                    List<JobPrice> list = JSON.parseArray(JSON.parseObject(result).getString("data"), JobPrice.class);
                    handlerSendBundleMsg(handler, HandlerWhat.GET_JOBPRICE_LIST_SUCCESS, ReturnStatus.KEY_GET_JOBPRICE_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_JOBPRICE_LIST_FALIURE, JSON.parseObject(result).getString("message"));
                }
            }
        }).start();
    }

    @Override
    public void getAllJobList(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetAllJobList, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_ALLJOB_LIST_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<JobPlanner> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSON.parseArray(result);
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JobPlanner jobPlannero = new Gson().fromJson(jsonArray.get(i).toString(), JobPlanner.class);
                            list.add(jobPlannero);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_ALLJOB_LIST_SUCCESS, ReturnStatus.KEY_GET_ALLJOB_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_ALLJOB_LIST_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getSingleJobList(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetSingleJobList, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_SINGLEJOB_LIST_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<JobPlanner> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSON.parseArray(result);
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JobPlanner jobPlannero = new Gson().fromJson(jsonArray.get(i).toString(), JobPlanner.class);
                            list.add(jobPlannero);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_SINGLEJOB_LIST_SUCCESS, ReturnStatus.KEY_GET_SINGLEJOB_LIST, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_SINGLEJOB_LIST_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getAllJobDistribute(final Handler handler, final String userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetAllJobDistribute, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("userId", userId);
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_ALLJOB_DIS_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<JobDistribute> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSON.parseArray(result);
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JobDistribute jobDistribute = new Gson().fromJson(jsonArray.get(i).toString(), JobDistribute.class);
                            list.add(jobDistribute);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_ALLJOB_DIS_SUCCESS, ReturnStatus.KEY_GET_ALLJOB_DIS, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_ALLJOB_DIS_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getSingleJobDistribute(final Handler handler, final String userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.GetSingleJobDistribute, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("userId", userId);
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.GET_SINGLEJOB_DIS_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    List<JobDistribute> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(result)) {
                        JSONArray jsonArray = JSON.parseArray(result);
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JobDistribute jobDistribute = new Gson().fromJson(jsonArray.get(i).toString(), JobDistribute.class);
                            list.add(jobDistribute);
                        }
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_SINGLEJOB_DIS_SUCCESS, ReturnStatus.KEY_GET_SINGLEJOB_DIS, list);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_SINGLEJOB_DIS_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void payAllJob(final Handler handler, final JobPrice jobPrice) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    RequestBody requestBody = new FormBody.Builder().build();
                    String result = OkHttpRequester.postStringFromServer(InterfaceConsts.PLAY_VIP_URL + "?id=" + jobPrice.getId(), requestBody, true);
                    map = JSONObject.parseObject(result);
                    if (map.get("code").equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.PAY_ALLJOB_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.PAY_ALLJOB_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.PAY_ALLJOB_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void paySingleJob(final Handler handler, final String userName, final int dayType, final String planUserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.PaySingleJob, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("userName", userName);
                            soapObject.addProperty("dayType", dayType);
                            soapObject.addProperty("planUserId", planUserId);
                            return soapObject;
                        }
                    });
                    if (json.getIntValue("ztdm") == 0) {
                        handlerSendMsg(handler, HandlerWhat.PAY_SINGLEJOB_TIMEOUT, ReturnStatus.DESCRIPT_CONNECT_TIMEOUT);
                        return;
                    }
                    String result = json.getString("result");
                    if (result.equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.PAY_SINGLEJOB_SUCCESS, "购买成功");
                    } else if (result.equals("-1")) {
                        handlerSendMsg(handler, HandlerWhat.PAY_SINGLEJOB_FALIURE, "失败请联系管理员");
                    } else if (result.equals("-2")) {
                        handlerSendMsg(handler, HandlerWhat.PAY_SINGLEJOB_FALIURE, "包月类型不存在");
                    } else if (result.equals("-3")) {
                        handlerSendMsg(handler, HandlerWhat.PAY_SINGLEJOB_FALIURE, " 余额不足");
                    }
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.PAY_SINGLEJOB_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void coinConversion(final Handler handler, final String uerId, final String price) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    RequestBody requestBody = new FormBody.Builder().build();
                    String url = InterfaceConsts.REDEEM_POINTS_URL + "?number=" + price + "&type=1";
                    String result = OkHttpRequester.postStringFromServer(url, requestBody, true);
                    map = JSON.parseObject(result);
                    if (map.get("code").equals("1")) {
                        UserInfo u = App.getInstance().getUser();
                        u.setMoney(String.valueOf(Double.parseDouble(u.getMoney()) - Double.parseDouble(price)));
                        u.setCoin(String.valueOf(Double.parseDouble(u.getCoin()) + Double.parseDouble(price)*Integer.parseInt(u.getIntegral())));
                        UserInfo u2 = App.getInstance().getUser();
                        handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void updateNewsStats(Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               /* try {
                    RequestBody requestBody = new FormBody.Builder().build();
                    String url = InterfaceConsts.REDEEM_POINTS_URL + "?number=" + price;
                    String result = OkHttpRequester.postStringFromServer(url, requestBody, true);
                    Map map = JSON.parseObject(result);
                    if (map.get("code").equals(1)) {
                        UserInfo u = App.getInstance().getUser();
                        u.setBalance(String.valueOf((Double.parseDouble(u.getMoney()) - Double.parseDouble(price))));
                        App.getInstance().setUser(u);
                        handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }*/
            }
        }).start();
    }

    @Override
    public void moneyConversion(final Handler handler, final String uerId, final String price) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody requestBody = new FormBody.Builder().build();
                    String url = InterfaceConsts.REDEEM_POINTS_URL + "?number=" + price + "&type=2";
                    String result = OkHttpRequester.postStringFromServer(url, requestBody, true);
                    Map map = JSON.parseObject(result);
                    if (map.get("code").equals("1")) {
                        UserInfo u = App.getInstance().getUser();
                        u.setBalance(String.valueOf((Double.parseDouble(u.getBalance()) - Double.parseDouble(price))));
                        u.setMoney(String.valueOf(Double.parseDouble(u.getMoney()) + Double.parseDouble(price)*Integer.parseInt(u.getGame())));
                        handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_COINCONVERSION_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void transferBalance(final Handler handler, final String originalUser, final String targetUser, final String amount) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    RequestBody requestBody = new FormBody.Builder().build();
                    String url = InterfaceConsts.EXCHANGE_INTEGRAL_URL + "?number=" + amount + "&phone=" + targetUser;
                    String result = OkHttpRequester.postStringFromServer(url, requestBody, true);
                    map = JSON.parseObject(result);
                    if (map.get("code").equals("1")) {
                        UserInfo userInfo = App.getInstance().getUser();
                        userInfo.setBalance(String.valueOf((Double.parseDouble(userInfo.getMoney()) - Double.parseDouble(amount))));
                        App.getInstance().setUser(userInfo);
                        handlerSendMsg(handler, HandlerWhat.TRANSFER_BALANCE_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.TRANSFER_BALANCE_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.TRANSFER_BALANCE_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void getLuckyNumber(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handlerSendMsg(handler, HandlerWhat.GET_LUCKYNUM_SUCCESS, App.getInstance().getLottery());
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_LUCKYNUM_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();

    }

    @Override
    public void getLuckyNumberList(final Handler handler, final String caiType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resultObject = "";
                try {
                    String caizhong = "gd";
                    if (caiType != null && !caiType.equals(""))
                        caizhong = CaiUtil.getCaiBm(caiType);

                    String url = InterfaceConsts.TREND_ALL_URL + "?province=" + caizhong;
                    resultObject = OkHttpRequester.getStringFromServer(url, false);
                    JSONArray result = JSON.parseArray(JSON.parseObject(resultObject).get("data").toString());
                    KaiJiang_11x5Dao kaiJiang_11x5Dao = new KaiJiang_11x5Dao();

                    getKjData(caizhong, result, kaiJiang_11x5Dao);

                    List<KaiJiang_11x5> lists = kaiJiang_11x5Dao.queryKaijiangList(caizhong);
                    if (lists != null && lists.size() > 0) {
                        Collections.sort(lists);
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_KAIJIANG_SUCCESS, ReturnStatus.KEY_GET_KAIJIANG_LIST, lists);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_KAIJIANG_FALIURE, JSON.parseObject(resultObject).getString("message"));
                }
            }
        }).start();
    }


    private void getKjData(String caizhong, JSONArray jsonArray, KaiJiang_11x5Dao kaiJiang_11x5Dao) {
        int size = jsonArray.size();

        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            KaiJiang_11x5 kaiJiang_11x5 = new KaiJiang_11x5(jsonObject.getString("id"), jsonObject.getString("code"));
            boolean exist = kaiJiang_11x5Dao.isExistOrderNum(caizhong, kaiJiang_11x5.getOrderNum());
            if (exist) {
                continue;
            }
            kaiJiang_11x5.setCaiZhong(caizhong);
            kaiJiang_11x5.initValue();
            if (i == 0) {
                String orderNum = kaiJiang_11x5.getOrderNum();
                int orderNo = Integer.parseInt(orderNum.substring(orderNum.length() - 2, orderNum.length()));
                KaiJiang_11x5 lastKaiJiang_11x5;
                if (orderNo == 1 || orderNo == 0) {
                    String zuotian = DateUtil.getZuotianDate().replace("-", "");
                    String qishu = zuotian.substring(2, zuotian.length()) + String.valueOf(CaiUtil.getCaiCount(caizhong));
                    lastKaiJiang_11x5 = kaiJiang_11x5Dao.queryKaiJiang11x5(caizhong, qishu);
                } else {
                    lastKaiJiang_11x5 = kaiJiang_11x5Dao.queryKaiJiang11x5(caizhong, String.valueOf(Integer.parseInt(orderNum) - 1));
                }
                if (lastKaiJiang_11x5 == null) {
                    kaiJiang_11x5.initZouShi(null, null);
                    kaiJiang_11x5Dao.createOrUpdate(kaiJiang_11x5);
                    continue;
                }
                kaiJiang_11x5.initChonghaoshu(lastKaiJiang_11x5.getNum());
                kaiJiang_11x5.initLinHaoGeshu(lastKaiJiang_11x5.getLuckyNumber());
                kaiJiang_11x5.initZouShi(lastKaiJiang_11x5.getZouShi(), lastKaiJiang_11x5.getNum());
                kaiJiang_11x5Dao.createOrUpdate(kaiJiang_11x5);
            } else {
                JSONObject jsonLast = jsonArray.getJSONObject(i - 1);
                KaiJiang_11x5 lastKaiJiang_11x5 = new KaiJiang_11x5(jsonLast.getString("id"), jsonLast.getString("code"));
                kaiJiang_11x5.initChonghaoshu(lastKaiJiang_11x5.getNum());
                kaiJiang_11x5.initLinHaoGeshu(lastKaiJiang_11x5.getLuckyNumber());
                kaiJiang_11x5Dao.addOrUpdate(kaiJiang_11x5);
            }
        }
        return;
    }

    @Override
    public void getHomeData(final Handler handler, final String userId, final String caiType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String strPlan = null;
                try {
                    ResultBean resultBean = new ResultBean();
                    RequestBody requestBody = new FormBody.Builder().build();
                    String resultImg = OkHttpRequester.postStringFromServer(InterfaceConsts.MANAGE_IMAGE_URL, requestBody, false);
                    List<ImageInfo> imgLists = JSON.parseArray(JSON.parseObject(resultImg).getString("data"), ImageInfo.class);
                    resultBean.setImgLists(imgLists);

                    /*List<NoticeInfo> noticeLists = JSON.parseArray(JSON.parseObject(resultImg).getString("data"), NoticeInfo.class);
                    resultBean.setNoticeLists(noticeLists);

                    ResultBean.Channel homeChannel1 = resultBean.new Channel(1, R.mipmap.ic_home_01, "盈利季度榜"); //
                    ResultBean.Channel homeChannel2 = resultBean.new Channel(2, R.mipmap.ic_home_02, "盈利月榜");
                    ResultBean.Channel homeChannel3 = resultBean.new Channel(3, R.mipmap.ic_home_03, "盈利周榜");
                    ResultBean.Channel homeChannel4 = resultBean.new Channel(4, R.mipmap.ic_home_lxkf, "联系客服");
                    List<ResultBean.Channel> homeChannelLists = new ArrayList<>();
                    homeChannelLists.add(homeChannel1);
                    homeChannelLists.add(homeChannel2);
                    homeChannelLists.add(homeChannel3);
                    homeChannelLists.add(homeChannel4);
                    resultBean.setChannelLists(homeChannelLists);*/

                    strPlan = OkHttpRequester.getStringFromServer(InterfaceConsts.PLAN_ALL_URL, false);
                    List<ResultBean.LotteryPlan> lotteryPlanList = JSON.parseArray(JSON.parseObject(strPlan).getString("data"), ResultBean.LotteryPlan.class);
                    resultBean.setLotteryPlanList(lotteryPlanList);

                    handlerSendMsg(handler, HandlerWhat.GET_HOMEDATA_SUCCESS, resultBean);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_HOMEDATA_FALIURE, JSON.parseObject(strPlan).getString("message"));
                }
            }

            private HttpClient getHttpClient() {
                BasicHttpParams httpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
                HttpConnectionParams.setSoTimeout(httpParams, 10000);
                HttpClient client = new DefaultHttpClient(httpParams);
                return client;
            }
        }).start();
    }

    @Override
    public void getJisuanSsglLists(final Handler handler, final List<SuoShuiFilter> lists) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> baseNums = new ArrayList<>();//基础号码
                List<String> danzu1 = new ArrayList<>();//胆组1
                List<Integer> danzu_chu1 = new ArrayList<>();//胆组1
                List<String> danzu2 = new ArrayList<>();//胆组2
                List<Integer> danzu_chu2 = new ArrayList<>();//胆组2
                List<String> danzu3 = new ArrayList<>();//胆组3
                List<Integer> danzu_chu3 = new ArrayList<>();//胆组3
                List<String> danzu4 = new ArrayList<>();//胆组4
                List<Integer> danzu_chu4 = new ArrayList<>();//胆组4
                List<String> danzu5 = new ArrayList<>();//胆组5
                List<Integer> danzu_chu5 = new ArrayList<>();//胆组5

                List<Integer> hezhi = new ArrayList<>();//和值
                List<Integer> hewei = new ArrayList<>();//和尾
                List<Integer> kuadu = new ArrayList<>();//跨度
                List<String> daxiaobi = new ArrayList<>();//大小比
                List<String> zhihebi = new ArrayList<>();//质合比
                List<String> jioubi = new ArrayList<>();//奇偶比
                List<Integer> linhaogeshu = new ArrayList<>();//邻号个数
                List<Integer> chonghaogeshu = new ArrayList<>();//重号个数
                List<Integer> pingjunzhi = new ArrayList<>();//平均值;
                List<String> lianhao = new ArrayList<>();//连号
                List<String> lubi = new ArrayList<>();//012路比
                SuoShuiFilter suoShuiFilter0 = lists.get(0);
                Map<Integer, String> map0 = suoShuiFilter0.getMap();
                for (String str : map0.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        baseNums.add(str.trim());
                    }
                }
                if (baseNums.size() < 5) {
                    handlerSendMsg(handler, HandlerWhat.GET_SSGLLIST_FALIURE, "请至少选择五个基础号码！");
                    return;
                }
                SuoShuiFilter suoShuiFilter1 = lists.get(1);
                Map<Integer, String> map1 = suoShuiFilter1.getMap();
                for (int key : map1.keySet()) {
                    String value = map1.get(key);
                    if (TextUtils.isEmpty(value)) {
                        continue;
                    }
                    if (key < 12) {
                        danzu1.add(value.trim());
                    } else {
                        danzu_chu1.add(Integer.parseInt(value.trim()));
                    }
                }
                SuoShuiFilter suoShuiFilter2 = lists.get(2);
                Map<Integer, String> map2 = suoShuiFilter2.getMap();
                for (int key : map2.keySet()) {
                    String value = map2.get(key);
                    if (TextUtils.isEmpty(value)) {
                        continue;
                    }
                    if (key < 12) {
                        danzu2.add(value.trim());
                    } else {
                        danzu_chu2.add(Integer.parseInt(value.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter3 = lists.get(3);
                Map<Integer, String> map3 = suoShuiFilter3.getMap();
                for (int key : map3.keySet()) {
                    String value = map3.get(key);
                    if (TextUtils.isEmpty(value)) {
                        continue;
                    }
                    if (key < 12) {
                        danzu3.add(value.trim());
                    } else {
                        danzu_chu3.add(Integer.parseInt(value.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter4 = lists.get(4);
                Map<Integer, String> map4 = suoShuiFilter4.getMap();
                for (int key : map4.keySet()) {
                    String value = map4.get(key);
                    if (TextUtils.isEmpty(value)) {
                        continue;
                    }
                    if (key < 12) {
                        danzu4.add(value.trim());
                    } else {
                        danzu_chu4.add(Integer.parseInt(value.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter5 = lists.get(5);
                Map<Integer, String> map5 = suoShuiFilter5.getMap();
                for (int key : map5.keySet()) {
                    String value = map5.get(key);
                    if (TextUtils.isEmpty(value)) {
                        continue;
                    }
                    if (key < 12) {
                        danzu5.add(value.trim());
                    } else {
                        danzu_chu5.add(Integer.parseInt(value.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter6 = lists.get(6);
                Map<Integer, String> map6 = suoShuiFilter6.getMap();
                for (String str : map6.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        hezhi.add(Integer.parseInt(str.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter7 = lists.get(7);
                Map<Integer, String> map7 = suoShuiFilter7.getMap();
                for (String str : map7.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        hewei.add(Integer.parseInt(str.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter8 = lists.get(8);
                Map<Integer, String> map8 = suoShuiFilter8.getMap();
                for (String str : map8.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        kuadu.add(Integer.parseInt(str.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter12 = lists.get(9);
                Map<Integer, String> map12 = suoShuiFilter12.getMap();
                for (String str : map12.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        daxiaobi.add(str.trim());
                    }
                }

                SuoShuiFilter suoShuiFilter9 = lists.get(10);
                Map<Integer, String> map9 = suoShuiFilter9.getMap();
                for (String str : map9.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        zhihebi.add(str.trim());
                    }
                }

                SuoShuiFilter suoShuiFilter16 = lists.get(11);
                Map<Integer, String> map16 = suoShuiFilter16.getMap();
                for (String str : map16.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        jioubi.add(str.trim());
                    }
                }

                SuoShuiFilter suoShuiFilter17 = lists.get(12);
                Map<Integer, String> map17 = suoShuiFilter17.getMap();
                for (String str : map17.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        linhaogeshu.add(Integer.parseInt(str.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter10 = lists.get(13);
                Map<Integer, String> map10 = suoShuiFilter10.getMap();
                for (String str : map10.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        chonghaogeshu.add(Integer.parseInt(str.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter11 = lists.get(14);
                Map<Integer, String> map11 = suoShuiFilter11.getMap();
                for (String str : map11.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        pingjunzhi.add(Integer.parseInt(str.trim()));
                    }
                }

                SuoShuiFilter suoShuiFilter13 = lists.get(15);
                Map<Integer, String> map13 = suoShuiFilter13.getMap();
                String qishu = map13.get(0);
                String cishu = map13.get(1);

                SuoShuiFilter suoShuiFilter14 = lists.get(16);
                Map<Integer, String> map14 = suoShuiFilter14.getMap();
                for (String str : map14.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        lianhao.add(str.trim());
                    }
                }

                SuoShuiFilter suoShuiFilter15 = lists.get(17);
                Map<Integer, String> map15 = suoShuiFilter15.getMap();
                for (String str : map15.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        lubi.add(str.trim());
                    }
                }

                KaiJiang_11x5Dao kaiJiang_11x5Dao = new KaiJiang_11x5Dao();
                if (linhaogeshu.size() > 0 || chonghaogeshu.size() > 0 || (!TextUtils.isEmpty(qishu) && !TextUtils.isEmpty(cishu))) {
                    List<KaiJiang_11x5> lis = kaiJiang_11x5Dao.queryForAll();
                    if (lis == null || lis.size() <= 3) {
                        try {
                            final String caiType = App.getInstance().getLottery().getCaiType();
                            String caizhong = CaiUtil.getCaiBm(caiType);
                            String url = InterfaceConsts.TREND_ALL_URL + "?province=" + caizhong;
                            String resultObject = OkHttpRequester.getStringFromServer(url, false);
                            JSONArray result = JSON.parseArray(JSON.parseObject(resultObject).get("data").toString());
                            getKjData(caizhong, result, kaiJiang_11x5Dao);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                String upNum = "";
                KaiJiang_11x5 kaiJiang_11x5 = kaiJiang_11x5Dao.queryCurrentKaijiang();
                if (kaiJiang_11x5 != null) {
                    upNum = kaiJiang_11x5.getLuckyNumber();
                }
                List<KaiJiang_11x5> glLists = new ArrayList<>();

                //缩水过滤
                ArrayList<String> haomaGuolvLists = new ArrayList<>();
                List<String> haomaLists = CaiUtil.getRen5haoma();
                for (String str : haomaLists) {
                    String[] num = str.split(" ");
                    //验证基础号码
                    String[] baseNum = baseNums.toArray(new String[baseNums.size()]);
                    Arrays.sort(baseNum);
                    boolean jichuhaoma = Ren5ExcludeUtil.BaseNumVerify(num, baseNum);
                    if (!jichuhaoma) {
                        continue;
                    }
                    //验证胆组1
                    String[] szdanzu1 = danzu1.toArray(new String[danzu1.size()]);
                    Arrays.sort(szdanzu1);
                    if (danzu1.size() > 0 && danzu_chu1.size() > 0) {
                        boolean danzuyi = Ren5ExcludeUtil.DanZuVerify(num, szdanzu1, danzu_chu1.toArray(new Integer[danzu_chu1.size()]));
                        if (!danzuyi) {
                            continue;
                        }
                    }
                    //验证胆组2
                    String[] szdanzu2 = danzu2.toArray(new String[danzu2.size()]);
                    Arrays.sort(szdanzu2);
                    if (danzu2.size() > 0 && danzu_chu2.size() > 0) {
                        boolean danzuer = Ren5ExcludeUtil.DanZuVerify(num, szdanzu2, danzu_chu2.toArray(new Integer[danzu_chu2.size()]));
                        if (!danzuer) {
                            continue;
                        }
                    }
                    //验证胆组3
                    String[] szdanzu3 = danzu3.toArray(new String[danzu3.size()]);
                    Arrays.sort(szdanzu3);
                    if (danzu3.size() > 0 && danzu_chu3.size() > 0) {
                        boolean danzusan = Ren5ExcludeUtil.DanZuVerify(num, szdanzu3, danzu_chu3.toArray(new Integer[danzu_chu3.size()]));
                        if (!danzusan) {
                            continue;
                        }
                    }
                    //验证胆组4
                    String[] szdanzu4 = danzu4.toArray(new String[danzu4.size()]);
                    Arrays.sort(szdanzu4);
                    if (danzu4.size() > 0 && danzu_chu4.size() > 0) {
                        boolean danzusi = Ren5ExcludeUtil.DanZuVerify(num, szdanzu4, danzu_chu4.toArray(new Integer[danzu_chu4.size()]));
                        if (!danzusi) {
                            continue;
                        }
                    }
                    //验证胆组5
                    String[] szdanzu5 = danzu5.toArray(new String[danzu5.size()]);
                    Arrays.sort(szdanzu5);
                    if (danzu5.size() > 0 && danzu_chu5.size() > 0) {
                        boolean danzuwu = Ren5ExcludeUtil.DanZuVerify(num, szdanzu5, danzu_chu5.toArray(new Integer[danzu_chu5.size()]));
                        if (!danzuwu) {
                            continue;
                        }
                    }
                    //验证和值
                    if (hezhi.size() > 0) {
                        boolean yzhezhi = Ren5ExcludeUtil.SumValueVerify(num, hezhi.toArray(new Integer[hezhi.size()]));
                        if (!yzhezhi) {
                            continue;
                        }
                    }
                    //验证和尾
                    if (hewei.size() > 0) {
                        boolean yzhw = Ren5ExcludeUtil.HeWeiValueVerify(num, hewei.toArray(new Integer[hewei.size()]));
                        if (!yzhw) {
                            continue;
                        }
                    }
                    //验证跨度
                    if (kuadu.size() > 0) {
                        boolean yzkuadu = Ren5ExcludeUtil.KuaduVerfy(num, kuadu.toArray(new Integer[kuadu.size()]));
                        if (!yzkuadu) {
                            continue;
                        }
                    }
                    //验证奇偶比
                    if (jioubi.size() > 0) {
                        boolean yzjioubi = Ren5ExcludeUtil.JiShuVerify(num, jioubi.toArray(new String[jioubi.size()]));
                        if (!yzjioubi) {
                            continue;
                        }
                    }
                    //验证邻号个数
                    if (linhaogeshu.size() > 0) {
                        String upNumLin = Ren5ExcludeUtil.jisuanLinhao(upNum);
                        boolean yzlinhaogeshu = Ren5ExcludeUtil.LinHaoCountVerify(num, linhaogeshu.toArray(new Integer[linhaogeshu.size()]), upNumLin);
                        if (!yzlinhaogeshu) {
                            continue;
                        }
                    }
                    //验证质合比
                    if (zhihebi.size() > 0) {
                        boolean yzzhihebi = Ren5ExcludeUtil.ZhiHaoVerify(num, zhihebi.toArray(new String[zhihebi.size()]));
                        if (!yzzhihebi) {
                            continue;
                        }
                    }
                    //验证重号个数
                    if (chonghaogeshu.size() > 0) {
                        boolean yzchonghaogeshu = Ren5ExcludeUtil.ChongHaoVerify(num, chonghaogeshu.toArray(new Integer[chonghaogeshu.size()]), upNum);
                        if (!yzchonghaogeshu) {
                            continue;
                        }
                    }
                    //验证平均值
                    if (pingjunzhi.size() > 0) {
                        boolean yzpingjunzhi = Ren5ExcludeUtil.AvgValueVerify(num, pingjunzhi.toArray(new Integer[pingjunzhi.size()]));
                        if (!yzpingjunzhi) {
                            continue;
                        }
                    }
                    //验证大小比
                    if (daxiaobi.size() > 0) {
                        boolean yzdaxiaobi = Ren5ExcludeUtil.DaHaoVerify(num, daxiaobi.toArray(new String[daxiaobi.size()]));
                        if (!yzdaxiaobi) {
                            continue;
                        }
                    }
                    //已出号排除
                    if (!TextUtils.isEmpty(qishu) && !TextUtils.isEmpty(cishu)) {
                        if (glLists == null || glLists.size() == 0) {
                            glLists = kaiJiang_11x5Dao.getKjByQIshu(qishu);
                        }
                        //boolean yzYichuhaopaichu1 = kaiJiang_11x5Dao.yichuhaoPaichu(str, qishu, cishu);
                        boolean yzYichuhaopaichu = Ren5ExcludeUtil.yichuhaoPaichu(str, cishu, glLists);
                        if (!yzYichuhaopaichu) {
                            continue;
                        }
                    }
                    //连号过滤
                    if (lianhao.size() > 0) {
                        boolean yzlianhaoguolv = Ren5ExcludeUtil.LianHaoVerify(num, lianhao.toArray(new String[lianhao.size()]));
                        if (!yzlianhaoguolv) {
                            continue;
                        }
                    }
                    //0:1:2路比
                    if (lubi.size() > 0) {
                        boolean yzlubi = Ren5ExcludeUtil.Ratio012Verify(num, lubi.toArray(new String[lubi.size()]));
                        if (!yzlubi) {
                            continue;
                        }
                    }
                    haomaGuolvLists.add(str);
                }
                if (haomaGuolvLists.size() < 1) {
                    handlerSendMsg(handler, HandlerWhat.GET_SSGLLIST_FALIURE, "没有满足条件的号码！");
                    return;
                }
                handlerSendBundleMsg(handler, HandlerWhat.GET_SSGLLIST_SUCCESS, ReturnStatus.KEY_SSGL_LIST, haomaGuolvLists);
            }
        }).start();
    }

    /**
     * @param handler      消息处理
     * @param beginCount   倍数
     * @param danBeiCount  单倍注数
     * @param sliderBonus  积分奖励
     * @param minRate      盈利率
     * @param planPostCout 即将开奖的期数
     */
    @Override
    public void createPlan(final Handler handler, final double beginCount, final double danBeiCount, final double sliderBonus, final double minRate, final int planPostCout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int qiCount = Integer.parseInt(App.getInstance().getLottery().getCount());
                    List<PlanBeiInfo> planBeiInfoList = new ArrayList<>();
                    //校验利率
                    double chengBen = danBeiCount * 2;
                    //盈利/成本
                    if (chengBen >= sliderBonus) {
                        handlerSendMsg(handler, HandlerWhat.GET_FASCLIST_FALIURE, "当前方案不会有盈利！");
                        return;
                    }
                    //double d = (((double)sliderBonus - chengBen) / chengBen) * 100;
                    if ((((double) sliderBonus - chengBen) / chengBen) * 100 < minRate) {
                        handlerSendMsg(handler, HandlerWhat.GET_FASCLIST_FALIURE, "当前方案设置的盈利率过高！");
                        return;
                    }
                    double zqBeishu = beginCount;//这期的倍数
                    double zcLeij = beginCount * (danBeiCount * 2);//这期累计成本
//                    String tobegin = CaiUtil.getNextPeriod();
                    String tobegin = String.valueOf(Integer.parseInt(App.getInstance().getLottery().getCaiQishu()) + 1);
                    DecimalFormat df = new DecimalFormat("0.00");
                    for (int i = 1; i <= planPostCout; i++) {
                        PlanBeiInfo plan = new PlanBeiInfo();
                        //计算当前的倍数 和成本 是否达到盈利率 达不到就增加 成本 达到就添加
                        if (i > 1) {
                            zcLeij = zcLeij + (zqBeishu * danBeiCount * 2);//加上当前倍数
                        }
                        //double i1 = (sliderBonus * zqBeishu - zcLeij) / zcLeij;
                        while (((sliderBonus * zqBeishu - zcLeij) / zcLeij) * 100 < minRate) {
                            zqBeishu++;
                            zcLeij = zcLeij + chengBen;
                            if (zcLeij >= 99999 || zcLeij < 0) {
                                handlerSendMsg(handler, HandlerWhat.GET_FASCLIST_FALIURE, "成本过高或计算异常！请修改方案后重新生成!");
                                return;
                            }
                        }
                        if (i > 1) {
                            if (Integer.parseInt(tobegin.substring(6, tobegin.length())) < qiCount) {
                                int q = Integer.parseInt(tobegin.substring(6, tobegin.length())) + 1;
                                if (q < 10) {
                                    tobegin = tobegin.substring(0, 6) + "0" + q;
                                } else {
                                    tobegin = tobegin.substring(0, 6) + q;
                                }
                            } else {
                                String riqi = DateUtil.getAfterDate(i / qiCount).replaceAll("-", "");
                                String nextQ = riqi.substring(2, riqi.length());
                                int mq = i - qiCount * (i / qiCount);
                                if (mq < 10) {
                                    tobegin = nextQ + "0" + mq;
                                } else {
                                    tobegin = nextQ + mq;
                                }
                            }
                        }
                        plan.setIssue(tobegin);
                        plan.setSlNub(i);
                        plan.setCurrentAmount(BigDecimal.valueOf(zqBeishu * danBeiCount * 2));
                        plan.setTotalAmount(BigDecimal.valueOf(zcLeij));
                        plan.setMultiple((int) zqBeishu);
                        plan.setProfit(BigDecimal.valueOf((sliderBonus * zqBeishu) - zcLeij));
                        Double ddd = Double.parseDouble(df.format(Double.parseDouble(plan.getProfit().toString()) * 100 / zcLeij));
                        plan.setProfitRate(ddd.toString());
                        planBeiInfoList.add(plan);
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_FASCLIST_SUCCESS, ReturnStatus.KEY_GET_FASC_LIST, planBeiInfoList);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_FASCLIST_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    //final String caiType, final String userId, final int planType, final int danBeiCount, final String sliderBonus, final double CheckPrice

    @Override
    public void okPlanPost(final Handler handler, final JSONObject json, final List<PlanBeiInfo> planBeiInfos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = null;
                try {
                    String planId = StrUtil.createRandomGUID().toLowerCase();
                    //详情内容时间限制
                    //开奖前1分钟停止详情内容
                    String nextQ = CaiUtil.getNextPeriod();
                    PlanInfo plan = new PlanInfo();
                    plan.setId(planId);
                    plan.setCaiType(json.getString("caiType"));
                    final String userId = json.getString("userId");
                    plan.setUserId(userId);
                    plan.setBeginOrder(nextQ);
                    plan.setPlanType(json.getInteger("planType"));
                    plan.setZhuCount(json.getInteger("danBeiCount"));
                    int sliderBonus = json.getInteger("sliderBonus");
                    plan.setMinIncome(sliderBonus);
                    plan.setCheckPrice(json.getInteger("checkPrice"));
                    if (plan.getCheckPrice() > 0) {
                        plan.setVisType(2);
                    } else {
                        plan.setVisType(1);
                    }
                    PlanDetails planDetails = new PlanDetails();
                    planDetails.setPlanId(plan.getId());
                    planDetails.setPlanContent(json.getString("postContent"));
                    planDetails.setDanBeiCount(json.getInteger("danBeiCount"));
                    planDetails.setBonus(sliderBonus);
                    boolean isSingle = json.getBoolean("isSingle");
                    if (isSingle) {//单期购买
                        plan.setEndOrder(String.valueOf(Integer.parseInt(App.getInstance().getLottery().getCaiQishu()) + 1));
                        plan.setOrderTotal(1);
                        int beishu = json.getInteger("beishu");
                        plan.setMultiples(String.valueOf(beishu));
                        plan.setTotalCost(plan.getZhuCount() * 2 * beishu);
                        if ((plan.getZhuCount() * 2) >= sliderBonus) {
                            handlerSendMsg(handler, HandlerWhat.GET_TZLIST_FALIURE, "当前方案不会有盈利！");
                            return;
                        }
                    } else {//追号
                        if (planBeiInfos == null || planBeiInfos.size() == 0) {
                            handlerSendMsg(handler, HandlerWhat.GET_TZLIST_FALIURE, "追号模式需要先生成方案后购买！");
                            return;
                        }
                        if (!plan.getBeginOrder().equals(planBeiInfos.get(0).getIssue())) {
                            handlerSendMsg(handler, HandlerWhat.GET_TZLIST_FALIURE, "第" + plan.getBeginOrder() + "期详情内容已结束，请重新生成后购买!");
                            return;
                        }
                        plan.setEndOrder(planBeiInfos.get(planBeiInfos.size() - 1).getIssue());
                        plan.setOrderTotal(planBeiInfos.size());
                        StringBuilder sb = new StringBuilder();
                        int totalCost = 0;
                        for (PlanBeiInfo planBeiInfo : planBeiInfos) {
                            totalCost += (plan.getZhuCount() * 2) * planBeiInfo.getMultiple();
                            sb.append(planBeiInfo.getMultiple()).append("-");
                        }
                        plan.setTotalCost(totalCost);
                        String m = sb.toString();
                        plan.setMultiples(m.substring(0, m.lastIndexOf("-")));
                    }

                    UserInfo userInfo = App.getInstance().getUser();
                    if (plan.getTotalCost() > Double.parseDouble(userInfo.getCoin())) {
                        handlerSendMsg(handler, HandlerWhat.GET_TZLIST_FALIURE, "余额不足！");
                        return;
                    }
                    //final PlanInfo planInfo_f = plan;
                    //final PlanDetails planDetails_f = planDetails;
                    /*JSONObject json_plan = callWebService(InterfaceConsts.WEB_SERVER_URL, InterfaceConsts.NAMESPACE, InterfaceConsts.AddPlanInfo, new DealParam() {
                        @Override
                        public SoapObject dealparam(SoapObject soapObject) {
                            soapObject.addProperty("planInfo", planInfo_f);
                            soapObject.addProperty("planDetil", planDetails_f);
                            return soapObject;
                        }
                    });*/
                    String multiples = "";
                    if (planBeiInfos.size() > 1) {
                        for (int i = 0; i < planBeiInfos.size(); i++) {
                            if (i == (planBeiInfos.size() - 1))
                                multiples += planBeiInfos.get(i).getMultiple() + "-";
                            else
                                multiples += planBeiInfos.get(i).getMultiple();
                        }
                    } else {
                        multiples += planBeiInfos.get(0).getMultiple();
                    }
                    JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(planBeiInfos));
                    JSONObject jsonObject = new JSONObject();
                    String planName = CaiUtil.getCaiMcShort(plan.getCaiType()) + "_任五_" + json.get("danBeiCount") + "_" + planBeiInfos.get(0).getIssue().substring(6, 8);
                    jsonObject.put("lotteryType", plan.getCaiType());
                    jsonObject.put("planName", planName);
                    jsonObject.put("zhuCount", json.get("danBeiCount"));
                    jsonObject.put("beginOrder", plan.getBeginOrder());
                    jsonObject.put("EndOrder", plan.getEndOrder());
                    jsonObject.put("checkPrice", json.get("checkPrice"));
                    jsonObject.put("multiples", multiples);
                    jsonObject.put("totalCost", planBeiInfos.get(planBeiInfos.size()-1).getTotalAmount());
                    jsonObject.put("minIncome", json.get("minlncome"));
                    jsonObject.put("planContent", json.get("postContent"));
                    RequestBody requestBody = new FormBody.Builder()
                            .add("planInfo", jsonObject.toString())
                            .add("beiInfo", jsonArray.toString())
                            .add("type", json.get("selectType").toString())
                            .build();
                    String url = InterfaceConsts.RELEASE_PLAN_URL;
                    String result = OkHttpRequester.postStringFromServer(url, requestBody, true);
                    map = JSON.parseObject(result, HashMap.class);
                    if (map.get("code").equals("1")) {
                        handlerSendMsg(handler, HandlerWhat.GET_TZLIST_SUCCESS, map.get("message"));
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_TZLIST_FALIURE, map.get("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_TZLIST_FALIURE, map.get("message"));
                }
            }
        }).start();
    }

    @Override
    public void getCalcProfitPer(final Handler handler, final int count, final int orderCount, final int multiple, final int bonus, final double profitPer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DecimalFormat df = new DecimalFormat("0.00");
                    List<CalculateInfo> calcInfos = new ArrayList<>();
                    //成本
                    int chengBen = count * 2;
                    //利率
                    double qishiQc = profitPer / 100;
                    if (((double) (bonus - chengBen) / (double) chengBen) > qishiQc) {
                        CalculateInfo cal = new CalculateInfo();
                        cal.setMultiple(multiple);
                        cal.setCost(chengBen * multiple);
                        cal.setTotalCost(chengBen * multiple);
                        cal.setProfit((bonus * multiple) - cal.getTotalCost());
                        //cal.setProfitPer(((bonus * multiple) - cal.getTotalCost())/cal.getTotalCost());
                        //String ly = df.format((float)((bonus * multiple) - cal.getTotalCost())/cal.getTotalCost());
                        cal.setProfitPer(Double.parseDouble(df.format((float) ((bonus * multiple) - cal.getTotalCost()) * 100 / cal.getTotalCost())));
                        calcInfos.add(cal);
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_CALCPROFITPER_FALIURE, "您设置的盈利过高或起始倍数过低，首期方案不能达到盈利要求，请调整！");
                        return;
                    }
                    for (int i = 2; i <= orderCount; i++) {
                        //计算出累计成本 和上期倍数
                        double leij = calcInfos.get(calcInfos.size() - 1).getTotalCost();
                        int sqBeishu = calcInfos.get(calcInfos.size() - 1).getMultiple();
                        int zqBeishu = sqBeishu;
                        //这次累计
                        double zcLeij = leij + (zqBeishu * chengBen);
                        if (zcLeij > 9999999) {
                            handlerSendMsg(handler, HandlerWhat.GET_CALCPROFITPER_FALIURE, "最大成本已超过9999999请调整方案！");
                            return;
                        }

                        //计算当前的倍数 和成本 是否达到盈利率 达不到就增加 成本 达到就添加
                        while (((bonus * zqBeishu) - zcLeij) / zcLeij < qishiQc) {
                            //double iy = ((bonus*zqBeishu) - zcLeij)/zcLeij;
                            zqBeishu++;
                            zcLeij = leij + (zqBeishu * chengBen);
                        }
                        CalculateInfo cal = new CalculateInfo();
                        cal.setMultiple(zqBeishu);
                        cal.setCost(chengBen * zqBeishu);
                        cal.setTotalCost((int) zcLeij);
                        cal.setProfit((bonus * zqBeishu) - cal.getTotalCost());
                        /*String ly = df.format((float)((bonus * multiple) - cal.getTotalCost())*100/cal.getTotalCost());
                        double d = Double.parseDouble(ly);*/
                        cal.setProfitPer(Double.parseDouble(df.format((float) (bonus * zqBeishu - cal.getTotalCost()) * 100 / cal.getTotalCost())));
                        //cal.setProfitPer((Math.round((bonus * zqBeishu - cal.getTotalCost())/cal.getTotalCost())/100.00));
                        calcInfos.add(cal);
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_CALCPROFITPER_SUCCESS, ReturnStatus.KEY_CALCPROFITPER_LIST, calcInfos);
                } catch (Exception e) {
                    e.printStackTrace();
                    handlerSendMsg(handler, HandlerWhat.GET_CALCPROFITPER_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void getCalcProfit(final Handler handler, final int count, final int orderCount, final int multiple, final int bonus, final int profit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DecimalFormat df = new DecimalFormat("0.00");
                    List<CalculateInfo> calcInfos = new ArrayList<>();
                    //成本
                    int chengBen = count * 2;
                    //利率
                    if ((double) (bonus - chengBen) > (double) profit) {
                        CalculateInfo cal = new CalculateInfo();
                        cal.setMultiple(multiple);
                        cal.setCost(chengBen * multiple);
                        cal.setTotalCost(chengBen * multiple);
                        cal.setProfit((bonus * multiple) - cal.getTotalCost());
                        //cal.setProfitPer((bonus * multiple - cal.getTotalCost()) / cal.getTotalCost());
                        cal.setProfitPer(Double.parseDouble(df.format((float) (bonus * multiple - cal.getTotalCost()) * 100 / cal.getTotalCost())));
                        calcInfos.add(cal);
                    } else {
                        handlerSendMsg(handler, HandlerWhat.GET_CALCPROFIT_FALIURE, "您设置的盈利过高或起始倍数过低，首期方案不能达到盈利要求，请调整！");
                        return;
                    }
                    for (int i = 2; i <= orderCount; i++) {
                        //计算出累计成本 和上期倍数
                        double leij = calcInfos.get(calcInfos.size() - 1).getTotalCost();
                        int sqBeishu = calcInfos.get(calcInfos.size() - 1).getMultiple();
                        int zqBeishu = sqBeishu;
                        //这次累计
                        double zcLeij = leij + (zqBeishu * chengBen);
                        if (zcLeij > 9999999) {
                            handlerSendMsg(handler, HandlerWhat.GET_CALCPROFIT_FALIURE, "最大成本已超过9999999请调整方案！");
                            return;
                        }

                        //计算当前的倍数 和成本 是否达到盈利率 达不到就增加 成本 达到就添加
                        while (((bonus * zqBeishu) - zcLeij) < profit) {
                            zqBeishu++;
                            zcLeij = leij + (zqBeishu * chengBen);
                        }
                        CalculateInfo cal = new CalculateInfo();
                        cal.setMultiple(zqBeishu);
                        cal.setCost(chengBen * zqBeishu);
                        cal.setTotalCost((int) zcLeij);
                        cal.setProfit((bonus * zqBeishu) - cal.getTotalCost());
                        //cal.setProfitPer((bonus * zqBeishu - cal.getTotalCost()) / cal.getTotalCost());
                        cal.setProfitPer(Double.parseDouble(df.format((float) (bonus * zqBeishu - cal.getTotalCost()) * 100 / cal.getTotalCost())));
                        calcInfos.add(cal);
                    }
                    handlerSendBundleMsg(handler, HandlerWhat.GET_CALCPROFIT_SUCCESS, ReturnStatus.KEY_CALCPROFIT_LIST, calcInfos);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_CALCPROFIT_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void calculationJiao(final Handler handler, final String haomaA, final String haomaB) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] aList = haomaA.split("\n");
                    List<String> hmAlists = new ArrayList<>();
                    for (String str : aList) {
                        hmAlists.add(str.trim());
                    }
                    String[] bList = haomaB.split("\n");
                    int icount = 0;
                    StringBuilder stringBuilder = new StringBuilder("");
                    for (String str : bList) {
                        if (hmAlists.contains(str)) {
                            stringBuilder.append(str.trim()).append("\n");
                            icount++;
                        }
                    }
                    String[] result = new String[2];
                    result[0] = String.valueOf(icount);
                    result[1] = String.valueOf(stringBuilder.toString().trim());

                    handlerSendMsg(handler, HandlerWhat.GET_JISUAN_JIAO_SUCCESS, result);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_JISUAN_JIAO_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void calculationBing(final Handler handler, final String haomaA, final String haomaB) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] aList = haomaA.split("\n");
                    List<String> hmAlists = new ArrayList<>();
                    for (String str : aList) {
                        hmAlists.add(str.trim());
                    }
                    String[] bList = haomaB.split("\n");
                    for (String str : bList) {
                        if (!hmAlists.contains(str)) {
                            hmAlists.add(str);
                        }
                    }
                    int icount = 0;
                    StringBuilder stringBuilder = new StringBuilder("");
                    for (String str : hmAlists) {
                        stringBuilder.append(str.trim()).append("\n");
                        icount++;
                    }
                    String[] result = new String[2];
                    result[0] = String.valueOf(icount);
                    result[1] = String.valueOf(stringBuilder.toString().trim());

                    handlerSendMsg(handler, HandlerWhat.GET_JISUAN_BING_SUCCESS, result);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_JISUAN_BING_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void calculationApaiB(final Handler handler, final String haomaA, final String haomaB) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] aList = haomaA.split("\n");
                    List<String> hmAlists = new ArrayList<>();
                    for (String str : aList) {
                        hmAlists.add(str.trim());
                    }
                    String[] bList = haomaB.split("\n");
                    for (String str : bList) {
                        if (hmAlists.contains(str)) {
                            hmAlists.remove(str);
                        }
                    }

                    int icount = 0;
                    StringBuilder stringBuilder = new StringBuilder("");
                    for (String str : hmAlists) {
                        stringBuilder.append(str.trim()).append("\n");
                        icount++;
                    }
                    String[] result = new String[2];
                    result[0] = String.valueOf(icount);
                    result[1] = String.valueOf(stringBuilder.toString().trim());
                    handlerSendMsg(handler, HandlerWhat.GET_A_PAI_B_SUCCESS, result);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_A_PAI_B_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    @Override
    public void calculationBpaiA(final Handler handler, final String haomaA, final String haomaB) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] aList = haomaA.split("\n");
                    String[] bList = haomaB.split("\n");

                    List<String> hmBlists = new ArrayList<>();
                    for (String str : bList) {
                        hmBlists.add(str.trim());
                    }

                    for (String str : aList) {
                        if (hmBlists.contains(str)) {
                            hmBlists.remove(str);
                        }
                    }

                    int icount = 0;
                    StringBuilder stringBuilder = new StringBuilder("");
                    for (String str : hmBlists) {
                        stringBuilder.append(str.trim()).append("\n");
                        icount++;
                    }
                    String[] result = new String[2];
                    result[0] = String.valueOf(icount);
                    result[1] = String.valueOf(stringBuilder.toString().trim());
                    handlerSendMsg(handler, HandlerWhat.GET_B_PAI_A_SUCCESS, result);
                } catch (Exception e) {
                    handlerSendMsg(handler, HandlerWhat.GET_B_PAI_A_FALIURE, ReturnStatus.DESCRIPT_CONNECT_FAILURE);
                }
            }
        }).start();
    }

    public static boolean checkURL(String url) {
        boolean value = false;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            int code = conn.getResponseCode();
            if (code != 200) {
                value = false;
            } else {
                value = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}

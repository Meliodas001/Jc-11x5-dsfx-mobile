package com.jingcai.jc_11x5.business;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.*;

import java.util.ArrayList;

public class HandlerSendMsg {

    /**
     * 发送空消息
     *
     * @param handler 消息处理器
     * @param what    标记
     */
    public void handlerSendEmptyMsg(Handler handler, int what) {
        Message msg = Message.obtain(handler, what);
        msg.sendToTarget();
    }

    /**
     * handler消息发送器
     * modified by tf 2014-06-12 14:56:11
     *
     * @param handler 消息处理器
     * @param msgwhat 标记
     * @param msgstr  值
     */
    public static void handlerSendMsg(Handler handler, int msgwhat, Object msgstr) {
        Message msg = Message.obtain(handler, msgwhat);
        msg.obj = msgstr;
        msg.sendToTarget();
    }

    /**
     * handler bundle data消息发送器
     * modified by tf 2014-06-12 14:56:11
     *
     * @param handler 消息处理器
     * @param msgwhat 标记
     * @param key     键
     * @param obj     值
     */
    public static void handlerSendBundleMsg(Handler handler, int msgwhat, String key, Object obj) {
        Message msg = Message.obtain(handler, msgwhat);
        if (key != null) {
            Bundle data = new Bundle();
            try {
                switch (msgwhat) {
                    case HandlerWhat.GET_KAIJIANG_SUCCESS:
                        data.putSerializable(key, (ArrayList<KaiJiang_11x5>) obj);
                        break;
                    case HandlerWhat.GET_SSGLLIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<String>) obj);
                        break;
                    case HandlerWhat.GET_WEEK_YL_SUCCESS:
                        data.putSerializable(key, (ArrayList<ProfitChart>) obj);
                        break;
                    case HandlerWhat.GET_MONTH_YL_SUCCESS:
                        data.putSerializable(key, (ArrayList<ProfitChart>) obj);
                        break;
                    case HandlerWhat.GET_SEASON_YL_SUCCESS:
                        data.putSerializable(key, (ArrayList<ProfitChart>) obj);
                        break;
                    case HandlerWhat.GET_NEWPLANLIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<ResultBean.LotteryPlan>) obj);
                        break;
                    case HandlerWhat.GET_NEWPLANLISTBYNAME_SUCCESS:
                        data.putSerializable(key, (ArrayList<ResultBean.LotteryPlan>) obj);
                        break;
                    case HandlerWhat.GET_COINCHANGE_TOP80_SUCCESS:
                        data.putSerializable(key, (ArrayList<CoinChangeRecord>) obj);
                        break;
                    case HandlerWhat.GET_BALANCECHANGE_TOP80_SUCCESS:
                        data.putSerializable(key, (ArrayList<BalanceChangeRecord>) obj);
                        break;
                    case HandlerWhat.GET_TZJL_SUCCESS:
                        data.putSerializable(key, (ArrayList<PlanDetails>) obj);
                        break;
                    case HandlerWhat.GET_FASCLIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<PlanBeiInfo>) obj);
                        break;
                    case HandlerWhat.GET_CALCPROFITPER_SUCCESS:
                        data.putSerializable(key, (ArrayList<CalculateInfo>) obj);
                        break;
                    case HandlerWhat.GET_CALCPROFIT_SUCCESS:
                        data.putSerializable(key, (ArrayList<CalculateInfo>) obj);
                        break;
                    case HandlerWhat.GET_JOBPRICE_LIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<JobPrice>) obj);
                        break;
                    case HandlerWhat.GET_ALLJOB_LIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<JobPlanner>) obj);
                        break;
                    case HandlerWhat.GET_SINGLEJOB_LIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<JobPlanner>) obj);
                        break;
                    case HandlerWhat.GET_ALLJOB_DIS_SUCCESS:
                        data.putSerializable(key, (ArrayList<JobDistribute>) obj);
                        break;
                    case HandlerWhat.GET_SINGLEJOB_DIS_SUCCESS:
                        data.putSerializable(key, (ArrayList<JobDistribute>) obj);
                        break;
                    case HandlerWhat.GET_NEWS_LIST_SUCCESS:
                        data.putSerializable(key, (ArrayList<News>) obj);
                        break;
                    default:
                        data.putString(key, (String) obj);
                        break;
                }
                msg.setData(data);
                msg.sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

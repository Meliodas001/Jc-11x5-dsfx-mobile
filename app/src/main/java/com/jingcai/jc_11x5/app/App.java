package com.jingcai.jc_11x5.app;

import android.app.Activity;
import android.app.Application;

import com.jingcai.jc_11x5.business.impl.WebSocketServer;
import com.jingcai.jc_11x5.db.SqliteHelper;
import com.jingcai.jc_11x5.db.dao.KaiJiang_11x5Dao;
import com.jingcai.jc_11x5.entity.KaiJiangTime;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import com.jingcai.jc_11x5.entity.Lottery;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.util.AppTool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by yangsen on 2016/7/13.
 */
public class App extends Application {

    private static App instance;
    private ArrayList<Activity> activityList=null;
    private UserInfo user;
    private boolean isWrite;
    private Lottery lottery;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;
        if(user == null){
            user = new UserInfo(AppTool.getImei(getApplicationContext()));
        }

        if(activityList==null){
            activityList=new ArrayList<>();
        }else{
            activityList.clear();
        }
        SqliteHelper helper = SqliteHelper.getHelper();
        try {
            helper.createTable(helper.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        KaiJiang_11x5Dao kaiJiang_11x5Dao = new KaiJiang_11x5Dao();
        KaiJiang_11x5 kaiJiang_11x53 = kaiJiang_11x5Dao.queryByPeriod("预选行3");
        if(kaiJiang_11x53 == null){
            kaiJiang_11x53 = new KaiJiang_11x5();
            kaiJiang_11x53.setOrderNum("预选行3");
            kaiJiang_11x53.setCaiZhong("yuxuan");
            kaiJiang_11x53.setZouShi("0,0,0,0,0,0,0,0,0,0,0");
            kaiJiang_11x5Dao.add(kaiJiang_11x53);
        }

        KaiJiang_11x5 kaiJiang_11x52 = kaiJiang_11x5Dao.queryByPeriod("预选行2");
        if(kaiJiang_11x52 == null){
            kaiJiang_11x52 = new KaiJiang_11x5();
            kaiJiang_11x52.setOrderNum("预选行2");
            kaiJiang_11x52.setCaiZhong("yuxuan");
            kaiJiang_11x52.setZouShi("0,0,0,0,0,0,0,0,0,0,0");
            kaiJiang_11x5Dao.add(kaiJiang_11x52);
        }

        KaiJiang_11x5 kaiJiang_11x5 = kaiJiang_11x5Dao.queryByPeriod("预选行1");
        if(kaiJiang_11x5 == null){
            kaiJiang_11x5 = new KaiJiang_11x5();
            kaiJiang_11x5.setOrderNum("预选行1");
            kaiJiang_11x5.setCaiZhong("yuxuan");
            kaiJiang_11x5.setZouShi("0,0,0,0,0,0,0,0,0,0,0");
            kaiJiang_11x5Dao.add(kaiJiang_11x5);
        }

        /*IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        KaijiangReceiver receiver = new KaijiangReceiver();
        registerReceiver(receiver, filter);*/
    }

    /**
     * 获取App对象
     */
    public static App getInstance() {
        if(instance == null)
            return new App();
        return instance;
    }

    /**
     * 将activity加入列表 作为类似栈的集合 来保存各个Activity
     */
    public void addActivity(Activity activity) {
        if(activityList != null && activity != null){
            activityList.add(activity);
        }
    }

    /**
     * 退出栈中的所有Activity
     */
    public void removeActivityStack(){
        try {
            if(activityList!=null ){
                while(activityList.size()>0){
                    activityList.remove(0).finish();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 移除单个Activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        try {
            if(activityList != null && activity != null){
                activityList.remove(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserInfo getUser() {
        if(user == null){
            user = new UserInfo(AppTool.getImei(getApplicationContext()));
        }
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public boolean isWrite() {
        return isWrite;
    }

    public void setWrite(boolean write) {
        isWrite = write;
    }

    public Lottery getLottery() {
        if(lottery == null){
            lottery = new Lottery();
        }
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }
}

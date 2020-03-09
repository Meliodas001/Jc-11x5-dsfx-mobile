package com.jingcai.jc_11x5.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by yangsen on 2017/5/22.
 */

public class NotificationUtil {

   /* public static void postKjNotification(int typeInt, String neirong) {
        BaoJingConfig baoJingConfig = App.getInstance().getBaoJingConfig();
        boolean iskj = baoJingConfig.isKaijiangVoice();
        if(!iskj){
            return;
        }
        Context context = App.getInstance().getApplicationContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.mipmap.ic_jc)
                .setContentTitle("Jc-11x5")
                .setContentText(neirong);
        mBuilder.setTicker("有新信息啦！");//第一次提示消息的时候显示在通知栏上
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        mBuilder.setWhen(System.currentTimeMillis());// 设置通知来到的时间
        mBuilder.setAutoCancel(true);//自己维护通知的消失
        //构建一个Intent
        //Intent resultIntent = new Intent(context, WelcomeActivity.class);  //需要跳转指定的页面
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("page", "kaijiang");
        //封装一个Intent
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置通知主题的意图
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(typeInt, mBuilder.build());
    }*/

    /*public static void postYlNotification(int typeInt, String neirong) {
        BaoJingConfig baoJingConfig = App.getInstance().getBaoJingConfig();
        boolean sybj = baoJingConfig.isAudibleAlarm();
        boolean zdbj = baoJingConfig.isVibrationAlarm();
        if(!sybj && !zdbj){
            return;
        }
        Context context = App.getInstance().getApplicationContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.mipmap.ic_jc)
                .setContentTitle("Jc-11x5")
                .setContentText(neirong);
        mBuilder.setTicker("New message");//第一次提示消息的时候显示在通知栏上
        if(sybj && zdbj){
            mBuilder.setDefaults(Notification.DEFAULT_ALL);
        }else if(sybj && !zdbj){
            mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        }else if(zdbj && !sybj){
            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        }

        mBuilder.setWhen(System.currentTimeMillis());// 设置通知来到的时间
        mBuilder.setAutoCancel(true);//自己维护通知的消失
        //构建一个Intent
        //Intent resultIntent = new Intent(context, WelcomeActivity.class);  //需要跳转指定的页面
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("page", "yilou");
        //封装一个Intent
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置通知主题的意图
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(typeInt, mBuilder.build());
    }*/

}

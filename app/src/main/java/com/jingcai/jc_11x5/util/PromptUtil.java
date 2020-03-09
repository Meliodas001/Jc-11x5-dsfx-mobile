package com.jingcai.jc_11x5.util;

import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.consts.Constants;

/**
 * Created by yangsen on 2018/2/5.
 */

public class PromptUtil {

    /**
     * final Activity activity  ：调用该方法的Activity实例
     * long milliseconds ：震动的时长，单位是毫秒
     * long[] pattern  ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     */
    public static void vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    public static void vibrate(final Activity activity, long[] pattern,boolean isRepeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }

    //提示音  有消息来了，让系统提示音响一下
    public static void startAlarm(Context context) {
        if(isOpenAlarm()){
            //Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Uri sound=Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +context.getPackageName() + "/"+R.raw.b);
            //Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.b );
            if (sound == null) return;
            Ringtone r = RingtoneManager.getRingtone(context, sound);
            r.play();
        }
    }

    private static boolean isOpenAlarm(){
        SharedPreferences pref = App.getInstance().getSharedPreferences(Constants.PREFERENCE_USER_KEY, App.getInstance().MODE_PRIVATE);
        return pref.getBoolean(Constants.PREFERENCE_SHARED_ALARM, true);
    }

    public static void audio(Activity activity){
        MediaPlayer mp = new MediaPlayer();
        mp.reset();
        try {
            // 自定义音频文件
            mp.setDataSource("/mnt/sdcard/AAAAA_CHENJIAN/test/music/1.mp3");
            mp.prepare();
            mp.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

package com.jingcai.jc_11x5.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.*;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.DownLoadManager;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.business.impl.WebSocketServer;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.entity.Version;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.MainActivity;
import com.jingcai.jc_11x5.util.AppTool;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.Html4Text;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import org.java_websocket.WebSocket;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.jingcai.jc_11x5.business.HandlerSendMsg.handlerSendMsg;

public class WelcomeActivity extends BaseActivity {

    private String phone;
    private String pwd;
    private String caiZhong;
    private Handler handler;
    private App app;
    private UserInfo user;
    private boolean isFirstRun;
    private Version version;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if(Build.VERSION.SDK_INT >= 23){
            //判断当前系统的版本
            verifyStoragePermissions(this);
        }
        initData();

        new Thread() {
            @Override
            public void run() {
                try {
                    /*sleep(2000);
                    startNewTaskActivity(WelcomeActivity.this,LoginActivity.class);*/
                    sleep(2000);
                    Jc11x5Factory.getInstance().getBanben(handler);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    protected void initData(){
        app =  App.getInstance();
        user = app.getUser();
        version = new Version();
        handler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }


    //是否需要自动登录
    protected boolean isAutoSignIn() {
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_USER_KEY, MODE_PRIVATE);
        phone = pref.getString(Constants.PREFERENCE_SHARED_USER, "");
        pwd = pref.getString(Constants.PREFERENCE_SHARED_PWD, "");
        caiZhong = pref.getString(Constants.PREFERENCE_SHARED_CAI, "");
        isFirstRun = pref.getBoolean(Constants.PREFERENCE_ISFIRST_RUN, true);
        boolean isAuto = pref.getBoolean(Constants.PREFERENCE_SHARED_AUTO, false);
        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(caiZhong)){
            return false;
        }
        return isAuto;
    }

    public void login(){
        user.setUserName(phone);
        user.setPassWord(pwd);
        user.setCaizhong(caiZhong);
        user.setCaizhongMc(CaiUtil.getCaiMc(caiZhong));
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        user.setMac(ANDROID_ID);
        if(TextUtils.isEmpty(caiZhong)){
            startNewTaskActivity(this, LoginActivity.class);
            this.finish();
        }
        Jc11x5Factory.getInstance().login(handler, user, isFirstRun);
//        startNewActivity(MainActivity.class);
    }

    DialogWiget dialog = new DialogWiget();
    private int dialog_state;
    public void showUpdataDialog() {
        //String string ="发布版本日期: "+version.getVersionDate()+"<br>"+ version.getUpgradeInfo().replace("|","<br>");
        String string = version.getUpgradeInfo().replace("|","<br>");
        if(version.isForceUpdate()){
            string += "<br>需要更新才能正常使用，请更新";
        }
        dialog.setOKText("更新");
        dialog.showCustomMessageQuery(WelcomeActivity.this, "应用更新",
                Html4Text.mk(string)
        );
        dialog.setOnQueryOkListener(new DialogWiget.OnQueryOk() {
            @Override
            public void onQueryOkClick(View v) {
                dialog_state=1;
                dialog.dismiss();
                downLoadApk();
            }
        });
        dialog.setOnQueryCancelListener(new DialogWiget.OnQueryCancel() {
            @Override
            public void onQueryCancel(View v) {
                dialog_state=1;
                if(version.isForceUpdate()){
                    finish();
                }else{
                    validateLogin();
                }
            }
        });
        dialog.setOnDialogMissListener(new DialogWiget.OnDialogMissListener() {
            @Override
            public void onDialogMiss(AlertDialog adlog) {
                if(dialog_state!=1){
                    if(version.isForceUpdate){
                        finish();
                    }else{
                        validateLogin();
                    }
                }
            }
        });
    }

    /**
     * 下载apk
     */
    private ProgressDialog pd;
    protected boolean run=true;
    protected void downLoadApk() {
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在下载更新");
        pd.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                run = false;
                // 断开DownLoadManager网络连接
                //结束
                finish();
            }
        });
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = DownLoadManager.getFileFromServer(version.getUpdateUrl(), pd);
                    sleep(50);
                    if(run){
                        AppTool.installApk(file);
                    }
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = HandlerWhat.DOWN_ERROR;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }


    private void handlerMsg(Message msg) {
        switch (msg.what){
            case HandlerWhat.LOGIN_SUCCESS:
                WebSocketServer.getSocketClient();
                WebSocketServer.sendMsg(App.getInstance().getUser().getCaizhong());
                startNewActivity(WelcomeActivity.this, MainActivity.class);
                finish();
                break;
            case HandlerWhat.GET_BANBEN_SUCCESS:
                //获取版本号成功
                version = (Version)msg.obj;
                if(version != null){
                    int versionCode = AppTool.getVersionCode(this);
                    if(version.getVersionCode() > versionCode){
                        showUpdataDialog();
                        return;
                    }
                }
                validateLogin();
                break;
            case HandlerWhat.GET_BANBEN_TIMEOUT:
            case HandlerWhat.GET_BANBEN_FALIURE:
                validateLogin();
                break;
            case  HandlerWhat.DOWN_ERROR:
                // 下载apk失败
                pd.hide();
                showMsg("下载新版本失败");
                validateLogin();
                break;
            default:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                startNewTaskActivity(WelcomeActivity.this, LoginActivity.class);
                finish();
                break;
        }
    }

    private void validateLogin(){
        if(isAutoSignIn()){
            login();
        }else{
            startNewTaskActivity(WelcomeActivity.this, LoginActivity.class);
            finish();
        }
    }

    public boolean checkURL(String url){
        boolean value = false;
        try {
            HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
            int code=conn.getResponseCode();
            if(code != 200){
                value=false;
            }else{
                value=true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


}

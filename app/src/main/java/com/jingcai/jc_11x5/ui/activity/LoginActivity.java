package com.jingcai.jc_11x5.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.CaiZhong;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.MainActivity;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.view.adapter.DmAdapter;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_zhuce)
    TextView tvZhuce;
    @Bind(R.id.tv_cai)
    TextView tvCai;
    @Bind(R.id.tv_wjmm)
    TextView tvWjmm;

    private App app;
    private UserInfo user;
    private Handler handler;
    private DialogWiget dialogWiget;
    private boolean isFirstRun;
    private boolean isInitKaijiang;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_PHONE_STATE = 2;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23) {
            //判断当前系统的版本
            verifyDeviceIdPermissions(this);
            verifyStoragePermissions(this);
        }
        initData();
        getPreference();
        initView();

        Intent intent = getIntent();
        if (intent != null) {
            String verifyMsg = (String) intent.getSerializableExtra("verifyMsg");
            if (verifyMsg != null && !TextUtils.isEmpty(verifyMsg)) {
                showMsg(verifyMsg);
            }
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    public static void verifyDeviceIdPermissions(Activity activity) {
        //Android6.0需要动态获取权限
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_STATE);
        }
    }

    /**
     *加个获取权限的监听
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        switch (requestCode){
            case REQUEST_PHONE_STATE:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
                    String id = TelephonyMgr.getDeviceId();
                    if(id != null && !TextUtils.isEmpty(id)){
                        user.setMac(id);
                        App.getInstance().setUser(user);
                    }
                }else{
                    verifyDeviceIdPermissions(this);
                }
                break;
        }

    }

    protected void initData() {
        app = App.getInstance();
        user = app.getUser();
        dialogWiget = new DialogWiget();
        handler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.LOGIN_SUCCESS:
                ProgressWidget.dismissProgressDialog();
                user = app.getUser();
                setPreference();
                startNewActivity(MainActivity.class);
                finish();
                break;
            default:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                break;
        }
    }

    protected void initView() {
        if (user != null) {
            if (!TextUtils.isEmpty(user.getUserName())) {
                etPhone.setText(user.getUserName());
            }
            if (!TextUtils.isEmpty(user.getPassWord())) {
                etPwd.setText(user.getPassWord());
            }
            if (!TextUtils.isEmpty(user.getCaizhong())) {
                tvCai.setTag(user.getCaizhong());
                tvCai.setText(CaiUtil.getCaiMc(user.getCaizhong()));
            }
        }
        //etPwd.setText("123456");
    }

    /**
     * 设置偏好设置
     */
    public void setPreference() {
        SharedPreferences.Editor edit = getSharedPreferences(Constants.PREFERENCE_USER_KEY, MODE_PRIVATE).edit();
        edit.putString(Constants.PREFERENCE_SHARED_USER, etPhone.getText().toString().trim());
        edit.putString(Constants.PREFERENCE_SHARED_PWD, etPwd.getText().toString().trim());
        edit.putString(Constants.PREFERENCE_SHARED_CAI, tvCai.getTag().toString().trim());
        edit.putBoolean(Constants.PREFERENCE_SHARED_AUTO, true);
        edit.putBoolean(Constants.PREFERENCE_ISFIRST_RUN, false);
        edit.putBoolean(Constants.PREFERENCE_ISINIT_KAIJIANG, isInitKaijiang);
        edit.commit();
    }

    public void setCaiPreference(String cai) {
        SharedPreferences.Editor edit = getSharedPreferences(Constants.PREFERENCE_USER_KEY, MODE_PRIVATE).edit();
        edit.putString(Constants.PREFERENCE_SHARED_CAI, cai);
        edit.commit();
    }

    /**
     * 读取偏好设置
     */
    public void getPreference() {
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_USER_KEY, MODE_PRIVATE);
        isFirstRun = pref.getBoolean(Constants.PREFERENCE_ISFIRST_RUN, true);
        isInitKaijiang = pref.getBoolean(Constants.PREFERENCE_ISINIT_KAIJIANG, false);
        /*if(isFirstRun){
            SharedPreferences.Editor editor = pref.edit();
            Log.e("debug", "第一次运行");
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }*/
        String userName = pref.getString(Constants.PREFERENCE_SHARED_USER, "");
        String pwd = pref.getString(Constants.PREFERENCE_SHARED_PWD, "");
        String cai = pref.getString(Constants.PREFERENCE_SHARED_CAI, "");
        if (!TextUtils.isEmpty(userName)) {
            user.setUserName(userName);
        }
        if (!TextUtils.isEmpty(pwd)) {
            user.setPassWord(pwd);
        }
        if (!TextUtils.isEmpty(cai)) {
            user.setCaizhong(cai);
        }
        tvCai.setText(CaiUtil.getCaiMc(cai));
        tvCai.setTag(cai);
        user.setCaizhongMc(CaiUtil.getCaiMc(cai));
        etPhone.setText(userName);
        etPwd.setText(pwd);
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showMsg("请填写手机号码！");
            return;
        }
        user.setUserName(phone);
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            showMsg("请填写手机密码！");
            return;
        }
        user.setPassWord(pwd);
        String cai = tvCai.getText().toString().trim();
        if (TextUtils.isEmpty(cai)) {
            showMsg("请选择彩票！");
            return;
        }
        requestForLogin();
//        startNewActivity(MainActivity.class);
    }

    private void requestForLogin() {
        ProgressWidget.showProgressDialog(this, "正在加载中...");
        Jc11x5Factory.getInstance().login(handler, user, isFirstRun);
    }

    private void showCaiZhongList() {
        String title = "请选择彩种：";
        final DmAdapter dmadapter = new DmAdapter(this, CaiUtil.getCaiZhongList());
        dialogWiget.showListview(this, title, dmadapter, new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adv, View view, int position, long id) {
                if (dialogWiget.lDialog != null) {
                    dialogWiget.lDialog.dismiss();
                }
                try {
                    CaiZhong entity = (CaiZhong) adv.getAdapter().getItem(position);
                    tvCai.setText(entity.getCaiMc());
                    tvCai.setTag(entity.getCaiBm());
                    user.setCaizhong(entity.getCaiBm());
                    user.setCaizhongMc(entity.getCaiMc());
                    setCaiPreference(entity.getCaiBm());
                } catch (Exception e) {
                }
            }
        });
    }

    private void zaiXianGouMai() {
        startNewActivityForForResult(ForgetPasswordActivity.class, 1012);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1012:
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            String phone = bundle.getString("phone");
                            etPhone.setText(phone);
                            etPwd.setText("");
                            tvCai.setText("");
                            tvCai.setTag("");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @OnClick(R.id.tv_zhuce)
    public void onTvZhuceClicked() {
        startNewActivityForForResult(RegisterActivity.class, 1012);
    }

    @OnClick(R.id.tv_cai)
    public void onTvCaiClicked() {
        showCaiZhongList();
    }

    @OnClick(R.id.iv_qq)
    public void onIvQqClicked() {
        zaiXianGouMai();
    }

    @OnClick(R.id.tv_wjmm)
    public void onViewClicked() {
        zaiXianGouMai();
    }
}

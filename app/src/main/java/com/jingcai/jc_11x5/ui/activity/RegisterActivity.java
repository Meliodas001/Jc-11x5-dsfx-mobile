package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.util.AppTool;
import com.jingcai.jc_11x5.util.LogUtil;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_phone_code)
    EditText etPhoneCode;
    @Bind(R.id.et_get_msg)
    Button etGetCode;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_qr_pwd)
    EditText etQrPwd;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.btn_zhuce)
    Button btnZhuce;
    @Bind(R.id.tv_denglu)
    TextView tvDenglu;
    @Bind(R.id.tv_fuwu)
    TextView tvFuwu;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.et_nick_name)
    EditText etNickName;
    @Bind(R.id.et_share_no)
    EditText etShareNo;

    private App app;
    private Handler handler;
    private UserInfo user;
    DialogWiget dialogWiget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    protected void initData() {
        app = App.getInstance();
        user = app.getUser();
        handler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
        dialogWiget = new DialogWiget();
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.REGISTER_SUCCESS:
                ProgressWidget.dismissProgressDialog();
                App.getInstance().setUser(user);
                setResultOk();
                showMsg("注册成功！");
                handler.removeMessages(HandlerWhat.REGISTER_SUCCESS);
                handler.removeCallbacksAndMessages(null);
                btnZhuce.setClickable(true);
                this.finish();
                break;
            case HandlerWhat.GET_PHONE_CODE_SUCCESS:

                break;
            default:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                handler.removeCallbacksAndMessages(null);
                btnZhuce.setClickable(true);
                ProgressWidget.dismissProgressDialog();
//                startNewActivity(this, WebRegisterActivity.class);
//                this.finish();
                break;
        }
    }

    private void setResultOk(){
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString("phone", etPhone.getText().toString());
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
    }

    protected void initView() {
        tvTitle.setText(getResources().getString(R.string.text_zhuce));
        tvFuwu.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvFuwu.getPaint().setAntiAlias(true);//抗锯齿
        etGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                Jc11x5Factory.getInstance().getMassgeCode(handler, phone);
                etGetCode.setEnabled(true);
                mTimer.start();
            }
        });
    }

    CountDownTimer mTimer = new CountDownTimer(60 * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            etGetCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            etGetCode.setEnabled(true);
            etGetCode.setText("重新发送");
            cancel();
        }
    };

    @OnClick(R.id.btn_zhuce)
    public void onBtnZhuceClicked() {
        if (user.getMac() == null || TextUtils.isEmpty(user.getMac())) {
            user.setMac(AppTool.getImei(getApplicationContext()));
        }
        //注册
        String phone = etPhone.getText().toString();
        boolean isMobile = isMobile(phone);
        if(!isMobile){
            showMsg("手机号码格式错误！");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showMsg("请填写手机号码！");
            return;
        }
        user.setUserName(phone);
        String msgCode = etPhoneCode.getText().toString();
        if (TextUtils.isEmpty(msgCode)) {
            showMsg("请输入验证码");
            return;
        }
        user.setMsgCode(msgCode);
        String etNc = etNickName.getText().toString();
        if (TextUtils.isEmpty(etNc)) {
            showMsg("请填写昵称！");
            return;
        }
        user.setNickName(etNc);
        String shareNo = etShareNo.getText().toString();
        if (TextUtils.isEmpty(shareNo)) {
            showMsg("请填写推广号！");
            return;
        }
        user.setShareNo(shareNo);
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showMsg("请填写手机密码！");
            return;
        }
        String qrPwd = etQrPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showMsg("请填写手机确认密码！");
            return;
        }
        if (!pwd.equals(qrPwd)) {
            showMsg("两次密码不一致，请重新输入！");
            return;
        }
        user.setPassWord(pwd);
        if (!cbAgree.isChecked()) {
            showMsg("请你确认是否同意服务条款！");
            return;
        }
        requestForZhuce();
    }

    /**
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p;
        Matcher m;
        boolean b;
        //p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{4,8}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    private void requestForZhuce() {
//        LogUtil.trac("zhuce", " == requestForZhuce");
        btnZhuce.setClickable(false);
        handler.removeCallbacksAndMessages(null);
        ProgressWidget.showProgressDialog(this, "正在加载中...");
        Jc11x5Factory.getInstance().register(handler, user);
    }

    private void zaiXianGouMai() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_QQ)));
    }

    @OnClick(R.id.tv_denglu)
    public void onTvDengluClicked() {
        //登录
        startNewActivity(LoginActivity.class);
    }

    @OnClick(R.id.tv_fuwu)
    public void onTvFuwuClicked() {
        dialogWiget.showFuwuTiaokuan(this, "最终用户许可协议");
    }

    @OnClick(R.id.iv_qq)
    public void onIvQqClicked() {
        zaiXianGouMai();
    }

}

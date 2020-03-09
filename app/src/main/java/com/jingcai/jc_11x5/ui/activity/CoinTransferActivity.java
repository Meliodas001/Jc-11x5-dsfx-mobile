package com.jingcai.jc_11x5.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinTransferActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_zcyh)
    TextView etZcyh;
    @Bind(R.id.et_zryh)
    EditText etZryh;
    @Bind(R.id.et_zcje)
    EditText etZcje;
    @Bind(R.id.btn_transfer)
    Button btnTransfer;

    private App app;
    private UserInfo userInfo;

    private Handler mHandler;
    private boolean isTransfer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_transfer);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        app = App.getInstance();
        userInfo = app.getUser();
        mHandler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.TRANSFER_BALANCE_SUCCESS:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                isTransfer = true;
                Jc11x5Factory.getInstance().getUserInfoByUserName(mHandler, userInfo.getUserName());
                break;
            case HandlerWhat.GET_USERINFOBYNAME_SUCCESS:
                userInfo = app.getUser();
                ProgressWidget.dismissProgressDialog();
                break;
            default:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initView() {
        tvTitle.setText("无忧币转赠");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        etZcyh.setText(userInfo.getUserName());
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        setResult();
        finish();
    }

    private void setResult(){
        if (isTransfer) {
            setResult(RESULT_OK);
        } else {
            setResult(-99);
        }
    }

    @OnClick(R.id.btn_transfer)
    public void onBtnJisuanClicked() {
        String zcyh = etZcyh.getText().toString();
        String zryh = etZryh.getText().toString();
        String zcje = etZcje.getText().toString();
        if(TextUtils.isEmpty(zryh)){
            showMsg("请填写转赠用户名！");
            return;
        }
        if(TextUtils.isEmpty(zcje)){
            showMsg("请填写转赠金额！");
            return;
        }
//        hideSoftKeyboard(etZryh);
//        hideSoftKeyboard(etZcje);
//        etZryh.clearFocus();
//        etZcje.clearFocus();
        ProgressWidget.showProgressDialog(this, "正在加载中...");
        Jc11x5Factory.getInstance().transferBalance(mHandler, zcyh, zryh, zcje);
    }

    public void hideSoftKeyboard(EditText mEditText) {
        InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}

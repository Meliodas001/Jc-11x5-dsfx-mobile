package com.jingcai.jc_11x5.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinDuihuanActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_caibi)
    TextView tvCaibi;
    @Bind(R.id.tv_jifen)
    TextView tvJifen;
    @Bind(R.id.et_dh_caibi)
    EditText etDhCaibi;
    @Bind(R.id.tv_dh_coin)
    TextView tvDhCoin;
    @Bind(R.id.tv_dh_game)
    TextView tvDhGame;
    @Bind(R.id.bt_dh_coin)
    Button btDhCoin;

    private App app;
    private UserInfo user;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_duihuan);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        app = App.getInstance();
        user = app.getUser();
        mHandler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }
    @Override
    protected void initView() {
        tvTitle.setText("积分兑换");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        tvCaibi.setText(user.getMoney());
        tvJifen.setText("积分："+user.getCoin());
        tvDhGame.setText("1个无忧币可兑" + user.getIntegral() + "积分");
        etDhCaibi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if(!TextUtils.isEmpty(str)){
                    int dianbi = Integer.parseInt(str);
                    tvDhCoin.setText(String.valueOf(dianbi*Integer.getInteger(user.getIntegral())));
                }
            }
        });
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_COINCONVERSION_SUCCESS:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                Jc11x5Factory.getInstance().getUserInfoByUserName(mHandler, user.getUserName());
                break;
            case HandlerWhat.GET_USERINFOBYNAME_SUCCESS:
                user = app.getUser();
                tvCaibi.setText(user.getMoney());
                tvJifen.setText("积分："+user.getCoin());
                etDhCaibi.setText("");
                tvDhCoin.setText("");
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

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        finish();
    }

    @OnClick(R.id.bt_dh_coin)
    public void onBtDhCoinClicked() {
        String dianbi = etDhCaibi.getText().toString();
        if(TextUtils.isEmpty(dianbi)){
            showMsg("请输入兑换无忧币数！");
            return;
        }
        Jc11x5Factory.getInstance().coinConversion(mHandler, user.getId(), dianbi);
    }
}

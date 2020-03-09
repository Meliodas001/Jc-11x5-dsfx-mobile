package com.jingcai.jc_11x5.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.CoinChangeRecord;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.adapter.LvCoinAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 最近top80积分账变记录
 */
public class CoinListActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_coin)
    ListView lvCoin;

    private Handler mHandler;
    private App app;
    private LvCoinAdapter lvCoinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        ButterKnife.bind(this);
        initData();
        initView();
        requestData();
    }

    @Override
    protected void initData() {
        app = App.getInstance();
        mHandler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    @Override
    protected void initView(){
        tvTitle.setText("积分账变记录");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        lvCoinAdapter = new LvCoinAdapter(this, new ArrayList<>(), lvCoin);
        lvCoin.setAdapter(lvCoinAdapter);
    }

    private void requestData() {
        ProgressWidget.showProgressDialog(this, "正在加载中...");
        Jc11x5Factory.getInstance().getCoinChangeRecordTop80(mHandler, app.getUser().getId());
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_COINCHANGE_TOP80_SUCCESS:
                Bundle bundle = msg.getData();
                if(bundle != null){
                    List<CoinChangeRecord> lists = (List<CoinChangeRecord>) bundle.getSerializable(ReturnStatus.KEY_GET_COIN_LIST);
                    lvCoinAdapter.setList(lists);
                }
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
    public void onViewClicked() {
        finish();
    }
}

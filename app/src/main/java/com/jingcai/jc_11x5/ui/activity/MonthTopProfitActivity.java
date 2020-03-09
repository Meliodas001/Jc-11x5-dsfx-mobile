package com.jingcai.jc_11x5.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.ProfitChart;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.adapter.LvProfitAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthTopProfitActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_profit)
    ListView lvProfit;

    private Handler mHandler;
    private LvProfitAdapter profitLvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_top_profit);
        ButterKnife.bind(this);
        initData();
        initView();
        requestData();
    }

    @Override
    protected void initData() {
        mHandler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    @Override
    protected void initView(){
        tvTitle.setText("月盈利排行榜");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        profitLvAdapter = new LvProfitAdapter(this, new ArrayList<ProfitChart>(), lvProfit);
        lvProfit.setAdapter(profitLvAdapter);
    }

    private void requestData() {
        Jc11x5Factory.getInstance().getMonthTopProfit(mHandler);
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_MONTH_YL_SUCCESS:
                Bundle bundle = msg.getData();
                if(bundle != null){
                    List<ProfitChart>  lists = (List<ProfitChart>) bundle.getSerializable(ReturnStatus.KEY_GET_PROFIT_LIST);
                    profitLvAdapter.setList(lists);
                }
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

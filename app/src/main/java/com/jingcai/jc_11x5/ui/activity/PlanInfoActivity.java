package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.PlanInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.MainActivity;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanInfoActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_cai)
    TextView tvCai;
    @Bind(R.id.tv_plan_mc)
    TextView tvPlanMc;
    @Bind(R.id.tv_visType)
    TextView tvVisType;
    @Bind(R.id.tv_beginOrder)
    TextView tvBeginOrder;
    @Bind(R.id.tv_endOrder)
    TextView tvEndOrder;
    @Bind(R.id.tv_orderTotal)
    TextView tvOrderTotal;
    @Bind(R.id.tv_currentOrder)
    TextView tvCurrentOrder;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_multiples)
    TextView tvMultiples;
    @Bind(R.id.tv_planType)
    TextView tvPlanType;
    @Bind(R.id.tv_zhuCount)
    TextView tvZhuCount;
    @Bind(R.id.tv_finishTime)
    TextView tvFinishTime;
    @Bind(R.id.tv_bonus)
    TextView tvBonus;
    @Bind(R.id.tv_planFinish)
    TextView tvPlanFinish;
    @Bind(R.id.tv_totalCost)
    TextView tvTotalCost;
    @Bind(R.id.tv_checkPrice)
    TextView tvCheckPrice;

    private Handler mHandler;
    private App app;
    private String planId;
    private PlanInfo planInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_info);
        ButterKnife.bind(this);

        initData();
        tvTitle.setText("方案详情查看");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        requestData();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                planId = bundle.getString("planId");
            }
        }
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
        tvCai.setText(planInfo.getCaiType());
        tvPlanMc.setText(planInfo.getPlanName());
        int visType = planInfo.getVisType();
        if(visType == 1){
            tvVisType.setText("完全公开");
        }
        if(visType == 2){
            tvVisType.setText("任意支付公开");
        }
        tvBeginOrder.setText(planInfo.getBeginOrder());
        tvEndOrder.setText(planInfo.getEndOrder());
        tvOrderTotal.setText(String.valueOf(planInfo.getOrderTotal()));
        int state = planInfo.getState();
        if(state == 1){
            tvState.setText("正确");
        }else if(state == 2){
            tvState.setText("错误");
        }else{
            tvState.setText("未开");
        }
        tvCurrentOrder.setText(String.valueOf(planInfo.getCurrentOrder()));
        tvMultiples.setText(planInfo.getMultiples());
        int planType = planInfo.getPlanType();
        if(planType == 4){
            tvPlanType.setText("任选五");
        }
        if(planType == 8){
            tvPlanType.setText("前二直选");
        }
        if(planType == 9){
            tvPlanType.setText("前二组选");
        }
        if(planType == 10){
            tvPlanType.setText("前三直选");
        }
        if(planType == 11){
            tvPlanType.setText("前三组选");
        }

        tvZhuCount.setText(String.valueOf(planInfo.getZhuCount()));
        tvFinishTime.setText(planInfo.getFinishTime());
        tvBonus.setText(planInfo.getMinIncome());
        if(planInfo.isPlanFinish()){
            tvPlanFinish.setText("是");
        }else{
            tvPlanFinish.setText("否");
        }
        tvTotalCost.setText(String.valueOf(planInfo.getTotalCost()));
        tvCheckPrice.setText(planInfo.getCheckPrice());
    }

    private void requestData() {
        Jc11x5Factory.getInstance().getPlanInfo(mHandler, planId, app.getUser().getId());
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_PLANINFO_SUCCESS:
                planInfo = (PlanInfo) msg.obj;
                initView();
                ((MainActivity) getBaseContext().getApplicationContext()).removeFragment(0);
                ((MainActivity) getBaseContext().getApplicationContext()).removeFragment(1);
                ((MainActivity) getBaseContext().getApplicationContext()).removeFragment(2);
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

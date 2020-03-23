package com.jingcai.jc_11x5.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.PlanDetails;
import com.jingcai.jc_11x5.entity.PlanInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.util.Html4Text;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanDetailActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_mc)
    TextView tvMc;
    @Bind(R.id.tv_plan_mc)
    TextView tvPlanMc;
    @Bind(R.id.tv_qihao)
    TextView tvQihao;
    @Bind(R.id.tv_cai)
    TextView tvCai;
    @Bind(R.id.tv_fay)
    TextView tvFay;
    @Bind(R.id.tv_zqishu)
    TextView tvZqishu;
    @Bind(R.id.tv_plan_jd)
    TextView tvPlanJd;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_feiyong)
    TextView tvFeiyong;
    @Bind(R.id.tv_beishu)
    TextView tvBeishu;
    @Bind(R.id.tv_tznr_bt)
    TextView tvTznrBt;
    @Bind(R.id.bt_copy)
    Button btCopy;
    @Bind(R.id.tv_haoma)
    TextView tvHaoma;
    @Bind(R.id.bt_zhifu)
    Button btZhifu;

    private Handler mHandler;
    private App app;
    private String planId;
    private boolean isCheck;
    private boolean isCheckPrice;
    private DialogWiget dialogWiget;
    private ClipboardManager mClipboardManager;//剪切板管理工具类
    private ClipData mClipData;//剪切板Data对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        ButterKnife.bind(this);

        initData();
        tvTitle.setText("方案详情");
        ivHeaderLeft.setVisibility(View.VISIBLE);

        setListener();
        requestData();
    }

    @Override
    protected void initData() {
        dialogWiget = new DialogWiget();
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                planId = bundle.getString("planId");
                isCheck = bundle.getBoolean("isCheck", false);
                isCheckPrice = bundle.getBoolean("isCheckPrice", false);
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

    private void initPlanInfoView(PlanInfo planInfo) {
        tvMc.setText(planInfo.getPlanName());
        tvPlanMc.setText(planInfo.getPlanName());
        tvQihao.setText(planInfo.getBeginOrder() + "-" + planInfo.getEndOrder());
        tvCai.setText(planInfo.getCaiType());
        tvFay.setText(planInfo.getNickName());
        tvZqishu.setText(String.valueOf(planInfo.getOrderTotal()));
        tvPlanJd.setText(String.valueOf(planInfo.getCurrentOrder()));
        int state = planInfo.getState();
        if (state == 1) {
            tvState.setText("正确");
        } else if (state == 2) {
            tvState.setText("错误");
        } else {
            tvState.setText("未开");
        }
        tvBeishu.setText(planInfo.getMultiples());
        tvFeiyong.setText(String.valueOf(planInfo.getCheckPrice()));
        tvTznrBt.setText(Html4Text.mk(getPlanTypeMc(planInfo.getPlanType()) + " " + Html4Text.color(String.valueOf(planInfo.getZhuCount()), "#fa5b60") + "注"));
        if (planInfo.isBuy()) {
            btZhifu.setVisibility(View.GONE);
            tvHaoma.setText(planInfo.getPlanContent());
        } else {
            btZhifu.setVisibility(View.VISIBLE);
        }
        /*if(isCheck && isCheckPrice){
            btZhifu.setVisibility(View.VISIBLE);
        }else{
            btZhifu.setVisibility(View.GONE);
        }*/
    }

    private void initPlanDetailsView(PlanDetails planDetails) {
        if (planDetails == null) {
            return;
        }
        if(isCheck && isCheckPrice){
            if(planInfo.getVisType() == 5 || planInfo.getVisType() == 6){
                tvHaoma.setText(planDetails.getPlanContent());
            }else{
                tvHaoma.setText("该方案为收费方案，请点击“无忧币支付”按钮进行支付后获取号码！");
            }
        }else{
            String planContent = planDetails.getPlanContent();
            if (planContent != null && !TextUtils.isEmpty(planContent)) {
                tvHaoma.setText(planContent);
            }
        }
    }

    private String getPlanTypeMc(int planType) {
        String text = "";
        if (planType == 4) {
            text = "任选五";
        }
        if (planType == 8) {
            text = "前二直选";
        }
        if (planType == 9) {
            text = "前二组选";
        }
        if (planType == 10) {
            text = "前三直选";
        }
        if (planType == 11) {
            text = "前三组选";
        }
        return text;
    }
    private PlanDetails planDetails;
    private PlanInfo planInfo;

    private void setListener() {
        dialogWiget.setOnQueryOkListener(new DialogWiget.OnQueryOk() {
            @Override
            public void onQueryOkClick(View v) {
                dialogWiget.dismiss();
                Jc11x5Factory.getInstance().addOwnPlan(mHandler, planId, planInfo.getCheckPrice().toString());
            }
        });
        dialogWiget.setOnQueryCancelListener(new DialogWiget.OnQueryCancel() {
            @Override
            public void onQueryCancel(View v) {
                dialogWiget.dismiss();
            }
        });
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_PLANINFO_SUCCESS:
                planInfo = (PlanInfo) msg.obj;
                initPlanInfoView(planInfo);
                Jc11x5Factory.getInstance().getTopOneByPlanId(mHandler, planInfo);
                break;
            case HandlerWhat.GET_TOPONEBYPLANID_SUCCESS:
                planDetails = (PlanDetails) msg.obj;
//                initPlanDetailsView(planDetails);
                break;
            case HandlerWhat.ADD_OWNPLAN_SUCCESS:
                isUpdate = true;
                tvHaoma.setText(msg.obj.toString());
                planInfo.setVisType(5);
                planInfo.setBuy(true);
                break;
            default:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                break;
        }
    }

    private boolean isUpdate;

    private void setResultOk(){
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putBoolean("isUpdate", isUpdate);
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
    }

    private void requestData() {
        Jc11x5Factory.getInstance().getPlanInfo(mHandler, planId, app.getUser().getId());

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResultOk();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        setResultOk();
        finish();
    }

    @OnClick(R.id.bt_copy)
    public void onBtCopyClicked() {
        if(!planInfo.isBuy()){
            showMsg("该方案为收费方案，请点击“无忧币支付”按钮进行支付后获取号码！");
            return;
        }
        String haoma = tvHaoma.getText().toString().replaceAll("\r", "\n");
        haoma = haoma.replaceAll("\n+", "\n");
        mClipData = ClipData.newPlainText("copy_ssNum", haoma);
        //把clip对象放在剪贴板中
        mClipboardManager.setPrimaryClip(mClipData);
        showMsg("号码复制成功！");
    }

    @OnClick(R.id.bt_zhifu)
    public void onBtZhifuClicked() {
        if(planInfo.getVisType() == 5 || planInfo.getVisType() == 6){
            showMsg("当前已支付，可直接查看！");
        }else{
            dialogWiget.setOKText("支付");
            String price = tvFeiyong.getText().toString();
            dialogWiget.showCustomMessageQuery(this, "无忧币支付", Html4Text.mk("确定使用"+price+"无忧币，查看该方案内容"));
        }
    }

}

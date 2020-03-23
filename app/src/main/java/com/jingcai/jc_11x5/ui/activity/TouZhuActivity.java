package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.Lottery;
import com.jingcai.jc_11x5.entity.PlanBeiInfo;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.DateUtil;
import com.jingcai.jc_11x5.view.adapter.TdFangAnAdapter;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class TouZhuActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_jzsj)
    TextView tvJzsj;
    @Bind(R.id.et_beishu)
    EditText etBeishu;
    @Bind(R.id.et_zhqs)
    EditText etZhqs;
    @Bind(R.id.et_yingli_lv)
    EditText etYingliLv;
    @Bind(R.id.bt_shengcheng)
    Button btShengcheng;
    @Bind(R.id.tb_plan)
    SortableTableView tbPlan;
    @Bind(R.id.bt_touzhu)
    Button btTouzhu;

    private Handler mHandle;
    private boolean isSingle = true;
    private int planType;
    private boolean isTouzhu = false;
    private DialogWiget dialogWiget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_zhu);
        ButterKnife.bind(this);

        initData();
        initView();
        getDjs();
        setListener();

        mHandle.sendEmptyMessageDelayed(9, 1000);
    }

    private String zhuShu;
    private String leiXing;
    private String danbeiJj;
    private TdFangAnAdapter tdFangAnAdapter;
    private UserInfo userInfo;
    private Lottery lottery;
    private String postContent;
    private String checkPrice;
    private String selectType;

    protected void initData() {
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                leiXing =  bundle.getString("leixing");
                planType = bundle.getInt("planType");
                zhuShu = bundle.getString("zhushu");
                danbeiJj = bundle.getString("danbeiJj");
                //isSingle = bundle.getBoolean("isSingle");
                postContent = bundle.getString("postContent");
                checkPrice = bundle.getString("checkPrice");
                selectType = bundle.getString("selectType");
            }
        }
        userInfo = App.getInstance().getUser();
        lottery = App.getInstance().getLottery();
        ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
        jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间

        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }

        };
    }

    protected void initView() {
        dialogWiget = new DialogWiget();
        tvTitle.setText(leiXing);
        Drawable nav_up = getResources().getDrawable(R.mipmap.downarrow_white);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tvTitle.setCompoundDrawables(null, null, nav_up, null);
        tvTitle.setCompoundDrawablePadding(8);//设置图片和text之间的间距
        ivHeaderLeft.setVisibility(View.VISIBLE);

        /*if(isSingle){
            etZhqs.setText("1");
        }else{
            etZhqs.setText("5");
        }*/
        etZhqs.setText("1");
        etYingliLv.setText("20");

        TableColumnWeightModel columnModel = new TableColumnWeightModel(6);
        columnModel.setColumnWeight(0, 2);
        columnModel.setColumnWeight(1, 3);
        columnModel.setColumnWeight(2, 2);
        columnModel.setColumnWeight(3, 3);
        columnModel.setColumnWeight(4, 3);
        columnModel.setColumnWeight(5, 3);
        tbPlan.setColumnModel(columnModel);

        SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(this, "序号", "期号", "倍数", "累积投入", "正确盈利", "盈利率");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(this, R.color.gray));
        simpleTableHeaderAdapter.setTextSize(13);
        simpleTableHeaderAdapter.setPaddingLeft(5);
        simpleTableHeaderAdapter.setPaddingRight(5);
        tbPlan.setHeaderAdapter(simpleTableHeaderAdapter);
        int colorEvenRows = getResources().getColor(R.color.white);
        int colorOddRows = getResources().getColor(R.color.listview_bs);
        tbPlan.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
        tdFangAnAdapter = new TdFangAnAdapter(this, new ArrayList<PlanBeiInfo>());
        tbPlan.setDataAdapter(tdFangAnAdapter);
    }

    private void setListener() {
        dialogWiget.setOnQueryCancelListener(new DialogWiget.OnQueryCancel() {
            @Override
            public void onQueryCancel(View v) {
                setResultOk();
                finish();
            }
        });
    }

    private long ksTime;
    private long jsTime;
    private long dt = 0;
    private int orderNo;
    private void getDjs() {
        long ctime = DateUtil.getNowMills();//当前时间
        orderNo = CaiUtil.getCurrentPeriod();
        if (ctime > jsTime + 30000) {
            tvJzsj.setText("今天开奖已经结束！");
            mHandle.removeCallbacksAndMessages(null);
            return;
        }
        if (ctime < ksTime) {
            dt = ksTime - ctime;
        } else {
            long kjsj = (ctime - ksTime) % (20 * 60 * 1000);
            dt = 20 * 60 * 1000 - kjsj;
        }
        if (orderNo >= Integer.parseInt(lottery.getCount())) {
            tvJzsj.setText("今天开奖已经结束！");
            mHandle.removeCallbacksAndMessages(null);
            return;
        }
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_FASCLIST_SUCCESS:
                Bundle bundle = msg.getData();
                if(bundle != null){
                    List<PlanBeiInfo> lists = (List<PlanBeiInfo>) bundle.getSerializable(ReturnStatus.KEY_GET_FASC_LIST);
                    tdFangAnAdapter.getData().clear();
                    tdFangAnAdapter.getData().addAll(lists);
                    tdFangAnAdapter.notifyDataSetChanged();
                }
                break;
            case HandlerWhat.GET_TZLIST_SUCCESS://
                isTouzhu = true;
                //tdFangAnAdapter.getData().clear();
                //tdFangAnAdapter.notifyDataSetChanged();
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                btTouzhu.setClickable(true);
                dialogWiget.showCustomMessage(this, "方案发布成功！");
                break;
            case HandlerWhat.GET_TZLIST_FALIURE:
            case HandlerWhat.GET_TZLIST_TIMEOUT:
                btTouzhu.setClickable(true);
                if (msg.obj != null && !TextUtils.isEmpty(String.valueOf(msg.obj))) {
                    showMsg(String.valueOf(msg.obj));
                }
                break;
            case 9:
                dt = dt - 1000;
                if(dt<0){
                    dt =0;
                }
                //时间格式化
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                String hms = formatter.format(dt);
                if(tvJzsj!=null){
                    tvJzsj.setText("距离第下期："+hms);
                }
                mHandle.removeMessages(9);
                //发送消息，不断减时间
                mHandle.sendEmptyMessageDelayed(9, 1000);
                if (dt <= 0) {
                    getDjs();
                    Jc11x5Factory.getInstance().getLuckyNumber(mHandle);
                }
            case HandlerWhat.GET_LUCKYNUM_SUCCESS:
                break;
            case HandlerWhat.GET_LUCKYNUM_TIMEOUT:
            case HandlerWhat.GET_LUCKYNUM_FALIURE:
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
        setResultOk();
        finish();
    }

    private void setResultOk(){
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putBoolean("isTouzhu", isTouzhu);
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
    }

    @OnClick(R.id.tv_title)
    public void onTvTitleClicked() {
    }

    @OnClick(R.id.bt_shengcheng)
    public void onBtShengchengClicked() {
        String bCount = etBeishu.getText().toString();
        if(TextUtils.isEmpty(bCount)){
            showMsg("请填写起始倍数！");
            return;
        }
        double beginCount = Double.parseDouble(bCount);
        String zhqs = etZhqs.getText().toString();
        if(TextUtils.isEmpty(zhqs)){
            showMsg("请填写追号期数！");
            return;
        }
        int planPostCout = Integer.parseInt(zhqs);
        String yingliLv = etYingliLv.getText().toString();
        if(TextUtils.isEmpty(yingliLv)){
            showMsg("请填写盈利率！");
            return;
        }
        double minRate = Double.parseDouble(yingliLv);
        double danBeiCount = Double.parseDouble(zhuShu);
        double sliderBonus = Double.parseDouble(danbeiJj);
        Jc11x5Factory.getInstance().createPlan(mHandle, beginCount, danBeiCount, sliderBonus, minRate, planPostCout);
    }

    @OnClick(R.id.bt_touzhu)
    public void onBtTouzhuClicked() {
        long ctime = DateUtil.getNowMills();//当前时间
        long nextTime = DateUtil.parseTimeToMillis(DateUtil.getMingtianDate()+" 00:00:00");
        if(ctime > jsTime && ctime < nextTime){
            showMsg("今天开奖结束已停止详情内容!");
            return;
        }
        if(dt <= 60000){
            showMsg("开奖前1分钟停止详情内容!");
            return;
        }
        if(Integer.parseInt(etZhqs.getText().toString()) > 1){
            isSingle = false;
        }
        String bCount = etBeishu.getText().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("planName", leiXing);
        jsonObject.put("caiType", userInfo.getCaizhong());//彩类型
        jsonObject.put("userId", userInfo.getId());//用户ID
        jsonObject.put("planType", planType);//方案类型
        jsonObject.put("danBeiCount", Integer.parseInt(zhuShu));//单倍注数
        jsonObject.put("sliderBonus", Double.parseDouble(danbeiJj));//积分奖励
        jsonObject.put("checkPrice", checkPrice);//查看费用
        jsonObject.put("postContent", postContent);//选号
        jsonObject.put("beishu", bCount);//单倍的倍数
        jsonObject.put("isSingle", isSingle);//
        jsonObject.put("selectType", selectType);
        jsonObject.put("minlncome", etYingliLv.getText().toString());
        Jc11x5Factory.getInstance().okPlanPost(mHandle, jsonObject, tdFangAnAdapter.getData());
        btTouzhu.setClickable(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialogWiget.dismiss();
            setResultOk();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.CalculateInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.adapter.TdBtjsAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * 倍投利润计算
 */
public class ProfitJsActivity extends BaseActivity{

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_header_right)
    ImageView ivHeaderSet;
    @Bind(R.id.et_zhushu)
    EditText etZhushu;
    @Bind(R.id.et_qishu)
    EditText etQishu;
    @Bind(R.id.et_beishu)
    EditText etBeishu;
    @Bind(R.id.et_shouyi)
    EditText etShouyi;
    @Bind(R.id.tb_btyl)
    SortableTableView tbBtyl;
    @Bind(R.id.btn_set)
    Button btnSet;
    @Bind(R.id.btn_jisuan)
    Button btnJisuan;
    @Bind(R.id.tv_set_yingli)
    TextView tvSetYingli;

    private String tzzs, jhqs, tzbs, dzjj, yingli;
    private int ylType; //0:收益率 1:最低盈利
    private Handler mHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_js);
        ButterKnife.bind(this);
        initData();
        initHandler();
        initView();
        requestData();
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                tzzs = bundle.getString("tzzs");
                jhqs = bundle.getString("jhqs");
                tzbs = bundle.getString("tzbs");
                dzjj = bundle.getString("dzjj");
                yingli = bundle.getString("yingli");
                ylType = bundle.getInt("ylType");
            }
        }
    }

    protected void initHandler() {
        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }
    protected void initView() {
        tvTitle.setText("倍投计算结果");
        ivHeaderBack.setVisibility(View.VISIBLE);
        etZhushu.setText(tzzs);
        etQishu.setText(jhqs);
        etBeishu.setText(tzbs);
        if(ylType == 0){
            tvSetYingli.setText("收益率(%)");
        }else{
            tvSetYingli.setText("最低盈利");
        }
        etShouyi.setText(yingli);

        tbBtyl.setColumnCount(5);
        /*TableColumnWeightModel columnModel = new TableColumnWeightModel(5);
        columnModel.setColumnWeight(0, 3);
        columnModel.setColumnWeight(1, 4);
        columnModel.setColumnWeight(2, 3);
        columnModel.setColumnWeight(3, 3);
        columnModel.setColumnWeight(4, 4);
        tbBtyl.setColumnModel(columnModel);*/
        SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(this, "倍数", "本金", "总投入", "总收益","收益率");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(this, R.color.gray));
        simpleTableHeaderAdapter.setTextSize(14);
        tbBtyl.setHeaderAdapter(simpleTableHeaderAdapter);
    }

    protected void setListener() {

    }

    protected void requestData(){
        ProgressWidget.showProgressDialog(this, "正在加载中...");
        int count = Integer.parseInt(tzzs);
        int orderCount = Integer.parseInt(jhqs);
        int multiple = Integer.parseInt(tzbs);
        int bonus = Integer.parseInt(dzjj);
        if(ylType == 0){
            Jc11x5Factory.getInstance().getCalcProfitPer(mHandle, count, orderCount, multiple, bonus, Double.parseDouble(yingli));
        }else{
            Jc11x5Factory.getInstance().getCalcProfit(mHandle, count, orderCount, multiple, bonus, Integer.parseInt(yingli));
        }

    }

    private void handlerMsg(Message msg){
        switch (msg.what) {
            case HandlerWhat.GET_CALCPROFITPER_SUCCESS:
                Bundle bundle1 = msg.getData();
                if(bundle1 != null){
                    List<CalculateInfo> lists1 = (List<CalculateInfo>)bundle1.getSerializable(ReturnStatus.KEY_CALCPROFITPER_LIST);
                    if(lists1 != null && lists1.size() > 0){
                        TdBtjsAdapter btjsTableDataAdapter = new TdBtjsAdapter(this, lists1);
                        tbBtyl.setDataAdapter(btjsTableDataAdapter);
                    }
                }
                ProgressWidget.dismissProgressDialog();
                break;
            case HandlerWhat.GET_CALCPROFIT_SUCCESS:
                Bundle bundle2 = msg.getData();
                if(bundle2 != null){
                    List<CalculateInfo> lists2 = (List<CalculateInfo>)bundle2.getSerializable(ReturnStatus.KEY_CALCPROFIT_LIST);
                    if(lists2 != null && lists2.size() > 0){
                        TdBtjsAdapter btjsTableDataAdapter = new TdBtjsAdapter(this, lists2);
                        tbBtyl.setDataAdapter(btjsTableDataAdapter);
                    }
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

    private void setBack(){
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString("tzzs",etZhushu.getText().toString());
        extras.putString("jhqs",etQishu.getText().toString());
        extras.putString("tzbs",etBeishu.getText().toString());
        extras.putString("dzjj",dzjj);
        extras.putInt("ylType", ylType);
        extras.putString("yingli",etShouyi.getText().toString());
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderBackClicked() {
        setBack();
        this.finish();
    }


    @OnClick(R.id.btn_set)
    public void onBtnSetClicked() {
        setBack();
        this.finish();
    }

    @OnClick(R.id.btn_jisuan)
    public void onBtnJisuanClicked() {
        String tzzs = etZhushu.getText().toString();
        if(TextUtils.isEmpty(tzzs)){
            showMsg("请填写详情内容注数！");
            return;
        }
        String jhqs = etQishu.getText().toString();
        if(TextUtils.isEmpty(jhqs)){
            showMsg("请填写方案期数！");
            return;
        }
        String tzbs = etBeishu.getText().toString();
        if(TextUtils.isEmpty(tzbs)){
            showMsg("请填写详情内容倍数！");
            return;
        }
        String yingli = etShouyi.getText().toString();
        if(TextUtils.isEmpty(yingli)){
            if(ylType == 0){
                showMsg("请填写收益率！");
            }else{
                showMsg("请填写最低盈利！");
            }
            return;
        }
        this.tzzs = tzzs;
        this.jhqs = jhqs;
        this.tzbs = tzbs;
        this.yingli = yingli;
        requestData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setBack();
        }
        return super.onKeyDown(keyCode, event);
    }
}

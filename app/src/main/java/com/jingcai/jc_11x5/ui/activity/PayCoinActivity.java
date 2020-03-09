package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.JobPlanner;
import com.jingcai.jc_11x5.entity.JobPrice;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.adapter.LvJobPlannerAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class PayCoinActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_job_planner)
    ListView lvJobPlanner;
    @Bind(R.id.btn_pay)
    Button btnPay;

    private App app;
    private UserInfo userInfo;

    private Handler mHandler;
    private JobPrice jobPrice;
    private LvJobPlannerAdapter lvJobPlannerAdapter;
    private boolean isPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_coin);

        ButterKnife.bind(this);
        initData();
        initView();
        requestData();
    }

    private void requestData() {
        Jc11x5Factory.getInstance().payAllJob(mHandler, jobPrice);
        /*if (type == 1) {
            Jc11x5Factory.getInstance().getAllJobList(mHandler);
        } else if (type == 2) {
            Jc11x5Factory.getInstance().getSingleJobList(mHandler);
        }*/
    }

    @Override
    protected void initData() {
        app = App.getInstance();
        userInfo = app.getUser();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                jobPrice = (JobPrice) bundle.getSerializable("jobPrice");
            }
        }
        mHandler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    private void handlerMsg(Message msg) {
        Bundle bundle = msg.getData();
        switch (msg.what) {
            case HandlerWhat.GET_ALLJOB_LIST_SUCCESS:
                if (bundle != null) {
                    List<JobPlanner> lists = (List<JobPlanner>) bundle.getSerializable(ReturnStatus.KEY_GET_ALLJOB_LIST);
                    lvJobPlannerAdapter.setList(lists);
                } else {
                    Toast.makeText(this, "暂未获取到数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case HandlerWhat.GET_SINGLEJOB_LIST_SUCCESS:
                if (bundle != null) {
                    List<JobPlanner> lists = (List<JobPlanner>) bundle.getSerializable(ReturnStatus.KEY_GET_SINGLEJOB_LIST);
                    lvJobPlannerAdapter.setList(lists);
                } else {
                    Toast.makeText(this, "暂未获取到数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case HandlerWhat.PAY_ALLJOB_SUCCESS:
                showMsg(String.valueOf(msg.obj));
                break;
            case HandlerWhat.PAY_SINGLEJOB_SUCCESS:
                showMsg(String.valueOf(msg.obj));
                break;
            default:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                break;
        }
    }

    private void setResult(){
        if (isPay) {
            setResult(RESULT_OK);
        } else {
            setResult(-99);
        }
    }

    @Override
    protected void initView() {
        tvTitle.setText("包月支付");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        lvJobPlannerAdapter = new LvJobPlannerAdapter(this, new ArrayList<>(), lvJobPlanner);
        lvJobPlannerAdapter.setJobType(jobPrice.getState());
        lvJobPlanner.setAdapter(lvJobPlannerAdapter);

    }

    @OnItemClick(R.id.lv_job_planner)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (jobPrice.getState() == 2) {
            LvJobPlannerAdapter adapter = (LvJobPlannerAdapter) parent.getAdapter();
            List<JobPlanner> list = adapter.getList();
            for (JobPlanner bean : list) {//全部设为未选中
                bean.setChecked(false);
            }
            list.get(position).setChecked(true);//点击的设为选中
            adapter.setList(list);
        }
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        setResult();
        finish();
    }

    @OnClick(R.id.btn_pay)
    public void onBtnPayClicked() {
        int jobType = jobPrice.getState();
        if (jobType == 1) {
//            Jc11x5Factory.getInstance().payAllJob(mHandler, userInfo.getUserName(), jobPrice.getDays());
        } else if (jobType == 2) {
            JobPlanner jobPlanner = null;
            for (JobPlanner entity : lvJobPlannerAdapter.getList()) {
                if (entity.isChecked()) {
                    jobPlanner = entity;
                    break;
                }
            }
            if (jobPlanner == null) {
                showMsg("请至少选择一个计划员！");
                return;
            }
            Jc11x5Factory.getInstance().paySingleJob(mHandler, userInfo.getUserName(), jobPrice.getDays(), jobPlanner.getUserId() );
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
}

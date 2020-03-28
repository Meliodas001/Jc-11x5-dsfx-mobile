package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.JobDistribute;
import com.jingcai.jc_11x5.entity.JobPrice;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.ScrollListView;
import com.jingcai.jc_11x5.view.adapter.LvJobDistributeAdapter;
import com.jingcai.jc_11x5.view.adapter.LvJobPriceAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class JobPriceListActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_job_price)
    ScrollListView lvJobPrice;
    @Bind(R.id.lv_all_job)
    ListView lvAllJob;
    @Bind(R.id.ll_all)
    LinearLayout llAll;
    @Bind(R.id.lv_single_job)
    ListView lvSingleJob;
    @Bind(R.id.ll_single)
    LinearLayout llSingle;

    private App app;
    private UserInfo userInfo;

    private Handler mHandler;

    private LvJobPriceAdapter lvJobPriceAdapter;
    LvJobDistributeAdapter allAdapter;
    LvJobDistributeAdapter singleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_price_list);

        ButterKnife.bind(this);
        initData();
        initView();
        requestData();
    }

    private void requestData() {
//        Jc11x5Factory.getInstance().getAllJobDistribute(mHandler, userInfo.getId());
//        Jc11x5Factory.getInstance().getSingleJobDistribute(mHandler, userInfo.getId());
        Jc11x5Factory.getInstance().getJobPriceList(mHandler);
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
            case HandlerWhat.GET_JOBPRICE_LIST_SUCCESS:
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    List<JobPrice> lists = (List<JobPrice>) bundle.getSerializable(ReturnStatus.KEY_GET_JOBPRICE_LIST);
                    lvJobPriceAdapter.setList(lists);
                } else {
                    Toast.makeText(this, "暂未获取到数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case HandlerWhat.GET_ALLJOB_DIS_SUCCESS:
                Bundle bundle1 = msg.getData();
                /*if (bundle1 != null) {
                    List<JobDistribute> lists = (List<JobDistribute>) bundle1.getSerializable(ReturnStatus.KEY_GET_ALLJOB_DIS);
                    if (lists != null && lists.size() > 0) {
                        allAdapter.setList(lists);
                        llAll.setVisibility(View.VISIBLE);
                    }
                }*/
                break;
            case HandlerWhat.GET_SINGLEJOB_DIS_SUCCESS:
                Bundle bundle2 = msg.getData();
                /*if (bundle2 != null) {
                    List<JobDistribute> lists = (List<JobDistribute>) bundle2.getSerializable(ReturnStatus.KEY_GET_SINGLEJOB_DIS);
                    if (lists != null && lists.size() > 0) {
                        singleAdapter.setList(lists);
                        llAll.setVisibility(View.VISIBLE);
                    }
                }
                llSingle.setVisibility(View.VISIBLE);*/
                break;
            case HandlerWhat.PAY_ALLJOB_SUCCESS:
                if (msg.obj != null)
                    showMsg(String.valueOf(msg.obj));
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
    protected void initView() {
        tvTitle.setText("游戏卡价格列表");
        ivHeaderLeft.setVisibility(View.VISIBLE);

        /*llAll.setVisibility(View.VISIBLE);
        llSingle.setVisibility(View.VISIBLE);
        allAdapter = new LvJobDistributeAdapter(this, new ArrayList<>(), lvAllJob);
        allAdapter.setJobType(1);
        lvAllJob.setAdapter(allAdapter);
        singleAdapter = new LvJobDistributeAdapter(this, new ArrayList<>(), lvSingleJob);
        singleAdapter.setJobType(2);
        lvSingleJob.setAdapter(singleAdapter);*/

        lvJobPriceAdapter = new LvJobPriceAdapter(this, new ArrayList<>(), lvJobPrice);
        lvJobPrice.setAdapter(lvJobPriceAdapter);
        lvJobPriceAdapter.setOnJobClickListener(new LvJobPriceAdapter.OnJobClickListener() {
            @Override
            public void onClick(JobPrice jobPrice) {
                Jc11x5Factory.getInstance().payAllJob(mHandler, jobPrice);
//                startNewActivityForResultWithBundle(PayCoinActivity.class, bundle, 1010);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1010:
                if (resultCode == RESULT_OK) {
                    requestData();
                }
                break;
        }
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        finish();
    }

    @OnItemClick(R.id.lv_job_price)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JobPrice jobPrice = (JobPrice) parent.getAdapter().getItem(position);


    }


}

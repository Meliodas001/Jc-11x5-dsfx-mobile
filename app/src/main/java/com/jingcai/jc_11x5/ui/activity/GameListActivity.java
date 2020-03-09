package com.jingcai.jc_11x5.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.PlanDetails;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.util.DateUtil;
import com.jingcai.jc_11x5.view.adapter.LvGameAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameListActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_game)
    ListView lvGame;

    private Handler mHandler;
    private App app;
    private LvGameAdapter lvGameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
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
    protected void initView() {
        tvTitle.setText("游戏记录");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        lvGameAdapter = new LvGameAdapter(this, new ArrayList<>(), lvGame);
        lvGame.setAdapter(lvGameAdapter);
        lvGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanDetails planDetails = (PlanDetails)parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("planId", planDetails.getPlanId());
                bundle.putBoolean("isCheck", false);
                bundle.putBoolean("isCheckPrice", false);
                startNewActivityWithBundle(PlanDetailActivity.class, bundle);
            }
        });
    }

    private void requestData() {
        ProgressWidget.showProgressDialog(this, "正在加载中...");
        String date = DateUtil.getCurrentDate().replaceAll("-", "");
        date = date.substring(2, date.length());
        String userId = app.getUser().getId();
        Jc11x5Factory.getInstance().getTodayDetailsByUserId(mHandler, app.getUser().getCaizhongMc(), userId, date + "01", date + app.getLottery().getCount());
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_TZJL_SUCCESS:
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    List<PlanDetails> lists = (List<PlanDetails>) bundle.getSerializable(ReturnStatus.KEY_GET_TOUZHU_LIST);
                    lvGameAdapter.setList(lists);
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

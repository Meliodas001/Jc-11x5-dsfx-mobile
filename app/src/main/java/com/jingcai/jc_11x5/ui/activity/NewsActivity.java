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
import com.jingcai.jc_11x5.entity.News;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.adapter.LvNewsAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_news)
    ListView lvNews;

    private Handler mHandler;
    private App app;
    private LvNewsAdapter lvNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        initData();
        initView();
        requestData();
    }

    private void requestData() {
        Jc11x5Factory.getInstance().getNewsList(mHandler);
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
        tvTitle.setText("消息中心");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        lvNewsAdapter = new LvNewsAdapter(this, new ArrayList<>(), lvNews);
        lvNews.setAdapter(lvNewsAdapter);
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_NEWS_LIST_SUCCESS:
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    List<News> lists = (List<News>) bundle.getSerializable(ReturnStatus.KEY_GET_NES_LIST);
                    lvNewsAdapter.setList(lists);
                } else {
                    showMsg("暂无数据");
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

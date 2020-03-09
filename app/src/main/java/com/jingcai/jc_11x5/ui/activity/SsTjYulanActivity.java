package com.jingcai.jc_11x5.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.view.adapter.LvSuoShuiTjAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SsTjYulanActivity extends AppCompatActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_content)
    ListView lvContent;
    private ArrayList<String> lists;
    private LvSuoShuiTjAdapter suoShuiTiaojianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss_tj_yulan);
        ButterKnife.bind(this);
        initData();
        tvTitle.setText("缩水条件预览");
        ivHeaderBack.setVisibility(View.VISIBLE);
        suoShuiTiaojianAdapter = new LvSuoShuiTjAdapter(this, lists, lvContent);
        lvContent.setAdapter(suoShuiTiaojianAdapter);
    }

    private void initData() {
        lists = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                lists = bundle.getStringArrayList("tjResult");
            }
        }
    }

    @OnClick(R.id.iv_header_left)
    public void onViewClicked() {
        finish();
    }
}

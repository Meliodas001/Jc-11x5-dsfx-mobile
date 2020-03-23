package com.jingcai.jc_11x5.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.view.adapter.TdSsglResultAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class SsglResultActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tb_ssjg)
    SortableTableView tbSsjg;
    @Bind(R.id.btn_copy)
    Button btnCopy;
    @Bind(R.id.tv_header_right)
    TextView tvHeaderRight;

    private ArrayList<String> lists;
    private TdSsglResultAdapter ssglResultAdapter;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssgl_result);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    protected void initData() {
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        lists = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                lists = bundle.getStringArrayList("numResult");
            }
        }
    }

    protected void initView() {
        tvTitle.setText("组合过滤结果");
        ivHeaderBack.setVisibility(View.VISIBLE);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(6);
        columnModel.setColumnWeight(0, 2);
        columnModel.setColumnWeight(1, 6);
        columnModel.setColumnWeight(2, 3);
        columnModel.setColumnWeight(3, 3);
        columnModel.setColumnWeight(4, 3);
        columnModel.setColumnWeight(5, 3);
        tbSsjg.setColumnModel(columnModel);

        SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(this, "序号", "号码", "大小比", "奇偶比", "质合比", "012路比");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(this, R.color.gray));
        simpleTableHeaderAdapter.setTextSize(13);
        simpleTableHeaderAdapter.setPaddingLeft(5);
        simpleTableHeaderAdapter.setPaddingRight(5);
        tbSsjg.setHeaderAdapter(simpleTableHeaderAdapter);
        int colorEvenRows = getResources().getColor(R.color.white);
        int colorOddRows = getResources().getColor(R.color.listview_bs);
        tbSsjg.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
        ssglResultAdapter = new TdSsglResultAdapter(this, lists);
        tbSsjg.setDataAdapter(ssglResultAdapter);
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setText("共"+ lists.size() +"条数据");
    }

    @OnClick(R.id.iv_header_left)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.btn_copy)
    public void onBtnCopyClicked() {
        StringBuffer sb = new StringBuffer("");
        for (String str : lists) {
            sb.append(str).append("\n");
        }
        mClipData = ClipData.newPlainText("copy_ssNum", sb.toString());
        //把clip对象放在剪贴板中
        mClipboardManager.setPrimaryClip(mClipData);
        showMsg("号码复制成功！");
    }
}

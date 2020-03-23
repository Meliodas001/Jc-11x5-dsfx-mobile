package com.jingcai.jc_11x5.ui.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BtjsActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_tzzs)
    EditText etTzzs;
    @Bind(R.id.et_jhqs)
    EditText etJhqs;
    @Bind(R.id.et_tzbs)
    EditText etTzbs;
    @Bind(R.id.et_dzjj)
    EditText etDzjj;
    @Bind(R.id.et_yl)
    EditText etYl;
    @Bind(R.id.btn_jisuan)
    Button btnJisuan;
    @Bind(R.id.tv_choice)
    TextView tvChoice;
    @Bind(R.id.iv_header_right)
    ImageView ivHeaderRight;

    private int ylType;
    private static final int BTJS = 1001;

    /** popup窗口里的ListView */
    private ListView lvSyms;

    /** popup窗口 */
    private PopupWindow typeSelectPopup;

    private List<String> moshiData;

    /** 数据适配器 */
    private ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btjs);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        moshiData = new ArrayList<>();
        moshiData.add("收益率%");
        moshiData.add("最低盈利");
    }

    @Override
    protected void initView() {
        tvTitle.setText(getResources().getString(R.string.menu_bottom_btjs));
        ivHeaderLeft.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化popup窗口
     */
    private void initSelectPopup() {
        lvSyms = new ListView(this);
        // 设置适配器
        dataAdapter = new ArrayAdapter<String>(this, R.layout.popup_text_item, moshiData);
        lvSyms.setAdapter(dataAdapter);

        // 设置ListView点击事件监听
        lvSyms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 在这里获取item数据
                String value = moshiData.get(position);
                if(position == 0){
                    ylType = 0;
                }else{
                    ylType = 1;
                }
                // 把选择的数据展示对应的TextView上
                tvChoice.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        typeSelectPopup = new PopupWindow(lvSyms, tvChoice.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_popup_corner);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case BTJS:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if(bundle == null){
                        return;
                    }
                    String tzzs = bundle.getString("tzzs");
                    etTzzs.setText(tzzs);
                    String jhqs = bundle.getString("jhqs");
                    etJhqs.setText(jhqs);
                    String tzbs = bundle.getString("tzbs");
                    etTzbs.setText(tzbs);
                    String dzjj = bundle.getString("dzjj");
                    etDzjj.setText(dzjj);
                    ylType = bundle.getInt("ylType");
                    if(ylType == 0){
                        tvChoice.setText(moshiData.get(0));
                    }else{
                        tvChoice.setText(moshiData.get(1));
                    }
                    String yingli = bundle.getString("yingli");
                    etYl.setText(yingli);
                }
            default:
                break;
        }
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        finish();
    }

    @OnClick(R.id.btn_jisuan)
    public void onBtnJisuanClicked() {

        String tzzs = etTzzs.getText().toString();
        String jhqs = etJhqs.getText().toString();
        String tzbs = etTzbs.getText().toString();
        String dzjj = etDzjj.getText().toString();
        String yingli = etYl.getText().toString();
        if(TextUtils.isEmpty(tzzs)){
            showMsg("请填写详情内容注数！");
            return;
        }
        if(TextUtils.isEmpty(jhqs)){
            showMsg("请填写方案期数！");
            return;
        }
        if(TextUtils.isEmpty(tzbs)){
            showMsg("请填写详情内容倍数！");
            return;
        }
        if(TextUtils.isEmpty(dzjj)){
            showMsg("请填写单注奖金");
            return;
        }
        if(TextUtils.isEmpty(yingli)){
            if(ylType == 0){
                showMsg("请填写收益率！");
            }else{
                showMsg("请填写最低盈利！");
            }
            return;
        }
        Bundle extras = new Bundle();
        extras.putString("tzzs",tzzs);
        extras.putString("jhqs",jhqs);
        extras.putString("tzbs",tzbs);
        extras.putString("dzjj",dzjj);
        extras.putInt("ylType", ylType);
        extras.putString("yingli",yingli);
        startNewActivityForResultWithBundle(ProfitJsActivity.class, extras, BTJS);
    }

    @OnClick(R.id.iv_header_right)
    public void onIvHeaderRightClicked() {
    }

    @OnClick(R.id.tv_choice)
    public void onTvChoiceClicked() {
        // 点击控件后显示popup窗口
        initSelectPopup();
        // 使用isShowing()检查popup窗口是否在显示状态
        if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
            typeSelectPopup.showAsDropDown(tvChoice, 0, 5);
        }
    }
}

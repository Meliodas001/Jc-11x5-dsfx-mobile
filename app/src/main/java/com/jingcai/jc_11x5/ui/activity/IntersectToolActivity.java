package com.jingcai.jc_11x5.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.view.PEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class IntersectToolActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_zhu_a)
    TextView tvZhuA;
    @Bind(R.id.bt_paste_a)
    Button btPasteA;
    @Bind(R.id.bt_clean_a)
    Button btCleanA;
    @Bind(R.id.et_haoma_a)
    PEditText etHaomaA;
    @Bind(R.id.tv_zhu_b)
    TextView tvZhuB;
    @Bind(R.id.bt_paste_b)
    Button btPasteB;
    @Bind(R.id.bt_clean_b)
    Button btCleanB;
    @Bind(R.id.et_haoma_b)
    PEditText etHaomaB;
    @Bind(R.id.bt_ab_jiao)
    Button btAbJiao;
    @Bind(R.id.bt_ab_bing)
    Button btAbBing;
    @Bind(R.id.bt_a_pai_b)
    Button btAPaiB;
    @Bind(R.id.bt_b_pai_a)
    Button btBPaiA;
    @Bind(R.id.rb_kongge)
    RadioButton rbKongge;
    @Bind(R.id.rb_douhao)
    RadioButton rbDouhao;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.bt_copy)
    Button btCopy;
    @Bind(R.id.tv_haoma_result)
    TextView tvHaomaResult;
    @Bind(R.id.tv_zhu_result)
    TextView tvZhuResult;
    @Bind(R.id.tv_leixing_a)
    TextView tvLeixingA;
    @Bind(R.id.tv_leixing_b)
    TextView tvLeixingB;

    private int defaultCountA = 0;
    private int defaultCountB = 0;
    private boolean isKongGe = true;

    private ClipboardManager mClipboardManager;//剪切板管理工具类
    private ClipData mClipData;//剪切板Data对象

    private App app;
    private Handler mHandle;

    private boolean isSaveHaoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intersect_tool);
        ButterKnife.bind(this);
        initData();
        initHandler();
        initView();
        setListener();
    }

    @Override
    protected void initData() {
        app = App.getInstance();
        isSaveHaoma = true;
        mClipboardManager = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
    }

    protected void initHandler() {
        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }

        };
    }

    @Override
    protected void initView() {
        tvTitle.setText("交集工具");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        etHaomaA.setLongClickable(false);
        etHaomaB.setLongClickable(false);
        getPreference();
    }

    private void handlerMsg(Message msg) {
        try {
            switch (msg.what) {
                case HandlerWhat.GET_JISUAN_JIAO_SUCCESS:
                case HandlerWhat.GET_JISUAN_BING_SUCCESS:
                case HandlerWhat.GET_A_PAI_B_SUCCESS:
                case HandlerWhat.GET_B_PAI_A_SUCCESS:
                    String[] result = (String[]) msg.obj;
                    tvZhuResult.setText("共" + result[0] + "注");
                    if (isKongGe) {
                        tvHaomaResult.setText(result[1]);
                    } else {
                        tvHaomaResult.setText(result[1].replace(" ", ","));
                    }
                    isSaveHaoma = false;
                    break;
                default:

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void setListener() {
        etHaomaA.addTextChangedListener(new TextWatcher() {

            private int mPreviousLength;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mPreviousLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mPreviousLength > s.length()) {
                    String str = s.toString().trim();
                    String[] result = str.split("\n");
                    if(result.length == 2){
                        int defaultCount = result[0].split(" ").length;
                        if(defaultCount != defaultCountA){
                            defaultCountA = defaultCount;
                            tvLeixingA.setText(String.valueOf(defaultCountA));
                        }
                    }
                    return;
                }
                if (s.toString().endsWith("  ")) {
                    s.delete(s.toString().length() - 1, s.toString().length());
                    return;
                }
                String str = s.toString().trim();
                String[] result = str.split("\n");
                /*if(result.length == 2 && defaultCountA == 0){
                    defaultCountA = result[0].split(" ").length;
                    tvLeixingA.setText(String.valueOf(defaultCountA));
                }*/
                if(result.length == 1){
                    defaultCountA = result[0].split(" ").length;
                    tvLeixingA.setText(String.valueOf(defaultCountA));
                }

                if(defaultCountA == 0){
                    return;
                }
                int wzz = str.lastIndexOf("\n");
                String endStr;
                if (wzz > 0) {
                    endStr = str.substring(str.lastIndexOf("\n"), str.length());
                } else {
                    endStr = str;
                }
                String[] array = endStr.trim().split(" ");
                if (array.length > defaultCountA && !str.endsWith("\n")) {
                    int wz = str.lastIndexOf("\n") + endStr.lastIndexOf(" ");
                    s.replace(wz + 1, wz + 2, "\n");
                }
                int zhu = etHaomaA.getText().toString().trim().split("\n").length;
                tvZhuA.setText(String.valueOf(zhu));
            }
        });
        etHaomaB.addTextChangedListener(new TextWatcher() {

            private int mPreviousLength;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mPreviousLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mPreviousLength > s.length()) {
                    String str = s.toString().trim();
                    String[] result = str.split("\n");
                    if(result.length == 2){
                        int defaultCount = result[0].split(" ").length;
                        if(defaultCount != defaultCountB){
                            defaultCountB = defaultCount;
                            tvLeixingB.setText(String.valueOf(defaultCountB));
                        }
                    }
                    return;
                }
                if (s.toString().endsWith("  ")) {
                    s.delete(s.toString().length() - 1, s.toString().length());
                    return;
                }
                String str = s.toString().trim();
                String[] result = str.split("\n");
                /*if(result.length == 2 && defaultCountB == 0){
                    defaultCountB = result[0].split(" ").length;
                    tvLeixingB.setText(String.valueOf(defaultCountB));
                }*/
                if(result.length == 1){
                    defaultCountB = result[0].split(" ").length;
                    tvLeixingB.setText(String.valueOf(defaultCountB));
                }
                if(defaultCountB == 0){
                    return;
                }
                int wzz = str.lastIndexOf("\n");
                String endStr;
                if (wzz > 0) {
                    endStr = str.substring(str.lastIndexOf("\n"), str.length());
                } else {
                    endStr = str;
                }
                String[] array = endStr.trim().split(" ");
                if (array.length > defaultCountB && !str.endsWith("\n")) {
                    int wz = str.lastIndexOf("\n") + endStr.lastIndexOf(" ");
                    s.replace(wz + 1, wz + 2, "\n");
                }
                int zhu = etHaomaB.getText().toString().trim().split("\n").length;
                tvZhuB.setText(String.valueOf(zhu));
            }
        });
    }

    public void getPreference() {
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_JIAOJI_KEY, MODE_PRIVATE);
        String haomaA = pref.getString(Constants.PREFERENCE_SHARED_HAOMAA, "");
        if(!TextUtils.isEmpty(haomaA)){
            setHaomaAtoView(haomaA);
        }
        String haomaB = pref.getString(Constants.PREFERENCE_SHARED_HAOMAB, "");
        if(!TextUtils.isEmpty(haomaB)){
            setHaomaBtoView(haomaB);
        }
    }

    private void setHaomaAtoView(String haoma){
        etHaomaA.setText(haoma);
        int defaultCount = 0;
        String text = haoma.replaceAll(",", " ");
        if (!TextUtils.isEmpty(text)) {
            text = text.replaceAll("\n+", "\n");
            text = text.replaceAll(" +", " ");
            if (text.length() > 1) {
                if (text.substring(0, 1).equals("\n")) {
                    text = text.substring(1, text.length());
                }
                if (text.substring(text.length() - 1, text.length()).equals("\n")) {
                    text = text.substring(0, text.length() - 1);
                }
            }
            String[] array = text.split("\n");
            if(array.length > 0){
                defaultCount = array[0].split(" ").length;
            }
            for (String str : array) {
                int length = str.split(" ").length;
                if (length > 0 && length != defaultCount) {
                    return;
                }
            }
        }
        defaultCountA = defaultCount;
        tvLeixingA.setText(String.valueOf(defaultCount));
        int zhu = etHaomaA.getText().toString().split("\n").length;
        tvZhuA.setText(String.valueOf(zhu));
    }

    private void setHaomaBtoView(String haoma){
        etHaomaB.setText(haoma);
        int defaultCount = 0;
        String text = haoma.replaceAll(",", " ");
        if (!TextUtils.isEmpty(text)) {
            text = text.replaceAll("\n+", "\n");
            text = text.replaceAll(" +", " ");
            if (text.length() > 1) {
                if (text.substring(0, 1).equals("\n")) {
                    text = text.substring(1, text.length());
                }
                if (text.substring(text.length() - 1, text.length()).equals("\n")) {
                    text = text.substring(0, text.length() - 1);
                }
            }
            String[] array = text.split("\n");
            if(array.length > 0){
                defaultCount = array[0].split(" ").length;
            }
            for (String str : array) {
                int length = str.split(" ").length;
                if (length > 0 && length != defaultCount) {
                    return;
                }
            }
        }
        defaultCountB = defaultCount;
        tvLeixingB.setText(String.valueOf(defaultCount));
        int zhu = etHaomaB.getText().toString().split("\n").length;
        tvZhuB.setText(String.valueOf(zhu));
    }

    @OnClick(R.id.iv_header_left)
    public void onIvHeaderLeftClicked() {
        finish();
    }

    @OnCheckedChanged(R.id.rb_kongge)
    public void onRbChooseCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isKongGe = true;
        }
    }

    @OnCheckedChanged(R.id.rb_douhao)
    public void onRbPasteCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isKongGe = false;
        }
    }

    @OnClick(R.id.bt_paste_a)
    public void onBtPasteAClicked() {
        mClipData = mClipboardManager.getPrimaryClip();//GET贴板是否有内容
        if (mClipData == null) {
            return;
        }
        ClipData.Item item = mClipData.getItemAt(0);//获取到内容
        if (item == null) {
            return;
        }
        int defaultCount = 0;
        String text = item.getText().toString().replaceAll(",", " ");
        if (!TextUtils.isEmpty(text)) {
            text = text.replaceAll("\n+", "\n");
            text = text.replaceAll(" +", " ");
            if (text.length() > 1) {
                if (text.substring(0, 1).equals("\n")) {
                    text = text.substring(1, text.length());
                }
                if (text.substring(text.length() - 1, text.length()).equals("\n")) {
                    text = text.substring(0, text.length() - 1);
                }
            }
            String[] array = text.split("\n");
            if(array.length > 0){
                defaultCount = array[0].split(" ").length;
            }
            for (String str : array) {
                int length = str.split(" ").length;
                if (length > 0 && length != defaultCount) {
                    showMsg("粘贴数据格式有误！");
                    return;
                }
            }
        }
        defaultCountA = defaultCount;
        tvLeixingA.setText(String.valueOf(defaultCount));
        String aText = etHaomaA.getText().toString();
        if (TextUtils.isEmpty(aText)) {
            etHaomaA.setText(text + "\n");
        } else {
            if(aText.lastIndexOf("\n") == aText.length()-1){
                etHaomaA.setText(aText + text + "\n");
            } else {
                etHaomaA.setText(aText + "\n" + text + "\n");
            }
        }
        int zhu = etHaomaA.getText().toString().split("\n").length;
        tvZhuA.setText(String.valueOf(zhu));
    }

    @OnClick(R.id.bt_clean_a)
    public void onBtCleanAClicked() {
        etHaomaA.setText("");
        tvZhuA.setText("0");
        tvLeixingA.setText("");
        defaultCountA = 0;
    }

    @OnClick(R.id.bt_paste_b)
    public void onBtPasteBClicked() {
        mClipData = mClipboardManager.getPrimaryClip();//GET贴板是否有内容
        if (mClipData == null) {
            return;
        }
        ClipData.Item item = mClipData.getItemAt(0);//获取到内容
        if (item == null) {
            return;
        }
        int defaultCount = 0;
        String text = item.getText().toString().replaceAll(",", " ");
        if (!TextUtils.isEmpty(text)) {
            text = text.replaceAll("\n+", "\n");
            text = text.replaceAll(" +", " ");
            if (text.length() > 1) {
                if (text.substring(0, 1).equals("\n")) {
                    text = text.substring(1, text.length());
                }
                if (text.substring(text.length() - 1, text.length()).equals("\n")) {
                    text = text.substring(0, text.length() - 1);
                }
            }
            String[] array = text.split("\n");
            if(array.length > 0){
                defaultCount = array[0].split(" ").length;
            }
            for (String str : array) {
                int length = str.split(" ").length;
                if (length > 0 && length != defaultCount) {
                    showMsg("粘贴数据格式有误！");
                    return;
                }
            }
        }
        defaultCountB = defaultCount;
        tvLeixingB.setText(String.valueOf(defaultCount));
        String bText = etHaomaB.getText().toString();
        if (TextUtils.isEmpty(bText)) {
            etHaomaB.setText(text + "\n");
        } else {
            if(bText.lastIndexOf("\n") == bText.length()-1){
                etHaomaB.setText(bText + text + "\n");
            }else{
                etHaomaB.setText(bText + "\n" + text + "\n");
            }

        }
        int zhu = etHaomaB.getText().toString().split("\n").length;
        tvZhuB.setText(String.valueOf(zhu));
    }

    @OnClick(R.id.bt_clean_b)
    public void onBtCleanBClicked() {
        etHaomaB.setText("");
        tvZhuB.setText("0");
        tvLeixingB.setText("");
        defaultCountB = 0;
    }

    @OnClick(R.id.bt_ab_jiao)
    public void onBtAbJiaoClicked() {
        String haomaA = etHaomaA.getText().toString();
        if (TextUtils.isEmpty(haomaA)) {
            showMsg("A数据不能为空！");
            return;
        }
        String haomaB = etHaomaB.getText().toString();
        if (TextUtils.isEmpty(haomaB)) {
            showMsg("B数据不能为空！");
            return;
        }
        haomaA = haomaA.replaceAll(" +", " ");
        haomaA = haomaA.replaceAll("\n+", "\n");
        haomaB = haomaB.replaceAll(" +", " ");
        haomaB = haomaB.replaceAll("\n+", "\n");
        Jc11x5Factory.getInstance().calculationJiao(mHandle, haomaA, haomaB);
    }

    @OnClick(R.id.bt_ab_bing)
    public void onBtAbBingClicked() {
        String haomaA = etHaomaA.getText().toString();
        if (TextUtils.isEmpty(haomaA)) {
            showMsg("A数据不能为空！");
            return;
        }
        String haomaB = etHaomaB.getText().toString();
        if (TextUtils.isEmpty(haomaB)) {
            showMsg("B数据不能为空！");
            return;
        }
        haomaA = haomaA.replaceAll(" +", " ");
        haomaA = haomaA.replaceAll("\n+", "\n");
        haomaB = haomaB.replaceAll(" +", " ");
        haomaB = haomaB.replaceAll("\n+", "\n");
        Jc11x5Factory.getInstance().calculationBing(mHandle, haomaA, haomaB);
    }

    @OnClick(R.id.bt_a_pai_b)
    public void onBtAPaiBClicked() {
        String haomaA = etHaomaA.getText().toString();
        if (TextUtils.isEmpty(haomaA)) {
            showMsg("A数据不能为空！");
            return;
        }
        String haomaB = etHaomaB.getText().toString();
        if (TextUtils.isEmpty(haomaB)) {
            showMsg("B数据不能为空！");
            return;
        }
        haomaA = haomaA.replaceAll(" +", " ");
        haomaA = haomaA.replaceAll("\n+", "\n");
        haomaB = haomaB.replaceAll(" +", " ");
        haomaB = haomaB.replaceAll("\n+", "\n");
        Jc11x5Factory.getInstance().calculationApaiB(mHandle, haomaA, haomaB);
    }

    @OnClick(R.id.bt_b_pai_a)
    public void onBtBPaiAClicked() {
        String haomaA = etHaomaA.getText().toString();
        if (TextUtils.isEmpty(haomaA)) {
            showMsg("A数据不能为空！");
            return;
        }
        String haomaB = etHaomaB.getText().toString();
        if (TextUtils.isEmpty(haomaB)) {
            showMsg("B数据不能为空！");
            return;
        }
        Jc11x5Factory.getInstance().calculationBpaiA(mHandle, haomaA, haomaB);
    }

    @OnClick(R.id.bt_copy)
    public void onBtCopyClicked() {
        String result = tvHaomaResult.getText().toString();
        mClipData = ClipData.newPlainText("copy_jisuan_result", result);
        //把clip对象放在剪贴板中
        mClipboardManager.setPrimaryClip(mClipData);
        showMsg("号码复制成功！");
    }

    public void setPreference() {
        SharedPreferences.Editor edit = getSharedPreferences(Constants.PREFERENCE_JIAOJI_KEY, MODE_PRIVATE).edit();
        String haoMaA = etHaomaA.getText().toString().trim();
        edit.putString(Constants.PREFERENCE_SHARED_HAOMAA, haoMaA);
        String haoMaB = etHaomaB.getText().toString().trim();
        edit.putString(Constants.PREFERENCE_SHARED_HAOMAB, haoMaB);
        edit.commit();
    }

    public void clearPreference() {
        SharedPreferences.Editor edit = getSharedPreferences(Constants.PREFERENCE_JIAOJI_KEY, MODE_PRIVATE).edit();
        edit.putString(Constants.PREFERENCE_SHARED_HAOMAA, "");
        edit.putString(Constants.PREFERENCE_SHARED_HAOMAB, "");
        edit.commit();
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(isSaveHaoma){
            setPreference();
        }else{
            clearPreference();
        }
    }
}

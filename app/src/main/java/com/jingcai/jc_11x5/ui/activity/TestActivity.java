package com.jingcai.jc_11x5.ui.activity;

import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.db.SqliteHelper;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.util.FileUtil;
import com.jingcai.jc_11x5.util.PromptUtil;
import com.jingcai.jc_11x5.util.SDCardUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_home_data)
    Button btnHomeData;
    @Bind(R.id.btn_kj_data)
    Button btnKjData;
    @Bind(R.id.et_haoma)
    EditText etHaoma;
    @Bind(R.id.btn_haoma_data)
    Button btnHaomaData;


    private App app;
    private UserInfo user;
    private Handler mHandle;
    private SqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        helper = SqliteHelper.getHelper();
        tvTitle.setText("数据库测试");
        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };

        app = App.getInstance();
        user = app.getUser();

        etHaoma.setText("01 02 03 04 05\n" + "01 02 03 04 05");

        setListener();
    }

    private void setListener(){
        etHaoma.addTextChangedListener(new TextWatcher() {
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
                if(mPreviousLength > s.length()){
                    return;
                }
                if(s.toString().endsWith("  ")){
                    s.delete(s.toString().length()-1, s.toString().length());
                    return;
                }
                String str = s.toString().trim();
                String endStr = str.substring(str.lastIndexOf("\n"), str.length());
                if(TextUtils.isEmpty(endStr)){
                    return;
                }
                String[] array = endStr.trim().split(" ");
                if(array.length > 5 && !str.endsWith("\n")){
                    int wz = str.lastIndexOf("\n")+endStr.lastIndexOf(" ");
                    s.replace(wz, wz+1, "\n");
                }
            }
        });
        registerClipEvents();
    }

    private void registerClipEvents() {

        final ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        manager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                    CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                    if (addedText != null) {
                    }
                }
            }
        });
    }

    private void handlerMsg(Message msg) {
        try {
            switch (msg.what) {
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearDb() {
        //数据库删除
        try {
            JSONObject json = helper.deleteDatabase();
            //helper.createTable(helper.getConnectionSource());
            if (json == null) {
                showMsg("JSONException");
            } else {
                if (json.getBoolean("isContain")) {
                    if (json.getBoolean("isSuccess")) {
                        showMsg("数据库删除成功！");
                    } else {
                        showMsg("数据库删除失败！");
                    }
                } else {
                    showMsg(json.getString("text"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    @OnClick(R.id.btn_home_data)
    public void onBtnHomeDataClicked() {
        Jc11x5Factory.getInstance().getHomeData(mHandle, "18392107387", "广东11选5");
    }

    @OnClick(R.id.btn_kj_data)
    public void onBtnKjDataClicked() {
        Jc11x5Factory.getInstance().getLuckyNumberList(mHandle, "广东11选5");
    }

    @OnClick(R.id.btn_kjsj_data)
    public void onBtnKjsjDataClicked() {
        Jc11x5Factory.getInstance().getBeginTime(mHandle, "广东11选5");
    }

    @OnClick(R.id.btn_zyl_data)
    public void onBtnZylDataClicked() {
        Jc11x5Factory.getInstance().getWeekTopProfit(mHandle);
    }

    @OnClick(R.id.btn_ormlite_delete)
    public void onBtnOrmliteDeleteClicked() {
        clearDb();
    }

    @OnClick(R.id.btn_ormlite_clean)
    public void onBtnOrmliteCleanClicked() {
        helper.claerTable();
        showMsg("数据库清除成功！");
    }

    @OnClick(R.id.btn_haoma_data)
    public void onBtnHaomaClicked() {
        /*String haoma = etHaoma.getText().toString();
        showMsg(""+isNumeric(haoma));*/
        /*String[] array = haoma.split("\n");
        if(array.length>0){
            showMsg(array[0]);
        }*/

        //PromptUtil.startAlarm(this);
        File files = SqliteHelper.getHelper().getPath();
        FileUtil.copyFile(files.getPath(), SDCardUtil.getSDCardPath() + "jingcai_ds_app.db");
        showMsg("chengg");
    }


}

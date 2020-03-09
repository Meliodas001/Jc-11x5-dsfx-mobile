package com.jingcai.jc_11x5.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jingcai.jc_11x5.app.App;

import java.io.Serializable;

/**
 * Created by fred on 16/7/8.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().addActivity(this);
    }

    protected void initData(){}
    protected void initView(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }

    protected void showMsg(String msg) {
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showMsg() {
        Toast.makeText(this, "该功能暂未开发！", Toast.LENGTH_LONG).show();
    }


    /**
     * 必须重写
     * @param v
     */
    public  void onStockClick(View v){

    }

    protected void startNewActivity(Class cls){
        Intent intent=new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    protected void startNewActivity(Context context,Class cls){
        Intent intent=new Intent();
        intent.setClass(context, cls);
        startActivity(intent);
    }

    protected void startNewActivityWithSerializableData(Class cls,String key ,Serializable value){
        Intent intent=new Intent();
        intent.setClass(this, cls);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    protected void startNewActivityForForResult(Class cls,int requestCode){
        Intent intent=new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
    }

    protected void startNewActivityWithBundle(Class cls, Bundle extras){
        Intent intent=new Intent();
        if(extras!=null){
            intent.putExtras(extras);
        }
        intent.setClass(this, cls);
        startActivity(intent);
    }

    protected void startNewActivityForResultWithBundle(Class cls, Bundle extras, int requestCode){
        Intent intent=new Intent();
        if(extras!=null){
            intent.putExtras(extras);
        }
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
    }

    protected void startNewTaskActivity(Context context,Class cls){
        Intent intent=new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void hideInputPanel(){
        try {
            InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm!=null){
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
            }
        } catch (Exception e) {
            //LogUtil.sv("BsActivity", e.getLocalizedMessage());
        }
    }

}

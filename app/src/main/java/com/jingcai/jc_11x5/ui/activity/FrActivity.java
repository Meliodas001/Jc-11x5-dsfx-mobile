package com.jingcai.jc_11x5.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.io.Serializable;

public class FrActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		App.getInstance().addActivity(this);
	}
	
	public void startNewActivityWithBundle(Class cls,Bundle extras){
		Intent intent=new Intent();
		if(extras!=null){
			intent.putExtras(extras);
		}
		intent.setClass(this, cls);
		startActivity(intent);
	}
	
	
	public void startNewActivity(Class cls){
		Intent intent=new Intent();
		intent.setClass(this, cls);
		startActivity(intent);
	}
	
	public void startNewActivityWithSerializableData(Class cls,String key ,Serializable value){
		Intent intent=new Intent();
		intent.setClass(this, cls);
		intent.putExtra(key, value);
		startActivity(intent);
	}
	
	public void startNewActivityForCallback(Class cls,int requestCode){
		Intent intent=new Intent();
		intent.setClass(this, cls);
		startActivityForResult(intent, requestCode);
	}

	protected void startNewTaskActivity(Context context, Class cls){
		Intent intent=new Intent(this, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	protected void startNewTaskActivity(Context context, Class cls, String msg){
		Intent intent=new Intent(this, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("verifyMsg", msg);
		startActivity(intent);
	}

	protected void showMsg(String msg){
		//ToastWidget.showMsg(this, msg);
	}
	
}

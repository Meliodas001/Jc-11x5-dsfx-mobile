package com.jingcai.jc_11x5.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jingcai.jc_11x5.R;

public abstract  class BaseFragment extends Fragment {

	protected Context mContext;

	/**
	 * 当该类被系统创建的时候回调
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
		initView();
		setListener();
		requestData();
	}

	public abstract void initData();

	//抽象类，由孩子实现，实现不同的效果
	public abstract void initView();

	public abstract void requestData();

	public void setListener(){}

	protected void showMsg(String msg){
		//ToastWidget.showMsg(getActivity(), msg);
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Toast
	 * @param toastMsg
	 */
	protected void showToastInCenter(String toastMsg) {
		if (TextUtils.isEmpty(toastMsg) || getContext() == null) {
			return;
		}
		Toast toast = Toast.makeText(getContext(),
				toastMsg, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	protected void startNewTaskActivity(Context context,Class cls){
		Intent intent=new Intent(context, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	protected void startNewActivityWithBundle(Context context, Class cls,Bundle extras){
		Intent intent=new Intent();
		if(extras!=null){
			intent.putExtras(extras);
		}
		intent.setClass(context, cls);
		startActivity(intent);
	}

	protected void startNewActivity(Context context, Class cls){
		Intent intent=new Intent();
		intent.setClass(context, cls);
		startActivity(intent);
	}

	protected void startNewActivityForResultWithBundle(Context context, Class cls, Bundle extras, int requestCode){
		Intent intent=new Intent();
		if(extras!=null){
			intent.putExtras(extras);
		}
		intent.setClass(context, cls);
		startActivityForResult(intent, requestCode);
	}

}

package com.jingcai.jc_11x5.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class LintHandler extends Handler {
	WeakReference<Object> object=null;
	public LintHandler(Object obj) {
		object=new WeakReference<Object>(obj);
	}
	@Override
	public void handleMessage(Message msg) {
		if(object.get()!=null){
			beginHandleMessage(msg);
		}
	}
	
	public abstract void beginHandleMessage(Message msg) ;

}

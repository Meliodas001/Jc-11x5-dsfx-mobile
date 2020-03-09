package com.jingcai.jc_11x5.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.jingcai.jc_11x5.app.App;

import java.io.File;

/**
 * 系统类工具
 * @author Fred Don
 */
public class AppTool
{
	public static void restartApplication(Context context) {
		final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	// 获取AppKey
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null; 
		}
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {

		}
		return apiKey;
	}
	/**
	 * 获取内部版本号
	 * @param paramContext
	 * @return
	 */
	public static int getVersionCode(Context paramContext)
	{
		try
		{
			PackageManager localPackageManager = paramContext.getPackageManager();
			String str = paramContext.getPackageName();
			int i = localPackageManager.getPackageInfo(str, 0).versionCode;
			return i;
		}
		catch (NameNotFoundException localNameNotFoundException)
		{
			localNameNotFoundException.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取版本串号
	 * @param paramContext
	 * @return
	 */
	public static String getVersionName(Context paramContext)
	{
		try
		{
			PackageManager localPackageManager = paramContext.getPackageManager();
			String str1 = paramContext.getPackageName();
			str1 = localPackageManager.getPackageInfo(str1, 0).versionName;
			return str1;
		}
		catch (NameNotFoundException localNameNotFoundException)
		{
			localNameNotFoundException.printStackTrace();
			String str2 = "unkown version name";
			return str2;
		}
	}

	/**
	 * 获取手机串号
	 * @param context
	 * @return
	 */
	public static String getImei(Context context){
		try {
			TelephonyManager tel=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			return  tel.getDeviceId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取手机型号
	 * @return
	 */
	public static String getModel(){
		String mtype = android.os.Build.MODEL; 
		return mtype;
	}

	public static String getPlatform(){
		return "系统版本:" + android.os.Build.VERSION.RELEASE; 
	}

	/**
	 * 获取硬件厂商
	 * @return 硬件厂商
	 */
	public static String getManufactory(){
		return android.os.Build.MANUFACTURER;
	}

	public static float getWidthCm(int w,float d,int dpi){
		return (float) (3.0*w*1.0/(d*dpi));
	}

	// 安装apk
	public static  void installApk(File file) {
		Intent intent = new Intent();
		// 执行动作
		intent.setAction(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 执行的数据类型
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");// 编者按：此处Android应为android，否则造成安装不了
		try {
			App.getInstance().startActivity(intent);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startActivity(Context context){
		Intent intent = new Intent();   
		intent.setComponent(new ComponentName("com.xacf.ydwb","com.xacf.ydwb.ui.activity.WelcomeActivity"));
		intent.setAction(Intent.ACTION_VIEW);   
		context.startActivity(intent);
	}


}




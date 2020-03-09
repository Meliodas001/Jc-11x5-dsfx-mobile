package com.jingcai.jc_11x5.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class LogUtil
{

	private LogUtil() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	private static boolean _debug = true;
	private static boolean _track = true;
	private static boolean _info = true;

	private static boolean _debug_save = true; //打印debug信息
	private static boolean _track_save = true;
	private static boolean _info_save = false;
	/**
	 * @param tag
	 * @param msg
	 */
	public static void debug(String tag, String msg) {
		try {
			if (_debug && msg!=null)
				Log.i(tag, msg);
			if(_debug_save){
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					File file=new File(Environment.getExternalStorageDirectory()+PathsUtil.getLogRoot()+"logd/"+DateUtil.formatterToDateHour(DateUtil.getNowMills())+".html");
					if(!file.exists()){
						file.getParentFile().mkdirs();
						file.createNewFile();
					}
					FileUtil.appendTo(file, "<a style=\"color:black\">【"+DateUtil.formatterToDateAndTime(DateUtil.getNowMills())+"】"+tag+" </a>: "+msg+"<br/>");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * @param tag
	 * @param msg
	 */
	public static void trac(String tag, String msg) {
		try {
			if (_track && msg!=null)
				Log.i(tag, msg);
			if(_track_save){
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					File file=new File(Environment.getExternalStorageDirectory()+PathsUtil.getLogRoot()+"loge/"+DateUtil.formatterToDateHour(DateUtil.getNowMills())+".html");
					if(!file.exists()){
						file.getParentFile().mkdirs();
						file.createNewFile();
					}
					FileUtil.appendTo(file, "<a style=\"color:purple\">【"+DateUtil.formatterToDateAndTime(DateUtil.getNowMills())+"】"+tag+" </a>: "+msg+"<br/>");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * @param tag
	 * @param msg
	 */
	public static void info(String tag, String msg) {
		try {
			if (_info && msg!=null)
				Log.i(tag, msg);
			if(_info_save){
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					File file=new File(Environment.getExternalStorageDirectory()+PathsUtil.getLogRoot()+"logi/"+DateUtil.formatterToDateHour(DateUtil.getNowMills())+".html");
					if(!file.exists()){
						file.getParentFile().mkdirs();
						file.createNewFile();
					}
					FileUtil.appendTo(file, "<a style=\"color:blue\">【"+DateUtil.formatterToDateAndTime(DateUtil.getNowMills())+"】"+tag+" </a>: "+msg+"<br/>");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}


}
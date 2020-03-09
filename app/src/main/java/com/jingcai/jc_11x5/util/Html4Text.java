package com.jingcai.jc_11x5.util;

import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Spanned;

public class Html4Text {

	private Html4Text() {
	}
	
	public static Spanned mk(String source) {
		return Html.fromHtml(source);
	}
	
	public static Spanned mks(String source,ImageGetter imageGetter,TagHandler tagHandler) {
		return Html.fromHtml(source, imageGetter, tagHandler);
	}
	public static String bold(String msg){
		StringBuilder sb=new StringBuilder();
		sb.append("<b>").append(msg).append("</b>");
		return sb.toString();
	}
	
	public static String color(String msg,String color){
		StringBuilder sb=new StringBuilder();
		sb.append("<font color=").
		append(color).
		append(" >").
		append(msg).append("</font>");
		return sb.toString();
	}
	
	public static String boldAndColor(String msg,String color){
		StringBuilder sb=new StringBuilder();
		sb.append("<font color=").
		append(color).
		append(">").
		append(bold(msg)).append("</font>");
		return sb.toString();
	}
	
	
	public static String span(String msg){
		StringBuilder sb=new StringBuilder();
		sb.append("<span>").append(msg).append("</span>");
		return sb.toString();
	}
	
	
}

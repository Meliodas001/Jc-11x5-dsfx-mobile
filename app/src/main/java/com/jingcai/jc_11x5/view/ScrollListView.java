package com.jingcai.jc_11x5.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 当ScrollView里嵌套ListView时，系统无法计算ListView的高度，导致ListView只显示第一行，现拓展ListView解决这个问题
 */
public class ScrollListView extends ListView {

	public ScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public ScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ScrollListView(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		 int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		 super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	

}

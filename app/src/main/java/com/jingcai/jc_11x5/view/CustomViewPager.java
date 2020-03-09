package com.jingcai.jc_11x5.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhaojian on 15/3/24.
 */
public class CustomViewPager extends ViewPager {

    /**
     * 设置ViewPager是否需要滑动
     *
     * @param isScroll
     */
    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    private boolean isScroll = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isScroll)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isScroll)
            return super.onTouchEvent(arg0);
        else
            return false;
    }
}

package com.jingcai.jc_11x5.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/11.
 */

public class NumTextView  extends TextView {
    Drawable mADD,mSUB;
    //Drawable mSUB_UNABLE;

    public NumTextView(Context context) {
        super(context);
    }

    public NumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();//初始化
    }

    /**
     * 初始化，设置初始值
     */
    private void init()
    {
//      本来想要在这里设置加减号图片，可是似乎这样做不行，知道的请告诉我吧
//      mADD = getResources().getDrawable(R.drawable.public_plus);
//      mSUB = getResources().getDrawable(R.drawable.public_minus);
        mADD = getCompoundDrawables()[0];
        mSUB = getCompoundDrawables()[2];
        //mSUB_UNABLE = getResources().getDrawable(R.drawable.public_minus_pressed);
        this.setText(0 + "");//设置初始值为0
    }

    /**
     * 获取点击时X值，判断其所在位置来虚拟点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int num;
        float X,add_left,add_right,sub_left,sub_right;
        X = event.getX();
        add_right = getTotalPaddingLeft();
        add_left = getPaddingLeft();
        sub_right = getWidth() - getPaddingRight();
        sub_left = getWidth() - getTotalPaddingRight();
        if(TextUtils.isEmpty(this.getText().toString()))
        {
            num = 1;
        }else{
            num = Integer.parseInt(getText().toString());
        }
        if(X > add_left && X < add_right)
        {
            num++;
            num = setCheckadle(num);//检验当前输入框数字,若为0则设置减号按钮不可见
            this.setText(num + "");

        }else if(X >sub_left && X <sub_right)
        {
            num--;
            num = setCheckadle(num);//检验当前输入框数字,若不为0则设置减号按钮可见
            this.setText(num + "");

        }
        return super.onTouchEvent(event);
    }

    /**
     * 判断num值，对加减号进行设置：若num = 0，减号不可用；若num = 99，加号不可用（限制num在0~99区间）
     * @param num
     * @return
     */
    private int setCheckadle(int num) {
        if(num == 0)
        {
            //setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mSUB_UNABLE, getCompoundDrawables()[3]);
        }else{
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mSUB, getCompoundDrawables()[3]);
        }
        if(num == 99)
        {
            setCompoundDrawables(null, getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
        }else{
            setCompoundDrawables(mADD, getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
        }
        return num;
    }


}

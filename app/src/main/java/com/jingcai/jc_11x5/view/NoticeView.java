package com.jingcai.jc_11x5.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.NoticeInfo;

import java.util.List;

public class NoticeView extends ViewFlipper implements View.OnClickListener {

    private Context mContext;
    private List<NoticeInfo> mNotices;
    private OnNoticeClickListener mOnNoticeClickListener;

    public NoticeView(Context context) {
        super(context);
    }

    public NoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        //轮播间隔时间3s
        setFlipInterval(4500);
//        // 内边距5dp
//        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        //设置动画
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out));
    }

    public void addNotice(List<NoticeInfo> notices) {
        mNotices = notices;
        removeAllViews();
        for (int i = 0; i < mNotices.size(); i++) {
            // 根据公告内容构建一个TextView
            NoticeInfo noticeInfo = mNotices.get(i);
            View itemView = View.inflate(mContext, R.layout.viewflipper_item, null);
            TextView noticeTitle = (TextView) itemView.findViewById(R.id.notice_title);
            TextView noticeNr = (TextView) itemView.findViewById(R.id.notice_nr);
            noticeTitle.setText(noticeInfo.getTitle());
            noticeNr.setText(noticeInfo.getDetails());
            this.addView(itemView);
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        NoticeInfo notice = mNotices.get(position);
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener.onNoticeClick(position, notice);
        }
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {
        void onNoticeClick(int position, NoticeInfo noticeInfo);
    }

    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setmOnNoticeClickListener(OnNoticeClickListener onNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener;
    }

    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, mContext.getResources().getDisplayMetrics());
    }
}

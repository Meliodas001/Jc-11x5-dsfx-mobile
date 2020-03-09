package com.jingcai.jc_11x5.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器
*/
public class ViewPagerAdapter extends PagerAdapter {
    public List<View> mListViews;

    public void setmListViews(List<View> mListViews) {
    	if(mListViews!=null){
    		this.mListViews = mListViews;
    		notifyDataSetChanged();
    	}
	}

    
	/**
	 * @return the mListViews
	 */
	public List<View> getmListViews() {
		return mListViews;
	}


	public ViewPagerAdapter(List<View> mListViews) {
    	if(mListViews==null){
    		this.mListViews=new ArrayList<View>();
    	}else{
    		this.mListViews = mListViews;
    	}
    }

    @Override
    public void destroyItem(ViewGroup v, int arg1, Object arg2) {
       v.removeView(mListViews.get(arg1));
    }


    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup v, int arg1) {
        v.addView(mListViews.get(arg1), 0);
        return mListViews.get(arg1);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


}
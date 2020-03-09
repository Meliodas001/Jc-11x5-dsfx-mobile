package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class PoweredAdapter<T> extends BaseAdapter {

	protected Object[] object;
	private List<T> list;
	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	protected int currentIndex=-1;
	protected Context context;
	private View box;
	/**
	 * @return the box
	 */
	public View getBox() {
		return box;
	}
	/**
	 * @param box the box to set
	 */
	public void setBox(View box) {
		this.box = box;
	}
	/**
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}
	/**
	 * @param currentIndex the currentIndex to set
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
		notifyDataSetChanged();
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		if(list==null){
			this.list=new ArrayList<T>();
		}else{
			this.list=list;
		}
		notifyDataSetChanged();
	}

	@SuppressWarnings("unchecked")
	public PoweredAdapter(Object... obj) {
		this.object=obj;
		for (int i = 0; i < obj.length; i++) {
			this.object[i]=obj[i];
			if(obj[i] instanceof List){
				if(obj[i]==null){
					list=new ArrayList<T>();
				}else{
					list=(List<T>) obj[i];
				}
			}
			else if(obj[i] instanceof Context){
				this.context=(Context) obj[i];
			}
			else if(obj[i] instanceof View){
				this.box=(View) obj[i];
			}
			
		}
		init();
	}
	
	public abstract void init() ;

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int postion) {
		return list.get(postion);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	
	OnListener onListeners;
	/**
	 * @return the onListeners
	 */
	public OnListener getOnListeners() {
		return onListeners;
	}
	/**
	 * @param onListeners the onListeners to set
	 */
	public void setOnListeners(OnListener onListeners) {
		this.onListeners = onListeners;
	}

	public interface OnListener{
		void onItemClickListener(Object object, int position);
	}

}

package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.CaiZhong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsen on 2016/9/6.
 */
public class DmAdapter<T>  extends BaseAdapter {
    private List<T> list;
    private LayoutInflater inflater;
    public void setList(ArrayList<T> list) {
        if(list!=null){
            this.list = list;
            notifyDataSetChanged();
        }
    }
    public DmAdapter(Context context, List<T> list) {
        this.inflater=LayoutInflater.from(context);
        if(list==null){
            this.list=new ArrayList<>();
        }else{
            this.list=list;
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup arg2) {
        ViewHolder vh;
        if(v==null){
            vh=new ViewHolder();
            v=inflater.inflate(R.layout.listview_item_dialog, null);
            vh.tvItemName=(TextView)v.findViewById(R.id.tv_item_xm);
            v.setTag(vh);
        }else{
            vh=(ViewHolder) v.getTag();
        }
        Object entity = list.get(position);
        if(entity instanceof CaiZhong){
            CaiZhong caizhong = (CaiZhong)entity;
            vh.tvItemName.setText(caizhong.getCaiMc());
        }
        return v;
    }

    class ViewHolder{
        TextView tvItemName;
    }
}

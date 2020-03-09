package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangsen on 2016/12/15.
 */
public class LvSuoShuiTjAdapter extends PoweredAdapter<String> {

    public LvSuoShuiTjAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_sstj, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        String item = getItem(position);
        if (item != null) {
            String[] array = item.split("-");
            if(array.length < 2){
                vh.tvYxText.setText(array[0]);
            }else{
                vh.tvYxText.setText(array[0]);
                vh.tvYxValue.setText(array[1]);
            }

        }
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_yx_text)
        TextView tvYxText;
        @Bind(R.id.tv_yx_value)
        TextView tvYxValue;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

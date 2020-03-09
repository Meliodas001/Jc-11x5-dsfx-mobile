package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 走势--使用
 */
public class LvXt2Adapter extends PoweredAdapter<KaiJiang_11x5> {

    public LvXt2Adapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_xt, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        KaiJiang_11x5 item = getItem(position);
        vh.tvOrder.setText(item.getOrderNum());
        vh.tvKjNum.setText(item.getLuckyNumber());
        vh.tvValue1.setText(item.getLubi());
        vh.tvValue2.setText(item.getLinhaogeshu());
        vh.tvValue3.setText(item.getPingjunzhi());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_order)
        TextView tvOrder;
        @Bind(R.id.tv_kj_num)
        TextView tvKjNum;
        @Bind(R.id.tv_value1)
        TextView tvValue1;
        @Bind(R.id.tv_value2)
        TextView tvValue2;
        @Bind(R.id.tv_value3)
        TextView tvValue3;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

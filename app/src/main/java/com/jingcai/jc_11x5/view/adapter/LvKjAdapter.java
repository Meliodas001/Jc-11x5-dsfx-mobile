package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import com.jingcai.jc_11x5.entity.ProfitChart;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 走势--使用
 */
public class LvKjAdapter extends PoweredAdapter<KaiJiang_11x5> {

    public LvKjAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_kj, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        KaiJiang_11x5 item = getItem(position);
        vh.tvOrder.setText(item.getOrderNum());
        vh.tvKjNum.setText(item.getLuckyNumber());
        vh.tvHezhi.setText(item.getHezhi());
        vh.tvKuadu.setText(item.getKuadu());
        vh.tvChonghao.setText(item.getChonghaoshu());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_order)
        TextView tvOrder;
        @Bind(R.id.tv_kj_num)
        TextView tvKjNum;
        @Bind(R.id.tv_hezhi)
        TextView tvHezhi;
        @Bind(R.id.tv_kuadu)
        TextView tvKuadu;
        @Bind(R.id.tv_chonghao)
        TextView tvChonghao;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.ProfitChart;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 季盈利榜单，月盈利榜单，周盈利榜单--使用
 */
public class LvProfitAdapter extends PoweredAdapter<ProfitChart> {

    public LvProfitAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_profit, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ProfitChart item = getItem(position);
        vh.tvOrder.setText(String.valueOf(position + 1));
        vh.tvNickName.setText(item.getNickName());
        vh.tvPlanCount.setText(String.valueOf(item.getPlanCount()));
        vh.tvYingli.setText(String.valueOf(item.getBonus()));
        vh.tvHitRate.setText(String.valueOf(item.getHitRate() + "%"));
        if (position % 2 == 0)
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white)); //颜色设置
        else
            convertView.setBackgroundColor(context.getResources().getColor(R.color.listview_bs));//颜色设置
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_order)
        TextView tvOrder;
        @Bind(R.id.tv_nick_name)
        TextView tvNickName;
        @Bind(R.id.tv_plan_count)
        TextView tvPlanCount;
        @Bind(R.id.tv_yingli)
        TextView tvYingli;
        @Bind(R.id.tv_hit_rate)
        TextView tvHitRate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

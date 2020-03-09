package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.PlanDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 走势--使用
 */
public class LvGameAdapter extends PoweredAdapter<PlanDetails> {

    public LvGameAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_game, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(position%2==0)
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white)); //颜色设置
        else
            convertView.setBackgroundColor(context.getResources().getColor(R.color.listview_bs));//颜色设置
        PlanDetails item = getItem(position);
        vh.tvCai.setText(item.getLotteryType());
        vh.tvOrder.setText(item.getBeginOrder());
        vh.tvZhushu.setText(item.getZhuCount());
        vh.tvState.setText(item.getState().toString());
        return convertView;
    }

    private String getStateMc(int state){
        String text = "";
        switch (state){
            case 1:
                text = "中奖";
                break;
            case 2:
                text = "未中";
                break;
            case 3:
                text = "未开";
                break;
        }
        return text;
    }

    static class ViewHolder {
        @Bind(R.id.tv_cai)
        TextView tvCai;
        @Bind(R.id.tv_order)
        TextView tvOrder;
        @Bind(R.id.tv_zhushu)
        TextView tvZhushu;
        @Bind(R.id.tv_state)
        TextView tvState;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

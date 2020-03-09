package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.ResultBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页计划列表adapter
 */
public class LvLotteryPlanAdapter extends PoweredAdapter<ResultBean.LotteryPlan> {

    public LvLotteryPlanAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_lottery_plan, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ResultBean.LotteryPlan item = getItem(position);
        vh.tvUserMc.setText(item.getNickName());
        vh.tvPlanMc.setText(item.getPlanName());
        int state = item.getState();
        if(state == 1){
            vh.tvJhzt.setText("中奖");
            vh.tvJhzt.setTextColor(context.getResources().getColor(R.color.red));
        }else if(state == 2){
            vh.tvJhzt.setText("未中");
            vh.tvJhzt.setTextColor(context.getResources().getColor(R.color.gray));
        }else{
            vh.tvJhzt.setText("进行中");
            vh.tvJhzt.setTextColor(context.getResources().getColor(R.color.text_orange));
        }
        if (position % 2 == 0)
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white)); //颜色设置
        else
            convertView.setBackgroundColor(context.getResources().getColor(R.color.listview_bs));//颜色设置
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_user_mc)
        TextView tvUserMc;
        @Bind(R.id.tv_plan_mc)
        TextView tvPlanMc;
        @Bind(R.id.tv_jhzt)
        TextView tvJhzt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

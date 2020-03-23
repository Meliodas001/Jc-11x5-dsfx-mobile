package com.jingcai.jc_11x5.view.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.BalanceChangeRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 走势--使用
 */
public class LvBalanceAdapter extends PoweredAdapter<BalanceChangeRecord> {

    public LvBalanceAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_zhangbian, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(position%2==0)
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white)); //颜色设置
        else
            convertView.setBackgroundColor(context.getResources().getColor(R.color.listview_bs));//颜色设置
        BalanceChangeRecord item = getItem(position);
        int changeType = item.getChangeType();
        vh.tvLeixing.setText(getChangeMc(changeType));
        vh.tvJine.setText(item.getChangeVal());
        String time = item.getCreateTime();
        if(time != null && !TextUtils.isEmpty(time)){
            time = time.replaceAll("T", " ");
        }
        vh.tvTime.setText(time);
        vh.tvInfo.setText(item.getDetails());
        return convertView;
    }

    private String getChangeMc(int changeType){
        String text = "";
        switch (changeType){
            case 1:
                text = "初始化";
                break;
            case 2:
                text = "充值";
                break;
            case 3:
                text = "自助兑换";
                break;
            case 4:
                text = "购买方案";
                break;
            case 5:
                text = "购买套餐";
                break;
            case 6:
                text = "游戏币转出";
                break;
            case 7:
                text = "游戏币转入";
                break;
            case 8:
                text = "详情内容";
                break;
            case 9:
                text = "正确";
                break;
        }
        return text;
    }

    static class ViewHolder {
        @Bind(R.id.tv_leixing)
        TextView tvLeixing;
        @Bind(R.id.tv_jine)
        TextView tvJine;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_info)
        TextView tvInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

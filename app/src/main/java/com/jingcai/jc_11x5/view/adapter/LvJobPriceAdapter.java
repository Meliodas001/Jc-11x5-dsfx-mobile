package com.jingcai.jc_11x5.view.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.CoinChangeRecord;
import com.jingcai.jc_11x5.entity.JobPrice;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 走势--使用
 */
public class LvJobPriceAdapter extends PoweredAdapter<JobPrice> {

    public LvJobPriceAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_jobprice, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final JobPrice item = getItem(position);

//        vh.tvJobType.setText(getTypeMc(item.getState()) + " " + item.getDays() + " 天");
        vh.tvJobType.setText(item.getDays() + " 天卡");
        vh.tvPrice.setText(item.getPrice());
        vh.tvCoin.setText(item.getMoney());
        vh.tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnJobClickListener().onClick(item);
            }
        });
        return convertView;
    }

    private String getTypeMc(int type){
        String typeMc = "";
        if (type == 1) {
            typeMc = "全包";
        } else if (type == 2) {
            typeMc = "部分包";
        }
        return typeMc;
    }

    static class ViewHolder {
        @Bind(R.id.tv_job_type)
        TextView tvJobType;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_pay)
        TextView tvPay;
        @Bind(R.id.tv_coin)
        TextView tvCoin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private OnJobClickListener onJobClickListener;

    public OnJobClickListener getOnJobClickListener() {
        return onJobClickListener;
    }

    public void setOnJobClickListener(OnJobClickListener onJobClickListener) {
        this.onJobClickListener = onJobClickListener;
    }

    public interface OnJobClickListener {
        void onClick(JobPrice jobPrice);
    }
}

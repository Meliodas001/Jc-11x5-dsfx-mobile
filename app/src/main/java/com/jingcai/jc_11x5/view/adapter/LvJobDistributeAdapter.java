package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.JobDistribute;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 包月信息--使用
 */
public class LvJobDistributeAdapter extends PoweredAdapter<JobDistribute> {

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    private int jobType;

    public LvJobDistributeAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_job_dis, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final JobDistribute item = getItem(position);

        if (jobType == 2) {
            vh.tvNickName.setVisibility(View.VISIBLE);
            vh.tvNickName.setText("计划昵称：" + item.getPlanNickName());
        } else {
            vh.tvNickName.setVisibility(View.GONE);
        }
        vh.tvTime.setText("到期时间：" + item.getExpireTime());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_type)
        TextView tvType;
        @Bind(R.id.tv_nick_name)
        TextView tvNickName;
        @Bind(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

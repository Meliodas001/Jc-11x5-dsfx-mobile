package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.JobPlanner;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 走势--使用
 */
public class LvJobPlannerAdapter extends PoweredAdapter<JobPlanner> {

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    private int jobType;

    public LvJobPlannerAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_jobplanner, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final JobPlanner item = getItem(position);

        vh.tvUserName.setText("用" + "\u0020\u0020" + "户" + "\u0020\u0020" + "名：" + item.getUserName());
        vh.tvNickName.setText("昵" + "\u3000\u3000" + "称：" + item.getNickName());
        vh.tvTime.setText("创建时间：" + item.getCreateTime());
        if (jobType == 2) {
            vh.cbSelcct.setVisibility(View.VISIBLE);
        } else {
            vh.cbSelcct.setVisibility(View.GONE);
        }
        vh.cbSelcct.setChecked(item.isChecked());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_user_name)
        TextView tvUserName;
        @Bind(R.id.tv_nick_name)
        TextView tvNickName;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.cb_selcct)
        CheckBox cbSelcct;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

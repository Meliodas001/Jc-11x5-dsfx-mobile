package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.News;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LvNewsAdapter extends PoweredAdapter<News> {

    public LvNewsAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_news, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        News item = getItem(position);
        vh.newsTime.setText(item.getCreateTime());
        vh.newsTitle.setText(item.getTitle());
        vh.newsContent.setText(item.getContent());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.news_time)
        TextView newsTime;
        @Bind(R.id.news_title)
        TextView newsTitle;
        @Bind(R.id.news_content)
        TextView newsContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

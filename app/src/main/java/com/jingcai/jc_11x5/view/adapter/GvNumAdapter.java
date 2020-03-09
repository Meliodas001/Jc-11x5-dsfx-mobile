package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.PlanFabu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangsen on 2018/1/16.
 */

public class GvNumAdapter extends PoweredAdapter<PlanFabu.HaoMa> {

    public GvNumAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    public int getSelectNumCount(){
        List<PlanFabu.HaoMa> lists = getList();
        int count = 0;
        for(PlanFabu.HaoMa haoMa : lists){
            if(haoMa.isChecked()){
                count+=1;
            }
        }
        return count;
    }

    public String getSelectNum(){
        List<PlanFabu.HaoMa> lists = getList();
        StringBuilder sb = new StringBuilder();
        for(PlanFabu.HaoMa haoMa : lists){
            if(haoMa.isChecked()){
                sb.append(haoMa.getHaoma()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item_xuanhao, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final PlanFabu.HaoMa item = getItem(position);
        vh.cbHaoma.setText(item.getHaoma());
        vh.cbHaoma.setChecked(item.isChecked());
        vh.cbHaoma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OnCheckedListener onCheckedListener = getOnCheckedListener();
                if(onCheckedListener!=null){
                    int d_count = onCheckedListener.getDefaultCount();
                    int count = getSelectNumCount();
                    item.setChecked(isChecked);
                    if(count >= d_count){
                        onCheckedListener.getCheckedState(-2);
                    }else if(isChecked && count == d_count - 1){
                        //onCheckedListener.getCheckedState(1);
                        onCheckedListener.getCheckedState(-2);
                    }else{
                        onCheckedListener.getCheckedState(-1);
                    }
                }
                /*OnCheckedListener onCheckedListener = getOnCheckedListener();
                if(onCheckedListener!=null){
                    int d_count = onCheckedListener.getDefaultCount();
                    int count = getSelectNumCount();
                    if(count >= d_count){
                        if(isChecked){
                            Toast.makeText(context, "你仅能选择"+d_count+"个号码！", Toast.LENGTH_SHORT).show();
                        }
                        buttonView.setChecked(false);
                        item.setChecked(false);
                        onCheckedListener.getCheckedState(-2);
                    }else if(count == d_count - 1){
                        item.setChecked(isChecked);
                        onCheckedListener.getCheckedState(1);
                    }else{
                        item.setChecked(isChecked);
                        onCheckedListener.getCheckedState(-1);
                    }
                }*/
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.cb_haoma)
        CheckBox cbHaoma;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private OnCheckedListener onCheckedListener;

    public OnCheckedListener getOnCheckedListener() {
        return onCheckedListener;
    }

    public void setOnCheckedListener(OnCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    public interface OnCheckedListener {
        //void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
        int getDefaultCount();
        void getCheckedState(int state);
        //void getCheckedCount(int count);
    }
}

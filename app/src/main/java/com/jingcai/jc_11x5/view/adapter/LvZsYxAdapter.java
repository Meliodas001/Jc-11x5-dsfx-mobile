package com.jingcai.jc_11x5.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 季盈利榜单，月盈利榜单，周盈利榜单--使用
 */
public class LvZsYxAdapter extends PoweredAdapter<KaiJiang_11x5> implements View.OnClickListener{

    public LvZsYxAdapter(Object... obj) {
        super(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_zsyx, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if(position%2==0)
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white)); //颜色设置
        else
            convertView.setBackgroundColor(context.getResources().getColor(R.color.listview_bs));//颜色设置

        KaiJiang_11x5 item = getItem(position);
        String order = item.getOrderNum();
        vh.tvOrder.setText(order.substring(order.length()-4, order.length()));
        vh.tv1.setOnClickListener(this);
        vh.tv2.setOnClickListener(this);
        vh.tv3.setOnClickListener(this);
        vh.tv4.setOnClickListener(this);
        vh.tv5.setOnClickListener(this);
        vh.tv6.setOnClickListener(this);
        vh.tv7.setOnClickListener(this);
        vh.tv8.setOnClickListener(this);
        vh.tv9.setOnClickListener(this);
        vh.tv10.setOnClickListener(this);
        vh.tv11.setOnClickListener(this);
        vh.tv1.setTag(item.getOrderNum());
        vh.tv2.setTag(item.getOrderNum());
        vh.tv3.setTag(item.getOrderNum());
        vh.tv4.setTag(item.getOrderNum());
        vh.tv5.setTag(item.getOrderNum());
        vh.tv6.setTag(item.getOrderNum());
        vh.tv7.setTag(item.getOrderNum());
        vh.tv8.setTag(item.getOrderNum());
        vh.tv9.setTag(item.getOrderNum());
        vh.tv10.setTag(item.getOrderNum());
        vh.tv11.setTag(item.getOrderNum());
        String[] str = item.getZouShi().split(",");
        if(str[0].equals("0")){
            setNormal(vh.tv1, "01");
        }else{
            setSelect(vh.tv1, "01");
        }
        if(str[1].equals("0")){
            setNormal(vh.tv2, "02");
        }else{
            setSelect(vh.tv2, "02");
        }
        if(str[2].equals("0")){
            setNormal(vh.tv3, "03");
        }else{
            setSelect(vh.tv3, "03");
        }
        if(str[3].equals("0")){
            setNormal(vh.tv4, "04");
        }else{
            setSelect(vh.tv4, "04");
        }
        if(str[4].equals("0")){
            setNormal(vh.tv5, "05");
        }else{
            setSelect(vh.tv5, "05");
        }
        if(str[5].equals("0")){
            setNormal(vh.tv6, "06");
        }else{
            setSelect(vh.tv6, "06");
        }
        if(str[6].equals("0")){
            setNormal(vh.tv7, "07");
        }else{
            setSelect(vh.tv7, "07");
        }
        if(str[7].equals("0")){
            setNormal(vh.tv8, "08");
        }else{
            setSelect(vh.tv8, "08");
        }
        if(str[8].equals("0")){
            setNormal(vh.tv9, "09");
        }else{
            setSelect(vh.tv9, "09");
        }
        if(str[9].equals("0")){
            setNormal(vh.tv10, "10");
        }else{
            setSelect(vh.tv10, "10");
        }
        if(str[10].equals("0")){
            setNormal(vh.tv11, "11");
        }else{
            setSelect(vh.tv11, "11");
        }
        return convertView;
    }



    private void setNormal(TextView v, String value){
        v.setText(value);
        v.setSelected(false);
        /*v.setTextColor(context.getResources().getColor(R.color.black));
        v.setBackground(null);*/
    }
    private void setSelect(TextView v, String value){
        v.setText(value);
        v.setSelected(true);
        /*v.setTextColor(context.getResources().getColor(R.color.white));
        v.setBackground(context.getResources().getDrawable(R.mipmap.circle));*/
    }
    private boolean isWinNum(String[] arrayWinNum, int index){
        if(arrayWinNum == null){
            return false;
        }
        for(int j=0; j<arrayWinNum.length; j++){
            if(index + 1 == Integer.parseInt(arrayWinNum[j])){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        List<KaiJiang_11x5> lists= getList();
        String tag = String.valueOf(v.getTag());
        KaiJiang_11x5 kaiJiang_11x5;
        if(tag.equals("预选行3")){
            kaiJiang_11x5 = lists.get(getCount()-1);
        }else if(tag.equals("预选行2")){
            kaiJiang_11x5 = lists.get(getCount()-2);
        }else{
            kaiJiang_11x5 = lists.get(getCount()-3);
        }
        String str = kaiJiang_11x5.getZouShi();
        String[] array = str.split(",");
        String value = "1";
        boolean isSelected = v.isSelected();
        if(isSelected){
            value = "0";
        }
        switch (v.getId()){
            case R.id.tv_1:
                array[0] = value;
                break;
            case R.id.tv_2:
                array[1] = value;
                break;
            case R.id.tv_3:
                array[2] = value;
                break;
            case R.id.tv_4:
                array[3] = value;
                break;
            case R.id.tv_5:
                array[4] = value;
                break;
            case R.id.tv_6:
                array[5] = value;
                break;
            case R.id.tv_7:
                array[6] = value;
                break;
            case R.id.tv_8:
                array[7] = value;
                break;
            case R.id.tv_9:
                array[8] = value;
                break;
            case R.id.tv_10:
                array[9] = value;
                break;
            case R.id.tv_11:
                array[10] = value;
                break;
        }
        String zs = "";
        for(String ss : array){
            zs=zs+ss+",";
        }
        zs = zs.substring(0, zs.length()-1);
        kaiJiang_11x5.setZouShi(zs);
        if(tag.equals("预选行3")){
            lists.set(getCount()-1,kaiJiang_11x5);
        }else if(tag.equals("预选行2")){
            lists.set(getCount()-2,kaiJiang_11x5);
        }else{
            lists.set(getCount()-3,kaiJiang_11x5);
        }
        v.setSelected(!isSelected);
        setList(lists);
    }

    static class ViewHolder {
        @Bind(R.id.tv_order)
        TextView tvOrder;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_3)
        TextView tv3;
        @Bind(R.id.tv_4)
        TextView tv4;
        @Bind(R.id.tv_5)
        TextView tv5;
        @Bind(R.id.tv_6)
        TextView tv6;
        @Bind(R.id.tv_7)
        TextView tv7;
        @Bind(R.id.tv_8)
        TextView tv8;
        @Bind(R.id.tv_9)
        TextView tv9;
        @Bind(R.id.tv_10)
        TextView tv10;
        @Bind(R.id.tv_11)
        TextView tv11;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

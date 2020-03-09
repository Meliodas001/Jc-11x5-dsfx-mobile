package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.util.MathUtil;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by yangsen on 2017/5/13.
 */

public class TdSsglResultAdapter extends TableDataAdapter<String> {

    //字体大小
    private static final int TEXT_SIZE = 12;
    private Context context;
    private List<String> data;
    private int paddingLeft = 3;
    private int paddingTop = 10;
    private int paddingRight = 3;
    private int paddingBottom = 10;

    public TdSsglResultAdapter(Context context, List<String> data) {
        super(context, data);
        this.data = data;
        this.context = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        String num = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderXuhao(rowIndex);
                break;
            case 1:
                renderedView = renderHaoma(num);
                break;
            case 2:
                renderedView = renderDaxiaobi(num);
                break;
            case 3:
                renderedView = renderJioubi(num);
                break;
            case 4:
                renderedView = renderZhihebi(num);
                break;
            case 5:
                renderedView = renderLubi(num);
                break;
        }

        return renderedView;
    }
    private View renderXuhao(int xuhao) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(xuhao+1));
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderHaoma(String text) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderDaxiaobi(String text) {
        String[] num = text.split(" ");
        int da = 0;
        int xiao = 0;
        for(String str : num){
            if(Integer.parseInt(str)<6){
                xiao+=1;
            }else{
                da+=1;
            }
        }
        TextView textView = new TextView(context);
        textView.setText(da+":"+xiao);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderZhihebi(String text) {
        String[] num = text.split(" ");
        int zs = 0;
        int hs = 0;
        for(String str : num){
            int n = Integer.parseInt(str);
            if(MathUtil.isZhishu(n)){
                zs+=1;
            }else{
                hs+=1;
            }
        }
        TextView textView = new TextView(context);
        textView.setText(zs+":"+hs);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderJioubi(String text) {
        String[] num = text.split(" ");
        int ji = 0;
        int ou = 0;
        for(String str : num){
            if(Integer.parseInt(str)%2==0){
                ou+=1;
            }else{
                ji+=1;
            }
        }
        TextView textView = new TextView(context);
        textView.setText(ji+":"+ou);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderLubi(String text) {
        String[] num = text.split(" ");
        int lu0 = 0;
        int lu1 = 0;
        int lu2 = 0;
        for(String str : num){
            if(Integer.parseInt(str)%3==0){
                lu0+=1;
            }else if(Integer.parseInt(str)%3==1){
                lu1+=1;
            }else{
                lu2+=1;
            }
        }
        TextView textView = new TextView(context);
        textView.setText(lu0+":"+lu1+":"+lu2);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

}

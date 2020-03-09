package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import com.jingcai.jc_11x5.entity.PlanBeiInfo;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by yangsen on 2017/4/12.
 */
public class TdFangAnAdapter extends TableDataAdapter<PlanBeiInfo> {

    //字体大小
    private static final int TEXT_SIZE = 12;
    private int paddingLeft = 5;
    private int paddingTop = 10;
    private int paddingRight = 5;
    private int paddingBottom = 10;
    private Context context;

    public TdFangAnAdapter(Context context, List<PlanBeiInfo> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        PlanBeiInfo planBeiInfo = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderData(String.valueOf(planBeiInfo.getSlNub()));
                break;
            case 1:
                renderedView = renderData(planBeiInfo.getIssue());
                break;
            case 2:
                renderedView = renderData(String.valueOf(planBeiInfo.getMultiple()));
                break;
            case 3:
                renderedView = renderData(String.valueOf(planBeiInfo.getTotalAmount()));
                break;
            case 4:
                renderedView = renderData(String.valueOf(planBeiInfo.getProfit()));
                break;
            case 5:
                renderedView = renderProfitRate(String.valueOf(planBeiInfo.getProfitRate()));
                break;
        }

        return renderedView;
    }

    private View renderProfitRate(String text) {
        TextView textView = new TextView(context);
        textView.setText(text+"%");
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private View renderData(String text) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

}

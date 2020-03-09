package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingcai.jc_11x5.entity.CalculateInfo;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class TdBtjsAdapter extends TableDataAdapter<CalculateInfo> {

    //字体大小
    private static final int TEXT_SIZE = 14;
    private Context context;

    public TdBtjsAdapter(Context context, List<CalculateInfo> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        CalculateInfo calculateInfo = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderBeishu(calculateInfo);
                break;
            case 1:
                renderedView = renderBenjin(calculateInfo);
                break;
            case 2:
                renderedView = renderZongTouru(calculateInfo);
                break;
            case 3:
                renderedView = renderZongshouyi(calculateInfo);
                break;
            case 4:
                renderedView = renderShouyilv(calculateInfo);
                break;
        }

        return renderedView;
    }

    private View renderBeishu(CalculateInfo calculateInfo) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(calculateInfo.getMultiple()));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private View renderBenjin(CalculateInfo calculateInfo) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(calculateInfo.getCost()));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private View renderZongTouru(CalculateInfo calculateInfo) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(calculateInfo.getTotalCost()));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private View renderZongshouyi(CalculateInfo calculateInfo) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(calculateInfo.getProfit()));
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private View renderShouyilv(CalculateInfo calculateInfo) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(calculateInfo.getProfitPer())+"%");
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}

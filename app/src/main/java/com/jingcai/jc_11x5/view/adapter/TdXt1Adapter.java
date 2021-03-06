package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import java.util.List;
import de.codecrafters.tableview.TableDataAdapter;

/**
 * 开奖走势--形态
 * Created by yangsen on 2017/4/12.
 */
public class TdXt1Adapter extends TableDataAdapter<KaiJiang_11x5> {

    //字体大小
    private static final int TEXT_SIZE = 13;
    private int paddingLeft = 5;
    private int paddingTop = 10;
    private int paddingRight = 5;
    private int paddingBottom = 10;
    private Context context;

    public TdXt1Adapter(Context context, List<KaiJiang_11x5> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        KaiJiang_11x5 kaiJiang_11x5 = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderQihao(kaiJiang_11x5);
                break;
            case 1:
                renderedView = renderWinNum(kaiJiang_11x5);
                break;
            case 2:
                renderedView = renderDaXiaoBi(kaiJiang_11x5);
                break;
            case 3:
                renderedView = renderJiOuBi(kaiJiang_11x5);
                break;
            case 4:
                renderedView = renderZhiHeBi(kaiJiang_11x5);
                break;
        }

        return renderedView;
    }

    private View renderQihao(KaiJiang_11x5 kaiJiang_11x5) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(kaiJiang_11x5.getOrderNum()));
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderWinNum(KaiJiang_11x5 kaiJiang_11x5) {
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(kaiJiang_11x5.getLuckyNumber()));
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderDaXiaoBi(KaiJiang_11x5 kaiJiang_11x5) {
        TextView textView = new TextView(context);
        String value = kaiJiang_11x5.getDaXiaoBi();
        if(value == null || TextUtils.isEmpty(value)){
            value = "--";
        }
        textView.setText(value);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderJiOuBi(KaiJiang_11x5 kaiJiang_11x5) {
        TextView textView = new TextView(context);
        String value = kaiJiang_11x5.getJiOuBi();
        if(value == null || TextUtils.isEmpty(value)){
            value = "--";
        }
        textView.setText(value);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
    private View renderZhiHeBi(KaiJiang_11x5 kaiJiang_11x5) {
        TextView textView = new TextView(context);
        String value = kaiJiang_11x5.getZhiHeBi();
        if(value == null || TextUtils.isEmpty(value)){
            value = "--";
        }
        textView.setText(value);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        //textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }
}

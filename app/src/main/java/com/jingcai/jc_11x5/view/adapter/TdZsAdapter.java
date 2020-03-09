package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import java.util.List;
import de.codecrafters.tableview.TableDataAdapter;

/**
 * 开奖走势--走势
 * Created by yangsen on 2017/4/12.
 */
public class TdZsAdapter extends TableDataAdapter<KaiJiang_11x5> {

    //字体大小
    private static final int TEXT_SIZE = 12;
    private Context context;

    public TdZsAdapter(Context context, List<KaiJiang_11x5> data) {
        super(context, data);
        this.context = context;
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
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        KaiJiang_11x5 kaiJiang_11x5 = getRowData(rowIndex);
        View renderedView = null;
        String[] arrayWinNum = kaiJiang_11x5.getNum();
        String[] arrayZoushi;
        if(arrayWinNum != null){
            String zoushi = kaiJiang_11x5.getZouShi();
            if(zoushi == null){
                arrayZoushi = new String[]{"1","1","1","1","1","1","1","1","1","1","1"};
            }else{
                arrayZoushi = zoushi.split(",");
            }
        }else{
            arrayZoushi = new String[]{"1","1","1","1","1","1","1","1","1","1","1"};
        }

        switch (columnIndex) {
            case 0:
                renderedView = rendeQiHao(kaiJiang_11x5.getOrderNum());
                break;
            case 1:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum = isWinNum(arrayWinNum, 0);
                renderedView = rendeData(parentView, arrayZoushi[0], iswinnum);
                break;
            case 2:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum1 = isWinNum(arrayWinNum, 1);
                renderedView = rendeData(parentView, arrayZoushi[1], iswinnum1);
                break;
            case 3:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum2 = isWinNum(arrayWinNum, 2);
                renderedView = rendeData(parentView, arrayZoushi[2], iswinnum2);
                break;
            case 4:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum3 = isWinNum(arrayWinNum, 3);
                renderedView = rendeData(parentView, arrayZoushi[3], iswinnum3);
                break;
            case 5:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum4 = isWinNum(arrayWinNum, 4);
                renderedView = rendeData(parentView, arrayZoushi[4], iswinnum4);
                break;
            case 6:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum5 = isWinNum(arrayWinNum, 5);
                renderedView = rendeData(parentView, arrayZoushi[5], iswinnum5);
                break;
            case 7:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum6 = isWinNum(arrayWinNum, 6);
                renderedView = rendeData(parentView, arrayZoushi[6], iswinnum6);
                break;
            case 8:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum7 = isWinNum(arrayWinNum, 7);
                renderedView = rendeData(parentView, arrayZoushi[7], iswinnum7);
                break;
            case 9:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum8 = isWinNum(arrayWinNum, 8);
                renderedView = rendeData(parentView, arrayZoushi[8], iswinnum8);
                break;
            case 10:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum9 = isWinNum(arrayWinNum, 9);
                renderedView = rendeData(parentView, arrayZoushi[9], iswinnum9);
                break;
            case 11:
                if(arrayWinNum == null){
                    renderedView = rendeData1(parentView);
                    break;
                }
                boolean iswinnum10 = isWinNum(arrayWinNum, 10);
                renderedView = rendeData(parentView, arrayZoushi[10], iswinnum10);
                break;
        }

        return renderedView;
    }

    private TextView rendeQiHao(String qihao){
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(qihao));
        textView.setPadding(0, 10, 0, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private  View  rendeData(ViewGroup parentView, String value, boolean isWinNum){
        View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_circle);
        if(isWinNum){
            textView.setText(String.valueOf(value));
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setBackground(getResources().getDrawable(R.mipmap.circle));
            //textView.setBackground(getResources().getDrawable(R.drawable.bg_circle_red));
        }else{
            textView.setText(String.valueOf(value));
        }
        textView.setTextSize(10);
        return view;
    }

    private View rendeData1(ViewGroup parentView){
        View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_circle);
        textView.setText("--");
        textView.setTextSize(10);
        return view;
    }

}

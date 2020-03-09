package com.jingcai.jc_11x5.view.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.SuoShuiFilter;


/**
 * Created by yangsen on 2016/8/23.
 */
public class DialogWiget {

    public static AlertDialog lDialog;

    public ListView showlistview(Context context, String pTitle) {
        lDialog = new AlertDialog.Builder(context).create();
        lDialog.setCanceledOnTouchOutside(true);
        lDialog.show();
        Window window = lDialog.getWindow();
        window.setContentView(R.layout.dialog_listview);
        ((TextView) window.findViewById(R.id.tv_dialog_title)).setText(pTitle);
        ListView lv=(ListView) window.findViewById(R.id.lv_dialog_content);
        return lv;
    }

    public void showListview(Context context,String pTitle,ListAdapter adapter, AdapterView.OnItemClickListener listener) {
        lDialog = new AlertDialog.Builder(context).create();
        lDialog.setCanceledOnTouchOutside(true);
        lDialog.show();
        Window window = lDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setContentView(R.layout.dialog_listview);
        ((TextView) window.findViewById(R.id.tv_dialog_title)).setText(pTitle);
        ListView lv=(ListView) window.findViewById(R.id.lv_dialog_content);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listener);
    }

    public void showSuoShuiJs(Context context, SuoShuiFilter suoShuiFilter){
        lDialog = new AlertDialog.Builder(context).create();
        lDialog.setCanceledOnTouchOutside(true);
        lDialog.show();
        Window window = lDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setContentView(R.layout.dialog_suoshui_jieshi);
        ((TextView) window.findViewById(R.id.tv_title)).setText(suoShuiFilter.getText());
        try{
            ((TextView) window.findViewById(R.id.tv_jieshi)).setText(suoShuiFilter.getJieshi());
            ((TextView) window.findViewById(R.id.tv_shili)).setText(suoShuiFilter.getAnli());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showFuwuTiaokuan(Context context, String pTitle){
        lDialog = new AlertDialog.Builder(context).create();
        lDialog.setCanceledOnTouchOutside(true);
        lDialog.show();
        Window window = lDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setContentView(R.layout.dialog_fuwutiaokuan_info);
        ((TextView) window.findViewById(R.id.tv_title)).setText(pTitle);
    }

    public void showCustomMessage(Context context, final CharSequence pMsg) {
        try {
            final AlertDialog lDialog = new AlertDialog.Builder(context).create();
            lDialog.show();
            lDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface arg0) {
                    arg0.dismiss();
                    if(odml!=null){
                        odml.onDialogMiss(lDialog);
                    }
                }
            });
            Window window = lDialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setContentView(R.layout.dialog_prompt);
            ((TextView) window.findViewById(R.id.dialog_message)).setText(pMsg);
            ((Button) window.findViewById(R.id.bt_fanhui)).setText(oKText);
            ((Button) window.findViewById(R.id.bt_fanhui))
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lDialog.dismiss();
                            if(oqc!=null){
                                oqc.onQueryCancel(v);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String oKText="确定";
    public String cancelText="取消";
    public void setOKText(String string) {
        this.oKText=string;
    }

    public void showCustomMessageQuery(Context context,String pTitle, final CharSequence pMsg) {
        try {
            final AlertDialog lDialog = new AlertDialog.Builder(context).create();
            lDialog.show();
            lDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface arg0) {
                    arg0.dismiss();
                    if(odml!=null){
                        odml.onDialogMiss(lDialog);
                    }
                }
            });
            Window window = lDialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setContentView(R.layout.dialog_query);
            ((TextView) window.findViewById(R.id.dialog_title)).setText(pTitle);
            ((TextView) window.findViewById(R.id.dialog_message)).setText(pMsg);
            ((Button) window.findViewById(R.id.bt_ok)).setText(oKText);
            ((Button) window.findViewById(R.id.bt_ok))
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lDialog.dismiss();
                            if(oqo!=null){
                                oqo.onQueryOkClick(v);
                            }
                        }
                    });
            ((Button) window.findViewById(R.id.bt_cancel)).setText(cancelText);
            ((Button) window.findViewById(R.id.bt_cancel))
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // write your code to do things after users clicks OK
                            lDialog.dismiss();
                            if(oqc!=null){
                                oqc.onQueryCancel(v);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void show(){
        if(lDialog != null){
            lDialog.show();
        }
    }

    public void dismiss(){
        if(lDialog != null){
            lDialog.dismiss();
        }
    }

    OnQueryCancel oqc;
    public void setOnQueryCancelListener(OnQueryCancel onQueryCancel){
        oqc=onQueryCancel;
    }
    public interface OnQueryCancel{
        void  onQueryCancel(View v);
    }

    OnQueryOk oqo;
    public void setOnQueryOkListener(OnQueryOk onQueryOk){
        oqo=onQueryOk;
    }
    public interface OnQueryOk{
        void  onQueryOkClick(View v);
    }

    OnDialogMissListener odml;
    public void setOnDialogMissListener(OnDialogMissListener odml) {
        this.odml = odml;
    }
    public interface OnDialogMissListener{
        void  onDialogMiss(AlertDialog adlog);
    }

    public void showDiyView(Context context, int layoutId, DiyDialog diy) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(diy!=null){
            diy.builder(builder);
        }
        final AlertDialog  lDialog=builder.create();
        lDialog.setView(LayoutInflater.from(context).inflate(layoutId, null));
        lDialog.show();
        Window window = lDialog.getWindow();
        window.setContentView(layoutId);
        if(diy!=null){
            diy.onDialog(lDialog, window);
        }
    }

    public interface DiyDialog extends View.OnClickListener {
        void onDialog( final AlertDialog lDialog,Window window);
        void builder(AlertDialog.Builder builder);
    }

}

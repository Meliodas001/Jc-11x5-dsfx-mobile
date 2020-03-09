package com.jingcai.jc_11x5.view.widget;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by yangsen on 2016/7/21.
 */
public class ProgressWidget {
    private static ProgressDialog mProgressDialog;
    public static void showProgressDialog(Context context, CharSequence message){
        if(mProgressDialog == null){
            //mProgressDialog = ProgressDialog.show(context, "", message);
            mProgressDialog = ProgressDialog.show(context, "", message, true, true, null);
        }else{
            mProgressDialog.show();
        }
    }


    public static void dismissProgressDialog(){
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}

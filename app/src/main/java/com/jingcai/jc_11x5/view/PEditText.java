package com.jingcai.jc_11x5.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 关于黏贴重写
 */

public class PEditText extends EditText {

    private static final int ID_PASTE = android.R.id.paste;

    public PEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        if(id == ID_PASTE){
            ClipboardManager clip = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            //clip.setText("增加的内容==>"+clip.getText());
            ClipData mClipData = clip.getPrimaryClip();//GET贴板是否有内容
            ClipData.Item item = mClipData.getItemAt(0);//获取到内容
            String text = item.getText().toString().replaceAll(" +"," ").trim();
            text = text.replaceAll("\n+","\n");
            if(text.length() >= 1 && text.substring(0, 1).equals("\n")){
                text = text.substring(1, text.length());
            }
            clip.setPrimaryClip(ClipData.newPlainText(null, "\n"+text));
        }
        return super.onTextContextMenuItem(id);
    }

    OnPasteListener onListeners;

    public OnPasteListener getOnListeners() {
        return onListeners;
    }

    public void setOnListeners(OnPasteListener onListeners) {
        this.onListeners = onListeners;
    }

    public interface OnPasteListener{
        //void onPasteListener(int defauftCount);
        int getDefauftCount();
    }
}

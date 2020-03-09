package com.jingcai.jc_11x5.view;

/**
 * Created by yangsen on 2017/6/4.
 */

public class MenuPopwindow {}

/*
public class MenuPopwindow extends PopupWindow {

    private View conentView;
    private ListView lvContent;
    //弹窗子类项选中时的监听
    private OnItemOnClickListener mItemOnClickListener;


    public MenuPopwindow(Activity context, List<LostInfo> list) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_window_menu, null);
        lvContent = (ListView) conentView.findViewById(R.id.lv_toptitle_menu);
        lvContent.setAdapter(new MyAdapter(context, list));
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 3+60);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.AnimationPreview);

        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                if(mItemOnClickListener != null)
                    mItemOnClickListener.onItemClick(parent, view, position, id);
            }
        });
    }

    */
/**
     * 设置监听事件
     *//*

    public void setItemOnClickListener(OnItemOnClickListener onItemOnClickListener){
        this.mItemOnClickListener = onItemOnClickListener;
    }

    public static interface OnItemOnClickListener{
        public void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    class MyAdapter extends BaseAdapter {
        private List<LostInfo> list;
        private LayoutInflater inflater;
        public MyAdapter(Context context, List<LostInfo> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
        }
        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.popup_window_menu_item, null);
                holder = new Holder();
                holder.tvItem = (TextView) convertView.findViewById(R.id.tv_menu_item);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            LostInfo lostInfo = list.get(position);
            holder.tvItem.setText(lostInfo.getLostConfig().getLostTypeMc()+" "+lostInfo.getTypeStr());
            return convertView;
        }
        class Holder {
            TextView tvItem;
        }
    }
    */
/**
     * 显示popupWindow
     *
     * @param parent
     *//*

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }
}
*/

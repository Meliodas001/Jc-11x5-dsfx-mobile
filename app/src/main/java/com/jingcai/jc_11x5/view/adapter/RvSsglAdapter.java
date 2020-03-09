package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.entity.SuoShuiFilter;
import com.jingcai.jc_11x5.view.widget.DialogWiget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Email      : marlborn.jars@gmail.com
 * Author     : Marlborn
 * Date       : 2017/4/26 16:35
 */

public class RvSsglAdapter extends RecyclerView.Adapter<RvSsglAdapter.BseeHolder> {

    List<SuoShuiFilter> list;
    Context context;
    public static final int VIEW_CONTENT0 = 0;
    public static final int VIEW_CONTENT1 = 1;
    public static final int VIEW_CONTENT2 = 2;
    public static final int VIEW_CONTENT3 = 3;
    public static final int VIEW_CONTENT4 = 4;
    public static final int VIEW_CONTENT5 = 5;
    public static final int VIEW_CONTENT6 = 6;
    public static final int VIEW_CONTENT7 = 7;
    public static final int VIEW_CONTENT8 = 8;
    public static final int VIEW_CONTENT9 = 9;
    public static final int VIEW_CONTENT10 = 10;
    public static final int VIEW_CONTENT11 = 11;
    public static final int VIEW_CONTENT12 = 12;
    public static final int VIEW_CONTENT13 = 13;
    public static final int VIEW_CONTENT14 = 14;
    public static final int VIEW_CONTENT15 = 15;
    public static final int VIEW_CONTENT16 = 16;
    public static final int VIEW_CONTENT17 = 17;


    private LayoutInflater inflater;
    private DialogWiget dialogWiget;

    public RvSsglAdapter(Context context, List<SuoShuiFilter> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.dialogWiget = new DialogWiget();
    }

    public List<SuoShuiFilter> getList() {
        return list;
    }

    public void setList(List<SuoShuiFilter> list) {
        if(list==null){
            this.list=new ArrayList<>();
        }else{
            this.list=list;
        }
        notifyDataSetChanged();
    }

    @Override
    public BseeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_CONTENT0:
                return new ViewHolder0(inflater.inflate(R.layout.recycle_item_jichuhaoma, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT1:
            case VIEW_CONTENT2:
            case VIEW_CONTENT3:
            case VIEW_CONTENT4:
            case VIEW_CONTENT5:
                return new ViewHolder1(inflater.inflate(R.layout.recycle_item_ssgl_danzu, parent, false), new CustomOnClickListener(), new CheckedChangeListener());
            case VIEW_CONTENT6:
                return new ViewHolder4(inflater.inflate(R.layout.recycle_item_ssgl_hezhi, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT7:
                return new ViewHolder5(inflater.inflate(R.layout.recycle_item_ssgl_hewei, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT8:
                return new ViewHolder6(inflater.inflate(R.layout.recycle_item_kuadu, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT9:
            case VIEW_CONTENT10:
            case VIEW_CONTENT11:
                return new ViewHolder7(inflater.inflate(R.layout.recycle_item_ssgl_bizhi, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT12:
                return new ViewHolder8(inflater.inflate(R.layout.recycle_item_linhaogeshu, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT13:
                return new ViewHolder10(inflater.inflate(R.layout.recycle_item_ssgl_chonghaogeshu, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT14:
                return new ViewHolder11(inflater.inflate(R.layout.recycle_item_pingjunzhi, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT15:
                return new ViewHolder13(inflater.inflate(R.layout.recycle_item_ssgl_yichuhaoguolv, parent, false));
            case VIEW_CONTENT16:
                return new ViewHolder14(inflater.inflate(R.layout.recycle_item_ssgl_lianhao, parent, false), new CustomOnClickListener());
            case VIEW_CONTENT17:
                return new ViewHolder15(inflater.inflate(R.layout.recycle_item_ssgl_012lubi, parent, false), new CustomOnClickListener());
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final BseeHolder holder, final int position) {
        final SuoShuiFilter suoShuiFilter = list.get(position);
        final Map<Integer, String> map = suoShuiFilter.getMap();
        if (holder instanceof ViewHolder0) {
            //       适配数据布局--基础号码
            ((ViewHolder0) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });

            ((ViewHolder0) holder).tvJchm1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder0) holder).tvJchm2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder0) holder).tvJchm3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder0) holder).tvJchm4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder0) holder).tvJchm5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder0) holder).tvJchm6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder0) holder).tvJchm7.setTag(suoShuiFilter.getType() + "," + 7);
            ((ViewHolder0) holder).tvJchm8.setTag(suoShuiFilter.getType() + "," + 8);
            ((ViewHolder0) holder).tvJchm9.setTag(suoShuiFilter.getType() + "," + 9);
            ((ViewHolder0) holder).tvJchm10.setTag(suoShuiFilter.getType() + "," + 10);
            ((ViewHolder0) holder).tvJchm11.setTag(suoShuiFilter.getType() + "," + 11);
            if(map.isEmpty()){
                map.put(1, "1");
                map.put(3, "3");
                map.put(5, "5");
                map.put(7, "7");
                map.put(9, "9");
                map.put(11, "11");
                map.put(2, "2");
                map.put(4, "4");
                map.put(6, "6");
                map.put(8, "8");
                map.put(10, "10");
                list.get(position).setMap(map);
                ((ViewHolder0) holder).tvJchm1.setSelected(true);
                ((ViewHolder0) holder).tvJchm2.setSelected(true);
                ((ViewHolder0) holder).tvJchm3.setSelected(true);
                ((ViewHolder0) holder).tvJchm4.setSelected(true);
                ((ViewHolder0) holder).tvJchm5.setSelected(true);
                ((ViewHolder0) holder).tvJchm6.setSelected(true);
                ((ViewHolder0) holder).tvJchm7.setSelected(true);
                ((ViewHolder0) holder).tvJchm8.setSelected(true);
                ((ViewHolder0) holder).tvJchm9.setSelected(true);
                ((ViewHolder0) holder).tvJchm10.setSelected(true);
                ((ViewHolder0) holder).tvJchm11.setSelected(true);
            }
            ((ViewHolder0) holder).tvDa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(6, "6");
                    map.put(7, "7");
                    map.put(8, "8");
                    map.put(9, "9");
                    map.put(10, "10");
                    map.put(11, "11");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(false);
                    ((ViewHolder0) holder).tvJchm2.setSelected(false);
                    ((ViewHolder0) holder).tvJchm3.setSelected(false);
                    ((ViewHolder0) holder).tvJchm4.setSelected(false);
                    ((ViewHolder0) holder).tvJchm5.setSelected(false);
                    ((ViewHolder0) holder).tvJchm6.setSelected(true);
                    ((ViewHolder0) holder).tvJchm7.setSelected(true);
                    ((ViewHolder0) holder).tvJchm8.setSelected(true);
                    ((ViewHolder0) holder).tvJchm9.setSelected(true);
                    ((ViewHolder0) holder).tvJchm10.setSelected(true);
                    ((ViewHolder0) holder).tvJchm11.setSelected(true);
                }
            });
            ((ViewHolder0) holder).tvXiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(2, "2");
                    map.put(3, "3");
                    map.put(4, "4");
                    map.put(5, "5");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(true);
                    ((ViewHolder0) holder).tvJchm2.setSelected(true);
                    ((ViewHolder0) holder).tvJchm3.setSelected(true);
                    ((ViewHolder0) holder).tvJchm4.setSelected(true);
                    ((ViewHolder0) holder).tvJchm5.setSelected(true);
                    ((ViewHolder0) holder).tvJchm6.setSelected(false);
                    ((ViewHolder0) holder).tvJchm7.setSelected(false);
                    ((ViewHolder0) holder).tvJchm8.setSelected(false);
                    ((ViewHolder0) holder).tvJchm9.setSelected(false);
                    ((ViewHolder0) holder).tvJchm10.setSelected(false);
                    ((ViewHolder0) holder).tvJchm11.setSelected(false);
                }
            });
            ((ViewHolder0) holder).tvJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(3, "3");
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    map.put(11, "11");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(true);
                    ((ViewHolder0) holder).tvJchm2.setSelected(false);
                    ((ViewHolder0) holder).tvJchm3.setSelected(true);
                    ((ViewHolder0) holder).tvJchm4.setSelected(false);
                    ((ViewHolder0) holder).tvJchm5.setSelected(true);
                    ((ViewHolder0) holder).tvJchm6.setSelected(false);
                    ((ViewHolder0) holder).tvJchm7.setSelected(true);
                    ((ViewHolder0) holder).tvJchm8.setSelected(false);
                    ((ViewHolder0) holder).tvJchm9.setSelected(true);
                    ((ViewHolder0) holder).tvJchm10.setSelected(false);
                    ((ViewHolder0) holder).tvJchm11.setSelected(true);
                }
            });
            ((ViewHolder0) holder).tvOu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(2, "2");
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(false);
                    ((ViewHolder0) holder).tvJchm2.setSelected(true);
                    ((ViewHolder0) holder).tvJchm3.setSelected(false);
                    ((ViewHolder0) holder).tvJchm4.setSelected(true);
                    ((ViewHolder0) holder).tvJchm5.setSelected(false);
                    ((ViewHolder0) holder).tvJchm6.setSelected(true);
                    ((ViewHolder0) holder).tvJchm7.setSelected(false);
                    ((ViewHolder0) holder).tvJchm8.setSelected(true);
                    ((ViewHolder0) holder).tvJchm9.setSelected(false);
                    ((ViewHolder0) holder).tvJchm10.setSelected(true);
                    ((ViewHolder0) holder).tvJchm11.setSelected(false);
                }
            });
            ((ViewHolder0) holder).tv0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(3, "3");
                    map.put(6, "6");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(false);
                    ((ViewHolder0) holder).tvJchm2.setSelected(false);
                    ((ViewHolder0) holder).tvJchm3.setSelected(true);
                    ((ViewHolder0) holder).tvJchm4.setSelected(false);
                    ((ViewHolder0) holder).tvJchm5.setSelected(false);
                    ((ViewHolder0) holder).tvJchm6.setSelected(true);
                    ((ViewHolder0) holder).tvJchm7.setSelected(false);
                    ((ViewHolder0) holder).tvJchm8.setSelected(false);
                    ((ViewHolder0) holder).tvJchm9.setSelected(true);
                    ((ViewHolder0) holder).tvJchm10.setSelected(false);
                    ((ViewHolder0) holder).tvJchm11.setSelected(false);
                }
            });
            ((ViewHolder0) holder).tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(4, "4");
                    map.put(7, "7");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(true);
                    ((ViewHolder0) holder).tvJchm2.setSelected(false);
                    ((ViewHolder0) holder).tvJchm3.setSelected(false);
                    ((ViewHolder0) holder).tvJchm4.setSelected(true);
                    ((ViewHolder0) holder).tvJchm5.setSelected(false);
                    ((ViewHolder0) holder).tvJchm6.setSelected(false);
                    ((ViewHolder0) holder).tvJchm7.setSelected(true);
                    ((ViewHolder0) holder).tvJchm8.setSelected(false);
                    ((ViewHolder0) holder).tvJchm9.setSelected(false);
                    ((ViewHolder0) holder).tvJchm10.setSelected(true);
                    ((ViewHolder0) holder).tvJchm11.setSelected(false);
                }
            });
            ((ViewHolder0) holder).tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(2, "2");
                    map.put(5, "5");
                    map.put(8, "8");
                    map.put(11, "11");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(false);
                    ((ViewHolder0) holder).tvJchm2.setSelected(true);
                    ((ViewHolder0) holder).tvJchm3.setSelected(false);
                    ((ViewHolder0) holder).tvJchm4.setSelected(false);
                    ((ViewHolder0) holder).tvJchm5.setSelected(true);
                    ((ViewHolder0) holder).tvJchm6.setSelected(false);
                    ((ViewHolder0) holder).tvJchm7.setSelected(false);
                    ((ViewHolder0) holder).tvJchm8.setSelected(true);
                    ((ViewHolder0) holder).tvJchm9.setSelected(false);
                    ((ViewHolder0) holder).tvJchm10.setSelected(false);
                    ((ViewHolder0) holder).tvJchm11.setSelected(true);
                }
            });
            ((ViewHolder0) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(3, "3");
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    map.put(11, "11");
                    map.put(2, "2");
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(true);
                    ((ViewHolder0) holder).tvJchm2.setSelected(true);
                    ((ViewHolder0) holder).tvJchm3.setSelected(true);
                    ((ViewHolder0) holder).tvJchm4.setSelected(true);
                    ((ViewHolder0) holder).tvJchm5.setSelected(true);
                    ((ViewHolder0) holder).tvJchm6.setSelected(true);
                    ((ViewHolder0) holder).tvJchm7.setSelected(true);
                    ((ViewHolder0) holder).tvJchm8.setSelected(true);
                    ((ViewHolder0) holder).tvJchm9.setSelected(true);
                    ((ViewHolder0) holder).tvJchm10.setSelected(true);
                    ((ViewHolder0) holder).tvJchm11.setSelected(true);
                }
            });
            ((ViewHolder0) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<Integer, String> map = list.get(position).getMap();
                    if (map.get(1) == null || TextUtils.isEmpty(map.get(1))) {
                        map.put(1, "1");
                        ((ViewHolder0) holder).tvJchm1.setSelected(true);
                    } else {
                        map.put(1, "");
                        ((ViewHolder0) holder).tvJchm1.setSelected(false);
                    }

                    if (map.get(2) == null || TextUtils.isEmpty(map.get(2))) {
                        map.put(2, "2");
                        ((ViewHolder0) holder).tvJchm2.setSelected(true);
                    } else {
                        map.put(2, "");
                        ((ViewHolder0) holder).tvJchm2.setSelected(false);
                    }

                    if (map.get(3) == null || TextUtils.isEmpty(map.get(3))) {
                        map.put(3, "3");
                        ((ViewHolder0) holder).tvJchm3.setSelected(true);
                    } else {
                        map.put(3, "");
                        ((ViewHolder0) holder).tvJchm3.setSelected(false);
                    }

                    if (map.get(4) == null || TextUtils.isEmpty(map.get(4))) {
                        map.put(4, "4");
                        ((ViewHolder0) holder).tvJchm4.setSelected(true);
                    } else {
                        map.put(4, "");
                        ((ViewHolder0) holder).tvJchm4.setSelected(false);
                    }

                    if (map.get(5) == null || TextUtils.isEmpty(map.get(5))) {
                        map.put(5, "5");
                        ((ViewHolder0) holder).tvJchm5.setSelected(true);
                    } else {
                        map.put(5, "");
                        ((ViewHolder0) holder).tvJchm5.setSelected(false);
                    }

                    if (map.get(6) == null || TextUtils.isEmpty(map.get(6))) {
                        map.put(6, "6");
                        ((ViewHolder0) holder).tvJchm6.setSelected(true);
                    } else {
                        map.put(6, "");
                        ((ViewHolder0) holder).tvJchm6.setSelected(false);
                    }

                    if (map.get(7) == null || TextUtils.isEmpty(map.get(7))) {
                        map.put(7, "7");
                        ((ViewHolder0) holder).tvJchm7.setSelected(true);
                    } else {
                        map.put(7, "");
                        ((ViewHolder0) holder).tvJchm7.setSelected(false);
                    }

                    if (map.get(8) == null || TextUtils.isEmpty(map.get(8))) {
                        map.put(8, "8");
                        ((ViewHolder0) holder).tvJchm8.setSelected(true);
                    } else {
                        map.put(8, "");
                        ((ViewHolder0) holder).tvJchm8.setSelected(false);
                    }

                    if (map.get(9) == null || TextUtils.isEmpty(map.get(9))) {
                        map.put(9, "9");
                        ((ViewHolder0) holder).tvJchm9.setSelected(true);
                    } else {
                        map.put(9, "");
                        ((ViewHolder0) holder).tvJchm9.setSelected(false);
                    }

                    if (map.get(10) == null || TextUtils.isEmpty(map.get(10))) {
                        map.put(10, "10");
                        ((ViewHolder0) holder).tvJchm10.setSelected(true);
                    } else {
                        map.put(10, "");
                        ((ViewHolder0) holder).tvJchm10.setSelected(false);
                    }

                    if (map.get(11) == null || TextUtils.isEmpty(map.get(11))) {
                        map.put(11, "11");
                        ((ViewHolder0) holder).tvJchm11.setSelected(true);
                    } else {
                        map.put(11, "");
                        ((ViewHolder0) holder).tvJchm11.setSelected(false);
                    }
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder0) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder0) holder).tvJchm1.setSelected(false);
                    ((ViewHolder0) holder).tvJchm2.setSelected(false);
                    ((ViewHolder0) holder).tvJchm3.setSelected(false);
                    ((ViewHolder0) holder).tvJchm4.setSelected(false);
                    ((ViewHolder0) holder).tvJchm5.setSelected(false);
                    ((ViewHolder0) holder).tvJchm6.setSelected(false);
                    ((ViewHolder0) holder).tvJchm7.setSelected(false);
                    ((ViewHolder0) holder).tvJchm8.setSelected(false);
                    ((ViewHolder0) holder).tvJchm9.setSelected(false);
                    ((ViewHolder0) holder).tvJchm10.setSelected(false);
                    ((ViewHolder0) holder).tvJchm11.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder1) {
            //       适配数据布局--胆组
            ((ViewHolder1) holder).tvDanzuMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            if (suoShuiFilter.getType() == 1) {
                ((ViewHolder1) holder).tvDanzuMc.setText("胆组一");
            }
            if (suoShuiFilter.getType() == 2) {
                ((ViewHolder1) holder).tvDanzuMc.setText("胆组二");
            }
            if (suoShuiFilter.getType() == 3) {
                ((ViewHolder1) holder).tvDanzuMc.setText("胆组三");
            }
            if (suoShuiFilter.getType() == 4) {
                ((ViewHolder1) holder).tvDanzuMc.setText("胆组四");
            }
            if (suoShuiFilter.getType() == 5) {
                ((ViewHolder1) holder).tvDanzuMc.setText("胆组五");
            }
            ((ViewHolder1) holder).tvDanzu1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder1) holder).tvDanzu2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder1) holder).tvDanzu3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder1) holder).tvDanzu4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder1) holder).tvDanzu5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder1) holder).tvDanzu6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder1) holder).tvDanzu7.setTag(suoShuiFilter.getType() + "," + 7);
            ((ViewHolder1) holder).tvDanzu8.setTag(suoShuiFilter.getType() + "," + 8);
            ((ViewHolder1) holder).tvDanzu9.setTag(suoShuiFilter.getType() + "," + 9);
            ((ViewHolder1) holder).tvDanzu10.setTag(suoShuiFilter.getType() + "," + 10);
            ((ViewHolder1) holder).tvDanzu11.setTag(suoShuiFilter.getType() + "," + 11);

            ((ViewHolder1) holder).cbChu0.setTag(suoShuiFilter.getType() + "," + 12);
            ((ViewHolder1) holder).cbChu1.setTag(suoShuiFilter.getType() + "," + 13);
            ((ViewHolder1) holder).cbChu2.setTag(suoShuiFilter.getType() + "," + 14);
            ((ViewHolder1) holder).cbChu3.setTag(suoShuiFilter.getType() + "," + 15);
            ((ViewHolder1) holder).cbChu4.setTag(suoShuiFilter.getType() + "," + 16);
            ((ViewHolder1) holder).cbChu5.setTag(suoShuiFilter.getType() + "," + 17);

            if(map.isEmpty()){
                ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                ((ViewHolder1) holder).cbChu0.setChecked(false);
                ((ViewHolder1) holder).cbChu1.setChecked(false);
                ((ViewHolder1) holder).cbChu2.setChecked(false);
                ((ViewHolder1) holder).cbChu3.setChecked(false);
                ((ViewHolder1) holder).cbChu4.setChecked(false);
                ((ViewHolder1) holder).cbChu5.setChecked(false);
            }

            ((ViewHolder1) holder).tvDa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(6, "6");
                    map.put(7, "7");
                    map.put(8, "8");
                    map.put(9, "9");
                    map.put(10, "10");
                    map.put(11, "11");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(true);
                }
            });
            ((ViewHolder1) holder).tvXiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(2, "2");
                    map.put(3, "3");
                    map.put(4, "4");
                    map.put(5, "5");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                }
            });
            ((ViewHolder1) holder).tvJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(3, "3");
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    map.put(11, "11");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(true);
                }
            });
            ((ViewHolder1) holder).tvOu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(2, "2");
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                }
            });
            ((ViewHolder1) holder).tv0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(3, "3");
                    map.put(6, "6");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                }
            });
            ((ViewHolder1) holder).tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(4, "4");
                    map.put(7, "7");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                }
            });
            ((ViewHolder1) holder).tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(2, "2");
                    map.put(5, "5");
                    map.put(8, "8");
                    map.put(11, "11");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(true);
                }
            });
            ((ViewHolder1) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(3, "3");
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    map.put(11, "11");
                    map.put(2, "2");
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(true);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(true);
                }
            });
            ((ViewHolder1) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<Integer, String> map = list.get(position).getMap();
                    if (map.get(1) == null || TextUtils.isEmpty(map.get(1))) {
                        map.put(1, "1");
                        ((ViewHolder1) holder).tvDanzu1.setSelected(true);
                    } else {
                        map.put(1, "");
                        ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                    }

                    if (map.get(2) == null || TextUtils.isEmpty(map.get(2))) {
                        map.put(2, "2");
                        ((ViewHolder1) holder).tvDanzu2.setSelected(true);
                    } else {
                        map.put(2, "");
                        ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                    }

                    if (map.get(3) == null || TextUtils.isEmpty(map.get(3))) {
                        map.put(3, "3");
                        ((ViewHolder1) holder).tvDanzu3.setSelected(true);
                    } else {
                        map.put(3, "");
                        ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                    }

                    if (map.get(4) == null || TextUtils.isEmpty(map.get(4))) {
                        map.put(4, "4");
                        ((ViewHolder1) holder).tvDanzu4.setSelected(true);
                    } else {
                        map.put(4, "");
                        ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                    }

                    if (map.get(5) == null || TextUtils.isEmpty(map.get(5))) {
                        map.put(5, "5");
                        ((ViewHolder1) holder).tvDanzu5.setSelected(true);
                    } else {
                        map.put(5, "");
                        ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                    }

                    if (map.get(6) == null || TextUtils.isEmpty(map.get(6))) {
                        map.put(6, "6");
                        ((ViewHolder1) holder).tvDanzu6.setSelected(true);
                    } else {
                        map.put(6, "");
                        ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                    }

                    if (map.get(7) == null || TextUtils.isEmpty(map.get(7))) {
                        map.put(7, "7");
                        ((ViewHolder1) holder).tvDanzu7.setSelected(true);
                    } else {
                        map.put(7, "");
                        ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                    }

                    if (map.get(8) == null || TextUtils.isEmpty(map.get(8))) {
                        map.put(8, "8");
                        ((ViewHolder1) holder).tvDanzu8.setSelected(true);
                    } else {
                        map.put(8, "");
                        ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                    }

                    if (map.get(9) == null || TextUtils.isEmpty(map.get(9))) {
                        map.put(9, "9");
                        ((ViewHolder1) holder).tvDanzu9.setSelected(true);
                    } else {
                        map.put(9, "");
                        ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                    }

                    if (map.get(10) == null || TextUtils.isEmpty(map.get(10))) {
                        map.put(10, "10");
                        ((ViewHolder1) holder).tvDanzu10.setSelected(true);
                    } else {
                        map.put(10, "");
                        ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                    }

                    if (map.get(11) == null || TextUtils.isEmpty(map.get(11))) {
                        map.put(11, "11");
                        ((ViewHolder1) holder).tvDanzu11.setSelected(true);
                    } else {
                        map.put(11, "");
                        ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                    }
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder1) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder1) holder).tvDanzu1.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu2.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu3.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu4.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu5.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu6.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu7.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu8.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu9.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu10.setSelected(false);
                    ((ViewHolder1) holder).tvDanzu11.setSelected(false);
                    ((ViewHolder1) holder).cbChu0.setChecked(false);
                    ((ViewHolder1) holder).cbChu1.setChecked(false);
                    ((ViewHolder1) holder).cbChu2.setChecked(false);
                    ((ViewHolder1) holder).cbChu3.setChecked(false);
                    ((ViewHolder1) holder).cbChu4.setChecked(false);
                    ((ViewHolder1) holder).cbChu5.setChecked(false);
                }
            });

        } else if (holder instanceof ViewHolder4) {
            //       适配数据布局--和值
            ((ViewHolder4) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            ((ViewHolder4) holder).tvHezhi15.setTag(suoShuiFilter.getType() + "," + 15);
            ((ViewHolder4) holder).tvHezhi16.setTag(suoShuiFilter.getType() + "," + 16);
            ((ViewHolder4) holder).tvHezhi17.setTag(suoShuiFilter.getType() + "," + 17);
            ((ViewHolder4) holder).tvHezhi18.setTag(suoShuiFilter.getType() + "," + 18);
            ((ViewHolder4) holder).tvHezhi19.setTag(suoShuiFilter.getType() + "," + 19);
            ((ViewHolder4) holder).tvHezhi20.setTag(suoShuiFilter.getType() + "," + 20);
            ((ViewHolder4) holder).tvHezhi21.setTag(suoShuiFilter.getType() + "," + 21);
            ((ViewHolder4) holder).tvHezhi22.setTag(suoShuiFilter.getType() + "," + 22);
            ((ViewHolder4) holder).tvHezhi23.setTag(suoShuiFilter.getType() + "," + 23);
            ((ViewHolder4) holder).tvHezhi24.setTag(suoShuiFilter.getType() + "," + 24);
            ((ViewHolder4) holder).tvHezhi25.setTag(suoShuiFilter.getType() + "," + 25);
            ((ViewHolder4) holder).tvHezhi26.setTag(suoShuiFilter.getType() + "," + 26);
            ((ViewHolder4) holder).tvHezhi27.setTag(suoShuiFilter.getType() + "," + 27);
            ((ViewHolder4) holder).tvHezhi28.setTag(suoShuiFilter.getType() + "," + 28);
            ((ViewHolder4) holder).tvHezhi29.setTag(suoShuiFilter.getType() + "," + 29);
            ((ViewHolder4) holder).tvHezhi30.setTag(suoShuiFilter.getType() + "," + 30);
            ((ViewHolder4) holder).tvHezhi31.setTag(suoShuiFilter.getType() + "," + 31);
            ((ViewHolder4) holder).tvHezhi32.setTag(suoShuiFilter.getType() + "," + 32);
            ((ViewHolder4) holder).tvHezhi33.setTag(suoShuiFilter.getType() + "," + 33);
            ((ViewHolder4) holder).tvHezhi34.setTag(suoShuiFilter.getType() + "," + 34);
            ((ViewHolder4) holder).tvHezhi35.setTag(suoShuiFilter.getType() + "," + 35);
            ((ViewHolder4) holder).tvHezhi36.setTag(suoShuiFilter.getType() + "," + 36);
            ((ViewHolder4) holder).tvHezhi37.setTag(suoShuiFilter.getType() + "," + 37);
            ((ViewHolder4) holder).tvHezhi38.setTag(suoShuiFilter.getType() + "," + 38);
            ((ViewHolder4) holder).tvHezhi39.setTag(suoShuiFilter.getType() + "," + 39);
            ((ViewHolder4) holder).tvHezhi40.setTag(suoShuiFilter.getType() + "," + 40);
            ((ViewHolder4) holder).tvHezhi41.setTag(suoShuiFilter.getType() + "," + 41);
            ((ViewHolder4) holder).tvHezhi42.setTag(suoShuiFilter.getType() + "," + 42);
            ((ViewHolder4) holder).tvHezhi43.setTag(suoShuiFilter.getType() + "," + 43);
            ((ViewHolder4) holder).tvHezhi44.setTag(suoShuiFilter.getType() + "," + 44);
            ((ViewHolder4) holder).tvHezhi45.setTag(suoShuiFilter.getType() + "," + 45);

            if(map.isEmpty()){
                ((ViewHolder4) holder).tvHezhi15.setSelected(false);
                ((ViewHolder4) holder).tvHezhi16.setSelected(false);
                ((ViewHolder4) holder).tvHezhi17.setSelected(false);
                ((ViewHolder4) holder).tvHezhi18.setSelected(false);
                ((ViewHolder4) holder).tvHezhi19.setSelected(false);
                ((ViewHolder4) holder).tvHezhi20.setSelected(false);
                ((ViewHolder4) holder).tvHezhi21.setSelected(false);
                ((ViewHolder4) holder).tvHezhi22.setSelected(false);
                ((ViewHolder4) holder).tvHezhi23.setSelected(false);
                ((ViewHolder4) holder).tvHezhi24.setSelected(false);
                ((ViewHolder4) holder).tvHezhi25.setSelected(false);
                ((ViewHolder4) holder).tvHezhi26.setSelected(false);
                ((ViewHolder4) holder).tvHezhi27.setSelected(false);
                ((ViewHolder4) holder).tvHezhi28.setSelected(false);
                ((ViewHolder4) holder).tvHezhi29.setSelected(false);
                ((ViewHolder4) holder).tvHezhi30.setSelected(false);
                ((ViewHolder4) holder).tvHezhi31.setSelected(false);
                ((ViewHolder4) holder).tvHezhi32.setSelected(false);
                ((ViewHolder4) holder).tvHezhi33.setSelected(false);
                ((ViewHolder4) holder).tvHezhi34.setSelected(false);
                ((ViewHolder4) holder).tvHezhi35.setSelected(false);
                ((ViewHolder4) holder).tvHezhi36.setSelected(false);
                ((ViewHolder4) holder).tvHezhi37.setSelected(false);
                ((ViewHolder4) holder).tvHezhi38.setSelected(false);
                ((ViewHolder4) holder).tvHezhi39.setSelected(false);
                ((ViewHolder4) holder).tvHezhi40.setSelected(false);
                ((ViewHolder4) holder).tvHezhi41.setSelected(false);
                ((ViewHolder4) holder).tvHezhi42.setSelected(false);
                ((ViewHolder4) holder).tvHezhi43.setSelected(false);
                ((ViewHolder4) holder).tvHezhi44.setSelected(false);
                ((ViewHolder4) holder).tvHezhi45.setSelected(false);
            }

            ((ViewHolder4) holder).tvJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(15, "15");
                    map.put(17, "17");
                    map.put(19, "19");
                    map.put(21, "21");
                    map.put(23, "23");
                    map.put(25, "25");
                    map.put(27, "27");
                    map.put(29, "29");
                    map.put(31, "31");
                    map.put(33, "33");
                    map.put(35, "35");
                    map.put(37, "37");
                    map.put(39, "39");
                    map.put(41, "41");
                    map.put(43, "43");
                    map.put(45, "45");
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(true);
                }
            });
            ((ViewHolder4) holder).tvOu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(16, "16");
                    map.put(18, "18");
                    map.put(20, "20");
                    map.put(22, "22");
                    map.put(24, "24");
                    map.put(26, "26");
                    map.put(28, "28");
                    map.put(30, "30");
                    map.put(32, "32");
                    map.put(34, "34");
                    map.put(36, "36");
                    map.put(38, "38");
                    map.put(40, "40");
                    map.put(42, "42");
                    map.put(44, "44");
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(false);
                }
            });
            ((ViewHolder4) holder).tv0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(15, "15");
                    map.put(18, "18");
                    map.put(21, "21");
                    map.put(24, "24");
                    map.put(27, "27");
                    map.put(30, "30");
                    map.put(33, "33");
                    map.put(36, "36");
                    map.put(39, "39");
                    map.put(42, "42");
                    map.put(45, "45");
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(true);
                }
            });
            ((ViewHolder4) holder).tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(16, "16");
                    map.put(19, "19");
                    map.put(22, "22");
                    map.put(25, "25");
                    map.put(28, "28");
                    map.put(31, "31");
                    map.put(34, "34");
                    map.put(37, "37");
                    map.put(40, "40");
                    map.put(43, "43");
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(false);
                }
            });
            ((ViewHolder4) holder).tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(17, "17");
                    map.put(20, "20");
                    map.put(23, "23");
                    map.put(26, "26");
                    map.put(29, "29");
                    map.put(32, "32");
                    map.put(35, "35");
                    map.put(38, "38");
                    map.put(41, "41");
                    map.put(44, "44");
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(false);
                }
            });
            ((ViewHolder4) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(15, "15");
                    map.put(17, "17");
                    map.put(19, "19");
                    map.put(21, "21");
                    map.put(23, "23");
                    map.put(25, "25");
                    map.put(27, "27");
                    map.put(29, "29");
                    map.put(31, "31");
                    map.put(33, "33");
                    map.put(35, "35");
                    map.put(37, "37");
                    map.put(39, "39");
                    map.put(41, "41");
                    map.put(43, "43");
                    map.put(45, "45");
                    map.put(16, "16");
                    map.put(18, "18");
                    map.put(20, "20");
                    map.put(22, "22");
                    map.put(24, "24");
                    map.put(26, "26");
                    map.put(28, "28");
                    map.put(30, "30");
                    map.put(32, "32");
                    map.put(34, "34");
                    map.put(36, "36");
                    map.put(38, "38");
                    map.put(40, "40");
                    map.put(42, "42");
                    map.put(44, "44");
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(true);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(true);
                }
            });
            ((ViewHolder4) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFan(((ViewHolder4) holder).tvHezhi15, map, 15);
                    initFan(((ViewHolder4) holder).tvHezhi16, map, 16);
                    initFan(((ViewHolder4) holder).tvHezhi17, map, 17);
                    initFan(((ViewHolder4) holder).tvHezhi18, map, 18);
                    initFan(((ViewHolder4) holder).tvHezhi19, map, 19);
                    initFan(((ViewHolder4) holder).tvHezhi20, map, 20);
                    initFan(((ViewHolder4) holder).tvHezhi21, map, 21);
                    initFan(((ViewHolder4) holder).tvHezhi22, map, 22);
                    initFan(((ViewHolder4) holder).tvHezhi23, map, 23);
                    initFan(((ViewHolder4) holder).tvHezhi24, map, 24);
                    initFan(((ViewHolder4) holder).tvHezhi25, map, 25);
                    initFan(((ViewHolder4) holder).tvHezhi26, map, 26);
                    initFan(((ViewHolder4) holder).tvHezhi27, map, 27);
                    initFan(((ViewHolder4) holder).tvHezhi28, map, 28);
                    initFan(((ViewHolder4) holder).tvHezhi29, map, 29);
                    initFan(((ViewHolder4) holder).tvHezhi30, map, 30);
                    initFan(((ViewHolder4) holder).tvHezhi31, map, 31);
                    initFan(((ViewHolder4) holder).tvHezhi32, map, 32);
                    initFan(((ViewHolder4) holder).tvHezhi33, map, 33);
                    initFan(((ViewHolder4) holder).tvHezhi34, map, 34);
                    initFan(((ViewHolder4) holder).tvHezhi35, map, 35);
                    initFan(((ViewHolder4) holder).tvHezhi36, map, 36);
                    initFan(((ViewHolder4) holder).tvHezhi37, map, 37);
                    initFan(((ViewHolder4) holder).tvHezhi38, map, 38);
                    initFan(((ViewHolder4) holder).tvHezhi39, map, 39);
                    initFan(((ViewHolder4) holder).tvHezhi40, map, 40);
                    initFan(((ViewHolder4) holder).tvHezhi41, map, 41);
                    initFan(((ViewHolder4) holder).tvHezhi42, map, 42);
                    initFan(((ViewHolder4) holder).tvHezhi43, map, 43);
                    initFan(((ViewHolder4) holder).tvHezhi44, map, 44);
                    initFan(((ViewHolder4) holder).tvHezhi45, map, 45);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder4) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder4) holder).tvHezhi15.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi16.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi17.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi18.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi19.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi20.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi21.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi22.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi23.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi24.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi25.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi26.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi27.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi28.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi29.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi30.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi31.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi32.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi33.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi34.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi35.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi36.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi37.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi38.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi39.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi40.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi41.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi42.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi43.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi44.setSelected(false);
                    ((ViewHolder4) holder).tvHezhi45.setSelected(false);
                }
            });


        } else if (holder instanceof ViewHolder5) {
            //       适配数据布局--和尾
            ((ViewHolder5) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            ((ViewHolder5) holder).tvHewei0.setTag(suoShuiFilter.getType() + "," + 0);
            ((ViewHolder5) holder).tvHewei1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder5) holder).tvHewei2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder5) holder).tvHewei3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder5) holder).tvHewei4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder5) holder).tvHewei5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder5) holder).tvHewei6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder5) holder).tvHewei7.setTag(suoShuiFilter.getType() + "," + 7);
            ((ViewHolder5) holder).tvHewei8.setTag(suoShuiFilter.getType() + "," + 8);
            ((ViewHolder5) holder).tvHewei9.setTag(suoShuiFilter.getType() + "," + 9);

            if(map.isEmpty()){
                ((ViewHolder5) holder).tvHewei0.setSelected(false);
                ((ViewHolder5) holder).tvHewei1.setSelected(false);
                ((ViewHolder5) holder).tvHewei2.setSelected(false);
                ((ViewHolder5) holder).tvHewei3.setSelected(false);
                ((ViewHolder5) holder).tvHewei4.setSelected(false);
                ((ViewHolder5) holder).tvHewei5.setSelected(false);
                ((ViewHolder5) holder).tvHewei6.setSelected(false);
                ((ViewHolder5) holder).tvHewei7.setSelected(false);
                ((ViewHolder5) holder).tvHewei8.setSelected(false);
                ((ViewHolder5) holder).tvHewei9.setSelected(false);
            }

            ((ViewHolder5) holder).tvJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(3, "3");
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(false);
                    ((ViewHolder5) holder).tvHewei1.setSelected(true);
                    ((ViewHolder5) holder).tvHewei2.setSelected(false);
                    ((ViewHolder5) holder).tvHewei3.setSelected(true);
                    ((ViewHolder5) holder).tvHewei4.setSelected(false);
                    ((ViewHolder5) holder).tvHewei5.setSelected(true);
                    ((ViewHolder5) holder).tvHewei6.setSelected(false);
                    ((ViewHolder5) holder).tvHewei7.setSelected(true);
                    ((ViewHolder5) holder).tvHewei8.setSelected(false);
                    ((ViewHolder5) holder).tvHewei9.setSelected(true);
                }
            });
            ((ViewHolder5) holder).tvOu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(0, "0");
                    map.put(2, "2");
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(true);
                    ((ViewHolder5) holder).tvHewei1.setSelected(false);
                    ((ViewHolder5) holder).tvHewei2.setSelected(true);
                    ((ViewHolder5) holder).tvHewei3.setSelected(false);
                    ((ViewHolder5) holder).tvHewei4.setSelected(true);
                    ((ViewHolder5) holder).tvHewei5.setSelected(false);
                    ((ViewHolder5) holder).tvHewei6.setSelected(true);
                    ((ViewHolder5) holder).tvHewei7.setSelected(false);
                    ((ViewHolder5) holder).tvHewei8.setSelected(true);
                    ((ViewHolder5) holder).tvHewei9.setSelected(false);
                }
            });
            ((ViewHolder5) holder).tv0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(0, "0");
                    map.put(3, "3");
                    map.put(6, "6");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(true);
                    ((ViewHolder5) holder).tvHewei1.setSelected(false);
                    ((ViewHolder5) holder).tvHewei2.setSelected(false);
                    ((ViewHolder5) holder).tvHewei3.setSelected(true);
                    ((ViewHolder5) holder).tvHewei4.setSelected(false);
                    ((ViewHolder5) holder).tvHewei5.setSelected(false);
                    ((ViewHolder5) holder).tvHewei6.setSelected(true);
                    ((ViewHolder5) holder).tvHewei7.setSelected(false);
                    ((ViewHolder5) holder).tvHewei8.setSelected(false);
                    ((ViewHolder5) holder).tvHewei9.setSelected(true);
                }
            });
            ((ViewHolder5) holder).tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, "1");
                    map.put(4, "4");
                    map.put(7, "7");
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(false);
                    ((ViewHolder5) holder).tvHewei1.setSelected(true);
                    ((ViewHolder5) holder).tvHewei2.setSelected(false);
                    ((ViewHolder5) holder).tvHewei3.setSelected(false);
                    ((ViewHolder5) holder).tvHewei4.setSelected(true);
                    ((ViewHolder5) holder).tvHewei5.setSelected(false);
                    ((ViewHolder5) holder).tvHewei6.setSelected(false);
                    ((ViewHolder5) holder).tvHewei7.setSelected(true);
                    ((ViewHolder5) holder).tvHewei8.setSelected(false);
                    ((ViewHolder5) holder).tvHewei9.setSelected(false);
                }
            });
            ((ViewHolder5) holder).tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(2, "2");
                    map.put(5, "5");
                    map.put(8, "8");
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(false);
                    ((ViewHolder5) holder).tvHewei1.setSelected(false);
                    ((ViewHolder5) holder).tvHewei2.setSelected(true);
                    ((ViewHolder5) holder).tvHewei3.setSelected(false);
                    ((ViewHolder5) holder).tvHewei4.setSelected(false);
                    ((ViewHolder5) holder).tvHewei5.setSelected(true);
                    ((ViewHolder5) holder).tvHewei6.setSelected(false);
                    ((ViewHolder5) holder).tvHewei7.setSelected(false);
                    ((ViewHolder5) holder).tvHewei8.setSelected(true);
                    ((ViewHolder5) holder).tvHewei9.setSelected(false);
                }
            });
            ((ViewHolder5) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(0, "0");
                    map.put(1, "1");
                    map.put(2, "2");
                    map.put(3, "3");
                    map.put(4, "4");
                    map.put(5, "5");
                    map.put(6, "6");
                    map.put(7, "7");
                    map.put(8, "8");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(true);
                    ((ViewHolder5) holder).tvHewei1.setSelected(true);
                    ((ViewHolder5) holder).tvHewei2.setSelected(true);
                    ((ViewHolder5) holder).tvHewei3.setSelected(true);
                    ((ViewHolder5) holder).tvHewei4.setSelected(true);
                    ((ViewHolder5) holder).tvHewei5.setSelected(true);
                    ((ViewHolder5) holder).tvHewei6.setSelected(true);
                    ((ViewHolder5) holder).tvHewei7.setSelected(true);
                    ((ViewHolder5) holder).tvHewei8.setSelected(true);
                    ((ViewHolder5) holder).tvHewei9.setSelected(true);
                }
            });
            ((ViewHolder5) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFan(((ViewHolder5) holder).tvHewei0, map, 0);
                    initFan(((ViewHolder5) holder).tvHewei1, map, 1);
                    initFan(((ViewHolder5) holder).tvHewei2, map, 2);
                    initFan(((ViewHolder5) holder).tvHewei3, map, 3);
                    initFan(((ViewHolder5) holder).tvHewei4, map, 4);
                    initFan(((ViewHolder5) holder).tvHewei5, map, 5);
                    initFan(((ViewHolder5) holder).tvHewei6, map, 6);
                    initFan(((ViewHolder5) holder).tvHewei7, map, 7);
                    initFan(((ViewHolder5) holder).tvHewei8, map, 8);
                    initFan(((ViewHolder5) holder).tvHewei9, map, 9);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder5) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder5) holder).tvHewei0.setSelected(false);
                    ((ViewHolder5) holder).tvHewei1.setSelected(false);
                    ((ViewHolder5) holder).tvHewei2.setSelected(false);
                    ((ViewHolder5) holder).tvHewei3.setSelected(false);
                    ((ViewHolder5) holder).tvHewei4.setSelected(false);
                    ((ViewHolder5) holder).tvHewei5.setSelected(false);
                    ((ViewHolder5) holder).tvHewei6.setSelected(false);
                    ((ViewHolder5) holder).tvHewei7.setSelected(false);
                    ((ViewHolder5) holder).tvHewei8.setSelected(false);
                    ((ViewHolder5) holder).tvHewei9.setSelected(false);
                }
            });


        } else if (holder instanceof ViewHolder6) {
            //       适配数据布局--跨度
            ((ViewHolder6) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });

            ((ViewHolder6) holder).tvKuadu4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder6) holder).tvKuadu5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder6) holder).tvKuadu6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder6) holder).tvKuadu7.setTag(suoShuiFilter.getType() + "," + 7);
            ((ViewHolder6) holder).tvKuadu8.setTag(suoShuiFilter.getType() + "," + 8);
            ((ViewHolder6) holder).tvKuadu9.setTag(suoShuiFilter.getType() + "," + 9);
            ((ViewHolder6) holder).tvKuadu10.setTag(suoShuiFilter.getType() + "," + 10);

            if(map.isEmpty()){
                ((ViewHolder6) holder).tvKuadu4.setSelected(false);
                ((ViewHolder6) holder).tvKuadu5.setSelected(false);
                ((ViewHolder6) holder).tvKuadu6.setSelected(false);
                ((ViewHolder6) holder).tvKuadu7.setSelected(false);
                ((ViewHolder6) holder).tvKuadu8.setSelected(false);
                ((ViewHolder6) holder).tvKuadu9.setSelected(false);
                ((ViewHolder6) holder).tvKuadu10.setSelected(false);
            }

            ((ViewHolder6) holder).tvDa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(6, "6");
                    map.put(7, "7");
                    map.put(8, "8");
                    map.put(9, "9");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(true);
                }
            });

            ((ViewHolder6) holder).tvXiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(4, "4");
                    map.put(5, "5");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(false);
                }
            });

            ((ViewHolder6) holder).tvJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(false);
                }
            });
            ((ViewHolder6) holder).tvOu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(true);
                }
            });
            ((ViewHolder6) holder).tv0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(6, "6");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(false);
                }
            });
            ((ViewHolder6) holder).tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(4, "4");
                    map.put(7, "7");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(true);
                }
            });
            ((ViewHolder6) holder).tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(5, "5");
                    map.put(8, "8");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(false);
                }
            });
            ((ViewHolder6) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(4, "4");
                    map.put(5, "5");
                    map.put(6, "6");
                    map.put(7, "7");
                    map.put(8, "8");
                    map.put(9, "9");
                    map.put(10, "10");
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(true);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(true);
                }
            });
            ((ViewHolder6) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFan(((ViewHolder6) holder).tvKuadu4, map, 4);
                    initFan(((ViewHolder6) holder).tvKuadu5, map, 5);
                    initFan(((ViewHolder6) holder).tvKuadu6, map, 6);
                    initFan(((ViewHolder6) holder).tvKuadu7, map, 7);
                    initFan(((ViewHolder6) holder).tvKuadu8, map, 8);
                    initFan(((ViewHolder6) holder).tvKuadu9, map, 9);
                    initFan(((ViewHolder6) holder).tvKuadu10, map, 10);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder6) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder6) holder).tvKuadu4.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu5.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu6.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu7.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu8.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu9.setSelected(false);
                    ((ViewHolder6) holder).tvKuadu10.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder7) {
            ((ViewHolder7) holder).tvBizhiMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });

            //       适配数据布局--比值
            if (suoShuiFilter.getType() == 11) {
                ((ViewHolder7) holder).tvBizhiMc.setText("奇偶比");
            }
            if (suoShuiFilter.getType() == 10) {
                ((ViewHolder7) holder).tvBizhiMc.setText("质合比");
            }
            if (suoShuiFilter.getType() == 9) {
                ((ViewHolder7) holder).tvBizhiMc.setText("大小比");
            }
            ((ViewHolder7) holder).tvBizhi1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder7) holder).tvBizhi2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder7) holder).tvBizhi3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder7) holder).tvBizhi4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder7) holder).tvBizhi5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder7) holder).tvBizhi6.setTag(suoShuiFilter.getType() + "," + 6);

            if(map.isEmpty()){
                ((ViewHolder7) holder).tvBizhi1.setSelected(false);
                ((ViewHolder7) holder).tvBizhi2.setSelected(false);
                ((ViewHolder7) holder).tvBizhi3.setSelected(false);
                ((ViewHolder7) holder).tvBizhi4.setSelected(false);
                ((ViewHolder7) holder).tvBizhi5.setSelected(false);
                ((ViewHolder7) holder).tvBizhi6.setSelected(false);
            }

            ((ViewHolder7) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, ((ViewHolder7) holder).tvBizhi1.getText().toString());
                    map.put(2, ((ViewHolder7) holder).tvBizhi2.getText().toString());
                    map.put(3, ((ViewHolder7) holder).tvBizhi3.getText().toString());
                    map.put(4, ((ViewHolder7) holder).tvBizhi4.getText().toString());
                    map.put(5, ((ViewHolder7) holder).tvBizhi5.getText().toString());
                    map.put(6, ((ViewHolder7) holder).tvBizhi6.getText().toString());
                    list.get(position).setMap(map);
                    ((ViewHolder7) holder).tvBizhi1.setSelected(true);
                    ((ViewHolder7) holder).tvBizhi2.setSelected(true);
                    ((ViewHolder7) holder).tvBizhi3.setSelected(true);
                    ((ViewHolder7) holder).tvBizhi4.setSelected(true);
                    ((ViewHolder7) holder).tvBizhi5.setSelected(true);
                    ((ViewHolder7) holder).tvBizhi6.setSelected(true);
                }
            });
            ((ViewHolder7) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFanText(((ViewHolder7) holder).tvBizhi1, map, 1);
                    initFanText(((ViewHolder7) holder).tvBizhi2, map, 2);
                    initFanText(((ViewHolder7) holder).tvBizhi3, map, 3);
                    initFanText(((ViewHolder7) holder).tvBizhi4, map, 4);
                    initFanText(((ViewHolder7) holder).tvBizhi5, map, 5);
                    initFanText(((ViewHolder7) holder).tvBizhi6, map, 6);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder7) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder7) holder).tvBizhi1.setSelected(false);
                    ((ViewHolder7) holder).tvBizhi2.setSelected(false);
                    ((ViewHolder7) holder).tvBizhi3.setSelected(false);
                    ((ViewHolder7) holder).tvBizhi4.setSelected(false);
                    ((ViewHolder7) holder).tvBizhi5.setSelected(false);
                    ((ViewHolder7) holder).tvBizhi6.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder8) {
            ((ViewHolder8) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });

            //       适配数据布局--连号个数
            ((ViewHolder8) holder).tvLinhao0.setTag(suoShuiFilter.getType() + "," + 0);
            ((ViewHolder8) holder).tvLinhao1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder8) holder).tvLinhao2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder8) holder).tvLinhao3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder8) holder).tvLinhao4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder8) holder).tvLinhao5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder8) holder).tvLinhao6.setTag(suoShuiFilter.getType() + "," + 6);

            if(map.isEmpty()){
                ((ViewHolder8) holder).tvLinhao0.setSelected(false);
                ((ViewHolder8) holder).tvLinhao1.setSelected(false);
                ((ViewHolder8) holder).tvLinhao2.setSelected(false);
                ((ViewHolder8) holder).tvLinhao3.setSelected(false);
                ((ViewHolder8) holder).tvLinhao4.setSelected(false);
                ((ViewHolder8) holder).tvLinhao5.setSelected(false);
                ((ViewHolder8) holder).tvLinhao6.setSelected(false);
            }

            ((ViewHolder8) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(0, ((ViewHolder8) holder).tvLinhao0.getText().toString());
                    map.put(1, ((ViewHolder8) holder).tvLinhao1.getText().toString());
                    map.put(2, ((ViewHolder8) holder).tvLinhao2.getText().toString());
                    map.put(3, ((ViewHolder8) holder).tvLinhao3.getText().toString());
                    map.put(4, ((ViewHolder8) holder).tvLinhao4.getText().toString());
                    map.put(5, ((ViewHolder8) holder).tvLinhao5.getText().toString());
                    map.put(6, ((ViewHolder8) holder).tvLinhao6.getText().toString());
                    list.get(position).setMap(map);
                    ((ViewHolder8) holder).tvLinhao0.setSelected(true);
                    ((ViewHolder8) holder).tvLinhao1.setSelected(true);
                    ((ViewHolder8) holder).tvLinhao2.setSelected(true);
                    ((ViewHolder8) holder).tvLinhao3.setSelected(true);
                    ((ViewHolder8) holder).tvLinhao4.setSelected(true);
                    ((ViewHolder8) holder).tvLinhao5.setSelected(true);
                    ((ViewHolder8) holder).tvLinhao6.setSelected(true);
                }
            });
            ((ViewHolder8) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFanText(((ViewHolder8) holder).tvLinhao0, map, 0);
                    initFanText(((ViewHolder8) holder).tvLinhao1, map, 1);
                    initFanText(((ViewHolder8) holder).tvLinhao2, map, 2);
                    initFanText(((ViewHolder8) holder).tvLinhao3, map, 3);
                    initFanText(((ViewHolder8) holder).tvLinhao4, map, 4);
                    initFanText(((ViewHolder8) holder).tvLinhao5, map, 5);
                    initFanText(((ViewHolder8) holder).tvLinhao6, map, 6);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder8) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder8) holder).tvLinhao0.setSelected(false);
                    ((ViewHolder8) holder).tvLinhao1.setSelected(false);
                    ((ViewHolder8) holder).tvLinhao2.setSelected(false);
                    ((ViewHolder8) holder).tvLinhao3.setSelected(false);
                    ((ViewHolder8) holder).tvLinhao4.setSelected(false);
                    ((ViewHolder8) holder).tvLinhao5.setSelected(false);
                    ((ViewHolder8) holder).tvLinhao6.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder10) {
            //       适配数据布局--重号个数
            ((ViewHolder10) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            ((ViewHolder10) holder).tvChonghao0.setTag(suoShuiFilter.getType() + "," + 0);
            ((ViewHolder10) holder).tvChonghao1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder10) holder).tvChonghao2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder10) holder).tvChonghao3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder10) holder).tvChonghao4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder10) holder).tvChonghao5.setTag(suoShuiFilter.getType() + "," + 5);

            if(map.isEmpty()){
                ((ViewHolder10) holder).tvChonghao0.setSelected(false);
                ((ViewHolder10) holder).tvChonghao1.setSelected(false);
                ((ViewHolder10) holder).tvChonghao2.setSelected(false);
                ((ViewHolder10) holder).tvChonghao3.setSelected(false);
                ((ViewHolder10) holder).tvChonghao4.setSelected(false);
                ((ViewHolder10) holder).tvChonghao5.setSelected(false);
            }

            ((ViewHolder10) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(0, "0");
                    map.put(1, "1");
                    map.put(2, "2");
                    map.put(3, "3");
                    map.put(4, "4");
                    map.put(5, "5");
                    list.get(position).setMap(map);
                    ((ViewHolder10) holder).tvChonghao0.setSelected(true);
                    ((ViewHolder10) holder).tvChonghao1.setSelected(true);
                    ((ViewHolder10) holder).tvChonghao2.setSelected(true);
                    ((ViewHolder10) holder).tvChonghao3.setSelected(true);
                    ((ViewHolder10) holder).tvChonghao4.setSelected(true);
                    ((ViewHolder10) holder).tvChonghao5.setSelected(true);
                }
            });
            ((ViewHolder10) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFan(((ViewHolder10) holder).tvChonghao0, map, 0);
                    initFan(((ViewHolder10) holder).tvChonghao1, map, 1);
                    initFan(((ViewHolder10) holder).tvChonghao2, map, 2);
                    initFan(((ViewHolder10) holder).tvChonghao3, map, 3);
                    initFan(((ViewHolder10) holder).tvChonghao4, map, 4);
                    initFan(((ViewHolder10) holder).tvChonghao5, map, 5);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder10) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder10) holder).tvChonghao0.setSelected(false);
                    ((ViewHolder10) holder).tvChonghao1.setSelected(false);
                    ((ViewHolder10) holder).tvChonghao2.setSelected(false);
                    ((ViewHolder10) holder).tvChonghao3.setSelected(false);
                    ((ViewHolder10) holder).tvChonghao4.setSelected(false);
                    ((ViewHolder10) holder).tvChonghao5.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder11) {
            //       适配数据布局--平均值
            ((ViewHolder11) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            ((ViewHolder11) holder).tvPingjunzhi3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder11) holder).tvPingjunzhi4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder11) holder).tvPingjunzhi5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder11) holder).tvPingjunzhi6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder11) holder).tvPingjunzhi7.setTag(suoShuiFilter.getType() + "," + 7);
            ((ViewHolder11) holder).tvPingjunzhi8.setTag(suoShuiFilter.getType() + "," + 8);
            ((ViewHolder11) holder).tvPingjunzhi9.setTag(suoShuiFilter.getType() + "," + 9);

            if(map.isEmpty()){
                ((ViewHolder11) holder).tvPingjunzhi3.setSelected(false);
                ((ViewHolder11) holder).tvPingjunzhi4.setSelected(false);
                ((ViewHolder11) holder).tvPingjunzhi5.setSelected(false);
                ((ViewHolder11) holder).tvPingjunzhi6.setSelected(false);
                ((ViewHolder11) holder).tvPingjunzhi7.setSelected(false);
                ((ViewHolder11) holder).tvPingjunzhi8.setSelected(false);
                ((ViewHolder11) holder).tvPingjunzhi9.setSelected(false);
            }

            ((ViewHolder11) holder).tvJi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(3, "3");
                    map.put(5, "5");
                    map.put(7, "7");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(true);
                }
            });
            ((ViewHolder11) holder).tvOu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(4, "4");
                    map.put(6, "6");
                    map.put(8, "8");
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(false);
                }
            });
            ((ViewHolder11) holder).tv0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(3, "3");
                    map.put(6, "6");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(true);
                }
            });
            ((ViewHolder11) holder).tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(4, "4");
                    map.put(7, "7");
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(false);
                }
            });
            ((ViewHolder11) holder).tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(5, "5");
                    map.put(8, "8");
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(false);
                }
            });
            ((ViewHolder11) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(3, "3");
                    map.put(4, "4");
                    map.put(5, "5");
                    map.put(6, "6");
                    map.put(7, "7");
                    map.put(8, "8");
                    map.put(9, "9");
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(true);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(true);
                }
            });
            ((ViewHolder11) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFan(((ViewHolder11) holder).tvPingjunzhi3, map, 3);
                    initFan(((ViewHolder11) holder).tvPingjunzhi4, map, 4);
                    initFan(((ViewHolder11) holder).tvPingjunzhi5, map, 5);
                    initFan(((ViewHolder11) holder).tvPingjunzhi6, map, 6);
                    initFan(((ViewHolder11) holder).tvPingjunzhi7, map, 7);
                    initFan(((ViewHolder11) holder).tvPingjunzhi8, map, 8);
                    initFan(((ViewHolder11) holder).tvPingjunzhi9, map, 9);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder11) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder11) holder).tvPingjunzhi3.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi4.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi5.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi6.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi7.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi8.setSelected(false);
                    ((ViewHolder11) holder).tvPingjunzhi9.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder13) {
            //       适配数据布局--已出号过滤
            ((ViewHolder13) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            if(map.isEmpty()){
                ((ViewHolder13) holder).etSetQishu.setText("");
                ((ViewHolder13) holder).etSetCishu.setText("");
            }
            ((ViewHolder13) holder).etSetQishu.setTag(suoShuiFilter.getType() + "," + 0);
            ((ViewHolder13) holder).etSetCishu.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder13) holder).etSetQishu.addTextChangedListener(new CustomTextWatcher(((ViewHolder13) holder).etSetQishu, 0));
            ((ViewHolder13) holder).etSetCishu.addTextChangedListener(new CustomTextWatcher(((ViewHolder13) holder).etSetCishu, 1));

        } else if (holder instanceof ViewHolder14) {
            //       适配数据布局--连号
            ((ViewHolder14) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            ((ViewHolder14) holder).tvLinhao1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder14) holder).tvLinhao2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder14) holder).tvLinhao3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder14) holder).tvLinhao4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder14) holder).tvLinhao5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder14) holder).tvLinhao6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder14) holder).tvLinhao7.setTag(suoShuiFilter.getType() + "," + 7);

            if(map.isEmpty()){
                ((ViewHolder14) holder).tvLinhao1.setSelected(false);
                ((ViewHolder14) holder).tvLinhao2.setSelected(false);
                ((ViewHolder14) holder).tvLinhao3.setSelected(false);
                ((ViewHolder14) holder).tvLinhao4.setSelected(false);
                ((ViewHolder14) holder).tvLinhao5.setSelected(false);
                ((ViewHolder14) holder).tvLinhao6.setSelected(false);
                ((ViewHolder14) holder).tvLinhao7.setSelected(false);
            }

            ((ViewHolder14) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, ((ViewHolder14) holder).tvLinhao1.getText().toString());
                    map.put(2, ((ViewHolder14) holder).tvLinhao2.getText().toString());
                    map.put(3, ((ViewHolder14) holder).tvLinhao3.getText().toString());
                    map.put(4, ((ViewHolder14) holder).tvLinhao4.getText().toString());
                    map.put(5, ((ViewHolder14) holder).tvLinhao5.getText().toString());
                    map.put(6, ((ViewHolder14) holder).tvLinhao6.getText().toString());
                    map.put(7, ((ViewHolder14) holder).tvLinhao7.getText().toString());
                    list.get(position).setMap(map);
                    ((ViewHolder14) holder).tvLinhao1.setSelected(true);
                    ((ViewHolder14) holder).tvLinhao2.setSelected(true);
                    ((ViewHolder14) holder).tvLinhao3.setSelected(true);
                    ((ViewHolder14) holder).tvLinhao4.setSelected(true);
                    ((ViewHolder14) holder).tvLinhao5.setSelected(true);
                    ((ViewHolder14) holder).tvLinhao6.setSelected(true);
                    ((ViewHolder14) holder).tvLinhao7.setSelected(true);
                }
            });
            ((ViewHolder14) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFanText(((ViewHolder14) holder).tvLinhao1, map, 1);
                    initFanText(((ViewHolder14) holder).tvLinhao2, map, 2);
                    initFanText(((ViewHolder14) holder).tvLinhao3, map, 3);
                    initFanText(((ViewHolder14) holder).tvLinhao4, map, 4);
                    initFanText(((ViewHolder14) holder).tvLinhao5, map, 5);
                    initFanText(((ViewHolder14) holder).tvLinhao6, map, 6);
                    initFanText(((ViewHolder14) holder).tvLinhao7, map, 7);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder14) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder14) holder).tvLinhao1.setSelected(false);
                    ((ViewHolder14) holder).tvLinhao2.setSelected(false);
                    ((ViewHolder14) holder).tvLinhao3.setSelected(false);
                    ((ViewHolder14) holder).tvLinhao4.setSelected(false);
                    ((ViewHolder14) holder).tvLinhao5.setSelected(false);
                    ((ViewHolder14) holder).tvLinhao6.setSelected(false);
                    ((ViewHolder14) holder).tvLinhao7.setSelected(false);
                }
            });

        } else if (holder instanceof ViewHolder15) {
            ((ViewHolder15) holder).tvMc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogWiget.showSuoShuiJs(context, suoShuiFilter);
                }
            });
            //       适配数据布局--012路比
            ((ViewHolder15) holder).tvLubi1.setTag(suoShuiFilter.getType() + "," + 1);
            ((ViewHolder15) holder).tvLubi2.setTag(suoShuiFilter.getType() + "," + 2);
            ((ViewHolder15) holder).tvLubi3.setTag(suoShuiFilter.getType() + "," + 3);
            ((ViewHolder15) holder).tvLubi4.setTag(suoShuiFilter.getType() + "," + 4);
            ((ViewHolder15) holder).tvLubi5.setTag(suoShuiFilter.getType() + "," + 5);
            ((ViewHolder15) holder).tvLubi6.setTag(suoShuiFilter.getType() + "," + 6);
            ((ViewHolder15) holder).tvLubi7.setTag(suoShuiFilter.getType() + "," + 7);
            ((ViewHolder15) holder).tvLubi8.setTag(suoShuiFilter.getType() + "," + 8);
            ((ViewHolder15) holder).tvLubi9.setTag(suoShuiFilter.getType() + "," + 9);
            ((ViewHolder15) holder).tvLubi10.setTag(suoShuiFilter.getType() + "," + 10);
            ((ViewHolder15) holder).tvLubi11.setTag(suoShuiFilter.getType() + "," + 11);
            ((ViewHolder15) holder).tvLubi12.setTag(suoShuiFilter.getType() + "," + 12);
            ((ViewHolder15) holder).tvLubi13.setTag(suoShuiFilter.getType() + "," + 13);
            ((ViewHolder15) holder).tvLubi14.setTag(suoShuiFilter.getType() + "," + 14);
            ((ViewHolder15) holder).tvLubi15.setTag(suoShuiFilter.getType() + "," + 15);
            ((ViewHolder15) holder).tvLubi16.setTag(suoShuiFilter.getType() + "," + 16);

            if(map.isEmpty()){
                ((ViewHolder15) holder).tvLubi1.setSelected(false);
                ((ViewHolder15) holder).tvLubi2.setSelected(false);
                ((ViewHolder15) holder).tvLubi3.setSelected(false);
                ((ViewHolder15) holder).tvLubi4.setSelected(false);
                ((ViewHolder15) holder).tvLubi5.setSelected(false);
                ((ViewHolder15) holder).tvLubi6.setSelected(false);
                ((ViewHolder15) holder).tvLubi7.setSelected(false);
                ((ViewHolder15) holder).tvLubi8.setSelected(false);
                ((ViewHolder15) holder).tvLubi9.setSelected(false);
                ((ViewHolder15) holder).tvLubi10.setSelected(false);
                ((ViewHolder15) holder).tvLubi11.setSelected(false);
                ((ViewHolder15) holder).tvLubi12.setSelected(false);
                ((ViewHolder15) holder).tvLubi13.setSelected(false);
                ((ViewHolder15) holder).tvLubi14.setSelected(false);
                ((ViewHolder15) holder).tvLubi15.setSelected(false);
                ((ViewHolder15) holder).tvLubi16.setSelected(false);
            }

            ((ViewHolder15) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    map.put(1, ((ViewHolder15) holder).tvLubi1.getText().toString());
                    map.put(2, ((ViewHolder15) holder).tvLubi2.getText().toString());
                    map.put(3, ((ViewHolder15) holder).tvLubi3.getText().toString());
                    map.put(4, ((ViewHolder15) holder).tvLubi4.getText().toString());
                    map.put(5, ((ViewHolder15) holder).tvLubi5.getText().toString());
                    map.put(6, ((ViewHolder15) holder).tvLubi6.getText().toString());
                    map.put(7, ((ViewHolder15) holder).tvLubi7.getText().toString());
                    map.put(8, ((ViewHolder15) holder).tvLubi8.getText().toString());
                    map.put(9, ((ViewHolder15) holder).tvLubi9.getText().toString());
                    map.put(10, ((ViewHolder15) holder).tvLubi10.getText().toString());
                    map.put(11, ((ViewHolder15) holder).tvLubi11.getText().toString());
                    map.put(12, ((ViewHolder15) holder).tvLubi12.getText().toString());
                    map.put(13, ((ViewHolder15) holder).tvLubi13.getText().toString());
                    map.put(14, ((ViewHolder15) holder).tvLubi14.getText().toString());
                    map.put(15, ((ViewHolder15) holder).tvLubi15.getText().toString());
                    map.put(16, ((ViewHolder15) holder).tvLubi16.getText().toString());

                    list.get(position).setMap(map);
                    ((ViewHolder15) holder).tvLubi1.setSelected(true);
                    ((ViewHolder15) holder).tvLubi2.setSelected(true);
                    ((ViewHolder15) holder).tvLubi3.setSelected(true);
                    ((ViewHolder15) holder).tvLubi4.setSelected(true);
                    ((ViewHolder15) holder).tvLubi5.setSelected(true);
                    ((ViewHolder15) holder).tvLubi6.setSelected(true);
                    ((ViewHolder15) holder).tvLubi7.setSelected(true);
                    ((ViewHolder15) holder).tvLubi8.setSelected(true);
                    ((ViewHolder15) holder).tvLubi9.setSelected(true);
                    ((ViewHolder15) holder).tvLubi10.setSelected(true);
                    ((ViewHolder15) holder).tvLubi11.setSelected(true);
                    ((ViewHolder15) holder).tvLubi12.setSelected(true);
                    ((ViewHolder15) holder).tvLubi13.setSelected(true);
                    ((ViewHolder15) holder).tvLubi14.setSelected(true);
                    ((ViewHolder15) holder).tvLubi15.setSelected(true);
                    ((ViewHolder15) holder).tvLubi16.setSelected(true);
                }
            });
            ((ViewHolder15) holder).tvFan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initFanText(((ViewHolder15) holder).tvLubi1, map, 1);
                    initFanText(((ViewHolder15) holder).tvLubi2, map, 2);
                    initFanText(((ViewHolder15) holder).tvLubi3, map, 3);
                    initFanText(((ViewHolder15) holder).tvLubi4, map, 4);
                    initFanText(((ViewHolder15) holder).tvLubi5, map, 5);
                    initFanText(((ViewHolder15) holder).tvLubi6, map, 6);
                    initFanText(((ViewHolder15) holder).tvLubi7, map, 7);
                    initFanText(((ViewHolder15) holder).tvLubi8, map, 8);
                    initFanText(((ViewHolder15) holder).tvLubi9, map, 9);
                    initFanText(((ViewHolder15) holder).tvLubi10, map, 10);
                    initFanText(((ViewHolder15) holder).tvLubi11, map, 11);
                    initFanText(((ViewHolder15) holder).tvLubi12, map, 12);
                    initFanText(((ViewHolder15) holder).tvLubi13, map, 13);
                    initFanText(((ViewHolder15) holder).tvLubi14, map, 14);
                    initFanText(((ViewHolder15) holder).tvLubi15, map, 15);
                    initFanText(((ViewHolder15) holder).tvLubi16, map, 16);
                    list.get(position).setMap(map);
                }
            });
            ((ViewHolder15) holder).tvClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.clear();
                    list.get(position).setMap(map);
                    ((ViewHolder15) holder).tvLubi1.setSelected(false);
                    ((ViewHolder15) holder).tvLubi2.setSelected(false);
                    ((ViewHolder15) holder).tvLubi3.setSelected(false);
                    ((ViewHolder15) holder).tvLubi4.setSelected(false);
                    ((ViewHolder15) holder).tvLubi5.setSelected(false);
                    ((ViewHolder15) holder).tvLubi6.setSelected(false);
                    ((ViewHolder15) holder).tvLubi7.setSelected(false);
                    ((ViewHolder15) holder).tvLubi8.setSelected(false);
                    ((ViewHolder15) holder).tvLubi9.setSelected(false);
                    ((ViewHolder15) holder).tvLubi10.setSelected(false);
                    ((ViewHolder15) holder).tvLubi11.setSelected(false);
                    ((ViewHolder15) holder).tvLubi12.setSelected(false);
                    ((ViewHolder15) holder).tvLubi13.setSelected(false);
                    ((ViewHolder15) holder).tvLubi14.setSelected(false);
                    ((ViewHolder15) holder).tvLubi15.setSelected(false);
                    ((ViewHolder15) holder).tvLubi16.setSelected(false);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == VIEW_CONTENT0) {
            return VIEW_CONTENT0;
        }
        if (list.get(position).getType() == VIEW_CONTENT1) {
            return VIEW_CONTENT1;
        }
        if (list.get(position).getType() == VIEW_CONTENT2) {
            return VIEW_CONTENT2;
        }
        if (list.get(position).getType() == VIEW_CONTENT3) {
            return VIEW_CONTENT3;
        }
        if (list.get(position).getType() == VIEW_CONTENT4) {
            return VIEW_CONTENT4;
        }
        if (list.get(position).getType() == VIEW_CONTENT5) {
            return VIEW_CONTENT5;
        }
        if (list.get(position).getType() == VIEW_CONTENT6) {
            return VIEW_CONTENT6;
        }
        if (list.get(position).getType() == VIEW_CONTENT7) {
            return VIEW_CONTENT7;
        }
        if (list.get(position).getType() == VIEW_CONTENT8) {
            return VIEW_CONTENT8;
        }
        if (list.get(position).getType() == VIEW_CONTENT9) {
            return VIEW_CONTENT9;
        }
        if (list.get(position).getType() == VIEW_CONTENT10) {
            return VIEW_CONTENT10;
        }
        if (list.get(position).getType() == VIEW_CONTENT11) {
            return VIEW_CONTENT11;
        }
        if (list.get(position).getType() == VIEW_CONTENT12) {
            return VIEW_CONTENT12;
        }
        if (list.get(position).getType() == VIEW_CONTENT13) {
            return VIEW_CONTENT13;
        }
        if (list.get(position).getType() == VIEW_CONTENT14) {
            return VIEW_CONTENT14;
        }
        if (list.get(position).getType() == VIEW_CONTENT15) {
            return VIEW_CONTENT15;
        }
        if (list.get(position).getType() == VIEW_CONTENT16) {
            return VIEW_CONTENT16;
        }
        if (list.get(position).getType() == VIEW_CONTENT17) {
            return VIEW_CONTENT17;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BseeHolder extends RecyclerView.ViewHolder {
        public BseeHolder(View itemView) {
            super(itemView);
        }
    }

    private void initFan(TextView textView, Map<Integer, String> map, int value){
        if(textView == null){
            return;
        }
        if (map.get(value) == null || TextUtils.isEmpty(map.get(value))) {
            map.put(value, String.valueOf(value));
            textView.setSelected(true);
        } else {
            map.put(value, "");
            textView.setSelected(false);
        }
    }

    private void initFanText(TextView textView, Map<Integer, String> map, int value){
        if(textView == null){
            return;
        }
        if (map.get(value) == null || TextUtils.isEmpty(map.get(value))) {
            map.put(value, textView.getText().toString());
            textView.setSelected(true);
        } else {
            map.put(value, "");
            textView.setSelected(false);
        }
    }

    class ViewHolder0 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_jchm_1)
        TextView tvJchm1;
        @Bind(R.id.tv_jchm_2)
        TextView tvJchm2;
        @Bind(R.id.tv_jchm_3)
        TextView tvJchm3;
        @Bind(R.id.tv_jchm_4)
        TextView tvJchm4;
        @Bind(R.id.tv_jchm_5)
        TextView tvJchm5;
        @Bind(R.id.tv_jchm_6)
        TextView tvJchm6;
        @Bind(R.id.tv_jchm_7)
        TextView tvJchm7;
        @Bind(R.id.tv_jchm_8)
        TextView tvJchm8;
        @Bind(R.id.tv_jchm_9)
        TextView tvJchm9;
        @Bind(R.id.tv_jchm_10)
        TextView tvJchm10;
        @Bind(R.id.tv_jchm_11)
        TextView tvJchm11;
        @Bind(R.id.tv_da)
        TextView tvDa;
        @Bind(R.id.tv_xiao)
        TextView tvXiao;
        @Bind(R.id.tv_ji)
        TextView tvJi;
        @Bind(R.id.tv_ou)
        TextView tvOu;
        @Bind(R.id.tv_0)
        TextView tv0;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder0(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.tvJchm1.setOnClickListener(customOnClickListener);
            this.tvJchm2.setOnClickListener(customOnClickListener);
            this.tvJchm3.setOnClickListener(customOnClickListener);
            this.tvJchm4.setOnClickListener(customOnClickListener);
            this.tvJchm5.setOnClickListener(customOnClickListener);
            this.tvJchm6.setOnClickListener(customOnClickListener);
            this.tvJchm7.setOnClickListener(customOnClickListener);
            this.tvJchm8.setOnClickListener(customOnClickListener);
            this.tvJchm9.setOnClickListener(customOnClickListener);
            this.tvJchm10.setOnClickListener(customOnClickListener);
            this.tvJchm11.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder1 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvDanzuMc;
        @Bind(R.id.tv_danzu1)
        TextView tvDanzu1;
        @Bind(R.id.tv_danzu2)
        TextView tvDanzu2;
        @Bind(R.id.tv_danzu3)
        TextView tvDanzu3;
        @Bind(R.id.tv_danzu4)
        TextView tvDanzu4;
        @Bind(R.id.tv_danzu5)
        TextView tvDanzu5;
        @Bind(R.id.tv_danzu6)
        TextView tvDanzu6;
        @Bind(R.id.tv_danzu7)
        TextView tvDanzu7;
        @Bind(R.id.tv_danzu8)
        TextView tvDanzu8;
        @Bind(R.id.tv_danzu9)
        TextView tvDanzu9;
        @Bind(R.id.tv_danzu10)
        TextView tvDanzu10;
        @Bind(R.id.tv_danzu11)
        TextView tvDanzu11;
        @Bind(R.id.cb_chu0)
        CheckBox cbChu0;
        @Bind(R.id.cb_chu1)
        CheckBox cbChu1;
        @Bind(R.id.cb_chu2)
        CheckBox cbChu2;
        @Bind(R.id.cb_chu3)
        CheckBox cbChu3;
        @Bind(R.id.cb_chu4)
        CheckBox cbChu4;
        @Bind(R.id.cb_chu5)
        CheckBox cbChu5;
        @Bind(R.id.tv_da)
        TextView tvDa;
        @Bind(R.id.tv_xiao)
        TextView tvXiao;
        @Bind(R.id.tv_ji)
        TextView tvJi;
        @Bind(R.id.tv_ou)
        TextView tvOu;
        @Bind(R.id.tv_0)
        TextView tv0;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder1(View itemView, CustomOnClickListener customOnClickListener, CheckedChangeListener checkedChangeListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.tvDanzu1.setOnClickListener(customOnClickListener);
            this.tvDanzu2.setOnClickListener(customOnClickListener);
            this.tvDanzu3.setOnClickListener(customOnClickListener);
            this.tvDanzu4.setOnClickListener(customOnClickListener);
            this.tvDanzu5.setOnClickListener(customOnClickListener);
            this.tvDanzu6.setOnClickListener(customOnClickListener);
            this.tvDanzu7.setOnClickListener(customOnClickListener);
            this.tvDanzu8.setOnClickListener(customOnClickListener);
            this.tvDanzu9.setOnClickListener(customOnClickListener);
            this.tvDanzu10.setOnClickListener(customOnClickListener);
            this.tvDanzu11.setOnClickListener(customOnClickListener);
            this.cbChu0.setOnCheckedChangeListener(checkedChangeListener);
            this.cbChu1.setOnCheckedChangeListener(checkedChangeListener);
            this.cbChu2.setOnCheckedChangeListener(checkedChangeListener);
            this.cbChu3.setOnCheckedChangeListener(checkedChangeListener);
            this.cbChu4.setOnCheckedChangeListener(checkedChangeListener);
            this.cbChu5.setOnCheckedChangeListener(checkedChangeListener);
        }
    }

    class ViewHolder4 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_hezhi_15)
        TextView tvHezhi15;
        @Bind(R.id.tv_hezhi_16)
        TextView tvHezhi16;
        @Bind(R.id.tv_hezhi_17)
        TextView tvHezhi17;
        @Bind(R.id.tv_hezhi_18)
        TextView tvHezhi18;
        @Bind(R.id.tv_hezhi_19)
        TextView tvHezhi19;
        @Bind(R.id.tv_hezhi_20)
        TextView tvHezhi20;
        @Bind(R.id.tv_hezhi_21)
        TextView tvHezhi21;
        @Bind(R.id.tv_hezhi_22)
        TextView tvHezhi22;
        @Bind(R.id.tv_hezhi_23)
        TextView tvHezhi23;
        @Bind(R.id.tv_hezhi_24)
        TextView tvHezhi24;
        @Bind(R.id.tv_hezhi_25)
        TextView tvHezhi25;
        @Bind(R.id.tv_hezhi_26)
        TextView tvHezhi26;
        @Bind(R.id.tv_hezhi_27)
        TextView tvHezhi27;
        @Bind(R.id.tv_hezhi_28)
        TextView tvHezhi28;
        @Bind(R.id.tv_hezhi_29)
        TextView tvHezhi29;
        @Bind(R.id.tv_hezhi_30)
        TextView tvHezhi30;
        @Bind(R.id.tv_hezhi_31)
        TextView tvHezhi31;
        @Bind(R.id.tv_hezhi_32)
        TextView tvHezhi32;
        @Bind(R.id.tv_hezhi_33)
        TextView tvHezhi33;
        @Bind(R.id.tv_hezhi_34)
        TextView tvHezhi34;
        @Bind(R.id.tv_hezhi_35)
        TextView tvHezhi35;
        @Bind(R.id.tv_hezhi_36)
        TextView tvHezhi36;
        @Bind(R.id.tv_hezhi_37)
        TextView tvHezhi37;
        @Bind(R.id.tv_hezhi_38)
        TextView tvHezhi38;
        @Bind(R.id.tv_hezhi_39)
        TextView tvHezhi39;
        @Bind(R.id.tv_hezhi_40)
        TextView tvHezhi40;
        @Bind(R.id.tv_hezhi_41)
        TextView tvHezhi41;
        @Bind(R.id.tv_hezhi_42)
        TextView tvHezhi42;
        @Bind(R.id.tv_hezhi_43)
        TextView tvHezhi43;
        @Bind(R.id.tv_hezhi_44)
        TextView tvHezhi44;
        @Bind(R.id.tv_hezhi_45)
        TextView tvHezhi45;
        @Bind(R.id.tv_ji)
        TextView tvJi;
        @Bind(R.id.tv_ou)
        TextView tvOu;
        @Bind(R.id.tv_0)
        TextView tv0;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder4(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvHezhi15.setOnClickListener(customOnClickListener);
            tvHezhi16.setOnClickListener(customOnClickListener);
            tvHezhi17.setOnClickListener(customOnClickListener);
            tvHezhi18.setOnClickListener(customOnClickListener);
            tvHezhi19.setOnClickListener(customOnClickListener);
            tvHezhi20.setOnClickListener(customOnClickListener);
            tvHezhi21.setOnClickListener(customOnClickListener);
            tvHezhi22.setOnClickListener(customOnClickListener);
            tvHezhi23.setOnClickListener(customOnClickListener);
            tvHezhi24.setOnClickListener(customOnClickListener);
            tvHezhi25.setOnClickListener(customOnClickListener);
            tvHezhi26.setOnClickListener(customOnClickListener);
            tvHezhi27.setOnClickListener(customOnClickListener);
            tvHezhi28.setOnClickListener(customOnClickListener);
            tvHezhi29.setOnClickListener(customOnClickListener);
            tvHezhi30.setOnClickListener(customOnClickListener);
            tvHezhi31.setOnClickListener(customOnClickListener);
            tvHezhi32.setOnClickListener(customOnClickListener);
            tvHezhi33.setOnClickListener(customOnClickListener);
            tvHezhi34.setOnClickListener(customOnClickListener);
            tvHezhi35.setOnClickListener(customOnClickListener);
            tvHezhi36.setOnClickListener(customOnClickListener);
            tvHezhi37.setOnClickListener(customOnClickListener);
            tvHezhi38.setOnClickListener(customOnClickListener);
            tvHezhi39.setOnClickListener(customOnClickListener);
            tvHezhi40.setOnClickListener(customOnClickListener);
            tvHezhi41.setOnClickListener(customOnClickListener);
            tvHezhi42.setOnClickListener(customOnClickListener);
            tvHezhi43.setOnClickListener(customOnClickListener);
            tvHezhi44.setOnClickListener(customOnClickListener);
            tvHezhi45.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder5 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_hewei_0)
        TextView tvHewei0;
        @Bind(R.id.tv_hewei_1)
        TextView tvHewei1;
        @Bind(R.id.tv_hewei_2)
        TextView tvHewei2;
        @Bind(R.id.tv_hewei_3)
        TextView tvHewei3;
        @Bind(R.id.tv_hewei_4)
        TextView tvHewei4;
        @Bind(R.id.tv_hewei_5)
        TextView tvHewei5;
        @Bind(R.id.tv_hewei_6)
        TextView tvHewei6;
        @Bind(R.id.tv_hewei_7)
        TextView tvHewei7;
        @Bind(R.id.tv_hewei_8)
        TextView tvHewei8;
        @Bind(R.id.tv_hewei_9)
        TextView tvHewei9;
        @Bind(R.id.tv_ji)
        TextView tvJi;
        @Bind(R.id.tv_ou)
        TextView tvOu;
        @Bind(R.id.tv_0)
        TextView tv0;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder5(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvHewei0.setOnClickListener(customOnClickListener);
            tvHewei1.setOnClickListener(customOnClickListener);
            tvHewei2.setOnClickListener(customOnClickListener);
            tvHewei3.setOnClickListener(customOnClickListener);
            tvHewei4.setOnClickListener(customOnClickListener);
            tvHewei5.setOnClickListener(customOnClickListener);
            tvHewei6.setOnClickListener(customOnClickListener);
            tvHewei7.setOnClickListener(customOnClickListener);
            tvHewei8.setOnClickListener(customOnClickListener);
            tvHewei9.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder6 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_kuadu_4)
        TextView tvKuadu4;
        @Bind(R.id.tv_kuadu_5)
        TextView tvKuadu5;
        @Bind(R.id.tv_kuadu_6)
        TextView tvKuadu6;
        @Bind(R.id.tv_kuadu_7)
        TextView tvKuadu7;
        @Bind(R.id.tv_kuadu_8)
        TextView tvKuadu8;
        @Bind(R.id.tv_kuadu_9)
        TextView tvKuadu9;
        @Bind(R.id.tv_kuadu_10)
        TextView tvKuadu10;
        @Bind(R.id.tv_da)
        TextView tvDa;
        @Bind(R.id.tv_xiao)
        TextView tvXiao;
        @Bind(R.id.tv_ji)
        TextView tvJi;
        @Bind(R.id.tv_ou)
        TextView tvOu;
        @Bind(R.id.tv_0)
        TextView tv0;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder6(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvKuadu4.setOnClickListener(customOnClickListener);
            tvKuadu5.setOnClickListener(customOnClickListener);
            tvKuadu6.setOnClickListener(customOnClickListener);
            tvKuadu7.setOnClickListener(customOnClickListener);
            tvKuadu8.setOnClickListener(customOnClickListener);
            tvKuadu9.setOnClickListener(customOnClickListener);
            tvKuadu10.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder7 extends BseeHolder {

        @Bind(R.id.tv_mc)
        TextView tvBizhiMc;
        @Bind(R.id.tv_bizhi_1)
        TextView tvBizhi1;
        @Bind(R.id.tv_bizhi_2)
        TextView tvBizhi2;
        @Bind(R.id.tv_bizhi_3)
        TextView tvBizhi3;
        @Bind(R.id.tv_bizhi_4)
        TextView tvBizhi4;
        @Bind(R.id.tv_bizhi_5)
        TextView tvBizhi5;
        @Bind(R.id.tv_bizhi_6)
        TextView tvBizhi6;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder7(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvBizhi1.setOnClickListener(customOnClickListener);
            tvBizhi2.setOnClickListener(customOnClickListener);
            tvBizhi3.setOnClickListener(customOnClickListener);
            tvBizhi4.setOnClickListener(customOnClickListener);
            tvBizhi5.setOnClickListener(customOnClickListener);
            tvBizhi6.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder8 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_linhao_0)
        TextView tvLinhao0;
        @Bind(R.id.tv_linhao_1)
        TextView tvLinhao1;
        @Bind(R.id.tv_linhao_2)
        TextView tvLinhao2;
        @Bind(R.id.tv_linhao_3)
        TextView tvLinhao3;
        @Bind(R.id.tv_linhao_4)
        TextView tvLinhao4;
        @Bind(R.id.tv_linhao_5)
        TextView tvLinhao5;
        @Bind(R.id.tv_linhao_6)
        TextView tvLinhao6;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder8(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvLinhao0.setOnClickListener(customOnClickListener);
            tvLinhao1.setOnClickListener(customOnClickListener);
            tvLinhao2.setOnClickListener(customOnClickListener);
            tvLinhao3.setOnClickListener(customOnClickListener);
            tvLinhao4.setOnClickListener(customOnClickListener);
            tvLinhao5.setOnClickListener(customOnClickListener);
            tvLinhao6.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder10 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_chonghao_0)
        TextView tvChonghao0;
        @Bind(R.id.tv_chonghao_1)
        TextView tvChonghao1;
        @Bind(R.id.tv_chonghao_2)
        TextView tvChonghao2;
        @Bind(R.id.tv_chonghao_3)
        TextView tvChonghao3;
        @Bind(R.id.tv_chonghao_4)
        TextView tvChonghao4;
        @Bind(R.id.tv_chonghao_5)
        TextView tvChonghao5;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder10(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvChonghao0.setOnClickListener(customOnClickListener);
            tvChonghao1.setOnClickListener(customOnClickListener);
            tvChonghao2.setOnClickListener(customOnClickListener);
            tvChonghao3.setOnClickListener(customOnClickListener);
            tvChonghao4.setOnClickListener(customOnClickListener);
            tvChonghao5.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder11 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_pingjunzhi_3)
        TextView tvPingjunzhi3;
        @Bind(R.id.tv_pingjunzhi_4)
        TextView tvPingjunzhi4;
        @Bind(R.id.tv_pingjunzhi_5)
        TextView tvPingjunzhi5;
        @Bind(R.id.tv_pingjunzhi_6)
        TextView tvPingjunzhi6;
        @Bind(R.id.tv_pingjunzhi_7)
        TextView tvPingjunzhi7;
        @Bind(R.id.tv_pingjunzhi_8)
        TextView tvPingjunzhi8;
        @Bind(R.id.tv_pingjunzhi_9)
        TextView tvPingjunzhi9;
        @Bind(R.id.tv_ji)
        TextView tvJi;
        @Bind(R.id.tv_ou)
        TextView tvOu;
        @Bind(R.id.tv_0)
        TextView tv0;
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder11(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvPingjunzhi3.setOnClickListener(customOnClickListener);
            tvPingjunzhi4.setOnClickListener(customOnClickListener);
            tvPingjunzhi5.setOnClickListener(customOnClickListener);
            tvPingjunzhi6.setOnClickListener(customOnClickListener);
            tvPingjunzhi7.setOnClickListener(customOnClickListener);
            tvPingjunzhi8.setOnClickListener(customOnClickListener);
            tvPingjunzhi9.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder13 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.et_set_qishu)
        EditText etSetQishu;
        @Bind(R.id.et_set_cishu)
        EditText etSetCishu;

        public ViewHolder13(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder14 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_linhao_1)
        TextView tvLinhao1;
        @Bind(R.id.tv_linhao_2)
        TextView tvLinhao2;
        @Bind(R.id.tv_linhao_3)
        TextView tvLinhao3;
        @Bind(R.id.tv_linhao_4)
        TextView tvLinhao4;
        @Bind(R.id.tv_linhao_5)
        TextView tvLinhao5;
        @Bind(R.id.tv_linhao_6)
        TextView tvLinhao6;
        @Bind(R.id.tv_linhao_7)
        TextView tvLinhao7;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder14(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvLinhao1.setOnClickListener(customOnClickListener);
            tvLinhao2.setOnClickListener(customOnClickListener);
            tvLinhao3.setOnClickListener(customOnClickListener);
            tvLinhao4.setOnClickListener(customOnClickListener);
            tvLinhao5.setOnClickListener(customOnClickListener);
            tvLinhao6.setOnClickListener(customOnClickListener);
            tvLinhao7.setOnClickListener(customOnClickListener);
        }
    }

    class ViewHolder15 extends BseeHolder {
        @Bind(R.id.tv_mc)
        TextView tvMc;
        @Bind(R.id.tv_lubi_1)
        TextView tvLubi1;
        @Bind(R.id.tv_lubi_2)
        TextView tvLubi2;
        @Bind(R.id.tv_lubi_3)
        TextView tvLubi3;
        @Bind(R.id.tv_lubi_4)
        TextView tvLubi4;
        @Bind(R.id.tv_lubi_5)
        TextView tvLubi5;
        @Bind(R.id.tv_lubi_6)
        TextView tvLubi6;
        @Bind(R.id.tv_lubi_7)
        TextView tvLubi7;
        @Bind(R.id.tv_lubi_8)
        TextView tvLubi8;
        @Bind(R.id.tv_lubi_9)
        TextView tvLubi9;
        @Bind(R.id.tv_lubi_10)
        TextView tvLubi10;
        @Bind(R.id.tv_lubi_11)
        TextView tvLubi11;
        @Bind(R.id.tv_lubi_12)
        TextView tvLubi12;
        @Bind(R.id.tv_lubi_13)
        TextView tvLubi13;
        @Bind(R.id.tv_lubi_14)
        TextView tvLubi14;
        @Bind(R.id.tv_lubi_15)
        TextView tvLubi15;
        @Bind(R.id.tv_lubi_16)
        TextView tvLubi16;
        @Bind(R.id.tv_all)
        TextView tvAll;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_clean)
        TextView tvClean;

        public ViewHolder15(View itemView, CustomOnClickListener customOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvLubi1.setOnClickListener(customOnClickListener);
            tvLubi2.setOnClickListener(customOnClickListener);
            tvLubi3.setOnClickListener(customOnClickListener);
            tvLubi4.setOnClickListener(customOnClickListener);
            tvLubi5.setOnClickListener(customOnClickListener);
            tvLubi6.setOnClickListener(customOnClickListener);
            tvLubi7.setOnClickListener(customOnClickListener);
            tvLubi8.setOnClickListener(customOnClickListener);
            tvLubi9.setOnClickListener(customOnClickListener);
            tvLubi10.setOnClickListener(customOnClickListener);
            tvLubi11.setOnClickListener(customOnClickListener);
            tvLubi12.setOnClickListener(customOnClickListener);
            tvLubi13.setOnClickListener(customOnClickListener);
            tvLubi14.setOnClickListener(customOnClickListener);
            tvLubi15.setOnClickListener(customOnClickListener);
            tvLubi16.setOnClickListener(customOnClickListener);
        }
    }

    private class CustomOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String tag = String.valueOf(v.getTag());
            if (tag == null || tag == "null" || TextUtils.isEmpty(tag)) {
                return;
            }
            String[] str = tag.split(",");
            if (!TextUtils.isEmpty(str[0]) && !TextUtils.isEmpty(str[1])) {
                Map<Integer, String> map = list.get(Integer.parseInt(str[0])).getMap();
                boolean isSelected = v.isSelected();
                if(isSelected){
                    map.put(Integer.parseInt(str[1]), "");
                }else{
                    map.put(Integer.parseInt(str[1]), ((TextView) v).getText().toString());
                }
                v.setSelected(!isSelected);
                list.get(Integer.parseInt(str[0])).setMap(map);
            }
        }
    }

    private class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String tag = String.valueOf(buttonView.getTag());
            if(tag == null || tag == "null" || TextUtils.isEmpty(tag)){
                return;
            }
            String[] str = tag.split(",");
            if(!TextUtils.isEmpty(str[0]) && !TextUtils.isEmpty(str[1])){
                Map<Integer, String> map = list.get(Integer.parseInt(str[0])).getMap();
                if(isChecked){
                    map.put(Integer.parseInt(str[1]), String.valueOf(Integer.parseInt(str[1])-12));
                }else{
                    map.put(Integer.parseInt(str[1]), "");
                }
                list.get(Integer.parseInt(str[0])).setMap(map);
            }
        }
    }

    private class CustomTextWatcher implements TextWatcher {
        private int position1, position2, type;
        private EditText editText;
        public CustomTextWatcher(EditText editText, int type){
            this.editText = editText;
            this.type = type;
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString().trim();
            if (type == 1 && text.equals("0")) {
                editText.setText("");
                return;
            }
            if(editText != null){
                String tag = String.valueOf(editText.getTag());
                if(tag == null || tag == "null" || TextUtils.isEmpty(tag)){
                    return;
                }
                String[] str = tag.split(",");
                if(!TextUtils.isEmpty(str[0]) && !TextUtils.isEmpty(str[1])){
                    this.position1 = Integer.parseInt(str[0]);
                    this.position2 = Integer.parseInt(str[1]);
                    Map<Integer, String> map = list.get(Integer.parseInt(str[0])).getMap();
                    map.put(Integer.parseInt(str[1]), editable.toString());
                    list.get(Integer.parseInt(str[0])).setMap(map);
                }
            }
        }
    }


}

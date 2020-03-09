package com.jingcai.jc_11x5.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.db.dao.KaiJiang_11x5Dao;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import com.jingcai.jc_11x5.entity.Lottery;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.DateUtil;
import com.jingcai.jc_11x5.util.LogUtil;
import com.jingcai.jc_11x5.util.PromptUtil;
import com.jingcai.jc_11x5.view.adapter.LvZsAdapter;
import com.jingcai.jc_11x5.view.adapter.LvZsYxAdapter;
import com.jingcai.jc_11x5.view.adapter.PoweredAdapter;
import com.jingcai.jc_11x5.view.adapter.ViewPagerAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KjzsFragment2 extends BaseFragment implements AbsListView.OnScrollListener {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp_pages)
    ViewPager vpPages;
    @Bind(R.id.tv_kjmc)
    TextView tvKjmc;
    @Bind(R.id.tv_kjsj)
    TextView tvKjsj;
    @Bind(R.id.iv_header_right)
    ImageView ivHeaderRight;
    @Bind(R.id.lv_yuliu)
    ListView lvYuliu;

    private App app;
    private static final String[] titles = {"走势"};
    private TextView[] tvNavi;
    private int[] pagerIndex;//单页页码
    private int currentIndex;//当TAB的index
    private boolean[] pageIsLoad = new boolean[]{false};//内容页加载状态 true 为已加载
    private Handler mHandle;
    private List<KaiJiang_11x5> kjLists;
    private LvZsYxAdapter lvZsAdapter;//预选adapter

    private Lottery lottery;

    public KjzsFragment2() {
    }

    public static KjzsFragment2 newInstance() {
        KjzsFragment2 fragment = new KjzsFragment2();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kjzs2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private long ksTime;
    private long jsTime;
    private long dt = 0;
    private KaiJiang_11x5Dao kaiJiang_11x5Dao;

    @Override
    public void initData() {
        app = App.getInstance();
        kaiJiang_11x5Dao = new KaiJiang_11x5Dao();
        lottery = app.getLottery();
        ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
        jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间
        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    @Override
    public void initView() {
        tvTitle.setText(getResources().getString(R.string.text_jcrj));
        ivHeaderRight.setImageResource(R.mipmap.icon_refresh);
        ivHeaderRight.setVisibility(View.VISIBLE);
        lookForNavBar();
        mkDiabledItem(0);
        createContentForViewPager();
        setListeners();
        vpPages.setCurrentItem(currentIndex, true);
        //lvZsAdapter = new LvZsAdapter(mContext, kaiJiang_11x5Dao.queryKaijiangList1("yuxuan"), lvYuliu);
        lvZsAdapter = new LvZsYxAdapter(mContext, new ArrayList<KaiJiang_11x5>(), lvYuliu);
        lvYuliu.setAdapter(lvZsAdapter);
    }

    @Override
    public void requestData() {
        ProgressWidget.showProgressDialog(getActivity(), "正在加载中...");
        Jc11x5Factory.getInstance().getLuckyNumberList(mHandle, app.getUser().getCaizhongMc());
    }

    private void lookForNavBar() {
        LinearLayout headLayout = (LinearLayout) getActivity().findViewById(R.id.layout_title_navi);
        if (titles.length <= 1) {
            //实际上 功能还是有的
            headLayout.setVisibility(View.GONE);
        }
        tvNavi = new TextView[titles.length];
        pagerIndex = new int[titles.length];
        for (int i = 0; i < headLayout.getChildCount(); i++) {
            TextView tv = (TextView) headLayout.getChildAt(i);
            if (i > titles.length - 1) {
                tv.setVisibility(View.GONE);
            } else {
                tv.setTag(i);
                tvNavi[i] = tv;
                tvNavi[i].setText(titles[i]);
                tvNavi[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tag = (Integer) v.getTag();
                        currentIndex = tag;
                        vpPages.setCurrentItem(currentIndex, true);
                    }
                });
                pagerIndex[i] = 1;
            }
        }
    }

    private void mkDiabledItem(int j) {
        for (int i = 0; i < tvNavi.length; i++) {
            if (i == j) {
                tvNavi[i].setTextColor(getResources().getColor(R.color.title_color));
                tvNavi[i].setEnabled(false);
            } else {
                tvNavi[i].setTextColor(getResources().getColor(R.color.black));
                tvNavi[i].setEnabled(true);
            }
        }
    }

    private ListView[] listViews;
    private PoweredAdapter[] adapters;//重写

    private void createContentForViewPager() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        List<View> viewList = new ArrayList<View>();
        for (int i = 0; i < titles.length; i++) {
            View contenter = inflater.inflate(R.layout.viewpager_content2, null);
            viewList.add(contenter);
        }
        PagerAdapter pagerAdapter = new ViewPagerAdapter(viewList);
        vpPages.setAdapter(pagerAdapter);
        listViews = new ListView[viewList.size()];
        adapters = new PoweredAdapter[viewList.size()];
        for (int i = 0; i < viewList.size(); i++) {
            View v = viewList.get(i);
            listViews[i] = (ListView) v.findViewById(R.id.lv_content);
            listViews[i].setDivider(getResources().getDrawable(R.mipmap.divider));
            listViews[i].setTag(i);
            listViews[i].setOnScrollListener(this);

            LinearLayout ll = (LinearLayout) v.findViewById(R.id.ll_title);
            View view = inflater.inflate(R.layout.title_content2, null);
            ll.addView(view);
            adapters[i] = new LvZsAdapter(mContext, new ArrayList<KaiJiang_11x5>(), listViews[i]);
            listViews[i].setAdapter(adapters[i]);
        }
    }

    private void setListeners() {
        vpPages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int index) {
                currentIndex = index;
                mkDiabledItem(index);
                if (!pageIsLoad[index]) {
                    pageIsLoad[index] = !pageIsLoad[index];
                    adapters[currentIndex].setList(kjLists);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private boolean isRunXunjian;
    private int nextOrderNo;

    private void handlerMsg(Message msg) {
        try {
            switch (msg.what) {
                case HandlerWhat.GET_KAIJIANG_SUCCESS:
                    Bundle ylBundle = msg.getData();
                    if (ylBundle != null) {
                        kjLists = (List<KaiJiang_11x5>) ylBundle.getSerializable(ReturnStatus.KEY_GET_KAIJIANG_LIST);
                        if (kjLists != null && kjLists.size() > 0) {
                            if (isRunXunjian) {
                                List<KaiJiang_11x5> lists = adapters[currentIndex].getList();
                                if(lists == null || lists.size() < 1 ){
                                    adapters[currentIndex].setList(kjLists);
                                    listViews[currentIndex].setSelection(kjLists.size() - 1);
                                    break;
                                }
                                if (lists.get(lists.size()-1).getOrderNum().equals(kjLists.get(kjLists.size()-1).getOrderNum())) {
                                    mHandle.sendEmptyMessageDelayed(11, 10000);
                                    ProgressWidget.dismissProgressDialog();
                                    break;
                                } else {
                                    isRunXunjian = false;
                                    adapters[currentIndex].setList(kjLists);
                                    listViews[currentIndex].setSelection(kjLists.size() - 1);
                                    if (tvKjmc != null) {
                                        KaiJiang_11x5 newKj = kjLists.get(kjLists.size() - 1);
                                        tvKjmc.setText(CaiUtil.getCaiMcShort(app.getUser().getCaizhong()) + newKj.getLuckyNumber());
                                        lottery.setCaiNumber(newKj.getLuckyNumber());
                                        app.setLottery(lottery);
                                    }
                                    if (!isHidden) {
                                        PromptUtil.startAlarm(mContext);
                                    }
                                }
                            } else {
                                //正常加载
                                for (int i = 0; i < pageIsLoad.length; i++) {
                                    pageIsLoad[i] = false;
                                }
                                adapters[currentIndex].setList(kjLists);
                                listViews[currentIndex].setSelection(kjLists.size() - 1);
                                if (tvKjmc != null) {
                                    tvKjmc.setText(CaiUtil.getCaiMcShort(app.getUser().getCaizhong()) + kjLists.get(kjLists.size() - 1).getLuckyNumber());
                                }
                                getDjs();
                                mHandle.sendEmptyMessageDelayed(10, 1000);
                                lvZsAdapter.setList(kaiJiang_11x5Dao.queryKaijiangList1("yuxuan"));
                            }
                            String order = kjLists.get(kjLists.size() - 1).getOrderNum();
                            if (order.substring(order.length() - 2).equals(lottery.getCount())) {
                                nextOrderNo = Integer.parseInt(CaiUtil.getNextPeriod());
                            } else {
                                nextOrderNo = Integer.parseInt(kjLists.get(kjLists.size() - 1).getOrderNum()) + 1;
                            }
                        }
                    }
                    ProgressWidget.dismissProgressDialog();
                    break;
                case 10:
                    dt = dt - 1000;
                    if (dt < 0) {
                        dt = 0;
                    }
                    //时间格式化
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                    String hms = formatter.format(dt);
                    tvKjsj.setText("距" + nextOrderNo + "期开奖还有" + hms);
                    mHandle.removeMessages(10);
                    //发送消息，不断减时间
                    mHandle.sendEmptyMessageDelayed(10, 1000);
                    if (dt <= 500) {
                        getDjs();
                        isRunXunjian = true;
                        Jc11x5Factory.getInstance().getLuckyNumberList(mHandle, app.getUser().getCaizhong());
                    }
                    break;
                case 11:
                    Jc11x5Factory.getInstance().getLuckyNumberList(mHandle, app.getUser().getCaizhong());
                    break;
                default:
                    if (msg.obj != null) {
                        showMsg(String.valueOf(msg.obj));
                    }
                    ProgressWidget.dismissProgressDialog();
                    break;
            }
        } catch (Exception e) {
            LogUtil.debug("KjzsFragment-->handlerMsg", e.getMessage());
        }finally {
            ProgressWidget.dismissProgressDialog();
        }
    }

    private void getDjs() {
        long ctime = DateUtil.getNowMills();//当前时间
        int orderNo = 0;
        if (ctime > jsTime) {
            tvKjsj.setText("今天开奖已经结束！");
            mHandle.removeCallbacksAndMessages(null);
            return;
        }
        if (ctime < ksTime) {
            dt = ksTime - ctime;
        } else {
            long kjsj = (ctime - ksTime) % (10 * 60 * 1000);
            dt = 10 * 60 * 1000 - kjsj;
        }
        if (orderNo >= Integer.parseInt(lottery.getCount())) {
            tvKjsj.setText("今天开奖已经结束！");
            mHandle.removeCallbacksAndMessages(null);
            return;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        try {
            ListView slv = (ListView) view;
            if (((PoweredAdapter) slv.getAdapter()).getCurrentIndex() != -1) {
                ((PoweredAdapter) slv.getAdapter()).setCurrentIndex(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        listViews[currentIndex].clearChoices();
    }

    private boolean isHidden;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isHidden = hidden;
        if (hidden) {
            List<KaiJiang_11x5> lists = lvZsAdapter.getList();
            if(lists != null){
                for(KaiJiang_11x5 kaiJiang_11x5: lists){
                    kaiJiang_11x5Dao.createOrUpdate(kaiJiang_11x5);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(mHandle != null){
            mHandle.removeCallbacksAndMessages(null);
        }
    }

    @OnClick(R.id.iv_header_right)
    public void onViewClicked() {
        mHandle.removeCallbacksAndMessages(null);
        isRunXunjian = false;
        requestData();
    }
}

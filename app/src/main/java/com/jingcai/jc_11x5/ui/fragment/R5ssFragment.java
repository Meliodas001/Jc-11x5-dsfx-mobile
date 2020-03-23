package com.jingcai.jc_11x5.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.SuoShuiFilter;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.activity.SsTjYulanActivity;
import com.jingcai.jc_11x5.ui.activity.SsglResultActivity;
import com.jingcai.jc_11x5.view.adapter.RvSsglAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class R5ssFragment extends BaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recycler_ssgl)
    RecyclerView recyclerSsgl;
    @Bind(R.id.btn_sstj)
    Button btnSstj;
    @Bind(R.id.btn_sstj_clean)
    Button btnSstjClean;
    @Bind(R.id.btn_ssgl)
    Button btnSsgl;

    private App app;
    private Handler mHandle;
    private RvSsglAdapter recyclerAdapter;

    public R5ssFragment() {}

    public static R5ssFragment newInstance() {
        R5ssFragment fragment = new R5ssFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_r5ss, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        tvTitle.setText("组合过滤");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerSsgl.setLayoutManager(linearLayoutManager);
        List<SuoShuiFilter> list = initSuoShuiFilterList();
        recyclerAdapter = new RvSsglAdapter(mContext, list);
        recyclerSsgl.setAdapter(recyclerAdapter);
    }

    @Override
    public void initData() {
        app = App.getInstance();
        initHandler();
    }

    @Override
    public void requestData() {

    }

    private void initHandler() {
        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }

        };
    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.GET_SSGLLIST_SUCCESS:
                ProgressWidget.dismissProgressDialog();
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    ArrayList<String> haomaGuolvLists = (ArrayList<String>) bundle.getSerializable(ReturnStatus.KEY_SSGL_LIST);
                    Bundle ssbundle = new Bundle();
                    ssbundle.putStringArrayList("numResult", haomaGuolvLists);
                    startNewActivityWithBundle(mContext, SsglResultActivity.class, ssbundle);
                }
                break;
            case HandlerWhat.GET_SSGLLIST_FALIURE:
                if (msg.obj != null) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                break;
        }
    }

    private List<SuoShuiFilter> initSuoShuiFilterList() {
        List<SuoShuiFilter> lists = new ArrayList<>();
        SuoShuiFilter suoShuiFilter0 = new SuoShuiFilter(0, "基础号码");
        suoShuiFilter0.setJieshi("从01-11中选择5个以上号码做为基础号码，过滤后的结果将由这些数字组成。");
        suoShuiFilter0.setAnli("例如：基础号码选择了01 02 03 04 05 06，那么过滤的结果只能由这六个号码组成。");

        SuoShuiFilter suoShuiFilter1 = new SuoShuiFilter(1, "胆组一");
        suoShuiFilter1.setJieshi("选中的胆码会出几个。");
        suoShuiFilter1.setAnli("例如：如果胆组一选择01 02 03出1个、两个，那么包含01 02 03中的1个或两个的号码将会被保留。");
        SuoShuiFilter suoShuiFilter2 = new SuoShuiFilter(2, "胆组二");
        suoShuiFilter2.setJieshi("选中的胆码会出几个。");
        suoShuiFilter2.setAnli("例如：如果胆组一选择01 02 03出1个、两个，那么包含01 02 03中的1个或两个的号码将会被保留。");
        SuoShuiFilter suoShuiFilter3 = new SuoShuiFilter(3, "胆组三");
        suoShuiFilter3.setJieshi("选中的胆码会出几个。");
        suoShuiFilter3.setAnli("例如：如果胆组一选择01 02 03出1个、两个，那么包含01 02 03中的1个或两个的号码将会被保留。");

        SuoShuiFilter suoShuiFilter16 = new SuoShuiFilter(4, "胆组四");
        suoShuiFilter16.setJieshi("选中的胆码会出几个。");
        suoShuiFilter16.setAnli("例如：如果胆组一选择01 02 03出1个、两个，那么包含01 02 03中的1个或两个的号码将会被保留。");
        SuoShuiFilter suoShuiFilter17 = new SuoShuiFilter(5, "胆组五");
        suoShuiFilter17.setJieshi("选中的胆码会出几个。");
        suoShuiFilter17.setAnli("例如：如果胆组一选择01 02 03出1个、两个，那么包含01 02 03中的1个或两个的号码将会被保留。");

        SuoShuiFilter suoShuiFilter4 = new SuoShuiFilter(6, "和值");
        suoShuiFilter4.setJieshi("号码中各位数字之和。");
        suoShuiFilter4.setAnli("例如：号码01 02 03 04 05的和值=1+2+3+4+5=15。");
        SuoShuiFilter suoShuiFilter5 = new SuoShuiFilter(7, "和尾");
        suoShuiFilter5.setJieshi("号码中各位数字之和的尾数（个位数）。");
        suoShuiFilter5.setAnli("例如：号码01 02 03 04 05的和值=1+2+3+4+5=15 和值15的尾数（个位数）即 5。");
        SuoShuiFilter suoShuiFilter6 = new SuoShuiFilter(8, "跨度");
        suoShuiFilter6.setJieshi("号码中的最大数字与最小数字的差。");
        suoShuiFilter6.setAnli("例如：号码01 02 03 06 09，跨度是9-1=8，跨度是8。");

        SuoShuiFilter suoShuiFilter12 = new SuoShuiFilter(9, "大小比");
        suoShuiFilter12.setJieshi("号码中大号个数与小号个数的比。大号（06-11）小号（01-05）。");
        suoShuiFilter12.setAnli("例如：号码 01 02 03 06 09，大小比为2:3");
        SuoShuiFilter suoShuiFilter9 = new SuoShuiFilter(10, "质合比");
        suoShuiFilter9.setJieshi("号码中质号和合号个数的比（质号：01 02 03 05 07 11合号：04 06 08 09 10）。");
        suoShuiFilter9.setAnli("例如：号码01 02 03 04 05，质合比为4:1。");
        SuoShuiFilter suoShuiFilter7 = new SuoShuiFilter(11, "奇偶比");
        suoShuiFilter7.setJieshi("号码中奇数号与偶数号个数的比。");
        suoShuiFilter7.setAnli("例如：号码01 02 03 04 05，奇号个数比偶号个数为3:2");

        SuoShuiFilter suoShuiFilter8 = new SuoShuiFilter(12, "邻号个数");
        suoShuiFilter8.setJieshi("邻码：就是与号码相邻的号码，一般指参照上期，和上期号码相邻的号码。");
        suoShuiFilter8.setAnli("例如：第1期开出：01，02，05，07，11第2期开出：01，02，06，09，10 相对上期有4个邻码，其中，第1期开出 的 01的邻码02 ，02的邻码 01 03，05的邻码 04 06，07的邻码 06 08 ，11的邻码 10，因此第2期包含有4个邻码01 02 06 10。");
        SuoShuiFilter suoShuiFilter10 = new SuoShuiFilter(13, "重号个数");
        suoShuiFilter10.setJieshi("重号就是和上期开奖重复的号码。");
        suoShuiFilter10.setAnli("例如：第1期开出：01 02 03 04 05，第2期开出:03 04 05 06 07则重号为03 04 05。");
        SuoShuiFilter suoShuiFilter11 = new SuoShuiFilter(14, "平均值");
        suoShuiFilter11.setJieshi("号码中各位数字之和除以号码个数，不能整除的四舍五入取整。");
        suoShuiFilter11.setAnli("例如：号码01 02 03 04 05，和值=1+2+3+4+5=15,15除以5=3 平均值为3。号码01 02 03 04 06 和值为16,16除以5=3.2，平均值四射五入为3。");

        SuoShuiFilter suoShuiFilter13 = new SuoShuiFilter(15, "已出号过滤");
        suoShuiFilter13.setJieshi("用来排除最近多少期内已经开出指定次数的号码。");
        suoShuiFilter13.setAnli("例如：设置最近100期开出2次，则最近100期内开出2期以上的号码会被排除。");
        SuoShuiFilter suoShuiFilter14 = new SuoShuiFilter(16, "连号");
        suoShuiFilter14.setJieshi("号码中数字连续情况。");
        suoShuiFilter14.setAnli("例如：号码01 02 03 04 05为五连，号码01 02 05 09 10，为两个两连，号码01 02 03 08 09为两连三连各一个。");
        SuoShuiFilter suoShuiFilter15 = new SuoShuiFilter(17, "012路比");
        suoShuiFilter15.setJieshi("号码中0路（03 06 09）个数、1路（01 04 07 10）个数、2路（02 05 08 11）个数的比。");
        suoShuiFilter15.setAnli("例如：号码01 02 03 04 05,012路比为1:2:2。");

        lists.add(suoShuiFilter0);
        lists.add(suoShuiFilter1);
        lists.add(suoShuiFilter2);
        lists.add(suoShuiFilter3);
        lists.add(suoShuiFilter16);
        lists.add(suoShuiFilter17);
        lists.add(suoShuiFilter4);
        lists.add(suoShuiFilter5);
        lists.add(suoShuiFilter6);
        lists.add(suoShuiFilter12);
        lists.add(suoShuiFilter9);
        lists.add(suoShuiFilter7);
        lists.add(suoShuiFilter8);
        lists.add(suoShuiFilter10);
        lists.add(suoShuiFilter11);
        lists.add(suoShuiFilter13);
        lists.add(suoShuiFilter14);
        lists.add(suoShuiFilter15);
        return lists;
    }

    public Map<Integer, String> sortMapByKey(Map<Integer, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<Integer, String> sortedMap = new TreeMap<Integer, String>(new Comparator<Integer>() {
            public int compare(Integer key1, Integer key2) {
                return key1 - key2;
            }});
        sortedMap.putAll(oriMap);
        return sortedMap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_sstj)
    public void onBtnSstjClicked() {
        List<SuoShuiFilter> lists = recyclerAdapter.getList();
        List<SuoShuiFilter> tjlists = new ArrayList<>();
        for(SuoShuiFilter suoShuiFilter : lists){
            Map<Integer, String> map = suoShuiFilter.getMap();
            for (String str : map.values()) {
                if (!TextUtils.isEmpty(str)) {
                    tjlists.add(suoShuiFilter);
                    break;
                }
            }
        }
        if(tjlists == null || tjlists.size() < 1){
            showMsg("您未设置任何过滤条件！");
            return;
        }
        ArrayList<String> guolTjLists = new ArrayList<>();
        for(SuoShuiFilter suoShuiFilter : tjlists){
            int type = suoShuiFilter.getType();
            String text = suoShuiFilter.getText();
            StringBuilder sbTiaoJian = new StringBuilder("");
            sbTiaoJian.append("[").append(text).append("]").append("已选").append("-");
            StringBuilder sbHaoma = new StringBuilder("");
            Map<Integer, String> map = suoShuiFilter.getMap();
            map = sortMapByKey(map);
            if(type == 1 || type == 2 || type == 3 || type == 4 || type == 5){
                StringBuilder haoma = new StringBuilder("");
                StringBuilder chuhao = new StringBuilder("");
                for (int key : map.keySet()) {
                    String value = map.get(key);
                    if (TextUtils.isEmpty(value)) {
                        continue;
                    }
                    if (key < 12) {
                        haoma.append(value.trim()).append(",");
                    } else {
                        chuhao.append(value.trim()).append("个").append(",");
                    }
                }
                String shaoma = haoma.toString();
                String schu = chuhao.toString();
                if (!TextUtils.isEmpty(shaoma)) {
                    shaoma = shaoma.substring(0, shaoma.length()-1);
                    sbTiaoJian.append(shaoma);
                }
                if (!TextUtils.isEmpty(schu)) {
                    schu = schu.substring(0, schu.length()-1);
                    sbTiaoJian.append("出").append(schu);
                }
            }else if(type == 15){
                String qishu = map.get(0);
                if (!TextUtils.isEmpty(qishu)) {
                    sbTiaoJian.append("最近").append(qishu).append("期");
                }
                String cishu = map.get(1);
                if (!TextUtils.isEmpty(cishu)) {
                    sbTiaoJian.append("已出次数:").append(cishu);
                }
            }else{
                for (String str : map.values()) {
                    if (!TextUtils.isEmpty(str)) {
                        sbHaoma.append(str.trim()).append(",");
                    }
                }
                String haoma = sbHaoma.toString();
                if (!TextUtils.isEmpty(haoma)) {
                    haoma = haoma.substring(0, haoma.length()-1);
                }
                sbTiaoJian.append(haoma);
            }
            guolTjLists.add(sbTiaoJian.toString());
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("tjResult", guolTjLists);
        startNewActivityWithBundle(mContext, SsTjYulanActivity.class, bundle);
    }

    @OnClick(R.id.btn_sstj_clean)
    public void onBtnSstjCleanClicked() {
        recyclerAdapter.setList(initSuoShuiFilterList());
    }

    @OnClick(R.id.btn_ssgl)
    public void onBtnSsglClicked() {
        ProgressWidget.showProgressDialog(mContext, "正在计算.请等待...");
        Jc11x5Factory.getInstance().getJisuanSsglLists(mHandle, recyclerAdapter.getList());
    }
}

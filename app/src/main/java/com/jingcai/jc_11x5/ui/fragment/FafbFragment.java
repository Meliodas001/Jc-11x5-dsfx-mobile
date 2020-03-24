package com.jingcai.jc_11x5.ui.fragment;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.CaiZhong;
import com.jingcai.jc_11x5.entity.Lottery;
import com.jingcai.jc_11x5.entity.PlanFabu;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.MainActivity;
import com.jingcai.jc_11x5.ui.activity.TouZhuActivity;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.DateUtil;
import com.jingcai.jc_11x5.util.FilteHmUtil;
import com.jingcai.jc_11x5.util.PromptUtil;
import com.jingcai.jc_11x5.view.adapter.DmAdapter;
import com.jingcai.jc_11x5.view.adapter.GvNumAdapter;
import com.jingcai.jc_11x5.view.widget.DialogWiget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FafbFragment extends BaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_kjqs)
    TextView tvKjqs;
    @Bind(R.id.tv_djs)
    TextView tvDjs;
    @Bind(R.id.tv_kj1)
    TextView tvKj1;
    @Bind(R.id.tv_kj2)
    TextView tvKj2;
    @Bind(R.id.tv_kj3)
    TextView tvKj3;
    @Bind(R.id.tv_kj4)
    TextView tvKj4;
    @Bind(R.id.tv_kj5)
    TextView tvKj5;
    @Bind(R.id.tv_jifen)
    TextView tvJifen;
    @Bind(R.id.tv_dianbi)
    TextView tvDianbi;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.gv_xuan_hao)
    GridView gvXuanHao;
    @Bind(R.id.rl_choose)
    RelativeLayout rlChoose;
    @Bind(R.id.bt_paste)
    Button btPaste;
    @Bind(R.id.bt_clean)
    Button btClean;
    @Bind(R.id.et_haoma)
    EditText etHaoma;
    @Bind(R.id.rl_paste)
    RelativeLayout rlPaste;
    @Bind(R.id.bt_next)
    Button btNext;
    @Bind(R.id.tv_zhu)
    TextView tvZhu;
    @Bind(R.id.tv_jf)
    TextView tvJf;
    @Bind(R.id.tv_jiangjin)
    TextView tvJiangjin;
    @Bind(R.id.rb_choose)
    RadioButton rbChoose;
    @Bind(R.id.rb_paste)
    RadioButton rbPaste;
    @Bind(R.id.et_fajia)
    EditText etFajia;

    private App app;
    private UserInfo user;
    private Handler mHandle;
    private Lottery lottery;
    private int defaultCount = 5;
    private boolean isSingle = true;
    private int selectType = 1;
    private DialogWiget dialogWiget;

    private GvNumAdapter numGvAdapter;

    private ClipboardManager mClipboardManager;//剪切板管理工具类
    private ClipData mClipData;//剪切板Data对象

    private int zhushu = 0;
    private int planType = 4;

    public FafbFragment() {
    }

    public static FafbFragment newInstance() {
        FafbFragment fragment = new FafbFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fafb, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        app = App.getInstance();
        mClipboardManager = (ClipboardManager) mContext.getSystemService(CLIPBOARD_SERVICE);
        user = app.getUser();
        dialogWiget = new DialogWiget();
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

        tvTitle.setText("任选五单式");
        tvTitle.setTag(1);
//        tvJiangjin.setText("");
        etHaoma.setLongClickable(false);

        lottery = app.getLottery();
        ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
        jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间

        Drawable nav_up = getResources().getDrawable(R.mipmap.downarrow_white);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tvTitle.setCompoundDrawables(null, null, nav_up, null);
        tvTitle.setCompoundDrawablePadding(8);//设置图片和text之间的间距
        tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + lottery.getCaiQishu() + "期");
        String[] array = lottery.getCaiNumArray();
        if (array != null && array.length >= 5) {
            tvKj1.setText(array[0]);
            tvKj2.setText(array[1]);
            tvKj3.setText(array[2]);
            tvKj4.setText(array[3]);
            tvKj5.setText(array[4]);
        }
        tvJifen.setText(user.getCoin());
        tvDianbi.setText(user.getMoney());
        numGvAdapter = new GvNumAdapter(mContext, new PlanFabu().getHaoMaList(), gvXuanHao);
        gvXuanHao.setAdapter(numGvAdapter);
        numGvAdapter.setOnCheckedListener(new GvNumAdapter.OnCheckedListener() {

            @Override
            public int getDefaultCount() {
                return defaultCount;
            }

            @Override
            public void getCheckedState(int state) {
                if (state == 1) {
                    zhushu += 1;
                    tvZhu.setText(String.valueOf(zhushu));
                    tvJf.setText(String.valueOf(zhushu * 2));
                }
                if (state == -1) {
                    if (zhushu > 0) {
                        zhushu -= 1;
                    }
                    tvZhu.setText(String.valueOf(zhushu));
                    tvJf.setText(String.valueOf(zhushu * 2));
                }
                if (state == -2) {
                    int count = numGvAdapter.getSelectNumCount();
                    if (count < defaultCount) {
                        tvZhu.setText("0");
                        tvJf.setText("0");
                        return;
                    }
                    if (planType == 10 || planType == 8) {
                        zhushu = (int) getAZhushu(defaultCount, count);
                        tvZhu.setText(String.valueOf(zhushu));
                        tvJf.setText(String.valueOf(zhushu * 2));
                    } else {
                        zhushu = (int) getCZhushu(defaultCount, count);
                        tvZhu.setText(String.valueOf(zhushu));
                        tvJf.setText(String.valueOf(zhushu * 2));
                    }
                }
            }
        });

        getDjs();
        mHandle.sendEmptyMessageDelayed(9, 1000);
    }

    public long getCZhushu(int m, int n) {
        return getNFactorial1(n) / (getNFactorial1(m) * getNFactorial1(n - m));
    }

    public long getAZhushu(int m, int n) {
        if (n == 0) {
            return 0;
        }
        return getNFactorial1(n) / getNFactorial1(n - m);
    }

    public long getNFactorial1(int n) {
        if (n == 0) {
            return 1l;
        }
        long sum = 1l;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    @Override
    public void setListener() {
        etHaoma.addTextChangedListener(new TextWatcher() {
            private int mPreviousLength;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mPreviousLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mPreviousLength > s.length()) {
                    return;
                }
                if (s.toString().endsWith("  ")) {
                    s.delete(s.toString().length() - 1, s.toString().length());
                    return;
                }
                String str = s.toString().trim();
                int wzz = str.lastIndexOf("\n");
                String endStr;
                if (wzz > 0) {
                    endStr = str.substring(str.lastIndexOf("\n"), str.length());
                } else {
                    endStr = str;
                }
                String[] array = endStr.trim().split(" ");
                if (array.length > defaultCount && !str.endsWith("\n")) {
                    int wz = str.lastIndexOf("\n") + endStr.lastIndexOf(" ");
                    s.replace(wz + 1, wz + 2, "\n");
                }
                int zhu = etHaoma.getText().toString().trim().split("\n").length;
                tvZhu.setText(String.valueOf(zhu));
                tvJf.setText(String.valueOf(zhu * 2));
            }
        });
    }

    private ArrayList<String> tmpArr = new ArrayList<>();

    //private String result = "";
    private String zuHeHaoma(String haoma, int k) {
        tmpArr.clear();
        String result = "";
        String[] com = haoma.split(" ");
        if (k > com.length || com.length <= 0) {
            return "";
        }
        result = combine(0, k, com, result);
        return result;
    }

    public String combine(int index, int k, String[] arr, String result) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmpArr.add(arr[i]);
                String str = tmpArr.toString();
                str = str.substring(1, str.length() - 1).replaceAll(",", "") + "\n";
                result += str;
                tmpArr.remove((Object) arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmpArr.add(arr[i]);
                result = combine(i + 1, k - 1, arr, result);
                tmpArr.remove((Object) arr[i]);
            }
        }
        return result;
    }

    @Override
    public void requestData() {

    }

    private void handlerMsg(Message msg) {
        try {
            switch (msg.what) {
                case HandlerWhat.GET_LUCKYNUM_SUCCESS:
                   /* lottery = (Lottery) msg.obj;
                    if (lottery == null) {
                        Jc11x5Factory.getInstance().getLuckyNumber(mHandle);
                        return;
                    }
                    String order = lottery.getCaiQishu();
                    if (!order.equals(tvKjqs.getText().toString())) {
                        tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + order + "期");
                        String[] array = lottery.getCaiNumArray();
                        if (array != null && array.length >= 5) {
                            tvKj1.setText(array[0]);
                            tvKj2.setText(array[1]);
                            tvKj3.setText(array[2]);
                            tvKj4.setText(array[3]);
                            tvKj5.setText(array[4]);
                        }
                        if (!isHidden) {
                            PromptUtil.startAlarm(mContext);
                        }
                        if (order.substring(6).equals(lottery.getCount())) {
                            tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + lottery.getCaiQishu() + "期");
                            long ctime = DateUtil.getNowMills();//当前时间
                            long nextTime = DateUtil.parseTimeToMillis(DateUtil.getMingtianDate() + " 00:00:00");
                            if (ctime < nextTime) {
                                tvDjs.setText("今天开奖已结束");
                                mHandle.sendEmptyMessageDelayed(11, 120000);
                            } else {
                                getDjs();
                            }
                        } else {
                            getDjs();
                        }
                    } else {
                        mHandle.sendEmptyMessageDelayed(10, 30000);
                    }*/
                    break;
                case 10:
                    Lottery lo = app.getLottery();
                    if (lo.getNew()) {
                        if (!isHidden)
                            PromptUtil.startAlarm(mContext);
                        lo.setNew(false);
                        initView();
                    } else {
                        mHandle.sendEmptyMessageDelayed(10, 1000);
                    }
                    break;
                case 9:
                    dt = dt - 1000;
                    if (dt < 0) {
                        dt = 0;
                    }
                    //时间格式化
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                    String hms = formatter.format(dt);
                    if (tvDjs != null) {
                        tvDjs.setText("下次开奖剩余：" + hms);
                    }
                    mHandle.removeMessages(9);
                    //发送消息，不断减时间
                    mHandle.sendEmptyMessageDelayed(9, 1000);
                    if (dt <= 500) {
                    /*getDjs();
                    Jc11x5Factory.getInstance().getLuckyNumber(mHandle, lottery.getCaiType());*/
                        String caiQishu = lottery.getCaiQishu();
                        tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + caiQishu + "期");
                        int nextQishu = Integer.parseInt(caiQishu.substring(6)) + 1;
                        if(nextQishu <= Integer.parseInt(lottery.getCount())){
                            tvDjs.setText("第" + nextQishu + "期正在开奖中");
                            mHandle.sendEmptyMessageDelayed(10, 1000);
                        }else{
                            tvDjs.setText("今天开奖已结束");
                            mHandle.sendEmptyMessageDelayed(11, 120000);
                        }
                    }
                    break;
                case 11:
                    long ctime = DateUtil.getNowMills();//当前时间
                    long nextTime = DateUtil.parseTimeToMillis(DateUtil.getMingtianDate() + " 00:00:00");
                    if (ctime > jsTime && ctime < nextTime) {
                        mHandle.sendEmptyMessageDelayed(11, 120000);
                    } else {
                        ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
                        jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间
                        mHandle.removeCallbacksAndMessages(null);
                        getDjs();
                    }
                    break;
                case HandlerWhat.GET_LUCKYNUM_TIMEOUT:
                case HandlerWhat.GET_LUCKYNUM_FALIURE:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private long ksTime;
    private long jsTime;
    private long dt = 0;

    private void getDjs() {
        long ctime = DateUtil.getNowMills();//当前时间
        int orderNo = CaiUtil.getCurrentPeriod();
        if (ctime > jsTime) {
            if (tvDjs != null) {
                tvDjs.setText("今天开奖已经结束");
            }
            mHandle.removeCallbacksAndMessages(null);
            return;
        }
        if (ctime < ksTime) {
            dt = ksTime - ctime;
        } else {
            long kjsj = (ctime - ksTime) % (20 * 60 * 1000);
            dt = 20 * 60 * 1000 - kjsj;
        }
        if (orderNo >= Integer.parseInt(lottery.getCount())) {
            if (tvDjs != null) {
                tvDjs.setText("今天开奖已经结束");
            }
            mHandle.removeCallbacksAndMessages(null);
            return;
        }
        //dt = 5000;
    }

    private void showCaiZhongList() {
        String title = "游戏玩法：";
        final DmAdapter dmadapter = new DmAdapter(mContext, CaiUtil.getPlanCaiList());
        dialogWiget.showListview(mContext, title, dmadapter, new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adv, View view, int position, long id) {
                if (dialogWiget.lDialog != null) {
                    dialogWiget.lDialog.dismiss();
                }
                try {
                    CaiZhong entity = (CaiZhong) adv.getAdapter().getItem(position);
                    tvTitle.setText(entity.getCaiMc());
                    String bm = entity.getCaiBm();
                    tvTitle.setTag(bm);
                    switch (bm) {
                        /*case "1":
                            defaultCount = 5;
                            planType = 4;
                            tvJiangjin.setText("840");
                            break;
                        case "2":
                            defaultCount = 3;
                            planType = 10;
                            tvJiangjin.setText("1081");
                            break;
                        case "3":
                            defaultCount = 3;
                            planType = 11;
                            tvJiangjin.setText("300");
                            break;
                        case "4":
                            defaultCount = 2;
                            planType = 8;
                            tvJiangjin.setText("200");
                            break;
                        case "5":
                            defaultCount = 2;
                            planType = 9;
                            tvJiangjin.setText("100");
                            break;*/
                    }
                    if (rbChoose.isChecked()) {
                        if (planType == 10 || planType == 8) {
                            zhushu = (int) getAZhushu(defaultCount, numGvAdapter.getSelectNumCount());
                            tvZhu.setText(String.valueOf(zhushu));
                            tvJf.setText(String.valueOf(zhushu * 2));
                        } else {
                            zhushu = (int) getCZhushu(defaultCount, numGvAdapter.getSelectNumCount());
                            tvZhu.setText(String.valueOf(zhushu));
                            tvJf.setText(String.valueOf(zhushu * 2));
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_title)
    public void onTvTitleClicked() {
        showCaiZhongList();
    }

    @OnCheckedChanged(R.id.rb_choose)
    public void onRbChooseCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            rlChoose.setVisibility(View.VISIBLE);
            rlPaste.setVisibility(View.GONE);
            etHaoma.setText("");
            tvZhu.setText("");
            tvJf.setText("");
            selectType = 1;
        }
    }

    @OnCheckedChanged(R.id.rb_paste)
    public void onRbPasteCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            numGvAdapter.setList(new PlanFabu().getHaoMaList());
            rlChoose.setVisibility(View.GONE);
            rlPaste.setVisibility(View.VISIBLE);
            tvZhu.setText("");
            tvJf.setText("");
            selectType = 2;
        }
    }

    @OnCheckedChanged(R.id.rb_danqi)
    public void onRbDqCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isSingle = true;
        }
    }

    @OnCheckedChanged(R.id.rb_zhuihao)
    public void onRbZhCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isSingle = false;
        }
    }

    @OnClick(R.id.bt_paste)
    public void onBtPasteClicked() {
        mClipData = mClipboardManager.getPrimaryClip();//GET贴板是否有内容
        if (mClipData == null) {
            return;
        }
        ClipData.Item item = mClipData.getItemAt(0);//获取到内容
        if (item == null) {
            return;
        }
        String text = item.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            text = text.replaceAll("\n+", "\n");
            text = text.replaceAll(" +", " ");
            if (text.length() > 1) {
                if (text.substring(0, 1).equals("\n")) {
                    text = text.substring(1, text.length());
                }
                if (text.substring(text.length() - 1, text.length()).equals("\n")) {
                    text = text.substring(0, text.length() - 1);
                }
            }
            String[] array = text.split("\n");
            for (String str : array) {
                int length = str.split(" ").length;
                if (length > 0 && length != defaultCount) {
                    showMsg("粘贴数据格式有误！");
                    return;
                }
            }
        }
        if (TextUtils.isEmpty(etHaoma.getText().toString())) {
            etHaoma.setText(text + "\n");
        } else {
            etHaoma.setText(etHaoma.getText().toString() + "\n" + text + "\n");
        }
        int zhu = etHaoma.getText().toString().split("\n").length;
        tvZhu.setText(String.valueOf(zhu));
        tvJf.setText(String.valueOf(zhu * 2));
    }

    @OnClick(R.id.bt_clean)
    public void onBtCleanClicked() {
        etHaoma.setText("");
    }

    @OnClick(R.id.bt_next)
    public void onBtNextClicked() {
        int count = numGvAdapter.getSelectNumCount();
        if (count != 0 && count < defaultCount) {
            showMsg("你当前选择的彩票最少是" + defaultCount + "位！");
            return;
        }
        String postContent = "";
        if (rbChoose.isChecked()) {
            String haoma = numGvAdapter.getSelectNum().trim();
            if (TextUtils.isEmpty(haoma)) {
                showMsg("请选择号码！");
                return;
            }
            if (planType == 10) {
                postContent = FilteHmUtil.getQian3(haoma);
            } else if (planType == 8) {
                postContent = FilteHmUtil.getQian2(haoma);
            } else {
                postContent = zuHeHaoma(haoma, defaultCount);
            }
        }
        if (rbPaste.isChecked()) {
            postContent = etHaoma.getText().toString().trim();
            if (TextUtils.isEmpty(postContent)) {
                showMsg("请填写号码！");
                return;
            }
        }
        postContent = postContent.replaceAll(" +", " ");
        postContent = postContent.replaceAll("\n+", "\n");
        if (postContent.length() > 1) {
            if (postContent.substring(0, 1).equals("\n")) {
                postContent = postContent.substring(1, postContent.length());
            }
            if (postContent.substring(postContent.length() - 1, postContent.length()).equals("\n")) {
                postContent = postContent.substring(0, postContent.length() - 1);
            }
        }
        String tvJ = tvJiangjin.getText().toString();
        if (isJiangjin(tvJ)) {
            int zhu = postContent.split("\n").length;
            Bundle bundle = new Bundle();
            bundle.putString("leixing", tvTitle.getText().toString());
            bundle.putInt("planType", planType);
            bundle.putString("zhushu", String.valueOf(zhu));
            bundle.putString("danbeiJj", tvJ);
            bundle.putString("postContent", postContent);
            bundle.putString("checkPrice", etFajia.getText().toString());
            bundle.putString("selectType", String.valueOf(selectType));
            //bundle.putBoolean("isSingle", isSingle);
            startNewActivityForResultWithBundle(mContext, TouZhuActivity.class, bundle, 1001);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case 1001:
                    user = app.getUser();
                    tvDianbi.setText(user.getMoney());
                    tvJifen.setText(user.getCoin());
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        boolean isTouzhu = bundle.getBoolean("isTouzhu", true);
                        if (isTouzhu) {
                            ((MainActivity) mContext).removeFragment(0);
                            ((MainActivity) mContext).removeFragment(4);
                        }
                    }
                    break;
            }
        }
    }

    private boolean isHidden;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isHidden = hidden;
        if (!hidden) {
            user = app.getUser();
        }
    }

    private boolean isJiangjin(String tvJ) {
        if (tvJ.equals("")) {
            showMsg("请填写积分奖励");
            return false;
        }
        if (tvJ.equals("0")) {
            showMsg("积分奖励不能为0");
            return false;
        }
        if (Integer.parseInt(tvJ) > 900) {
            showMsg("积分奖励不能大于900");
            return false;
        }
        return true;
    }
}

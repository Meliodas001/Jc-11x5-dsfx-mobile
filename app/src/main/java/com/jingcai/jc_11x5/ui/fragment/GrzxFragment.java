package com.jingcai.jc_11x5.ui.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.business.impl.WebSocketServer;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.CaiZhong;
import com.jingcai.jc_11x5.entity.ResultBean;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.MainActivity;
import com.jingcai.jc_11x5.ui.activity.BalanceListActivity;
import com.jingcai.jc_11x5.ui.activity.BtjsActivity;
import com.jingcai.jc_11x5.ui.activity.CoinDuihuanActivity;
import com.jingcai.jc_11x5.ui.activity.CoinListActivity;
import com.jingcai.jc_11x5.ui.activity.CoinTransferActivity;
import com.jingcai.jc_11x5.ui.activity.GameListActivity;
import com.jingcai.jc_11x5.ui.activity.HelpActivity;
import com.jingcai.jc_11x5.ui.activity.IntersectToolActivity;
import com.jingcai.jc_11x5.ui.activity.JobPriceListActivity;
import com.jingcai.jc_11x5.ui.activity.LoginActivity;
import com.jingcai.jc_11x5.ui.activity.MoneyDuihuanActivity;
import com.jingcai.jc_11x5.ui.activity.NewsActivity;
import com.jingcai.jc_11x5.ui.activity.ShareActivity;
import com.jingcai.jc_11x5.ui.activity.TestActivity;
import com.jingcai.jc_11x5.util.AppUtil;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.view.adapter.DmAdapter;
import com.jingcai.jc_11x5.view.adapter.GvChannelAdapter;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 个人中心
 */
public class GrzxFragment extends BaseFragment {


    @Bind(R.id.tv_nc)
    TextView tvNc;
    @Bind(R.id.tv_set)
    TextView tvSet;
    @Bind(R.id.tv_login_mc)
    TextView tvLoginMc;
    @Bind(R.id.tv_jifen)
    TextView tvJifen;
    @Bind(R.id.tv_dianbi)
    TextView tvDianbi;
    @Bind(R.id.tv_yue)
    TextView tvYue;
    @Bind(R.id.vip_time)
    TextView tvVipTime;
    @Bind(R.id.gv_channel)
    GridView gvChannel;
    @Bind(R.id.tv_exit)
    TextView tvExit;
    @Bind(R.id.btn_ceshi)
    Button btnCeshi;
    @Bind(R.id.rl_jifen)
    RelativeLayout rlJifen;
    @Bind(R.id.rl_dianbi)
    RelativeLayout rlDianbi;
    @Bind(R.id.rl_yue)
    RelativeLayout rlyue;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.kj_switch)
    Switch kjSwitch;

    private App app;
    private UserInfo user;
    private Handler mHandle;
    protected Window alertDialog;
    private DialogWiget dialogWiget;

    public GrzxFragment() {
    }

    public static GrzxFragment newInstance() {
        GrzxFragment fragment = new GrzxFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grzx, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        app = App.getInstance();
        user = app.getUser();
        dialogWiget = new DialogWiget();

        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }

        };
    }

    @Override
    public void initView() {
        List<ResultBean.Channel> lists = new ArrayList<>();
        ResultBean resultBean = new ResultBean();
        ResultBean.Channel channel1 = resultBean.new Channel(13, R.mipmap.ic_grzx_zs, "游戏币转赠");
        ResultBean.Channel channel2 = resultBean.new Channel(14, R.mipmap.ic_grzx_zs, "我要包时");
        ResultBean.Channel channel3 = resultBean.new Channel(1, R.mipmap.ic_grzx_db, "游戏币帐变"); //
        ResultBean.Channel channel4 = resultBean.new Channel(2, R.mipmap.ic_grzx_jf, "积分帐变");
        ResultBean.Channel channel5 = resultBean.new Channel(3, R.mipmap.ic_grzx_yx, "游戏记录");
        ResultBean.Channel channel6 = resultBean.new Channel(10, R.mipmap.ic_grzx_btjs, "计算器");
        ResultBean.Channel channel7 = resultBean.new Channel(12, R.mipmap.ic_grzx_jiaoji, "集合工具");
        ResultBean.Channel channel8 = resultBean.new Channel(8, R.mipmap.ic_grzx_db, "余额兑换");
        ResultBean.Channel channel9 = resultBean.new Channel(5, R.mipmap.ic_grzx_cai, "图表切换");
        ResultBean.Channel channel20 = resultBean.new Channel(6, R.mipmap.ic_grzx_xg, "修改密码");
        ResultBean.Channel channel21 = resultBean.new Channel(7, R.mipmap.ic_grzx_share, "分享好友");
        ResultBean.Channel channel22 = resultBean.new Channel(4, R.mipmap.ic_grzx_kf, "在线咨询");
        ResultBean.Channel channel23 = resultBean.new Channel(11, R.mipmap.ic_grzx_help, "帮助说明");
        ResultBean.Channel channel24 = resultBean.new Channel(15, R.mipmap.ic_grzx_zs, "消息");
//        ResultBean.Channel channel9 = resultBean.new Channel(9, R.mipmap.ic_grzx_qqz, "加入QQ群");
        lists.add(channel1);
        lists.add(channel2);
        lists.add(channel3);
        lists.add(channel4);
        lists.add(channel5);
        lists.add(channel6);
        lists.add(channel7);
        lists.add(channel8);
        lists.add(channel9);
        lists.add(channel20);
        lists.add(channel21);
        lists.add(channel22);
        lists.add(channel23);
        lists.add(channel24);
//        lists.add(channel25);
        GvChannelAdapter channelGvAdapter = new GvChannelAdapter(mContext, lists, gvChannel);
        gvChannel.setAdapter(channelGvAdapter);
        tvNc.setText(user.getNickName());
        tvLoginMc.setText("登录名：" + user.getUserName());
        tvDianbi.setText(user.getMoney());
        tvJifen.setText(user.getCoin());
        tvYue.setText(user.getBalance());
        tvVipTime.setText(user.getVipEndData());
        tvVersion.setText("版本号：" + AppUtil.getVersionName(App.getInstance()));
//        btnCeshi.setVisibility(View.VISIBLE);
        kjSwitch.setChecked(isOpenAlarm());
    }

    @Override
    public void requestData() {

    }

    private void handlerMsg(Message msg) {
        switch (msg.what) {
            case HandlerWhat.UPDATE_NC_SUCCESS:
                user = app.getUser();
                tvNc.setText(user.getNickName());
                showMsg(msg.obj);
                break;
            case HandlerWhat.UPDATE_PWD_SUCCESS:
                user = app.getUser();
                showMsg(msg.obj);
                SharedPreferences.Editor edit = mContext.getSharedPreferences(Constants.PREFERENCE_USER_KEY, mContext.MODE_PRIVATE).edit();
                edit.putString(Constants.PREFERENCE_SHARED_PWD, "");
                edit.commit();
                Jc11x5Factory.getInstance().loginOut(mHandle);
                startNewActivity(mContext, LoginActivity.class);
                break;
            case HandlerWhat.GET_USERINFOBYNAME_SUCCESS:
                user = app.getUser();
                tvNc.setText(user.getNickName());
                tvDianbi.setText(user.getMoney());
                tvJifen.setText(user.getCoin());
                tvVipTime.setText(user.getVipEndData());
                ProgressWidget.dismissProgressDialog();
                showMsg("刷新成功！");
                break;
            case HandlerWhat.GET_KJSJ_SUCCESS:
                break;
            case HandlerWhat.GET_LUCKYNUM_SUCCESS:
                break;
            default:
                if (msg.obj != null && !TextUtils.isEmpty(String.valueOf(msg.obj))) {
                    showMsg(String.valueOf(msg.obj));
                }
                ProgressWidget.dismissProgressDialog();
                break;
        }
    }

    private void showMsg(Object obj) {
        if (obj != null && !TextUtils.isEmpty(String.valueOf(obj))) {
            showMsg(String.valueOf(obj));
        }
        ProgressWidget.dismissProgressDialog();
    }

    private void showCaiZhongList() {
        String title = "请选择彩种：";
        final DmAdapter dmadapter = new DmAdapter(mContext, CaiUtil.getCaiZhongList());
        dialogWiget.showListview(mContext, title, dmadapter, new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adv, View view, int position, long id) {
                if (dialogWiget.lDialog != null) {
                    dialogWiget.lDialog.dismiss();
                }
                try {
                    CaiZhong entity = (CaiZhong) adv.getAdapter().getItem(position);
                    user.setCaizhong(entity.getCaiBm());
                    user.setCaizhongMc(entity.getCaiMc());
                    app.setUser(user);
                    WebSocketServer webSocketServer = new WebSocketServer();
                    webSocketServer.initSocketClient();
                    setCaiPreference(entity.getCaiBm());
                    ((MainActivity) mContext).removeFragment(0);
                    ((MainActivity) mContext).removeFragment(1);
                    ((MainActivity) mContext).removeFragment(2);
                } catch (Exception e) {
                }
            }
        });
    }

    public void setCaiPreference(String cai) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(Constants.PREFERENCE_USER_KEY, mContext.MODE_PRIVATE).edit();
        edit.putString(Constants.PREFERENCE_SHARED_CAI, cai);
        edit.commit();
    }

    private void showSetPanel(boolean isUpdateNc) {
        if (panelCallBack == null) {
            panelCallBack = new PanelCallBack();
        }
        panelCallBack.setShowUpdateNc(isUpdateNc);
        if (isUpdateNc) {
            new DialogWiget().showDiyView(getActivity(), R.layout.panel_set_nickname, panelCallBack);
        } else {
            new DialogWiget().showDiyView(getActivity(), R.layout.panel_set_pwd, panelCallBack);
        }
    }

    private PanelCallBack panelCallBack;

    class PanelCallBack implements DialogWiget.DiyDialog {

        public EditText etNickName;
        public EditText etPwd;
        public EditText etOldPwd;
        private Button btSubmit;
        private Button btCancel;
        private AlertDialog lDialog;
        private boolean showUpdateNc;

        public PanelCallBack() {
        }

        public void setShowUpdateNc(boolean showUpdateNc) {
            this.showUpdateNc = showUpdateNc;
        }

        @Override
        public void onDialog(AlertDialog lDialog, Window window) {
            findViewsInDialog(window);
            if (this.lDialog != null) {
                this.lDialog.dismiss();
            }
            this.lDialog = lDialog;
        }

        /**
         * 初始化控件
         *
         * @param window
         */
        private void findViewsInDialog(Window window) {
            GrzxFragment.this.alertDialog = window;
            if (showUpdateNc) {
                etNickName = (EditText) window.findViewById(R.id.et_nick_name);
            } else {
                etPwd = (EditText) window.findViewById(R.id.et_pwd);
                etOldPwd = (EditText) window.findViewById(R.id.et_pwd_old);
            }
            btSubmit = (Button) window.findViewById(R.id.bt_submit);
            btCancel = (Button) window.findViewById(R.id.bt_cancel);
            btSubmit.setOnClickListener(this);
            btCancel.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == btSubmit) {
                EditText t;
                if (showUpdateNc) {
                    if ((t = etNickName).getText().toString().length() == 0) {
                        t.setError("未输入，无法提交");
                        return;
                    }
                    String nickName = etNickName.getText().toString();
                    Jc11x5Factory.getInstance().UpdateNickName(mHandle, user.getId(), nickName);
                    closeKeyboard(getActivity(), etNickName);
                } else {
                    if ((t = etOldPwd).getText().toString().length() == 0) {
                        t.setError("未输入，无法提交");
                        return;
                    }
                    if (!(t = etOldPwd).getText().toString().trim().equals(user.getPassWord())) {
                        t.setError("原始密码不正确，无法修改");
                        return;
                    }
                    if ((t = etPwd).getText().toString().length() == 0) {
                        t.setError("未输入，无法提交");
                        return;
                    }
                    String newPwd = etPwd.getText().toString();
                    if (newPwd.equals(user.getPassWord())) {
                        t.setError("您输入的密码与原始密码相同，无法提交");
                        return;
                    }
                    Jc11x5Factory.getInstance().updatePassWord(mHandle, etOldPwd.getText().toString(), newPwd);
                    closeKeyboard(getActivity(), etPwd);
                }
                lDialog.dismiss();
            }
            if (v == btCancel) {
                lDialog.dismiss();
            }
        }

        @Override
        public void builder(AlertDialog.Builder builder) {
        }
    }

    public static void closeKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void zaiXianGouMai() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_QQ)));
        } catch (Exception e){
            showMsg("请检查是否安装QQ！");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnCheckedChanged(R.id.kj_switch)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        buttonView.setChecked(isChecked);
        if(isChecked != isOpenAlarm()){
            SharedPreferences.Editor edit = getActivity().getSharedPreferences(Constants.PREFERENCE_USER_KEY, getActivity().MODE_PRIVATE).edit();
            edit.putBoolean(Constants.PREFERENCE_SHARED_ALARM, isChecked);
            edit.commit();
        }
    }

    private boolean isOpenAlarm(){
        SharedPreferences pref = getActivity().getSharedPreferences(Constants.PREFERENCE_USER_KEY, getActivity().MODE_PRIVATE);
        return pref.getBoolean(Constants.PREFERENCE_SHARED_ALARM, true);
    }

    @OnClick(R.id.tv_set)
    public void onTvSetClicked() {
        showSetPanel(true);
    }

    @OnClick(R.id.tv_exit)
    public void onTvExitClicked() {
        app.removeActivityStack();
        app.setUser(null);
        SharedPreferences.Editor edit = getActivity().getSharedPreferences(Constants.PREFERENCE_USER_KEY, getActivity().MODE_PRIVATE).edit();
        edit.putString(Constants.PREFERENCE_SHARED_PWD, "");
        edit.putBoolean(Constants.PREFERENCE_SHARED_AUTO, false);
        edit.commit();
        Jc11x5Factory.getInstance().loginOut(mHandle);
        startNewTaskActivity(getActivity(), LoginActivity.class);
        getActivity().finish();
    }

    @OnClick(R.id.btn_ceshi)
    public void onBtnCeshiClicked() {
        startActivity(new Intent(getActivity(), TestActivity.class));
    }

    @OnItemClick(R.id.gv_channel)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResultBean.Channel channel = (ResultBean.Channel) parent.getAdapter().getItem(position);
        int channelId = channel.getId();
        switch (channelId) {
            case 1:
                startNewActivity(mContext, BalanceListActivity.class);
                break;
            case 2:
                startNewActivity(mContext, CoinListActivity.class);
                break;
            case 3:
                startNewActivity(mContext, GameListActivity.class);
                break;
            case 4:
                zaiXianGouMai();
                break;
            case 5:
                //彩种切换
                showCaiZhongList();
                break;
            case 6:
                //修改密码
                showSetPanel(false);
                break;
            case 7:
                //好友分享
                startNewActivity(mContext, ShareActivity.class);
                break;
            case 8:
                //余额兑换
                startNewActivityForResultWithBundle(mContext, MoneyDuihuanActivity.class, null, 1001);
                break;
            case 9:
                //加入QQ群
                /*Uri uri = Uri.parse("http://39.108.153.232/download/qqqun.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
                startActivity(intent);*/
                String key = "sOTAoR1ORmgrcdJZj7uDqBoiZW9EtQVC";
                Intent intent = new Intent();
                intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    showMsg("未安装手Q或安装的版本不支持!");
                }
                break;
            case 10:
                //倍投计算器
                startNewActivity(getActivity(), BtjsActivity.class);
                break;
            case 11:
                startNewActivity(getActivity(), HelpActivity.class);
                break;
            case 12:
                //交集工具
                startNewActivity(getActivity(), IntersectToolActivity.class);
                break;
            case 13:
                //无忧币转赠
                startNewActivityForResultWithBundle(mContext, CoinTransferActivity.class, null, 1002);
                break;
            case 14:
                //包月功能
                startNewActivity(getActivity(), JobPriceListActivity.class);
                break;
            case 15:
                //消息
                startNewActivity(getActivity(), NewsActivity.class);
                break;
        }
    }

    /****************
     *
     * 发起添加群流程。群号：51lucky会员交流群(759478852) 的 key 为： sOTAoR1ORmgrcdJZj7uDqBoiZW9EtQVC
     * 调用 joinQQGroup(sOTAoR1ORmgrcdJZj7uDqBoiZW9EtQVC) 即可发起手Q客户端申请加群 51lucky会员交流群(759478852)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1001:
                updatePageInfo();
                break;
            case 1002:
                if (resultCode == getActivity().RESULT_OK) {
                    updatePageInfo();
                }
                break;
        }
    }

    private void updatePageInfo(){
        user = app.getUser();
        tvDianbi.setText(user.getMoney());
        tvJifen.setText(user.getCoin());
        ((MainActivity) mContext).removeFragment(0);
        ((MainActivity) mContext).removeFragment(2);
    }

    @OnClick(R.id.rl_jifen)
    public void onRlJifenClicked() {
        startNewActivityForResultWithBundle(mContext, CoinDuihuanActivity.class, null, 1001);
        //startNewActivity(mContext, CoinDuihuanActivity.class);
    }

    @OnClick(R.id.rl_dianbi)
    public void onRlDianbiClicked() {
        zaiXianGouMai();
    }

    @OnClick(R.id.tv_refresh)
    public void onViewClicked() {
        ProgressWidget.showProgressDialog(getActivity(), "正在加载中...");
        Jc11x5Factory.getInstance().getUserInfoByUserName(mHandle, user.getUserName());
    }
}

package com.jingcai.jc_11x5.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.business.impl.WebSocketServer;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.ResultBean;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.activity.*;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.view.adapter.RvHomeAdapter;
import com.jingcai.jc_11x5.view.widget.ProgressWidget;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private final static String TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.recycler_home)
    RecyclerView recyclerHome;

    private App app;
    private UserInfo user;
    private ResultBean resultBean;
    private Handler mHandle;
    private RvHomeAdapter recyclerAdapter;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    private String leiXing;

    @Override
    public void initView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerHome.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RvHomeAdapter(mContext, resultBean);
        recyclerHome.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnGvListeners(new RvHomeAdapter.OnGvListener() {
            @Override
            public void onItemClickListener(Object object) {
                if(object instanceof ResultBean.Channel){
                    int id = ((ResultBean.Channel)object).getId();
                    switch (id){
                        case 1:
                            startNewActivity(mContext, SeasonTopProfitActivity.class);
                            break;
                        case 2:
                            startNewActivity(mContext, MonthTopProfitActivity.class);
                            break;
                        case 3:
                            startNewActivity(mContext, WeekTopProfitActivity.class);
                            break;
                        case 4:
                            zaiXianGouMai();
                            break;
                    }
                }
            }
            @Override
            public void onItemClickListener(Object object, String leixing) {
                leiXing = leixing;
                if(object instanceof ResultBean.LotteryPlan){
                    ResultBean.LotteryPlan lotteryPlan = (ResultBean.LotteryPlan)object;
                    Bundle bundle = new Bundle();
                    bundle.putString("planId", lotteryPlan.getId());
                    bundle.putBoolean("isCheck", true);
                    bundle.putBoolean("isCheckPrice", false);
                    if(lotteryPlan.getUserId().equals(user.getId())){
                        bundle.putBoolean("isCheckPrice", false);
                    }else{
                        if(lotteryPlan.getCheckPrice() > 0)
                            bundle.putBoolean("isCheckPrice", true);
                    }
                    startNewActivityForResultWithBundle(mContext, PlanDetailActivity.class, bundle, 1011);
                }
            }
        });
    }

    @Override
    public void initData() {
        app = App.getInstance();
        user = app.getUser();
        resultBean = new ResultBean();
        mHandle = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    @Override
    public void requestData() {
        ProgressWidget.showProgressDialog(getActivity(), "正在加载中...");
        if(user.getCaizhongMc() == null){
            user.setCaizhongMc(CaiUtil.getCaiMc(user.getCaizhong()));
            app.setUser(user);
        }
        Jc11x5Factory.getInstance().getHomeData(mHandle, user.getUserName(), user.getCaizhong());
    }

    private void handlerMsg(Message msg) {
        try {
            switch (msg.what) {
                case HandlerWhat.GET_HOMEDATA_SUCCESS:
                    ProgressWidget.dismissProgressDialog();
                    Object obj = msg.obj;
                    if(obj instanceof ResultBean){
                        ResultBean resultBean = (ResultBean)obj;
                        recyclerAdapter.setData(resultBean);
                    }
                    break;
                case HandlerWhat.GET_NEWPLANLIST_SUCCESS:
                    Bundle bundle = msg.getData();
                    if(bundle != null){
                        List<ResultBean.LotteryPlan> lists = (List<ResultBean.LotteryPlan>) bundle.getSerializable(ReturnStatus.KEY_GET_NEWPLAN_LIST);
                        resultBean.setLotteryPlanList(lists);
                        recyclerAdapter.notifyItemChanged(4);
                    }
                    break;
                case HandlerWhat.GET_HOMEDATA_FALIURE:
                    if (msg.obj != null) {
                        showMsg(String.valueOf(msg.obj));
                    }
                    startNewActivity(mContext, LoginActivity.class);
                    break;
                default:
                    if (msg.obj != null) {
                        showMsg(String.valueOf(msg.obj));
                    }
                    ProgressWidget.dismissProgressDialog();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case 1011:
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        boolean isUpdate = bundle.getBoolean("isUpdate", false);
                        if (isUpdate) {
                            Jc11x5Factory.getInstance().newPlanList(mHandle, leiXing);
                        }
                    }
                    break;
            }
        }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(recyclerAdapter != null){
            recyclerAdapter.setHidden(hidden);
//            recyclerAdapter.updateCai();
        }
        if(!hidden){
            user = app.getUser();
        }
    }

}

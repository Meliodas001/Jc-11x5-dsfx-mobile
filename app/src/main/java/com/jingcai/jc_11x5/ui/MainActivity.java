package com.jingcai.jc_11x5.ui;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.business.Jc11x5Interface;
import com.jingcai.jc_11x5.consts.Constants;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.entity.UserInfo;
import com.jingcai.jc_11x5.handler.LintHandler;
import com.jingcai.jc_11x5.ui.activity.FrActivity;
import com.jingcai.jc_11x5.ui.activity.LoginActivity;
import com.jingcai.jc_11x5.ui.activity.NewsActivity;
import com.jingcai.jc_11x5.ui.fragment.FafbFragment;
import com.jingcai.jc_11x5.ui.fragment.GrzxFragment;
import com.jingcai.jc_11x5.ui.fragment.HomeFragment;
import com.jingcai.jc_11x5.ui.fragment.KjzsFragment2;
import com.jingcai.jc_11x5.ui.fragment.R5ssFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FrActivity {

    @Bind(R.id.main_indicator_home)
    RelativeLayout mainIndicatorHome;
    @Bind(R.id.main_indicator_zst)
    RelativeLayout mainIndicatorZst;
    @Bind(R.id.main_indicator_fafb)
    RelativeLayout mainIndicatorFafb;
    @Bind(R.id.main_indicator_r5ss)
    RelativeLayout mainIndicatorR5ss;
    @Bind(R.id.main_indicator_grzx)
    RelativeLayout mainIndicatorGrzx;
    @Bind(R.id.iv_set)
    ImageView ivSet;

    private Handler mHandler;
    private List<View> mTabIndicator = new ArrayList<>();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private int pageIndex = 0;

    private Map<Integer, Fragment> fragmentMap;
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";

    private static final int MSG_SINGLE_POINT = 10010;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = App.getInstance();
        app.setWrite(true);
        fragmentMap = new HashMap<>();
        fragmentManager = getSupportFragmentManager();
        if(Build.VERSION.SDK_INT >= 23){
            //判断当前系统的版本
            verifyStoragePermissions(this);
        }
        initHandler();
        initViews();
        if (savedInstanceState != null) {
            //获取“内存重启”时保存的索引下标
            pageIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);
            restoreFragment();
            updateTabBar(pageIndex);
        }else{
            mTabIndicator.get(pageIndex).setSelected(true);
            setCurrentFragment(pageIndex);
        }
    }

    private void initData(){
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                String phone = App.getInstance().getUser().getUserName();
                if(phone == null || TextUtils.isEmpty(phone)){
                    startNewTaskActivity(MainActivity.this, LoginActivity.class);
                    finish();
                }
            }
        }
    }

    private void initHandler(){
        mHandler = new LintHandler(this) {
            @Override
            public void beginHandleMessage(Message msg) {
                handlerMsg(msg);
            }
        };
    }

    private void handlerMsg(Message msg) {
        try {
            switch (msg.what) {
                case MSG_SINGLE_POINT:
//                    Jc11x5Factory.getInstance().verifyComputer(mHandler, App.getInstance().getUser());
                    UserInfo userInfo = App.getInstance().getUser();
                    String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
                    if (!userInfo.getMac().equals(ANDROID_ID)) {
                        app.removeActivityStack();
                        app.setUser(null);
                        SharedPreferences.Editor edit = getSharedPreferences(Constants.PREFERENCE_USER_KEY, MODE_PRIVATE).edit();
                        edit.putString(Constants.PREFERENCE_SHARED_PWD, "");
                        edit.putBoolean(Constants.PREFERENCE_SHARED_AUTO, false);
                        edit.commit();
                        String tip = "您的账号已在其他设备登录，您已被迫退出！";
                        startNewTaskActivity(MainActivity.this, LoginActivity.class, tip);
                        showMsg("");
                        finish();
                    }
                    break;
                case HandlerWhat.VERIFY_SUCCESS:
                    Object obj = msg.obj;
                    if (obj != null && !TextUtils.isEmpty(String.valueOf(obj))) {
                        String result = String.valueOf(obj);
                        if (result.equals("-1")) {
                            app.removeActivityStack();
                            app.setUser(null);
                            SharedPreferences.Editor edit = getSharedPreferences(Constants.PREFERENCE_USER_KEY, MODE_PRIVATE).edit();
                            edit.putString(Constants.PREFERENCE_SHARED_PWD, "");
                            edit.putBoolean(Constants.PREFERENCE_SHARED_AUTO, false);
                            edit.commit();
                            String tip = "您的账号已在其他设备登录，您已被迫退出！";
                            startNewTaskActivity(MainActivity.this, LoginActivity.class, tip);
                            showMsg("");
                            finish();
                        }
                    }
//                    verifyComputer();
                    break;
                case HandlerWhat.VERIFY_TIMEOUT:
                case HandlerWhat.VERIFY_FALIURE:
//                    verifyComputer();
                    break;
                default:
                    break;
            }
        }catch (Exception e){

        }
    }

    private static final long DELAY_TIME = 15000;

    private void verifyComputer(){
        mHandler.sendEmptyMessageDelayed(MSG_SINGLE_POINT, DELAY_TIME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessage(MSG_SINGLE_POINT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

    private void initViews() {
        mTabIndicator.add(mainIndicatorHome);
        mTabIndicator.add(mainIndicatorZst);
        mTabIndicator.add(mainIndicatorFafb);
        mTabIndicator.add(mainIndicatorR5ss);
        mTabIndicator.add(mainIndicatorGrzx);
        for (int i = 0; i < mTabIndicator.size(); i++) {
            View v = mTabIndicator.get(i);
            v.setTag(i);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getTag() instanceof Integer) {
                        Integer position = (Integer) v.getTag();
                        pageIndex = position;
                        setCurrentFragment(position);
                        updateTabBar(position);
                    }
                }
            });
        }
    }

    private void setCurrentFragment(int position) {
        Fragment fragment = getCurrentFragment(position);//得到要显示的fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();//通过FragmentManager得到fragment事务
        //如果之前没有添加过
        if(!fragment.isAdded()){
            transaction.hide(currentFragment).add(R.id.content_home, fragment, String.valueOf(position));
        }else{
            transaction.hide(currentFragment).show(fragment);
        }
        currentFragment = fragment;
        transaction.commit();
        UserInfo u = App.getInstance().getUser();
        if (u.getNews() != null && u.getNews() == 1) {
            Resources resources = this.getResources();
            ivSet.setImageDrawable(resources.getDrawable(R.mipmap.icon_grzx_xx));
        }
    }

    /*private void setCurrentFragment(int position) {
        Fragment fragment = getFragment(position);//得到要显示的fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//通过FragmentManager得到fragment事务
        transaction.replace(R.id.content_home, fragment);//将fragment放到content_home中进行显示
        transaction.commit();//将事务进行提交
    }*/

    /**
     * 恢复fragment
     */
    private void restoreFragment(){
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < 5; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(String.valueOf(pageIndex));
            if(fragment != null){
                fragmentMap.put(pageIndex, fragment);
                if(i == pageIndex){
                    mBeginTreansaction.show(fragment);
                }else{
                    mBeginTreansaction.hide(fragment);
                }
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragmentMap.get(pageIndex);
    }

    /**
     * 更新底部TABBAR状态
     *
     * @param position 位置
     */
    private void updateTabBar(int position) {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            if (i != position) {
                mTabIndicator.get(i).setSelected(false);
            } else {
                mTabIndicator.get(i).setSelected(true);
                if(i == 4 && App.getInstance().getUser().getNews() == 1){
                    startNewActivity(NewsActivity.class);
                }
            }
        }
    }

    private Fragment getCurrentFragment(int position){
        if(fragmentMap.containsKey(position)){
            Fragment fragment = fragmentMap.get(position);
            if(fragment != null){
                return fragment;
            }
        }
        Fragment fragment = getFragment(position);
        fragmentMap.put(position, fragment);
        return fragment;
    }

    public void removeFragment(int position){
        Fragment fragment = null;
        if(fragmentMap.containsKey(position)){
            fragment = fragmentMap.get(position);
        }
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
            fragmentMap.put(position, null);
        }
    }

    public Fragment getFragment(int position) {
        switch (position) {
            case Constants.PAGE_ONE:
                return HomeFragment.newInstance();
            case Constants.PAGE_TWO:
                //return KjzsFragment.newInstance();
                return KjzsFragment2.newInstance();
            case Constants.PAGE_THREE:
                return FafbFragment.newInstance();
            case Constants.PAGE_FOUR:
                return R5ssFragment.newInstance();
            case Constants.PAGE_FIVE:
                return GrzxFragment.newInstance();
            default:
                break;
        }
        return null;
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    App.getInstance().setWrite(true);
                }else{
                    App.getInstance().setWrite(false);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT, pageIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}

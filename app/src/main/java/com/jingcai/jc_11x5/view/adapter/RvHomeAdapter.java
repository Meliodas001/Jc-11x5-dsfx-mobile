package com.jingcai.jc_11x5.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jingcai.jc_11x5.R;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.business.GlideImageLoader;
import com.jingcai.jc_11x5.business.Jc11x5Factory;
import com.jingcai.jc_11x5.business.impl.WebSocketServer;
import com.jingcai.jc_11x5.consts.HandlerWhat;
import com.jingcai.jc_11x5.consts.ReturnStatus;
import com.jingcai.jc_11x5.entity.*;
import com.jingcai.jc_11x5.ui.activity.PlanDetailActivity;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.DateUtil;
import com.jingcai.jc_11x5.util.LogUtil;
import com.jingcai.jc_11x5.util.PromptUtil;
import com.jingcai.jc_11x5.view.NoticeView;
import com.jingcai.jc_11x5.view.widget.DialogWiget;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangsen on 2018/1/14.
 */

public class RvHomeAdapter extends RecyclerView.Adapter<RvHomeAdapter.BseeHolder> {

    /**
     * 广告幅
     */
    private static final int BANNER = 0;
    private static final int NOTICE = 1;
    private static final int YINGLI = 2;
    private static final int CAIPIAO = 3;
    private static final int JIHUA = 4;

    private int itemCount = 5;

    private LayoutInflater mLayoutInflater;
    private ResultBean resultBean;
    private Lottery lottery;
    private Context mContext;
    private boolean isHidden;
    App app = App.getInstance();

    public RvHomeAdapter(Context mContext, ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void updateCai(){
        Lottery lt = app.getLottery();
        if(lt != null && (!lt.getCaiNumArray().equals(resultBean.getLottery().getCaiNumber()))){
            lt.setDianBi(app.getUser().getMoney());
            lt.setJiFen(app.getUser().getCoin());
            resultBean.setLottery(lt);
            this.notifyItemChanged(3);
        }
    }

    public void setData(ResultBean resultBean){
        if(resultBean == null){
            this.resultBean = new ResultBean();
        }
        this.resultBean = resultBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public BseeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            //创建BannerViewHolder，Banner里面传布局文件
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.recycle_item_banner, parent, false));
        }
        if (viewType == NOTICE) {
            return new NoticeViewHolder(mLayoutInflater.inflate(R.layout.recycle_item_notice, parent, false));
        }
        if (viewType == YINGLI) {
            return new ProfitViewHolder(mLayoutInflater.inflate(R.layout.recycle_item_profit, parent, false));
        }
        if (viewType == CAIPIAO) {
            return new LotteryViewHolder(mLayoutInflater.inflate(R.layout.recycle_item_cai, parent, false));
        }
        if (viewType == JIHUA) {
            return new PlanViewHolder(mLayoutInflater.inflate(R.layout.recycle_item_plan, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BseeHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<ImageInfo> imgLists = resultBean.getImgLists();
            if (imgLists != null && imgLists.size() > 0) {
                bannerViewHolder.setVisibility(true);
                bannerViewHolder.setData(imgLists);
            } else {
                bannerViewHolder.setVisibility(false);
            }
        } else if (itemViewType == NOTICE) {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;
            List<NoticeInfo> noticeLists = resultBean.getNoticeLists();
            if (noticeLists != null && noticeLists.size() > 0) {
                noticeViewHolder.setVisibility(true);
                noticeViewHolder.setData(noticeLists);
            } else {
                noticeViewHolder.setVisibility(false);
            }
        } else if (itemViewType == YINGLI) {
            ProfitViewHolder profitViewHolder = (ProfitViewHolder) holder;
            profitViewHolder.setData(resultBean.getChannelLists());
        } else if (itemViewType == CAIPIAO) {
            LotteryViewHolder lotteryViewHolder = (LotteryViewHolder) holder;
//            lotteryViewHolder.setData();
        } else if (itemViewType == JIHUA) {
            PlanViewHolder planViewHolder = (PlanViewHolder) holder;
            planViewHolder.setData();
        }
    }

    @Override
    public int getItemViewType(int position) {
        int currenType = BANNER;
        switch (position) {
            case BANNER:
                currenType = BANNER;
                break;
            case NOTICE:
                currenType = NOTICE;
                break;
            case YINGLI:
                currenType = YINGLI;
                break;
            case CAIPIAO:
                currenType = CAIPIAO;
                break;
            case JIHUA:
                currenType = JIHUA;
                break;
        }
        return currenType;
    }

    public class BseeHolder extends RecyclerView.ViewHolder {
        public BseeHolder(View itemView) {
            super(itemView);
        }
    }

    class BannerViewHolder extends BseeHolder {
        @Bind(R.id.banner)
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setVisibility(boolean isVisible){
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
            if (isVisible){
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
            }else{
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
            }
            itemView.setLayoutParams(param);
        }

        public void setData(List<ImageInfo> imgLists) {
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            //设置轮播时间
            banner.setDelayTime(4500);
            /*List<Integer> list = new ArrayList<>();
            list.add(R.mipmap.banner);
            list.add(R.mipmap.banner);
            list.add(R.mipmap.banner);*/
            List<String> list = new ArrayList<>();
            if(imgLists != null && imgLists.size() > 0){
                for(int i = 0; i < imgLists.size(); i++){
                    list.add(imgLists.get(i).getImgUrl());
                }
            }
            banner.setImages(list).setImageLoader(new GlideImageLoader()).start();
            //设置点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                }
            });
        }
    }

    class NoticeViewHolder extends BseeHolder {

        @Bind(R.id.notice_view)
        NoticeView noticeView;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setVisibility(boolean isVisible){
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
            if (isVisible){
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
            }else{
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
            }
            itemView.setLayoutParams(param);
        }

        public void setData(List<NoticeInfo> noticeLists){
            noticeView.addNotice(noticeLists);
            noticeView.startFlipping();
        }
    }

    class ProfitViewHolder extends BseeHolder {

        @Bind(R.id.gv_profit)
        GridView gvProfit;

        ProfitViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(List<ResultBean.Channel> lists) {
            if(lists == null){
                lists = new ArrayList<>();
            }
            GvProfitAdapter profitGvAdapter = new GvProfitAdapter(mContext, lists, gvProfit);
            gvProfit.setAdapter(profitGvAdapter);
            gvProfit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onGvListeners.onItemClickListener(parent.getAdapter().getItem(position));
                }
            });

        }
    }

    class LotteryViewHolder extends BseeHolder {
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
        /*@Bind(R.id.tv_yue)
        TextView tvYue;*/

        private String currentOrder;

        LotteryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            setData();
        }

        private long dt = 0;
        long ksTime;//第一期开始时间
        long jsTime;//最后一期开始时间

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    switch (msg.what) {
                        case HandlerWhat.GET_LUCKYNUM_SUCCESS:
                           /* String lastOrder = lottery.getCaiQishu();
                            Lottery newLottery = (Lottery) msg.obj;
                            String order = newLottery.getCaiQishu();
                            LogUtil.info("HomeAdapter","lastOrder=" + lastOrder + "; order = " + order);
                            if (!order.equals(lastOrder)) {
                                tvKjqs.setText(newLottery.getCaiTypeMc() + " 第" + order + "期");
                                currentOrder = order;
                                String[] array = newLottery.getCaiNumArray();
                                if (array != null && array.length >= 5) {
                                    tvKj1.setText(array[0]);
                                    tvKj2.setText(array[1]);
                                    tvKj3.setText(array[2]);
                                    tvKj4.setText(array[3]);
                                    tvKj5.setText(array[4]);
                                }
                                App.getInstance().setLottery(newLottery);
                                if(!isHidden){
                                    PromptUtil.startAlarm(mContext);
                                }
                                if (order.substring(6).equals(newLottery.getCount())) {
                                    tvKjqs.setText(newLottery.getCaiTypeMc() + " 第" + order + "期");
                                    long ctime = DateUtil.getNowMills();//当前时间
                                    long nextTime = DateUtil.parseTimeToMillis(DateUtil.getMingtianDate() + " 00:00:00");
                                    if (ctime < nextTime) {
                                        tvDjs.setText("今天开奖已结束");
                                        handler.sendEmptyMessageDelayed(11, 120000);
                                    } else {
                                        getDjs();
                                    }
                                } else {
                                    handler.removeCallbacksAndMessages(null);
                                    getDjs();
                                    handler.sendEmptyMessageDelayed(0,1000);
                                }
                            } else {
                                handler.sendEmptyMessageDelayed(10, 2000);
                            }*/
                            break;
                        case HandlerWhat.GET_LUCKYNUM_TIMEOUT:
                        case HandlerWhat.GET_LUCKYNUM_FALIURE:
                            break;
                        case 0:
                            dt = dt - 1000;
                            if (dt < 0) {
                                dt = 0;
                            }
                            //时间格式化
                            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                            formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                            String hms = formatter.format(dt);
                            tvDjs.setText("下次开奖剩余：" + hms);
                            handler.removeMessages(0);
                            //发送消息，不断减时间
                            handler.sendEmptyMessageDelayed(0, 1000);
                            if (dt <= 500) {
                                //getDjs();
                                String caiQishu = lottery.getCaiQishu();
                                /*if (cq == null || TextUtils.isEmpty(cq)) {
                                    Jc11x5Factory.getInstance().getLuckyNumber(handler);
                                    break;
                                }
                                String caiQishu = lottery.getCaiQishu();
                                if (cq.substring(6, 8).equals(lottery.getCount())) {
                                    tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + caiQishu + "期");
                                    long ctime = DateUtil.getNowMills();//当前时间
                                    long nextTime = DateUtil.parseTimeToMillis(DateUtil.getMingtianDate() + " 00:00:00");
                                    if (ctime < nextTime) {
                                        tvDjs.setText("今天开奖已结束");
                                        handler.sendEmptyMessageDelayed(11, 120000);
                                    } else {
                                        getDjs();
                                    }
                                } else {*/
                                    tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + caiQishu + "期");
                                    int nextQishu = Integer.parseInt(caiQishu.substring(6)) + 1;
                                    if(nextQishu <= Integer.parseInt(lottery.getCount())){
                                        tvDjs.setText("第" + nextQishu + "期正在开奖中");
                                        handler.sendEmptyMessageDelayed(10, 1000);
                                    }else{
                                        tvDjs.setText("今天开奖已结束");
                                        handler.sendEmptyMessageDelayed(11, 120000);
                                    }
//                                }
                            }
                            break;
                        case 10:
                            Lottery lo = app.getLottery();
                            if (lo.getNew()) {
                                if (!isHidden)
                                    PromptUtil.startAlarm(mContext);
                                lo.setNew(false);
                                setData();
                            } else {
                                handler.sendEmptyMessageDelayed(10, 1000);
                            }
                            break;
                        case 11:
                            long ctime = DateUtil.getNowMills();//当前时间
                            long nextTime = DateUtil.parseTimeToMillis(DateUtil.getMingtianDate() + " 00:00:00");
                            if (ctime > jsTime && ctime < nextTime) {
                                handler.sendEmptyMessageDelayed(11, 120000);
                            } else {
                                ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
                                jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间
                                handler.removeCallbacksAndMessages(null);
                                getDjs();
                            }
                            break;

                        default:

                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        public void setData(){
            lottery = app.getLottery();
            String qishu = lottery.getCaiQishu();
            tvKjqs.setText(lottery.getCaiTypeMc() + " 第" + qishu + "期");
            String[] array = lottery.getCaiNumArray();
            if(array != null && array.length >= 5){
                tvKj1.setText(array[0]);
                tvKj2.setText(array[1]);
                tvKj3.setText(array[2]);
                tvKj4.setText(array[3]);
                tvKj5.setText(array[4]);
            }
            tvJifen.setText(app.getUser().getCoin());
            tvDianbi.setText(app.getUser().getMoney());
            ksTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getKjsj());//第一期开始时间
            jsTime = DateUtil.parseTimeToMillis(DateUtil.getCurrentDate() + " " + lottery.getJssj());//最后一期开始时间
            getDjs();
            //进入后1秒钟就去发送这个消息
            handler.sendEmptyMessageDelayed(0,1000);
        }

        private void getDjs(){
            long ctime = DateUtil.getNowMills();//当前时间
            int orderNo = CaiUtil.getCurrentPeriod();
           /* if (ctime > jsTime + 30000) {
                tvDjs.setText("今天开奖已经结束");
                handler.removeCallbacksAndMessages(null);
                return;
            }*/
            if (ctime < ksTime) {
                dt = ksTime - ctime;
            } else {
                long kjsj = (ctime - ksTime) % (20 * 60 * 1000);
                dt = 20 * 60 * 1000 - kjsj;
            }
            /*if (orderNo >= Integer.parseInt(lottery.getCount())) {
                tvDjs.setText("今天开奖已经结束");
                handler.removeCallbacksAndMessages(null);
                return;
            }*/
        }
    }

    class PlanViewHolder extends BseeHolder {
        @Bind(R.id.tv_all_plan)
        TextView tvAllPlan;
        @Bind(R.id.et_content)
        EditText etContent;
        @Bind(R.id.ibt_search)
        ImageButton ibtSearch;
        @Bind(R.id.tv_update_plan)
        TextView tvUpdatePlan;
        @Bind(R.id.lv_plan)
        ListView lvPlan;

        private LvLotteryPlanAdapter lvLotteryPlanAdapter;
        private DialogWiget dialogWiget;
        private String userId;

        PlanViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    switch (msg.what){
//                        case HandlerWhat.GET_NEWPLANLIST_SUCCESS:
//                            Bundle bundle = msg.getData();
//                            if(bundle != null){
//                                List<ResultBean.LotteryPlan>  lists = (List<ResultBean.LotteryPlan>) bundle.getSerializable(ReturnStatus.KEY_GET_NEWPLAN_LIST);
//                                resultBean.setLotteryPlanList(lists);
//                                lvLotteryPlanAdapter.setList(lists);
//                            }else{
//                                Toast.makeText(mContext,"暂未获取到数据", Toast.LENGTH_SHORT).show();
//                            }
//                            handler.sendEmptyMessageDelayed(41, 30000);
//                            break;
                        case HandlerWhat.GET_NEWPLANLISTBYNAME_SUCCESS:
                            Bundle bundle1 = msg.getData();
                            if(bundle1 != null){
                                List<ResultBean.LotteryPlan>  lists = (List<ResultBean.LotteryPlan>) bundle1.getSerializable(ReturnStatus.KEY_GET_NEWPLANBYNAME_LIST);
                                resultBean.setLotteryPlanList(lists);
                                lvLotteryPlanAdapter.setList(lists);
                            }else{
                                Toast.makeText(mContext,"暂未获取到数据", Toast.LENGTH_SHORT).show();
                            }
                            handler.sendEmptyMessageDelayed(41, 30000);
                            break;
                        case 41:
//                            Jc11x5Factory.getInstance().newPlanListByNickName(handler, tvAllPlan.getText().toString(), etContent.getText().toString());
                            break;
                        default:
                            break;
                    }
                }catch (Exception e){

                }

            }
        };

        public void setData(){
            userId = App.getInstance().getUser().getId();
            dialogWiget = new DialogWiget();
            List<ResultBean.LotteryPlan> lists = resultBean.getLotteryPlanList();
            lvLotteryPlanAdapter = new LvLotteryPlanAdapter(mContext, lists, lvPlan);
            lvPlan.setAdapter(lvLotteryPlanAdapter);
            lvPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onGvListeners.onItemClickListener(parent.getAdapter().getItem(position), tvAllPlan.getText().toString());
                }
            });
            tvAllPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCaiZhongList();
                }
            });
            tvUpdatePlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.removeCallbacksAndMessages(null);
                    String caiType = tvAllPlan.getText().toString();
                    String nickName = etContent.getText().toString();
                    Jc11x5Factory.getInstance().newPlanListByNickName(handler, CaiUtil.getCaiBm(caiType), nickName);
                }
            });
            ibtSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler.removeCallbacksAndMessages(null);
                    String caiType = tvAllPlan.getText().toString();
                    String nickName = etContent.getText().toString();
                    Jc11x5Factory.getInstance().newPlanListByNickName(handler, CaiUtil.getCaiBm(caiType), nickName);
                }
            });
            handler.sendEmptyMessageDelayed(41, 30000);
        }

        private void showCaiZhongList() {
            String title = "游戏玩法：";
            List<CaiZhong> lists =  CaiUtil.getCaiZhongList();
            lists.add(0, new CaiZhong("全部方案", "全部方案"));
            final DmAdapter dmadapter = new DmAdapter(mContext, lists);
            dialogWiget.showListview(mContext, title, dmadapter, new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adv, View view, int position, long id) {
                    if (dialogWiget.lDialog != null) {
                        dialogWiget.lDialog.dismiss();
                    }
                    try {
                        CaiZhong entity = (CaiZhong) adv.getAdapter().getItem(position);
                        tvAllPlan.setText(entity.getCaiMc());
                        Jc11x5Factory.getInstance().newPlanListByNickName(handler, entity.getCaiBm(), etContent.getText().toString());
                    } catch (Exception e) {
                    }
                }
            });
        }
    }



    /*private void startActivityWithBundle(Class cls, Bundle extras){
        Intent intent=new Intent();
        if(extras!=null){
            intent.putExtras(extras);
        }
        intent.setClass(mContext, cls);
        mContext.startActivity(intent);
    }*/

    OnGvListener onGvListeners;

    public OnGvListener getOnGvListeners() {
        return onGvListeners;
    }

    public void setOnGvListeners(OnGvListener onGvListeners) {
        this.onGvListeners = onGvListeners;
    }

    public interface OnGvListener {
        void onItemClickListener(Object object);
        void onItemClickListener(Object object, String leixing);
    }



}

package com.jingcai.jc_11x5.db.dao;

import android.text.TextUtils;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;
import com.jingcai.jc_11x5.util.CaiUtil;
import com.jingcai.jc_11x5.util.DateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class KaiJiang_11x5Dao extends BaseDao<KaiJiang_11x5> {

    public KaiJiang_11x5Dao() {
        super();
    }

    @Override
    public Class<KaiJiang_11x5> getEntity() {
        return KaiJiang_11x5.class;
    }

    public boolean addOrUpdate(KaiJiang_11x5 kaiJiang_11x5){
       return addOrUpdate(kaiJiang_11x5, new KaiJiang_11x5Dao());
    }

    public boolean addOrUpdate(KaiJiang_11x5 kaiJiang_11x5, KaiJiang_11x5Dao kaiJiang_11x5Dao){
        if(kaiJiang_11x5.getNum() == null){
            return createOrUpdate(kaiJiang_11x5);
        }
        String zoushi = kaiJiang_11x5.getZouShi();
        boolean isEmptyZoushi = zoushi == null || TextUtils.isEmpty(zoushi);
        if(isEmptyZoushi){
            String caizhong = kaiJiang_11x5.getCaiZhong();
            String orderNum = kaiJiang_11x5.getOrderNum();
            int orderNo = Integer.parseInt(orderNum.substring(orderNum.length()-2, orderNum.length()));
            KaiJiang_11x5 lastKaiJiang_11x5;
            if(orderNo == 1){
                String zuotian = DateUtil.getZuotianDate().replace("-", "");
                String qishu = zuotian.substring(2, zuotian.length()) + String.valueOf(CaiUtil.getCaiCount(caizhong));
                lastKaiJiang_11x5 = queryKaiJiang11x5(caizhong, qishu);
            }else{
                lastKaiJiang_11x5 = queryKaiJiang11x5(caizhong, String.valueOf(Integer.parseInt(orderNum) - 1));
            }
            if (lastKaiJiang_11x5 == null) {
                kaiJiang_11x5.initZouShi(null, null);
                kaiJiang_11x5Dao.createOrUpdate(kaiJiang_11x5);
            }else {
                kaiJiang_11x5.initZouShi(lastKaiJiang_11x5.getZouShi(), lastKaiJiang_11x5.getNum());
            }
        }
        return createOrUpdate(kaiJiang_11x5);
    }



    public boolean isExistOrderNum(String caiZhong, String orderNum){
        try {
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.where().eq("caiZhong", "guangdong").and().eq("orderNum", orderNum);
            List<KaiJiang_11x5> lists = queryBuilder.query();
            int count = (int)queryBuilder.countOf();
            if(count <= 0){
                return false;
            }
        }catch (Exception e){
        }
        return true;
    }

    /**
     * 查询通过期号
     * @param orderNum
     * @return
     */
    public KaiJiang_11x5 queryByPeriod(String orderNum){
        List<KaiJiang_11x5> lists = queryForEq("orderNum", orderNum);
        if(lists != null && lists.size() > 0){
            return lists.get(0);
        }
        return null;
    }

    public KaiJiang_11x5 queryKaiJiang11x5(String caiZhong, String qihao){
        List<KaiJiang_11x5> lists= null;
        try {
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.where().
                    eq("caiZhong", caiZhong).and().
                    eq("orderNum",qihao);
            lists = queryBuilder.query();
            if(lists != null && lists.size() > 0){
                return lists.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<KaiJiang_11x5> queryKaijiangList(String caiZhong){
        try{
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.offset(0L).limit(100L);
            queryBuilder.where().eq("caiZhong", caiZhong);
            queryBuilder.orderBy("time", false).orderBy("orderNum",false);//参数false表示降序，true表示升序。
            List<KaiJiang_11x5> lists = queryBuilder.query();
            if(lists != null && lists.size()>0){
                return lists;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<KaiJiang_11x5> queryKaijiangList1(String caiZhong){
        try{
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.offset(0L).limit(100L);
            queryBuilder.where().eq("caiZhong", caiZhong);
            queryBuilder.orderBy("orderNum",true);//参数false表示降序，true表示升序。
            List<KaiJiang_11x5> lists = queryBuilder.query();
            if(lists != null && lists.size()>0){
                return lists;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public KaiJiang_11x5 queryCurrentKaijiang(){
        try{
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.offset(0L).limit(10L);
            queryBuilder.where().eq("caiZhong", App.getInstance().getUser().getCaizhong());
            queryBuilder.orderBy("time", false).orderBy("orderNum",false);//参数false表示降序，true表示升序。
            List<KaiJiang_11x5> lists = queryBuilder.query();
            if(lists != null && lists.size()>0){
                return lists.get(0);
            }
        }catch (Exception e){
        }
        return null;
    }

    public List<KaiJiang_11x5> getKjByQIshu(String qishu){
        try {
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.offset(0L).limit(Long.parseLong(qishu));
            queryBuilder.where().eq("caiZhong", App.getInstance().getUser().getCaizhong());
            queryBuilder.orderBy("time", false).orderBy("orderNum",false);
            List<KaiJiang_11x5> kjLists = queryBuilder.query();
            return kjLists;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean yichuhaoPaichu(String num, String qishu, String cishu){
        try{
            if(TextUtils.isEmpty(cishu)){
                cishu = "0";
            }
            QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.offset(0L).limit(Long.parseLong(qishu));
            queryBuilder.where().eq("caiZhong", App.getInstance().getUser().getCaizhong());
            queryBuilder.orderBy("time", false).orderBy("orderNum",false);
            List<KaiJiang_11x5> kjLists = queryBuilder.query();
            int count = 0;
            for(KaiJiang_11x5 kaiJiang_11x5: kjLists){
                String sortNum = kaiJiang_11x5.getLuckyNumberSort();
                if(sortNum.equals(num)){
                    count+=1;
                    if(count >= Integer.parseInt(cishu)){
                        return false;
                    }
                }
            }

            /*QueryBuilder queryBuilder = getDao().queryBuilder();
            queryBuilder.offset(0L).limit(Long.parseLong(qishu));
            queryBuilder.where().eq("caiZhong", App.getInstance().getUser().getCaizhong()).and().like("luckyNumberSort", "%"+num+"%");
            queryBuilder.orderBy("time", false).orderBy("orderNum",false);
            List<KaiJiang_11x5> lists = queryBuilder.query();
            if(lists != null && lists.size() > Integer.parseInt(cishu)){
                return false;
            }*/
        }catch (Exception e){
        }
        return true;
    }

}

package com.jingcai.jc_11x5.db.dao;

import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.entity.KaiJiangTime;
import com.jingcai.jc_11x5.util.LogUtil;

import java.util.List;

/**
 * Created by yangsen on 2017/5/28.
 */

public class KaiJiangTimeDao extends BaseDao<KaiJiangTime>  {

    public KaiJiangTimeDao() {
        super();
    }

    @Override
    public Class<KaiJiangTime> getEntity() {
        return KaiJiangTime.class;
    }


    public KaiJiangTime getKaiJiangTime(){
        try {
            List<KaiJiangTime> lists = queryForEq("caizhong", App.getInstance().getUser().getCaizhong());
            if(lists != null && lists.size() > 0){
                return lists.get(0);
            }
        }catch (Exception e){
            LogUtil.debug("KaiJiangTimeDao-->getKaiJiangTime",e.getMessage());
        }
        return null;
    }

}

package com.jingcai.jc_11x5.db.dao;

import android.text.TextUtils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jingcai.jc_11x5.db.SqliteHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * <p>数据库操作基类</p>
 * <p>描述：数据库操作方法封装.</p>
 * <p>系统：移动维保</p>
 * <p>日期：2016-07-20</p>
 * <p>公司：<a href="http://www.xacf.com">西安创富电子科技有限公司</a></p>
 * @version 1.0
 * @author yangsen
 */
public abstract class BaseDao<T> {

    private Dao<T, Integer> mDao;
    private SqliteHelper helper;

    public BaseDao(){
        try {
            helper = helper.getHelper();
            mDao = helper.getDao(getEntity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract Class<T> getEntity() ;

    public Dao<T, Integer> getDao(){
        return mDao;
    }

    /**
     * 创建or更新数据
     * @param t 实体
     * @return
     */
    public boolean createOrUpdate(T t){
        boolean isSuccess = false;
        try {
            Dao.CreateOrUpdateStatus status = getDao().createOrUpdate(t);//传入id则更新
            if (status.isCreated() || status.isUpdated()) {
                isSuccess = true;
            }else{
                isSuccess = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  isSuccess;
    }

    /**
     * 添加数据
     * @param t
     * @return
     */
    public boolean add(T t) {
        boolean flag = false;
        try {
            int result = mDao.create(t);
            if (result > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新数据
     * @param t
     * @return
     */
    public boolean update(T t) {
        boolean flag = false;
        try {
            int result = mDao.update(t);
            if (result > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据ID删除记录
     * @param id
     */
    public void delete(int id){
        try {
            mDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据实体删除记录
     * @param t
     */
    public void delete(T t){
        try {
            mDao.delete(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    public T queryForId(int id) {
        T t;
        try
        {
            t = mDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<T> queryForAll() {
        List<T> list;
        try {
            list = mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 分页查询
     * @param offset 起始行
     * @param limit //表示总共获取的对象数量  limit限制获取指定行数
     * @param order 排序字段
     * @return
     */
    public List<T> queryByLimit(final long offset, final long limit, final String order) {
        List<T> query = null;
        try {
            QueryBuilder queryBuilder = mDao.queryBuilder();
            queryBuilder.offset(offset).limit(limit);
            if(!TextUtils.isEmpty(order)){
                queryBuilder.orderBy(order, false);
            }
            query = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 查询指定字段value等于查询值的行： where fieldName ＝ value
     * @param fieldName
     * @param value
     * @return
     */
    public List<T> queryForEq(String fieldName, Object value){
        List<T> lists = new ArrayList<>();
        try {
            lists = mDao.queryForEq(fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 根据传入的字段与value值的map匹配查询
     * @param fieldValues
     * @return
     */
    public List<T> queryForFieldValues(Map<String, Object> fieldValues){
        List<T> lists = new ArrayList<>();
        try {
            lists = mDao.queryForFieldValues(fieldValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 计算表记录总数
     * @return
     */
    public long  countAll(){
        long  count = 0L;
        try {
            count = mDao.countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 判断表是否存在
     * @return
     */
    public boolean isTableExists(){
        boolean flag = false;
        try {
            flag = mDao.isTableExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void callBatchTasks(final BatchTaskManage batchTaskManage){
        try {
            getDao().callBatchTasks(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    batchTaskManage.batchTask();
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface BatchTaskManage{
        void batchTask() throws Exception;
    }

}

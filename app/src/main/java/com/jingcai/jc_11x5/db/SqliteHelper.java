package com.jingcai.jc_11x5.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jingcai.jc_11x5.app.App;
import com.jingcai.jc_11x5.entity.KaiJiangTime;
import com.jingcai.jc_11x5.entity.KaiJiang_11x5;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FRED on 2016/7/8.
 */
public class SqliteHelper extends OrmLiteSqliteOpenHelper {
    /*数据库配置*/
    public static final String DATABASE_NAME = "jingcai_ds_app.db";
    public static final int DATABASE_VERSION = 1;

    private static SqliteHelper helper;
    private static Context mContext;
    private Map<String, Dao> daos = new HashMap<String, Dao>();


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private Context getContext(){
        if(mContext == null){
            mContext = App.getInstance().getApplicationContext();
        }
        return mContext;
    }

    public File getPath(){
        return getContext().getDatabasePath(DATABASE_NAME);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //初始化建表
        try {
            createTable(connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            if (oldVersion == 1) {
                //getDao(BaoJingConfig.class).executeRaw("ALTER TABLE `jc-11x5_sys_baoJingConfig` ADD COLUMN kaijiangVoice INTEGER DEFAULT 'false';");
            }
            if (oldVersion == 2) {
            }
            if(oldVersion == 7){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(ConnectionSource connectionSource)throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, KaiJiang_11x5.class);
        TableUtils.createTableIfNotExists(connectionSource, KaiJiangTime.class);
    }

    public void clearTable(ConnectionSource connectionSource){
        try {
            TableUtils.clearTable(connectionSource, KaiJiang_11x5.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void claerTable(){
        ConnectionSource connectionSource = helper.getConnectionSource();
        try {
            TableUtils.clearTable(connectionSource, KaiJiang_11x5.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropTable(ConnectionSource connectionSource)throws SQLException {
        TableUtils.dropTable(connectionSource, KaiJiang_11x5.class, true);
        TableUtils.dropTable(connectionSource, KaiJiangTime.class, true);
    }


    public JSONObject deleteDatabase() {
        Context context = getContext();
        JSONObject json = new JSONObject();
        try {
            json.put("isSuccess",false);
            String[] dataList = context.databaseList();
            List<String> dbList = new ArrayList<String>(Arrays.asList(dataList));
            boolean isContain= dbList.contains(DATABASE_NAME);
            json.put("isContain",isContain);
            if(isContain){
                daos.clear();
                dropTable(context);
                helper = null;
                boolean isSuccess = context.deleteDatabase(DATABASE_NAME);
                json.put("isSuccess",isSuccess);
                return json;
            }
            json.put("text", "当前数据库已被删除！");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private void dropTable(Context context){
        try {
            if(helper==null){
                helper = new SqliteHelper(context);
            }
            ConnectionSource connectionSource = helper.getConnectionSource();
            dropTable(connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static synchronized SqliteHelper getHelper() {
        if(mContext == null){
            mContext = App.getInstance().getApplicationContext();
        }
        if (helper == null) {
            synchronized (SqliteHelper.class) {
                helper = new SqliteHelper(mContext);
            }
        }
        return helper;
    }

    public synchronized Dao getDao(Class cls) throws SQLException
    {
        Dao dao = null;
        String className = cls.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(cls);
            daos.put(className, dao);
        }
        if(!dao.isTableExists()){
            TableUtils.createTable(connectionSource, cls);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();
        daos.clear();
    }

}

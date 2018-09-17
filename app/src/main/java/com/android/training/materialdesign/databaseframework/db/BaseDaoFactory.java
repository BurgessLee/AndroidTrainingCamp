package com.android.training.materialdesign.databaseframework.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by king on 2018/9/15.
 */
public class BaseDaoFactory {

    private static final BaseDaoFactory sInstance = new BaseDaoFactory();

    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 定义数据库路径
     * <p>
     * 建议创建到SD卡中，删除应用后，数据依然存在
     */
    private String mSQLiteDatabasePath;

    private BaseDaoFactory() {
        // 判断是否存在SD卡
        mSQLiteDatabasePath = "/data/data/com.android.training/jett.db";

        // 创建数据库文件
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(mSQLiteDatabasePath, null);

    }

    public static BaseDaoFactory getsInstance() {
        return sInstance;
    }

    /**
     * 创建BaseDao实例
     */
    public <T> BaseDao<T> getBaseDao(Class<T> entityClass) {
        BaseDao dao = null;
        try {
            dao = BaseDao.class.newInstance();
            dao.init(mSQLiteDatabase, entityClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return dao;
    }
}

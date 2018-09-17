package com.android.training.materialdesign.databaseframework.db;

import android.database.sqlite.SQLiteDatabase;

import com.android.training.materialdesign.databaseframework.annotation.DbField;
import com.android.training.materialdesign.databaseframework.annotation.DbTable;

import java.lang.reflect.Field;

/**
 * 1、完成自动建表的功能
 */
public class BaseDao<T> implements IBaseDao<T> {

    // 持有数据库操作的引用
    private SQLiteDatabase mSQLiteDatabase;
    // 表名
    private String mTableName;
    // 持有操作数据库对象的Java类型字节码
    private Class<T> mEntityClass;

    // 标识：是否建表
    private boolean mIsInit = false;

    /**
     * 框架内部的逻辑，不对外提供构造方法
     */
    protected boolean init(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        mSQLiteDatabase = sqLiteDatabase;
        mEntityClass = entityClass;

        // 根据传入Class类型建表，只需要创建一次
        if (!mIsInit) {
            // 获取表名
            if (entityClass.getAnnotation(DbTable.class) == null) {
                // 反射获取类名作为表名
                mTableName = entityClass.getSimpleName();
            } else {
                // 获取注解值作为表名
                mTableName = entityClass.getAnnotation(DbTable.class).value();
            }

            // 执行建表操作：create table if not exists tb_user(_id INTEGER, name TEXT, password TEXT)
            if (!sqLiteDatabase.isOpen()) {
                return false;
            }

            sqLiteDatabase.execSQL(getCreateTableSql());

            mIsInit = true;
        }

        return mIsInit;
    }

    /**
     * 创建建表SQL语句
     * <p>
     * create table if not exists tb_user(_id INTEGER, name TEXT, password TEXT)
     */
    private String getCreateTableSql() {
        StringBuffer sql = new StringBuffer();
        sql.append("create table if not exists ");
        sql.append(mTableName);
        sql.append("(");

        // 反射获取字段
        Field[] fields = mEntityClass.getDeclaredFields();
        for (Field field : fields) {
            // 获取字段名
            if (field.getAnnotation(DbField.class) == null) {
                sql.append(field.getName());
            } else {
                sql.append(field.getAnnotation(DbField.class).value());
            }

            // 获取字段类型
            Class type = field.getType();
            if (type == String.class) {
                sql.append(" TEXT,");
            } else if (type == Integer.class) {
                sql.append(" INTEGER,");
            } else if (type == Long.class) {
                sql.append(" BIGINT,");
            } else if (type == Double.class) {
                sql.append(" DOUBLE,");
            } else if (type == byte[].class) {
                sql.append(" BLOB,");
            } else {
                // 不支持的类型
                continue;
            }
        }

        // 删除最后一个逗号
        if (sql.charAt(sql.length() - 1) == ',') {
            sql.deleteCharAt(sql.length() - 1);
        }

        sql.append(")");

        return sql.toString();
    }

    @Override
    public long insert(T entity) {
        return 0;
    }
}

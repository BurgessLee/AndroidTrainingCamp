package com.android.training.materialdesign.databaseframework.db;

/**
 * 规范所有的数据库操作
 */
public interface IBaseDao<T> {

    long insert(T entity);

//    long update(T entity, T where);
//
//    int delete(T where);
//
//    List<T> query(T where);
//
//    List<T> query(T where, String orderBy, int startIndex, int limit);
//
//    List<T> query(String sql);
}

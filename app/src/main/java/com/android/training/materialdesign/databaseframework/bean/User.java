package com.android.training.materialdesign.databaseframework.bean;

import com.android.training.materialdesign.databaseframework.annotation.DbField;
import com.android.training.materialdesign.databaseframework.annotation.DbTable;

/**
 * User表，字段：id name password 使用：api.insert(new User());
 */
@DbTable("tb_user")
public class User {

    @DbField("_id")
    private Integer id;

    private String name;

    private String password;
}

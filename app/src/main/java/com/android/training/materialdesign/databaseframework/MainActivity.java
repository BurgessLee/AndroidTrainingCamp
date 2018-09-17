package com.android.training.materialdesign.databaseframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.training.R;
import com.android.training.materialdesign.databaseframework.bean.User;
import com.android.training.materialdesign.databaseframework.db.BaseDao;
import com.android.training.materialdesign.databaseframework.db.BaseDaoFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDao<User> dao = BaseDaoFactory.getsInstance().getBaseDao(User.class);

                Toast.makeText(MainActivity.this, "插入数据表成功", Toast.LENGTH_LONG).show();
            }
        });
    }
}

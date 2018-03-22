package com.android.training.materialdesign.appcompat;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.training.R;

public class CompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compat);

        Button button = findViewById(R.id.show_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CompatActivity.this);
                builder.setTitle("兼容性对话框");
                builder.setMessage("测试兼容性对话框");
                builder.setPositiveButton("确定", null);
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(50, true);
    }
}

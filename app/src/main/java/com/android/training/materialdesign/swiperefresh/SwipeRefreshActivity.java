package com.android.training.materialdesign.swiperefresh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.android.training.R;

public class SwipeRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefresh;
    private RefreshProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        mSwipeRefresh = findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setSize(SwipeRefreshLayout.DEFAULT);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 下拉完毕，加载数据
                mSwipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        // 设置进度条背景颜色
        mSwipeRefresh.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        // 设置下拉多少距离开始刷新
        mSwipeRefresh.setDistanceToTriggerSync(90);

        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setColorSchemeColors(
                Color.parseColor("#ef2620"), Color.parseColor("#ffa600"),
                Color.parseColor("#ffee33"), Color.parseColor("#20ef35")
        );

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleProgress(mProgressBar.getVisibility() == View.GONE);
            }
        });

        final Button popupButton = findViewById(R.id.button_popup_window);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListPopupWindow popupWindow = new ListPopupWindow(SwipeRefreshActivity.this);
                popupWindow.setAdapter(new ArrayAdapter<String>(SwipeRefreshActivity.this, android.R.layout.simple_list_item_1, new String[]{"条目1", "条目2", "条目3"}));
                popupWindow.setAnchorView(v);
                popupWindow.setWidth(400);
                popupWindow.setHeight(600);
                popupWindow.show();
            }
        });
    }

    private void toggleProgress(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }

        mProgressBar.setRefreshing(show);
    }
}

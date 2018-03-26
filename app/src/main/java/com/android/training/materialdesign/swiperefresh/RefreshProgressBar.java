package com.android.training.materialdesign.swiperefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class RefreshProgressBar extends View {

    private static final float PROGRESS_BAR_HEIGHT = 5f;

    private boolean mIsRefreshing = false;
    private SwipeProgressBar mProgressBar;
    private final int mProgressBarHeight;

    private final Handler mHandler;

    private final Runnable mRefreshUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsRefreshing) {
                mProgressBar.start();
            } else {
                mProgressBar.stop();
            }
        }
    };

    public RefreshProgressBar(Context context) {
        this(context, null, 0);
    }

    public RefreshProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHandler = new Handler();

        if (!isInEditMode()) {
            mProgressBar = new SwipeProgressBar(this);
        }

        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mProgressBarHeight = (int) (metrics.density * PROGRESS_BAR_HEIGHT + 0.5f);
    }

    /**
     * Starts or stops the refresh animation. Animation is stopped by default. After the stop animation has completed,
     * the progress bar will be fully transparent.
     */
    public void setRefreshing(boolean refreshing) {
        if (mIsRefreshing != refreshing) {
            mIsRefreshing = refreshing;
            mHandler.removeCallbacks(mRefreshUpdateRunnable);
            mHandler.post(mRefreshUpdateRunnable);
        }
    }

    /**
     * Set the four colors used in the progress animation.
     */
    public void setColorSchemeColors(int color1, int color2, int color3, int color4) {
        mProgressBar.setColorScheme(color1, color2, color3, color4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), mProgressBarHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mProgressBar != null) {
            mProgressBar.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mProgressBar != null) {
            mProgressBar.draw(canvas);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mHandler.removeCallbacks(mRefreshUpdateRunnable);
        super.onDetachedFromWindow();
    }
}

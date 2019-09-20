package com.cuci.enticement.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.widget.NestedScrollView;

/**
 * Created by Cloud on 2017/12/6.
 */

public class SmoothScrollview extends NestedScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop;

    public SmoothScrollview(Context context) {
        this(context, null);
    }

    public SmoothScrollview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmoothScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public interface onScrollChangedListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    private onScrollChangedListener mOnScrollChangedListener;

    public void setOnScrollChangedListener(onScrollChangedListener onScrollChangedListener) {
        mOnScrollChangedListener = onScrollChangedListener;
    }
}

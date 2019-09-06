package com.example.enticement.utils;

import android.view.View;

public class ViewUtils {

    private ViewUtils() {
    }

    public static void showView(View view) {

        if (view == null)
            return;

        if (view.getVisibility() == View.VISIBLE)
            return;

        view.setVisibility(View.VISIBLE);
    }

    public static void hideView(View view) {

        if (view == null)
            return;

        if (view.getVisibility() == View.GONE)
            return;

        view.setVisibility(View.GONE);
    }

    public static void invisibleView(View view) {

        if (view == null)
            return;

        if (view.getVisibility() == View.INVISIBLE)
            return;

        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 获取控件的高度
     */
    public static int getViewMeasuredHeight(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取控件的宽度
     */
    public static int getViewMeasuredWidth(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件的尺寸
     */
    private static void calculateViewMeasure(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(w, h);
    }



}

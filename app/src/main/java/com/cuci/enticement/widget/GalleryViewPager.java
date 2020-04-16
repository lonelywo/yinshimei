package com.cuci.enticement.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by Cloud on 2017/12/19.
 */

public class GalleryViewPager extends ViewPager {

    private static final float MIN_SCALE = 0.8f;
    private static final float MIN_ALPHA = 0.8f;

    public GalleryViewPager(Context context) {
        this(context, null);
    }

    public GalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new GalleryPageTransformer());
        setOffscreenPageLimit(2);
        setOnHierarchyChangeListener(new OnHierarchyChangeListener() {

            @Override
            public void onChildViewAdded(View parent, View child) {
                child.setScaleY(MIN_SCALE);
                child.setAlpha(MIN_ALPHA);
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }

        });
    }

    public static class GalleryPageTransformer implements PageTransformer {

        @Override
        public void transformPage(View view, float position) {
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setScaleY(MIN_SCALE);
                view.setAlpha(MIN_ALPHA);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                float alphaFactor = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - Math.abs(position));
                view.setScaleY(scaleFactor);
                view.setAlpha(alphaFactor);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                // Counteract the default slide transition
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                float alphaFactor = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - Math.abs(position));
                view.setScaleY(scaleFactor);
                view.setAlpha(alphaFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setScaleY(MIN_SCALE);
                view.setAlpha(MIN_ALPHA);
            }
        }

    }

}

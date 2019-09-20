package com.cuci.enticement.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.cuci.enticement.utils.DimensionUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomBarView extends LinearLayout {

    private int MARGIN_TOP_BOTTOM;
    private int MID_HEIGHT;
    private List<Tab> mTabs;

    public BottomBarView(@NonNull Context context) {
        this(context, null);
    }

    public BottomBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        MARGIN_TOP_BOTTOM = DimensionUtils.dp2px(context, 6);
        MID_HEIGHT = DimensionUtils.dp2px(context, 56);
    }


    public void setTabs(List<Tab> tabs) {
        mTabs = tabs;
//        removeAllViews();
        for (int i = 0; i < tabs.size(); i++) {
            Tab tab = tabs.get(i);

            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams;

            if (tab.mid) {
                layoutParams = new LayoutParams(0, MID_HEIGHT, 1);
                layoutParams.gravity = Gravity.BOTTOM;
            } else {
                layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            }

            layoutParams.setMargins(0, MARGIN_TOP_BOTTOM, 0, MARGIN_TOP_BOTTOM);
            imageView.setLayoutParams(layoutParams);
            imageView.setContentDescription(tab.desc);

            if (i == 0) {
//                Glide.with(getContext()).load(tab.resSelected).into(imageView);
                imageView.setImageResource(tab.resSelected);
            } else {
                imageView.setImageResource(tab.resNormal);
//                Glide.with(getContext()).load(tab.resNormal).into(imageView);
            }

            int finalI = i;
            imageView.setOnClickListener(v -> {

            /*    if (finalI == 4 && ServiceCreator.getInstance().getUserInfo() == null) {
                    ClickHandler.getInstance()
                            .click(getContext(), "B11026");
                    return;
                }*/

                onScaleAnimationBySpring(imageView, tab.mid);

                for (int c = 0; c < tabs.size(); c++) {
                    Tab tab1 = tabs.get(c);
                    ImageView iv = (ImageView) getChildAt(c);
                    if (c != finalI) {
//                        Glide.with(getContext()).load(tab1.resNormal).into(iv);
                        iv.setImageResource(tab1.resNormal);
                    } else {
//                        Glide.with(getContext()).load(tab1.resSelected).into(iv);
                        iv.setImageResource(tab1.resSelected);
                    }
                }

                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onTabClicked(finalI);
                }
            });

            addView(imageView);
        }

    }

    public void setSelected(int position) {

        if (mTabs == null)
            return;

        for (int c = 0; c < mTabs.size(); c++) {
            Tab tab1 = mTabs.get(c);
            ImageView iv = (ImageView) getChildAt(c);
            if (c != position) {
//                Glide.with(getContext()).load(tab1.resNormal).into(iv);
                iv.setImageResource(tab1.resNormal);
            } else {
//                Glide.with(getContext()).load(tab1.resSelected).into(iv);
                iv.setImageResource(tab1.resSelected);
            }
        }

        ImageView imageView = (ImageView) getChildAt(position);
        onScaleAnimationBySpring(imageView, mTabs.get(position).mid);

        if (mOnTabClickListener != null) {
            mOnTabClickListener.onTabClicked(position);
        }
    }

    /**
     * 点击图标抖动效果
     */
    private void onScaleAnimationBySpring(ImageView imageView, boolean mid) {

        ObjectAnimator animatorX;
        ObjectAnimator animatorY;

        if (mid) {
            animatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.1f, 1.0f);
            animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.1f, 1.0f);
        } else {
            animatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.15f, 1.0f);
            animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.15f, 1.0f);
        }

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new SpringScaleInterpolator(0.4f));
        set.playTogether(animatorX, animatorY);
        set.start();
    }

    public interface OnTabClickListener {

        void onTabClicked(int position);

    }

    private OnTabClickListener mOnTabClickListener;

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    public static class Tab {

        public Tab(int resSelected, int resNormal, boolean mid, String desc) {
            this.resSelected = resSelected;
            this.resNormal = resNormal;
            this.mid = mid;
            this.desc = desc;
        }

        public int resSelected;
        public int resNormal;
        public String desc;
        public boolean mid;
    }

}

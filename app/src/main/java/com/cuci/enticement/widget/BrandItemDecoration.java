package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.utils.DimensionUtils;

public class BrandItemDecoration extends RecyclerView.ItemDecoration {

    private int mVSpace;//纵向间距
    private int mHSpace;//横向间距

    public BrandItemDecoration(Context context, int space) {
        mVSpace = DimensionUtils.dp2px(context, space);
    }
    public BrandItemDecoration(Context context, int space,int hSpace) {
        mVSpace = DimensionUtils.dp2px(context, space);
        mHSpace = DimensionUtils.dp2px(context, hSpace);
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        outRect.set(mVSpace, mHSpace, mVSpace, mHSpace);


    }
}

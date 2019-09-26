package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.utils.DimensionUtils;


public class CartItemDecoration extends RecyclerView.ItemDecoration {

    private int mVSpace;//纵向间距
    private int mHSpace;//横向间距

    public CartItemDecoration(Context context, int space) {
        mVSpace = DimensionUtils.dp2px(context, space);
    }

    public CartItemDecoration(Context context, int vSpace,int hSpace) {
        mVSpace = DimensionUtils.dp2px(context, vSpace);
        mHSpace = DimensionUtils.dp2px(context, hSpace);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if(position==0){
            outRect.set(mHSpace, mVSpace, mHSpace, mVSpace);
        }else {
            outRect.set(mHSpace, 0, mHSpace, mVSpace);
        }



    }
}

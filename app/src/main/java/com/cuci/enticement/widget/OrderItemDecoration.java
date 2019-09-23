package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import com.cuci.enticement.utils.DimensionUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class OrderItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public OrderItemDecoration(Context context, int space) {
        mSpace = DimensionUtils.dp2px(context, space);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {


        outRect.set(0, 0, 0, mSpace);


    }
}
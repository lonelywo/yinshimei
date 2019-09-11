package com.example.enticement.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enticement.utils.DimensionUtils;


public class CartItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public CartItemDecoration(Context context, int space) {
        mSpace = DimensionUtils.dp2px(context, space);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {


        outRect.set(mSpace, 0, mSpace, 0);


    }
}

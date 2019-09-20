package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.utils.DimensionUtils;


/**
 * 为首页定制的分割线
 */
public class HomeGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = HomeGridItemDecoration.class.getSimpleName();

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    Context context;
    private int headerCount = 5;

    public void setHeaderCount(int headerCount) {
        this.headerCount = headerCount;
    }

    public HomeGridItemDecoration(Context context, int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.context=context;
        this.spacing = DimensionUtils.dp2px(context, spacing);
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {

        int p = parent.getChildAdapterPosition(view);

        if (p < headerCount) {
           // super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, DimensionUtils.dp2px(context, 6));
            return;
        }

        int position = parent.getChildAdapterPosition(view) + headerCount;

        int column = position % spanCount;

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount;
            outRect.right = (column + 1) * spacing / spanCount;
            if (position < spanCount) {
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        } else {
            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            if (position >= spanCount) {
                outRect.top = spacing;
            }
        }
    }

}

package com.example.enticement.widget;

import androidx.recyclerview.widget.GridLayoutManager;

public class HomeListSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int mSize;
    private GridLayoutManager mGridLayoutManager;

    public HomeListSpanSizeLookup(GridLayoutManager gridLayoutManager, int size) {
        mSize = size;
        mGridLayoutManager = gridLayoutManager;
    }

    public void setSize(int size) {
        mSize = size;
    }

    @Override
    public int getSpanSize(int position) {
        if (position < mSize)
            return mGridLayoutManager.getSpanCount();
        return 1;
    }
}

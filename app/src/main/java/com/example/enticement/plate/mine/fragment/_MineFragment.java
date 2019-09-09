package com.example.enticement.plate.mine.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;

/**
 * 首页外层Fragment
 */
public class _MineFragment extends BaseFragment  {

    private static final String TAG = _MineFragment.class.getSimpleName();





    private boolean mCouldChange = true;


    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
   //     mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);




    }


    private void load() {
      //  mViewModel.getSplash().observe(this, mObserver);
    }









}

package com.example.enticement.plate.home.activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseActivity;
import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.utils.FToast;

public class ProdActivity extends BaseActivity {
    private static final String TAG = ProdActivity.class.getSimpleName();
    private BannerDataBean mData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_prod;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }

        mData = intent.getParcelableExtra("bannerData");

        if (mData == null) {
            FToast.error("数据错误");
            return;
        }

    }


}

package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class YinshimeiActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_yinshimei;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }



    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}

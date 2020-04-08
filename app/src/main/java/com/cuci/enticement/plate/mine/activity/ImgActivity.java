package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.TuiImgBean;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.utils.ImageLoader;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImgActivity extends BaseActivity {
    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.ll_buju)
    LinearLayout llBuju;
    @Override
    public int getLayoutId() {
        return R.layout.activity_img;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        imgTupian.setImageBitmap(BasicApp.Constant_PZ_IMG );

        llBuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

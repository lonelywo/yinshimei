package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.plate.common.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuiHuanSuccessActivity extends BaseActivity {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.line)
    Guideline line;
    @BindView(R.id.tv_shangcheng)
    TextView tvShangcheng;
    @BindView(R.id.tv_jilu)
    TextView tvJilu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_duihuansuccess;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.image_back, R.id.tv_shangcheng, R.id.tv_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_shangcheng:
                Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_HOME);
                LocalBroadcastManager.getInstance(this)
                        .sendBroadcast(intentRank);
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.tv_jilu:
                startActivity(new Intent(this, DuiHuanJiLuActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

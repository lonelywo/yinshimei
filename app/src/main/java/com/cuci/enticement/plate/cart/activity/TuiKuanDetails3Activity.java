package com.cuci.enticement.plate.cart.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.plate.common.MainActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 退款详情页
 */
public class TuiKuanDetails3Activity extends BaseActivity {

    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_zhuangtai)
    TextView textZhuangtai;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.con_zhuangtai)
    ConstraintLayout conZhuangtai;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.line2)
    View line2;
    private AllOrderList.DataBean.ListBeanX mInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.tuikuan_details3;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

    }

    private void initContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(mInfo.getExpress_name()).append(" ")
                .append(mInfo.getExpress_phone()).append(" ").append("\n")
                .append(mInfo.getExpress_province()).append(" ")
                .append(mInfo.getExpress_city()).append(" ")
                .append(mInfo.getExpress_area()).append(" ")
                .append(mInfo.getExpress_address());


    }
    @Override
    public void onBackPressed() {
        Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_MINE);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intentRank);
        startActivity(new Intent(this, MainActivity.class));
        // super.onBackPressed();
    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_MINE);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intentRank);
        startActivity(new Intent(this, MainActivity.class));
    }
}

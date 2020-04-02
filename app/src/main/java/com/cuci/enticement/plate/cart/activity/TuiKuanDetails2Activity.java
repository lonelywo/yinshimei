package com.cuci.enticement.plate.cart.activity;


import android.content.Intent;
import android.os.Bundle;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.OrderGoods;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;


/**
 * 退款详情页
 */
public class TuiKuanDetails2Activity extends BaseActivity  {

    private AllOrderList.DataBean.ListBeanX mInfo;
    private String refund_id;

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
        return R.layout.tuikuan_details2;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        refund_id = intent.getStringExtra("refund_id");
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

}

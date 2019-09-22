package com.cuci.enticement.plate.cart.activity;

import android.os.Bundle;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;

/**
 * 订单详情页
 */
public class OrderDetailsActivity extends BaseActivity {



    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;

    private int mPayType=1;

    private AllOrderList.DataBean.ListBeanX   mInfo;
    @Override
    public int getLayoutId() {
        return R.layout.order_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {



    }
}

package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.mall.fragment._MallFragment;
import com.cuci.enticement.plate.mall.vm.MallViewModel;
import com.cuci.enticement.plate.mine.fragment._OrderFragment01;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {
    private static final String TAG = _MallFragment.class.getSimpleName();

    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.status_view)
    MultipleStatusView mStatusView;

    private boolean mCouldChange = true;
    private MallViewModel mViewModel;

    List<String> data = new ArrayList<String>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_myorder;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int cur = intent.getIntExtra("cur", 0);
        data.add("全部");
        data.add("待付款");
        data.add("待发货");
        data.add("待收货");
        data.add("退款/售后");

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        String[] titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {

            titles[i] = data.get(i);
        }
        adapter.addFragment(_OrderFragment01.newInstance(""));
        adapter.addFragment(_OrderFragment01.newInstance("2"));
        adapter.addFragment(_OrderFragment01.newInstance("3"));
        adapter.addFragment(_OrderFragment01.newInstance("4"));
        adapter.addFragment(_OrderFragment01.newInstance("5"));
        mViewPager.setOffscreenPageLimit(data.size() - 1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager, titles);
        mViewPager.setCurrentItem(cur);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });



    }





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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderEventMessage(OrderEvent event) {
        if(event.getCode()==OrderEvent.INTENT_MY_ORDER){
            mViewPager.setCurrentItem(0);
        }
        if(event.getCode()==OrderEvent.INTENT_YIWANCHENG){
            mViewPager.setCurrentItem(4);
        }

    }







    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}

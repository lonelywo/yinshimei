package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.mall.fragment._MallFragment;
import com.cuci.enticement.plate.mall.fragment._MallFragment01;
import com.cuci.enticement.plate.mall.vm.MallViewModel;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyOrderActivity extends BaseActivity {
    private static final String TAG = _MallFragment.class.getSimpleName();
    @BindView(R.id.image_top)
    TextView textTop;
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

    String [] titles={"全部","待付款","待发货","待收货","已完成"};
    List<String> data = new ArrayList<String>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_myorder;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        load();
    }


    private void load() {
        data.add("全部");
        data.add("待付款");
        data.add("待发货");
        data.add("待收货");
        data.add("已完成");

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        String[] titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            adapter.addFragment(_MallFragment01.newInstance(data.get(i)));
            titles[i] = data.get(i);
        }
        mViewPager.setOffscreenPageLimit(data.size() - 1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager, titles);
        mStatusView.showContent();
    }

}

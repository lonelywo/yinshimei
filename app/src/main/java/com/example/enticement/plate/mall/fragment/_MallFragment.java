package com.example.enticement.plate.mall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.bean.BaseList;
import com.example.enticement.bean.ItemBanner;
import com.example.enticement.bean.MallSourceBean;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.common.adapter.MainPagerAdapter;
import com.example.enticement.plate.home.fragment._HomeFragment;
import com.example.enticement.plate.mall.vm.MallViewModel;
import com.example.enticement.utils.FToast;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 首页外层Fragment
 */
public class _MallFragment extends BaseFragment {

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


    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
             mViewModel = ViewModelProviders.of(this).get(MallViewModel.class);
            mStatusView.setOnRetryClickListener(v -> {
                load();
        });
    }

    private void load() {
          mViewModel.getSource("产品图","1","20").observe(this, mObserver);
    }
    private Observer<Status<MallSourceBean>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                MallSourceBean bean = status.content;
                List<String> data = bean.getData().getTypes();
                MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager());
                String[] titles = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    adapter.addFragment(_MallFragment01.newInstance(data.get(i)));
                    titles[i] = data.get(i);
                }
                mViewPager.setOffscreenPageLimit(data.size() - 1);
                mViewPager.setAdapter(adapter);
                mTabLayout.setViewPager(mViewPager, titles);
                mStatusView.showContent();
                break;
            case Status.LOADING:
                mStatusView.showLoading();
                break;
            case Status.ERROR:
                mStatusView.showError();
                FToast.error(status.message);

                break;
        }
    };

}

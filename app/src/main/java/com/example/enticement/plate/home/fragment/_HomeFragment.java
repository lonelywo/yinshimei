package com.example.enticement.plate.home.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.bean.BaseList;

import com.example.enticement.bean.ItemBanner;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.home.adapter.ItemBannerViewBinder;
import com.example.enticement.plate.home.vm.HomeViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.widget.CustomRefreshHeader;
import com.example.enticement.widget.HomeGridItemDecoration;
import com.example.enticement.widget.HomeListSpanSizeLookup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 首页外层Fragment
 */
public class _HomeFragment extends BaseFragment  implements ItemBannerViewBinder.OnBannerClickListener , OnRefreshLoadMoreListener {

    private static final String TAG = _HomeFragment.class.getSimpleName();




    //private HomeViewModel mViewModel;
    private Drawable mOriginalDrawable;

    private boolean mCouldChange = true;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private HomeGridItemDecoration mDecoration;
    private GridLayoutManager mLayoutManager;
    private HomeListSpanSizeLookup mSizeLookup;
    private HomeViewModel mViewModel;
    private boolean mCanLoadMore = true;

//    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
       mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
     //   mOriginalDrawable = ContextCompat.getDrawable(mActivity, R.drawable.img_top_bar_small);
        mRecyclerView = view.findViewById(R.id.rec_goods);
        mRefreshLayout = view.findViewById(R.id.refresh_home);

//
//        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ACTION_NEW_MESSAGE);
//        mLocalBroadcastManager.registerReceiver(mReceiver, filter);

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);

        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter.register(ItemBanner.class, new ItemBannerViewBinder(this));
       // mAdapter.register(GoodsItem.class, new ItemGoodsLongViewBinder());

        mDecoration = new HomeGridItemDecoration(mActivity, 2, 6, true);
        mDecoration.setHeaderCount(7);

        mRecyclerView.addItemDecoration(mDecoration);

        mLayoutManager = new GridLayoutManager(mActivity, 2);
        mSizeLookup = new HomeListSpanSizeLookup(mLayoutManager, 11);
        mLayoutManager.setSpanSizeLookup(mSizeLookup);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




    }




    @Override
    public void onBannerClick(BannerDataBean bannerDataBean) {

    }

    @Override
    public void onBannerChange(BannerDataBean bannerDataBean) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.getBanner().observe(_HomeFragment.this, mObserver);
        } else {
            mRefreshLayout.finishLoadMore();
        }
    }
    private void load() {
        mViewModel.getBanner().observe(this, mObserver);
    }
    private Observer<Status<BaseList<BannerDataBean>>>  mObserver = new Observer<Status<BaseList<BannerDataBean>>>() {

        @Override
        public void onChanged(Status<BaseList<BannerDataBean>> baseListStatus) {
            switch (baseListStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    BaseList<BannerDataBean> list = baseListStatus.content;
                    if (list == null) {
                        mRefreshLayout.finishLoadMore();
                        return;
                    }
                    if (list.code == 1) {
                        List<BannerDataBean> items = list.data;
                        ItemBanner itemBanner = new ItemBanner(items);
                        mItems.add(itemBanner);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        FToast.error(list.msg);
                    }
                    mRefreshLayout.finishLoadMore();
                    break;
                case Status.ERROR:
                    mRefreshLayout.finishLoadMore();
                    FToast.error(baseListStatus.message);
                    break;
            }
            mCanLoadMore = true;
        }
    };

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
/*    private Observer<Status<Splash>> mObserver = splashStatus -> {
        switch (splashStatus.status) {
            case Status.LOADING:
                mStatusView.showLoading();
                break;
            case Status.SUCCESS:
                mStatusView.showContent();
                Splash splash = splashStatus.content;
                if (splash == null) return;
                SharedPrefUtils.saveUserAgreement(splash.getUserAgreement());
                initTopTab(splash);
                saveSplash(splash);
                break;
            case Status.ERROR:
                mStatusView.showError();
                break;
        }
    };*/







}

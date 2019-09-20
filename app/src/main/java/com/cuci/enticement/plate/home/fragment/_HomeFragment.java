package com.cuci.enticement.plate.home.fragment;

import android.content.Intent;
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
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.BannerDataBean;
import com.cuci.enticement.bean.BaseList;

import com.cuci.enticement.bean.GeneralGoods;
import com.cuci.enticement.bean.GeneralGoodsItem;
import com.cuci.enticement.bean.ItemBanner;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.adapter.ItemBannerViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemGoodsLongViewBinder;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.HomeGridItemDecoration;
import com.cuci.enticement.widget.HomeListSpanSizeLookup;
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
    private int mMinId = 1;
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
        mRecyclerView = view.findViewById(R.id.rec_goods);
        mRefreshLayout = view.findViewById(R.id.refresh_home);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
       // mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter.register(ItemBanner.class, new ItemBannerViewBinder(this));
        mAdapter.register(GeneralGoodsItem.class, new ItemGoodsLongViewBinder(mActivity));

        mDecoration = new HomeGridItemDecoration(mActivity, 2, 6, true);
        mDecoration.setHeaderCount(1);

        mRecyclerView.addItemDecoration(mDecoration);

        mLayoutManager = new GridLayoutManager(mActivity, 2);
        mSizeLookup = new HomeListSpanSizeLookup(mLayoutManager, 1);
        mLayoutManager.setSpanSizeLookup(mSizeLookup);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




    }




    @Override
    public void onBannerClick(BannerDataBean bannerDataBean) {
        Intent intentProd = new Intent(mActivity, ProdActivity.class);
        intentProd.putExtra("bannerData", bannerDataBean.getUrl());
        mActivity.startActivity(intentProd);
    }

    @Override
    public void onBannerChange(BannerDataBean bannerDataBean) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
       /* if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.getGeneralGoods( mMinId, Status.LOAD_MORE).observe(this, GoodsmObserver);
        } else {*/
            mRefreshLayout.finishLoadMore();
     //   }
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
                        mItems.clear();
                        mItems.add(itemBanner);

                        mViewModel.getGeneralGoods( mMinId, Status.LOAD_REFRESH).observe(_HomeFragment.this, GoodsmObserver);
                    } else {
                        FToast.error(list.info);
                    }
                    mRefreshLayout.finishLoadMore();
                    break;
                case Status.ERROR:
                    mRefreshLayout.finishRefresh();

                    FToast.error("网络请求失败");
                    break;
            }
            mCanLoadMore = true;
        }
    };

    private Observer<Status<GeneralGoods>> GoodsmObserver = new Observer<Status<GeneralGoods>>() {
        @Override
        public void onChanged(Status<GeneralGoods> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    GeneralGoods data = status.content;
                    List<GeneralGoodsItem> list = data.getData().getList();
                    if (data == null) {
                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }

                    if (data.getCode() == 1) {
                        mCanLoadMore = true;
                        if (status.loadType == Status.LOAD_REFRESH) {
                            mItems.addAll(list);
                            mAdapter.notifyDataSetChanged();
                            mRefreshLayout.finishRefresh();
                        } else {
                            int o = mItems.size();
                            mItems.addAll(list);
                            int c = mItems.size();
                            mAdapter.notifyItemRangeInserted(o, c);
                            mRefreshLayout.finishLoadMore();
                        }
                    } else {
                        if (status.loadType == Status.LOAD_MORE) {
                            mCanLoadMore = true;
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        FToast.error(data.getInfo());
                    }
                    break;
                case Status.ERROR:
                    FToast.error(status.message);
                    if (status.loadType == Status.LOAD_MORE) {
                        mCanLoadMore = true;
                        mRefreshLayout.finishLoadMore();
                    } else {
                        mRefreshLayout.finishRefresh();
                    }
                    break;
                case Status.LOADING:

                    break;
            }
        }
    };
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        mMinId=1;
        mViewModel.getBanner().observe(this, mObserver);

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

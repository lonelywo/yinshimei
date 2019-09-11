package com.example.enticement.plate.cart.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.GeneralGoods;
import com.example.enticement.bean.GeneralGoodsItem;
import com.example.enticement.bean.ItemBanner;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.cart.adapter.ItemCartViewBinder;
import com.example.enticement.plate.cart.vm.CartViewModel;
import com.example.enticement.plate.home.adapter.ItemBannerViewBinder;
import com.example.enticement.plate.home.adapter.ItemGoodsLongViewBinder;
import com.example.enticement.utils.FToast;
import com.example.enticement.widget.CartItemDecoration;
import com.example.enticement.widget.HomeGridItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 购物车
 */
public class _CartFragment extends BaseFragment implements OnRefreshLoadMoreListener {

    private static final String TAG = _CartFragment.class.getSimpleName();


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private boolean mCanLoadMore=true;
   private CartViewModel mViewModel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onLazyLoad() {

        mRefreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
      mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

        mAdapter.register(CartListBean.DataBean.ListBean.class, new ItemCartViewBinder());
        CartItemDecoration mDecoration = new CartItemDecoration(mActivity, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager=new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void load() {

        mViewModel.getCartList("","37","", Status.LOAD_REFRESH).observe(this, mObserver);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore){
            mCanLoadMore = false;
            mViewModel.getCartList("", "37","",
                    Status.LOAD_MORE).observe(this, mObserver);
        }else {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }

    private Observer<Status<CartListBean>> mObserver = new Observer<Status<CartListBean>>() {
        @Override
        public void onChanged(Status<CartListBean> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    CartListBean data = status.content;
                    List<CartListBean.DataBean.ListBean> list = data.getData().getList();
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






}

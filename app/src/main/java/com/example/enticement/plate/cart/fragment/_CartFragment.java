package com.example.enticement.plate.cart.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.CartListBean;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.OrderActivity;
import com.example.enticement.plate.cart.adapter.ItemCartViewBinder;
import com.example.enticement.plate.cart.vm.CartViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.widget.CartItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 购物车
 */
public class _CartFragment extends BaseFragment implements ItemCartViewBinder.OnItemClickListener, OnRefreshLoadMoreListener {

    private static final String TAG = _CartFragment.class.getSimpleName();

    @BindView(R.id.rb_check_all)
    CheckBox mRbCheckAll;
    @BindView(R.id.recycler_view)
    SwipeRecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_total_money)
    TextView mTvTotal;
    @BindView(R.id.status_view)
    MultipleStatusView mStatusView;

    private boolean mCanLoadMore = true;
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

        mAdapter.register(CartListBean.DataBean.ListBean.class, new ItemCartViewBinder(this));
        CartItemDecoration mDecoration = new CartItemDecoration(mActivity, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRbCheckAll.setOnCheckedChangeListener((buttonView, isChecked) -> checkAll(isChecked));
        mRecyclerView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。

        mRecyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。


    }

    OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            // 此方法在Item拖拽交换位置时被调用。
            // 第一个参数是要交换为之的Item，第二个是目标位置的Item。

            // 交换数据，并更新adapter。
            int fromPosition = srcHolder.getAdapterPosition();
            int toPosition = targetHolder.getAdapterPosition();
          //  Collections.swap(mDataList, fromPosition, toPosition);
            mAdapter.notifyItemMoved(fromPosition, toPosition);

            // 返回true，表示数据交换成功，ItemView可以交换位置。
            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            // 此方法在Item在侧滑删除时被调用。

            // 从数据源移除该Item对应的数据，并刷新Adapter。
            int position = srcHolder.getAdapterPosition();
          //  mDataList.remove(position);
            mAdapter.notifyItemRemoved(position);
        }
    };
    private void load() {

        mViewModel.getCartList("", "37", "", Status.LOAD_REFRESH).observe(this, mObserver);

    }




    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.getCartList("", "37", "",
                    Status.LOAD_MORE).observe(this, mObserver);
        } else {
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
                        mStatusView.showEmpty();
                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }
                    mStatusView.showContent();
                    if (data.getCode() == 1) {
                        mCanLoadMore = true;
                        if (status.loadType == Status.LOAD_REFRESH) {
                            mItems.clear();
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


    private void checkAll(boolean checkAll) {
        if(mItems.size()==0){
            return;
        }
        for (int i = 0; i < mItems.size(); i++) {
            CartListBean.DataBean.ListBean item = (CartListBean.DataBean.ListBean) mItems.get(i);
            item.setCheck(checkAll);
        }
        mAdapter.notifyDataSetChanged();
        if (checkAll) {
            mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
        } else {
            mTvTotal.setText("0.00");
        }
    }


    private List<CartListBean.DataBean.ListBean> getCheckeds() {
        List<CartListBean.DataBean.ListBean> items = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            CartListBean.DataBean.ListBean item = (CartListBean.DataBean.ListBean) mItems.get(i);
            if (item.isCheck()) {
                items.add(item);
            }
        }
        return items;
    }


    private float getCheckedsMoeny() {

        float totalF = 0;
        for (int i = 0; i < mItems.size(); i++) {
            CartListBean.DataBean.ListBean item = (CartListBean.DataBean.ListBean) mItems.get(i);
            if (item.isCheck()) {
                float itemMoeny = Float.parseFloat(item.getGoods_price_selling());
                totalF = totalF + item.getGoods_num() * itemMoeny;
            }
        }
        return totalF;
    }

    private boolean mCanChange=true;
    private String mGoodsId;
    @Override
    public void onAddClick(CartListBean.DataBean.ListBean bean) {
        //点击一次加1一次
        if(mCanChange){
            int goods_num = bean.getGoods_num()+1;
            String goods_id = bean.getGoods_id();
            String goods_spec = bean.getGoods_spec();
            mGoodsId=goods_id;
            mViewModel.cartChange("", "37", goods_id,goods_spec,String.valueOf(goods_num)).observe(this, mChangeObserver);

        }
    }

    @Override
    public void onMinusClick(CartListBean.DataBean.ListBean bean) {
        if(mCanChange) {
            int goods_num = bean.getGoods_num() - 1;
            String goods_id = bean.getGoods_id();
            String goods_spec = bean.getGoods_spec();
            mGoodsId=goods_id;
            mViewModel.cartChange("", "37", goods_id, goods_spec, String.valueOf(goods_num)).observe(this, mChangeObserver);
        }

    }


    @Override
    public void onCheckedChange() {
        int checkSize = getCheckeds().size();
        int allSize = mItems.size();

        if (checkSize == allSize) {
            mRbCheckAll.setChecked(true);
        }

        mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
    }





    private Observer<Status<CartListBean>> mChangeObserver = new Observer<Status<CartListBean>>() {
        @Override
        public void onChanged(Status<CartListBean> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    CartListBean data = status.content;
                    List<CartListBean.DataBean.ListBean> list = data.getData().getList();
                    if (data == null) {
                        mStatusView.showEmpty();
                        mCanChange = true;
                        return;
                    }
                    mStatusView.showContent();
                    if (data.getCode() == 1) {

                        mCanChange = true;
                        int changeIndex=0;
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).getGoods_id().equals(mGoodsId)){
                                changeIndex=i;
                                break;
                            }
                        }
                        mItems.clear();
                        mItems.addAll(list);
                        mAdapter.notifyItemChanged(changeIndex);

                    } else {
                        mCanChange = true;
                        FToast.error(data.getInfo());
                    }
                    break;
                case Status.ERROR:

                    FToast.error(status.message);
                    mCanChange = true;
                    break;
                case Status.LOADING:

                    break;
            }
        }
    };



    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        mViewModel.commitOrder("", "37", "","").observe(this, mCommitObserver);

    }


    private Observer<Status<OrderResult>> mCommitObserver = new Observer<Status<OrderResult>>() {
        @Override
        public void onChanged(Status<OrderResult> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    OrderResult content = status.content;
                    if(content.getCode()==1){
                        Intent intent = new Intent(mActivity, OrderActivity.class);
                        startActivity(intent);
                        //刷新视图并跳转
                        mViewModel.getCartList("", "37", "", Status.LOAD_REFRESH).observe(mActivity, mObserver);
                    }else {
                        FToast.warning(content.getInfo());
                    }

                    break;
                case Status.ERROR:
                    FToast.error(status.message);

                    break;
                case Status.LOADING:

                    break;
            }
        }
    };


}

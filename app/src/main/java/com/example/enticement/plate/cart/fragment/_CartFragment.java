package com.example.enticement.plate.cart.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.CartDataBean;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.plate.cart.adapter.ItemCartViewBinder;
import com.example.enticement.plate.cart.vm.CartViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.SharedPrefUtils;
import com.example.enticement.widget.CartItemDecoration;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import java.util.ArrayList;
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
    public static final String ACTION_REFRESH_DATA = "com.yinshimei.plate.common.ACTION_REFRESH_DATA";
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
    private LocalBroadcastManager mLocalBroadcastManager;
    private UserInfo mUserInfo;
    private int mPage=1;
    @Override
    protected void onLazyLoad() {
        if(mUserInfo==null){
            return;
        }
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);

        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

        mAdapter.register(CartDataBean.ListBean.class, new ItemCartViewBinder(this));
        CartItemDecoration mDecoration = new CartItemDecoration(mActivity, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRbCheckAll.setOnCheckedChangeListener((buttonView, isChecked) -> checkAll(isChecked));
        mRecyclerView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。

        mRecyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。


        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_REFRESH_DATA);
        mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter);

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





    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if(intent.getAction().equals(ACTION_REFRESH_DATA)){
                    mRefreshLayout.autoRefresh();
                }

            }
        }
    };






    private void load() {

        if(mUserInfo==null){

            return;
        }
        mUserInfo = SharedPrefUtils.get(UserInfo.class);

        mViewModel.getCartList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", Status.LOAD_REFRESH).observe(this, mObserver);

    }




    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mUserInfo==null){
            mRefreshLayout.finishLoadMore();
            return;
        }
        if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.getCartList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(mPage),
                    Status.LOAD_MORE).observe(this, mObserver);
        } else {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }

    private Observer<Status<Base<CartDataBean>>> mObserver = new Observer<Status<Base<CartDataBean>>>() {
        @Override
        public void onChanged(Status<Base<CartDataBean>> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    mStatusView.showContent();

                    Base<CartDataBean> content = status.content;
                    CartDataBean data = content.data;
                    String s = new Gson().toJson(data);
                    if (data.getList() == null) {
                        mStatusView.showEmpty();
                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }

                    List<CartDataBean.ListBean> list = data.getList();
                   if (status.content.code == 1) {
                        mCanLoadMore = true;
                        mPage= (int) (data.getPage().getCurrent()+1);
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
                        FToast.error(content.info);
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
            CartDataBean.ListBean item = (CartDataBean.ListBean) mItems.get(i);
            item.setCheck(checkAll);
        }
        mAdapter.notifyDataSetChanged();
        if (checkAll) {
            mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
        } else {
            mTvTotal.setText("0.00");
        }
    }


    private List<CartDataBean.ListBean> getCheckeds() {
        List<CartDataBean.ListBean> items = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            CartDataBean.ListBean item = (CartDataBean.ListBean) mItems.get(i);
            if (item.isCheck()) {
                items.add(item);
            }
        }
        return items;
    }


    private double getCheckedsMoeny() {

        double totalF = 0;
        for (int i = 0; i < mItems.size(); i++) {
            CartDataBean.ListBean item = (CartDataBean.ListBean) mItems.get(i);
            if (item.isCheck()) {
                double itemMoeny = Double.parseDouble(item.getGoods_price_selling());
                totalF = totalF + item.getGoods_num() * itemMoeny;
            }
        }
        return totalF;
    }

    private boolean mCanChange=true;
    private long mGoodsId;
    @Override
    public void onAddClick(CartDataBean.ListBean bean) {
        //点击一次加1一次
        if(mCanChange){
            int goods_num = bean.getGoods_num()+1;
            String goods_id = String.valueOf(bean.getGoods_id());
            String goods_spec = bean.getGoods_spec();
            mGoodsId=bean.getGoods_id();
            mCanChange=false;
            mViewModel.cartChange(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), goods_id,goods_spec,String.valueOf(goods_num)).observe(this, mChangeObserver);

        }
    }

    @Override
    public void onMinusClick(CartDataBean.ListBean bean) {
        if(mCanChange) {
            int goods_num = bean.getGoods_num() - 1;
            String goods_id = String.valueOf(bean.getGoods_id());
            String goods_spec = bean.getGoods_spec();
            mGoodsId=bean.getGoods_id();
            mCanChange=false;
            mViewModel.cartChange(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), goods_id, goods_spec, String.valueOf(goods_num)).observe(this, mChangeObserver);
        }

    }




    @Override
    public void onCheckedChange() {
        int checkSize = getCheckeds().size();
        int allSize = mItems.size();

        if (checkSize == allSize) {
            mRbCheckAll.setChecked(true);
        }else {
            mRbCheckAll.setChecked(false);
        }

        mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
    }


    /**
     * 购物车修改结果
     */
    private Observer<Status<Base>> mChangeObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> status) {
            switch (status.status) {

                case Status.SUCCESS:
               /*     Base<CartDataBean> content = status.content;
                    CartDataBean data = status.content.data;

                    if (data == null) {
                        mStatusView.showEmpty();
                        mCanChange = true;
                        return;
                    }
                    mStatusView.showContent();
                    List<CartDataBean.ListBean> list = data.getList();
                    if (status.content.code == 1) {

                        mCanChange = true;
                        int changeIndex=0;
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).getGoods_id()==mGoodsId){
                                changeIndex=i;
                                break;
                            }
                        }
                        mItems.clear();
                        mItems.addAll(list);
                        mAdapter.notifyItemChanged(changeIndex);

                    } else {
                        mCanChange = true;
                        FToast.error(content.info);
                    }*/
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
        List<CartDataBean.ListBean> items = (List<CartDataBean.ListBean>) mAdapter.getItems();


        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            CartDataBean.ListBean listBean = items.get(i);
            sb.append(listBean.getGoods_id()).append("@")
                    .append(listBean.getGoods_spec()).append("@")
                    .append(listBean.getGoods_num());
            if(items.size()>1&&i!=items.size()-1){
                sb.append("||");
            }

        }
        String s = sb.toString();
        mViewModel.commitOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), sb.toString(),"").observe(this, mCommitObserver);

    }


    private Observer<Status<OrderResult>> mCommitObserver = new Observer<Status<OrderResult>>() {
        @Override
        public void onChanged(Status<OrderResult> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    OrderResult content = status.content;
                    String s = new Gson().toJson(content);
                    if(content.getCode()==1){
                        //清空视图并跳转
                        mItems.clear();
                        mAdapter.notifyDataSetChanged();

                      /*  Intent intent = new Intent(mActivity, OrderActivity.class);
                        intent.putExtra("order",co ntent.getData().getOrder());
                        startActivity(intent);*/


                    }else {
                        FToast.error(content.getInfo());
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

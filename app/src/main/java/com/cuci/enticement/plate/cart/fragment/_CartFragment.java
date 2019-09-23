package com.cuci.enticement.plate.cart.fragment;


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
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.CartDataBean;
import com.cuci.enticement.bean.CartDelete;
import com.cuci.enticement.bean.CartIntentInfo;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.cart.adapter.ItemCartViewBinder;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

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
    private int mPosition;
    @Override
    protected void onLazyLoad() {
        if(mUserInfo==null){
            mStatusView.showEmpty();
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

        mAdapter.register(OrderGoods.class, new ItemCartViewBinder(this));
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
           /* int fromPosition = srcHolder.getAdapterPosition();
            int toPosition = targetHolder.getAdapterPosition();
          //  Collections.swap(mDataList, fromPosition, toPosition);
            mAdapter.notifyItemMoved(fromPosition, toPosition);*/

            // 返回true，表示数据交换成功，ItemView可以交换位置。
            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            // 此方法在Item在侧滑删除时被调用。

            // 从数据源移除该Item对应的数据，并刷新Adapter。
             mPosition = srcHolder.getAdapterPosition();
            if(mCanChange) {

                mCanChange=false;
                OrderGoods bean = (OrderGoods) mAdapter.getItems().get(mPosition);
                int cart_id = bean.getCart_id();
                mViewModel.cartDelete(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),String.valueOf(cart_id) )
                        .observe(mActivity, mDeleteObserver);
            }

        }
    };





    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if(intent.getAction().equals(ACTION_REFRESH_DATA)){
                    //刷新购物车列表
                    mUserInfo = SharedPrefUtils.get(UserInfo.class);
                    if(mUserInfo==null){
                        mStatusView.showEmpty();
                        return;
                    }
                    mRefreshLayout.autoRefresh();
                }

            }
        }
    };






    private void load() {

        mViewModel.getCartList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", Status.LOAD_REFRESH).observe(this, mObserver);

    }




    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        mUserInfo = SharedPrefUtils.get(UserInfo.class);

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


                    Base<CartDataBean> content = status.content;
                    if (content == null) {
                            mStatusView.showEmpty();
                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }
                    CartDataBean data = content.data;
                    //  String s = new Gson().toJson(data);
                    if (data.getList() == null) {
                        if(mAdapter.getItemCount()==0){
                            mStatusView.showEmpty();
                        }

                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }

                    mStatusView.showContent();


                    mPage=data.getPage().getCurrent()+1;
                    List<OrderGoods> list = data.getList();
                   if (status.content.code == 1) {
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
            OrderGoods item = (OrderGoods) mItems.get(i);
            item.setCheck(checkAll);
        }
        mAdapter.notifyDataSetChanged();
        if (checkAll) {
            mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
        } else {
            mTvTotal.setText("0.00");
        }
    }


    private List<OrderGoods> getCheckeds() {
        List<OrderGoods> items = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            OrderGoods item = (OrderGoods) mItems.get(i);
            if (item.isCheck()) {
                items.add(item);
            }
        }
        return items;
    }


    private double getCheckedsMoeny() {

        double totalF = 0;
        for (int i = 0; i < mItems.size(); i++) {
            OrderGoods item = (OrderGoods) mItems.get(i);
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
    public void onAddClick(OrderGoods bean) {
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
    public void onMinusClick(OrderGoods bean) {
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

    @Override
    public void onDelete(OrderGoods bean) {

    }


    /**
     * 购物车删除
     */
    private Observer<Status<ResponseBody>> mDeleteObserver = new Observer<Status<ResponseBody>>() {
        @Override
        public void onChanged(Status<ResponseBody> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    ResponseBody content = status.content;
                    try {
                        String result = content.string();
                        CartDelete cartDelete = new Gson().fromJson(result, CartDelete.class);
                        if(cartDelete.code==1){
                            mAdapter.notifyItemRemoved(mPosition);
                            FToast.success(cartDelete.info);
                            if(mAdapter.getItemCount()==0){
                                mStatusView.showEmpty();
                            }
                        }else {
                            FToast.warning(cartDelete.info);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
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
                    List<OrderGoods> list = data.getList();
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
        List<OrderGoods> checkeds = getCheckeds();
      //  List<OrderGoods> items = (List<OrderGoods>) mAdapter.getItems();

        if(checkeds.size()==0){
            FToast.warning("请先选择要结算的商品");
            return;
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < checkeds.size(); i++) {
            OrderGoods listBean = checkeds.get(i);
            sb.append(listBean.getGoods_id()).append("@")
                    .append(listBean.getGoods_spec()).append("@")
                    .append(listBean.getGoods_num());
            if(checkeds.size()>1&&i!=checkeds.size()-1){
                sb.append("||");
            }

        }
        String s = sb.toString();
       mViewModel.commitOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), sb.toString(),"")
               .observe(this, mCommitObserver);


    }


    private Observer<Status<ResponseBody>> mCommitObserver = new Observer<Status<ResponseBody>>() {
        @Override
        public void onChanged(Status<ResponseBody> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    ResponseBody content = status.content;
                    try {
                        String result = content.string();
                        OrderResult orderResult = new Gson().fromJson(result, OrderResult.class);
                        if(orderResult.getCode()==1){
                            //跳转

                            List<OrderGoods> items = (List<OrderGoods>) mAdapter.getItems();
                            AllOrderList.DataBean.ListBeanX cartIntentInfo=new AllOrderList.DataBean.ListBeanX();

                            cartIntentInfo.setOrder_no(Long.parseLong(orderResult.getData().getOrder().getOrder_no()));
                            cartIntentInfo.setList(items);
                            cartIntentInfo.setGoods_count(items.size());
                            cartIntentInfo.setPrice_goods(mTvTotal.getText().toString());
                            Intent intent = new Intent(mActivity, OrderActivity.class);
                            intent.putExtra("intentInfo",cartIntentInfo);
                            startActivity(intent);


                        }else {
                            FToast.warning(orderResult.getInfo());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
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

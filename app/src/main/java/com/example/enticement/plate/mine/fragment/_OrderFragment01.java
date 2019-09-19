package com.example.enticement.plate.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;

import com.example.enticement.bean.GeneralGoodsItem;
import com.example.enticement.bean.ItemOrderBottom;
import com.example.enticement.bean.ItemOrderTitle;
import com.example.enticement.bean.MallSourceBean;
import com.example.enticement.bean.OrderList;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.plate.cart.activity.OrderActivity;
import com.example.enticement.plate.cart.adapter.ItemCartViewBinder;
import com.example.enticement.plate.mall.adapter.NineAdapter;
import com.example.enticement.plate.mall.vm.MallViewModel;
import com.example.enticement.plate.mine.adapter.ItemBottomViewBinder;
import com.example.enticement.plate.mine.adapter.ItemProdViewBinder;
import com.example.enticement.plate.mine.adapter.ItemTitleViewBinder;
import com.example.enticement.plate.mine.vm.OrderViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.SharedPrefUtils;
import com.example.enticement.widget.CartItemDecoration;
import com.example.enticement.widget.CustomRefreshHeader;
import com.example.enticement.widget.GridItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 首页外层Fragment
 */
public class _OrderFragment01 extends BaseFragment implements OnRefreshLoadMoreListener, ItemProdViewBinder.OnProdClickListener
, ItemBottomViewBinder.OnItemClickListener {

    private static final String TAG = _OrderFragment01.class.getSimpleName();

    private boolean mCanLoadMore = true;
    private OrderViewModel mViewModel;
    private int page=1;
    private String mtype;
    private final String PAGE_SIZE="20";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.image_top)
    ImageView mIvTop;
    private CartItemDecoration mDecoration;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private  List<OrderList.DataBean.OrderBean> mDatas=new ArrayList<>();
    private  UserInfo mUserInfo;

    public static _OrderFragment01 newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        _OrderFragment01 fragment = new _OrderFragment01();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onLazyLoad() {
        mRefreshLayout.autoRefresh();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_source01;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mtype = bundle.getString("type");

        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo==null){
            return;
        }

        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

       // mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new GridLayoutManager(mActivity, 2);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(ItemOrderTitle.class, new ItemTitleViewBinder());
        mAdapter.register(OrderList.DataBean.OrderBean.GoodsBean.class, new ItemProdViewBinder(this));
        mAdapter.register(ItemOrderBottom.class, new ItemBottomViewBinder(this));
        CartItemDecoration mDecoration = new CartItemDecoration(mActivity, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void load() {
        mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"",mtype,"",Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }




    private Observer<Status<OrderList>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                OrderList bean = status.content;

                if (bean.getCode() == 1) {
                    OrderList.DataBean data = bean.getData();
                    List<OrderList.DataBean.OrderBean> item = data.getList();

                    if (item == null || item.size() == 0) {
                        if (status.loadType == Status.LOAD_REFRESH) {

                            mRefreshLayout.finishRefresh();
                        } else {

                            mRefreshLayout.finishLoadMore();
                        }

                        return;
                    }

                    page = status.content.getData().getPage().getCurrent()+1;
                    mCanLoadMore = true;

                    if (status.loadType == Status.LOAD_REFRESH) {
                        mDatas.clear();
                        mDatas.addAll(item);


                        mItems.clear();
                        addOrderItem(item);
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.finishRefresh();
                    } else {
                        mDatas.addAll(item);

                        addOrderItem(item);
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.finishLoadMore();
                    }

                } else {
                    if (status.loadType == Status.LOAD_MORE) {
                        mCanLoadMore = true;
                        mRefreshLayout.finishLoadMore();
                    } else {

                        mRefreshLayout.finishRefresh();
                    }
                    FToast.error(bean.getInfo());
                }
                break;
            case Status.LOADING:

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
        }
    };


    /**
     * 添加订单item
     * @param item
     */
    private void addOrderItem(List<OrderList.DataBean.OrderBean> item) {
        for (int i = 0; i <item.size() ; i++) {
            OrderList.DataBean.OrderBean orderBean = item.get(i);
            ItemOrderTitle itemOrderTitle = new ItemOrderTitle(String.valueOf(orderBean.getOrder_no()), orderBean.getStatus());
            mItems.add(itemOrderTitle);
            List<OrderList.DataBean.OrderBean.GoodsBean> goodsBeanList = orderBean.getList();
            for (int j = 0; j < goodsBeanList.size(); j++) {
                mItems.add(goodsBeanList.get(j));
            }
            ItemOrderBottom itemOrderBottom = new ItemOrderBottom();
            mItems.add(itemOrderBottom);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore=true){
            mCanLoadMore = false;
            mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"",mtype,"",Status.LOAD_MORE).observe(this, mObserver);

        }else {
            mRefreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }



    /**
     * 查看订单详情
     */
    @Override
    public void onProdClick(OrderList.DataBean.OrderBean.GoodsBean item) {
        Intent intent = new Intent(mActivity, OrderActivity.class);
        int curOrder=0;
        for (int i = 0; i < mDatas.size(); i++) {
            long order_no = mDatas.get(i).getOrder_no();
            if(order_no==item.getOrder_no()){
                curOrder=i;
                break;
            }
        }
        OrderList.DataBean.OrderBean orderBean = mDatas.get(curOrder);
        intent.putExtra("order",orderBean);
        startActivity(intent);
    }


    /**
     * 取消订单
     * @param itemOrderBottom
     */
    @Override
    public void onCancel(ItemOrderBottom itemOrderBottom) {
        mViewModel.orderCancel("","",itemOrderBottom.orderNum).observe(this, mResultObserver);
    }


    /**
     * 立即支付
     * @param itemOrderBottom
     */
    @Override
    public void onPay(ItemOrderBottom itemOrderBottom) {
        mViewModel.orderConfirm("","",itemOrderBottom.orderNum).observe(this, mResultObserver);
    }

    /**
     * 确认收货
     * @param itemOrderBottom
     */
    @Override
    public void onConfirmGoods(ItemOrderBottom itemOrderBottom) {

    }


    /**
     * 查看物流
     * @param itemOrderBottom
     */
    @Override
    public void onViewLogistics(ItemOrderBottom itemOrderBottom) {

    }




    private Observer<Status<OrderResult>> mResultObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                OrderResult content = status.content;
                if(content.getCode()==1){

                }

                break;
            case Status.LOADING:

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
        }
    };




}

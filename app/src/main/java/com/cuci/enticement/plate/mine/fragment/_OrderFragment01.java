package com.cuci.enticement.plate.mine.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;

import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.OrderCancel;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderList;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.cart.activity.OrderDetailsActivity;
import com.cuci.enticement.plate.common.LoginActivity;
import com.cuci.enticement.plate.common.eventbus.MessageEvent1;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.mine.adapter.ItemBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemProdViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemTitleViewBinder;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

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
    private  List<AllOrderList.DataBean.ListBeanX> mDatas=new ArrayList<>();
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

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //注册布局
        mAdapter.register(ItemOrderTitle.class, new ItemTitleViewBinder());

        mAdapter.register(OrderGoods.class, new ItemProdViewBinder(this));

        mAdapter.register(ItemOrderBottom.class, new ItemBottomViewBinder(this));
        OrderItemDecoration mDecoration = new OrderItemDecoration(mActivity, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void load() {

        mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"1",mtype,"",Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }




    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    if(mtype.equals("2")){

                        Log.d("east", ": "+result);
                    }
                    AllOrderList allOrderList = new Gson().fromJson(result, AllOrderList.class);

                    if (allOrderList.getCode() == 1) {
                        AllOrderList.DataBean data = allOrderList.getData();
                        List<AllOrderList.DataBean.ListBeanX> item = data.getList();

                        if (item == null ) {
                            if (status.loadType == Status.LOAD_REFRESH) {

                                mRefreshLayout.finishRefresh();
                            } else {

                                mRefreshLayout.finishLoadMore();
                            }

                            return;
                        }

                        page = data.getPage().getCurrent()+1;
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
                            int o = mItems.size();
                            addOrderItem(item);
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
                        FToast.error(allOrderList.getInfo());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



                //  OrderList bean = status.content;

                /**/
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
    private void addOrderItem(List<AllOrderList.DataBean.ListBeanX> item) {

        for (int i = 0; i <item.size() ; i++) {
            AllOrderList.DataBean.ListBeanX orderBean = item.get(i);
            int cur=mItems.size();
            ItemOrderTitle itemOrderTitle = new ItemOrderTitle(String.valueOf(orderBean.getOrder_no()), orderBean.getStatus(),cur);
            mItems.add(itemOrderTitle);
            List<OrderGoods> goodsBeanList = orderBean.getList();
            mItems.addAll(goodsBeanList);
            int curBottom=mItems.size();
            ItemOrderBottom itemOrderBottom = new ItemOrderBottom();
            itemOrderBottom.status=orderBean.getStatus();
            itemOrderBottom.orderNum=String.valueOf(orderBean.getOrder_no());
            itemOrderBottom.bottomcur=curBottom;
            itemOrderBottom.topCur=cur;
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


/*
    *//**
     * 查看订单详情
     *//*
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
    }*/



    private ItemOrderBottom  mCancelItem;

    @Override
    public void onReBuy(ItemOrderBottom itemOrderBottom) {

        String orderNum = itemOrderBottom.orderNum;
        int cur=0;
        for (int i = 0; i < mDatas.size(); i++) {
            AllOrderList.DataBean.ListBeanX listBeanX = mDatas.get(i);
            if(orderNum.equals(String.valueOf(listBeanX.getOrder_no()))){
                        cur=i;
                break;
            }
        }
        AllOrderList.DataBean.ListBeanX cartIntentInfo = mDatas.get(cur);

        Intent intent = new Intent(mActivity, OrderActivity.class);
        intent.putExtra("intentInfo",cartIntentInfo);
        startActivity(intent);



    }

    /**
     * 取消订单
     * @param itemOrderBottom
     */
    @Override
    public void onCancel(ItemOrderBottom itemOrderBottom) {

        String orderNum = itemOrderBottom.orderNum;
        mCancelItem=itemOrderBottom;
        mViewModel.orderCancel(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),orderNum).observe(this, mCancelObserver);
    }


    /**
     * 立即支付
     * @param itemOrderBottom
     */
    @Override
    public void onPay(ItemOrderBottom itemOrderBottom) {


        String orderNum = itemOrderBottom.orderNum;
        int cur=0;
        for (int i = 0; i < mDatas.size(); i++) {
            AllOrderList.DataBean.ListBeanX listBeanX = mDatas.get(i);
            if(orderNum.equals(String.valueOf(listBeanX.getOrder_no()))){
                cur=i;
                break;
            }
        }
        AllOrderList.DataBean.ListBeanX cartIntentInfo = mDatas.get(cur);

        Intent intent = new Intent(mActivity, OrderActivity.class);
        intent.putExtra("intentInfo",cartIntentInfo);
        startActivity(intent);


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

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onOrderEventMessage(OrderEvent event) {
        if(event.getCode()==OrderEvent.CANCEL_ORDER){
            if(mtype.equals("")){
                mRefreshLayout.autoRefresh();
            }
        }else if(event.getCode()==OrderEvent.REFRESH_OUTSIDE){
            mRefreshLayout.autoRefresh();
        }


    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }



    private Observer<Status<ResponseBody>> mCancelObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    OrderCancel orderCancel = new Gson().fromJson(result, OrderCancel.class);
                    if(orderCancel.getCode()==1){
                        if(mtype.equals("2")||mtype.equals("3")){
                            //待支付或者待发货页面  局部删除
                           int count= mCancelItem.bottomcur-mCancelItem.topCur+1;
                            Items items = new Items();
                            for (int i = mCancelItem.topCur; i <= mCancelItem.bottomcur; i++) {
                                items.add(mItems.get(i));
                            }
                            mItems.removeAll(items);
                            mAdapter.notifyItemRangeRemoved(mCancelItem.topCur,count);

                           // mAdapter.notifyItemRemoved();



                            EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.CANCEL_ORDER));

                            /* mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"1",mtype,"",Status.LOAD_REFRESH)
                                .observe(this, mObserver);*/
                        }else {
                            //全部页面  局部刷新
                            ItemOrderTitle titleBean = (ItemOrderTitle) mItems.get(mCancelItem.topCur);
                            titleBean.setStatus(0);
                            ItemOrderBottom bottomBean = (ItemOrderBottom) mItems.get(mCancelItem.bottomcur);
                            bottomBean.status=0;
                            mAdapter.notifyItemChanged(mCancelItem.topCur,titleBean);
                            mAdapter.notifyItemChanged(mCancelItem.bottomcur,bottomBean);
                        }

                        //刷新状态
                        Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);

                        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);


                        FToast.success(orderCancel.getInfo());
                    }else {
                        FToast.error("订单取消失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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






    @Override
    public void onProdClick(OrderGoods item) {
        Intent intent = new Intent(mActivity, OrderDetailsActivity.class);
        int curOrder=0;
        for (int i = 0; i < mDatas.size(); i++) {
            long order_no = mDatas.get(i).getOrder_no();
            if(order_no==item.getOrder_no()){
                curOrder=i;
                break;
            }
        }
        AllOrderList.DataBean.ListBeanX orderBean = mDatas.get(curOrder);

        intent.putExtra("intentInfo",orderBean);
        startActivity(intent);
    }
}

package com.cuci.enticement.plate.cart.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.cuci.enticement.bean.CartChange;
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
import com.cuci.enticement.plate.common.eventbus.AddressEvent;
import com.cuci.enticement.plate.common.eventbus.CartEvent;
import com.cuci.enticement.plate.common.popup.CartTipsPopup;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.SlideRecyclerView;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

import static androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance;

/**
 * 购物车
 */
public class _CartFragment extends BaseFragment implements ItemCartViewBinder.OnItemClickListener, OnRefreshLoadMoreListener {

    private static final String TAG = _CartFragment.class.getSimpleName();
    public static final String ACTION_REFRESH_DATA = "com.yinshimei.plate.common.ACTION_REFRESH_DATA";
    @BindView(R.id.rb_check_all)
    CheckBox mRbCheckAll;
    @BindView(R.id.recycler_view)
    SlideRecyclerView mRecyclerView;
    //SlideRecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_total_money)
    TextView mTvTotal;
    @BindView(R.id.status_view)
    MultipleStatusView mStatusView;
    @BindView(R.id.bottom)
    ConstraintLayout bottomLyaout;
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
            ViewUtils.hideView(bottomLyaout);
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
        CartItemDecoration mDecoration = new CartItemDecoration(mActivity, 10,4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRbCheckAll.setOnCheckedChangeListener((buttonView, isChecked) -> checkAll(isChecked));

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_REFRESH_DATA);
        mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter);



        mRecyclerView.setAdapter(mAdapter);


    }






    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onCartEventMessage(CartEvent event) {
        if(event.getCode()==CartEvent.REFRESH_CART_LIST){
            mUserInfo = SharedPrefUtils.get(UserInfo.class);
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
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }



    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if(intent.getAction().equals(ACTION_REFRESH_DATA)){
                    //刷新购物车列表
                /*    mUserInfo = SharedPrefUtils.get(UserInfo.class);
                    if(mUserInfo==null){
                        mStatusView.showEmpty();
                        return;
                    }
                    load();*/
                }

            }
        }
    };






    private void load() {

        mViewModel.getCartList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", Status.LOAD_REFRESH)
                .observe(this, mObserver);

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

                    mStatusView.showContent();

                    mRbCheckAll.setChecked(false);

                    Base<CartDataBean> content = status.content;
                    if (content == null) {

                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            ViewUtils.hideView(bottomLyaout);
                            mStatusView.showEmpty();
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }

                    CartDataBean data = content.data;
                    String s = new Gson().toJson(data);
                    Log.d("east", "onChanged: "+s);
                    if (data.getList() == null||data.getList().size()==0) {


                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            ViewUtils.hideView(bottomLyaout);
                            mStatusView.showEmpty();

                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }

                    mStatusView.showContent();

                    ViewUtils.showView(bottomLyaout);
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
                        //删除商品刷新视图和底部总价
                        mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
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
        if(isItemCheck&&!checkAll){
            isItemCheck=false;
            return;
        }
        isItemCheck=false;//不能删
        if(mItems.size()==0){

            return;
        }
        //某次操作 item true    全选状态
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


    private boolean isItemCheck;
    @Override
    public void onCheckedChange(int position) {

        int checkSize = getCheckeds().size();
        int allSize = mItems.size();


        if (checkSize == allSize) {
            isItemCheck=false;
            mRbCheckAll.setChecked(true);
        }else {

            isItemCheck=true;

            mRbCheckAll.setChecked(false);
        }

        mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
    }


    private boolean mCanChange=true;
    private long mGoodsId;

    @Override
    public void onAddClick(OrderGoods b,int position) {
        //点击一次加1一次
        OrderGoods bean = (OrderGoods)mItems.get(position);
        if(mCanChange){


            String goods_id = String.valueOf(bean.getGoods_id());
            String goods_spec = bean.getGoods_spec();
            mGoodsId=bean.getGoods_id();
            mCanChange=false;

            mAdapter.notifyItemChanged(position);
            mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
            mViewModel.cartChange(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),
                    goods_id,goods_spec,String.valueOf(bean.getGoods_num())).observe(this, mChangeObserver);
            Log.d("east", "onAddClick: "+bean.getGoods_num());
        }
    }


    @Override
    public void onMinusClick(OrderGoods b,int position) {
        OrderGoods bean = (OrderGoods)mItems.get(position);
        if(mCanChange) {



            String goods_id = String.valueOf(bean.getGoods_id());
            String goods_spec = bean.getGoods_spec();
            mGoodsId=bean.getGoods_id();

            mCanChange=false;

            mAdapter.notifyItemChanged(position);

            mTvTotal.setText(String.format(Locale.CHINA, "%s", getCheckedsMoeny()));
            mViewModel.cartChange(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), goods_id,
                    goods_spec, String.valueOf(bean.getGoods_num())).observe(this, mChangeObserver);
            Log.d("east", "onMinusClick: "+bean.getGoods_num());
        }

    }






    @Override
    public void onDelete(OrderGoods b,int position) {
        Log.d("east", "ondelete: "+new Gson().toJson(b));
        OrderGoods bean = (OrderGoods)mItems.get(position);
        new XPopup.Builder(mActivity)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CartTipsPopup(mActivity,
                        "亲，确定要删除此商品吗？", "取消", "确定", new CartTipsPopup.OnExitListener() {
                    @Override
                    public void onPositive() {
                        mPosition=position;
                        Log.d("east", "onDelete: "+mPosition);
                        int cart_id = bean.getCart_id();
                        Log.d("east", "cart_id: "+cart_id);

                        mViewModel.cartDelete(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),String.valueOf(cart_id) )
                                .observe(mActivity, mDeleteObserver);
                    }

                    @Override
                    public void onCancel() {
                        mRecyclerView.closeMenu();
                    }
                }))
                .show();




    }


    /**
     * 购物车删除
     */
    private Observer<Status<ResponseBody>> mDeleteObserver = new Observer<Status<ResponseBody>>() {
        @Override
        public void onChanged(Status<ResponseBody> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    mStatusView.showContent();
                    mRecyclerView.closeMenu();
                    mCanChange=true;
                    ResponseBody content = status.content;
                    try {
                        String result = content.string();
                        CartDelete cartDelete = new Gson().fromJson(result, CartDelete.class);
                        if(cartDelete.code==1){
                            // mItems.remove(mPosition);
                            // mRefreshLayout.autoRefresh();
                            mViewModel.getCartList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", Status.LOAD_REFRESH)
                                    .observe(mActivity, mObserver);

                            //    mAdapter.notifyItemRemoved(mPosition);

                            FToast.success(cartDelete.info);
                            if(mAdapter.getItemCount()==0){
                                ViewUtils.hideView(bottomLyaout);
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
                    mRecyclerView.closeMenu();
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
    private Observer<Status<ResponseBody>> mChangeObserver = new Observer<Status<ResponseBody>>() {
        @Override
        public void onChanged(Status<ResponseBody> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    mStatusView.showContent();
                    mCanChange = true;
                    ResponseBody content = status.content;
                    try {
                        String result = content.string();
                        CartChange bean = new Gson().fromJson(result, CartChange.class);

                        if (bean.getCode() == 1) {



                        } else {
                            FToast.warning(bean.getInfo());
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
        //  String s = sb.toString();
        mViewModel.commitOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), sb.toString(),"")
                .observe(this, mCommitObserver);


    }


    private Observer<Status<ResponseBody>> mCommitObserver = new Observer<Status<ResponseBody>>() {
        @Override
        public void onChanged(Status<ResponseBody> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    mStatusView.showContent();
                    ResponseBody content = status.content;
                    try {
                        String result = content.string();
                        OrderResult orderResult = new Gson().fromJson(result, OrderResult.class);
                        if(orderResult.getCode()==1){
                            //跳转

                            List<OrderGoods> items = getCheckeds();
                            AllOrderList.DataBean.ListBeanX cartIntentInfo=new AllOrderList.DataBean.ListBeanX();

                            cartIntentInfo.setOrder_no(Long.parseLong(orderResult.getData().getOrder().getOrder_no()));
                            cartIntentInfo.setList(items);
                            cartIntentInfo.setGoods_count(items.size());
                            cartIntentInfo.setPrice_goods(mTvTotal.getText().toString());
                            Intent intent = new Intent(mActivity, OrderActivity.class);
                            intent.putExtra("intentInfo",cartIntentInfo);
                            startActivity(intent);


                        }else {
                            FToast.error(orderResult.getInfo());
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
package com.cuci.enticement.plate.cart.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.adapter.ItemProdAfterViewBinder;
import com.cuci.enticement.plate.mall.fragment._MallFragment;
import com.cuci.enticement.plate.mine.adapter.ItemBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemProdViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemTitleViewBinder;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.OrderItemTopDecoration;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class AftermarketActivity extends BaseActivity implements OnRefreshLoadMoreListener,ItemTitleViewBinder.OnProdTitleClickListener,ItemProdAfterViewBinder.OnProdClickListener {
    private static final String TAG = _MallFragment.class.getSimpleName();
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.image_top)
    ImageView imageTop;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyLayout;
    private OrderViewModel mViewModel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private UserInfo mUserInfo;
    private boolean mCanLoadMore = true;
    private String mtype="6";
    private int page=1;
    private  List<AllOrderList.DataBean.ListBeanX> mDatas=new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_aftermarket;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //注册布局
        mAdapter.register(ItemOrderTitle.class, new ItemTitleViewBinder(this));
        mAdapter.register(OrderGoods.class, new ItemProdAfterViewBinder(this));

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        OrderItemTopDecoration mDecoration = new OrderItemTopDecoration(this, 4);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
        load();
    }

    private void load() {

        mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"1",mtype,"",""+ AppUtils.getVersionCode(BasicApp.getContext()), Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }


    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    if(mtype.equals("6")){
                        Log.d("east", ": "+result);
                    }
                    AllOrderList allOrderList = new Gson().fromJson(result, AllOrderList.class);

                    if (allOrderList.getCode() == 1) {
                        AllOrderList.DataBean data = allOrderList.getData();
                        List<AllOrderList.DataBean.ListBeanX> item = data.getList();

                        if (item == null ) {
                            if (status.loadType == Status.LOAD_REFRESH) {

                                ViewUtils.showView(mEmptyLayout);

                                mRefreshLayout.finishRefresh();
                            } else {

                                mRefreshLayout.finishLoadMore();
                            }

                            return;
                        }

                        page = data.getPage().getCurrent()+1;
                        mCanLoadMore = true;

                        if (status.loadType == Status.LOAD_REFRESH) {

                            if (item.size() > 0) {
                                ViewUtils.hideView(mEmptyLayout);

                                mDatas.clear();
                                mDatas.addAll(item);
                                mItems.clear();
                                addOrderItem(item);
                                mAdapter.notifyDataSetChanged();
                                mRefreshLayout.finishRefresh();
                            } else {
                                ViewUtils.showView(mEmptyLayout);
                            }


                        } else {


                            mDatas.addAll(item);

                            addOrderItem(item);
                            int o = mItems.size();
                            addOrderItem(item);
                            int c = mItems.size();
                            mAdapter.notifyItemRangeInserted(o, c);

                            mRefreshLayout.finishLoadMore();
                        }

                    }else if(allOrderList.getCode() == HttpUtils.CODE_INVALID){
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(allOrderList.getInfo());
                    } else {
                        if (status.loadType == Status.LOAD_MORE) {
                            mCanLoadMore = true;
                            mRefreshLayout.finishLoadMore();
                        } else {
                            ViewUtils.showView(mEmptyLayout);
                            mRefreshLayout.finishRefresh();
                        }
                        FToast.error(allOrderList.getInfo());
                    }
                } catch (Exception e) {
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

        }

    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore=true){
            mCanLoadMore = false;
            mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),String.valueOf(page),mtype,"",""+ AppUtils.getVersionCode(BasicApp.getContext()),Status.LOAD_MORE).observe(this, mObserver);

        }else {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }

    @Override
    public void onProdClick(OrderGoods item) {

    }

    @Override
    public void onProdTitleClick(ItemOrderTitle item) {

    }
}

package com.cuci.enticement.plate.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.LogisticsActivity;
import com.cuci.enticement.plate.mine.adapter.ItemReceiveBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemReceiveTitleViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemReceiveViewBinder;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.FToast;
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
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 首页外层Fragment
 */
public class _ShareliwuFragment01 extends BaseFragment implements OnRefreshLoadMoreListener, ItemReceiveBottomViewBinder.OnItemClickListener {

    private static final String TAG = _ShareliwuFragment01.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.image_top)
    ImageView imageTop;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyLayout;


    private boolean mCanLoadMore = true;
    private OrderViewModel mViewModel;
    private int page = 1;
    private String mtype;
    private final String PAGE_SIZE = "20";


    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private List<AllOrderList.DataBean.ListBeanX> mDatas = new ArrayList<>();
    private UserInfo mUserInfo;

    public static _ShareliwuFragment01 newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        _ShareliwuFragment01 fragment = new _ShareliwuFragment01();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onLazyLoad() {
        mRefreshLayout.autoRefresh();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shareliwu;

    }


    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mtype = bundle.getString("type");

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo == null) {
            return;
        }
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //注册布局
        mAdapter.register(ItemOrderTitle.class, new ItemReceiveTitleViewBinder());

        mAdapter.register(OrderGoods.class, new ItemReceiveViewBinder());

        mAdapter.register(ItemOrderBottom.class, new ItemReceiveBottomViewBinder(this));

        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        OrderItemTopDecoration mDecoration = new OrderItemTopDecoration(mActivity, 4);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void load() {
        String token = mUserInfo.getToken();
        int id = mUserInfo.getId();
        mRefreshLayout.finishRefresh();
       /* mViewModel.getOrderList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", mtype, "", Status.LOAD_REFRESH)
          .observe(this, mObserver);*/
    }

    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    if (mtype.equals("2")) {

                        Log.d("east", ": " + result);
                    }
                    AllOrderList allOrderList = new Gson().fromJson(result, AllOrderList.class);

                    if (allOrderList.getCode() == 1) {
                        AllOrderList.DataBean data = allOrderList.getData();
                        List<AllOrderList.DataBean.ListBeanX> item = data.getList();

                        if (item == null) {
                            if (status.loadType == Status.LOAD_REFRESH) {

                                ViewUtils.showView(mEmptyLayout);

                                mRefreshLayout.finishRefresh();
                            } else {

                                mRefreshLayout.finishLoadMore();
                            }

                            return;
                        }

                        page = data.getPage().getCurrent() + 1;
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
            itemOrderBottom.totalMoney=orderBean.getPrice_total();
            itemOrderBottom.goodsMoney=orderBean.getPrice_goods();
            itemOrderBottom.expressMoney=orderBean.getPrice_express();
            itemOrderBottom.expressNo=orderBean.getExpress_send_no();
            itemOrderBottom.express_company_title=orderBean.getExpress_company_title();
            itemOrderBottom.expressCode=orderBean.getExpress_company_code();
            itemOrderBottom.num=orderBean.getGoods_count();
            itemOrderBottom.bottomcur=curBottom;
            itemOrderBottom.topCur=cur;
            mItems.add(itemOrderBottom);
        }

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mCanLoadMore = true) {
            mCanLoadMore = false;
            //   mViewModel.getOrderList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(page), mtype, "", Status.LOAD_MORE).observe(this, mObserver);

        } else {
            mRefreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        load();
    }


    @Override
    public void onReBuy(ItemOrderBottom itemOrderBottom) {

    }

    @Override
    public void onCancel(ItemOrderBottom itemOrderBottom) {

    }

    @Override
    public void onPay(ItemOrderBottom itemOrderBottom) {

    }

    @Override
    public void onConfirmGoods(ItemOrderBottom itemOrderBottom) {

    }

    @Override
    public void onViewLogistics(ItemOrderBottom itemOrderBottom) {
        Intent intent = new Intent(mActivity, LogisticsActivity.class);
        intent.putExtra("express_no", itemOrderBottom.expressNo);
        intent.putExtra("express_code", itemOrderBottom.expressCode);
        intent.putExtra("express_company_title", itemOrderBottom.express_company_title);
        startActivity(intent);
    }
}

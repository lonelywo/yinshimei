package com.cuci.enticement.plate.mine.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllJiFenJiLuBean;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.ItemDuiHuanOrderTitle;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.ItemTuikuaiOrderTitle;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.LogisticsActivity;
import com.cuci.enticement.plate.cart.activity.OrderDetailsActivity;
import com.cuci.enticement.plate.mine.adapter.ItemBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemDuiHuanBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemDuiHuanProdViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemDuiHuanTitleViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemProdViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemTitleViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
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
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class DuiHuanJiLuActivity extends BaseActivity implements OnRefreshLoadMoreListener,ItemDuiHuanTitleViewBinder.OnProdTitleClickListener,ItemDuiHuanProdViewBinder.OnProdClickListener,ItemDuiHuanBottomViewBinder.OnItemClickListener{
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
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyLayout;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private int page=1;
    private boolean mCanLoadMore = true;
    private  List<AllJiFenJiLuBean.DataBean.ListBean> mDatas=new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_duihuanjilu;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo == null) {
            return;
        }

        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);

        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

        // mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //注册布局
        mAdapter.register(ItemDuiHuanOrderTitle.class, new ItemDuiHuanTitleViewBinder(this));

        mAdapter.register(AllJiFenJiLuBean.DataBean.ListBean.OrderListBean.class, new ItemDuiHuanProdViewBinder(this));

        mAdapter.register(ItemOrderBottom.class, new ItemDuiHuanBottomViewBinder(this));

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        OrderItemTopDecoration mDecoration = new OrderItemTopDecoration(this, 4);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
        load();
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void load() {
        mViewModel.JiFenOrderList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", "", "1", "20","" + AppUtils.getVersionCode(BasicApp.getContext()), Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }
    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    AllJiFenJiLuBean allOrderList = new Gson().fromJson(result, AllJiFenJiLuBean.class);
                    if (allOrderList.getCode() == 1) {
                        AllJiFenJiLuBean.DataBean data = allOrderList.getData();
                        List<AllJiFenJiLuBean.DataBean.ListBean> item = data.getList();
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
                    } else if (allOrderList.getCode() == HttpUtils.CODE_INVALID) {
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
    private void addOrderItem(List<AllJiFenJiLuBean.DataBean.ListBean> item) {



        for (int i = 0; i <item.size() ; i++) {
            AllJiFenJiLuBean.DataBean.ListBean orderBean = item.get(i);
            int cur=mItems.size();
            ItemDuiHuanOrderTitle itemOrderTitle = new ItemDuiHuanOrderTitle(String.valueOf(orderBean.getOrder_no()), orderBean.getStatus(),cur);
            mItems.add(itemOrderTitle);
            List<AllJiFenJiLuBean.DataBean.ListBean.OrderListBean> goodsBeanList = orderBean.getOrder_list();
            mItems.addAll(goodsBeanList);
            int curBottom=mItems.size();
            ItemOrderBottom itemOrderBottom = new ItemOrderBottom();
            itemOrderBottom.status=orderBean.getStatus();
            itemOrderBottom.orderNum=String.valueOf(orderBean.getOrder_no());
            itemOrderBottom.goodsMoney=""+orderBean.getPay_points();
            itemOrderBottom.expressMoney=orderBean.getPrice_express();
            itemOrderBottom.expressNo=orderBean.getExpress_send_no();
            itemOrderBottom.express_company_title=orderBean.getExpress_company_title();
            itemOrderBottom.expressCode=orderBean.getExpress_company_code();
            itemOrderBottom.bottomcur=curBottom;
            itemOrderBottom.topCur=cur;
            mItems.add(itemOrderBottom);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onProdTitleClick(ItemDuiHuanOrderTitle item) {
        // 从API11开始android推荐使用android.content.ClipboardManager
// 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
// 将文本内容放到系统剪贴板里。
        String order_no = item.getOrderNum();
        cm.setText(order_no);
        FToast.success("订单编号复制成功");
    }

    @Override
    public void onProdClick(AllJiFenJiLuBean.DataBean.ListBean.OrderListBean item) {
        Intent intent = new Intent(this, DuiHuanJiLuDetailsActivity.class);
       /* int curOrder=0;
        for (int i = 0; i < mDatas.size(); i++) {
            long order_no = mDatas.get(i).getOrder_no();
            if(order_no==item.getOrder_no()){
                curOrder=i;
                break;
            }
        }
        AllJiFenJiLuBean.DataBean.ListBean orderBean = mDatas.get(curOrder);*/
        intent.putExtra("intentInfo",String.valueOf(item.getOrder_no()));
        startActivity(intent);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore=true){
            mCanLoadMore = false;
            mViewModel.JiFenOrderList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", "", ""+page, "20","" + AppUtils.getVersionCode(BasicApp.getContext()), Status.LOAD_MORE)
                    .observe(this, mObserver);

        }else {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }



    @Override
    public void onViewLogistics(ItemOrderBottom itemOrderBottom) {
        Intent intent = new Intent(this, LogisticsActivity.class);
        intent.putExtra("express_no",itemOrderBottom.expressNo);
        intent.putExtra("express_code",itemOrderBottom.expressCode);
        intent.putExtra("express_company_title",itemOrderBottom.express_company_title);
        startActivity(intent);
    }
}

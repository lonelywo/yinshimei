package com.cuci.enticement.plate.cart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.ExpressInfo;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.adapter.ItemLogisticsViewBinder;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 订单详情页
 */
public class LogisticsActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.text_wuliugongsi)
    TextView textWuliugongsi;
    @BindView(R.id.text_yundanbianhao)
    TextView textYundanbianhao;
    @BindView(R.id.con_zhongjian)
    ConstraintLayout conZhongjian;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    private OrderViewModel mViewModel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private UserInfo mUserInfo;
    private boolean mCanLoadMore = true;
    private String mExpressNo;
    private String mExpressCode;
    @Override
    public int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
       mExpressNo= intent.getStringExtra("express_no");
        mExpressCode = intent.getStringExtra("express_code");
       /* data = (MyTeamlbBean.DataBean.ListBean) intent.getSerializableExtra("Data");

        if (data == null) {
            FToast.error("数据错误");
            return;
        }*/

        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(ExpressInfo.DataBeanX.DataBean.class, new ItemLogisticsViewBinder());

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.autoRefresh();
    }

    private void load() {
        if (mUserInfo == null) {
            refreshLayout.finishRefresh();
            return;
        }
        mViewModel.getExpressInfo(mExpressNo, mExpressCode, Status.LOAD_REFRESH)
                .observe(this, mObserver1);
    }

    private Observer<Status<ResponseBody>> mObserver1 = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                statusView.showContent();

                ResponseBody body = status.content;
                opera1(body, status);
                break;
            case Status.ERROR:

                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {
                    refreshLayout.finishRefresh();
                }
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera1(ResponseBody body, Status status) {
        try {
            String b = body.string();
            ExpressInfo mExpressInfo = new Gson().fromJson(b, ExpressInfo.class);
            textWuliugongsi.setText(String.format(Locale.CHINA,"物流公司:%s",mExpressInfo.getData().getCom()));
            textYundanbianhao.setText(String.format(Locale.CHINA,"运单编号：%s",mExpressInfo.getData().getNu()));
            List<ExpressInfo.DataBeanX.DataBean> item = mExpressInfo.getData().getData();
            if (item == null || item.size() == 0) {

                if (status.loadType == Status.LOAD_REFRESH) {
                    statusView.showEmpty();
                    refreshLayout.finishRefresh();
                } else {
                    refreshLayout.finishLoadMore();
                }
                return;
            }


            if (mExpressInfo.getCode() == 1) {
                mCanLoadMore = true;

                if (status.loadType == Status.LOAD_REFRESH) {
                    mItems.clear();
                    mItems.addAll(item);
                    mAdapter.notifyDataSetChanged();
                    refreshLayout.finishRefresh();
                } else {
                    mItems.addAll(item);
                    mAdapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore();
                }
            } else {
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {

                    refreshLayout.finishRefresh();
                }
                FToast.warning(mExpressInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        load();
    }



    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}

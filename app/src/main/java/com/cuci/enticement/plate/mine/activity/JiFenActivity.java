package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.JiFenMingXiBean;
import com.cuci.enticement.bean.NoticeListBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemJiFenListViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemNoticeListViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class JiFenActivity extends BaseActivity  implements OnRefreshLoadMoreListener, ItemJiFenListViewBinder.OnProdClickListener{
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_guize)
    TextView tvGuize;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_wenzi)
    TextView tvMoneyWenzi;
    @BindView(R.id.tv_shangcheng)
    TextView tvShangcheng;
    @BindView(R.id.tv_jilu)
    TextView tvJilu;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.tv_biaoti)
    TextView tvBiaoti;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private boolean mCanLoadMore= true;
    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_jifen;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        refreshLayout.setEnableRefresh(false);
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(JiFenMingXiBean.DataBean.ListBean.class, new ItemJiFenListViewBinder(this));
       /* CartItemDecoration mDecoration = new CartItemDecoration(this, 4);
        recyclerView.addItemDecoration(mDecoration);*/
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        load();
    }

    private void load() {
        mViewModel.JiFenMingXiList( mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2", "1","20",""+ AppUtils.getVersionCode(this), Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.tv_guize,R.id.tv_shangcheng,R.id.tv_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_guize:
                Intent intentProd = new Intent(this, TuiAgreementActivity.class);
                intentProd.putExtra("title", "积分规则");
                intentProd.putExtra("bannerData", "https://web.enticementchina.com/appweb/integral_rule.html");
                startActivity(intentProd);
                break;
            case R.id.tv_shangcheng:
                startActivity(new Intent(this, DuiHuanMallActivity.class));
                break;
            case R.id.tv_jilu:
                startActivity(new Intent(this, DuiHuanJiLuActivity.class));
                break;
        }
    }


    private Observer<Status<ResponseBody>> mObserver = status -> {

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
            JiFenMingXiBean mJiFenMingXiBean = new Gson().fromJson(b, JiFenMingXiBean.class);
            List<JiFenMingXiBean.DataBean.ListBean> item = mJiFenMingXiBean.getData().getList();
            if (item == null || item.size() == 0) {
                if (status.loadType == Status.LOAD_REFRESH) {
                    refreshLayout.finishRefresh();
                    statusView.showEmpty();
                } else {
                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mJiFenMingXiBean.getCode() == 1) {
                tvMoney.setText(""+mJiFenMingXiBean.getData().getPoints());
                page = mJiFenMingXiBean.getData().getPage().getCurrent() + 1;
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
            }else if(mJiFenMingXiBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mJiFenMingXiBean.getInfo());
            } else {
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {

                    refreshLayout.finishRefresh();
                }
                FToast.error(mJiFenMingXiBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    @Override
    public void onProdClick(int id) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.JiFenMingXiList( mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2", ""+page,"20",""+ AppUtils.getVersionCode(this), Status.LOAD_MORE)
                    .observe(this, mObserver);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        load();
    }
}

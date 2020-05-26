package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.NoticeListBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.plate.mine.adapter.ItemNoticeListViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class NoticeActivity extends BaseActivity implements OnRefreshLoadMoreListener, ItemNoticeListViewBinder.OnProdClickListener {
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private boolean mCanLoadMore= true;
    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(NoticeListBean.DataBean.ListBean.class, new ItemNoticeListViewBinder(this));
        CartItemDecoration mDecoration = new CartItemDecoration(this, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        load();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }


    private void load() {

        mViewModel.Noticelist( "2", "1",""+ AppUtils.getVersionCode(this), Status.LOAD_REFRESH)
                .observe(this, mObserver);

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
            NoticeListBean mNoticeListBean = new Gson().fromJson(b, NoticeListBean.class);
            List<NoticeListBean.DataBean.ListBean> item = mNoticeListBean.getData().getList();
            if (item == null || item.size() == 0) {
                if (status.loadType == Status.LOAD_REFRESH) {
                    refreshLayout.finishRefresh();
                    statusView.showEmpty();
                } else {
                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mNoticeListBean.getCode() == 1) {

                page = mNoticeListBean.getData().getPage().getCurrent() + 1;
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
                FToast.error(mNoticeListBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }




    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.Noticelist( "2",  "" + page,""+ AppUtils.getVersionCode(this), Status.LOAD_MORE)
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

    @Override
    public void onProdClick(int id) {
        Intent intentProd = new Intent(this, NoticeRecActivity.class);
        intentProd.putExtra("id", id);
        startActivity(intentProd);
    }
}

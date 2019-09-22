package com.cuci.enticement.plate.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.MallSourceBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.eventbus.MessageEvent;
import com.cuci.enticement.plate.common.eventbus.MessageEvent1;
import com.cuci.enticement.plate.mall.adapter.NineAdapter;
import com.cuci.enticement.plate.mall.vm.MallViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.GridItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YuLanActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    private MallViewModel mViewModel;
    private NineAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.image_top)
    ImageView mIvTop;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private final String PAGE_SIZE="30";
    private GridItemDecoration mDecoration;
    private GridLayoutManager mLayoutManager;
    private boolean mCanLoadMore=true;
    private int page=1;
    private String type;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_source02;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //注册事件
        EventBus.getDefault().register(this);
        mViewModel = ViewModelProviders.of(this).get(MallViewModel.class);
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        type = intent.getStringExtra("Data");
        if (type == null) {
            FToast.error("数据错误");
            return;
        }
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

        // mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new GridLayoutManager(this, 3);
        mAdapter = new NineAdapter(this,mLayoutManager);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDecoration = new GridItemDecoration(this, 3, 12, true);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.autoRefresh();
        mIvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Glide.with(BasicApp.getContext()).resumeRequests();
                        if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                            ViewUtils.hideView(mIvTop);
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Glide.with(BasicApp.getContext()).pauseRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Glide.with(BasicApp.getContext()).resumeRequests();
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    ViewUtils.showView(mIvTop);
                } else {
                    ViewUtils.hideView(mIvTop);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onShowStickyEventMessage(MessageEvent1 messageEvent) {
      //  type = messageEvent.getMessage();

     /*   //显示粘性事件
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);

        // mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new GridLayoutManager(this, 3);
        mAdapter = new NineAdapter(this, mLayoutManager);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDecoration = new GridItemDecoration(this, 3, 12, true);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
        mIvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        if (messageEvent.getType() == 0) {
            mAdapter.setList(messageEvent.getItems());
            mAdapter.notifyDataSetChanged();
        }else {
            mAdapter.addList(messageEvent.getItems());
        }*/
    }
    private void load() {
        mViewModel.getSource02(type,"1",PAGE_SIZE, Status.LOAD_REFRESH).observe(this, mObserver);
    }

    private Observer<Status<MallSourceBean>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                MallSourceBean item = status.content;

                if (item.getCode() == 1) {

                    List<MallSourceBean.DataBean.ListBean> items = item.getData().getList();
                    if (items == null || items.size() == 0) {
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

                        mAdapter.setList(items);
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.finishRefresh();
                    } else {

                        mAdapter.addList(items);
                        mRefreshLayout.finishLoadMore();
                    }

                } else {
                    if (status.loadType == Status.LOAD_MORE) {
                        mCanLoadMore = true;
                        mRefreshLayout.finishLoadMore();
                    } else {

                        mRefreshLayout.finishRefresh();
                    }
                    FToast.error(item.getInfo());
                }
                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error("网络请求错误");
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    mRefreshLayout.finishLoadMore();
                } else {

                    mRefreshLayout.finishRefresh();

                }
                break;
        }
    };

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore){
            mCanLoadMore = false;
            mViewModel.getSource02(type, String.valueOf(page),PAGE_SIZE,
                    Status.LOAD_MORE).observe(this, mObserver);
        }else {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }
}

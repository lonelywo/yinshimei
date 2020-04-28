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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.JiFenMallBean;
import com.cuci.enticement.bean.JiFenMingXiBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemJiFenGoodsListViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.GridItemDecoration;
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

public class DuiHuanMallActivity extends BaseActivity implements OnRefreshLoadMoreListener, ItemJiFenGoodsListViewBinder.OnProdClickListener {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    @BindView(R.id.image_top)
    ConstraintLayout mIvTop;
    private UserInfo mUserInfo;
    private MultiTypeAdapter mAdapter;
    private MineViewModel mViewModel;
    private Items mItems;
    private GridItemDecoration mDecoration;
    private GridLayoutManager mLayoutManager;
    private boolean mCanLoadMore= true;
    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_duihuanmall;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
       // refreshLayout.setEnableRefresh(false);
        CustomRefreshHeader header = new CustomRefreshHeader(this);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(JiFenMallBean.DataBean.ListBean.class, new ItemJiFenGoodsListViewBinder(this));
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        mDecoration = new GridItemDecoration(this, 2, 12, true);
        recyclerView.addItemDecoration(mDecoration);
        recyclerView.setAdapter(mAdapter);
        load();
        mIvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                            ViewUtils.hideView(mIvTop);
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:

                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:

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

    private void load() {
        mViewModel.JiFenShangChengList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", "1", "20", "" + AppUtils.getVersionCode(this), Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
            JiFenMallBean mJiFenMallBean = new Gson().fromJson(b, JiFenMallBean.class);
            List<JiFenMallBean.DataBean.ListBean> item = mJiFenMallBean.getData().getList();
            if (item == null || item.size() == 0) {
                if (status.loadType == Status.LOAD_REFRESH) {
                    refreshLayout.finishRefresh();
                    statusView.showEmpty();
                } else {
                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mJiFenMallBean.getCode() == 1) {
                page = mJiFenMallBean.getData().getPage().getCurrent() + 1;
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
            }else if(mJiFenMallBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mJiFenMallBean.getInfo());
            } else {
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {

                    refreshLayout.finishRefresh();
                }
                FToast.error(mJiFenMallBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    @Override
    public void onProdClick(JiFenMallBean.DataBean.ListBean item) {
        Intent intentProd = new Intent(this, JiFenProdActivity.class);
        intentProd.putExtra("bannerData", ""+item.getId());
        startActivity(intentProd);
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

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}

package com.example.enticement.plate.mall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.MallSourceBean;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.common.popup.ImageViewerPopup;
import com.example.enticement.plate.mall.adapter.NineAdapter;
import com.example.enticement.plate.mall.vm.MallViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.widget.CustomRefreshHeader;
import com.example.enticement.widget.GridItemDecoration;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;

/**
 * 首页外层Fragment
 */
public class _MallFragment01 extends BaseFragment implements OnRefreshLoadMoreListener, NineAdapter.OnItemClickListener {

    private static final String TAG = _MallFragment01.class.getSimpleName();

    private boolean mCanLoadMore = true;
    private MallViewModel mViewModel;
    private int page=1;
    private String mtype;
    private final String PAGE_SIZE="20";
    private NineAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.image_top)
    ImageView mIvTop;
    private GridItemDecoration mDecoration;
    private GridLayoutManager mLayoutManager;

    public static _MallFragment01 newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        _MallFragment01 fragment = new _MallFragment01();
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
        mViewModel = ViewModelProviders.of(this).get(MallViewModel.class);
        Bundle bundle = getArguments();
        mtype = bundle.getString("type");
        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

       // mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new GridLayoutManager(mActivity, 2);
        mAdapter = new NineAdapter(mActivity,mLayoutManager);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDecoration = new GridItemDecoration(mActivity, 2, 12, true);
        mRecyclerView.addItemDecoration(mDecoration);

        mRecyclerView.setAdapter(mAdapter);
        mIvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void load() {
        mViewModel.getSource01(mtype,"1",PAGE_SIZE,Status.LOAD_REFRESH).observe(this, mObserver);
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
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore){
            mCanLoadMore = false;
            mViewModel.getSource01(mtype, String.valueOf(page),PAGE_SIZE,
                    Status.LOAD_MORE).observe(this, mObserver);
        }else {
            mRefreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

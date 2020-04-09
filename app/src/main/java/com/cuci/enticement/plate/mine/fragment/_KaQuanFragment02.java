package com.cuci.enticement.plate.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemKaQuanViewBinder2;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 首页外层Fragment
 */
public class _KaQuanFragment02 extends BaseFragment implements OnRefreshLoadMoreListener, ItemKaQuanViewBinder2.OnPKClickListener {

    private static final String TAG = _KaQuanFragment02.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private MultiTypeAdapter mAdapter;
    private boolean mCanLoadMore = true;
    private int page = 1;
    private String mtype;
    private final String PAGE_SIZE = "20";
    private LinearLayoutManager mLayoutManager;
    private Items mItems;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private boolean ishand = false;

    public static _KaQuanFragment02 newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        _KaQuanFragment02 fragment = new _KaQuanFragment02();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kaquan;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        Bundle bundle = getArguments();
        mtype = bundle.getString("type");
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(KaQuanListBean.DataBean.ListBean.class, new ItemKaQuanViewBinder2(this));
        BrandItemDecoration mDecoration = new BrandItemDecoration(mActivity, 12, 6);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    private void load() {
        page = 1;
        mViewModel.kaquanlist(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2", "1", "10", "1",""+ AppUtils.getVersionCode(mActivity), Status.LOAD_REFRESH)
                .observe(this, mObserver);
       // ViewUtils.showView(progressBar);
    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                statusView.showContent();
                ResponseBody body = status.content;
                opera1(body, status);
            //    ViewUtils.hideView(progressBar);
                break;
            case Status.ERROR:
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {
                    refreshLayout.finishRefresh();
                }
                FToast.error("网络错误");
            //    ViewUtils.hideView(progressBar);
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera1(ResponseBody body, Status status) {
        try {
            String b = body.string();
            KaQuanListBean mKaQuanListBean = new Gson().fromJson(b, KaQuanListBean.class);
             List<KaQuanListBean.DataBean.ListBean> item = mKaQuanListBean.getData().getList();
            if (item == null || item.size() == 0) {
                if (status.loadType == Status.LOAD_REFRESH) {
                    refreshLayout.finishRefresh();
                    statusView.showEmpty();
                } else {
                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mKaQuanListBean.getCode() == 1) {

                page = mKaQuanListBean.getData().getPage().getCurrent() + 1;
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
            }else if(mKaQuanListBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(mActivity);
                mActivity.finish();
                FToast.warning(mKaQuanListBean.getInfo());
            } else {
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {

                    refreshLayout.finishRefresh();
                }
                FToast.warning(mKaQuanListBean.getInfo());

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
            mViewModel.kaquanlist(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2", page+"", "10", "1",""+ AppUtils.getVersionCode(mActivity), Status.LOAD_MORE)
                    .observe(this, mObserver);

        } else {
            refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }


    @Override
    public void onProdClick(KaQuanListBean.DataBean.ListBean item) {

    }



}

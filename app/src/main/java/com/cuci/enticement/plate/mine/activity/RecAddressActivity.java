package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AdressBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.plate.mine.adapter.ItemAdressViewBinder;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class RecAddressActivity extends BaseActivity implements OnRefreshLoadMoreListener,ItemAdressViewBinder.OnItemClickListener {


    private String mAdress;
    private String mAdressId;

    @BindView(R.id.status_view)
    MultipleStatusView mStatusView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private CommonViewModel mViewModel;

    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private String mId;
    private String minId;
    private boolean mCanLoadMore = true;
    private ItemAdressViewBinder mViewBinder;
    private UserInfo mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recaddress;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo==null){
            return;

        }
        mViewModel= ViewModelProviders.of(this).get(CommonViewModel.class);



        mAdapter = new MultiTypeAdapter();

        mItems = new Items();
        mAdapter.setItems(mItems);
        mViewBinder = new ItemAdressViewBinder(this);
        mAdapter.register(AdressBean.DataBean.ListBean.class, mViewBinder);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new CartItemDecoration(this,4));
        mRecyclerView.setAdapter(mAdapter);

        CustomRefreshHeader header = new CustomRefreshHeader(this);
        mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

        mRefreshLayout.autoRefresh();

    }




    @OnClick({R.id.img_back, R.id.btn_add_new_adress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:

                finish();
                break;
            case R.id.btn_add_new_adress:

                Intent addIntent = new Intent(RecAddressActivity.this, ZengAddressActivity.class);
                startActivity(addIntent);
                break;
        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mViewModel.getAdressList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),Status.LOAD_REFRESH).observe(this,mObserver);
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
     /*   if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.getAdressList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),Status.LOAD_MORE).observe(this,mObserver);

        } else {*/
            mRefreshLayout.finishLoadMore();
       // }
    }

    private Observer<Status<AdressBean>> mObserver = new Observer<Status<AdressBean>>() {
        @Override
        public void onChanged(Status<AdressBean> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    AdressBean data = status.content;
                    List<AdressBean.DataBean.ListBean> list = data.getData().getList();
                    if (data == null) {
                        mStatusView.showEmpty();
                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }
                    mStatusView.showContent();
                    if (data.getCode() == 1) {
                        mCanLoadMore = true;
                        if (status.loadType == Status.LOAD_REFRESH) {
                            mItems.clear();
                            mItems.addAll(list);
                            mAdapter.notifyDataSetChanged();
                            mRefreshLayout.finishRefresh();
                        } else {
                            int o = mItems.size();
                            mItems.addAll(list);
                            int c = mItems.size();
                            mAdapter.notifyItemRangeInserted(o, c);
                            mRefreshLayout.finishLoadMore();
                        }
                    } else {
                        if (status.loadType == Status.LOAD_MORE) {
                            mCanLoadMore = true;
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        FToast.error(data.getInfo());
                    }
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
                case Status.LOADING:

                    break;
            }
        }
    };

    @Override
    public void onEditClick(AdressBean.DataBean.ListBean bean) {

    }

    @Override
    public void onCheckAdress(AdressBean.DataBean.ListBean bean) {
        StringBuilder sb = new StringBuilder();
        sb.append(bean.getName()).append(" ")
                .append(bean.getPhone()).append(" ")
                .append(bean.getProvince()).append(" ")
                .append(bean.getCity()).append(" ")
                .append(bean.getArea()).append(" ")
                .append(bean.getAddress());
        String adress=sb.toString();
        Intent intent = new Intent(RecAddressActivity.this, OrderActivity.class);
        intent.putExtra("adress",adress);
        intent.putExtra("adressId",bean.getId());

        setResult(101,intent);
        finish();
    }

    @Override
    public void onDelete(AdressBean.DataBean.ListBean bean) {

    }
}

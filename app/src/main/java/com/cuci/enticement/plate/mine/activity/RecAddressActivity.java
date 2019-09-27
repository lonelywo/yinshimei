package com.cuci.enticement.plate.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AddressBean;
import com.cuci.enticement.bean.DeleteAddress;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.common.eventbus.AddressEvent;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.plate.mine.adapter.ItemAdressViewBinder;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

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
    private int mCode;
    @Override
    public int getLayoutId() {
        return R.layout.activity_recaddress;
    }


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onAddressEventMessage(AddressEvent event) {
       if(event.getCode()==AddressEvent.REFRESH_ADRESS_LIST){
            mRefreshLayout.autoRefresh();
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }





    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
         mCode = intent.getIntExtra("code", 0);
        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo==null){
            return;

        }
        mViewModel= ViewModelProviders.of(this).get(CommonViewModel.class);





        mAdapter = new MultiTypeAdapter();

        mItems = new Items();
        mAdapter.setItems(mItems);
        mViewBinder = new ItemAdressViewBinder(this);
        mAdapter.register(AddressBean.DataBean.ListBean.class, mViewBinder);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new CartItemDecoration(this,10));
        mRecyclerView.setAdapter(mAdapter);

        CustomRefreshHeader header = new CustomRefreshHeader(this);
       // mRefreshLayout.setRefreshHeader(header);
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
        mViewModel.getAdressList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),Status.LOAD_REFRESH)
                .observe(this,mObserver);
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            mRefreshLayout.finishLoadMore();

    }

    private Observer<Status<AddressBean>> mObserver = new Observer<Status<AddressBean>>() {
        @Override
        public void onChanged(Status<AddressBean> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    AddressBean data = status.content;
                    List<AddressBean.DataBean.ListBean> list = data.getData().getList();
                    if (list == null||list.size()==0) {

                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mStatusView.showEmpty();
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

                            saveDefault(list);

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
                        FToast.warning(data.getInfo());
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

    private void saveDefault(List<AddressBean.DataBean.ListBean> list) {
        boolean hasDefault=false;
        int num=0;
        for (int i = 0; i < list.size(); i++) {
            AddressBean.DataBean.ListBean item = list.get(i);

            int is_default = item.getIs_default();
            if(is_default==1){
                //存在默认就设置
                hasDefault=true;
                num=i;
                break;
            }
        }

        if(hasDefault){

            AddressBean.DataBean.ListBean item = list.get(num);
            SharedPrefUtils.saveDefaultAdressId(String.valueOf(item.getId()));
            StringBuilder sb = new StringBuilder();
            sb.append(item.getName()).append(" ")
                    .append(item.getPhone()).append(" ").append("\n")
                    .append(item.getProvince()).append(" ")
                    .append(item.getCity()).append(" ")
                    .append(item.getArea()).append(" ")
                    .append(item.getAddress());
            SharedPrefUtils.saveDefaultAdress(sb.toString());
        }




    }

    @Override
    public void onEditClick(AddressBean.DataBean.ListBean bean) {
        Intent intent = new Intent(this, ZengAddressActivity.class);
        intent.putExtra("addressBean",bean);
        startActivity(intent);
    }

    @Override
    public void onCheckAdress(AddressBean.DataBean.ListBean bean) {
        if(mCode==100){
        /*    StringBuilder sb = new StringBuilder();
            sb.append(bean.getName()).append(" ")
                    .append(bean.getPhone()).append(" ")
                    .append(bean.getProvince()).append(" ")
                    .append(bean.getCity()).append(" ")
                    .append(bean.getArea()).append(" ")
                    .append(bean.getAddress());
            String adress=sb.toString();*/
            Intent intent = new Intent(RecAddressActivity.this, OrderActivity.class);
            intent.putExtra("addressBean",bean);


            setResult(101,intent);
            finish();
        }

    }

    @Override
    public void onDelete(AddressBean.DataBean.ListBean bean,int position) {
        mPosition=position;
               mViewModel.deleteAddress(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),String.valueOf(bean.getId()))
                .observe(this,mDeleteObserver);




    }

    private int mPosition;

    private Observer<Status<ResponseBody>> mDeleteObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                mStatusView.showContent();
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    DeleteAddress deleteAddress = new Gson().fromJson(result, DeleteAddress.class);
                    if(deleteAddress.getCode()==1){
                        mItems.remove(mPosition);
                        mAdapter.notifyItemRemoved(mPosition);
                        if(mItems.size()==0){
                            mStatusView.showEmpty();
                        }

                        FToast.success(deleteAddress.getInfo());
                    }else {
                        FToast.error(deleteAddress.getInfo());
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }



                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);

                break;
        }
    };



}

package com.cuci.enticement.plate.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.BannerDataBean;
import com.cuci.enticement.bean.BaseList;

import com.cuci.enticement.bean.DataUserInfo;
import com.cuci.enticement.bean.EssayBean;
import com.cuci.enticement.bean.GeneralGoods;
import com.cuci.enticement.bean.GoodsItem;
import com.cuci.enticement.bean.ItemBanner;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.ClickCatEvent;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.common.Agreement2Activity;
import com.cuci.enticement.plate.common.AgreementActivity;
import com.cuci.enticement.plate.common.popup.CenterShareAppPopup;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.adapter.ItemBannerViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemGoodsLongViewBinder;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.plate.mine.activity.PKActivity;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.HomeGridItemDecoration;
import com.cuci.enticement.widget.HomeListSpanSizeLookup;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import io.reactivex.internal.operators.flowable.FlowableSwitchIfEmpty;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 首页外层Fragment
 */
public class _HomeFragment extends BaseFragment  implements ItemBannerViewBinder.OnBannerClickListener , OnRefreshLoadMoreListener {

    private static final String TAG = _HomeFragment.class.getSimpleName();
    private int mMinId = 1;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private HomeGridItemDecoration mDecoration;
    private GridLayoutManager mLayoutManager;
    private HomeListSpanSizeLookup mSizeLookup;
    private HomeViewModel mViewModel;
    private boolean mCanLoadMore = true;
    private UserInfo mUserInfo;


//    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mRecyclerView = view.findViewById(R.id.rec_goods);
        mRefreshLayout = view.findViewById(R.id.refresh_home);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
       // mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter.register(ItemBanner.class, new ItemBannerViewBinder(this));
        mAdapter.register(GoodsItem.class, new ItemGoodsLongViewBinder(mActivity));

        mDecoration = new HomeGridItemDecoration(mActivity, 2, 6, true);
        mDecoration.setHeaderCount(1);

        mRecyclerView.addItemDecoration(mDecoration);

        mLayoutManager = new GridLayoutManager(mActivity, 2);
        mSizeLookup = new HomeListSpanSizeLookup(mLayoutManager, 1);
        mLayoutManager.setSpanSizeLookup(mSizeLookup);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);

    }
    //刷新isnew显示数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickProgoodsEvent(ProgoodsEvent event) {
       load();
    }

    private Observer<Status<ResponseBody>> essayObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                opera2(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };
    private void opera2(ResponseBody body) {
        try {
            String b = body.string();
            EssayBean mEssayBean = new Gson().fromJson(b, EssayBean.class);
            if (mEssayBean.getCode() == 1) {
                Intent intentProd = new Intent(mActivity, AgreementActivity.class);
                intentProd.putExtra("bannerData", mEssayBean.getData().getContent());
                mActivity.startActivity(intentProd);
            } else {
                FToast.error(mEssayBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }
    @Override
    public void onBannerClick(BannerDataBean bannerDataBean) {
     switch (bannerDataBean.getType()){
         case "0":
             Intent intentProd = new Intent(mActivity, ProdActivity.class);
             intentProd.putExtra("bannerData", bannerDataBean.getLink());
             mActivity.startActivity(intentProd);
             break;
         case "1" :
             mViewModel.essay(bannerDataBean.getLink(), "2",  ""+AppUtils.getVersionCode(mActivity)).observe(this, essayObserver);
             break;
         case "2":
             Intent intentProd1 = new Intent(mActivity, Agreement2Activity.class);
             intentProd1.putExtra("url", bannerDataBean.getLink());
             mActivity.startActivity(intentProd1);
             break;
     }


    }

    @Override
    public void onBannerChange(BannerDataBean bannerDataBean) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
       /* if (mCanLoadMore) {
            mCanLoadMore = false;
            mViewModel.getGeneralGoods( mMinId, Status.LOAD_MORE).observe(this, GoodsmObserver);
        } else {*/
            mRefreshLayout.finishLoadMore();
     //   }
    }
    private void load() {
        mViewModel.getBanner().observe(this, mObserver);

    }



    private Observer<Status<BaseList<BannerDataBean>>>  mObserver = new Observer<Status<BaseList<BannerDataBean>>>() {

        @Override
        public void onChanged(Status<BaseList<BannerDataBean>> baseListStatus) {
            switch (baseListStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    BaseList<BannerDataBean> list = baseListStatus.content;
                    if (list == null) {
                        mRefreshLayout.finishLoadMore();
                        return;
                    }
                    if (list.code == 1) {
                        List<BannerDataBean> items = list.data;
                        ItemBanner itemBanner = new ItemBanner(items);
                        mItems.clear();
                        mItems.add(itemBanner);
                    if(mUserInfo!=null){
                        mViewModel.getGeneralGoods("2" ,String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), Status.LOAD_REFRESH).observe(_HomeFragment.this, GoodsmObserver);
                    } else {
                        mViewModel.getGeneralGoods("2" ,"", "", Status.LOAD_REFRESH).observe(_HomeFragment.this, GoodsmObserver);
                    }

                    } else {
                        FToast.error(list.info);
                    }
                    mRefreshLayout.finishLoadMore();
                    break;
                case Status.ERROR:
                    mRefreshLayout.finishRefresh();

                    FToast.error("网络请求错误");
                    break;
            }
            mCanLoadMore = true;
        }
    };

    private Observer<Status<GeneralGoods>> GoodsmObserver = new Observer<Status<GeneralGoods>>() {
        @Override
        public void onChanged(Status<GeneralGoods> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    GeneralGoods data = status.content;
                    List<GoodsItem> list = data.getData().getList();
                    if (data == null) {
                        if (status.loadType == Status.LOAD_MORE) {
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mRefreshLayout.finishRefresh();
                        }
                        return;
                    }

                    if (data.getCode() == 1) {
                        mCanLoadMore = true;
                        if (status.loadType == Status.LOAD_REFRESH) {
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
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        mMinId=1;
        mViewModel.getBanner().observe(this, mObserver);

    }
/*    private Observer<Status<Splash>> mObserver = splashStatus -> {
        switch (splashStatus.status) {
            case Status.LOADING:
                mStatusView.showLoading();
                break;
            case Status.SUCCESS:
                mStatusView.showContent();
                Splash splash = splashStatus.content;
                if (splash == null) return;
                SharedPrefUtils.saveUserAgreement(splash.getUserAgreement());
                initTopTab(splash);
                saveSplash(splash);
                break;
            case Status.ERROR:
                mStatusView.showError();
                break;
        }
    };*/







}

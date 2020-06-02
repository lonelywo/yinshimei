package com.cuci.enticement.plate.home.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.BannerDataBean;
import com.cuci.enticement.bean.BaseList;
import com.cuci.enticement.bean.EssayBean;
import com.cuci.enticement.bean.GeneralGoods;
import com.cuci.enticement.bean.GoodsItem;
import com.cuci.enticement.bean.IsYhjLingBean;
import com.cuci.enticement.bean.ItemBanner;
import com.cuci.enticement.bean.QyandYHJBean;
import com.cuci.enticement.bean.ShareHomeImgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.ClickMyEvent;
import com.cuci.enticement.plate.common.Agreement2Activity;
import com.cuci.enticement.plate.common.AgreementActivity;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.common.eventbus.EssayEvent;
import com.cuci.enticement.plate.common.popup.TipsPopup_kaquan;
import com.cuci.enticement.plate.home.activity.CenterLingQuanActivity;
import com.cuci.enticement.plate.home.activity.CenterLingQuanActivity2;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.adapter.ItemBannerViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemGoodsLongViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemImageViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemLingQuanViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemQiYeViewBinder;
import com.cuci.enticement.plate.home.adapter.ItemShareViewBinder;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.plate.mine.activity.KaQuanActivity;
import com.cuci.enticement.plate.mine.activity.QianDaoActivity;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
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

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 首页外层Fragment
 */
public class _HomeFragment extends BaseFragment implements ItemBannerViewBinder.OnBannerClickListener, OnRefreshLoadMoreListener, ItemLingQuanViewBinder.OnLingQuanClickListener, ItemQiYeViewBinder.OnQiYeClickListener, ItemShareViewBinder.OnShareClickListener, ItemImageViewBinder.OnItemClickListener {

    private static final String TAG = _HomeFragment.class.getSimpleName();
    @BindView(R.id.rec_goods)
    RecyclerView recGoods;
    @BindView(R.id.refresh_home)
    SmartRefreshLayout refreshHome;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.img_dingbu)
    ImageView imgDingbu;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.con_dingbu)
    ConstraintLayout conDingbu;
    @BindView(R.id.flot)
    ImageView flot;
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
    private QyandYHJBean.DataBean.GroupbuyBean groupbuy;
    private QyandYHJBean.DataBean.CouponBean userlevel;
    private QyandYHJBean.DataBean.CouponBean userlevelno;
    private boolean is_show_kj = false;
    private boolean is_show_qy = false;
    private int type = 1;
    private List<QyandYHJBean.DataBean.ShareBean> share;
    private ProgressDialog mProgressDialog;


//    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onLazyLoad() {
        load();
        loadyhq();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
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
        mAdapter.register(QyandYHJBean.DataBean.CouponBean.class, new ItemLingQuanViewBinder(this));
        //  mAdapter.register(QyandYHJBean.DataBean.ShareBean.class, new ItemShareViewBinder(this));
        mAdapter.register(QyandYHJBean.DataBean.class, new ItemImageViewBinder(this));
        mAdapter.register(GoodsItem.class, new ItemGoodsLongViewBinder(mActivity));
        mAdapter.register(QyandYHJBean.DataBean.GroupbuyBean.class, new ItemQiYeViewBinder(this));
        mDecoration = new HomeGridItemDecoration(mActivity, 2, 6, true);
        mDecoration.setHeaderCount(3);
        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new GridLayoutManager(mActivity, 2);

       /* mSizeLookup = new HomeListSpanSizeLookup(mLayoutManager, 1);
        mLayoutManager.setSpanSizeLookup(mSizeLookup);*/
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                switch (itemViewType) {
                    case 0:
                        return 2;
                    case 1:
                        return 2;
                    case 2:
                        return 2;
                    case 3:
                        return 1;
                    case 4:
                        return 2;
                }
                return 0;
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //  Glide.with(BasicApp.getContext()).resumeRequests();
                        if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                            ViewUtils.hideView(conDingbu);
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //  Glide.with(BasicApp.getContext()).pauseRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        //  Glide.with(BasicApp.getContext()).resumeRequests();
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    ViewUtils.showView(conDingbu);
                } else {
                    ViewUtils.hideView(conDingbu);
                }

            }
        });
        flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(mActivity, ProdActivity.class);
                intentProd.putExtra("bannerData", "6904121452");
                mActivity.startActivity(intentProd);
            }
        });
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
                intentProd.putExtra("share_info", mEssayBean.getData().getShare_info());
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
        switch (bannerDataBean.getType()) {
            case "0":
                Intent intentProd = new Intent(mActivity, ProdActivity.class);
                intentProd.putExtra("bannerData", bannerDataBean.getLink());
                mActivity.startActivity(intentProd);
                break;
            case "1":
                mViewModel.essay(bannerDataBean.getLink(), "2", "" + AppUtils.getVersionCode(mActivity)).observe(this, essayObserver);
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

        mRefreshLayout.finishLoadMore();

    }

    private void loadyhq() {
        MineViewModel mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        if (mUserInfo != null) {
            mViewModel.isyhjdailing("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, misyhjObserver);
        } else {
            mViewModel.isyhjdailing("2", "", "", "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, misyhjObserver);
        }
    }

    private void load() {
        if (mUserInfo != null) {
            mViewModel.getyhjandqiye("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(mActivity)).observe(_HomeFragment.this, YhjandQymObserver);
        } else {
            mViewModel.getyhjandqiye("2", "", "", "" + AppUtils.getVersionName(mActivity)).observe(_HomeFragment.this, YhjandQymObserver);
        }

    }


    private Observer<Status<BaseList<BannerDataBean>>> mObserver = new Observer<Status<BaseList<BannerDataBean>>>() {

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
                        if (is_show_kj) {
                            if (mUserInfo != null) {
                                if (mUserInfo.getVip_level() == 0) {
                                    mItems.add(userlevelno);
                                } else {
                                    mItems.add(userlevel);
                                }
                            } else {
                                mItems.add(userlevelno);
                            }
                        } else {
                            QyandYHJBean.DataBean.CouponBean couponBean = new QyandYHJBean.DataBean.CouponBean();
                            couponBean.setAlias("pidan");
                            mItems.add(couponBean);
                        }
                        QyandYHJBean.DataBean dataBean = new QyandYHJBean.DataBean();
                        dataBean.setShare(share);
                        mItems.add(dataBean);
                        if (mUserInfo != null) {
                            mViewModel.getGeneralGoods("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(mActivity), Status.LOAD_REFRESH).observe(_HomeFragment.this, GoodsmObserver);
                        } else {
                            mViewModel.getGeneralGoods("2", "", "", "" + AppUtils.getVersionCode(mActivity), Status.LOAD_REFRESH).observe(_HomeFragment.this, GoodsmObserver);
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
                            if (is_show_qy) {
                                mItems.add(groupbuy);
                            } else {
                                QyandYHJBean.DataBean.GroupbuyBean groupbuyBean = new QyandYHJBean.DataBean.GroupbuyBean();
                                groupbuyBean.setAlias("pidan");
                                mItems.add(groupbuyBean);
                            }
                            // loadtype(type);
                            mAdapter.notifyDataSetChanged();
                            mRefreshLayout.finishRefresh();
                        } else {
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
        mMinId = 1;
        load();

    }

    @Override
    public void onLingQuanClick(QyandYHJBean.DataBean.CouponBean DataBean) {
        if (AppUtils.isAllowPermission(mActivity)) {
            Intent intentProd1 = new Intent(mActivity, Agreement2Activity.class);
            intentProd1.putExtra("url", DataBean.getLink() + "?mid=" + mUserInfo.getId());
            mActivity.startActivity(intentProd1);
        }


    }

    private Observer<Status<ResponseBody>> YhjandQymObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }
    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            QyandYHJBean mQyandYHJBean = new Gson().fromJson(b, QyandYHJBean.class);
            if (mQyandYHJBean.getCode() == 1) {
                share = mQyandYHJBean.getData().getShare();
                mViewModel.getBanner("2", "" + AppUtils.getVersionCode(mActivity)).observe(this, mObserver);
                if (mQyandYHJBean.getData().getCoupon_show() == 1) {
                    is_show_kj = true;
                    userlevel = mQyandYHJBean.getData().getCoupon().get(0);
                    userlevelno = mQyandYHJBean.getData().getCoupon().get(1);
                } else {
                    is_show_kj = false;
                }
                if (mQyandYHJBean.getData().getGroup_show() == 1) {
                    is_show_qy = true;
                    groupbuy = mQyandYHJBean.getData().getGroupbuy();
                } else {
                    is_show_qy = false;
                }


            } else {
                FToast.error(mQyandYHJBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    public void onQiYeClick(QyandYHJBean.DataBean.GroupbuyBean DataBean) {
        Intent intentProd1 = new Intent(mActivity, Agreement2Activity.class);
        intentProd1.putExtra("url", DataBean.getLink());
        mActivity.startActivity(intentProd1);
    }


    //切换此页面请求是否有券
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickMyEvent(ClickMyEvent event) {
        if (event.getCode() == ClickMyEvent.CHECK_ITEM0) {
            MineViewModel mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
            if (mUserInfo != null) {
                mViewModel.isyhjdailing("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(BasicApp.getContext())
                ).observe(this, misyhjObserver);
            } else {
                mViewModel.isyhjdailing("2", "", "", "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, misyhjObserver);
            }
        }

    }

    private Observer<Status<ResponseBody>> misyhjObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                operaYhq(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void operaYhq(ResponseBody body) {
        try {
            String b = body.string();
            IsYhjLingBean mIsYhjLingBean = new Gson().fromJson(b, IsYhjLingBean.class);
            if (mIsYhjLingBean.getCode() == 1) {
                if (mIsYhjLingBean.getData().getIs_coupon() == 1) {
                    IsYhjLingBean.DataBean data = mIsYhjLingBean.getData();
                    if (mIsYhjLingBean.getData().getType() == -1) {
                        if (SharedPrefUtils.getyhqtype() == 0) {
                            new XPopup.Builder(mActivity)
                                    .dismissOnTouchOutside(false)
                                    .dismissOnBackPressed(false)
                                    .asCustom(new TipsPopup_kaquan(mActivity, data, new TipsPopup_kaquan.OnExitListener() {
                                        @Override
                                        public void onPositive() {
                                            if (AppUtils.isAllowPermission(mActivity)) {

                                            }
                                        }
                                    }))
                                    .show();
                        }
                        SharedPrefUtils.saveyhqtype(1);
                    } else {
                        new XPopup.Builder(mActivity)
                                .dismissOnTouchOutside(false)
                                .dismissOnBackPressed(false)
                                .asCustom(new TipsPopup_kaquan(mActivity, data, new TipsPopup_kaquan.OnExitListener() {
                                    @Override
                                    public void onPositive() {
                                        if (AppUtils.isAllowPermission(mActivity)) {
                                            Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_HOME);
                                            LocalBroadcastManager.getInstance(mActivity)
                                                    .sendBroadcast(intentRank);
                                        }
                                    }
                                }))
                                .show();

                    }

                }
            } else {
                FToast.error(mIsYhjLingBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    public void onShareClick(QyandYHJBean.DataBean.ShareBean DataBean) {
        if (DataBean.getType() == 0) {
            if (AppUtils.isAllowPermission(mActivity)) {
                mViewModel.shareimg("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), DataBean.getJump_id(), "" + AppUtils.getVersionCode(mActivity)).observe(mActivity, mObservershare);
                //ViewUtils.showView(progressBar);
                mProgressDialog = ProgressDialog.show(mActivity, "正在生成海报", "请稍等...");
            }
        } else if (DataBean.getType() == 1) {
            mViewModel.essay(DataBean.getJump_id(), "2", "" + AppUtils.getVersionCode(mActivity)).observe(this, essayObserver);
        }
    }

    private Observer<Status<ResponseBody>> mObservershare = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                //ViewUtils.hideView(progressBar);
                mProgressDialog.dismiss();
                ResponseBody body = status.content;
                opera3(body);
                break;
            case Status.ERROR:
                // ViewUtils.hideView(progressBar);
                mProgressDialog.dismiss();
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera3(ResponseBody body) {
        try {
            String b = body.string();
            ShareHomeImgBean mMyTeamslBean = new Gson().fromJson(b, ShareHomeImgBean.class);
            if (mMyTeamslBean.getCode() == 1) {
                ShareHomeImgBean.DataBean data = mMyTeamslBean.getData();
                Intent intentProd = new Intent(mActivity, CenterLingQuanActivity2.class);
                intentProd.putExtra("Data", data);
                mActivity.startActivity(intentProd);
            } else if (mMyTeamslBean.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(mActivity);
                FToast.error(mMyTeamslBean.getInfo());
            } else {
                FToast.error(mMyTeamslBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.con_dingbu)
    public void onViewClicked() {
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onShare1Click(QyandYHJBean.DataBean.ShareBean bean) {
        if (AppUtils.isAllowPermission(mActivity)) {
            mViewModel.sharehaobao("2", mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(mActivity)).observe(this, mObservershare);
            //ViewUtils.showView(progressBar);
            mProgressDialog = ProgressDialog.show(mActivity, "正在生成海报", "请稍等...");
        }
    }

    @Override
    public void onShare2Click(QyandYHJBean.DataBean.ShareBean bean) {
        int type = bean.getType();
        if(type==1){
            mViewModel.essay(bean.getJump_id(), "2", "" + AppUtils.getVersionCode(mActivity)).observe(this, essayObserver);
        }else if(type==3){
            if (AppUtils.isAllowPermission(mActivity)) {
                startActivity(new Intent(mActivity, QianDaoActivity.class));
            }
        }

    }

    @Override
    public void onShare3Click(QyandYHJBean.DataBean.ShareBean bean) {
            Intent intentProd = new Intent(mActivity, ProdActivity.class);
            intentProd.putExtra("bannerData", bean.getGood_id());
            mActivity.startActivity(intentProd);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEssayEventMessage(EssayEvent event) {
        String code = event.getCode();
        mViewModel.essay(code, "2", "" + AppUtils.getVersionCode(mActivity)).observe(this, essayObserver);
    }


}

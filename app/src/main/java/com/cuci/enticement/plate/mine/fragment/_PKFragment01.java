package com.cuci.enticement.plate.mine.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.Constant;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.PKbean1;
import com.cuci.enticement.bean.PKbean3;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.PKEvent;
import com.cuci.enticement.event.PKEvent1;
import com.cuci.enticement.event.PKEvent3;
import com.cuci.enticement.plate.mine.adapter.ItemPKViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemPKViewBinder1;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
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
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 首页外层Fragment
 */
public class _PKFragment01 extends BaseFragment implements OnRefreshLoadMoreListener, ItemPKViewBinder1.OnPKClickListener {

    private static final String TAG = _PKFragment01.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.text_num1)
    TextView textNum1;
    @BindView(R.id.img_huangguan1)
    ImageView imgHuangguan1;
    @BindView(R.id.img_tuxiang)
    CircleImageView imgTuxiang;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.text_shuliang)
    TextView textShuliang;
    @BindView(R.id.img_huangguan2)
    ImageView imgHuangguan2;
    @BindView(R.id.img_huangguan3)
    ImageView imgHuangguan3;
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

    public static _PKFragment01 newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        _PKFragment01 fragment = new _PKFragment01();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected void onLazyLoad() {
      //  refreshLayout.autoRefresh();
      load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pk;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        Bundle bundle = getArguments();
        mtype = bundle.getString("type");
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(PKbean1.DataBean.ListBean.class, new ItemPKViewBinder1(this));
        BrandItemDecoration mDecoration = new BrandItemDecoration(mActivity, 0,0);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        ishand=true;
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        ishand=false;
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0&&ishand) {
                    EventBus.getDefault().post(new PKEvent3("0"));
                }
                if (dy<0&&mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    EventBus.getDefault().post(new PKEvent3("1"));


                }

            }
        });

    }

    private void load() {
        page = 1;
        mViewModel.pk1(mUserInfo.getToken(), "" + mUserInfo.getId(), "2", "" + page, Status.LOAD_REFRESH).observe(this, mObserver);
        ViewUtils.showView(progressBar);
    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                statusView.showContent();
                ViewUtils.hideView(progressBar);
                ResponseBody body = status.content;
                opera1(body, status);
                break;
            case Status.ERROR:
                ViewUtils.hideView(progressBar);
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
            PKbean1 mPKbean1 = new Gson().fromJson(b, PKbean1.class);
            String total_amount = mPKbean1.getData().getMyself().getTotal_amount();
            String headimg = mPKbean1.getData().getMyself().getHeadimg();
            String nickname = mPKbean1.getData().getMyself().getNickname();
            int ranking = mPKbean1.getData().getMyself().getRanking();
            if (ranking == 1) {
                imgHuangguan1.setVisibility(View.VISIBLE);
                imgHuangguan2.setVisibility(View.GONE);
                imgHuangguan3.setVisibility(View.GONE);
                textNum.setBackgroundResource(R.drawable.num_1);
            } else if (ranking == 2) {
                imgHuangguan1.setVisibility(View.GONE);
                imgHuangguan2.setVisibility(View.VISIBLE);
                imgHuangguan3.setVisibility(View.GONE);
                textNum.setBackgroundResource(R.drawable.num_2);
            } else if (ranking == 3) {
                imgHuangguan1.setVisibility(View.GONE);
                imgHuangguan2.setVisibility(View.GONE);
                imgHuangguan3.setVisibility(View.VISIBLE);
                textNum.setBackgroundResource(R.drawable.num_3);
            }else {
                imgHuangguan1.setVisibility(View.GONE);
                imgHuangguan2.setVisibility(View.GONE);
                imgHuangguan3.setVisibility(View.GONE);
                textNum.setBackgroundColor(Color.WHITE);
                ViewGroup.LayoutParams para1;
                para1 = textNum.getLayoutParams();
                para1.height= ViewGroup.LayoutParams.WRAP_CONTENT;
                para1.width= ViewGroup.LayoutParams.WRAP_CONTENT;
                textNum.setLayoutParams(para1);
            }
            textNum.setText("" + ranking);
            ImageLoader.loadPlaceholder1(headimg, imgTuxiang);
            textWenzi1.setText(nickname);
            textShuliang.setText("¥"+total_amount);
            //传送数据
            Constant.PK1=total_amount;
            EventBus.getDefault().post(new PKEvent1(total_amount, headimg, nickname));
            List<PKbean1.DataBean.ListBean> item = mPKbean1.getData().getList();
            if (item == null || item.size() == 0) {

                if (status.loadType == Status.LOAD_REFRESH) {
                    refreshLayout.finishRefresh();
                    statusView.showEmpty();
                } else {
                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mPKbean1.getCode() == 1) {

                page = mPKbean1.getData().getPage().getCurrent() + 1;
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
                FToast.warning(mPKbean1.getInfo());

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
            mViewModel.pk1(mUserInfo.getToken(), "" + mUserInfo.getId(), "2", "" + page, Status.LOAD_MORE).observe(this, mObserver);
            ViewUtils.showView(progressBar);
        } else {
            refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        load();
    }




    @Override
    public void onProdClick(PKbean1.DataBean.ListBean item) {

    }
}

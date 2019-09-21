package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissionjlBean;
import com.cuci.enticement.bean.CommissiontjBean;
import com.cuci.enticement.bean.OrderList;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemCommissionJLViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class CommissionActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.button_tixian)
    Button buttonTixian;
    @BindView(R.id.text_wenzi)
    TextView textWenzi;
    @BindView(R.id.text_yongjing)
    TextView textYongjing;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.text_yitixian)
    TextView textYitixian;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.text_wenzi2)
    TextView textWenzi2;
    @BindView(R.id.text_ketixian)
    TextView textKetixian;
    @BindView(R.id.con_huang)
    ConstraintLayout conHuang;
    @BindView(R.id.text_shanggeyue)
    TextView textShanggeyue;
    @BindView(R.id.text_rqi)
    TextView textRqi;
    @BindView(R.id.img_xiajiantou)
    ImageView imgXiajiantou;

    @BindView(R.id.text_xiageyue)
    TextView textXiageyue;
    @BindView(R.id.con_hui)
    ConstraintLayout conHui;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    @BindView(R.id.container)
    ConstraintLayout container;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private Items mItems;
    private MultiTypeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private  List<CommissionjlBean.DataBean.ListBean> mDatas=new ArrayList<>();
    private boolean mCanLoadMore = true;
    private String format;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commisson;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
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
        mAdapter.register(CommissionjlBean.DataBean.ListBean.class, new ItemCommissionJLViewBinder());
        CartItemDecoration mDecoration = new CartItemDecoration(this, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mViewModel.hqcommissiontj(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2")
                .observe(this, mObserver);
        long time = new Date().getTime();
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
         format = sdf.format(d);
        textRqi.setText(format);

        refreshLayout.autoRefresh();

        buttonTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(CommissionActivity.this, CashActivity.class);
                intentProd.putExtra("Data", "");
                startActivity(intentProd);
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void load() {
        if(mUserInfo==null){
            refreshLayout.finishRefresh();
            return;
        }
        mViewModel.hqcommissiontj(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2",format,Status.LOAD_REFRESH)
                .observe(this, mObserver1);
    }
    private Observer<Status<ResponseBody>> mObserver1 = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                statusView.showContent();

                ResponseBody body = status.content;
                opera1(body,status);
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
    private void opera1(ResponseBody body,Status status) {
        try {
            String b = body.string();
            CommissionjlBean mCommissionjlBean = new Gson().fromJson(b, CommissionjlBean.class);
            List<CommissionjlBean.DataBean.ListBean> item = mCommissionjlBean.getData().getList();
            if (item == null || item.size() == 0) {
                statusView.showEmpty();
                if (status.loadType == Status.LOAD_REFRESH) {

                    refreshLayout.finishRefresh();
                } else {

                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mCommissionjlBean.getCode()==1) {
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
            }else {
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    refreshLayout.finishLoadMore();
                } else {

                    refreshLayout.finishRefresh();
                }
                FToast.error(mCommissionjlBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }








    private Observer<Status<ResponseBody>> mObserver = status -> {

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
            CommissiontjBean mCommissiontjBean = new Gson().fromJson(b, CommissiontjBean.class);
            if (mCommissiontjBean.getCode()==1) {
                String subtract = MathExtend.subtract(String.valueOf(mCommissiontjBean.getData().getTotal()), String.valueOf(mCommissiontjBean.getData().getUsed()));
                textYongjing.setText(String.valueOf(mCommissiontjBean.getData().getTotal()));
                textYitixian.setText(String.valueOf(mCommissiontjBean.getData().getUsed()));
                textKetixian.setText(subtract);
            }else {
                FToast.error(mCommissiontjBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
     /*  if(mCanLoadMore){
            mCanLoadMore = false;
           mViewModel.getSource01(mtype, String.valueOf(page),PAGE_SIZE,
                    Status.LOAD_MORE).observe(this, mObserver);
        }else {

        }*/
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        load();

    }
}

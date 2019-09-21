package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissionjlBean;
import com.cuci.enticement.bean.CommissiontjBean;
import com.cuci.enticement.bean.MyTeamlbBean;
import com.cuci.enticement.bean.MyTeamslBean;
import com.cuci.enticement.bean.OrderList;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemCommissionJLViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemMyTeamViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class MyTeamActivity extends BaseActivity implements OnRefreshLoadMoreListener , ItemMyTeamViewBinder.OnProdClickListener {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_zhongrenshu)
    TextView textZhongrenshu;
    @BindView(R.id.text_xinzengrenshu)
    TextView textXinzengrenshu;
    @BindView(R.id.text_zhongrenshu1)
    TextView textZhongrenshu1;
    @BindView(R.id.text_xinzengrenshu1)
    TextView textXinzengrenshu1;
    @BindView(R.id.con_zhongbu)
    ConstraintLayout conZhongbu;
    @BindView(R.id.edt_shousuo)
    EditText edtShousuo;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private boolean mCanLoadMore=true;
    private int page=1;
    private String s;

    @Override
    public int getLayoutId() {
        return R.layout.activity_myteam;
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
        mAdapter.register(MyTeamlbBean.DataBean.ListBean.class, new ItemMyTeamViewBinder(this));
        CartItemDecoration mDecoration = new CartItemDecoration(this, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mViewModel.hqteamsl(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2")
                .observe(this, mObserver);

        refreshLayout.autoRefresh();
        s = edtShousuo.getText().toString();
        edtShousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                //如果actionId是搜索的id，则进行下一步的操作
                    load();
                }
                return false;
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
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
        if(TextUtils.isEmpty(s)){
            mViewModel.hqteamtj(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2",mUserInfo.getNickname(),"1", Status.LOAD_REFRESH)
                    .observe(this, mObserver1);
        }else {
            mViewModel.hqteamtj(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2",s,"1", Status.LOAD_REFRESH)
                    .observe(this, mObserver1);
        }

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
            MyTeamlbBean mMyTeamlbBean = new Gson().fromJson(b, MyTeamlbBean.class);
            List<MyTeamlbBean.DataBean.ListBean> item = mMyTeamlbBean.getData().getList();
            if (item == null || item.size() == 0) {
                statusView.showEmpty();
                if (status.loadType == Status.LOAD_REFRESH) {

                    refreshLayout.finishRefresh();
                } else {

                    refreshLayout.finishLoadMore();
                }
                return;
            }
            if (mMyTeamlbBean.getCode()==1) {
                page = mMyTeamlbBean.getData().getPage().getPages()+1;
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
                FToast.error(mMyTeamlbBean.getInfo());
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
            MyTeamslBean mMyTeamslBean = new Gson().fromJson(b, MyTeamslBean.class);
            if (mMyTeamslBean.getCode()==1) {
                textZhongrenshu.setText(""+mMyTeamslBean.getData().getTotal_all());
                textXinzengrenshu.setText(""+mMyTeamslBean.getData().getTotal_mon());
            }else {
                FToast.error(mMyTeamslBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
           if(mCanLoadMore){
            mCanLoadMore = false;
               mViewModel.hqteamtj(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2",mUserInfo.getNickname(),""+page, Status.LOAD_MORE)
                       .observe(this, mObserver1);
           }else {
               refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCanLoadMore = false;
        load();
    }

    @Override
    public void onProdClick(MyTeamlbBean.DataBean.ListBean item) {

    }
}
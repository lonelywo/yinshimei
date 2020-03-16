package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.ProCheckLqBean;
import com.cuci.enticement.bean.ProYhqBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.home.adapter.ProYhqViewBinder;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.CartItemDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.core.BottomPopupView;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class ProLingQuanTipsPopup extends BottomPopupView implements ProYhqViewBinder.OnProdYhqClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_cancel)
    ImageView imgCancel;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    ConstraintLayout container;
    private String mcancel;
    private Context mContext;
    private MultiTypeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private BrandItemDecoration mDecoration;
    private List<ProYhqBean.DataBean> datas;
    private KaQuanListBean.DataBean.ListBean item;
    private Items mItems;
    private UserInfo mUserInfo;
    private ProYhqViewBinder proYhqViewBinder;
    private ProYhqBean.DataBean mitem;


    public ProLingQuanTipsPopup(@NonNull Context context, List<ProYhqBean.DataBean> item, UserInfo userInfo) {
        super(context);
        mContext = context;
        datas = item;
        mUserInfo = userInfo;

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_checkkaquan_tips_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        proYhqViewBinder = new ProYhqViewBinder(this);
        mAdapter.register(ProYhqBean.DataBean.class,proYhqViewBinder);
        CartItemDecoration mDecoration = new CartItemDecoration(mContext, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mItems.clear();
        mItems.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.img_cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;


    @Override
    public void onProdClick(ProYhqBean.DataBean item) {
        load(item);
        mitem=item;
    }


    public interface OnExitListener {

        void onCommit(List<ProYhqBean.DataBean> item);
    }

    public void load(ProYhqBean.DataBean item) {
        HomeViewModel mHomeViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(HomeViewModel.class);
        mHomeViewModel.getprolingyhq("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), ""+item.getId(),""+ AppUtils.getVersionCode(mContext)).observe((LifecycleOwner) mContext, myhqLObserver);

    }


    private Observer<Status<ResponseBody>> myhqLObserver = status -> {

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
            ProCheckLqBean mProCheckLqBean = new Gson().fromJson(b, ProCheckLqBean.class);

            if (mProCheckLqBean.getCode() == 1) {
                FToast.success(mProCheckLqBean.getInfo());
              //  proYhqViewBinder.setdata(true);
                mitem.setIscheck(true);
                        mAdapter.notifyDataSetChanged();
            }else if(mProCheckLqBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(mContext);
                dismiss();
                FToast.error(mProCheckLqBean.getInfo());
            } else {
                FToast.error(mProCheckLqBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


}

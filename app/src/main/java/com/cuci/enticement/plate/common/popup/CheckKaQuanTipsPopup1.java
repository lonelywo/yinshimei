package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.plate.cart.adapter.OrderProYhqViewBinder;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.CartItemDecoration;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class CheckKaQuanTipsPopup1 extends BottomPopupView implements OrderProYhqViewBinder.OnProdYhqClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    ConstraintLayout container;
    private String mcancel;
    private Context mContext;
    private MultiTypeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private BrandItemDecoration mDecoration;
    private List<KaQuanListBean.DataBean.ListBean> datas;
    private KaQuanListBean.DataBean.ListBean mitem;
    private Items mItems;
    private OrderProYhqViewBinder proYhqViewBinder;

    public CheckKaQuanTipsPopup1(@NonNull Context context, List<KaQuanListBean.DataBean.ListBean> item, OnExitListener listener) {
        super(context);
        mContext = context;
        mOnExitListener = listener;
        datas = item;
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_order_checkkaquan_tips_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
       /* mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mAdapter = new SingleAdapter(datas);
        mAdapter.setOnItemClickLitener(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mDecoration = new BrandItemDecoration(mContext, 2, 12);
        recyclerView.addItemDecoration(mDecoration);
        recyclerView.setAdapter(mAdapter);*/

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        proYhqViewBinder = new OrderProYhqViewBinder(this);
        mAdapter.register(KaQuanListBean.DataBean.ListBean.class,proYhqViewBinder);
        CartItemDecoration mDecoration = new CartItemDecoration(mContext, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mItems.clear();
        mItems.addAll(datas);
        mAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.tv_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                if (mOnExitListener != null) {
                    if (mitem != null) {
                        mOnExitListener.onCommit(mitem);
                    }
                }
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;



    @Override
    public void onProdClick(KaQuanListBean.DataBean.ListBean data) {
        mitem=data;
        mAdapter.notifyDataSetChanged();
    }

    public interface OnExitListener {

        void onCommit(KaQuanListBean.DataBean.ListBean item);
    }

}

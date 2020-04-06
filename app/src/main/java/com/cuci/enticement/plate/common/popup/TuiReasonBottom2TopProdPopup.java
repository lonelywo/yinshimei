package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.TuiKuanWuLiuBean;
import com.cuci.enticement.plate.cart.adapter.OrderProYhqViewBinder;
import com.cuci.enticement.plate.cart.adapter.TuiKuanWuLiuViewBinder;
import com.cuci.enticement.plate.mall.adapter.SingleAdapter;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.DimensionUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.CartItemDecoration;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class TuiReasonBottom2TopProdPopup extends BottomPopupView implements TuiKuanWuLiuViewBinder.OnProdYhqClickListener{

    private static final String TAG = TuiReasonBottom2TopProdPopup.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cancel_tv)
    ImageView cancelTv;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private OrderViewModel mViewModel;

    private int mScreenWidth;
    private Context mContext;


    @BindView(R.id.container)
    ConstraintLayout mContainer;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private List<TuiKuanWuLiuBean.DataBean.ExpressBean> mExpressBean;


    public TuiReasonBottom2TopProdPopup(@NonNull Context context,List<TuiKuanWuLiuBean.DataBean.ExpressBean> ExpressBean, OnCommitClickListener OnCommitClickListener) {
        super(context);
        mContext = context;
        mExpressBean=ExpressBean;

        mOnCommitClickListener = OnCommitClickListener;
    }


    @Override
    protected int getImplLayoutId() {

        return R.layout.popup_share_bottom_to_top_tui_reason;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(TuiKuanWuLiuBean.DataBean.ExpressBean.class,new TuiKuanWuLiuViewBinder(this));
        CartItemDecoration mDecoration = new CartItemDecoration(mContext, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mItems.clear();
        mItems.addAll(mExpressBean);
        mAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.cancel_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_tv:
                dismiss();
                break;
        }
    }

    @Override
    public void onProdClick(TuiKuanWuLiuBean.DataBean.ExpressBean item) {
        if (mOnCommitClickListener != null) {
            mOnCommitClickListener.onCommitClick(item);
            dismiss();
        }
    }


/*
      if (mOnCommitClickListener != null) {
        mOnCommitClickListener.onCommitClick(mType);
        dismiss();
    }
*/


    public interface OnCommitClickListener {

        void onCommitClick(TuiKuanWuLiuBean.DataBean.ExpressBean sex);

    }

    private OnCommitClickListener mOnCommitClickListener;


}

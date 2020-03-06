package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.IsYhjLingBean;
import com.cuci.enticement.bean.ProYhqBean;
import com.cuci.enticement.plate.home.adapter.ProYhqViewBinder;
import com.cuci.enticement.plate.mine.adapter.IsHaveYhqViewBinder;
import com.cuci.enticement.widget.CartItemDecoration;
import com.lxj.xpopup.core.CenterPopupView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class TipsPopup_kaquan extends CenterPopupView implements IsHaveYhqViewBinder.OnProdYhqClickListener{

    private final Context mcontext;
    @BindView(R.id.desc_title)
    TextView desc_title;
    @BindView(R.id.img_yhq_xian)
    ImageView imgYhqXian;
    @BindView(R.id.text_tips)
    TextView textTips;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_shiyong)
    TextView textShiyong;
    private IsYhjLingBean.DataBean mdata;

    @BindView(R.id.cancel)
    ImageView cancel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private IsHaveYhqViewBinder mIsHaveYhqViewBinder;

    public TipsPopup_kaquan(@NonNull Context context, IsYhjLingBean.DataBean data, OnExitListener listener) {
        super(context);
        mOnExitListener = listener;
        mdata = data;
        mcontext=context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_tips_kaquan;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        String button = mdata.getCoupons_text().getButton();
        String desc = mdata.getCoupons_text().getDesc();
        String title = mdata.getCoupons_text().getTitle();
        desc_title.setText(title);
        textShiyong.setText(button);
        textTips.setText(desc);

        List<IsYhjLingBean.DataBean.CouponsBean> coupons = mdata.getCoupons();
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mIsHaveYhqViewBinder = new IsHaveYhqViewBinder(this);
        mAdapter.register(IsYhjLingBean.DataBean.CouponsBean.class,mIsHaveYhqViewBinder);
        CartItemDecoration mDecoration = new CartItemDecoration(mcontext, 4);
        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mItems.clear();
        mItems.addAll(coupons);
        mAdapter.notifyDataSetChanged();

    }

    @OnClick({R.id.cancel, R.id.text_shiyong})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.text_shiyong:
                if (mOnExitListener != null) {
                    mOnExitListener.onPositive();
                }
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;

    @Override
    public void onProdClick(ProYhqBean.DataBean item) {

    }

    public interface OnExitListener {
        void onPositive();
    }

}

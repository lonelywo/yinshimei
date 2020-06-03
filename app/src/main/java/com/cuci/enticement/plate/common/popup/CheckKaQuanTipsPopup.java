package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.plate.mall.adapter.SingleAdapter;
import com.cuci.enticement.widget.BrandItemDecoration;
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

public class CheckKaQuanTipsPopup extends BottomPopupView implements SingleAdapter.OnItemClickLitener {


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
    private SingleAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private BrandItemDecoration mDecoration;
    private List<KaQuanListBean.DataBean.ListBean> datas;
    private KaQuanListBean.DataBean.ListBean item;

    public CheckKaQuanTipsPopup(@NonNull Context context, List<KaQuanListBean.DataBean.ListBean> item, OnExitListener listener) {
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
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mAdapter = new SingleAdapter(datas);
        mAdapter.setOnItemClickLitener(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mDecoration = new BrandItemDecoration(mContext, 2, 12);
        recyclerView.addItemDecoration(mDecoration);
        recyclerView.setAdapter(mAdapter);

    }

    @OnClick({R.id.tv_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                if (mOnExitListener != null) {
                    if (item != null) {
                        mOnExitListener.onCommit(item);
                    }else {
                        mOnExitListener.onCommit(datas.get(0));
                    }
                }
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;

    @Override
    public void onItemClick(KaQuanListBean.DataBean.ListBean datas, int position) {
        mAdapter.setSelection(position);
        item = datas;
    }

    public interface OnExitListener {

        void onCommit(KaQuanListBean.DataBean.ListBean item);
    }

}

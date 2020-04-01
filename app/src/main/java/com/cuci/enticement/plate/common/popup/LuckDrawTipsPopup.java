package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.LuckDrawBean;
import com.cuci.enticement.utils.ImageLoader;
import com.lxj.xpopup.core.CenterPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LuckDrawTipsPopup extends CenterPopupView {


    @BindView(R.id.img_cancel)
    ImageView imgCancel;
    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_commit)
    Button tvCommit;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    ConstraintLayout container;
    private String mcancel;
    private Context mContext;
    private LuckDrawBean.DataBean mitem;


    public LuckDrawTipsPopup(@NonNull Context context, LuckDrawBean.DataBean item, OnExitListener listener) {
        super(context);
        mContext = context;
        mOnExitListener = listener;
        mitem = item;
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_luckdraw_tips_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        ImageLoader.loadPlaceholder(mitem.getLogo(),imgTupian);
        tvDesc.setText(mitem.getDesc());
        tvMsg.setText(mitem.getMsg());
    }

    @OnClick({R.id.tv_commit, R.id.img_cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                if (mOnExitListener != null) {
                    mOnExitListener.onCommit(mitem);
                }
                dismiss();
                break;
            case R.id.img_cancel:
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;


    public interface OnExitListener {

        void onCommit(LuckDrawBean.DataBean item);
    }

}

package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.lxj.xpopup.core.BottomPopupView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharegoodsImgTipsPopup extends BottomPopupView {


    @BindView(R.id.tv_share_wx)
    TextView tvShareWx;
    @BindView(R.id.icon_share_save)
    TextView iconShareSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.container)
    LinearLayout container;
    private String mcancel;
    private Context mContext;
    public SharegoodsImgTipsPopup(@NonNull Context context, String cancel, OnExitListener listener) {
        super(context);
        mContext = context;
        mOnExitListener = listener;
        mcancel = cancel;
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_sharegoods_img_tips_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        tvCancel.setText(mcancel);
    }

    @OnClick({R.id.tv_share_wx,R.id.icon_share_save, R.id.tv_cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_share_wx:
                if (mOnExitListener != null) {
                    mOnExitListener.onPositive1();
                }
                dismiss();
                break;
            case R.id.icon_share_save:
                if (mOnExitListener != null) {
                    mOnExitListener.onPositive2();
                }
                dismiss();
                break;
            case R.id.tv_cancel:
                if (mOnExitListener != null) {
                    mOnExitListener.onCancel();
                }
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;

    public interface OnExitListener {
        void onPositive1();
        void onPositive2();
        void onCancel();
    }

}

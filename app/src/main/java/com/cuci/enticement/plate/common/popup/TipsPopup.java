package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cuci.enticement.R;
import com.lxj.xpopup.core.CenterPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TipsPopup extends CenterPopupView {


    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.ok)
    TextView ok;
    private String mDesc;
    private String mcancel;
    private String mok ;

    public TipsPopup(@NonNull Context context, String desc,String cancel,String ok, OnExitListener listener) {
        super(context);
        mOnExitListener = listener;
        mDesc = desc;
        mcancel = cancel;
        mok = ok;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_tips_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        desc.setText(mDesc);
        cancel.setText(mcancel);
        ok.setText(mok);
    }

    @OnClick({R.id.ok, R.id.cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ok:
                if (mOnExitListener != null) {
                    mOnExitListener.onPositive();
                }
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    private OnExitListener mOnExitListener;

    public interface OnExitListener {
        void onPositive();
    }

}

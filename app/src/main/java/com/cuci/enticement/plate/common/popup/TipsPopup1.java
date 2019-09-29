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

public class TipsPopup1 extends CenterPopupView {


    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.cancel)
    TextView cancel;

    private String mDesc;
    private String mcancel;

    public TipsPopup1(@NonNull Context context, String desc, String cancel,  OnExitListener listener) {
        super(context);
        mOnExitListener = listener;
        mDesc = desc;
        mcancel = cancel;


    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_tips_view1;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        desc.setText(mDesc);
        cancel.setText(mcancel);

    }

    @OnClick({R.id.cancel})
    public void onViewClick(View view) {
        switch (view.getId()) {
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

package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.cuci.enticement.R;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.lxj.xpopup.core.CenterPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JGPYPopup extends CenterPopupView {


    @BindView(R.id.top)
    ImageView top;
    @BindView(R.id.close)
    ImageView close;
    private Context mContext;


    public JGPYPopup(@NonNull Context context, OnUpdateListener listener) {
        super(context);
        mContext = context;
        mOnUpdateListener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_jgpy_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.top, R.id.close})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.top:
                if (mOnUpdateListener != null) {
                    mOnUpdateListener.updateNow();
                }
                dismiss();
                break;
            case R.id.close:
                dismiss();
                break;
        }
    }

    private OnUpdateListener mOnUpdateListener;

    public interface OnUpdateListener {
        void updateNow();
    }

}

package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.lxj.xpopup.core.CenterPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePopup extends CenterPopupView {


    @BindView(R.id.top)
    ImageView top;
    @BindView(R.id.close)
    ImageView mClose;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.ignore)
    TextView mIgnore;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.update)
    TextView update;
    private Context mContext;
    private Version mVersion;

    public UpdatePopup(@NonNull Context context, Version version, OnUpdateListener listener) {
        super(context);
        mContext = context;
        mVersion = version;
        mOnUpdateListener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_update_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);

        mContent.setText(mVersion.getData().getContent());

        if (mVersion.getData().getIs_force() == 1) {
            ViewUtils.hideView(mClose);
            ViewUtils.hideView(mIgnore);
        }

    }

    @OnClick({R.id.update, R.id.close, R.id.ignore})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.update:
                if (mOnUpdateListener != null) {
                    mOnUpdateListener.updateNow();
                }
                dismiss();
                break;
            case R.id.close:
                dismiss();
                break;
            case R.id.ignore:
                SharedPrefUtils.saveIgnoreVersion(mVersion.getData().getVersionName());
                dismiss();
                break;
        }
    }

    private OnUpdateListener mOnUpdateListener;

    public interface OnUpdateListener {
        void updateNow();
    }

}

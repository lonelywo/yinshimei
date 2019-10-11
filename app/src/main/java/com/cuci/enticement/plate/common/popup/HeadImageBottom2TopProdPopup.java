package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.DimensionUtils;
import com.lxj.xpopup.core.BottomPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeadImageBottom2TopProdPopup extends BottomPopupView {

    private static final String TAG = HeadImageBottom2TopProdPopup.class.getSimpleName();

    private OrderViewModel mViewModel;

    private int mScreenWidth;
    private Context mContext;


    @BindView(R.id.container)
    ConstraintLayout mContainer;



    public HeadImageBottom2TopProdPopup(@NonNull Context context, OnCommitClickListener OnCommitClickListener) {
        super(context);
        mContext = context;
        mScreenWidth = DimensionUtils.getScreenWidth(context);
        mOnCommitClickListener = OnCommitClickListener;
    }


    @Override
    protected int getImplLayoutId() {

        return R.layout.popup_share_bottom_to_top_headimage;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);


        ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
        layoutParams.width = mScreenWidth;
        mContainer.setLayoutParams(layoutParams);

    }

    @OnClick({R.id.photo_tv, R.id.images_tv, R.id.cancel_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.photo_tv:
                if (mOnCommitClickListener != null) {
                    mOnCommitClickListener.onCommitClick(1);
                    dismiss();
                }
                break;
            case R.id.images_tv:
                if (mOnCommitClickListener != null) {
                    mOnCommitClickListener.onCommitClick(2);
                    dismiss();
                }
                break;
            case R.id.cancel_tv:
                dismiss();
                break;
        }
    }


    public interface OnCommitClickListener {

        void onCommitClick(int type);

    }

    private OnCommitClickListener mOnCommitClickListener;


}

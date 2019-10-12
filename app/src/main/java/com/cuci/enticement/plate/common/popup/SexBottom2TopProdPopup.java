package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.DimensionUtils;
import com.lxj.xpopup.core.BottomPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SexBottom2TopProdPopup extends BottomPopupView {

    private static final String TAG = SexBottom2TopProdPopup.class.getSimpleName();
    @BindView(R.id.man_tv)
    TextView manTv;
    @BindView(R.id.women_tv)
    TextView womenTv;

    private OrderViewModel mViewModel;

    private int mScreenWidth;
    private Context mContext;


    @BindView(R.id.container)
    ConstraintLayout mContainer;


    public SexBottom2TopProdPopup(@NonNull Context context, OnCommitClickListener OnCommitClickListener) {
        super(context);
        mContext = context;

        mScreenWidth = DimensionUtils.getScreenWidth(context);
        mOnCommitClickListener = OnCommitClickListener;
    }


    @Override
    protected int getImplLayoutId() {

        return R.layout.popup_share_bottom_to_top_sex;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);


        ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
        layoutParams.width = mScreenWidth;
        mContainer.setLayoutParams(layoutParams);

    }

    @OnClick({R.id.man_tv, R.id.women_tv, R.id.cancel_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.man_tv:
                if (mOnCommitClickListener != null) {
                    mOnCommitClickListener.onCommitClick(1);
                    dismiss();
                }
                break;
            case R.id.women_tv:
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


/*
      if (mOnCommitClickListener != null) {
        mOnCommitClickListener.onCommitClick(mType);
        dismiss();
    }
*/


    public interface OnCommitClickListener {

        void onCommitClick(int type);

    }

    private OnCommitClickListener mOnCommitClickListener;


}

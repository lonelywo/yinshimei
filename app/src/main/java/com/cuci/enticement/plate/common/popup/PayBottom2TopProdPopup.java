package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.DimensionUtils;
import com.lxj.xpopup.core.BottomPopupView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayBottom2TopProdPopup extends BottomPopupView {

    private static final String TAG = PayBottom2TopProdPopup.class.getSimpleName();
    @BindView(R.id.ali_iv)
    ImageView aliIv;
    @BindView(R.id.wechat_iv)
    ImageView wechatIv;
    @BindView(R.id.text_money)
    TextView textMoney;

    private OrderViewModel mViewModel;

    private int mScreenWidth;
    private Context mContext;

    private String mTotalMoney;

    @BindView(R.id.container)
    ConstraintLayout mContainer;


    private int mType = 2;


    public PayBottom2TopProdPopup(@NonNull Context context, String totalMoney, OnCommitClickListener OnCommitClickListener) {
        super(context);
        mContext = context;
        mTotalMoney = totalMoney;
        mScreenWidth = DimensionUtils.getScreenWidth(context);
        mOnCommitClickListener = OnCommitClickListener;
    }


    @Override
    protected int getImplLayoutId() {

        return R.layout.popup_share_bottom_to_top_view_quzhifu;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);


        ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
        layoutParams.width = mScreenWidth;
        mContainer.setLayoutParams(layoutParams);
        textMoney.setText(mTotalMoney);
    }


    @OnClick(R.id.tv_commit)
    public void onViewClicked() {

        if (mOnCommitClickListener != null) {

            dismiss();
        }
    }

    @OnClick({R.id.con_fangshi1, R.id.con_fangshi2, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.con_fangshi1:
                mType=2;

                aliIv.setImageResource(R.drawable.xuanzhong);
                wechatIv.setImageResource(R.drawable.noxuanzhong);
                break;
            case R.id.con_fangshi2:
                mType=1;
                aliIv.setImageResource(R.drawable.noxuanzhong);
                wechatIv.setImageResource(R.drawable.xuanzhong);
                break;
            case R.id.tv_commit:
                if (mOnCommitClickListener != null) {
                    mOnCommitClickListener.onCommitClick(mType);
                    dismiss();
                }

                break;
        }



    }


    public interface OnCommitClickListener {

        void onCommitClick(int type);

    }

    private OnCommitClickListener mOnCommitClickListener;


}

package com.example.enticement.plate.common.popup;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.example.enticement.bean.HomeDetailsBean;
import com.example.enticement.utils.DimensionUtils;
import com.lxj.xpopup.core.BottomPopupView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareBottom2TopProdPopup extends BottomPopupView {

    private static final String TAG = ShareBottom2TopProdPopup.class.getSimpleName();

    private HomeDetailsBean.DataBean mItem;

    private int mScreenWidth;
    private Context mContext;
    //code用来区分是购物车还是立即购买
    private int mCode;

   @BindView(R.id.container)
   ConstraintLayout mContainer;
    public ShareBottom2TopProdPopup(@NonNull Context context, HomeDetailsBean.DataBean item, int code) {
        super(context);
        mContext = context;
        mItem = item;
        mScreenWidth = DimensionUtils.getScreenWidth(context);
        mCode=code;
    }



    @Override
    protected int getImplLayoutId() {
        //todo 这里把你写的布局填在里面
        return R.layout.popup_share_bottom_to_top_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        //todo 这里把最外层控件换成你布局里面的

       ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
        layoutParams.width = mScreenWidth;
        mContainer.setLayoutParams(layoutParams);

       // this.setFinishOnTouchOutside(true);

    }


}

package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.annotation.NonNull;


import com.cuci.enticement.R;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.bean.FunctionModel;
import com.cuci.enticement.utils.WxShareUtils;
import com.lxj.xpopup.core.CenterPopupView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CenterShareAppPopup extends CenterPopupView {

    private FunctionModel mFunctionModel;
    private Context mContext;

    public CenterShareAppPopup(@NonNull Context context, FunctionModel model) {
        super(context);
        mContext = context;
        mFunctionModel = model;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_app_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment,
            R.id.tv_share_qq, R.id.tv_share_qzone, R.id.close})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_share_wx:
                Bitmap bitmap = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.drawable.tuxiang);
                WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_SESSION,
                        mFunctionModel.getKeyword(), mContext.getString(R.string.app_name),
                        mFunctionModel.getContent(), bitmap);
                dismiss();
                break;
            case R.id.tv_share_moment:
                Bitmap bitmap1 = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.drawable.tuxiang);
                WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_TIME_LINE,
                        mFunctionModel.getKeyword(), mContext.getString(R.string.app_name),
                        mFunctionModel.getContent(), bitmap1);
                dismiss();
                break;
        /*    case R.id.tv_share_qq:
                //分享给QQ好友
                Intent intent = new Intent(mContext, QQShareActivity.class);
                intent.putExtra(QQShareActivity.DATA_TYPE, QQShareActivity.SHARE_QQ);
                intent.putExtra(QQShareActivity.DATA_TITLE, mContext.getString(R.string.app_name));
                intent.putExtra(QQShareActivity.DATA_LINK, mFunctionModel.getKeyword());
                intent.putExtra(QQShareActivity.DATA_DESC, mFunctionModel.getContent());
                intent.putExtra(QQShareActivity.DATA_IMAGE, "http://img.fqapps.com/d69e39dc4d75c27e3759a1e159932d4f-310x310");
                mContext.startActivity(intent);
                dismiss();
                break;
            case R.id.tv_share_qzone:
                //分享给QQ空间
                Intent intentZ = new Intent(mContext, QQShareActivity.class);
                intentZ.putExtra(QQShareActivity.DATA_TYPE, QQShareActivity.SHARE_Qzone);
                intentZ.putExtra(QQShareActivity.DATA_TITLE, mContext.getString(R.string.app_name));
                intentZ.putExtra(QQShareActivity.DATA_LINK, mFunctionModel.getKeyword());
                intentZ.putExtra(QQShareActivity.DATA_DESC, mFunctionModel.getContent());
                intentZ.putExtra(QQShareActivity.DATA_IMAGE, "http://img.fqapps.com/d69e39dc4d75c27e3759a1e159932d4f-310x310");
                mContext.startActivity(intentZ);
                dismiss();
                break;*/
            case R.id.close:
                dismiss();
                break;
        }
    }


}

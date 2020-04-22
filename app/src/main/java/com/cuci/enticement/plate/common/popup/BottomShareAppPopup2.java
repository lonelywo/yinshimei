package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.lxj.xpopup.core.BottomPopupView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomShareAppPopup2 extends BottomPopupView {


    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    @BindView(R.id.con_img)
    ConstraintLayout conImg;
    @BindView(R.id.tv_share_wx)
    TextView tvShareWx;
    @BindView(R.id.tv_share_moment)
    TextView tvShareMoment;
    @BindView(R.id.icon_share_save)
    TextView iconShareSave;
    @BindView(R.id.ll_tupian)
    LinearLayout llTupian;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private UserInfo mUserInfo;
    private Bitmap bitmap;
    private String mposter;
    private String mqrcode;
    private Context mContext;

    public BottomShareAppPopup2(@NonNull Context context, UserInfo userInfo, String poster, String qrcode) {
        super(context);
        mContext = context;
        mUserInfo = userInfo;
        mposter = poster;
        mqrcode = qrcode;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_qiandao_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
      //  ViewUtils.showView(progressBar);
       /* Glide.with(this)
                .load(mposter)
                .placeholder(R.drawable.img_placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        ViewUtils.hideView(progressBar);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        ViewUtils.hideView(progressBar);
                        return false;
                    }
                })
                .into(imgTupian);


        ImageLoader.loadPlaceholder(mqrcode, qrcode);*/

        textName.setText(mUserInfo.getNickname());

    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment,
            R.id.icon_share_save})
    public void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.tv_share_wx:
                bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bitmap);
                dismiss();
                break;
            case R.id.tv_share_moment:
                bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                dismiss();
                break;
            case R.id.icon_share_save:
                bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                File file = ImageUtils.saveBitmap(BasicApp.getContext(),
                        FileUtils.FOLDER_NAME_SAVE, String.valueOf(System.currentTimeMillis()), bitmap, true);
                if (file != null) {
                    FToast.success("图片成功保存到：" + file.getAbsolutePath());
                }
                dismiss();
                break;

        }
    }
}





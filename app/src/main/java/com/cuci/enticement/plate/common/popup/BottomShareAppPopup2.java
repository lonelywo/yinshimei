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
import androidx.constraintlayout.widget.Guideline;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.QianDaoBean;
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
    @BindView(R.id.img_tupian_share)
    ImageView imgTupianShare;
    @BindView(R.id.text_name_share)
    TextView textNameShare;
    @BindView(R.id.text_name_share1)
    TextView textNameShare1;
    @BindView(R.id.qrcode_share)
    ImageView qrcodeShare;
    @BindView(R.id.con_img_share)
    ConstraintLayout conImgShare;
    @BindView(R.id.line)
    Guideline line;
    @BindView(R.id.text_name1)
    TextView textName1;
    @BindView(R.id.line_share)
    Guideline lineShare;
    @BindView(R.id.con_yy)
    ConstraintLayout conYy;
    private UserInfo mUserInfo;
    private Bitmap bitmap;
    QianDaoBean.DataBean.ShareInfoBean mshare_info;
    private Context mContext;

    public BottomShareAppPopup2(@NonNull Context context, UserInfo userInfo, QianDaoBean.DataBean.ShareInfoBean share_info) {
        super(context);
        mContext = context;
        mUserInfo = userInfo;
        mshare_info = share_info;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_qiandao_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        ViewUtils.showView(progressBar);
        Glide.with(this)
                .load(mshare_info.getPoster())
                .placeholder(R.drawable.poster)
                .dontAnimate()//解决第一次加载不显示bug
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        ViewUtils.hideView(progressBar);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        ViewUtils.hideView(progressBar);
                        imgTupian.setImageDrawable(resource);
                        return false;
                    }
                })
                .into(imgTupian);
        //  ImageLoader.loadPlaceholder(mposter, imgTupian);
        ImageLoader.loadPlaceholder(mshare_info.getQrcode(), qrcode);
        textName.setText(mshare_info.getNickname());
        textName1.setText(mshare_info.getSlogan());


        Glide.with(this)
                .load(mshare_info.getPoster())
                .placeholder(R.drawable.poster)
                .dontAnimate()//解决第一次加载不显示bug
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imgTupianShare.setImageDrawable(resource);
                        return false;
                    }
                })
                .into(imgTupianShare);
        ImageLoader.loadPlaceholder(mshare_info.getQrcode(), qrcodeShare);
        textNameShare.setText(mshare_info.getNickname());
        textNameShare1.setText(mshare_info.getSlogan());
    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment,
            R.id.icon_share_save})
    public void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.tv_share_wx:
                bitmap = ImageUtils.getViewBitmap(conImgShare, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bitmap);
                dismiss();
                break;
            case R.id.tv_share_moment:
                bitmap = ImageUtils.getViewBitmap(conImgShare, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    dismiss();
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                dismiss();
                break;
            case R.id.icon_share_save:
                bitmap = ImageUtils.getViewBitmap(conImgShare, 750, 1334);
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
           /* case R.id.con_yy:
               // dismiss();
                break;*/
        }
    }
}





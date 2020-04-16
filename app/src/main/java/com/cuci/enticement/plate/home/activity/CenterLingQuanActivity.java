package com.cuci.enticement.plate.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.ShareHomeImgBean;
import com.cuci.enticement.bean.ShareimgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;


public class CenterLingQuanActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
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
    @BindView(R.id.img_jia)
    ImageView imgJia;
    @BindView(R.id.img_tupian0)
    ImageView imgTupian0;
    @BindView(R.id.text_name0)
    TextView textName0;
    @BindView(R.id.qrcode0)
    ImageView qrcode0;
    @BindView(R.id.con_img0)
    ConstraintLayout conImg0;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private Bitmap bitmap;
    private ProgressDialog mProgressDialog;
    private ShareHomeImgBean.DataBean data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_center_lingquan;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        data = (ShareHomeImgBean.DataBean) intent.getSerializableExtra("Data");
        String nickname = data.getNickname();
        textName.setText(nickname);
        List<ShareHomeImgBean.DataBean.GoodsListBean> goods_list = data.getGoods_list();
        ShareHomeImgBean.DataBean.GoodsListBean goodsListBean = goods_list.get(0);
        String poster = goodsListBean.getPoster();
        String qrcode1 = goodsListBean.getQrcode();
        ViewUtils.showView(progressBar);
        Glide.with(this)
                .load(poster)
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
        ImageLoader.loadPlaceholder(qrcode1, qrcode);


        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textName0.setText(nickname);
        Glide.with(this)
                .load(poster)
                .placeholder(R.drawable.img_placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                        return false;
                    }
                })
                .into(imgTupian0);
        ImageLoader.loadPlaceholder(qrcode1, qrcode0);
    }

    public void load() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment, R.id.icon_share_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share_wx:

                bitmap = ImageUtils.getViewBitmap(conImg0, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    return;
                }
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bitmap);
                break;
            case R.id.tv_share_moment:
                bitmap = ImageUtils.getViewBitmap(conImg0, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    return;
                }

                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                break;
            case R.id.icon_share_save:
                bitmap = ImageUtils.getViewBitmap(conImg0, 750, 1334);
                if (bitmap == null) {
                    FToast.error("数据错误");
                    return;
                }
                File file = ImageUtils.saveBitmap(BasicApp.getContext(),
                        FileUtils.FOLDER_NAME_SAVE, String.valueOf(System.currentTimeMillis()), bitmap, true);
                if (file != null) {
                    FToast.success("图片成功保存到：" + file.getAbsolutePath());
                }
                break;
        }
    }

    private Observer<Status<ResponseBody>> mObservershare = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                //ViewUtils.hideView(progressBar);
                mProgressDialog.dismiss();
                ResponseBody body = status.content;
                opera3(body);
                break;
            case Status.ERROR:
                // ViewUtils.hideView(progressBar);
                mProgressDialog.dismiss();
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera3(ResponseBody body) {
        try {
            String b = body.string();
            ShareimgBean mMyTeamslBean = new Gson().fromJson(b, ShareimgBean.class);
            if (mMyTeamslBean.getCode() == 1) {
                String poster = mMyTeamslBean.getData().getPoster();
                String qrcode = mMyTeamslBean.getData().getQrcode();
                //   bitmap1 = downloadImage(ProdActivity.this, poster);
                //   bitmap2 = downloadImage(ProdActivity.this, qrcode);

            } else if (mMyTeamslBean.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(this);
                FToast.error(mMyTeamslBean.getInfo());
            } else {
                FToast.error(mMyTeamslBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.cuci.enticement.event.CashEvent;
import com.cuci.enticement.event.DownShareImgEvent;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;
import com.xiaomi.push.F;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QrCodeProdView extends FrameLayout {

    private static final String TAG = QrCodeProdView.class.getSimpleName();
    private ImageView imgTupianShare;
    private ImageView qrcodeShare;

    private TextView textNameShare;


    public QrCodeProdView(@NonNull Context context) {
        this(context, null);
    }

    public QrCodeProdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QrCodeProdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_qr_code_prod, this);
        imgTupianShare = findViewById(R.id.img_tupian_share);
        qrcodeShare = findViewById(R.id.qrcode_share);

        textNameShare = findViewById(R.id.text_name_share);
    }

    public void setImageMain(String url) {

       /* try {
            Bitmap bitmap = Glide.with(BasicApp.getContext())
                    .asBitmap()
                    .load(url)
                    .placeholder(R.drawable.poster)
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            imgTupianShare.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "图片下载失败");
            imgTupianShare.setImageResource(R.drawable.poster);
        }*/

       Glide.with(BasicApp.getContext())
                .load(url)
                .placeholder(R.drawable.poster)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        EventBus.getDefault().post(new DownShareImgEvent());
                        FToast.error("图片加载失败");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        EventBus.getDefault().post(new DownShareImgEvent());
                        return false;
                    }
                })
                .into(imgTupianShare);
     //   ImageLoader.loadNoPlaceholder(url, imgTupianShare);
    }


    public void setDesc(String s) {
        textNameShare.setText(s);
    }


    public void setImageQrCode(String url) {

       /* try {
            Bitmap bitmap = Glide.with(BasicApp.getContext())
                    .asBitmap()
                    .load(url)
                    .placeholder(R.drawable.img_placeholder)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "图片下载失败");
            qrcode.setImageResource(R.drawable.img_placeholder);
        }*/
        ImageLoader.loadPlaceholder(url, qrcodeShare);
    }


}

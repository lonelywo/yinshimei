package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.event.DownShareImgEvent;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;

import org.greenrobot.eventbus.EventBus;


public class QrCodeProdView2 extends FrameLayout {

    private static final String TAG = QrCodeProdView2.class.getSimpleName();
    private ImageView imgTupianShare;
    private ImageView qrcodeShare;
    private TextView textNameShare;



    public QrCodeProdView2(@NonNull Context context) {
        this(context, null);
    }

    public QrCodeProdView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QrCodeProdView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.view_qr_code_prod2, this);
        imgTupianShare = findViewById(R.id.img_tupian);
        qrcodeShare = findViewById(R.id.qrcode);
        textNameShare = findViewById(R.id.text_name);

    }

    public void setImageMain(String url) {


       Glide.with(BasicApp.getContext())
                .load(url)
                .placeholder(R.drawable.poster2)
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

    }


    public void setDesc(String s) {
        textNameShare.setText(s);
    }


    public void setImageQrCode(String url) {


        ImageLoader.loadPlaceholder(url, qrcodeShare);
    }


}

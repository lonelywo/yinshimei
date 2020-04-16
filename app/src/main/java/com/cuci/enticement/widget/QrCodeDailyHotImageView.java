package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;


/**
 * Created by Cloud on 2017/10/10.
 * 生成带图片二维码View
 */

public class QrCodeDailyHotImageView extends FrameLayout {

    private static final String TAG = QrCodeDailyHotImageView.class.getSimpleName();

    private ImageView mMainImg;
    private ImageView mQrCodeImg;

    private TextView mMainTitle;


    public QrCodeDailyHotImageView(@NonNull Context context) {
        this(context, null);
    }

    public QrCodeDailyHotImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QrCodeDailyHotImageView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.activity_share_haibao, this);


        mMainImg = findViewById(R.id.img_tupian);
        mQrCodeImg = findViewById(R.id.qrcode);

        mMainTitle = findViewById(R.id.text_name);


    }



    /**
     * 设置主图
     *
     */
    public void setMainImg(String url) {

        try {
            Bitmap bitmap = Glide.with(BasicApp.getContext())
                    .asBitmap()
                    .load(url)
                    .submit(750, 1334)
                    .get();
            mMainImg.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "图片下载失败");
        }
    }

    /**
     * 设置二维码
     *
     * @param bitmap
     */
    public void setQrCode(Bitmap bitmap) {
        mQrCodeImg.setImageBitmap(bitmap);
    }
    public void setQrCode(String url) {
        try {
            Bitmap bitmap = Glide.with(BasicApp.getContext())
                    .asBitmap()
                    .load(url)
                    .submit(155, 155)
                    .get();
            mQrCodeImg.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "图片下载失败");
        }

    }
    /**
     * 设置昵称
     *
     * @param title
     */
    public void setMainTitle(String title) {
        mMainTitle.setText(title);
    }




}

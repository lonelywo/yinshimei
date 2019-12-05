package com.cuci.enticement.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QrCodeProdView extends FrameLayout {

    private static final String TAG = QrCodeProdView.class.getSimpleName();
    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    @BindView(R.id.con_img)
    ConstraintLayout conImg;


    private Context mContext;

    public QrCodeProdView(@NonNull Context context) {
        this(context, null);
    }

    public QrCodeProdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QrCodeProdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_qr_code_prod, this);
        ButterKnife.bind(this);
    }

    public void setImageMain(String url) {

        try {
            Bitmap bitmap = Glide.with(BasicApp.getContext())
                    .asBitmap()
                    .load(url)
                    .placeholder(R.drawable.img_placeholder)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            imgTupian.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "图片下载失败");
            imgTupian.setImageResource(R.drawable.img_placeholder);
        }

    }




    public void setDesc(String s) {
        textName.setText(s);
    }


    public void setImageQrCode(String url) {

        try {
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
        }

    }


}

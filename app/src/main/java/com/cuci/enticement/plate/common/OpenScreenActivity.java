package com.cuci.enticement.plate.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.OpenGGBean;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;

import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OpenScreenActivity extends AppCompatActivity {

    private ImageView mImageView;


    private MyCountDownTimer mCountDownTimer;
    private OpenGGBean.DataBean mdata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
            setContentView(R.layout.popup_opengg_view);

            mImageView = findViewById(R.id.image_view);
        mdata = (OpenGGBean.DataBean)intent.getSerializableExtra("Data");

         if(mdata.getAd_type()==0){
             Glide.with(this).load(mdata.getUrl()).into(mImageView);
         }else if (mdata.getAd_type()==1){

         }
        mImageView.setOnClickListener(v -> {
            switch (mdata.getType()) {
                case 0:
                    Intent intentProd = new Intent(this, ProdActivity.class);
                    intentProd.putExtra("bannerData", mdata.getLink());
                    startActivity(intentProd);
                    break;
                case 1:
                    Intent intentProd1 = new Intent(this, Agreement2Activity.class);
                    intentProd1.putExtra("url", mdata.getLink());
                    startActivity(intentProd1);
                    break;
                case 2:
                    Intent intentProd2 = new Intent(this, Agreement2Activity.class);
                    intentProd2.putExtra("url", mdata.getLink());
                    startActivity(intentProd2);
                    break;
            }
            finish();


        });

        mCountDownTimer = new MyCountDownTimer(this,40000 , 1000);
            mCountDownTimer.start();

    }

    private static class MyCountDownTimer extends CountDownTimer {

        private final WeakReference<OpenScreenActivity> mActivityWeakReference;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(OpenScreenActivity activity, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mActivityWeakReference = new WeakReference<>(activity);

        }

        @Override
        public void onTick(long millisUntilFinished) {

            long m = millisUntilFinished  / 1000L;

            OpenScreenActivity activity = mActivityWeakReference.get();

        }

        @Override
        public void onFinish() {
            OpenScreenActivity activity = mActivityWeakReference.get();
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        }
    }

    @Override
    public void finish() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cuci.enticement.R;


import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;

public class LauncherActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTvTime;
    private LinearLayout mLayout;

    private MyCountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!isTaskRoot() && intent != null) {
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }

            setContentView(R.layout.popup_splash_view);

            mImageView = findViewById(R.id.image_view);

            File folder = new File(getFilesDir(), "ysm_splash");
            if (!folder.exists()) {
                Glide.with(this).load(R.drawable.img_splash).into(mImageView);
            } else {
                File[] fa = folder.listFiles();
                if (fa.length <= 0) {
                    Glide.with(this).load(R.drawable.img_splash).into(mImageView);
                } else {
                    Uri uri = Uri.parse("file://" + fa[0].getPath());
                    Glide.with(this).load(uri).into(mImageView);
                }
            }

            mTvTime = findViewById(R.id.tv_time);
            mLayout = findViewById(R.id.ll_close);

            mLayout.setOnClickListener(v -> {
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                finish();
            });
            mCountDownTimer = new MyCountDownTimer(this, 4000, 1000);
            mCountDownTimer.start();

    }

    private static class MyCountDownTimer extends CountDownTimer {

        private final WeakReference<LauncherActivity> mActivityWeakReference;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(LauncherActivity activity, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mActivityWeakReference = new WeakReference<>(activity);

        }

        @Override
        public void onTick(long millisUntilFinished) {

            long m = millisUntilFinished  / 1000L;

            LauncherActivity activity = mActivityWeakReference.get();
            activity.mTvTime.setText(String.format(Locale.getDefault(), "%ds", m));
        }

        @Override
        public void onFinish() {
            LauncherActivity activity = mActivityWeakReference.get();
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
}

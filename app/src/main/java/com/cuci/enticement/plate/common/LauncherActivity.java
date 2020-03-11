package com.cuci.enticement.plate.common;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.ResponseBody;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.ClauseBean;
import com.cuci.enticement.bean.OpenGGBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.network.DownloadManager;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi2;
import com.cuci.enticement.plate.common.vm.MainViewModel;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.lxj.xpopup.XPopup;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Locale;

public class LauncherActivity extends AppCompatActivity {
    private static final String TAG = LauncherActivity.class.getSimpleName();
    private ImageView mImageView;
    private TextView mTvTime;
    private LinearLayout mLayout;

    private MyCountDownTimer mCountDownTimer;
    private static int is_show=0;
    private static OpenGGBean.DataBean data;
    private static boolean isAdClicked=false;

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
        MainViewModel   mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.openScreen("2").observe(this, clauseObserver);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //延迟三秒再启动 app
                start();
            }
        }, 3000);
        mImageView = findViewById(R.id.image_view);

            mTvTime = findViewById(R.id.tv_time);
            mLayout = findViewById(R.id.ll_close);
            mLayout.setOnClickListener(v -> {
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                    finish();
            });


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
            if(isAdClicked){
               return;
            }
            LauncherActivity activity = mActivityWeakReference.get();
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
            isAdClicked=false;
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

    private Observer<Status<ResponseBody>> clauseObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };
    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            OpenGGBean mOpenGGBean = new Gson().fromJson(b, OpenGGBean.class);
            if (mOpenGGBean.getCode() == 1) {
                if(is_show==1){
                    data = mOpenGGBean.getData();
                    is_show = mOpenGGBean.getData().getIs_show();
                  if(data.getAd_type()==0){
                      Glide.with(this)
                              .load(data.getUrl())
                              .placeholder(R.drawable.img_splash)
                              .listener(new RequestListener<Drawable>() {
                                  @Override
                                  public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                      startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                                      finish();
                                      return false;
                                  }

                                  @Override
                                  public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                      ViewUtils.showView(mLayout);
                                      mCountDownTimer = new MyCountDownTimer(LauncherActivity.this, 5000, 1000);
                                      mCountDownTimer.start();
                                      return false;
                                  }
                              })
                              .into(mImageView);

                      mImageView.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              //点击广告图片设置标记并跳转页面
                              isAdClicked=true;
                              switch (data.getType()) {
                                  case 0:
                                      Intent intentProd = new Intent(LauncherActivity.this, ProdActivity.class);
                                      intentProd.putExtra("bannerData", data.getLink());
                                      startActivity(intentProd);
                                      break;
                                  case 1:
                                  case 2:
                                      Intent intentProd1 = new Intent(LauncherActivity.this, Agreement2Activity.class);
                                      intentProd1.putExtra("url", data.getLink());
                                      startActivity(intentProd1);
                                      break;
                              }
                          }
                      });
                  } else {

                  }

                }else {

                    ViewUtils.hideView(mLayout);
                }

            } else {
                FToast.error(mOpenGGBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void start() {
        //如果点击了广告那么停止启动应用，没点广告的话就按正常情况启动
        if (is_show==1) {
            return;
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //从广告页回退时再延迟一点执行启动程序，并改变标记状态
        if (isAdClicked) {
            is_show = 0;
            isAdClicked=false;
            start();
        }

    }

   /* private void saveSplash(OpenGGBean.DataBean splash) {

        if (splash.getUrl() == null) return;

        String url = splash.getUrl();
        String oldSplash = SharedPrefUtils.getSplashUrl();
        if (!TextUtils.isEmpty(url) && !url.equals(oldSplash)) {

            File folder = new File(this.getFilesDir(), "ysm_splash");

            if (!folder.exists()) {
                boolean mkdirs = folder.mkdirs();
            } else {
                File[] fa = folder.listFiles();
                if (fa != null && fa.length > 0) {
                    for (File f : fa) {
                        if (f.exists()) {
                            boolean delete = f.delete();
                        }
                    }
                }
            }

            File file = new File(folder, EncryptUtils.md5Encrypt(url) + ".png");

            if (file.exists()) {
                boolean delete = file.delete();
                if (delete) {
                    FLog.e(TAG, "Splash图片已删除");
                } else {
                    FLog.e(TAG, "Splash删除失败");
                }
            }

            DownloadManager.getInstance().download(url, file.getAbsolutePath(), new DownloadManager.DownloadListener() {
                @Override
                public void onDownloadSucceed(String savePath) {
                    FLog.e(TAG, "Splash图片已下载成功：" + savePath);
                    SharedPrefUtils.saveSplashUrl(url);
                }

                @Override
                public void onDownloading(int progress) {

                }

                @Override
                public void onDownloadFailure(Exception e) {
                    FLog.e(TAG, "Splash图片下载失败" + (e.getMessage() == null ? "" : e.getMessage()));
                }
            });

        }
    }*/

}

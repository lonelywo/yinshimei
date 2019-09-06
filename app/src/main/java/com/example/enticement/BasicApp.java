package com.example.enticement;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;



import org.litepal.LitePal;



public class BasicApp extends Application {

    private static final String TAG = BasicApp.class.getSimpleName();

    private static BasicApp mContext;
    private static AppExecutors mAppExecutors;

   // private static IWXAPI mIWXAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mAppExecutors = new AppExecutors();
        //数据库LitePal初始化
        LitePal.initialize(this);




      //严格模式下，会调用 检测FileUriExposure
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }



    public static AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    public static BasicApp getContext() {
        return mContext;
    }
}

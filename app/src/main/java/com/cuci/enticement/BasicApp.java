package com.cuci.enticement;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;


import com.cuci.enticement.bean.WxPayBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.litepal.LitePal;



public class BasicApp extends Application {

    private static final String TAG = BasicApp.class.getSimpleName();

    private static BasicApp mContext;
    private static AppExecutors mAppExecutors;

    private static IWXAPI mIWXAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mAppExecutors = new AppExecutors();
        //数据库LitePal初始化
        LitePal.initialize(this);


        //注册微信分享，第三个参数为是否检查signature，正式发布改为true
        mIWXAPI = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, !BuildConfig.DEBUG);
        mIWXAPI.registerApp(Constant.WX_APP_ID);

      //严格模式下，会调用 检测FileUriExposure
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }


    public static IWXAPI getIWXAPI() {
        return mIWXAPI;
    }
    public static AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    public static BasicApp getContext() {
        return mContext;
    }

}

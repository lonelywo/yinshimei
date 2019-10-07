package com.cuci.enticement;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;


import com.cuci.enticement.bean.WxPayBean;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.UIProvider;
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

        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("1484190905068246#kefuchannelapp74235");//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId("74235");//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”

        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);
        //后面可以设置其他属性


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

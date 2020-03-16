package com.cuci.enticement.network;

import android.text.TextUtils;
import com.cuci.enticement.BuildConfig;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {

    private static final String TAG = ServiceCreator.class.getSimpleName();
    private HeaderInterceptor mHeaderInterceptor;
    private volatile static ServiceCreator mInstance;
    public static  int ConstantA = 0;
//商品id
    public static  String Constant_GOODS_ID = "";
//is_new支付前
    public static  int Constant_IS_NEW = 0;
//总金额
public static  String Constant_ZONG_MONEY = "";
      private static final String BASE_URL = "https://test.enticementchina.com/";
    //  private static final String BASE_URL = "https://app.enticementchina.com/";

    private final Retrofit mRetrofit;

    private final OkHttpClient mClient;

    public static ServiceCreator getInstance() {
        if (mInstance == null) {
            synchronized (ServiceCreator.class) {
                if (mInstance == null) {
                    mInstance = new ServiceCreator();
                }
            }
        }
        return mInstance;
    }




    private ServiceCreator() {

        mHeaderInterceptor = new HeaderInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(mHeaderInterceptor);

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LoggingInterceptor());
        }

        mClient = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    if(TextUtils.equals(BASE_URL,"https://test.enticementchina.com/")){
        ConstantA=0;
    }else {
        ConstantA=1;
    }
    }

    public OkHttpClient getClient() {
        return mClient;
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }




}

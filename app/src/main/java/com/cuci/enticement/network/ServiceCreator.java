package com.cuci.enticement.network;




import android.text.TextUtils;

import com.cuci.enticement.BuildConfig;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {

    private static final String TAG = ServiceCreator.class.getSimpleName();

    private volatile static ServiceCreator mInstance;
    public static  int ConstantA = 0;

    // private static final String BASE_URL = "https://test.enticementchina.com/";
     private static final String BASE_URL = "https://app.enticementchina.com/";
    // //private static final String BASE_URL = "http://192.168.2.152:809/";

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



        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS);


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

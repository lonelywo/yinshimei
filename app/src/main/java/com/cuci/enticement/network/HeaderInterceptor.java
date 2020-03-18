package com.cuci.enticement.network;

import android.util.Log;

import com.cuci.enticement.BasicApp;

import com.cuci.enticement.utils.AppUtils;


import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


public class HeaderInterceptor implements Interceptor {

    private static final String TAG = HeaderInterceptor.class.getSimpleName();

    private int mVersionCode;
    public HeaderInterceptor() {
        mVersionCode = AppUtils.getVersionCode(BasicApp.getContext());
    }



    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request newRequest = chain.request().newBuilder()
                .addHeader("new_version", String.valueOf(mVersionCode))
                .addHeader("from_type", "2")
                .build();

        return chain.proceed(newRequest);

    }
}

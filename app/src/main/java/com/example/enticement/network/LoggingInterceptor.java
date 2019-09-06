package com.example.enticement.network;

import android.util.Log;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class LoggingInterceptor implements Interceptor {

    private static final String TAG = LoggingInterceptor.class.getSimpleName();

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();

        long t1 = System.nanoTime();

//        Log.d(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        if (response.code() != 200) {
            Log.e(TAG,request.headers().toString());
            Log.e(TAG, String.format("Received response for code %d%n%s in %.1fms%n%s", response.code(), response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        }

        return response;
    }
}

package com.cuci.enticement.network;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class DownloadManager {

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    private DownloadManager() {
        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
        mHandler = new Handler(Looper.myLooper());
    }

    private static class SingletonHolder {
        private static final DownloadManager INSTANCE = new DownloadManager();
    }

    public static DownloadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void download(String url, String savePath, DownloadListener listener) {

        if (TextUtils.isEmpty(url)) {
            mHandler.post(() -> listener.onDownloadFailure(new NullPointerException("下载地址有误。")));
            return;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHandler.post(() -> listener.onDownloadFailure(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                ResponseBody body = response.body();

                if (body == null) {
                    mHandler.post(() -> listener.onDownloadFailure(new NullPointerException("ResponseBody is null")));
                    return;
                }

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;

                try {
                    is = body.byteStream();
                    long total = body.contentLength();
                    File file = new File(savePath);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        mHandler.post(() -> listener.onDownloading(progress));
                    }
                    fos.flush();
                    // 下载完成
                    mHandler.post(() -> listener.onDownloadSucceed(savePath));
                } catch (Exception e) {
                    mHandler.post(() -> listener.onDownloadFailure(e));
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public interface DownloadListener {

        void onDownloadSucceed(String savePath);

        void onDownloading(int progress);

        void onDownloadFailure(Exception e);
    }
}

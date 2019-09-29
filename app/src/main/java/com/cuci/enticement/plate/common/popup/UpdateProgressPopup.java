package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.lxj.xpopup.core.CenterPopupView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateProgressPopup extends CenterPopupView {

    private static final String TAG = UpdateProgressPopup.class.getSimpleName();

    @BindView(R.id.tv_progress)
    TextView mTvProgress;
    @BindView(R.id.pb)
    ProgressBar mPb;

    private Version.DataBean mData;
    private UpdateNotification mNotify;

    public UpdateProgressPopup(@NonNull Context context, Version.DataBean data) {
        super(context);
        mData = data;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_update_progress_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        mNotify = new UpdateNotification(BasicApp.getContext());
        download();
    }


    private void download() {

        String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator;
        File folder = new File(rootPath, FileUtils.FOLDER_NAME_CACHE);

        if (!folder.exists()) {
            boolean result = folder.mkdirs();
            if (result) {
                FLog.e(TAG, "创建文件夹成功");
            } else {
                FLog.e(TAG, "创建文件夹失败");
            }
        }

        File file = new File(folder, String.format(Locale.getDefault(), "version_%s_update.apk", mData.getVersion()));

        if (file.exists()) {
            boolean delete = file.delete();
            if (delete) {
                FLog.e(TAG, "删除原文件成功");
            } else {
                FLog.e(TAG, "删除原文件失败");
            }
        }

        FToast.success("开始下载");

        BasicApp.getAppExecutors().networkIO().execute(() -> {

            try {
                URL url = new URL(mData.getUrl());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(12 * 1000);
                InputStream inputStream = conn.getInputStream();

                byte[] buffer = new byte[1024];
                int len;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                long sum = 0;
                long total = conn.getContentLength();
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                    sum += len;
                    int progress = (int) (sum * 1.0f / total * 100);
                    BasicApp.getAppExecutors().mainThread().execute(() -> {
                        mPb.setProgress(progress);
                        mTvProgress.setText(String.format(Locale.CHINA, "%d%%", progress));
                    });
                }
                bos.close();
                byte[] getData = bos.toByteArray();

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(getData);
                fos.flush();
                fos.close();
                inputStream.close();

                BasicApp.getAppExecutors().mainThread().execute(() -> {
                    installApk(file.getAbsolutePath());
                    mNotify.dismiss();
                    dismiss();
                });

            } catch (IOException e) {
                e.printStackTrace();
                BasicApp.getAppExecutors().mainThread().execute(() -> {
                    mNotify.dismiss();
                    if (e.getMessage() != null) {
                        FToast.error("网络异常，下载失败 " + e.getMessage());
                    } else {
                        FToast.error("网络异常，下载失败");
                    }
                    dismiss();
                });
            }


        });
    }

    private void installApk(String path) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        File apkFile = new File(path);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uri = FileProvider.getUriForFile(BasicApp.getContext(), BasicApp.getContext().getPackageName() + ".fileprovider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(apkFile);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }

        BasicApp.getContext().startActivity(intent);
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }
}

package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.cuci.enticement.R;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.cuci.enticement.network.DownloadManager;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.lxj.xpopup.core.CenterPopupView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadPopup extends CenterPopupView {

    public static final int DATA_TYPE_IMAGE = 232;
    public static final int DATA_TYPE_VIDEO = 233;

    @BindView(R.id.tv_progress)
    TextView mTvProgress;
    @BindView(R.id.pb)
    ProgressBar mPb;

    private Context mContext;
    private String mUrl;
    private int mType;
    private boolean mShare;

    public DownloadPopup(@NonNull Context context, String url, int type, boolean share) {
        super(context);
        mContext = context;
        mUrl = url;
        mType = type;
        mShare = share;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_download_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);

        File save;

        if (mShare) {
            if (mType == DATA_TYPE_IMAGE) {
                save = FileUtils.getCachePath(EncryptUtils.md5Encrypt(mUrl) + ".jpg");
            } else if (mType == DATA_TYPE_VIDEO) {
                save = FileUtils.getCachePath(EncryptUtils.md5Encrypt(mUrl) + ".mp4");
            } else {
                return;
            }
        } else {
            if (mType == DATA_TYPE_IMAGE) {
                save = FileUtils.getSavePath(EncryptUtils.md5Encrypt(mUrl) + ".jpg");
            } else if (mType == DATA_TYPE_VIDEO) {
                save = FileUtils.getSavePath(EncryptUtils.md5Encrypt(mUrl) + ".mp4");
            } else {
                return;
            }
        }

        DownloadManager.getInstance()
                .download(mUrl, save.getAbsolutePath(), new DownloadManager.DownloadListener() {
                    @Override
                    public void onDownloadSucceed(String savePath) {
                        if (mShare) {
                            if (mType == DATA_TYPE_IMAGE) {
                                Uri imageContentUri = ImageUtils.getImageContentUri(mContext, save);
                                List<Uri> uris = new ArrayList<>();
                                uris.add(imageContentUri);
                                WxShareUtils.shareToWxNative(mContext, uris, Constant.WX_PACKAGE_NAME, Constant.WX_FRIENDS_ACTIVITY);
                            } else if (mType == DATA_TYPE_VIDEO) {
                                Uri uri = Uri.fromFile(save);
                                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                                share.setType("video/*");
                                share.putExtra(Intent.EXTRA_STREAM, uri);
                                mContext.startActivity(Intent.createChooser(share, "分享到"));
                            }
                        } else {
                            Uri uri = Uri.fromFile(save);
                            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                            BasicApp.getContext().sendBroadcast(intent);
                            FToast.success("保存成功：" + savePath);
                        }

                        dismiss();
                    }

                    @Override
                    public void onDownloading(int progress) {
                        if (progress <= 100) {
                            mPb.setProgress(progress);
                        }
                        mTvProgress.setText(String.format(Locale.getDefault(), "%d%%", progress));
                    }

                    @Override
                    public void onDownloadFailure(Exception e) {
                        if (save.exists()) {
                            boolean delete = save.delete();
                            if (delete) {
                                Uri uri = Uri.fromFile(save);
                                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                                BasicApp.getContext().sendBroadcast(intent);
                            }
                        }
                        FToast.error("下载失败，请重试");
                        dismiss();
                    }
                });

    }

}

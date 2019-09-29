package com.cuci.enticement.plate.common.popup;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;


import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;

import java.util.Locale;

public class UpdateNotification {

    private static final int mNotifyId = 0;
    private static final String mChannelId = "__updateChannel";

    private NotificationManager mManager;
    private Context mContext;
    private Notification.Builder mBuilder;

    public UpdateNotification(Context context) {
        mContext = context;
        init();
    }

    private void init() {

        mManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(mContext);
        mBuilder.setContentTitle("正在下载更新...")
                .setProgress(100, 0, false)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence channelName = BasicApp.getContext().getString(R.string.channel_name);
            String channelDescription = BasicApp.getContext().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(mChannelId, channelName, importance);

            channel.setDescription(channelDescription);

            mBuilder.setChannelId(mChannelId);
            mManager.createNotificationChannel(channel);
        }

        mBuilder.setOnlyAlertOnce(true);

        mManager.notify(mNotifyId, mBuilder.build());

    }

    public void updateProgress(int progress) {
        if (mBuilder != null) {
            mBuilder.setProgress(100, progress, false);
            mBuilder.setContentText(String.format(Locale.getDefault(), "%d%%", progress));
            mManager.notify(mNotifyId, mBuilder.build());
        }
    }

    public void dismiss() {
        if (mManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mManager.cancel(mNotifyId);
                mManager.deleteNotificationChannel(mChannelId);
            } else {
                mManager.cancel(mNotifyId);
            }

        }
    }
}

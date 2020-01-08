package com.cuci.enticement.push;

import android.app.ActivityManager;
import android.content.Context;

import com.cuci.enticement.utils.FLog;

import java.util.List;

public class PushHelper {

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
            for (int i = 0; i < processInfos.size(); i++) {
                if (processInfos.get(i).processName.equals(packageName)) {
                    FLog.e("NotificationLaunch", String.format("the %s is running, isAppAlive return true", packageName));
                    return true;
                }
            }
        }

        FLog.e("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }


}

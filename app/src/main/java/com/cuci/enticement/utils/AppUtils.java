package com.cuci.enticement.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityManager;

import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.LoginActivity;
import com.mob.secverify.SecVerify;
import com.mob.secverify.VerifyCallback;
import com.mob.secverify.datatype.VerifyResult;
import com.mob.secverify.exception.VerifyException;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;


public class AppUtils {

    /**
     * 判断账号是否登录
     */
    public static boolean isAllowPermission(Context context) {
        UserInfo userInfo = SharedPrefUtils.get(UserInfo.class);
        if (userInfo != null) {
            return true;
        }else {
          //  context.startActivity(new Intent(context, LoginActivity.class));
            yijian(context);
            return false;
        }
    }
    //版本名
    public static String getVersionName(Context context) {
        PackageInfo p = getPackageInfo(context);
        if (p == null) {
            return "获取版本名失败";
        }
        return p.versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        PackageInfo p = getPackageInfo(context);
        if (p == null) {
            return -1;
        }
        return p.versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断Service是否正在运行
     *
     * @param context     Context
     * @param serviceName Service的包名+类名
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断AccessibilityService服务是否已经启动
     *
     * @param context
     * @param name
     * @return
     */
    public static boolean isStartAccessibilityService(Context context, String name) {

        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);

        if (am == null) {
            return false;
        }

        List<AccessibilityServiceInfo> serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);

        for (AccessibilityServiceInfo info : serviceInfos) {
            String id = info.getId();

            if (name.equals(id)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context     Context
     * @param packageName 包名
     * @return 安装了指定的软件
     */
    public static boolean appExist(Context context, String packageName) {

        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<>();

        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }

        return packageNames.contains(packageName);
    }


    /**
     * 根据包名获取APP的versionCode
     *
     * @param context     Context
     * @param packageName 包名
     * @return versionCode
     */
    public static int getVersionCode(Context context, String packageName) {

        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);

        for (PackageInfo info : infos) {

            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }

            if (packageName.equals(info.packageName)) {
                return info.versionCode;
            }

        }

        return -1;
    }

    /**
     * 根据包名获取APP的versionName
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getVersionName(Context context, String packageName) {

        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);

        for (PackageInfo info : infos) {

            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }

            if (packageName.equals(info.packageName)) {
                return info.versionName;
            }

        }

        return null;
    }


    public static void yijian(Context context) {
        SecVerify.verify(new VerifyCallback() {
            @Override
            public void onOtherLogin() {
                // 用户点击“其他登录方式”，处理自己的逻辑
                FLog.e("yijian:", "切换账号");
                context.startActivity(new Intent(context, LoginActivity.class));
            }

            @Override
            public void onUserCanceled() {
                // 用户点击“关闭按钮”或“物理返回键”取消登录，处理自己的逻辑
            }

            @Override
            public void onComplete(VerifyResult data) {
                FLog.e("yijian:", data.toString());

            }

            @Override
            public void onFailure(VerifyException e) {
                //TODO处理失败的结果
            }
        });
    }
}

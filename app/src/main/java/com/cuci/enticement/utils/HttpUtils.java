package com.cuci.enticement.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.LoginOutEvent;
import com.cuci.enticement.plate.common.LoginActivity;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.igexin.sdk.PushManager;

import org.greenrobot.eventbus.EventBus;

public class HttpUtils {
//无效会员返回code
    public static final int CODE_INVALID = 4001;
    public HttpUtils() {
        /* cannot be instantiated */
        throw new RuntimeException("Stub!");
    }

    /**
     * 判断网络是否连接
     *
     * @param context ContentList
     * @return 网络是否连接
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     *
     * @param context Context
     * @return 是否是wifi连接
     */
    public static boolean isWifi(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;

        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Context context) {
        context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }
    /**
     * 后台返回4001用户id失效
     */
    public static void Invalid(Context context) {
        UserInfo mUserInfo = SharedPrefUtils.get(UserInfo.class);
        //清空微信绑定标识
        SharedPrefUtils.saveWXBind(0);
        //清空环信
        SharedPrefUtils.saveShowhxCode(0);
        //解绑个推
        PushManager.getInstance().unBindAlias(context, String.valueOf(mUserInfo.getId()), true);
        //退出默认设置isnew为0
        SharedPrefUtils.saveisnew(0);
        //清空默认地址缓存
        SharedPrefUtils.saveDefaultAdress("");
        SharedPrefUtils.exit();
        mUserInfo = null;
        EventBus.getDefault().post(new LoginOutEvent());
        //第一个参数为是否解绑推送的devicetoken
        ChatClient.getInstance().logout(true, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String error) {

            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
        AppUtils.isAllowPermission(context);
    }

}

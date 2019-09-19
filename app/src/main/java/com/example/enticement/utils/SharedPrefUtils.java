package com.example.enticement.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;


import com.example.enticement.BasicApp;
import com.example.enticement.bean.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharedPrefUtils {

    private static final String NAME_OBJECT = "object_data";
    private static final String NAME_STRING = "string_data";
    private static final String NAME_SETTINGS = "settings_data";

    private static final String STRING_IGNORE = "string_ignore";
    private static final String STRING_TAIL = "string_tail";
    private static final String STRING_WX_OPENID = "string_wx_openid";
    private static final String STRING_USER_AGREEMENT = "string_user_agreement";
    private static final String STRING_LAST_PHONE = "string_last_phone";
    private static final String STRING_JPUSH_INIT = "string_jpush_init";
    private static final String STRING_MESSAGE_STATE = "string_message_state";
    private static final String STRING_SPLASH_URL = "string_splash_url";
    private static final String STRING_IS_TODAY = "string_is_today";

    private static final String SETTINGS_FIRST_TIME = "settings_first_time";
    private static final String SETTINGS_FIRST_POPUP = "string_first_popup";
    private static final String SETTINGS_WECHAT_AUTH = "settings_wechat_auth";
    private static final String SETTINGS_IGNORE_VERSION = "settings_ignore_version";
    private static final String SETTINGS_WRITING_TYPE = "settings_writing_type";
    private static final String SETTINGS_WRITING_EARN = "settings_writing_earn";
    private static final String SETTINGS_WRITING_CODE = "settings_writing_code";
    private static final String SETTINGS_PUSH_NEW_MONEY = "settings_push_new_money";
    private static final String SETTINGS_PUSH_NEW_AGENT = "settings_push_new_agent";
    private static final String SETTINGS_TITLE_POPUP = "settings_title_popup";
    private static final String SETTINGS_WITH_APP = "settings_with_app";
    private static final String DY_FIRST_GUIDE = "dy_first_guide";


    private SharedPrefUtils() {
    }

    /**
     * 通过SharedPreferences保存一个对象
     */
    public static <T> boolean save(T t, Class<T> tClass) {

        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_OBJECT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(tClass.getSimpleName(), temp);
            editor.apply();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 通过SharedPreferences获取保存的对象
     */
    public static <T> T get(Class<T> tClass) {

        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_OBJECT, Context.MODE_PRIVATE);
        String temp = sp.getString(tClass.getSimpleName(), "");

        if (TextUtils.isEmpty(temp)) {
            return null;
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 退出登录
     */
    public static void exit() {
       SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_OBJECT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(UserInfo.class.getSimpleName(), "");
        editor.apply();
    }

    /**
     * 保存标题检测忽略的字符串
     */
    public static void saveIgnoreString(String string) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_IGNORE, string);
        editor.apply();
    }

    /**
     * 获取标题检测忽略的字符串
     */
    public static String getIgnoreString() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_IGNORE, "");
    }

    /**
     * 保存文案小尾巴
     */
    public static void saveTailString(String string) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_TAIL, string);
        editor.apply();
    }

    /**
     * 获取文案小尾巴
     */
    public static String getTailString() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_TAIL, "");
    }

    /**
     * 保存微信登录返回的OpenId
     */
    public static void saveWxOpenId(String string) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_WX_OPENID, string);
        editor.apply();
    }

    /**
     * 获取微信登录返回的OpenId
     */
    public static String getWxOpenId() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_WX_OPENID, "0YSM0YSM0");
    }


    /**
     * 保存是否第一次打开APP
     */
    public static void saveFirstTime(boolean first) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SETTINGS_FIRST_TIME, first);
        editor.apply();
    }

    /**
     * 获取是否第一次打开APP
     */
    public static boolean getFirstTime() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getBoolean(SETTINGS_FIRST_TIME, true);
    }


    /**
     * 保存是否第一次获取首页弹窗
     */
    public static void saveFirstPopup(boolean first) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SETTINGS_FIRST_POPUP, first);
        editor.apply();
    }

    /**
     * 获取是否第一次首页弹窗
     */
    public static boolean getFirstPopup() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getBoolean(SETTINGS_FIRST_POPUP, true);
    }

    /**
     * 保存是否启用商品标题识别弹窗
     */
    public static void saveTitlePopup(boolean flag) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SETTINGS_TITLE_POPUP, flag);
        editor.apply();
    }

    /**
     * 获取是否启用商品标题识别弹窗
     */
    public static boolean getTitlePopup() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getBoolean(SETTINGS_TITLE_POPUP, true);
    }


    /**
     * 保存是登录页调起微信登录还是个人中心
     */
    public static void saveWechatAuth(String type) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SETTINGS_WECHAT_AUTH, type);
        editor.apply();
    }

    /**
     * 获取是登录页调起微信登录还是个人中心
     */
    public static String getWechatAuth() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getString(SETTINGS_WECHAT_AUTH, "");
    }

    /**
     * 保存忽略更新的版本号
     */
    public static void saveIgnoreVersion(int versionCode) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(SETTINGS_IGNORE_VERSION, versionCode);
        editor.apply();
    }

    /**
     * 获取忽略更新的版本号
     */
    public static int getIgnoreVersion() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getInt(SETTINGS_IGNORE_VERSION, -1);
    }

    /**
     * 保存文案类型
     */
    public static void saveWritingType(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SETTINGS_WRITING_TYPE, s);
        editor.apply();
    }

    /**
     * 获取文案类型
     */
    public static String getWritingType() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getString(SETTINGS_WRITING_TYPE, "0");
    }

    /**
     * 保存分享页 预估赚设置
     */
    public static void saveWritingEarn(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SETTINGS_WRITING_EARN, s);
        editor.apply();
    }

    /**
     * 获取分享页 预估赚设置
     */
    public static String getWritingEarn() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getString(SETTINGS_WRITING_EARN, "0");
    }

    /**
     * 保存分享页 邀请码设置
     */
    public static void saveWritingCode(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SETTINGS_WRITING_CODE, s);
        editor.apply();
    }

    /**
     * 获取分享页 邀请码设置
     */
    public static String getWritingCode() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getString(SETTINGS_WRITING_CODE, "0");
    }

    /**
     * 保存分享商品带APP下载 1开0关
     */
    public static void saveWithApp(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SETTINGS_WITH_APP, s);
        editor.apply();
    }

    /**
     * 获取分享商品带APP下载 1开0关
     */
    public static String getWithApp() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getString(SETTINGS_WITH_APP, "1");
    }

    /**
     * 保存新增收益推送开关
     */
    public static void saveNewMoney(boolean flag) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SETTINGS_PUSH_NEW_MONEY, flag);
        editor.apply();
    }

    /**
     * 获取新增收益推送开关
     */
    public static boolean getNewMoney() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getBoolean(SETTINGS_PUSH_NEW_MONEY, true);
    }

    /**
     * 保存新增合伙人推送开关
     */
    public static void saveNewAgent(boolean flag) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SETTINGS_PUSH_NEW_AGENT, flag);
        editor.apply();
    }

    /**
     * 获取新增合伙人推送开关
     */
    public static boolean getNewAgent() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getBoolean(SETTINGS_PUSH_NEW_AGENT, true);
    }

    /**
     * 保存用户注册协议网址
     */
    public static void saveUserAgreement(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_USER_AGREEMENT, s);
        editor.apply();
    }

    /**
     * 获取用户注册协议网址
     */
    public static String getUserAgreement() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_USER_AGREEMENT, "");
    }

    /**
     * 保存最后登录的手机号
     */
    public static void saveLastPhone(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_LAST_PHONE, s);
        editor.apply();
    }

    /**
     * 获取最后登录的手机号
     */
    public static String getLastPhone() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_LAST_PHONE, "");
    }

    /**
     * 保存极光推送是否初始化
     */
    public static void saveJPushInit(boolean init) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(STRING_JPUSH_INIT, init);
        editor.apply();
    }

    /**
     * 获取极光推送是否初始化
     */
    public static boolean getJPushInit() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getBoolean(STRING_JPUSH_INIT, false);
    }


    /**
     * 保存消息中心是否有新消息
     */
    public static void saveMessageState(boolean init) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(STRING_MESSAGE_STATE, init);
        editor.apply();
    }

    /**
     * 获取消息中心是否有新消息
     */
    public static boolean getMessageState() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getBoolean(STRING_MESSAGE_STATE, false);
    }

    /**
     * 保存Splash
     */
    public static void saveSplashUrl(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_SPLASH_URL, s);
        editor.apply();
    }

    /**
     * 获取Splash
     */
    public static String getSplashUrl() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_SPLASH_URL, "");
    }

    /**
     * 保存当日一次
     */
    public static void saveToday(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(STRING_IS_TODAY, s);
        editor.apply();
    }

    /**
     * 获取当日一次
     */
    public static String getToday() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString(STRING_IS_TODAY, "");
    }



    /**
     * 保存抖好货是否第一次进入
     */
    public static void saveDyGuideIsFirst(boolean isFirstTime) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(DY_FIRST_GUIDE, isFirstTime);
        editor.apply();
    }

    /**
     * 获取抖好货是否第一次进入
     */
    public static boolean getDyGuideIsFist() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        return sp.getBoolean(DY_FIRST_GUIDE, true);
    }

    public static void saveShowquyuCode(int show) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences("quyu", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("showQyCode", show);
        editor.apply();
    }

    public static int getShowquyuCode() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences("quyu", Context.MODE_PRIVATE);
        return sp.getInt("showQyCode", 86);
    }



    /**
     * 保存默认收货地址
     */
    public static void saveDefaultAdress(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("default_adress", s);
        editor.apply();
    }

    /**
     * 获取默认收货地址
     */
    public static String getDefaultAdress() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString("default_adress", "");
    }

    /**
     * 保存默认收货地址id
     */
    public static void saveDefaultAdressId(String s) {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("default_adress_id", s);
        editor.apply();
    }

    /**
     * 获取默认收货地址id
     */
    public static String getDefaultAdressId() {
        SharedPreferences sp = BasicApp.getContext().getSharedPreferences(NAME_STRING, Context.MODE_PRIVATE);
        return sp.getString("default_adress_id", "");
    }
}

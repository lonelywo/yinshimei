package com.example.enticement.utils;

import android.text.TextUtils;
import android.util.Log;

import com.cuci.enticement.BuildConfig;


public class FLog {

    private FLog() {
    }

    public static void e(String TAG, String text) {
        if (BuildConfig.DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.e(TAG, text);
            } else {
                Log.e(TAG, "打印对象为空！");
            }
        }
    }

}

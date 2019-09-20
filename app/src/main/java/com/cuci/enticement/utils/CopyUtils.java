package com.cuci.enticement.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

public class CopyUtils {

    private CopyUtils() {
    }

    public static boolean copy(Context context, String text) {

        if (TextUtils.isEmpty(text)) return false;

        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            ClipData clipData = ClipData.newPlainText("text", text);
            manager.setPrimaryClip(clipData);
            //发送广播，防止弹自动识别标题的搜索窗
//            Intent intent = new Intent(MainActivity.ACTION_SAVE_PASTE);
//            intent.putExtra(MainActivity.DATA_CONTENT, text);
//            App.getContext().sendBroadcast(intent);
            return true;
        }

        return false;
    }

    public static boolean clear(Context context) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            ClipData clipData = ClipData.newPlainText("clear", "");
            manager.setPrimaryClip(clipData);
            return true;
        }
        return false;
    }
}

package com.cuci.enticement.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Parcelable;


import com.cuci.enticement.R;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class WxShareUtils {

    private static final String TAG = WxShareUtils.class.getSimpleName();

    public static final int WX_SCENE_SESSION = 0;
    public static final int WX_SCENE_TIME_LINE = 1;
    public static final int WX_SCENE_FAVORITE = 2;

    private static final int THUMB_SIZE = 150;

    /**
     * 打开微信
     *
     * @param context
     */
    public static void openWx(Context context) {
        try {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName(Constant.WX_PACKAGE_NAME, "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 原生分享到微信
     */
    public static void shareToWxNative(Context context, List<Uri> uris, String packageName, String activityName) {

        Intent intent = new Intent();
        ComponentName comp = new ComponentName(packageName, activityName);
        intent.setComponent(comp);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        int wxVerCode = AppUtils.getVersionCode(context, Constant.WX_PACKAGE_NAME);
        //微信6.7.3之后的版本限制发朋友圈只能一张图
        if (wxVerCode >= 1360 && Constant.WX_MOMENTS_ACTIVITY.equals(activityName)) {
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, uris.get(0));
        } else {
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(uris));
        }

        context.startActivity(Intent.createChooser(intent, "分享"));

    }

    /**
     * 系统默认单张图片分享，非指定packageName
     */
    public static void shareToWxNativeSingle(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    /**
     * 系统默认多张图片分享，非指定packageName
     */
    public static void shareToWxNativeMore(Context context, List<Uri> uris) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) uris);
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }


    /**
     * 链接分享（商品）
     *
     * @param type        类型
     * @param url         地址
     * @param title       标题
     * @param description 描述
     * @param imageUrl    图片地址
     */
    public static void shareToWX(int type, String url, String title, String description, String imageUrl) {

        BasicApp.getAppExecutors()
                .networkIO()
                .execute(() -> {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(new URL(imageUrl).openStream());

                        WXWebpageObject object = new WXWebpageObject();
                        object.webpageUrl = url;
                        WXMediaMessage mediaMessage = new WXMediaMessage(object);

                        String t = title;
                        String d = description;

                        if (title.length() > 512) {
                            t = title.substring(0, 511);
                        }
                        if (description.length() > 1024) {
                            d = description.substring(0, 1023);
                        }

                        mediaMessage.title = t;
                        mediaMessage.description = d;

                        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
                        bitmap.recycle();
                        mediaMessage.thumbData = getBitmapBytes(thumbBmp, true);

                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = mediaMessage;

                        switch (type) {
                            case WX_SCENE_SESSION:
                                req.scene = SendMessageToWX.Req.WXSceneSession;
                                break;
                            case WX_SCENE_TIME_LINE:
                                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                                break;
                            case WX_SCENE_FAVORITE:
                                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                                break;
                        }

                        BasicApp.getIWXAPI().sendReq(req);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

    }

    /**
     * 链接分享（APP）
     *
     * @param type        类型
     * @param url         地址
     * @param title       标题
     * @param description 描述
     * @param bitmap      图片Bitmap
     */
    public static void shareToWX(int type, String url, String title, String description, Bitmap bitmap,String phone) {

        WXWebpageObject object = new WXWebpageObject();
        object.webpageUrl = url;
        WXMediaMessage mediaMessage = new WXMediaMessage(object);

        if (title.length() > 512) {
            title = title.substring(0, 511);
        }

        if (description.length() > 1024) {
            description = description.substring(0, 1023);
        }

        mediaMessage.title = title;
        mediaMessage.description = description;

        mediaMessage.setThumbImage(bitmap);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = phone;
        req.message = mediaMessage;

        switch (type) {
            case WX_SCENE_SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case WX_SCENE_TIME_LINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case WX_SCENE_FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                break;
        }

        BasicApp.getIWXAPI().sendReq(req);

    }
    /**
     * 链接分享（APP）
     *
     * @param type        类型
     * @param url         地址
     * @param title       标题
     * @param description 描述
     * @param bitmap      图片Bitmap
     */
    public static void shareToWX(int type, String url, String title, String description, Bitmap bitmap) {

        WXWebpageObject object = new WXWebpageObject();
        object.webpageUrl = url;
        WXMediaMessage mediaMessage = new WXMediaMessage(object);

        if (title.length() > 512) {
            title = title.substring(0, 511);
        }

        if (description.length() > 1024) {
            description = description.substring(0, 1023);
        }

        mediaMessage.title = title;
        mediaMessage.description = description;

        mediaMessage.setThumbImage(bitmap);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());;
        req.message = mediaMessage;

        switch (type) {
            case WX_SCENE_SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case WX_SCENE_TIME_LINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case WX_SCENE_FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                break;
        }

        BasicApp.getIWXAPI().sendReq(req);

    }
    /**
     * 分享纯图片
     *
     * @param type
     * @param bitmap
     */
    public static void shareImageToWX(int type, Bitmap bitmap) {

        WXImageObject object = new WXImageObject(bitmap);
        WXMediaMessage mediaMessage = new WXMediaMessage(object);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = mediaMessage;

        switch (type) {
            case WX_SCENE_SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case WX_SCENE_TIME_LINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case WX_SCENE_FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                break;
        }

        BasicApp.getIWXAPI().sendReq(req);

    }

    /**
     * 分享视频
     *
     * @param type        类型
     * @param title       标题
     * @param description 描述
     * @param url         视频地址
     */
    public static void shareVideoToWx(int type, String title, String description, String url) {

        WXVideoObject video = new WXVideoObject();
        video.videoUrl = url;

        WXMediaMessage message = new WXMediaMessage(video);
        message.title = title;
        message.description = description;
        Bitmap bitmap = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.mipmap.ic_launcher);
        message.setThumbImage(bitmap);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = message;

        switch (type) {
            case WX_SCENE_SESSION:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case WX_SCENE_TIME_LINE:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
            case WX_SCENE_FAVORITE:
                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                break;
        }

        BasicApp.getIWXAPI().sendReq(req);
    }

    /**
     * 分享单张图片到微信
     *
     * @param type
     * @param url
     */
    public static void shareImageToWX(int type, String url) {

        BasicApp.getAppExecutors()
                .networkIO()
                .execute(() -> {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
                        WXImageObject object = new WXImageObject(bitmap);
                        WXMediaMessage mediaMessage = new WXMediaMessage(object);

                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = mediaMessage;

                        switch (type) {
                            case WX_SCENE_SESSION:
                                req.scene = SendMessageToWX.Req.WXSceneSession;
                                break;
                            case WX_SCENE_TIME_LINE:
                                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                                break;
                            case WX_SCENE_FAVORITE:
                                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                                break;
                        }

                        BasicApp.getIWXAPI().sendReq(req);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static byte[] getBitmapBytes(Bitmap bitmap, boolean paramBoolean) {
        Bitmap localBitmap = Bitmap.createBitmap(80, 80, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);
        int i;
        int j;
        if (bitmap.getHeight() > bitmap.getWidth()) {
            i = bitmap.getWidth();
            j = bitmap.getWidth();
        } else {
            i = bitmap.getHeight();
            j = bitmap.getHeight();
        }
        while (true) {
            localCanvas.drawBitmap(bitmap, new Rect(0, 0, i, j), new Rect(0, 0,
                    80, 80), null);
            if (paramBoolean)
                bitmap.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                e.printStackTrace();
            }
            i = bitmap.getHeight();
            j = bitmap.getHeight();
        }
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0,i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                //F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }


}

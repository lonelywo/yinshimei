package com.cuci.enticement.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BitmapUitls {
    public static Bitmap drawWXMiniBitmap(Bitmap bitmap, int width, int height) {
        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 用这个Bitmap生成一个Canvas,然后canvas就会把内容绘制到上面这个bitmap中
        Canvas mCanvas = new Canvas(mBitmap);
        // 绘制画笔
        Paint mPicturePaint = new Paint();
        // 绘制背景图片
        mCanvas.drawBitmap(mBitmap, 0.0f, 0.0f, mPicturePaint);
        // 绘制图片的宽、高
        int width_head = bitmap.getWidth();
        int height_head = bitmap.getHeight();
        // 绘制图片－－保证其在水平方向居中
        mCanvas.drawBitmap(bitmap, (width - width_head) / 2, (height - height_head) / 2,
                mPicturePaint);
        // 保存绘图为本地图片
        mCanvas.save();
        mCanvas.restore();
        return mBitmap;
    }
}

package com.cuci.enticement.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    public static final String FILE_NAME_EMOJI = "emoji.json";
    public static final String FILE_NAME_QQ = "qq.json";

    public static final String FOLDER_NAME_SAVE = "FDSH";
    public static final String FOLDER_NAME_CACHE = "fdsh_cache";


    private FileUtils() {
    }

    /**
     * 用来获取保存图片的文件路径
     *
     * @param fileName 文件名
     * @return File
     */
    public static File getSavePath(String fileName) {
        String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator;
        File folder = new File(rootPath, FOLDER_NAME_SAVE);
        folder.mkdirs();
        return new File(folder, fileName);
    }

    /**
     * 用来获取保存缓存图片的文件路径
     *
     * @param fileName 文件名
     * @return File
     */
    public static File getCachePath(String fileName) {
        String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator;
        File folder = new File(rootPath, FOLDER_NAME_CACHE);
        folder.mkdirs();
        return new File(folder, fileName);
    }

    /**
     * 读取本地JSON文件
     *
     * @param fileName 文件名
     * @param context  Context
     * @return JSON String
     */
    public static String getJson(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        Uri uri = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                Uri baseUri = Uri.parse("content://media/external/images/media");
                uri = Uri.withAppendedPath(baseUri, "" + id);
            }

            cursor.close();
        }

        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA, filePath);
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        }

        return uri;
    }




    /**保存Bitmap到sdcard
     * @param b
     */
    public static void saveBitmap(Bitmap b){

        String path =  Environment.getExternalStorageDirectory().getPath();
        long dataTake = System.currentTimeMillis();
        String jpegName = path + "/" + "moreinfo" +".jpg";

        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            Log.e("===", "saveBitmap成功");

        } catch (IOException e) {

            Log.e("===", "saveBitmap:失败");
            e.printStackTrace();
        }

    }


}

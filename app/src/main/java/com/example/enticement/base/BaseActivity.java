package com.example.enticement.base;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;


import com.example.enticement.utils.FileUtils;
import com.example.enticement.utils.FlymeStatusBarColorUtils;
import com.example.enticement.utils.MIUIStatusBarUtils;
import com.example.enticement.utils.Rom;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    private BasePopupView mLoading;
    private ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        Window window = getWindow();
      //  window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //小米手机状态栏字体黑色
      //  MIUIStatusBarUtils.MIUISetStatusBarLightMode(this, true);
        //魅族手机状态栏字体黑色
        if (Rom.check(Rom.ROM_FLYME)) {
      //      FlymeStatusBarColorUtils.setStatusBarDarkIcon(this, true);
        }
        
        if (getLayoutId() == 0) {
            throw new UnsupportedOperationException("Please set layout in \"getLayoutId()\".");
        }

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        mLoading = new XPopup.Builder(this)
                .dismissOnTouchOutside(false)
                .asLoading();

        initViews(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    /**
     * 获取剪切板的数据
     */
    private String getClipDataString() {
        ClipData clipData = mClipboardManager.getPrimaryClip();
        if (clipData == null) return null;
        ClipData.Item item = clipData.getItemAt(0);
        if (item == null) return null;
        if (item.getText() == null) return null;
        return item.getText().toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //删除缓存文件夹的图片
        String rootPath = Environment.getExternalStorageDirectory().getPath() + File.separator;
        File folder = new File(rootPath, FileUtils.FOLDER_NAME_CACHE);

        if (!folder.exists()) {
            return;
        }

        File[] fa = folder.listFiles();

        if (fa == null || fa.length <= 0) return;

        for (File f : fa) {
            if (f.exists()) {
                boolean delete = f.delete();
                if (delete) {
                    Uri uri = Uri.fromFile(f);
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                    sendBroadcast(intent);
                }
            }
        }
    }

    public void showLoading() {
        mLoading.show();
    }

    public void dismissLoading() {
        mLoading.dismiss();
    }
}

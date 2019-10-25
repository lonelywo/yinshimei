package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.lxj.xpopup.core.CenterPopupView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CenterShareAppPopup extends CenterPopupView {


    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.tv_share_wx)
    TextView tvShareWx;
    @BindView(R.id.tv_share_moment)
    TextView tvShareMoment;
    @BindView(R.id.icon_share_save)
    TextView iconShareSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.close)
    ImageView close;
    private UserInfo mFunctionModel;
    private Context mContext;
    private Bitmap bmp;

    public CenterShareAppPopup(@NonNull Context context, UserInfo model) {
        super(context);
        mContext = context;
        mFunctionModel = model;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_app_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        ImageLoader.loadPlaceholder(R.drawable.poster, imgTupian);

        bmp = returnBitMap("https://qiniu.cdn.enticementchina.com/f1e185e12cfacf56/e35fda95344b6830.jpg");

    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_moment,
            R.id.icon_share_save, R.id.close})
    public void onViewClick(View view) {
        if (bmp == null) {
            FToast.error("数据错误");
            return;
        }
        switch (view.getId()) {
            case R.id.tv_share_wx:
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bmp);
                dismiss();
                break;
            case R.id.tv_share_moment:
                WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bmp);
                dismiss();
                break;
            case R.id.icon_share_save:
                File file = ImageUtils.saveBitmap(BasicApp.getContext(),
                        FileUtils.FOLDER_NAME_SAVE, String.valueOf(System.currentTimeMillis()), bmp, true);
                if (file != null) {
                    FToast.success("图片成功保存到：" + file.getAbsolutePath());
                }
                dismiss();
                break;
            case R.id.close:
                dismiss();
                break;
        }
    }
    public  Bitmap returnBitMap(final String url){

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bmp = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bmp;
    }

}

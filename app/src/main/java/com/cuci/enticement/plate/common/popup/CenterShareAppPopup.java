package com.cuci.enticement.plate.common.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.HomeDetailsBean;
import com.cuci.enticement.bean.ShareimgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.EncryptUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.FileUtils;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.cuci.enticement.widget.QrCodeProdView;
import com.google.gson.Gson;
import com.lxj.xpopup.core.CenterPopupView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

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
    @BindView(R.id.ll_tupian)
    LinearLayout llTupian;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.con_back)
    ConstraintLayout conBack;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    @BindView(R.id.con_img)
    ConstraintLayout conImg;
    @BindView(R.id.text_name)
    TextView textName;
    private UserInfo mFunctionModel;
    private Context mContext;
    private HomeViewModel mHomeViewModel;
    private Bitmap bmp;
    private UserInfo mUserInfo;
    private HomeDetailsBean.DataBean mmProData;
    private Bitmap bitmap;


    public CenterShareAppPopup(@NonNull Context context, HomeDetailsBean.DataBean mProData) {
        super(context);
        mContext = context;
        mmProData = mProData;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_share_app_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mHomeViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(HomeViewModel.class);
        mHomeViewModel.shareimg("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), String.valueOf(mmProData.getId()),""+ AppUtils.getVersionCode(mContext)).observe((LifecycleOwner) mContext, mObserver);
        ViewUtils.showView(progressBar);
        // ImageLoader.loadPlaceholder(R.drawable.poster, imgTupian);

        // bmp = returnBitMap("https://qiniu.cdn.enticementchina.com/poster%281%29.jpg");
        //  bmp = getBitmap(BasicApp.getContext(),R.drawable.poster );
        //  bmp = BitmapFactory.decodeResource(getResources(),R.drawable.poster );
    }

    /* @OnClick({R.id.tv_share_wx, R.id.tv_share_moment,
             R.id.icon_share_save, R.id.close})
     public void onViewClick(View view) {
         if (bmp == null) {
             FToast.error("数据错误");
             return;
         }
         if (sharepic == null) {
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
               //  Bitmap bmp1 = getBitmap(BasicApp.getContext(),R.drawable.poster );
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
     }*/
    public Bitmap returnBitMap(final String url) {

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
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
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


    public Bitmap getBitmap(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        TypedValue value = new TypedValue();
        context.getResources().openRawResource(resId, value);
        options.inTargetDensity = value.density;
        options.inScaled = false;//不缩放
        return BitmapFactory.decodeResource(getResources(), resId, options);
    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ViewUtils.hideView(progressBar);
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                ViewUtils.hideView(progressBar);
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            ShareimgBean mMyTeamslBean = new Gson().fromJson(b, ShareimgBean.class);
            if (mMyTeamslBean.getCode() == 1) {
                ImageLoader.loadPlaceholder(mMyTeamslBean.getData().getPoster(), imgTupian);
                ImageLoader.loadPlaceholder(mMyTeamslBean.getData().getQrcode(), qrcode);
                textName.setText(mUserInfo.getNickname());

             /*   QrCodeProdView view = new QrCodeProdView(mContext);
                view.setImageMain(mMyTeamslBean.getData().getPoster());
                view.setImageQrCode(mMyTeamslBean.getData().getQrcode());
                view.setDesc(mUserInfo.getNickname());*/

                // String sharepic = mMyTeamslBean.getData().getSharepic();
                //  bmp = returnBitMap(sharepic);

               // bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);

                tvShareWx.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  Bitmap comp = comp(bmp);
                       /* String path = saveImageToGallery(bitmap);
                        WXsharePic("he" + System.currentTimeMillis(), true, bitmap, path);*/
                         bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);
                         WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_SESSION, bitmap);
                        dismiss();
                    }
                });
                tvShareMoment.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //    Bitmap comp = comp(bmp);
                        /*String path = saveImageToGallery(bitmap);
                        WXsharePic("he" + System.currentTimeMillis(), false, bitmap, path);*/
                        bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);
                          WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                        dismiss();
                    }
                });
                iconShareSave.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bitmap = ImageUtils.getViewBitmap(conImg, 750, 1334);
                        File file = ImageUtils.saveBitmap(BasicApp.getContext(),
                                FileUtils.FOLDER_NAME_SAVE, String.valueOf(System.currentTimeMillis()), bitmap, true);
                        if (file != null) {
                            FToast.success("图片成功保存到：" + file.getAbsolutePath());
                        }
                        dismiss();
                    }
                });
                close.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

            }else if(mMyTeamslBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(mContext);
                dismiss();
                FToast.error(mMyTeamslBean.getInfo());
            } else {
                close.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                FToast.error(mMyTeamslBean.getInfo());
                dismiss();
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //图片按比例大小压缩方法（根据Bitmap图片压缩）
    @SuppressLint("WrongThread")
    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 960f;//这里设置高度为800f
        float ww = 540f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    @SuppressLint("WrongThread")
    public String saveImageToGallery(Bitmap bmp) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearyy";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "poster.jpg";
        File file = new File(appDir, fileName);
        if (file.exists()) {
            return storePath + "/" + fileName;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storePath + "/" + fileName;
    }

    public void WXsharePic(String transaction, final boolean isSession, Bitmap bitmap, String path) {
        //初始化WXImageObject和WXMediaMessage对象
        WXImageObject imageObject;
        if (isExist(path)) {
            imageObject = new WXImageObject();
            imageObject.setImagePath(path);
        } else {
            imageObject = new WXImageObject(bitmap);
        }
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imageObject;
            /* //设置缩略图
             Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
             bitmap.recycle();
             msg.thumbData = getBitmapByte(scaledBitmap);*/
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = transaction + Long.toString(System.currentTimeMillis());
        req.message = msg;
        //表示发送给朋友圈  WXSceneTimeline  表示发送给朋友  WXSceneSession
        req.scene = isSession ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        //调用api接口发送数据到微信
        BasicApp.getIWXAPI().sendReq(req);
    }

    public static boolean isExist(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    public static Uri saveBitmap(Bitmap bitmap, File path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Uri uri = ImageUtils.getImageContentUri(BasicApp.getContext(), path);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            BasicApp.getContext().sendBroadcast(intent);
            return uri;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

package com.cuci.enticement.plate.mine.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.ui.fragment.PickerFragment;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.JsonBean;
import com.cuci.enticement.bean.ModifyInfo;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderDetailsActivity;
import com.cuci.enticement.plate.common.popup.HeadImageBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.PayBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.SexBottom2TopProdPopup;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.GetJsonDataUtil;

import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.RxImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lxj.xpopup.XPopup;
import com.uuzuche.lib_zxing.activity.CodeUtils;



import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;
import okhttp3.Callback;
import okhttp3.ResponseBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class InfoActivity extends BaseActivity {


    @BindView(R.id.icon_iv)
    CircleImageView iconIv;
    @BindView(R.id.tv_icon)
    TextView tvIcon;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    private String mProvince, mCity, mArea, mAddress;
    private boolean isLoaded=false;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public static final int CHANGE_HEAD_IMAG=1000;

    public static final int CHANGE_SEX=1001;
    public static final int CHANGE_ADDRESS=1002;
    private int mPostType;
    private  UserInfo mUserInfo;
    private CommonViewModel mViewModel;
    private String mImagePath;
    private CityPickerView mPicker;

    @Override
    public int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserInfo= SharedPrefUtils.get(UserInfo.class);

      /*  if("1".equals(userInfo.getSex())){
            tvSex.setText("男");
        }else if("0".equals(userInfo.getSex())){
            tvSex.setText("女");
        }*/

        if(TextUtils.isEmpty(mUserInfo.getHeadimg())){
            ViewUtils.showView(tvIcon);
            ViewUtils.hideView(iconIv);
            tvIcon.setText("点击设置头像");
        }else {
            ViewUtils.hideView(tvIcon);
            ViewUtils.showView(iconIv);
            ImageLoader.loadPlaceholder(mUserInfo.getHeadimg(),iconIv);
        }




        if(TextUtils.isEmpty(mUserInfo.getNickname())){
            tvNick.setText("点击设置昵称");
        }else {
            tvNick.setText(mUserInfo.getNickname());
        }

      if(TextUtils.isEmpty(mUserInfo.getSex())){
          tvSex.setText("点击设置性别");
      }else {
          tvSex.setText(mUserInfo.getSex());
      }

        String address=mUserInfo.getProvince()+" "+mUserInfo.getCity()+" "+mUserInfo.getArea();
        if(!TextUtils.isEmpty(mUserInfo.getProvince())){
            tvAddress.setText(address);
        }else {
            tvAddress.setText("点击设置地区");
        }


        mViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);
        RxPicker.init(new RxImageLoader());

        InfoActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this);
        //initJsonData();
        mPicker = new CityPickerView();
        mPicker.init(this);
        CityConfig cityConfig = new CityConfig.Builder()
                .cancelTextSize(20)//取消按钮文字大小
                .confirmTextSize(20)//确认按钮文字大小
                .titleTextSize(20)
                .setLineColor("#000000")//中间横线的颜色
                .setCustomItemLayout(R.layout.custom_picker_text)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)//自定义item布局里面的textViewid
                .confirTextColor("#BF9964")//确认按钮文字颜色
                .province("")//默认显示的省份
                .city("")//默认显示省份下面的城市
                .district("")//默认显示省市下面的区县数据
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();

        mPicker.setConfig(cityConfig);

        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                mProvince = province.getName();
                mCity = city.getName();
                mArea = district.getName();
                mAddress = province.getName() + " " + city.getName() + " " + district.getName();
                mPostType=CHANGE_ADDRESS;
                mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                        mUserInfo.getHeadimg(),"",mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                        ,mProvince,mCity,mArea)
                        .observe(InfoActivity.this, mObserver);
            }

            @Override
            public void onCancel() {

            }
        });

    }




    @OnClick({R.id.image_back, R.id.ll_icon, R.id.ll_nick, R.id.ll_sex, R.id.ll_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ll_icon:
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(true)
                        .dismissOnBackPressed(true)
                        .asCustom(new HeadImageBottom2TopProdPopup(this, type -> {
                            switch (type){
                                case 1:


                                    getPicFromCamera();

                                    break;
                                case 2:
                                    setIcon(true);
                                    break;
                            }
                        }))
                        .show();

                break;
            case R.id.ll_nick:
                startActivityForResult(new Intent(this,NickModifyActivity.class),100);
                break;
            case R.id.ll_sex:
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(true)
                        .dismissOnBackPressed(true)
                        .asCustom(new SexBottom2TopProdPopup(this, sex -> {
                            mPostType=CHANGE_SEX;
                            mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                                    mUserInfo.getHeadimg(),"",mUserInfo.getNickname(),sex,mUserInfo.getUnionid()
                                    ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                                    .observe(this, mObserver);
                        }))
                        .show();
                break;
            case R.id.ll_address:
              /*  if (isLoaded) {
                    showPickerView();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View v = getCurrentFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//从控件所在的窗口中隐藏
                } else {
                    FToast.warning("城市数据正在解析，请稍等。");
                }*/
                mPicker.showCityPicker();
                break;
        }
    }

    private void setIcon(boolean showCamera) {
        Disposable d = RxPicker.of()
                .camera(showCamera)
                .start(this)
                .subscribe(imageItems -> {
                    //得到结果
                    ImageItem imageItem = imageItems.get(0);

                    if (imageItem == null) {
                        FToast.error("选取图片失败");
                        return;
                    }

                     mImagePath = imageItem.getPath();
                  // CodeUtils.analyzeBitmap(mImagePath, mAnalyzeCallback);

                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {

                                          Bitmap mBitmap = null;
                                          try {
                                              mBitmap = analyzeBitmap(mImagePath);
                                          } catch (Exception e) {
                                              e.printStackTrace();
                                          }
                                          String base64Str = ImageUtils.bitmapToBase64(mBitmap);

                                          mPostType=CHANGE_HEAD_IMAG;
                                          mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                                                  mUserInfo.getHeadimg(),base64Str,mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                                                  ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                                                  .observe(InfoActivity.this, mObserver);
                                      }
                                  });





                });
    }


    private File tempFile;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;

    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;


    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BasicApp.getContext().getPackageName() + ".fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);

    }


    CodeUtils.AnalyzeCallback mAnalyzeCallback = new CodeUtils.AnalyzeCallback() {

        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {

            String base64Str = ImageUtils.bitmapToBase64(mBitmap);

            mPostType=CHANGE_HEAD_IMAG;
            mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                    mUserInfo.getHeadimg(),base64Str,mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                    ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                    .observe(InfoActivity.this, mObserver);
        }

        @Override
        public void onAnalyzeFailed() {

            Bitmap mBitmap = BitmapFactory.decodeFile(mImagePath);
            if(mBitmap==null){
                FToast.error("图片压缩失败");
                return;
            }
            String base64Str = ImageUtils.bitmapToBase64(mBitmap);

            //todo 上传头像
            mPostType=CHANGE_HEAD_IMAG;
            mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                    mUserInfo.getHeadimg(),base64Str,mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                    ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                    .observe(InfoActivity.this, mObserver);
        }
    };


    // 弹出选择器
    private void showPickerView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";
                mProvince = opt1tx;

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";
                mCity = opt2tx;

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                mArea = opt3tx;


                mAddress = opt1tx + " " + opt2tx + " " + opt3tx;
                //todo 上传地址

                mPostType=CHANGE_ADDRESS;
                mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                        mUserInfo.getHeadimg(),"",mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                        ,mProvince,mCity,mArea)
                        .observe(InfoActivity.this, mObserver);




            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setLineSpacingMultiplier((float) 2.0)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FToast.error("解析城市数据失败");
        }
        return detail;
    }


    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }


        isLoaded = true;
    }





    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
            case Status.LOADING:
                showLoading();
                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();

            ModifyInfo modifyInfo = new Gson().fromJson(b, ModifyInfo.class);
            if (modifyInfo.getCode() == 1) {
                UserInfo userInfo = modifyInfo.getData();
                mUserInfo=userInfo;
                SharedPrefUtils.save(userInfo,UserInfo.class);

                switch (mPostType){
                    case CHANGE_HEAD_IMAG:
                        ViewUtils.hideView(tvIcon);
                        ViewUtils.showView(iconIv);
                        ImageLoader.loadPlaceholder(userInfo.getHeadimg(), iconIv);
                        refreshBroadcast( userInfo);
                        break;
                    case CHANGE_SEX:
                        if(!TextUtils.isEmpty(userInfo.getSex())) {
                            tvSex.setText(userInfo.getSex());
                            refreshBroadcast( userInfo);
                        }
                        break;
                    case CHANGE_ADDRESS:
                        String address=userInfo.getProvince()+" "+userInfo.getCity()+" "+userInfo.getArea();
                        if(!TextUtils.isEmpty(address)){
                            tvAddress.setText(address);

                        }
                        break;
                }
                FToast.success(modifyInfo.getInfo());

            } else {
                FToast.error(modifyInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void refreshBroadcast( UserInfo userInfo) {
        Intent intentRefresh = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
        intentRefresh.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentRefresh);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==101){
            String nickname = data.getStringExtra("nickname");
            tvNick.setText(nickname);
        }

        switch (requestCode) {
            // 调用相机后返回
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                   /* Bitmap image = BitmapFactory.decodeFile(tempFile.getPath());
                    //处理图片旋转问题
                    ExifInterface exif = null;

                    try {
                        exif = new ExifInterface(tempFile.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    int orientation = exif.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION, 0);
                    Matrix matrix = new Matrix();
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                        matrix.postRotate(90);
                    } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                        matrix.postRotate(180);
                    } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                        matrix.postRotate(270);
                    }

                    image = Bitmap.createBitmap(image, 0, 0,
                            image.getWidth(), image.getHeight(), matrix, true);
                    MediaStore.Images.Media.insertImage(getContentResolver(), image, "", "");*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Bitmap mBitmap = null;
                            try {
                                mBitmap = analyzeBitmap(tempFile.getPath());
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                            String base64Str = ImageUtils.bitmapToBase64(mBitmap);

                            mPostType=CHANGE_HEAD_IMAG;
                            mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                                    mUserInfo.getHeadimg(),base64Str,mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                                    ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                                    .observe(InfoActivity.this, mObserver);
                        }
                    });

                   /* //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(this, BasicApp.getContext().getPackageName() + ".fileprovider", tempFile);
                        cropPhoto(contentUri);//裁剪图片
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));//裁剪图片
                    }*/
                }
                break;

            case CROP_REQUEST_CODE:
               /* Bundle bundle = data.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    String base64Str = ImageUtils.bitmapToBase64(image);
                    *//*
                     *上传文件的额操作
                     *//*
                    mPostType=CHANGE_HEAD_IMAG;
                    mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),
                            mUserInfo.getHeadimg(),base64Str,mUserInfo.getNickname(),mUserInfo.getSex(),mUserInfo.getUnionid()
                            ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                            .observe(InfoActivity.this, mObserver);

                }*/
                break;

        }

    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        InfoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    /**
     * 获得权限
     */
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void needsPermission() {

    }

    /**
     * 点取消后再次点击此功能触发
     *
     * @param request PermissionRequest
     */
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onShowPermission(final PermissionRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setCancelable(false);
        builder.setMessage("没有所需权限，将无法继续，请点击下方“确定”后打开APP所需的权限。");
        builder.setPositiveButton("确定", (dialog, which) -> request.proceed());
        builder.setNegativeButton("取消", (dialog, which) -> {
            request.cancel();
            finish();
        });
        builder.create().show();
    }

    /**
     * 权限被拒绝
     */
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onPermissionDenied() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage("因为您拒绝授予使用相机和存储照片的权限，导致无法正常使相机功能，请返回再次点击后授予权限。");
        builder.setPositiveButton("确定", (dialog, which) -> finish());
        builder.create().show();
    }

    /**
     * 点了不再询问后再次打开
     */
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onNeverAskAgain() {
        ask4Permission();
    }

    /**
     * 提示需要从APP设置里面打开权限
     */
    private void ask4Permission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setCancelable(false);
        builder.setMessage("因为需要使用相机和存储照片的权限，请点击下方“设置”按钮后进入权限设置打开相机和存储照片的权限后再次使用此功能。");
        builder.setNegativeButton("取消", (dialog, which) -> finish());
        builder.setPositiveButton("设置", (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + BasicApp.getContext().getPackageName()));
            startActivity(intent);
            finish();
        });

        builder.create().show();
    }



    /**
     * yasuo
     */
    public Bitmap analyzeBitmap(String path) throws Exception{

        /**
         * 首先判断图片的大小,若图片过大,则执行图片的裁剪操作,防止OOM
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        Bitmap mBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小

        int sampleSize = (int) (options.outHeight / (float) 400);

        if (sampleSize <= 0)
            sampleSize = 1;
        options.inSampleSize = sampleSize;
        mBitmap = BitmapFactory.decodeFile(path, options);

        //处理图片旋转问题
        ExifInterface exif = null;

            exif = new ExifInterface(path);
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Matrix matrix = new Matrix();
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }

            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                    mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);

        return mBitmap;




    }
    public byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
    public  Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

}

package com.cuci.enticement.plate.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.JsonBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderDetailsActivity;
import com.cuci.enticement.plate.common.popup.PayBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.SexBottom2TopProdPopup;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.GetJsonDataUtil;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        UserInfo userInfo = SharedPrefUtils.get(UserInfo.class);
        String nickname = userInfo.getNickname();
        tvNick.setText(nickname);
      /*  if("1".equals(userInfo.getSex())){
            tvSex.setText("男");
        }else if("0".equals(userInfo.getSex())){
            tvSex.setText("女");
        }*/

        if(TextUtils.isEmpty(userInfo.getHeadimg())){
            ViewUtils.showView(tvIcon);
            ViewUtils.hideView(iconIv);
            tvIcon.setText("点击设置头像");
        }else {
            ViewUtils.hideView(tvIcon);
            ViewUtils.showView(iconIv);
            ImageLoader.loadPlaceholder(userInfo.getHeadimg(),iconIv);
        }




        if(TextUtils.isEmpty(userInfo.getNickname())){
            tvSex.setText("点击设置昵称");
        }else {
            tvSex.setText(userInfo.getNickname());
        }

      if(TextUtils.isEmpty(userInfo.getSex())){
          tvSex.setText("点击设置性别");
      }else {
          tvSex.setText(userInfo.getSex());
      }

        if(TextUtils.isEmpty(userInfo.getAddress())){
            tvSex.setText("点击设置地区");
        }else {
            tvSex.setText(userInfo.getAddress());
        }

        initJsonData();

    }




    @OnClick({R.id.image_back, R.id.ll_icon, R.id.ll_nick, R.id.ll_sex, R.id.ll_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ll_icon:
                setIcon();
                break;
            case R.id.ll_nick:
                startActivity(new Intent(this,NickModifyActivity.class));
                break;
            case R.id.ll_sex:
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(true)
                        .dismissOnBackPressed(true)
                        .asCustom(new SexBottom2TopProdPopup(this, type -> {

                        }))
                        .show();
                break;
            case R.id.ll_address:
                if (isLoaded) {
                    showPickerView();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View v = getCurrentFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//从控件所在的窗口中隐藏
                } else {
                    FToast.warning("城市数据正在解析，请稍等。");
                }
                break;
        }
    }

    private void setIcon() {
        Disposable d = RxPicker.of()
                .camera(false)
                .start(this)
                .subscribe(imageItems -> {
                    //得到结果
                    ImageItem imageItem = imageItems.get(0);

                    if (imageItem == null) {
                        FToast.error("选取图片失败");
                        return;
                    }

                    String path = imageItem.getPath();
                    CodeUtils.analyzeBitmap(path, mAnalyzeCallback);

                });
    }



    CodeUtils.AnalyzeCallback mAnalyzeCallback = new CodeUtils.AnalyzeCallback() {

        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            String base64Str = ImageUtils.bitmapToBase64(mBitmap);
            //todo 上传头像

        }

        @Override
        public void onAnalyzeFailed() {
          FToast.error("图片压缩失败");
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

                tvAddress.setText(mAddress);


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
}

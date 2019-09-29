package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AddressBean;
import com.cuci.enticement.bean.DeleteAddress;
import com.cuci.enticement.bean.JsonBean;
import com.cuci.enticement.bean.SetDefaultAddress;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UpdateAddress;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.eventbus.AddressEvent;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.GetJsonDataUtil;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class ZengAddressActivity extends BaseActivity {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.text_shoujihao)
    TextView textShoujihao;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.img_youjiantou)
    ImageView imgYoujiantou;
    @BindView(R.id.edt_xiangxi)
    EditText edtXiangxi;
    @BindView(R.id.text_morendizi)
    TextView textMorendizi;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    private CommonViewModel mViewModel;
    private UserInfo mUserInfo;
    private static boolean isLoaded = false;
    private String mProvince, mCity, mArea, mAddress;
    private String mIsDefault = "0";
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String mAddressId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_bianji;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        AddressBean.DataBean.ListBean addressBean = intent.getParcelableExtra("addressBean");
        if (addressBean != null) {
            edtName.setText(addressBean.getName());
            edtPhone.setText(addressBean.getPhone());
            edtXiangxi.setText(addressBean.getAddress());
            mAddress = addressBean.getProvince() + " " + addressBean.getCity() + " " + addressBean.getArea();
            tvCode.setText(mAddress);
            mProvince = addressBean.getProvince();
            mCity = addressBean.getCity();
            mArea = addressBean.getArea();
            mAddressId = String.valueOf(addressBean.getId());
            int is_default = addressBean.getIs_default();
            if(is_default==1){
                checkbox.setChecked(true);
            }else {
                checkbox.setChecked(false);
            }

        }


        mUserInfo = SharedPrefUtils.get(UserInfo.class);


        mViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);


        initJsonData();
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (TextUtils.isEmpty(edtName.getText())) {
                    FToast.warning("请填写收货人姓名");
                    checkbox.setChecked(false);
                } else if (TextUtils.isEmpty(edtPhone.getText())) {
                    FToast.warning("请填写收货人电话");
                    checkbox.setChecked(false);
                } else if (TextUtils.isEmpty(edtXiangxi.getText())) {
                    FToast.warning("请填写收货人详细地址");
                    checkbox.setChecked(false);
                } else if (TextUtils.isEmpty(mAddress)) {
                    FToast.warning("请选择城市");
                    checkbox.setChecked(false);
                }else {
                    if(isChecked){
                        mIsDefault = "1";
                    }else {
                        mIsDefault = "0";
                    }
                  /*  mViewModel.setDefaultAddress(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),mAddressId)
                            .observe(ZengAddressActivity.this,mSetObserver);*/
                }



            }
        });
    }


    private Observer<Status<ResponseBody>> mSetObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    SetDefaultAddress bean = new Gson().fromJson(result, SetDefaultAddress.class);
                    if(bean.getCode()==1){

                        mIsDefault = "1";
                        FToast.success(bean.getInfo());
                    }else {
                        mIsDefault = "0";
                        checkbox.setChecked(false);
                        FToast.error(bean.getInfo());
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }



                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                mIsDefault = "0";
                checkbox.setChecked(false);
                FToast.error(status.message);

                break;
        }
    };





    @OnClick({R.id.image_back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(edtName.getText())) {
                    FToast.warning("请填写收货人姓名");

                } else if (TextUtils.isEmpty(edtPhone.getText())) {
                    FToast.warning("请填写收货人电话");
                } else if (TextUtils.isEmpty(edtXiangxi.getText())) {
                    FToast.warning("请填写收货人详细地址");
                } else if (TextUtils.isEmpty(mAddress)) {
                    FToast.warning("请选择城市");
                } else {
                    String name = edtName.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();
                    String detailAdress = edtXiangxi.getText().toString().trim();

                    if(TextUtils.isEmpty(SharedPrefUtils.getDefaultAdress())){
                        mIsDefault="1";
                    }

                    mViewModel.addAdress(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),
                            name, phone, mProvince, mCity, mArea, detailAdress, mIsDefault, mAddressId)
                            .observe(this, mObserver);


                }

                break;
        }
    }


    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    UpdateAddress updateAddress = new Gson().fromJson(result, UpdateAddress.class);
                    if (updateAddress.getCode() == 1) {
                        EventBus.getDefault().postSticky(new AddressEvent(AddressEvent.REFRESH_ADRESS_LIST));
                        FToast.success(updateAddress.getInfo());
                        finish();
                    } else {
                        FToast.error(updateAddress.getInfo());

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);

                break;
        }
    };


    @OnClick(R.id.tv_code)
    public void onViewClicked() {

        if (isLoaded) {
            showPickerView();
        } else {
            FToast.warning("城市数据正在解析，请稍等。");
        }

    }


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

                tvCode.setText(mAddress);


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


}

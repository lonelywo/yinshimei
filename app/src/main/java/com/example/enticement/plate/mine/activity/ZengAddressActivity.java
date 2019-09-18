package com.example.enticement.plate.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.cuci.enticement.R;
import com.example.enticement.base.BaseActivity;
import com.example.enticement.bean.JsonBean;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.GetJsonDataUtil;
import com.example.enticement.widget.ClearEditText;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private static boolean isLoaded = false;
    private String cityStr;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_address_bianji;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initJsonData();
    }


    @OnClick({R.id.image_back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(edtName.getText())) {
                    FToast.error("请填写收货人姓名");

                } else if (TextUtils.isEmpty(edtPhone.getText())) {
                    FToast.error("请填写收货人电话");
                } else if (TextUtils.isEmpty(edtXiangxi.getText())) {
                    FToast.error("请填写收货人详细地址");
                } else if (TextUtils.isEmpty(cityStr)) {
                    FToast.error("请选择城市");
                } else {
                    finish();
                }

                break;
        }
    }



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

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

               cityStr = opt1tx+" " + opt2tx+" "  + opt3tx;

                tvCode.setText(cityStr);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
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


        isLoaded=true;
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

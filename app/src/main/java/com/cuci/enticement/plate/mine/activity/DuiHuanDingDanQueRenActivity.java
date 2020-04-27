package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AddressBean;
import com.cuci.enticement.bean.ExpressCost;
import com.cuci.enticement.bean.JiFenDuiHuanSubmitBean;
import com.cuci.enticement.bean.JiFenProBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.common.popup.WarningPopup;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class DuiHuanDingDanQueRenActivity extends BaseActivity {

    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.text_address)
    TextView tvAddress;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.text_neirong)
    TextView textNeirong;
    @BindView(R.id.text_qian)
    TextView textQian;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.con_buju)
    ConstraintLayout conBuju;
    @BindView(R.id.con_zhongjian)
    ConstraintLayout conZhongjian;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bottom)
    ConstraintLayout bottom;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private JiFenProBean.DataBean mInfo;
    private UserInfo mUserInfo;
    private OrderViewModel mViewModel;
    private String mAddressId;
    private String rule;
    @Override
    public int getLayoutId() {
        return R.layout.activity_sendorder_duihuan;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        mInfo = (JiFenProBean.DataBean) intent.getSerializableExtra("intentInfo");

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        long id = mInfo.getId();
        rule=id+"@1";
        textQian.setText(mInfo.getPoints() + "积分");
        tv.setText(mInfo.getPoints() + "积分");
        textBiaoti.setText(mInfo.getTitle());
        ImageLoader.loadPlaceholder(mInfo.getLogo(),imgTupian);
        String address = SharedPrefUtils.getDefaultAdress();

        if (!TextUtils.isEmpty(address)) {
            ViewUtils.hideView(textDizi);
            ViewUtils.showView(tvAddress);
            tvAddress.setText(address);
            //默认地址id，不去选中就用这个
            mAddressId = SharedPrefUtils.getDefaultAdressId();
        } else {
            CommonViewModel commonViewModel = new ViewModelProvider(this).get(CommonViewModel.class);
            commonViewModel.getAdressList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(BasicApp.getContext()), Status.LOAD_REFRESH)
                    .observe(this, mObserver);

        }


    }


    private Observer<Status<AddressBean>> mObserver = new Observer<Status<AddressBean>>() {
        @Override
        public void onChanged(Status<AddressBean> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    AddressBean data = status.content;
                    List<AddressBean.DataBean.ListBean> list = data.getData().getList();
                    if (list == null || list.size() == 0) {

                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(tvAddress);
                        return;
                    }

                    if (data.getCode() == 1) {

                        saveDefault(list);


                    } else if (data.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(DuiHuanDingDanQueRenActivity.this);
                        finish();
                        FToast.error(data.getInfo());
                    } else {
                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(tvAddress);
                        FToast.error(data.getInfo());
                    }
                    break;
                case Status.ERROR:
                    FToast.error(status.message);
                    ViewUtils.showView(textDizi);
                    ViewUtils.hideView(tvAddress);
                    break;
                case Status.LOADING:

                    break;
            }
        }
    };

    private void saveDefault(List<AddressBean.DataBean.ListBean> list) {
        boolean hasDefault = false;
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            AddressBean.DataBean.ListBean item = list.get(i);

            int is_default = item.getIs_default();
            if (is_default == 1) {
                //存在默认就设置
                hasDefault = true;
                num = i;
                break;
            }
        }

        if (hasDefault) {

            AddressBean.DataBean.ListBean item = list.get(num);
            SharedPrefUtils.saveDefaultAdressId(String.valueOf(item.getId()));
            mAddressId = SharedPrefUtils.getDefaultAdressId();
            StringBuilder sb = new StringBuilder();
            sb.append(item.getName()).append(" ")
                    .append(item.getPhone()).append(" ").append("\n")
                    .append(item.getProvince()).append(" ")
                    .append(item.getCity()).append(" ")
                    .append(item.getArea()).append(" ")
                    .append(item.getAddress());
            SharedPrefUtils.saveDefaultAdress(sb.toString());
            ViewUtils.hideView(textDizi);
            ViewUtils.showView(tvAddress);
            tvAddress.setText(SharedPrefUtils.getDefaultAdress());
        } else {
            ViewUtils.showView(textDizi);
            ViewUtils.hideView(tvAddress);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_iv, R.id.tv_commit,R.id.text_dizi, R.id.text_address,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_dizi:
            case R.id.text_address:
                Intent intent = new Intent(this, RecAddressActivity.class);
                intent.putExtra("code", 100);
                startActivityForResult(intent, 100);
                break;
            case R.id.back_iv:
                finish();
                break;
            case R.id.tv_commit:
                if (TextUtils.isEmpty(tvAddress.getText())) {

                    new XPopup.Builder(this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new WarningPopup(this,
                                    "请选择收货地址", "确定")).show();
                    //FToast.warning("请选择收货地址");
                    return;
                }
                if (UtilsForClick.isFastClick()) {
                    //提交订单，成功后，去调用获取支付参数接口
                    mViewModel.getJiFenDuiHuanTiJiao(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),rule,  mAddressId,""+ AppUtils.getVersionCode(BasicApp.getContext()))
                            .observe(this, mCommitObserver);
                }
                break;
        }
    }

    /**
     * 提交
     */
    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    JiFenDuiHuanSubmitBean mJiFenDuiHuanSubmitBean = new Gson().fromJson(result, JiFenDuiHuanSubmitBean.class);
                    if (mJiFenDuiHuanSubmitBean.getCode() == 1) {
                        Intent intentProd = new Intent(DuiHuanDingDanQueRenActivity.this, DuiHuanSuccessActivity.class);
                      //  intentProd.putExtra("bannerData", "");
                        startActivity(intentProd);
                        finish();
                        FToast.success(mJiFenDuiHuanSubmitBean.getInfo());
                    }else if(mJiFenDuiHuanSubmitBean.getCode() == HttpUtils.CODE_INVALID){
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(mJiFenDuiHuanSubmitBean.getInfo());
                    } else {
                        FToast.error(mJiFenDuiHuanSubmitBean.getInfo());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            //返回更新地址
            AddressBean.DataBean.ListBean bean = data.getParcelableExtra("addressBean");
            mAddressId = String.valueOf(bean.getId());
            StringBuilder sb = new StringBuilder();
            sb.append(bean.getName()).append(" ")
                    .append(bean.getPhone()).append(" ").append("\n")
                    .append(bean.getProvince()).append(" ")
                    .append(bean.getCity()).append(" ")
                    .append(bean.getArea()).append(" ")
                    .append(bean.getAddress());
            String adress = sb.toString();
            ViewUtils.hideView(textDizi);
            ViewUtils.showView(tvAddress);
            tvAddress.setText(adress);
        }
    }
}

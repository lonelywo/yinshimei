package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AddressBean;
import com.cuci.enticement.bean.MonBackBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.YeJiYueFanBean;
import com.cuci.enticement.plate.cart.activity.LogisticsActivity;
import com.cuci.enticement.plate.common.popup.WarningPopup;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.smtt.sdk.WebView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class AchievementActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.web_guize)
    WebView webGuize;
    @BindView(R.id.text_weidabiao)
    TextView textWeidabiao;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.con_1)
    ConstraintLayout con1;
    @BindView(R.id.text_kelingqu)
    TextView textKelingqu;

    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.con_2)
    ConstraintLayout con2;
    @BindView(R.id.text_fafang)
    TextView textFafang;

    @BindView(R.id.con_3)
    ConstraintLayout con3;
    @BindView(R.id.text_weidabiao1)
    TextView textWeidabiao1;
    @BindView(R.id.text_weidabiao2)
    TextView textWeidabiao2;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.con_4)
    ConstraintLayout con4;
    @BindView(R.id.text_shanchu)
    TextView textShanchu;
    @BindView(R.id.edit_tv)
    TextView editTv;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.con_dibu)
    ConstraintLayout conDibu;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private String mAddressId;
    private String is = "dadadadadadadadadadad";
    private String express_no;
    private String express_code;
    private String express_company_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_achievement;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        int is_month = intent.getIntExtra("Data", 0);
        if (is_month == 1) {
            ViewUtils.showView(conDibu);

        }else {
            ViewUtils.hideView(conDibu);

        }

        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel.achievement(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2")
                .observe(this, mObserver);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                FToast.error("网络错误");
                break;
            case Status.LOADING:
                break;
        }

    };

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            YeJiYueFanBean mYeJiYueFanBean = new Gson().fromJson(b, YeJiYueFanBean.class);
            final YeJiYueFanBean.DataBean item = mYeJiYueFanBean.getData();
            if (item == null) {
                return;
            }
            if (mYeJiYueFanBean.getCode() == 1) {
                express_no = item.getExpress_no();
                express_code = item.getExpress_code();
                express_company_title = item.getExpress_company_title();
                String htmlContent = mYeJiYueFanBean.getData().getExplain();
                webGuize.loadDataWithBaseURL(null,
                        getHtmlData(htmlContent), "text/html", "utf-8", null);
                if (!TextUtils.isEmpty(item.getAmount())) {
                    textWeidabiao.setText(item.getAmount());
                }
                if (!TextUtils.isEmpty(item.getGift_name())) {
                    textWeidabiao1.setText(item.getGift_name());
                }
                if (item.getStatus().equals(1)) {
                    textWeidabiao2.setText("已发放");
                    ViewUtils.showView(textShanchu);
                    ViewUtils.hideView(editTv);
                } else {
                    textWeidabiao2.setText("-");
                    ViewUtils.hideView(textShanchu);
                    if (TextUtils.isEmpty(item.getAddress().getProvince())) {
                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(textAddress);
                        textAddress.setText("");
                        ViewUtils.showView(editTv);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(item.getAddress().getName()).append(" ")
                                .append(item.getAddress().getPhone()).append(" ").append("\n")
                                .append(item.getAddress().getProvince()).append(" ")
                                .append(item.getAddress().getCity()).append(" ")
                                .append(item.getAddress().getArea()).append(" ")
                                .append(item.getAddress().getAddress());
                        String adress = sb.toString();
                        ViewUtils.hideView(textDizi);
                        ViewUtils.showView(textAddress);
                        textAddress.setText(adress);
                        ViewUtils.hideView(editTv);
                    }
                }

            } else {
                FToast.error(mYeJiYueFanBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text_dizi, R.id.text_address, R.id.text_shanchu, R.id.edit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_dizi:
            case R.id.text_address:
                Intent intent = new Intent(AchievementActivity.this, RecAddressActivity.class);
                intent.putExtra("code", 100);
                startActivityForResult(intent, 100);
                break;
            case R.id.text_shanchu:
                //查看物流  intent
                Intent intent1 = new Intent(AchievementActivity.this, LogisticsActivity.class);
                intent1.putExtra("express_no", express_no);
                intent1.putExtra("express_code", express_code);
                intent1.putExtra("express_company_title", express_company_title);
                startActivity(intent1);
                break;
            case R.id.edit_tv:
                if (TextUtils.isEmpty(textAddress.getText())) {

                    new XPopup.Builder(AchievementActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new WarningPopup(AchievementActivity.this,
                                    "请选择收货地址", "确定")).show();


                    //FToast.warning("请选择收货地址");
                    return;
                }
                //提交月返地址
                mViewModel.monbackdizi("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), mAddressId)
                        .observe(AchievementActivity.this, mCommitObserver);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            //返回更新地址
            //todo
            /*String adress = data.getStringExtra("address");
            mAddressId= data.getStringExtra("addressId");*/
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
            ViewUtils.showView(textAddress);
            textAddress.setText(adress);
        }
    }

    /**
     * 提交月返地址
     */
    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    MonBackBean commitOrder = new Gson().fromJson(result, MonBackBean.class);
                    if (commitOrder.getCode() == 1) {
                        ViewUtils.hideView(editTv);
                        mViewModel.achievement(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2")
                                .observe(this, mObserver);
                        FToast.success("保存地址成功");

                    } else {
                        FToast.error("保存地址失败");
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


}

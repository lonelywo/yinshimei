package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissiontxBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class CashActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.text_tixian)
    TextView textTixian;
    @BindView(R.id.text_shuoming)
    TextView textShuoming;
    @BindView(R.id.edt_phone_container)
    ConstraintLayout edtPhoneContainer;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.text_mingxi)
    TextView textMingxi;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;


    @Override
    public int getLayoutId() {
        return R.layout.activity_cash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(CashActivity.this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new TipsPopup(CashActivity.this,
                                "亲，确定要提现吗？","取消","确定" ,() -> {
                            load();
                        }))
                        .show();

            }
        });
        textMingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(CashActivity.this, CashMXActivity.class);
                intentProd.putExtra("Data", "");
                startActivity(intentProd);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void load() {

        String meony = edtPhone.getText().toString();
        if (TextUtils.isEmpty(meony)) {
            FToast.warning("请填写金额");
            return;
        }
        mViewModel.txcommissionsq(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", meony)
                .observe(this, mObserver);
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

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            CommissiontxBean mCommissiontxBean = new Gson().fromJson(b, CommissiontxBean.class);
            if (mCommissiontxBean.getCode() == 1) {

            } else {
                FToast.warning(mCommissiontxBean.getInfo());
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
}

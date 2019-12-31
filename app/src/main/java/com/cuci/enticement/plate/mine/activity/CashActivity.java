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
import com.cuci.enticement.bean.CommissiontjBean;
import com.cuci.enticement.bean.CommissiontxBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.CashEvent;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.common.popup.TipsPopup1;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi_cash;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.text_lock_profit)
    TextView text_lockProfit;
    @BindView(R.id.lock_desc)
    TextView text_lockDesc;
    @BindView(R.id.con_lock)
    ConstraintLayout conLock;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private boolean isclick = true;
    private String lock_desc;


    @Override
    public int getLayoutId() {
        return R.layout.activity_cash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo!=null){
            mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2")
                    .observe(this, mObserver1);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(CashActivity.this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new TipsPopup(CashActivity.this,
                                "亲，确定要提现吗？", "取消", "确定", () -> {
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
        textShuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(CashActivity.this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new TipsPopupxieyi_cash(CashActivity.this,
                                () -> {

                                }))
                        .show();
            }
        });

    }

    private void load() {

        String meony = edtPhone.getText().toString();
        if (TextUtils.isEmpty(meony)) {
            FToast.warning("请填写金额");
            return;
        }
        if (isclick) {
            isclick = false;
            mViewModel.txcommissionsq(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", meony)
                    .observe(this, mObserver);
        }


    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                isclick = true;
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                isclick = true;
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
                FToast.success(mCommissiontxBean.getInfo());
                EventBus.getDefault().post(new CashEvent());
                if(mUserInfo!=null){
                    mViewModel.hqcommissiontj(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2")
                            .observe(this, mObserver1);
                }
            } else {
                FToast.error(mCommissiontxBean.getInfo());
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

    @OnClick(R.id.lock_desc)
    public void onViewClicked() {
        new XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new TipsPopup1(this,
                        lock_desc, "关闭", () -> {
                }))
                .show();
    }

    private Observer<Status<ResponseBody>> mObserver1 = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                opera1(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera1(ResponseBody body) {
        try {
            String b = body.string();
            CommissiontjBean mCommissiontjBean = new Gson().fromJson(b, CommissiontjBean.class);
            if (mCommissiontjBean.getCode() == 1) {
               double lock_profit = mCommissiontjBean.getData().getLock_profit();
                lock_desc = mCommissiontjBean.getData().getLock_desc();
                String subtract = MathExtend.subtract(String.valueOf(mCommissiontjBean.getData().getTotal()), String.valueOf(mCommissiontjBean.getData().getUsed()),String.valueOf(mCommissiontjBean.getData().getLock_profit()));
                edtPhone.setHint("可提现¥"+subtract);
                if(lock_profit>0){
                   ViewUtils.showView(conLock);
                    text_lockProfit.setText("有"+lock_profit+"元不可提现");
                }else {
                    ViewUtils.hideView(conLock);
                }
            } else {
                FToast.error(mCommissiontjBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }
}

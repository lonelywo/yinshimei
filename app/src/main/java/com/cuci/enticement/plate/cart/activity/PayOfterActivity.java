package com.cuci.enticement.plate.cart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissiontxBean;
import com.cuci.enticement.bean.PayOfterBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.mine.activity.KaQuanActivity;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.activity.SettingsActivity;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;

import java.io.IOException;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class PayOfterActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    private CartViewModel mViewModel;
    private UserInfo mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_ofter;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //透明状态栏
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        tvMoney.setText("实付"+ServiceCreator.Constant_ZONG_MONEY);
        load();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayOfterActivity.this, KaQuanActivity.class));
                finish();
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayOfterActivity.this, MainActivity.class));
                Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_HOME);
                LocalBroadcastManager.getInstance(PayOfterActivity.this)
                        .sendBroadcast(intentRank);
               finish();
            }
        });
        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayOfterActivity.this, MyOrderActivity.class));
                finish();
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
        if(ServiceCreator.Constant_IS_NEW==0){
            mViewModel.payofter(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()), "1",ServiceCreator.Constant_GOODS_ID).observe(this, mObserver);
        }else {
            mViewModel.payofter(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()), "0",ServiceCreator.Constant_GOODS_ID).observe(this, mObserver);
        }
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
            PayOfterBean mPayOfterBean = new Gson().fromJson(b, PayOfterBean.class);
            if (mPayOfterBean.getCode() == 1) {
                String img = mPayOfterBean.getData().getImg();
                String desc = mPayOfterBean.getData().getDesc();
                String replace = desc.replace("\\n", "\n");

                if(mPayOfterBean.getData().getIs_coupon()==1){
                    ImageLoader.loadPlaceholder1(img,imgLogo);
                    tv1.setText(replace);
                    ViewUtils.showView(ok);
                }else {
                    ImageLoader.loadPlaceholder1(img,imgLogo);
                    tv1.setText(replace);
                    ViewUtils.hideView(ok);
                }

            } else if(mPayOfterBean.getCode()==HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mPayOfterBean.getInfo());
            }else {
                FToast.error(mPayOfterBean.getInfo());
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

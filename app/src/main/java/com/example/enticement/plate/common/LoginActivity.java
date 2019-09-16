package com.example.enticement.plate.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.example.enticement.Constant;
import com.example.enticement.base.BaseActivity;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.plate.common.vm.LoginViewModel;
import com.example.enticement.plate.home.activity.ProdActivity;
import com.example.enticement.plate.mine.fragment._MineFragment;
import com.example.enticement.utils.FLog;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.Re;
import com.example.enticement.utils.SharedPrefUtils;
import com.example.enticement.widget.ClearEditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.img_shoutu)
    ImageView imgShoutu;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.text_shoujihao)
    TextView textShoujihao;
    @BindView(R.id.edt_phone_container)
    ConstraintLayout edtPhoneContainer;
    @BindView(R.id.text_yanzhengma)
    TextView textYanzhengma;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.text_zhuce)
    TextView textZhuce;
    @BindView(R.id.text_dibuwenzi)
    TextView textDibuwenzi;
    @BindView(R.id.weixin)
    ImageView weixin;
    @BindView(R.id.text_weixindenglu)
    TextView textWeixindenglu;
    public static final String ACTION_WX_LOGIN_SUCCEED = "com.example.enticement.plate.user.ACTION_WX_LOGIN_SUCCEED";
    private LocalBroadcastManager mBroadcastManager;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel mViewModel;
    private String guojiacode;
    private Handler mTimeHandler = new Handler();
    private boolean mShowContract = false;
    public interface OnLoginListener {
        void onLoginSucceed(UserInfo userInfo, boolean showContract);
    }

    private OnLoginListener mOnLoginListener;

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_WX_LOGIN_SUCCEED);
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_code, R.id.ok, R.id.text_zhuce, R.id.weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                getSmsCodelogin();
                break;
            case R.id.ok:
                login();
                break;
            case R.id.text_zhuce:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.weixin:
                break;
        }
    }


    private void login() {

        String phone = edtPhone.getText().toString().trim();
        String smsCode = edtCode.getText().toString().trim();
        if ( TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(smsCode) ) {
            FToast.warning("请填写完整");
            return;
        }

        mViewModel.login(phone,smsCode).observe(this, mObserver);

    }
    private Observer<Status<Base<UserInfo>>> mObserver = new Observer<Status<Base<UserInfo>>>() {
        @Override
        public void onChanged(Status<Base<UserInfo>> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    LoginActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    LoginActivity.this.dismissLoading();
                    break;
                case Status.SUCCESS:
                    LoginActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    if (baseStatus.content.code == 1) {
                        UserInfo userInfo =baseStatus.content.data;
                        FToast.success("注册成功，请登录");
                        finish();
                        Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                        intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                        LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);


                        if (mOnLoginListener != null) {
                            mOnLoginListener.onLoginSucceed(userInfo, mShowContract);
                        }
                    } else {
                        FToast.error(baseStatus.content.msg);
                    }
                    break;
            }
        }
    };


    private void getSmsCodelogin() {

        String phone = edtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone) || !Re.is11Number(phone)) {
            FToast.warning("请填写正确的手机号");
            return;
        }
        mViewModel.getSmsCodelogin(phone, "cuci", "86").observe(this, mSmsCodeObserver);
    }
    private Observer<Status<Base>> mSmsCodeObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.SUCCESS:
                    LoginActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    FLog.e("dengluyanzhengma",baseStatus.content.toString());
                    if (baseStatus.content.code == 1) {
                        tvCode.setClickable(false);
                        tvCode.setTag(60);
                        tvCode.setBackground(ContextCompat.getDrawable(LoginActivity.this,
                                R.drawable.shape_login_get_code_gray));
                        FToast.success(baseStatus.content.info);
                        run.run();
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
                case Status.LOADING:
                    LoginActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    LoginActivity.this.dismissLoading();
                    FToast.error("网络错误");
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(mReceiver);
    }
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = SharedPrefUtils.getWechatAuth();
            if (intent != null
                    && ACTION_WX_LOGIN_SUCCEED.equals(intent.getAction())
                    && "login".equals(type)) {
                if (tenOuter()) {
                    //超过10分钟

                    String code = intent.getStringExtra("code");
                    FLog.e(TAG, "超过10分钟：" + code);
                 /*   mViewModel.getWxToken(Constant.WX_APP_ID, Constant.WX_APP_SECRET_ID,
                            code, "authorization_code")
                            .observe(LoginActivity.this, mWxTokenObserver);*/
                } else {
                    //十分钟内
                    //获取保存的信息
                    String wxOpenId = SharedPrefUtils.getWxOpenId();
                    String openId = wxOpenId.split("FDSH")[1];
                    String token = wxOpenId.split("FDSH")[2];
                    FLog.e(TAG, "十分钟内：" + openId + " " + token);
                  /*  mViewModel.getWxInfo(token, openId)
                            .observe(LoginActivity.this, mWxInfoObserver);*/
                }

            }
        }
    };
    //判断是否超过10分钟
    private boolean tenOuter() {

        String wxOpenId = SharedPrefUtils.getWxOpenId();

        if ("0FDSH0FDSH0".equals(wxOpenId)) {
            return true;
        }

        long oldTime = Long.parseLong(wxOpenId.split("FDSH")[0]);

        long now = new Date().getTime();
        long ten = 10 * 60 * 1000L;

        if (now - oldTime >= ten) {
            return true;
        }

        return false;
    }


    private Runnable run = new Runnable() {
        @Override
        public void run() {
            int a = (int) tvCode.getTag();
            tvCode.setTag(a - 1);
            tvCode.setText("重新获取(" + a + "s)");
            if (a > 0) {
                mTimeHandler.postDelayed(run, 1000L);
            } else {
                tvCode.setClickable(true);
                tvCode.setText("获取验证码");
                tvCode.setBackground(ContextCompat.getDrawable(LoginActivity.this,
                        R.drawable.shape_sibian_bai_bg5));
            }
        }
    };
}

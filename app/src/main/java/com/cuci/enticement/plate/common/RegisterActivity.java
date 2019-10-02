package com.cuci.enticement.plate.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.vm.RegActivityViewModel;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.Re;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.text_suosu)
    TextView textSuosu;
    @BindView(R.id.text_guojia)
    TextView textGuojia;
    @BindView(R.id.img_youjiantou)
    ImageView imgYoujiantou;
    @BindView(R.id.viem_line)
    View viemLine;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.viem_line1)
    View viemLine1;
    @BindView(R.id.text_shoujihao)
    TextView textShoujihao;
    @BindView(R.id.text_yanzhengma)
    TextView textYanzhengma;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.text_tuijianren)
    TextView textTuijianren;
    @BindView(R.id.edt_code_tuijian)
    EditText edtCodeTuijian;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.con_zhongjian)
    ConstraintLayout conZhongjian;
    private Handler mTimeHandler = new Handler();
    private RegActivityViewModel mViewModel;
    private String guojiacode;
    private int mQrCodeChoice=0;

    private String[] mQrItems = new String[]{"中国", "马来西亚"};
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(RegActivityViewModel.class);
    }



    @OnClick({R.id.tv_code, R.id.ok,R.id.image_back,R.id.text_guojia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                sendSmsCode();
                break;
            case R.id.ok:

                register();
                break;
            case R.id.image_back:
               finish();

                break;
            case R.id.text_guojia:
                showQrCodeDialog();

                break;
        }
    }

    private void register() {

        String phone = edtPhone.getText().toString().trim();
        String smsCode = edtCode.getText().toString().trim();
        String inviteCode = edtCodeTuijian.getText().toString().trim();

        if (TextUtils.isEmpty(inviteCode) || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(smsCode) ) {
            FToast.warning("请填写完整");
            return;
        }
        if (TextUtils.isEmpty(inviteCode)) {
            FToast.warning("请填写正确的推荐人手机号");
            return;
        }
        mViewModel.register(smsCode,phone,inviteCode,"","","","","").observe(this, mObserver);

    }
    private Observer<Status<Base<UserInfo>>> mObserver = new Observer<Status<Base<UserInfo>>>() {
        @Override
        public void onChanged(Status<Base<UserInfo>> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    RegisterActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    RegisterActivity.this.dismissLoading();
                    break;
                case Status.SUCCESS:
                    RegisterActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    if (baseStatus.content.code == 1) {
                        FToast.success("注册成功，请登录");
                        finish();
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
            }
        }
    };

    private void sendSmsCode() {

        String phone = edtPhone.getText().toString().trim();
        String guojia = textGuojia.getText().toString();
        if (TextUtils.isEmpty(phone) ) {
            FToast.warning("请填写手机号");
            return;
        }
        if(guojia.equals("中国")){
            guojiacode ="86";
        }else {
            guojiacode ="60";
        }

        mViewModel.getSmsCode(phone, "cuci", guojiacode,"1").observe(this, mSmsCodeObserver);

    }
    private Observer<Status<Base>> mSmsCodeObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.SUCCESS:
                    RegisterActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    FLog.e("zhuceyanzhengma",baseStatus.content.toString());
                    if (baseStatus.content.code == 1) {
                        tvCode.setClickable(false);
                        tvCode.setTag(60);
                        tvCode.setBackground(ContextCompat.getDrawable(RegisterActivity.this,
                                R.drawable.shape_login_get_code_gray));
                        FToast.success(baseStatus.content.info);
                        run.run();
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
                case Status.LOADING:
                    RegisterActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    RegisterActivity.this.dismissLoading();
                    FToast.error("网络错误");
                    break;
            }
        }
    };

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
                tvCode.setBackground(ContextCompat.getDrawable(RegisterActivity.this,
                        R.drawable.shape_sibian_bai_bg5));
            }
        }
    };
    private void showQrCodeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("选择");
        if (mQrCodeChoice == 0) {
            mQrCodeChoice = 0;
        } else if (mQrCodeChoice == 1) {
            mQrCodeChoice = 1;
        }
        builder.setSingleChoiceItems(mQrItems, mQrCodeChoice, (dialog, which) ->
                mQrCodeChoice = which);

        builder.setPositiveButton("保存", (dialog, which) -> {
            if (mQrCodeChoice == 0) {
                SharedPrefUtils.saveShowquyuCode(86);
                textGuojia.setText("中国");
            } else if (mQrCodeChoice == 1) {
                SharedPrefUtils.saveShowquyuCode(60);
                textGuojia.setText("马来西亚");
            }
            FToast.success("保存成功");
        });

        builder.setNegativeButton("取消", null);
        builder.create().show();

    }


}

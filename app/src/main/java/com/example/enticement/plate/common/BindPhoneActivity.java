package com.example.enticement.plate.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseActivity;
import com.example.enticement.bean.Base;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.common.vm.BindPhoneViewModel;
import com.example.enticement.plate.mine.fragment._MineFragment;
import com.example.enticement.utils.FLog;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.Re;
import com.example.enticement.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    private String mUnionId;
    private BindPhoneViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) return;

        mUnionId = intent.getStringExtra(LoginActivity.DATA_UNION_ID);

        if (mUnionId == null) {
            FToast.error("数据有误");
            return;
        }

        mViewModel = ViewModelProviders.of(this).get(BindPhoneViewModel.class);
    }


    @OnClick({R.id.iv_back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ok:
                bindPhone();
                break;
        }
    }

    private void bindPhone() {
        String phone = edtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || !Re.is11Number(phone)) {
            FToast.warning("请填写正确的手机号");
            return;
        }



        mViewModel.wxBindPhone(phone).observe(this, mWxBindPhoneObserver);
    }


    private Observer<Status<Base>> mWxBindPhoneObserver = baseStatus -> {
        switch (baseStatus.status) {
            case Status.SUCCESS:
                dismissLoading();
                  dispatchUserInfo(baseStatus);
                finish();
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");

                break;
            case Status.LOADING:
                showLoading();
                break;
        }
    };

    private void dispatchUserInfo(Status<Base> baseStatus) {
        Base base = baseStatus.content;

        if (base == null) {
            FToast.error("请求失败");
            return;
        }
        if (base.code == 1) {

                //登录成功
                FToast.success("绑定成功");
                Intent intent = new Intent();
                intent.putExtra(_MineFragment.DATA_USER_INFO,1);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }




        }




}

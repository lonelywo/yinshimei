package com.cuci.enticement.plate.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.CheckPhoneBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxInfo;
import com.cuci.enticement.plate.common.vm.RegActivityViewModel;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.biz.UserInfoBean;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class BindPhoneActivity extends BaseActivity {


    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.viem_line1)
    View viemLine1;
    @BindView(R.id.text_shoujihao)
    TextView textShoujihao;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ok)
    TextView ok;
    private Handler mTimeHandler = new Handler();
    private RegActivityViewModel mViewModel;
    private String guojiacode;
    private int mQrCodeChoice = 0;

    private String[] mQrItems = new String[]{"中国", "马来西亚"};
    private WxInfo minfo;
    private static final String TAG = BindPhoneActivity.class.getSimpleName();
    private boolean mSucceed = false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(RegActivityViewModel.class);
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        minfo= (WxInfo) intent.getSerializableExtra("Data");
        if (minfo == null) {
            FToast.error("数据错误");
            return;
        }
    }


    @OnClick({R.id.ok, R.id.image_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ok:
                bindPhone();
                break;
            case R.id.image_back:
                finish();
                break;

        }
    }

    private void bindPhone() {
        String phone = edtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone)
        ) {
            FToast.warning("请填写完整");
            return;
        }
        //  mViewModel.bindPhone(smsCode,phone).observe(this, mObserver);
        mViewModel.wxBindPhone(phone,minfo.getUnionId(),minfo.getOpenId(),  minfo.getHeadImgUrl(),  minfo.getNickName(),"2",String.valueOf(minfo.getSex())
        ).observe(this, mObservercheck);
    }
    private Observer<Status<Base<UserInfo>>> mObservercheck = baseStatus -> {
        switch (baseStatus.status) {
            case Status.SUCCESS:
                dismissLoading();
                Base<UserInfo> userInfoBase = baseStatus.content;
                if (userInfoBase.code == 1) {
                    UserInfo muserInfoBase = userInfoBase.data;

                    dispatchUserInfo(muserInfoBase);
                } else {
                    FToast.error(userInfoBase.msg);
                }
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };
    private void dispatchUserInfo(UserInfo userInfo) {
        if ("1".equals(userInfo.getIs_binding())) {
            //登录成功
            boolean save = SharedPrefUtils.save(userInfo, UserInfo.class);
            FToast.success("登录成功");
            mSucceed = true;
            if (save) {
                FLog.e(TAG, "用户信息保存成功");
            } else {
                FLog.e(TAG, "用户信息保存失败");
            }
            Intent intent = new Intent();
            intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        if (!mSucceed) {
            FToast.warning("未完成绑定，请重新登录");
        }
        super.onDestroy();

    }
}

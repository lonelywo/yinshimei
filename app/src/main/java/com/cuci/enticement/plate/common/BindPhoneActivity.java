package com.cuci.enticement.plate.common;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.CheckPhoneBean;
import com.cuci.enticement.bean.GuoJiaBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxInfo;
import com.cuci.enticement.event.LoginSucceedEvent;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.common.vm.RegActivityViewModel;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.Re;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private boolean ischeck = false;
    private WxInfo minfo;
    private static final String TAG = BindPhoneActivity.class.getSimpleName();
    private boolean mSucceed = false;
    private int reg = 1;
    private String[] mQrItems ;
    private Integer[] mQrItems2 ;
    private List<Integer> list1 = new ArrayList<Integer>();
    private  List<String> list2 = new ArrayList<String>();
    private GuoJiaBean mGuoJiaBean;
    private Integer guojiacode=86;
    private int mQrCodeChoice = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(RegActivityViewModel.class);

        mGuoJiaBean = SharedPrefUtils.get(GuoJiaBean.class);
        if(mGuoJiaBean!=null){
            for (int i = 0; i < mGuoJiaBean.getData().size() ; i++) {
                list1.add(mGuoJiaBean.getData().get(i).getCode());
                list2.add(mGuoJiaBean.getData().get(i).getTitle());
            }
            mQrItems = list2.toArray(new String[list2.size()]);
            mQrItems2 = list1.toArray(new Integer[list1.size()]);
        }
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        minfo = (WxInfo) intent.getSerializableExtra("Data");
        if (minfo == null) {
            FToast.error("数据错误");
            return;
        }
    }


    @OnClick({R.id.tv_code, R.id.ok, R.id.image_back,R.id.text_shoujihao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_code:

                bindPhone();
                break;
            case R.id.ok:
                if (!ischeck) {
                    FToast.error("网络错误，未验证手机号");
                } else {
                    if (reg == 1) {
                        register2();
                    } else {
                        register1();
                    }

                }
                break;
            case R.id.text_shoujihao:
                showQrCodeDialog();
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
            FToast.warning("请填写手机号");
            return;
        }

        mViewModel.wxCheckBindPhone(phone,""+ AppUtils.getVersionCode(BasicApp.getContext()))
                .observe(this, mObservercheck);

    }

    private Observer<Status<ResponseBody>> mObservercheck = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                operacheck(body);
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };

    private void operacheck(ResponseBody body) {
        try {
            String b = body.string();
            CheckPhoneBean info = new Gson().fromJson(b, CheckPhoneBean.class);
            if (info.getCode() == 1) {
                if (info.getData().getIs_reg() == 1) {
                    textTuijianren.setVisibility(View.GONE);
                    edtCodeTuijian.setVisibility(View.GONE);
                    reg = 1;
                    ischeck = true;
                    sendSmsCode("2");
                } else {
                    textTuijianren.setVisibility(View.VISIBLE);
                    edtCodeTuijian.setVisibility(View.VISIBLE);
                    reg = 0;
                    ischeck = true;
                    sendSmsCode("1");
                }
            } else {
                FToast.error(info.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }


    private void sendSmsCode(String type) {

        String phone = edtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            FToast.warning("请填写手机号");
            return;
        }

        mViewModel.getSmsCode(phone, "cuci", ""+guojiacode, type,""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mSmsCodeObserver);

    }

    private Observer<Status<Base>> mSmsCodeObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.SUCCESS:
                    BindPhoneActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    FLog.e("zhuceyanzhengma", baseStatus.content.toString());
                    if (baseStatus.content.code == 1) {
                        tvCode.setClickable(false);
                        tvCode.setTag(60);
                        tvCode.setBackground(ContextCompat.getDrawable(BindPhoneActivity.this,
                                R.drawable.shape_login_get_code_gray));
                        FToast.success(baseStatus.content.info);
                        run.run();
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
                case Status.LOADING:
                    BindPhoneActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    BindPhoneActivity.this.dismissLoading();
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
                tvCode.setBackground(ContextCompat.getDrawable(BindPhoneActivity.this,
                        R.drawable.shape_sibian_bai_bg5));
            }
        }
    };


    private Observer<Status<Base<UserInfo>>> mObservercheck1 = baseStatus -> {
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
            FToast.success("绑定成功");
            boolean save = SharedPrefUtils.save(userInfo, UserInfo.class);
            mSucceed = true;
            EventBus.getDefault().post(new LoginSucceedEvent());
            //关闭详情
            EventBus.getDefault().post(new ProgoodsEvent());
            if (save) {
                FLog.e(TAG, "用户信息保存成功");
            } else {
                FLog.e(TAG, "用户信息保存失败");
            }
           /* Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
            intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);*/
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

    //注册接口
    private void register1() {

        String phone = edtPhone.getText().toString().trim();
        String smsCode = edtCode.getText().toString().trim();

        String inviteCode = edtCodeTuijian.getText().toString().trim();

        if ( TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(smsCode)) {
            FToast.warning("请填写手机号和验证码");
            return;
        }
      /*  if (TextUtils.isEmpty(inviteCode)) {
            FToast.warning("请填写正确的推荐人手机号");
            return;
        }*/
        mViewModel.register(smsCode, phone, inviteCode, minfo.getUnionId(), minfo.getOpenId(), minfo.getHeadImgUrl(), minfo.getNickName(), String.valueOf(minfo.getSex()),""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mObserver1);

    }

    //绑定手机接口
    private void register2() {

        String phone = edtPhone.getText().toString().trim();
        String smsCode = edtCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(smsCode)) {
            FToast.warning("请填写手机号和验证码");
            return;
        }
        mViewModel.wxBindPhone(phone,smsCode, minfo.getUnionId(), minfo.getOpenId(), minfo.getHeadImgUrl(), minfo.getNickName(), "2", String.valueOf(minfo.getSex()),
                ""+ AppUtils.getVersionCode(BasicApp.getContext())  ).observe(this, mObservercheck1);

    }

    private Observer<Status<Base<UserInfo>>> mObserver1 = new Observer<Status<Base<UserInfo>>>() {
        @Override
        public void onChanged(Status<Base<UserInfo>> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    BindPhoneActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    BindPhoneActivity.this.dismissLoading();
                    break;
                case Status.SUCCESS:
                    BindPhoneActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    if (baseStatus.content.code == 1) {
                        //登录成功
                        FToast.success("绑定成功");
                        String s = new Gson().toJson(baseStatus.content.data);
                        UserInfo userInfo = baseStatus.content.data;
                        boolean save = SharedPrefUtils.save(userInfo, UserInfo.class);
                        mSucceed = true;
                        EventBus.getDefault().post(new LoginSucceedEvent());
                        if (save) {
                            FLog.e(TAG, "用户信息保存成功");
                        } else {
                            FLog.e(TAG, "用户信息保存失败");
                        }
                        /*Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                        intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                        LocalBroadcastManager.getInstance(BindPhoneActivity.this).sendBroadcast(intent);*/
                        finish();

                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
            }
        }
    };

    private void showQrCodeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("选择");

        builder.setSingleChoiceItems(mQrItems, list1.size(), (dialog, which) ->
                mQrCodeChoice = which);

        builder.setPositiveButton("确定", (dialog, which) -> {

           // textGuojia.setText(mQrItems[mQrCodeChoice]);
            guojiacode=mQrItems2[mQrCodeChoice];
            textShoujihao.setText("+"+guojiacode);
        });

        builder.setNegativeButton("取消", null);
        builder.create().show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

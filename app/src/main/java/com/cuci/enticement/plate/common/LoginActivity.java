package com.cuci.enticement.plate.common;

import android.app.AlertDialog;
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

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.GuoJiaBean;
import com.cuci.enticement.bean.LoginBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxError;
import com.cuci.enticement.bean.WxInfo;
import com.cuci.enticement.bean.WxToken;
import com.cuci.enticement.event.LoginSucceedEvent;
import com.cuci.enticement.plate.common.eventbus.CartEvent;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi;
import com.cuci.enticement.plate.common.vm.LoginViewModel;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.RSAUtil;
import com.cuci.enticement.utils.Re;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class LoginActivity extends BaseActivity {

    public static final String ACTION_WX_LOGIN_SUCCEED = "com.example.enticement.plate.user.ACTION_WX_LOGIN_SUCCEED";
    public static final String DATA_UNION_ID = "data_union_id";
    @BindView(R.id.img_shoutu)
    ImageView imgShoutu;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.text_suosu)
    TextView textSuosu;
    @BindView(R.id.text_guojia)
    TextView textGuojia;
    @BindView(R.id.img_youjiantou)
    ImageView imgYoujiantou;
    @BindView(R.id.view_zeng)
    View viewZeng;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.view_zeng1)
    View viewZeng1;
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

    private LocalBroadcastManager mBroadcastManager;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel mViewModel;
    private Integer guojiacode=86;
    private Handler mTimeHandler = new Handler();
    private boolean mShowContract = false;
    private String mUnionId = "";
    private WxInfo minfo;
    private int mQrCodeChoice=0;
    //private String[] mQrItems = new String[]{"中国", "马来西亚"};
    private GuoJiaBean mGuoJiaBean;
    private String[] mQrItems ;
    private Integer[] mQrItems2 ;
    private List<Integer> list1 = new ArrayList<Integer>();
    private  List<String> list2 = new ArrayList<String>();
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
        mGuoJiaBean = SharedPrefUtils.get(GuoJiaBean.class);
        if(mGuoJiaBean.getData()!=null){
            for (int i = 0; i < mGuoJiaBean.getData().size() ; i++) {
                list1.add(mGuoJiaBean.getData().get(i).getCode());
                list2.add(mGuoJiaBean.getData().get(i).getTitle());
            }
            mQrItems = list2.toArray(new String[list2.size()]);
            mQrItems2 = list1.toArray(new Integer[list1.size()]);
        }
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_WX_LOGIN_SUCCEED);
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick({R.id.tv_code, R.id.ok, R.id.text_zhuce, R.id.weixin, R.id.text_dibuwenzi,R.id.text_guojia})
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
                SharedPrefUtils.saveWechatAuth("login");
                startWxLogin();
                break;
            case R.id.text_dibuwenzi:
                new XPopup.Builder(this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new TipsPopupxieyi(this,
                                () -> {

                                }))
                        .show();

                break;
            case R.id.text_guojia:
                showQrCodeDialog();
                break;
        }
    }

    private void startWxLogin() {

        if (!BasicApp.getIWXAPI().isWXAppInstalled()) {
            FToast.error("您还未安装微信客户端");
            return;
        }

        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_login_yinsimei_app";
        BasicApp.getIWXAPI().sendReq(req);


    }

    private void login() {

        String phone = edtPhone.getText().toString().trim();
        String smsCode = edtCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(smsCode)) {
            FToast.warning("请填写完整");
            return;
        }
        LoginBean loginBean = new LoginBean();
        loginBean.setPhone(phone);
        loginBean.setCode(smsCode);
        String mloginBean = new Gson().toJson(loginBean);
        String data = RSAUtil.encryptByPublic(this, mloginBean);


        mViewModel.login(data).observe(this, mObserver);

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

                    FToast.error("网络错误");
                    break;
                case Status.SUCCESS:
                    LoginActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("数据异常");
                        return;
                    }
                    if (baseStatus.content.code == 1) {
                        String s = new Gson().toJson(baseStatus.content.data);
                        UserInfo userInfo = baseStatus.content.data;
                        FToast.success("登录成功");
                        SharedPrefUtils.save(userInfo, UserInfo.class);
                        //登录成功后发广播刷新，此次改成eventbus，原先的先不删除
                      /*  Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                        intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                        LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);*/
                        //eventbus  刷新视图  四个fragment重新载入

                        EventBus.getDefault().post(new LoginSucceedEvent());

                        //刷新购物车数据

                        EventBus.getDefault().postSticky(new CartEvent(CartEvent.REFRESH_CART_LIST));
                        /*LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(LoginActivity.this);
                        broadcastManager.sendBroadcast(new Intent(ACTION_REFRESH_DATA));*/


                        finish();

                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
            }
        }
    };


    private void getSmsCodelogin() {

        String phone = edtPhone.getText().toString().trim();
        String guojia = textGuojia.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            FToast.warning("请填写手机号");
            return;
        }

        mViewModel.getSmsCodelogin(phone, "cuci", ""+guojiacode).observe(this, mSmsCodeObserver);
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
                    FLog.e("dengluyanzhengma", baseStatus.content.toString());
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
                    mViewModel.getWxToken(Constant.WX_APP_ID, Constant.WX_APP_SECRET_ID,
                            code, "authorization_code")
                            .observe(LoginActivity.this, mWxTokenObserver);
                } else {
                    //十分钟内
                    //获取保存的信息
                    String wxOpenId = SharedPrefUtils.getWxOpenId();
                    String openId = wxOpenId.split("YSM")[1];
                    String token = wxOpenId.split("YSM")[2];
                    FLog.e(TAG, "十分钟内：" + openId + " " + token);
                    mViewModel.getWxInfo(token, openId)
                            .observe(LoginActivity.this, mWxInfoObserver);
                }

            }
        }
    };
    private Observer<Status<ResponseBody>> mWxTokenObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                operaWxToken(body);
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

    private void operaWxToken(ResponseBody body) {
        try {
            String b = body.string();
            if (b.contains("errcode")) {
                dismissLoading();
                WxError error = new Gson().fromJson(b, WxError.class);
              //  finish();
                FToast.error(error.getErrMsg() + "->" + error.getErrCode());
              //  FToast.error("请再登录");
            } else {
                WxToken token = new Gson().fromJson(b, WxToken.class);

                if (tenOuter()) {
                    SharedPrefUtils.saveWxOpenId(new Date().getTime() + "YSM" +
                            token.getOpenId() + "YSM" + token.getAccessToken());
                }

                //获取微信账户信息
                mViewModel.getWxInfo(token.getAccessToken(), token.getOpenId())
                        .observe(this, mWxInfoObserver);
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }

    private Observer<Status<ResponseBody>> mWxInfoObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                operaWxInfo(body);
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };

    private void operaWxInfo(ResponseBody body) {
        try {
            String b = body.string();
            if (b.contains("errcode")) {
                dismissLoading();
                WxError error = new Gson().fromJson(b, WxError.class);
                FToast.error(error.getErrMsg() + ":" + error.getErrCode());
            } else {
                minfo = new Gson().fromJson(b, WxInfo.class);
                mUnionId = minfo.getUnionId();

                mViewModel.checkUserInfo(minfo.getUnionId(), minfo.getOpenId(), minfo.getHeadImgUrl(), minfo.getNickName(), "2", String.valueOf(minfo.getSex())
                ).observe(this, mCheckWxInfoObserver);
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }


    private Observer<Status<Base<UserInfo>>> mCheckWxInfoObserver = baseStatus -> {
        switch (baseStatus.status) {
            case Status.SUCCESS:
                dismissLoading();
                Base<UserInfo> userInfoBase = baseStatus.content;
                if (userInfoBase.code == 1) {
                    UserInfo muserInfoBase = userInfoBase.data;

                    dispatchUserInfo(muserInfoBase);
                } else {
                    FToast.error(userInfoBase.info);
                }
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };

    private void dispatchUserInfo(UserInfo userInfo) {
        if ("0".equals(userInfo.getIs_binding())) {

            FToast.warning("请绑定手机号");
            Intent intent = new Intent(this, BindPhoneActivity.class);
            intent.putExtra("Data", minfo);
            startActivity(intent);
            finish();
        } else {
            //登录成功
            boolean save = SharedPrefUtils.save(userInfo, UserInfo.class);
            FToast.success("登录成功");
            EventBus.getDefault().post(new LoginSucceedEvent());
            if (save) {
                FLog.e(TAG, "用户信息保存成功");
            } else {
                FLog.e(TAG, "用户信息保存失败");
            }
         /*   Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
            intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);*/
            finish();
        }
    }


    //判断是否超过10分钟
    private boolean tenOuter() {

        String wxOpenId = SharedPrefUtils.getWxOpenId();

        if ("0YSM0YSM0".equals(wxOpenId)) {
            return true;
        }

        long oldTime = Long.parseLong(wxOpenId.split("YSM")[0]);

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
    private void showQrCodeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("选择");

        builder.setSingleChoiceItems(mQrItems, list1.size(), (dialog, which) ->
                mQrCodeChoice = which);

        builder.setPositiveButton("确定", (dialog, which) -> {

            textGuojia.setText(mQrItems[mQrCodeChoice]);
            guojiacode=mQrItems2[mQrCodeChoice];
        });

        builder.setNegativeButton("取消", null);
        builder.create().show();

    }


}

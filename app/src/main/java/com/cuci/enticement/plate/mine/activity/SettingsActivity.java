package com.cuci.enticement.plate.mine.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxError;
import com.cuci.enticement.bean.WxInfo;
import com.cuci.enticement.bean.WxToken;
import com.cuci.enticement.bean.WxbindBean;
import com.cuci.enticement.event.LoginOutEvent;
import com.cuci.enticement.plate.common.HuanBindActivity;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.igexin.sdk.PushManager;
import com.lxj.xpopup.XPopup;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import java.util.Date;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import static com.cuci.enticement.plate.common.LoginActivity.ACTION_WX_LOGIN_SUCCEED;


public class SettingsActivity extends BaseActivity {
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.bind_status_tv)
    TextView bindStatusTv;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    public static final String DATA_USER_INFO = "data_user_info_";
    private static final String TAG = SettingsActivity.class.getSimpleName();
    private LocalBroadcastManager mBroadcastManager;



    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        phoneTv.setText(mUserInfo.getPhone());
        int  wxBind = SharedPrefUtils.getWXBind();
        if(wxBind==1){
            //todo   这里换成微信号显示   不要用昵称
         //   bindStatusTv.setText(mUserInfo.getNickname());
            bindStatusTv.setText("已绑定");
        }else {
            bindStatusTv.setText("未绑定");
        }
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_WX_LOGIN_SUCCEED);
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }



    @OnClick({R.id.image_back, R.id.ll_info, R.id.ll_phone, R.id.ll_wechat,R.id.ll_tuijianren,R.id.ll_about_us, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ll_info:
                startActivity(new Intent(this,InfoActivity.class));
                break;
            case R.id.ll_phone:
                Intent intent = new Intent(this, HuanBindActivity.class);
                startActivityForResult(intent, 10010);
                break;
            case R.id.ll_wechat:
             if(SharedPrefUtils.getWXBind()==1){
                 new XPopup.Builder(this)
                         .dismissOnBackPressed(false)
                         .dismissOnTouchOutside(false)
                         .asCustom(new TipsPopup(this,
                                 "要解除与微信账号的绑定吗？", "取消", "解除绑定", () -> {


                             mViewModel.jiebindwx("2", ""+mUserInfo.getId(), mUserInfo.getToken(),""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mjiebindwxObserver);

                         }))
                         .show();
             }else {
                 new XPopup.Builder(this)
                         .dismissOnBackPressed(false)
                         .dismissOnTouchOutside(false)
                         .asCustom(new TipsPopup(this,
                                 "要与微信账号绑定吗？", "取消", "确定绑定", () -> {
                             SharedPrefUtils.saveWechatAuth("login");
                             startWxLogin();


                         }))
                         .show();
             }
                break;
            case R.id.ll_tuijianren:
                Intent intent1 = new Intent(this, BindTuiJinActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_about_us:

                startActivity(new Intent(this,YinshimeiActivity.class));
                break;
            case R.id.tv_exit:
                if (AppUtils.isAllowPermission(this)) {

                    new XPopup.Builder(this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(this,
                                    "亲，确定要退出吗？", "取消", "确定", () -> {

                                int mid = mUserInfo.getId();
                                String token = mUserInfo.getToken();
                                mViewModel.loginOut("2", token, String.valueOf(mid),""+AppUtils.getVersionCode(this)).observe(this, mloginoutObserver);

                            }))
                            .show();

                }
                break;
        }
    }

    private Observer<Status<Base>> mloginoutObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    showLoading();
                    break;
                case Status.ERROR:
                    loginout();
                    break;
                case Status.SUCCESS:
                    loginout();
                    break;
            }
        }
    };


    private void loginout() {
        dismissLoading();
        FToast.success("退出登录");
        //清空微信绑定标识
        SharedPrefUtils.saveWXBind(0);
        //清空环信
        SharedPrefUtils.saveShowhxCode(0);
        //解绑个推
        PushManager.getInstance().unBindAlias(this, String.valueOf(mUserInfo.getId()), true);
        //退出默认设置isnew为0
        SharedPrefUtils.saveisnew(0);
        //清空默认地址缓存
        SharedPrefUtils.saveDefaultAdress("");
        SharedPrefUtils.exit();
        mUserInfo = null;
        EventBus.getDefault().post(new LoginOutEvent());
        //第一个参数为是否解绑推送的devicetoken
        ChatClient.getInstance().logout(true, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String error) {

            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == 10010 ) {
                String phone = data.getStringExtra(SettingsActivity.DATA_USER_INFO);
                phoneTv.setText(phone);
               /* Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);*/

            }
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

                    mViewModel.getWxToken(Constant.WX_APP_ID, Constant.WX_APP_SECRET_ID,
                            code, "authorization_code")
                            .observe(SettingsActivity.this, mWxTokenObserver);
                } else {
                    //十分钟内
                    //获取保存的信息
                    String wxOpenId = SharedPrefUtils.getWxOpenId();
                    String openId = wxOpenId.split("YSM")[1];
                    String token = wxOpenId.split("YSM")[2];

                    mViewModel.getWxInfo(token, openId)
                            .observe(SettingsActivity.this, mWxInfoObserver);
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
                FToast.error(error.getErrMsg() + "->" + error.getErrCode());
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
                WxInfo minfo = new Gson().fromJson(b, WxInfo.class);

                mViewModel.bindwx("2",""+mUserInfo.getId(),mUserInfo.getToken(),minfo.getUnionId(), minfo.getOpenId(), minfo.getHeadImgUrl(), minfo.getNickName(),""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mbindwxObserver);
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }
    private Observer<Status<ResponseBody>> mbindwxObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                operaBindWxInfo(body);
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };

    private void operaBindWxInfo(ResponseBody body) {
        try {
            String b = body.string();
            WxbindBean mModifyInfo = new Gson().fromJson(b, WxbindBean.class);
            if (mModifyInfo.getCode() == 1) {
                FToast.success(mModifyInfo.getInfo());
                bindStatusTv.setText("已绑定");
                SharedPrefUtils.saveWXBind(1);
            }else if(mModifyInfo.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mModifyInfo.getInfo());
            }else {
                FToast.error(mModifyInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }
    private Observer<Status<ResponseBody>> mjiebindwxObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                operajieBindWxInfo(body);
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }
    };

    private void operajieBindWxInfo(ResponseBody body) {
        try {
            String b = body.string();
            WxbindBean mModifyInfo = new Gson().fromJson(b, WxbindBean.class);
            if (mModifyInfo.getCode() == 1) {
                FToast.success(mModifyInfo.getInfo());
                bindStatusTv.setText("未绑定");

                SharedPrefUtils.saveWXBind(0);
            }else if(mModifyInfo.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mModifyInfo.getInfo());
            }else {
                FToast.error(mModifyInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(mReceiver);
    }


}

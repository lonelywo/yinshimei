package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;

import com.cuci.enticement.event.LoginOutEvent;
import com.cuci.enticement.plate.common.HuanBindActivity;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.lxj.xpopup.XPopup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

import butterknife.OnClick;



public class SettingsActivity extends BaseActivity {
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.bind_status_tv)
    TextView bindStatusTv;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        phoneTv.setText(mUserInfo.getPhone());
        String is_binding = mUserInfo.getIs_binding();
        if("1".equals(is_binding)){
            //todo   这里换成微信号显示   不要用昵称
         //   bindStatusTv.setText(mUserInfo.getNickname());
            bindStatusTv.setText("已绑定");
        }else {
            bindStatusTv.setText("未绑定");
        }

    }



    @OnClick({R.id.image_back, R.id.ll_info, R.id.ll_phone, R.id.ll_wechat,R.id.ll_about_us, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.ll_info:
                startActivity(new Intent(this,InfoActivity.class));
                break;
            case R.id.ll_phone:
                startActivity(new Intent(this, HuanBindActivity.class));
                break;
            case R.id.ll_wechat:

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
                                mViewModel.loginOut("2", token, String.valueOf(mid)).observe(this, mloginoutObserver);

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
        SharedPrefUtils.exit();
        EventBus.getDefault().postSticky(new LoginOutEvent());
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

}

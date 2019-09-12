package com.example.enticement.wxapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.enticement.BasicApp;
import com.example.enticement.plate.common.LoginActivity;
import com.example.enticement.utils.FToast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String TAG = WXEntryActivity.class.getSimpleName();

    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BasicApp.getIWXAPI().handleIntent(getIntent(), this);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        int type = baseResp.getType();

        switch (baseResp.errCode) {

            case BaseResp.ErrCode.ERR_OK:
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    Intent intent = new Intent(LoginActivity.ACTION_WX_LOGIN_SUCCEED);
                    intent.putExtra("code", code);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }
//                else if (type == RETURN_MSG_TYPE_SHARE) {
//                    FToast.success("分享成功");
//                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                FToast.warning("用户拒绝授权");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                FToast.warning("用户取消");
                break;
        }

        finish();
    }
}

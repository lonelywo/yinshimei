package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissiontxBean;
import com.cuci.enticement.bean.ModifyInfo;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class NickModifyActivity extends BaseActivity {

    @BindView(R.id.nick_et)
    ClearEditText nickEt;
    private CommonViewModel mViewModel;
    private UserInfo mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_nick_modify;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        String nickname = mUserInfo.getNickname();
        nickEt.setText(nickname);
        nickEt.setSelection(nickname.length());

        mViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);
    }


    @OnClick({R.id.image_back, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_commit:
               String nickStr = nickEt.getText().toString().trim();
                mViewModel.modifyInfo(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),mUserInfo.getOpenid(),mUserInfo.getHeadimg(),"",nickStr,mUserInfo.getSex(),mUserInfo.getUnionid()
                ,mUserInfo.getProvince(),mUserInfo.getCity(),mUserInfo.getArea())
                        .observe(this, mObserver);

                break;
        }


    }





    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                opera(body);
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

    private void opera(ResponseBody body) {
        try {
            String b = body.string();

           ModifyInfo modifyInfo = new Gson().fromJson(b, ModifyInfo.class);
            if (modifyInfo.getCode() == 1) {
                UserInfo userInfo = modifyInfo.getData();
                SharedPrefUtils.save(userInfo,UserInfo.class);
                Intent intent = new Intent(this, InfoActivity.class);
                intent.putExtra("nickname",userInfo.getNickname());
                setResult(101,intent);
                Intent intentRefresh = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                intentRefresh.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intentRefresh);
                finish();
                FToast.success(modifyInfo.getInfo());
            } else {
                FToast.error(modifyInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


}

package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.Observer;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissiontxBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_nick_modify;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        UserInfo userInfo = SharedPrefUtils.get(UserInfo.class);
        String nickname = userInfo.getNickname();
        nickEt.setText(nickname);
        nickEt.setSelection(nickname.length());
    }


    @OnClick({R.id.image_back, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_commit:
                String nickStr = nickEt.getText().toString().trim();


                break;
        }


    }





    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                FToast.error("网络错误");
                break;
            case Status.LOADING:
                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            //todo   昵称  保存本地
         /*   CommissiontxBean mCommissiontxBean = new Gson().fromJson(b, CommissiontxBean.class);
            if (mCommissiontxBean.getCode() == 1) {
                FToast.success(mCommissiontxBean.getInfo());
            } else {
                FToast.error(mCommissiontxBean.getInfo());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


}

package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.BindTuijianBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class BindTuiJinActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.text_tixian)
    TextView textTixian;
    @BindView(R.id.edt_phone_container)
    ConstraintLayout edtPhoneContainer;
    @BindView(R.id.ok)
    TextView ok;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private boolean isclick = true;


    @Override
    public int getLayoutId() {
        return R.layout.activity_bindtuijian;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        edtPhone.setClearIconVisible(false);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(BindTuiJinActivity.this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new TipsPopup(BindTuiJinActivity.this,
                                "亲，确定绑定此号码吗？", "取消", "确定", () -> {
                            load();
                        }))


                        .show();

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void load() {

        String phone = edtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            FToast.warning("请填写完整");
            return;
        }
        if (isclick) {
            isclick = false;
            mViewModel.bindTuijian(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", phone,""+ AppUtils.getVersionCode(BasicApp.getContext()))
                    .observe(this, mObserver);
        }


    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                isclick = true;
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                isclick = true;
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            BindTuijianBean mBindTuijianBean = new Gson().fromJson(b, BindTuijianBean.class);
            if (mBindTuijianBean.getCode() == 1) {
                EventBus.getDefault().post(new ProgoodsEvent());
                FToast.success(mBindTuijianBean.getInfo());
            }else if(mBindTuijianBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mBindTuijianBean.getInfo());
            } else {
                FToast.error(mBindTuijianBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

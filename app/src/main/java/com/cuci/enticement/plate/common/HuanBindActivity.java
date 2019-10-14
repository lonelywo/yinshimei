package com.cuci.enticement.plate.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Bag499Bean;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.GuoJiaBean;
import com.cuci.enticement.bean.ModifyInfo;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.vm.RegActivityViewModel;
import com.cuci.enticement.plate.mine.activity.SettingsActivity;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.ClearEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class HuanBindActivity extends BaseActivity {


    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.text_suosu)
    TextView textSuosu;
    @BindView(R.id.text_guojia)
    TextView textGuojia;
    @BindView(R.id.img_youjiantou)
    ImageView imgYoujiantou;
    @BindView(R.id.viem_line)
    View viemLine;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.viem_line1)
    View viemLine1;
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
    @BindView(R.id.con_zhongjian)
    ConstraintLayout conZhongjian;
    private Handler mTimeHandler = new Handler();
    private RegActivityViewModel mViewModel;
    private Integer guojiacode=86;
    private int mQrCodeChoice = 0;

    //private String[] mQrItems = new String[]{"中国", "马来西亚"};
    private UserInfo mUserInfo;
    private GuoJiaBean mGuoJiaBean;
    private String[] mQrItems ;
    private Integer[] mQrItems2 ;
    private List<Integer> list1 = new ArrayList<Integer>();
    private  List<String> list2 = new ArrayList<String>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_bianbind;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(RegActivityViewModel.class);
        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        mGuoJiaBean = SharedPrefUtils.get(GuoJiaBean.class);
        if(mGuoJiaBean.getData()!=null){
            for (int i = 0; i < mGuoJiaBean.getData().size() ; i++) {
                list1.add(mGuoJiaBean.getData().get(i).getCode());
                list2.add(mGuoJiaBean.getData().get(i).getTitle());
            }
            mQrItems = list2.toArray(new String[list2.size()]);
            mQrItems2 = list1.toArray(new Integer[list1.size()]);
        }
    }


    @OnClick({R.id.tv_code, R.id.ok, R.id.image_back, R.id.text_guojia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                sendSmsCode();
                break;
            case R.id.ok:

                register();
                break;
            case R.id.image_back:
                finish();

                break;
            case R.id.text_guojia:
                showQrCodeDialog();

                break;
        }
    }

    private void register() {

        String phone = edtPhone.getText().toString().trim();
        String smsCode = edtCode.getText().toString().trim();

        if ( TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(smsCode)) {
            FToast.warning("请填写完整");
            return;
        }

        mViewModel.huanBindPhone("2", ""+mUserInfo.getId(), mUserInfo.getToken(), phone, smsCode).observe(this, mObserver);

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
            ModifyInfo mModifyInfo = new Gson().fromJson(b, ModifyInfo.class);
            if (mModifyInfo.getCode() == 1) {
                UserInfo userInfo = mModifyInfo.getData();
                SharedPrefUtils.save(userInfo,UserInfo.class);
                Intent intent = new Intent();
                intent.putExtra(SettingsActivity.DATA_USER_INFO, userInfo);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else {
                FToast.error(mModifyInfo.getInfo());
            }

        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void sendSmsCode() {

        String phone = edtPhone.getText().toString().trim();
        String guojia = textGuojia.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            FToast.warning("请填写手机号");
            return;
        }

        mViewModel.getSmsCode(phone, "cuci", ""+guojiacode, "4").observe(this, mSmsCodeObserver);

    }

    private Observer<Status<Base>> mSmsCodeObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.SUCCESS:
                    HuanBindActivity.this.dismissLoading();
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    FLog.e("zhuceyanzhengma", baseStatus.content.toString());
                    if (baseStatus.content.code == 1) {
                        tvCode.setClickable(false);
                        tvCode.setTag(60);
                        tvCode.setBackground(ContextCompat.getDrawable(HuanBindActivity.this,
                                R.drawable.shape_login_get_code_gray));
                        FToast.success(baseStatus.content.info);
                        run.run();
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
                case Status.LOADING:
                    HuanBindActivity.this.showLoading();
                    break;
                case Status.ERROR:
                    HuanBindActivity.this.dismissLoading();
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
                tvCode.setBackground(ContextCompat.getDrawable(HuanBindActivity.this,
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

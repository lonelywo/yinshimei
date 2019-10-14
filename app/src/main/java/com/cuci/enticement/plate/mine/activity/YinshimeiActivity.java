package com.cuci.enticement.plate.mine.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.ModifyInfo;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.YsmBean;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi1;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.io.IOException;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class YinshimeiActivity extends BaseActivity {
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.text_banbenhao)
    TextView textBanbenhao;
    @BindView(R.id.text_xieyi)
    TextView textXieyi;
    @BindView(R.id.img_youjiantou)
    ImageView imgYoujiantou;
    @BindView(R.id.con_zhongjian)
    ConstraintLayout conZhongjian;
    @BindView(R.id.viem_line)
    View viemLine;
    @BindView(R.id.text_yingye)
    TextView textYingye;
    @BindView(R.id.img_youjiantou1)
    ImageView imgYoujiantou1;
    @BindView(R.id.con_zhongjian1)
    ConstraintLayout conZhongjian1;
    @BindView(R.id.text_1)
    TextView text1;
    @BindView(R.id.text_2)
    TextView text2;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;
    private String title;
    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yinshimei;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        String versionName = getVerName(this);
        textBanbenhao.setText("版本号v"+versionName);

        mViewModel.ysm("2", ""+mUserInfo.getId(), mUserInfo.getToken()).observe(this, mjiebindwxObserver);
        conZhongjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(url)){

                    new XPopup.Builder(YinshimeiActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopupxieyi1(YinshimeiActivity.this,
                                    url,title,  () -> {

                            }))
                            .show();

                }

            }
        });
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }


    @OnClick({R.id.image_back})
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
            YsmBean mModifyInfo = new Gson().fromJson(b, YsmBean.class);
            if (mModifyInfo.getCode() == 1) {
                 title = mModifyInfo.getData().get(0).getTitle();
                url = mModifyInfo.getData().get(0).getUrl();

            }else {
                FToast.error(mModifyInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            dismissLoading();
            FToast.error("数据错误");
        }
    }

}

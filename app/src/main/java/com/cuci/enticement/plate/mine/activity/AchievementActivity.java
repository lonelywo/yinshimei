package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.YeJiYueFanBean;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.google.gson.Gson;
import com.tencent.smtt.sdk.WebView;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class AchievementActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.web_guize)
    WebView webGuize;
    @BindView(R.id.text_weidabiao)
    TextView textWeidabiao;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.con_1)
    ConstraintLayout con1;
    @BindView(R.id.text_kelingqu)
    TextView textKelingqu;
    @BindView(R.id.img_weidabiao)
    ImageView imgWeidabiao;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.con_2)
    ConstraintLayout con2;
    @BindView(R.id.text_fafang)
    TextView textFafang;
    @BindView(R.id.img_weidabiao1)
    ImageView imgWeidabiao1;
    @BindView(R.id.con_3)
    ConstraintLayout con3;
    private MineViewModel mViewModel;
    private UserInfo mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_achievement;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel.achievement(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2")
                .observe(this, mObserver);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            YeJiYueFanBean mYeJiYueFanBean = new Gson().fromJson(b, YeJiYueFanBean.class);
            YeJiYueFanBean.DataBean item = mYeJiYueFanBean.getData();
            if (item == null ) {
                return;
            }
            if (mYeJiYueFanBean.getCode() == 1) {
                String htmlContent = mYeJiYueFanBean.getData().getExplain();
                webGuize.loadDataWithBaseURL(null,
                        getHtmlData(htmlContent), "text/html", "utf-8", null);
            } else {
                FToast.error(mYeJiYueFanBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

}

package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.HomeDetailsBean;
import com.cuci.enticement.bean.JiFenProBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.GlideImageLoader;
import com.cuci.enticement.plate.common.popup.WarningPopup;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.smtt.sdk.WebView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;


public class JiFenProdActivity extends BaseActivity {
    private static final String TAG = JiFenProdActivity.class.getSimpleName();
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_detail_goodsname)
    TextView homeDetailGoodsname;
    @BindView(R.id.text_jiage)
    TextView textJiage;
    @BindView(R.id.con_info)
    ConstraintLayout conInfo;
    @BindView(R.id.con_beijing)
    ConstraintLayout conBeijing;
    @BindView(R.id.view_xian2)
    View viewXian2;
    @BindView(R.id.con_xq_title)
    ConstraintLayout conXqTitle;
    @BindView(R.id.web_details)
    WebView webDetails;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.text_buy)
    TextView textBuy;
    @BindView(R.id.ll_dibu)
    LinearLayout llDibu;
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private String url;
    private JiFenProBean.DataBean mProData;
    private MineViewModel mMineViewModel;
    private int status;


    @Override
    public int getLayoutId() {
        return R.layout.activity_prod_jifen;
    }

    private UserInfo mUserInfo;

    @Override
    public void initViews(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        url = intent.getStringExtra("bannerData");


        if (url == null) {
            FToast.error("数据错误");
            return;
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());

        mMineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        if (mUserInfo != null) {
            mMineViewModel.getJiFenDetails("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), url, "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
        }

    }


    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }


    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.LOADING:
                break;
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String b = body.string();
                    JiFenProBean mJiFenProBean = new Gson().fromJson(b, JiFenProBean.class);
                    if (mJiFenProBean.getCode() == 1) {
                        mProData = mJiFenProBean.getData();
                        final List<String> images = mProData.getImage();
                        banner.setImages(images);
                        banner.start();
                        String htmlContent = mProData.getContent();
                        homeDetailGoodsname.setText(mProData.getTitle());
                        textJiage.setText(mProData.getPoints() + "积分");
                        webDetails.loadDataWithBaseURL(null,
                                getHtmlData(htmlContent), "text/html", "utf-8", null);
                    } else if (mJiFenProBean.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(JiFenProdActivity.this);
                        finish();
                        FToast.error(mJiFenProBean.getInfo());
                    } else {
                        FToast.error(mJiFenProBean.getInfo());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
        }


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_back,R.id.text_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.text_buy:
                if(mProData!=null){
                    Intent intentProd = new Intent(this, DuiHuanDingDanQueRenActivity.class);
                    intentProd.putExtra("intentInfo",mProData );
                    startActivity(intentProd);
                }

                break;
        }
    }
}

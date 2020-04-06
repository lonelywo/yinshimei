package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.utils.FToast;
import com.tencent.smtt.sdk.WebView;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;

public class TuiAgreementActivity extends BaseActivity {


    @BindView(R.id.web_context)
    WebView webContext;
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_agreement_tui;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        url = intent.getStringExtra("bannerData");
        webContext.loadUrl(url
        );
      /*  webContext.loadDataWithBaseURL(null,
                getHtmlData(url), "text/html", "utf-8", null);*/
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }



    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}

package com.cuci.enticement.plate.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.ShareBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.BitmapUitls;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.google.gson.Gson;
import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.smtt.sdk.WebView;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgreementActivity extends BaseActivity {


    @BindView(R.id.web_context)
    WebView webContext;
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.tv_share)
    ImageView tvShare;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String url;
    private String brief;
    private ShareBean mShareBean;
    private UserInfo mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        url = intent.getStringExtra("bannerData");
        brief = intent.getStringExtra("share_info");
        webContext.loadDataWithBaseURL(null,
                getHtmlData(url), "text/html", "utf-8", null);
        if(brief==null|| TextUtils.isEmpty(brief)){
            ViewUtils.hideView(tvShare);
            ViewUtils.hideView(imageTop);
            return;
        }
        try {
            mShareBean = new Gson().fromJson(brief, ShareBean.class);

        } catch (Exception e) {
            e.printStackTrace();

        }
        ViewUtils.showView(tvShare);
        ViewUtils.showView(imageTop);
        imageTop.setText(mShareBean.getTitle());
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public void ShareXiaoChengnXu() {
        BasicApp.getAppExecutors()
                .networkIO()
                .execute(() -> {
                    try {
                        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                        miniProgramObj.webpageUrl = "https://test.enticementchina.com/pages/goods/detail?g=" + mShareBean.getGoods_id(); // 兼容低版本的网页链接
                        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                        miniProgramObj.userName = "gh_26859964eed3";     // 小程序原始id
                        miniProgramObj.path = "pages/goods/detail?g=" + mShareBean.getGoods_id() + "&p=" + mUserInfo.getPhone();
                        //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                        // miniProgramObj.path = "";            //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                        msg.title = mShareBean.getTitle();                    // 小程序消息title
                        msg.description = "因诗美，因你而美";               // 小程序消息desc
                        Bitmap bmp = BitmapFactory.decodeStream(new URL(mShareBean.getLogo()).openStream());
                        Bitmap thumbBmp = BitmapUitls.drawWXMiniBitmap(bmp, 500, 400);
                        //  Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 500, 400, true);
                        bmp.recycle();
                        msg.thumbData = WxShareUtils.bmpToByteArray0(thumbBmp, true); // 小程序消息封面图片，小于128k
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = msg;
                        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
                        BasicApp.getIWXAPI().sendReq(req);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @OnClick({R.id.image_back,R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_share:
                if(AppUtils.isAllowPermission(this)){
                    ShareXiaoChengnXu();
                }
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

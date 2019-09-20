package com.cuci.enticement.plate.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;


import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;

import com.cuci.enticement.utils.FLog;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;


public class BrowserActivity extends BaseActivity {

    private static final String TAG = BrowserActivity.class.getSimpleName();
    public static final String ACTION_LOGIN_SUCCEED_REFRESH = "com.fqapps.fdsh.plate.common.ACTION_LOGIN_SUCCEED_REFRESH";
    public static final String DATA_USER_INFO = "data_user_info";
    public static final String URL = "url";
    public static final String SHOW_BAR = "show_bar";
    public static final String SHOW_SHARE = "show_share";

    public static final int TYPE_HIDE_SHARE = 0;
    public static final int TYPE_SHOW_SHARE = 1;

    public static final int TYPE_SHOW_TOP_BAR = 2;
    public static final int TYPE_HIDE_TOP_BAR = 3;


    @BindView(R.id.web_view)
    WebView mWebView;


    @BindView(R.id.web_progressBar)
    ProgressBar mProgressBar;

    private String mUrl;
    private int mShowTopBar;

    private int mShowShare;
    private int mVersionCode;
    private String mVersionName;

    private LocalBroadcastManager mLocalBroadcastManager;
    private WebSettings mWebSettings;

    private String mOldUserAgent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_browser;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initViews(Bundle savedInstanceState) {

        mWebSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webView必须设置支持Javascript
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        //设置自适应屏幕
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);


        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAppCacheEnabled(true);

        JavaScriptMetod m = new JavaScriptMetod(BrowserActivity.this, mWebView);

        mWebView.addJavascriptInterface(m, JavaScriptMetod.JAVAINTERFACE);
        mWebView.setWebViewClient(mWebViewClient);
      //  mWebView.loadUrl(mUrl);




    }




    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {


            return true;
        }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }
};
    public class JavaScriptMetod {

        public static final String JAVAINTERFACE = "javaInterface";
        private Context mContext;

        private WebView mWebView;

        public JavaScriptMetod(Context context, WebView webView) {

            mContext = context;

            mWebView = webView;

        }
        @android.webkit.JavascriptInterface
        public void functionClick(String code, String name, String string) {

            FLog.e(TAG, "\n网页传过来的数据:"
                    + "\ncode:" + code
                    + "\nname:" + name
                    + "\n内容：" + string);
        }

    }
}
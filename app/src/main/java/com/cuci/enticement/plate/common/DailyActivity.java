package com.cuci.enticement.plate.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.event.ReceiveEvent;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.mine.fragment._OrderFragment01;
import com.cuci.enticement.plate.mine.fragment._ShareliwuFragment01;
import com.cuci.enticement.utils.FToast;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyActivity extends BaseActivity {


    List<String> data = new ArrayList<String>();
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_daily;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        data.add("未领取");
        data.add("已领取");
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        String[] titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {

            titles[i] = data.get(i);
        }
        adapter.addFragment(_ShareliwuFragment01.newInstance("0"));
        adapter.addFragment(_ShareliwuFragment01.newInstance("1"));
        mViewPager.setOffscreenPageLimit(data.size() - 1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager, titles);
        mViewPager.setCurrentItem(0);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       /* url = intent.getStringExtra("url");
        token = intent.getStringExtra("token");
        webContext.loadUrl(url + "&token=" + token);

        WebSettings webSettings = webContext.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //网页在app内打开
        webContext.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webContext.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
                super.onProgressChanged(view, newProgress);
            }
        });*/

    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEventMessage(ReceiveEvent event) {
        if(event.getCode()==ReceiveEvent.CHECK_ITEM){
            mViewPager.setCurrentItem(1);
        }
    }

}

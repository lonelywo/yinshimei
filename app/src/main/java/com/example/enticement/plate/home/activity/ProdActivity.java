package com.example.enticement.plate.home.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseActivity;

import com.example.enticement.bean.Base;
import com.example.enticement.bean.HomeDetailsBean;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.plate.cart.activity.OrderActivity;
import com.example.enticement.plate.cart.vm.CartViewModel;
import com.example.enticement.plate.common.GlideImageLoader;

import com.example.enticement.plate.common.popup.ShareBottom2TopProdPopup;
import com.example.enticement.plate.home.vm.HomeViewModel;
import com.example.enticement.utils.AppUtils;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.SharedPrefUtils;
import com.example.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.smtt.sdk.WebView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProdActivity extends BaseActivity implements ShareBottom2TopProdPopup.OnCommitClickListener {
    private static final String TAG = ProdActivity.class.getSimpleName();
    public static final int PUT_IN_CART=100;
    public static final int QUICK_BUY=101;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_detail_goodsname)
    TextView homeDetailGoodsname;
    @BindView(R.id.text_jiage)
    TextView text_jiage;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.con_info)
    ConstraintLayout conInfo;
    @BindView(R.id.web_details)
    WebView webDetails;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.text_cart)
    TextView textCart;
    @BindView(R.id.text_sell)
    TextView textSell;

    private String url;
    private HomeDetailsBean.DataBean mProData;
    private HomeViewModel mHomeViewModel;
    private int mCode;
    @Override
    public int getLayoutId() {
        return R.layout.activity_prod;
    }
    private UserInfo mUserInfo;
    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }
        mUserInfo=SharedPrefUtils.get(UserInfo.class);
        url = intent.getStringExtra("bannerData");


        if (url == null) {
            FToast.error("数据错误");
            return;
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.getHomeDetails(url).observe(this, mObserver);


    }


    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }


    private Observer<Status<HomeDetailsBean>> mObserver = new Observer<Status<HomeDetailsBean>>() {

        @Override
        public void onChanged(Status<HomeDetailsBean> baseListStatus) {
            switch (baseListStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    HomeDetailsBean content = baseListStatus.content;
                    if (content == null) {

                        return;
                    }
                    if (content.getCode() == 1) {
                        mProData=content.getData();
                     //   String s = new Gson().toJson(mProData);
                        final List<String> images = content.getData().getImage();
                        banner.setImages(images);
                        banner.start();
                        String htmlContent = content.getData().getContent();
                        homeDetailGoodsname.setText(content.getData().getTitle());
                        text_jiage.setText(content.getData().getList().get(0).getPrice_selling());
                        webDetails.loadDataWithBaseURL(null,
                                getHtmlData(htmlContent), "text/html", "utf-8", null);

                    } else {
                        FToast.error(content.getInfo());
                    }
                    break;
                case Status.ERROR:

                    FToast.error(baseListStatus.message);
                    break;
            }

        }
    };




    @OnClick({R.id.image_back, R.id.text_cart, R.id.text_sell, R.id.iv_to_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;

            case R.id.text_cart:
                if(AppUtils.isAllowPermission(ProdActivity.this)){
                    new XPopup.Builder(ProdActivity.this)
                            .dismissOnTouchOutside(true)
                            .dismissOnBackPressed(true)
                            .asCustom(new ShareBottom2TopProdPopup(ProdActivity.this, mProData,PUT_IN_CART,this))
                            .show();
                }

                break;
            case R.id.text_sell:
                if(AppUtils.isAllowPermission(ProdActivity.this)) {
                    new XPopup.Builder(ProdActivity.this)
                            .dismissOnTouchOutside(true)
                            .dismissOnBackPressed(true)
                            .asCustom(new ShareBottom2TopProdPopup(ProdActivity.this, mProData, QUICK_BUY, this))
                            .show();
                }
                break;
            case R.id.iv_to_top:
              //  scrollDetails.smoothScrollTo(0,0);
             if(AppUtils.isAllowPermission(this)){
                 startActivity(new Intent(this, OrderActivity.class));
             }
                break;
        }
    }


    @Override
    public void onCommitClick(String spec, int num,int code) {
        mCode=code;
        mUserInfo=SharedPrefUtils.get(UserInfo.class);
        CartViewModel   mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);



         if(code==PUT_IN_CART){
             long id = mProData.getId();
             String s=spec;
             String numStr=String.valueOf(num);
             mViewModel.cartChange(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),String.valueOf(id),spec,String.valueOf(num)).observe(this,mIntoCart);


          }else if(code==QUICK_BUY){
             StringBuilder sb=new StringBuilder();
                 sb.append(mProData.getId()).append("@")
                         .append(spec).append("@")
                         .append(num);

             String rule = sb.toString();
             mViewModel.commitOrder(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),rule,"").observe(this,mCommitObserver);

          }

    }




    private Observer<Status<OrderResult>> mCommitObserver = new Observer<Status<OrderResult>>() {

        @Override
        public void onChanged(Status<OrderResult> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    OrderResult content = baseStatus.content;
                    if (content == null) {
                        FToast.error(content.getInfo());
                        return;
                    }
                    if (content.getCode() == 1) {
                       /* Intent intent = new Intent(ProdActivity.this, OrderActivity.class);
                        intent.putExtra("order",content.getData().getOrder());
                        startActivity(intent);*/

                    } else {
                        FToast.error(content.getInfo());
                    }
                    break;
                case Status.ERROR:

                    FToast.error(baseStatus.message);
                    break;
            }

        }
    };


    private Observer<Status<Base>> mIntoCart = new Observer<Status<Base>>() {

        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    String s = new Gson().toJson(baseStatus.content);
                 /*   String s2 = new Gson().toJson(baseStatus.content.data);
                    String s3 = new Gson().toJson(baseStatus.content.data);*/

                 /*   CartListBean content = baseStatus.content;
                    if (content == null) {
                        FToast.error(content.getInfo());
                        return;
                    }
                    if (content.getCode() == 1) {
                        finish();
                        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(ProdActivity.this);
                        broadcastManager.sendBroadcast(new Intent(ACTION_GO_TO_CART));
                        broadcastManager.sendBroadcast(new Intent(ACTION_REFRESH_DATA));

                    } else {
                        FToast.error(content.getInfo());
                    }*/
                    break;
                case Status.ERROR:

                    FToast.error(baseStatus.message);
                    break;
            }

        }
    };


}

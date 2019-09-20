package com.cuci.enticement.plate.home.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;

import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.CartNum;
import com.cuci.enticement.bean.HomeDetailsBean;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.GlideImageLoader;

import com.cuci.enticement.plate.common.popup.ShareBottom2TopProdPopup;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.smtt.sdk.WebView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static androidx.localbroadcastmanager.content.LocalBroadcastManager.*;
import static com.cuci.enticement.plate.cart.fragment._CartFragment.ACTION_REFRESH_DATA;
import static com.cuci.enticement.plate.common.MainActivity.ACTION_GO_TO_CART;

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

    @BindView(R.id.cart_num_tv)
    TextView cartNumTv;


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




    @OnClick({R.id.image_back, R.id.text_cart, R.id.text_sell, R.id.iv_cart})
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
            case R.id.iv_cart:
              //  点击购物车跳转到购物车页面
             if(AppUtils.isAllowPermission(this)){
                 finish();
                 LocalBroadcastManager broadcastManager = getInstance(ProdActivity.this);
                 broadcastManager.sendBroadcast(new Intent(ACTION_GO_TO_CART));

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




    private Observer<Status<ResponseBody>> mCommitObserver = new Observer<Status<ResponseBody>>() {

        @Override
        public void onChanged(Status<ResponseBody> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    ResponseBody content = baseStatus.content;

                    String result = null;
                    try {
                        result = content.string();
                        OrderResult orderResult = new Gson().fromJson(result, OrderResult.class);
                        if(orderResult.getCode()==1){

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
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
                    if(baseStatus.content.code==1){
                        FToast.success("加入购物车成功");
                        //调用接口改变小车上的数量
                        CartViewModel   viewModel = ViewModelProviders.of(ProdActivity.this).get(CartViewModel.class);
                        viewModel.cartNum(mUserInfo.getToken(),String.valueOf(mUserInfo.getId())).observe(ProdActivity.this,mNumObserver);

                        LocalBroadcastManager broadcastManager = getInstance(ProdActivity.this);
                        broadcastManager.sendBroadcast(new Intent(ACTION_REFRESH_DATA));


                    }else {
                        FToast.warning(baseStatus.content.info);
                    }

                    break;
                case Status.ERROR:

                    FToast.error(baseStatus.message);
                    break;
            }

        }
    };


    private Observer<Status<ResponseBody>> mNumObserver = new Observer<Status<ResponseBody>>() {

        @Override
        public void onChanged(Status<ResponseBody> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    ResponseBody content = baseStatus.content;

                    String result = null;
                    try {
                        result = content.string();

                        CartNum numResult = new Gson().fromJson(result, CartNum.class);
                        if(numResult.getCode()==1){
                            cartNumTv.setText(String.valueOf(numResult.getData().getC_num()));
                        }else {
                            FToast.warning(numResult.getInfo());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case Status.ERROR:

                    FToast.error(baseStatus.message);
                    break;
            }

        }
    };


}

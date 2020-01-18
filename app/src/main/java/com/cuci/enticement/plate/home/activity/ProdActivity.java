package com.cuci.enticement.plate.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.CartChange;
import com.cuci.enticement.bean.CartNum;
import com.cuci.enticement.bean.DataUserInfo;
import com.cuci.enticement.bean.HomeDetailsBean;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.ShareimgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.GlideImageLoader;
import com.cuci.enticement.plate.common.eventbus.CartEvent;
import com.cuci.enticement.plate.common.popup.CenterShareAppPopup2;
import com.cuci.enticement.plate.common.popup.ShareBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.ShareImgTipsPopup;
import com.cuci.enticement.plate.common.popup.SharegoodsImgTipsPopup;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.BitmapUitls;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.smtt.sdk.WebView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance;
import static com.cuci.enticement.plate.common.MainActivity.ACTION_GO_TO_CART;


public class ProdActivity extends BaseActivity implements ShareBottom2TopProdPopup.OnCommitClickListener {
    private static final String TAG = ProdActivity.class.getSimpleName();
    public static final int PUT_IN_CART = 100;
    public static final int QUICK_BUY = 101;
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
    @BindView(R.id.text_buy)
    TextView textBuy;

    @BindView(R.id.cart_num_tv)
    TextView cartNumTv;
    @BindView(R.id.text_share)
    TextView textShare;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.iv_cart)
    ImageView ivCart;

    private static final int THUMB_SIZE = 500;
    private static final int THUMB_SIZE1 = 400;
    @BindView(R.id.ll_dibu)
    LinearLayout llDibu;
    @BindView(R.id.text_xiajia)
    TextView textXiajia;
    @BindView(R.id.con_xiajiabuju)
    ConstraintLayout conXiajiabuju;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String url;
    private HomeDetailsBean.DataBean mProData;
    private HomeViewModel mHomeViewModel;
    private int mCode;
    private long id;
    private int status;
    private int type;
    private String rule;


    @Override
    public int getLayoutId() {
        return R.layout.activity_prod;
    }

    private UserInfo mUserInfo;

    @Override
    public void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        if (mUserInfo != null) {
            mHomeViewModel.getHomeDetails("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), url).observe(this, mObserver);
        } else {
            mHomeViewModel.getHomeDetails("2", "", "", url).observe(this, mObserver);
        }

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(ProdActivity.this)) {
                    mUserInfo = SharedPrefUtils.get(UserInfo.class);
                    if (mProData != null) {
                        type = 1;
                        mHomeViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(ProdActivity.this, mdataObserver);
                        ViewUtils.showView(progressBar);
                        /* new XPopup.Builder(ProdActivity.this)
                                .dismissOnTouchOutside(false)
                                .dismissOnBackPressed(false)
                                .asCustom(new CenterShareAppPopup(ProdActivity.this, mProData))
                                .show();*/
                    }
                   /* if (mProData != null) {
                            new XPopup.Builder(ProdActivity.this)
                                    .dismissOnBackPressed(false)
                                    .dismissOnTouchOutside(false)
                                    .asCustom(new SharegoodsImgTipsPopup(ProdActivity.this, "取消", new SharegoodsImgTipsPopup.OnExitListener() {
                                        @Override
                                        public void onPositive1() {
                                            mHomeViewModel.shareimg("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), String.valueOf(mProData.getId())).observe(ProdActivity.this, mObservershare);
                                            ViewUtils.showView(progressBar);

                                        }

                                        @Override
                                        public void onPositive2() {
                                            ShareXiaoChengnXu();

                                        }

                                        @Override
                                        public void onCancel() {

                                        }
                                    }))
                                    .show();
                        }*/

                } else {
                    finish();
                }
            }
        });


        //进入页面先请求小脚本数字
        CartViewModel viewModel = ViewModelProviders.of(ProdActivity.this).get(CartViewModel.class);
        if (mUserInfo != null) {
            viewModel.cartNum(mUserInfo.getToken(), String.valueOf(mUserInfo.getId())).observe(ProdActivity.this, mNumObserver);
        }


    }

    private Observer<Status<ResponseBody>> mdataObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ViewUtils.hideView(progressBar);
                ResponseBody body = status.content;
                if (type == 1) {
                    opera1(body);
                } else if (type == 2) {
                    opera2(body);
                } else if (type == 3) {
                    opera3(body);
                }

                break;
            case Status.ERROR:
                ViewUtils.hideView(progressBar);
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera1(ResponseBody body) {
        try {
            String b = body.string();
            DataUserInfo mMyTeamslBean = new Gson().fromJson(b, DataUserInfo.class);
            if (mMyTeamslBean.getCode() == 1) {
                int is_new = mMyTeamslBean.getData().getIs_new();
                SharedPrefUtils.saveisnew(is_new);
                if(is_new==1){
                    WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_SESSION,
                            "http://web.enticementchina.com/present.html?mid="
                                    + mUserInfo.getId() + "&goods_id=" + mProData.getId()+"&phone=" + mUserInfo.getPhone()
                            , ProdActivity.this.getString(R.string.app_name_test),
                            "因诗美，我的质感美学", mProData.getLogo());
                }else {
                    FToast.warning("购买任意商品成为会员即可分享");
                }
            } else {
                FToast.error(mMyTeamslBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }

    }


    private void opera2(ResponseBody body) {
        try {
            String b = body.string();
            DataUserInfo mMyTeamslBean = new Gson().fromJson(b, DataUserInfo.class);
            if (mMyTeamslBean.getCode() == 1) {
                int is_new = mMyTeamslBean.getData().getIs_new();
                SharedPrefUtils.saveisnew(is_new);
                List<OrderGoods> items = new ArrayList<>();
                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setGoods_logo(mProData.getLogo());
                orderGoods.setGoods_title(mProData.getTitle());
                orderGoods.setGoods_num(mNum);
                orderGoods.setGoods_spec(mSpec);
                orderGoods.setGoods_price_selling(mprice_sell);
                orderGoods.setGoods_price_market(mprice_market);
                orderGoods.setVip_mod(mProData.getVip_mod());
                items.add(orderGoods);

                AllOrderList.DataBean.ListBeanX cartIntentInfo = new AllOrderList.DataBean.ListBeanX();

                //cartIntentInfo.setOrder_no(Long.parseLong(orderResult.getData().getOrder().getOrder_no()));
                cartIntentInfo.setList(items);
                cartIntentInfo.setGoods_count(items.size());
                if (is_new == 0 && mProData.getVip_mod() == 0 || is_new == 1 && mProData.getVip_mod() == 1) {
                    String goodsPrice = MathExtend.multiply(mprice_market, String.valueOf(mNum));
                    cartIntentInfo.setPrice_goods(String.valueOf(goodsPrice));
                } else {
                    String goodsPrice = MathExtend.multiply(mprice_sell, String.valueOf(mNum));
                    cartIntentInfo.setPrice_goods(String.valueOf(goodsPrice));
                }
                Intent intent = new Intent(ProdActivity.this, OrderActivity.class);
                intent.putExtra("intentInfo", cartIntentInfo);
                intent.putExtra("vip", is_new);
                intent.putExtra("num", mNum);
                intent.putExtra("rule", rule);
                startActivity(intent);
            } else {
                FToast.error(mMyTeamslBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void opera3(ResponseBody body) {

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
                        mProData = content.getData();
                        status = mProData.getStatus();
                        if (status == 1) {
                            ViewUtils.hideView(conXiajiabuju);
                        } else {
                            ViewUtils.showView(conXiajiabuju);
                        }

                        final List<String> images = content.getData().getImage();
                        banner.setImages(images);
                        banner.start();
                        String htmlContent = content.getData().getContent();
                        homeDetailGoodsname.setText(content.getData().getTitle());

                        if (mProData.getVip_mod() == 1) {
                            String strMsg = "<font color=\"#BF9964\">" + mProData.getPricename() + "¥" + mProData.getInitial_price_selling() + "</font>";
                            text_jiage.setText(Html.fromHtml(strMsg));
                        } else {
                            String strMsg = "原价¥" + mProData.getInitial_price_market() + " " + "<font color=\"#BF9964\">" + "会员价¥" + mProData.getInitial_price_selling() + "</font>";
                            text_jiage.setText(Html.fromHtml(strMsg));
                        }

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


    @OnClick({R.id.image_back, R.id.text_cart, R.id.text_buy, R.id.iv_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;

            case R.id.text_cart:
                if (AppUtils.isAllowPermission(ProdActivity.this)) {
                    if (status == 1) {
                        if (SharedPrefUtils.getisnew() == 1 && mProData.getVip_mod() == 1) {
                            FToast.warning("该活动仅限新美粉");
                        } else {
                            if (mProData.getNumber_stock() == 0) {
                                FToast.warning("库存没啦~");
                                return;
                            }
                            new XPopup.Builder(ProdActivity.this)
                                    .dismissOnTouchOutside(true)
                                    .dismissOnBackPressed(true)
                                    .asCustom(new ShareBottom2TopProdPopup(ProdActivity.this, mProData, PUT_IN_CART, this))
                                    .show();
                        }
                    } else {
                        FToast.warning("商品已经下架啦~");

                    }

                } else {
                    finish();
                }

                break;
            case R.id.text_buy:
                if (AppUtils.isAllowPermission(ProdActivity.this)) {
                    if (status == 1) {
                        if (SharedPrefUtils.getisnew() == 1 && mProData.getVip_mod() == 1) {
                            FToast.warning("该活动仅限新美粉");
                        } else {
                            if (mProData.getNumber_stock() == 0) {
                                FToast.warning("库存没啦~");
                                return;
                            }
                            new XPopup.Builder(ProdActivity.this)
                                    .dismissOnTouchOutside(true)
                                    .dismissOnBackPressed(true)
                                    .asCustom(new ShareBottom2TopProdPopup(ProdActivity.this, mProData, QUICK_BUY, this))
                                    .show();
                        }
                    } else {

                        FToast.warning("商品已经下架啦~");
                    }

                } else {
                    finish();
                }
                break;
            case R.id.iv_cart:
                //  点击购物车跳转到购物车页面
                if (AppUtils.isAllowPermission(this)) {
                    finish();
                    LocalBroadcastManager broadcastManager = getInstance(ProdActivity.this);
                    broadcastManager.sendBroadcast(new Intent(ACTION_GO_TO_CART));

                } else {
                    finish();
                }
                break;

        }
    }


    private String mTotalMoeny;
    private int mNum;
    private String mSpec;
    private String mprice_sell;
    private String mprice_market;

    @Override
    public void onCommitClick(String spec, int num, int code, String price_sell, String price_market) {
        mCode = code;
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        CartViewModel mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mprice_sell = price_sell;
        mprice_market = price_market;
        if (code == PUT_IN_CART) {
            long id = mProData.getId();
            String s = spec;
            String numStr = String.valueOf(num);
            mViewModel.cartChange(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(id), spec, String.valueOf(num)).observe(this, mIntoCart);


        } else if (code == QUICK_BUY) {
            mNum = num;
            mSpec = spec;
            StringBuilder sb = new StringBuilder();
            sb.append(mProData.getId()).append("@")
                    .append(spec).append("@")
                    .append(num);
            String price = mProData.getInitial_price_selling();
            mTotalMoeny = MathExtend.multiply(price, String.valueOf(num));

            rule = sb.toString();
            // mViewModel.commitOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), rule, "").observe(this, mCommitObserver);

            //进入页面先请求是否会员
            if (mUserInfo != null) {
                type = 2;
                mHomeViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(this, mdataObserver);
            }
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

                    try {
                        String result = content.string();
                        OrderResult orderResult = new Gson().fromJson(result, OrderResult.class);
                        if (orderResult.getCode() == 1) {
                            //跳转

                            List<OrderGoods> items = new ArrayList<>();
                            OrderGoods orderGoods = new OrderGoods();
                            orderGoods.setGoods_logo(mProData.getLogo());
                            orderGoods.setGoods_title(mProData.getTitle());
                            orderGoods.setGoods_num(mNum);
                            orderGoods.setGoods_spec(mSpec);
                            orderGoods.setGoods_price_selling(mProData.getInitial_price_selling());
                            items.add(orderGoods);

                            AllOrderList.DataBean.ListBeanX cartIntentInfo = new AllOrderList.DataBean.ListBeanX();

                            cartIntentInfo.setOrder_no(Long.parseLong(orderResult.getData().getOrder().getOrder_no()));
                            cartIntentInfo.setList(items);
                            cartIntentInfo.setGoods_count(items.size());
                            String goodsPrice = MathExtend.multiply(mProData.getInitial_price_selling(), String.valueOf(mNum));
                            cartIntentInfo.setPrice_goods(String.valueOf(goodsPrice));
                            Intent intent = new Intent(ProdActivity.this, OrderActivity.class);
                            intent.putExtra("intentInfo", cartIntentInfo);
                            startActivity(intent);


                        } else {
                            FToast.error(orderResult.getInfo());
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


    private Observer<Status<ResponseBody>> mIntoCart = new Observer<Status<ResponseBody>>() {

        @Override
        public void onChanged(Status<ResponseBody> status) {
            switch (status.status) {
                case Status.LOADING:
                    break;
                case Status.SUCCESS:
                    ResponseBody content = status.content;
                    try {
                        String result = content.string();
                        CartChange bean = new Gson().fromJson(result, CartChange.class);

                        if (bean.getCode() == 1) {
                            FToast.success(bean.getInfo());
                            //调用接口改变小车上的数量
                            CartViewModel viewModel = ViewModelProviders.of(ProdActivity.this).get(CartViewModel.class);
                            viewModel.cartNum(mUserInfo.getToken(), String.valueOf(mUserInfo.getId())).observe(ProdActivity.this, mNumObserver);
                            //刷新购物车列表
                            EventBus.getDefault().post(new CartEvent(CartEvent.REFRESH_CART_LIST));
                           /* LocalBroadcastManager broadcastManager = getInstance(ProdActivity.this);
                            broadcastManager.sendBroadcast(new Intent(ACTION_REFRESH_DATA));*/


                        } else {
                            FToast.error(bean.getInfo());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                case Status.ERROR:

                    FToast.error(status.message);
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
                        if (numResult.getCode() == 1) {
                            int c_num = numResult.getData().getC_num();
                            if (c_num == 0) {
                                ViewUtils.hideView(cartNumTv);
                            } else {
                                ViewUtils.showView(cartNumTv);
                                cartNumTv.setText(String.valueOf(numResult.getData().getC_num()));
                            }

                        } else {
                            FToast.error(numResult.getInfo());
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

    //刷新isnew显示数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickProgoodsEvent(ProgoodsEvent event) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo != null) {
            mHomeViewModel.getHomeDetails("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), url).observe(this, mObserver);
        } else {
            mHomeViewModel.getHomeDetails("2", "", "", url).observe(this, mObserver);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void ShareXiaoChengnXu() {
        BasicApp.getAppExecutors()
                .networkIO()
                .execute(() -> {
                    try {
                        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                        miniProgramObj.webpageUrl = "https://test.enticementchina.com/pages/goods/detail?g=" + mProData.getId(); // 兼容低版本的网页链接
                        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                        miniProgramObj.userName = "gh_26859964eed3";     // 小程序原始id
                        miniProgramObj.path = "pages/goods/detail?g=" + mProData.getId() + "&p=" + mUserInfo.getPhone();            //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                        // miniProgramObj.path = "";            //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                        msg.title = mProData.getTitle();                    // 小程序消息title
                        msg.description = "因诗美，因你而美";               // 小程序消息desc
                        Bitmap bmp = BitmapFactory.decodeStream(new URL(mProData.getLogo()).openStream());
                        //Bitmap thumbBmp = BitmapUitls.drawWXMiniBitmap(bmp, 500, 500);
                        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 500 , 500, true);
                        bmp.recycle();
                        msg.thumbData = WxShareUtils.bmpToByteArray(thumbBmp, true); // 小程序消息封面图片，小于128k
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

    private Observer<Status<ResponseBody>> mObservershare = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ViewUtils.hideView(progressBar);
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:
                ViewUtils.hideView(progressBar);
                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            ShareimgBean mMyTeamslBean = new Gson().fromJson(b, ShareimgBean.class);
            if (mMyTeamslBean.getCode() == 1) {
                String poster = mMyTeamslBean.getData().getPoster();
                String qrcode = mMyTeamslBean.getData().getQrcode();
                new XPopup.Builder(ProdActivity.this)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(false)
                        .asCustom(new CenterShareAppPopup2(ProdActivity.this, mUserInfo, poster, qrcode))
                        .show();
            } else {
                FToast.error(mMyTeamslBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.example.enticement.plate.home.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.cuci.enticement.R;
import com.example.enticement.BasicApp;
import com.example.enticement.base.BaseActivity;
import com.example.enticement.bean.BannerDataBean;
import com.example.enticement.bean.BaseList;
import com.example.enticement.bean.HomeDetailsBean;
import com.example.enticement.bean.ItemBanner;
import com.example.enticement.bean.Status;
import com.example.enticement.plate.common.GlideImageLoader;
import com.example.enticement.plate.home.fragment._HomeFragment;
import com.example.enticement.plate.home.vm.HomeViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.ImageLoader;
import com.tencent.smtt.sdk.WebView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class ProdActivity extends BaseActivity {
    private static final String TAG = ProdActivity.class.getSimpleName();
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_detail_goodsname)
    TextView homeDetailGoodsname;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.con_info)
    ConstraintLayout conInfo;
    @BindView(R.id.web_details)
    WebView webDetails;
    @BindView(R.id.scroll_details)
    ScrollView scrollDetails;
    @BindView(R.id.text_cart)
    TextView textCart;
    @BindView(R.id.text_sell)
    TextView textSell;
    @BindView(R.id.text_cart_sell)
    ImageView textCartSell;
    private BannerDataBean mData;
    private HomeViewModel mHomeViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_prod;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            FToast.error("数据错误");
            return;
        }

        mData = intent.getParcelableExtra("bannerData");
        final String url = mData.getUrl();

        if (mData == null) {
            FToast.error("数据错误");
            return;
        }


        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.getHomeDetails(mData.getUrl()).observe(this, mObserver);


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
                        final List<String> images = content.getData().getImage();
                        banner.setImages(images);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        banner.setImageLoader(new GlideImageLoader());

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





}

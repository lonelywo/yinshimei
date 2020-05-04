package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.CommissionmxBean;
import com.cuci.enticement.bean.QianDaoBean;
import com.cuci.enticement.bean.QianDaoShareImgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.CashEvent;
import com.cuci.enticement.event.DownShareImgEvent;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.common.popup.BottomShareAppPopup2;
import com.cuci.enticement.plate.common.popup.QianDaoHouPopup;
import com.cuci.enticement.plate.mine.adapter.ItemCommissionMXViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemQianDaoShareViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.TimeUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.QrCodeProdView;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class QianDaoActivity extends BaseActivity implements ItemQianDaoShareViewBinder.OnProdClickListener{
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_share)
    ImageView tvshare;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_wenzi)
    TextView tvMoneyWenzi;
    @BindView(R.id.tv_qiandao_guize)
    TextView tvQiandaoGuize;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.tv_jifen_biaoti)
    TextView tvJifenBiaoti;
    @BindView(R.id.line1)
    Guideline line1;
    @BindView(R.id.line2)
    Guideline line2;
    @BindView(R.id.line3)
    Guideline line3;
    @BindView(R.id.line4)
    Guideline line4;
    @BindView(R.id.tv_time_1)
    TextView tvTime1;
    @BindView(R.id.tv_jifen_2)
    TextView tvJifen2;
    @BindView(R.id.tv_time_2)
    TextView tvTime2;
    @BindView(R.id.tv_jifen_3)
    TextView tvJifen3;
    @BindView(R.id.tv_time_3)
    TextView tvTime3;
    @BindView(R.id.tv_jifen_4)
    TextView tvJifen4;
    @BindView(R.id.tv_time_4)
    TextView tvTime4;
    @BindView(R.id.tv_jifen_5)
    TextView tvJifen5;
    @BindView(R.id.tv_time_5)
    TextView tvTime5;
    @BindView(R.id.tv_jifen_6)
    TextView tvJifen6;
    @BindView(R.id.tv_time_6)
    TextView tvTime6;
    @BindView(R.id.tv_jifen_7)
    TextView tvJifen7;
    @BindView(R.id.tv_time_7)
    TextView tvTime7;
    @BindView(R.id.con_kapai)
    ConstraintLayout conKapai;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_biaoti2)
    TextView tvBiaoti2;
    @BindView(R.id.tv_shangcheng)
    TextView tvShangcheng;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.tv_user_integral)
    TextView tvUserIntegral;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private String poster;
    private String qrcode;
    private QrCodeProdView mQrCodeProdView;
    private ItemQianDaoShareViewBinder itemQianDaoShareViewBinder;
    private QianDaoBean.DataBean.ShareInfoBean share_info;
    @Override
    public int getLayoutId() {
        return R.layout.activity_qiandao;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mQrCodeProdView = new QrCodeProdView(this);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
       /* new XPopup.Builder(this)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(new QianDaoHouPopup(this, "哈喽",
                        () -> {

                        }))
                .show();*/
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        itemQianDaoShareViewBinder = new ItemQianDaoShareViewBinder(this);
        mAdapter.register(QianDaoBean.DataBean.SigninTaskBean.class, itemQianDaoShareViewBinder);
      /*  CartItemDecoration mDecoration = new CartItemDecoration(this, 4);
        recyclerView.addItemDecoration(mDecoration);*/
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        long ltime = System.currentTimeMillis();
        long ltime3 = ltime + 86400000 * 2;
        long ltime4 = ltime + 86400000 * 3;
        long ltime5 = ltime + 86400000 * 4;
        long ltime6 = ltime + 86400000 * 5;
        long ltime7 = ltime + 86400000 * 6;
        String timeWithSplit3 = TimeUtils.getTimeWithSplit(ltime3);
        String timeWithSplit4 = TimeUtils.getTimeWithSplit(ltime4);
        String timeWithSplit5 = TimeUtils.getTimeWithSplit(ltime5);
        String timeWithSplit6 = TimeUtils.getTimeWithSplit(ltime6);
        String timeWithSplit7 = TimeUtils.getTimeWithSplit(ltime7);
        tvTime3.setText(timeWithSplit3);
        tvTime4.setText(timeWithSplit4);
        tvTime5.setText(timeWithSplit5);
        tvTime6.setText(timeWithSplit6);
        tvTime7.setText(timeWithSplit7);
        load();


    }

    private void load() {
        showLoading();
        mViewModel.qiandao("2", mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(this))
                .observe(this, mObserver);
    }



    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.LOADING:
                break;
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                try {
                    String b = body.string();
                    QianDaoBean mQianDaoBean = new Gson().fromJson(b, QianDaoBean.class);
                    if (mQianDaoBean.getCode() == 1) {
                        setBottomAlignment(mQianDaoBean.getData().getEven_day() + "天");
                        tvUserIntegral.setText(mQianDaoBean.getData().getUser_integral()+"分");
                        tvJifen2.setText("+"+mQianDaoBean.getData().getIntegral_next().get(1));
                        tvJifen3.setText("+"+mQianDaoBean.getData().getIntegral_next().get(2));
                        tvJifen4.setText("+"+mQianDaoBean.getData().getIntegral_next().get(3));
                        tvJifen5.setText("+"+mQianDaoBean.getData().getIntegral_next().get(4));
                        tvJifen6.setText("+"+mQianDaoBean.getData().getIntegral_next().get(5));
                        tvJifen7.setText("+"+mQianDaoBean.getData().getIntegral_next().get(6));
                        List<QianDaoBean.DataBean.SigninTaskBean> signin_task = mQianDaoBean.getData().getSignin_task();
                        mItems.clear();
                        mItems.addAll(signin_task);
                        mAdapter.notifyDataSetChanged();
                        int today_integral = mQianDaoBean.getData().getToday_integral();
                        int is_signin = mQianDaoBean.getData().getIs_signin();
                        share_info = mQianDaoBean.getData().getShare_info();
                        mQrCodeProdView.setDesc(share_info.getNickname());
                        mQrCodeProdView.setImageMain(share_info.getPoster());
                        mQrCodeProdView.setImageQrCode(share_info.getQrcode());
                        if(is_signin==0){
                            new XPopup.Builder(this)
                                    .dismissOnTouchOutside(false)
                                    .dismissOnBackPressed(false)
                                    .asCustom(new QianDaoHouPopup(this, "积分+"+today_integral,share_info,
                                            () -> {

                                            }))
                                    .show();
                        }
                        //刷新签到
                        EventBus.getDefault().post(new IsnewEvent());
                    } else if (mQianDaoBean.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(mQianDaoBean.getInfo());
                    } else {
                        FToast.error(mQianDaoBean.getInfo());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error("网络错误");
                break;
        }


    };

    private void setBottomAlignment(String item) {
        SpannableStringBuilder spanString = new SpannableStringBuilder(item);
        //绝对尺寸
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(160);
        spanString.setSpan(absoluteSizeSpan, 0, spanString.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
       /* // 字体颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GRAY);
        spanString.setSpan(colorSpan, String.valueOf(item.getValue()).length(), spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // 字体加粗
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        spanString.setSpan(styleSpan, 0, String.valueOf(item.getValue()).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        tvMoney.setText(spanString);
    }



    @OnClick({R.id.img_back, R.id.tv_share, R.id.tv_qiandao_guize,R.id.tv_shangcheng,R.id.tv_user_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_share:
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(true)
                        .asCustom(new BottomShareAppPopup2(this, mUserInfo, share_info))
                        .show();
                break;
            case R.id.tv_qiandao_guize:
                Intent intentProd = new Intent(this, TuiAgreementActivity.class);
                intentProd.putExtra("title", "签到规则");
                intentProd.putExtra("bannerData", "https://web.enticementchina.com/appweb/signin_rule.html");
                startActivity(intentProd);
                break;
            case R.id.tv_shangcheng:
                startActivity(new Intent(this, DuiHuanMallActivity.class));
                break;
            case R.id.tv_user_integral:
                startActivity(new Intent(this, JiFenActivity.class));
                break;
        }
    }

    @Override
    public void onProdClick(QianDaoBean.DataBean.SigninTaskBean item) {


        mViewModel.sharehaibao("2", mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(this))
                .observe(this, mshareObserver);
    }

    private Observer<Status<ResponseBody>> mshareObserver = status -> {
        switch (status.status) {
            case Status.LOADING:
                break;
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String b = body.string();
                    QianDaoShareImgBean mQianDaoBean = new Gson().fromJson(b, QianDaoShareImgBean.class);
                    if (mQianDaoBean.getCode() == 1) {
                  //     FToast.success(mQianDaoBean.getInfo());
                        Bitmap bitmap = ImageUtils.getViewBitmap(mQrCodeProdView,750,1334);
                        if (bitmap == null) {
                            FToast.error("数据错误");
                            return;
                        }
                        WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
                    } else if (mQianDaoBean.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(mQianDaoBean.getInfo());
                    } else {
                   //     FToast.error(mQianDaoBean.getInfo());
                        Bitmap bitmap = ImageUtils.getViewBitmap(mQrCodeProdView,750,1334);
                        if (bitmap == null) {
                            FToast.error("数据错误");
                            return;
                        }
                        WxShareUtils.shareImageToWX(WxShareUtils.WX_SCENE_TIME_LINE, bitmap);
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
    public void onProdClick2(QianDaoBean.DataBean.SigninTaskBean item) {
        startActivity(new Intent(this, MainActivity.class));
        Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_HOME);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intentRank);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDownShareImgEventMessage(DownShareImgEvent event) {


    }
}

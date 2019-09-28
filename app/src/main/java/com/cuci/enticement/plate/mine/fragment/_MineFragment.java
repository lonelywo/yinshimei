package com.cuci.enticement.plate.mine.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.OrderStatistics;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.LoginOutEvent;
import com.cuci.enticement.event.LoginSucceedEvent;
import com.cuci.enticement.plate.common.LoginActivity;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.activity.AchievementActivity;
import com.cuci.enticement.plate.mine.activity.CommissionActivity;
import com.cuci.enticement.plate.mine.activity.KeFuActivity;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.RecAddressActivity;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.util.Date;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance;
import static com.cuci.enticement.plate.common.MainActivity.ACTION_GO_TO_HOME;


/**
 * 首页外层Fragment
 */

public class _MineFragment extends BaseFragment {

    private static final String TAG = _MineFragment.class.getSimpleName();

    @BindView(R.id.img_kaiguan)
    ImageView imgKaiguan;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.img_shape_huang)
    ImageView imgShapeHuang;
    @BindView(R.id.img_shape_bai)
    ImageView imgShapeBai;
    @BindView(R.id.img_tuxiang)
    CircleImageView imgTuxiang;
    @BindView(R.id.img_jingxiao)
    ImageView imgJingxiao;
    @BindView(R.id.btn_shengji)
    Button btnShengji;
    @BindView(R.id.img_shape_bai2)
    ImageView imgShapeBai2;
    @BindView(R.id.text_dingdan)
    TextView textDingdan;
    @BindView(R.id.text_quanbudingdan)
    TextView textQuanbudingdan;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.text_daifukuan)
    TextView textDaifukuan;
    @BindView(R.id.text_daifahuo)
    TextView textDaifahuo;
    @BindView(R.id.text_daishouhuo)
    TextView textDaishouhuo;
    @BindView(R.id.text_yiwancheng)
    TextView textYiwancheng;
    @BindView(R.id.img_shape_bai3)
    ImageView imgShapeBai3;
    @BindView(R.id.text_fuwu)
    TextView textFuwu;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.text_tuiguangyongjing)
    TextView textTuiguangyongjing;
    @BindView(R.id.text_wodetuandui)
    TextView textWodetuandui;
    @BindView(R.id.text_shouhuodizi)
    TextView textShouhuodizi;
    @BindView(R.id.text_yejiyuefan)
    TextView textYejiyuefan;
    @BindView(R.id.ll_fuwu)
    LinearLayout llFuwu;
    @BindView(R.id.text_wodekefu)
    TextView textWodekefu;
    @BindView(R.id.ll_fuwu2)
    LinearLayout llFuwu2;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    public static final String DATA_USER_INFO = "data_user_info_";
    public static final String ACTION_LOGIN_SUCCEED = "com.example.enticement.plate.mine.fragment.ACTION_LOGIN_SUCCEED";
    public static final String ACTION_REFRESH_STATUS = "com.example.enticement.plate.mine.fragment.ACTION_REFRESH_STATUS";
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.v6)
    View v6;
    @BindView(R.id.dot1_tv)
    TextView dot1Tv;
    @BindView(R.id.dot2_tv)
    TextView dot2Tv;
    @BindView(R.id.dot3_tv)
    TextView dot3Tv;
    @BindView(R.id.dot4_tv)
    TextView dot4Tv;
    @BindView(R.id.img_yqhy)
    ImageView imgYqhy;
    @BindView(R.id.daifukuan_ll)
    ConstraintLayout daifukuanLl;
    @BindView(R.id.daifahuo_ll)
    ConstraintLayout daifahuoLl;
    @BindView(R.id.daishouhuo_ll)
    ConstraintLayout daishouhuoLl;
    @BindView(R.id.yiwancheng_ll)
    ConstraintLayout yiwanchengLl;
    @BindView(R.id.con_yingchang)
    ConstraintLayout conYingchang;
    private boolean mCouldChange = true;
    private LocalBroadcastManager mBroadcastManager;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private static final int THUMB_SIZE = 500;
    private static final int THUMB_SIZE1 = 400;

    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mBroadcastManager = getInstance(mActivity);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LoginActivity.ACTION_WX_LOGIN_SUCCEED);
        intentFilter.addAction(ACTION_LOGIN_SUCCEED);
        intentFilter.addAction(ACTION_REFRESH_STATUS);
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo==null)
        {
            conYingchang.setVisibility(View.GONE);
        }
        //todo  临时存储
      /*  mUserInfo=new UserInfo();
        mUserInfo.setToken("7ee35ab8215b6992c500a42ae6abe3ec");
        mUserInfo.setId(18281);
        SharedPrefUtils.save(mUserInfo,UserInfo.class);*/
        refreshLayout();
        imgYqhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    Bitmap bitmap = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.drawable.tuxiang);
                    WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_SESSION,
                            "http://web.enticementchina.com/register.html", mActivity.getString(R.string.app_name_test),
                            "因诗美，我的质感美学", bitmap);
                }
            }

        });
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {

                }
            }
        });
    }


    private void load() {
        //   mViewModel.getSplash().observe(this, mObserver);

    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String type = SharedPrefUtils.getWechatAuth();

            if (intent != null) {

                if (LoginActivity.ACTION_WX_LOGIN_SUCCEED.equals(intent.getAction()) && "mine".equals(type)) {
                    /*if (tenOuter()) {
                       String code = intent.getStringExtra("code");
                        mLoginViewModel.getWxToken(Constant.WX_APP_ID, Constant.WX_APP_SECRET_ID,
                                code, "authorization_code")
                                .observe(_MineFragment.this, mWxTokenObserver);
                    } else {
                        //获取保存的信息
                        String wxOpenId = SharedPrefUtils.getWxOpenId();
                        String openId = wxOpenId.split("FDSH")[1];
                        String token = wxOpenId.split("FDSH")[2];
                        mLoginViewModel.getWxInfo(token, openId)
                                .observe(_MineFragment.this, mWxInfoObserver);
                    }*/
                } else if (ACTION_LOGIN_SUCCEED.equals(intent.getAction())) {
                    UserInfo userInfo = (UserInfo) intent.getSerializableExtra(DATA_USER_INFO);
                    if (userInfo != null) {
                        mUserInfo = userInfo;
                        refreshLayout();

                    }
                } else if (ACTION_REFRESH_STATUS.equals(intent.getAction())) {
                    OrderViewModel orderViewModel = ViewModelProviders.of(_MineFragment.this).get(OrderViewModel.class);
                    orderViewModel.getStatisticsOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()))
                            .observe(mActivity, mTotalOrderObserver);
                }

            }
        }
    };

    private void refreshLayout() {
        if (mUserInfo == null) {
            ImageLoader.loadNoPlaceholder(R.drawable.tuxiang, imgTuxiang);
            textName.setText("请登录");
            ViewUtils.hideView(dot1Tv);
            ViewUtils.hideView(dot2Tv);
            ViewUtils.hideView(dot3Tv);
            ViewUtils.hideView(dot4Tv);
            conYingchang.setVisibility(View.GONE);
            return;
        }
        conYingchang.setVisibility(View.VISIBLE);
        ImageLoader.loadNoPlaceholder(mUserInfo.getHeadimg(), imgTuxiang);
        textName.setText(mUserInfo.getNickname());

        OrderViewModel orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderViewModel.getStatisticsOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()))
                .observe(mActivity, mTotalOrderObserver);
    }

    @OnClick({R.id.img_kaiguan, R.id.btn_shengji, R.id.text_quanbudingdan, R.id.text_tuiguangyongjing, R.id.text_wodetuandui, R.id.text_shouhuodizi, R.id.text_yejiyuefan, R.id.text_wodekefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_kaiguan:
                if (AppUtils.isAllowPermission(mActivity)) {

                    new XPopup.Builder(mActivity)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(mActivity,
                                    "亲，确定要退出吗？", "取消", "确定", () -> {
                                int mid = mUserInfo.getId();
                                String token = mUserInfo.getToken();
                                mViewModel.loginOut("2", token, "" + mid).observe(this, mloginoutObserver);
                                //loginout();
                            }))
                            .show();

                }
                break;
            case R.id.btn_shengji:
                //startActivity(new Intent(mActivity, ZengAddressActivity.class));
                if (AppUtils.isAllowPermission(mActivity)) {
                    new XPopup.Builder(mActivity)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(mActivity,
                                    "购买入会礼包即可升级成为经销商", "关闭", "去购买", () -> {
                                LocalBroadcastManager broadcastManager = getInstance(mActivity);
                                broadcastManager.sendBroadcast(new Intent(ACTION_GO_TO_HOME));
                            }))
                            .show();
                }
                break;
            case R.id.text_quanbudingdan:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, MyOrderActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.text_tuiguangyongjing:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, CommissionActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.text_wodetuandui:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, MyTeamActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.text_shouhuodizi:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, RecAddressActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.text_yejiyuefan:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, AchievementActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.text_wodekefu:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, KeFuActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
        }
    }


    @OnClick({R.id.daifukuan_ll, R.id.daifahuo_ll, R.id.daishouhuo_ll, R.id.yiwancheng_ll})
    public void onOrderClicked(View view) {
        if (AppUtils.isAllowPermission(mActivity)) {

            Intent intent = new Intent(mActivity, MyOrderActivity.class);
            switch (view.getId()) {
                case R.id.daifukuan_ll:
                    intent.putExtra("cur", 1);
                    break;
                case R.id.daifahuo_ll:
                    intent.putExtra("cur", 2);
                    break;
                case R.id.daishouhuo_ll:
                    intent.putExtra("cur", 3);
                    break;
                case R.id.yiwancheng_ll:
                    intent.putExtra("cur", 4);
                    break;
            }

            startActivity(intent);
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(mReceiver);
    }

    //判断是否超过10分钟
    private boolean tenOuter() {

        String wxOpenId = SharedPrefUtils.getWxOpenId();

        if ("0FDSH0FDSH0".equals(wxOpenId)) {
            return true;
        }

        long oldTime = Long.parseLong(wxOpenId.split("FDSH")[0]);

        long now = new Date().getTime();
        long ten = 10 * 60 * 1000L;

        if (now - oldTime >= ten) {
            return true;
        }

        return false;
    }

    private Observer<Status<Base>> mObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    break;
                case Status.ERROR:
                    FToast.error("请求错误，请稍后再试。");
                    break;
                case Status.SUCCESS:
                    if (baseStatus.content == null) {
                        FToast.error("请求错误，请稍后再试。");
                        return;
                    }
                    if (baseStatus.content.code == 1) {
                        String s = new Gson().toJson(baseStatus.content.data);
                        //    UserInfo userInfo =(UserInfo)baseStatus.content.data;
                        FToast.success("登录成功");
                     /*   SharedPrefUtils.save(userInfo,UserInfo.class);
                        Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                       intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                        LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
                        finish();*/
                     /*   if (mOnLoginListener != null) {
                            mOnLoginListener.onLoginSucceed(userInfo, mShowContract);
                        }*/
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
            }
        }
    };

    private Observer<Status<Base>> mloginoutObserver = new Observer<Status<Base>>() {
        @Override
        public void onChanged(Status<Base> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:
                    break;
                case Status.ERROR:
                    loginout();
                    break;
                case Status.SUCCESS:

                    loginout();
                    break;
            }
        }
    };


    private void loginout() {
        FToast.success("退出登录");
        SharedPrefUtils.exit();
        mUserInfo = null;
        EventBus.getDefault().postSticky(new LoginOutEvent());
       // refreshLayout();
    }


    private Observer<Status<OrderStatistics>> mTotalOrderObserver = new Observer<Status<OrderStatistics>>() {
        @Override
        public void onChanged(Status<OrderStatistics> orderStatus) {
            switch (orderStatus.status) {
                case Status.LOADING:
                    break;
                case Status.ERROR:
                    FToast.error("获取订单状态失败");
                    break;
                case Status.SUCCESS:

                    if (orderStatus.content == null) {

                        FToast.error("获取订单状态失败");
                        return;
                    }

                    String s = new Gson().toJson(orderStatus.content);
                    if (orderStatus.content.getCode() == 1) {
                        OrderStatistics content = orderStatus.content;
                        List<OrderStatistics.DataBean> data = content.getData();
                        for (int i = 0; i < data.size(); i++) {
                            OrderStatistics.DataBean dataBean = data.get(i);
                            int status = dataBean.getStatus();
                            int count = dataBean.getCount();

                            // 对订单状态设置数量
                            switch (status) {

                                case 2:
                                    if (count == 0) {
                                        ViewUtils.hideView(dot1Tv);
                                    } else {
                                        ViewUtils.showView(dot1Tv);
                                        dot1Tv.setText(String.valueOf(count));
                                    }

                                    break;
                                case 3:
                                    if (count == 0) {
                                        ViewUtils.hideView(dot2Tv);
                                    } else {
                                        ViewUtils.showView(dot2Tv);
                                        dot2Tv.setText(String.valueOf(count));
                                    }

                                    break;
                                case 4:
                                    if (count == 0) {
                                        ViewUtils.hideView(dot3Tv);
                                    } else {
                                        ViewUtils.showView(dot3Tv);
                                        dot3Tv.setText(String.valueOf(count));
                                    }

                                    break;
                                case 5:
                                    if (count == 0) {
                                        ViewUtils.hideView(dot4Tv);
                                    } else {
                                        ViewUtils.showView(dot4Tv);
                                        dot4Tv.setText(String.valueOf(count));
                                    }

                                    break;

                            }
                        }

                    } else {
                        FToast.error("获取订单状态失败");
                    }
                    break;
            }
        }
    };


}

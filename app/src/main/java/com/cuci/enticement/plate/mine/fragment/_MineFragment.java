package com.cuci.enticement.plate.mine.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.DataUserInfo;
import com.cuci.enticement.bean.HxBean;
import com.cuci.enticement.bean.OrderStatistics;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.ClickMyEvent;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.common.DailyActivity;
import com.cuci.enticement.plate.common.LoginActivity;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.activity.AchievementActivity;
import com.cuci.enticement.plate.mine.activity.CommissionActivity;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.PKActivity;
import com.cuci.enticement.plate.mine.activity.RecAddressActivity;
import com.cuci.enticement.plate.mine.activity.SettingsActivity;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Conversation;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.igexin.sdk.PushManager;
import com.lxj.xpopup.XPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

import static androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance;
import static com.cuci.enticement.plate.common.MainActivity.ACTION_GO_TO_HOME;
import static com.superrtc.ContextUtils.getApplicationContext;


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
    public static final String ACTION_REFRESH_HX = "com.example.enticement.plate.mine.fragment.ACTION_REFRESH_HX";
    @BindView(R.id.text_name)
    TextView textName;
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
    @BindView(R.id.text_huiyuan)
    TextView textHuiyuan;
    @BindView(R.id.text_huiyuan1)
    TextView textHuiyuan1;
    @BindView(R.id.text_huiyuan2)
    TextView textHuiyuan2;
    @BindView(R.id.dot1_hx)
    TextView dot1Hx;
    @BindView(R.id.wodekefu_ll)
    ConstraintLayout wodekefuLl;
    @BindView(R.id.text_pk)
    TextView textPk;
    @BindView(R.id.text_wodeshezhi)
    TextView textWodeshezhi;
    @BindView(R.id.img_headwear)
    ImageView imgHeadwear;
    @BindView(R.id.text_wodegonggao)
    TextView textWodegonggao;
    @BindView(R.id.img_shape_bai4)
    ImageView imgShapeBai4;
    @BindView(R.id.text_gongju)
    TextView textGongju;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.con_gongju)
    ConstraintLayout conGongju;
    @BindView(R.id.text_shareliwu)
    TextView textShareliwu;
    @BindView(R.id.text_wodetuandui2)
    TextView textWodetuandui2;
    @BindView(R.id.text_pk2)
    TextView textPk2;
    @BindView(R.id.text_yejiyuefan2)
    TextView textYejiyuefan2;
    @BindView(R.id.ll_fuwu1)
    LinearLayout llFuwu1;
    private boolean mCouldChange = true;
    private LocalBroadcastManager mBroadcastManager;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private static final int THUMB_SIZE = 500;
    private static final int THUMB_SIZE1 = 400;
    private boolean bag = false;
    private int is_month;



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
        intentFilter.addAction(ACTION_REFRESH_HX);
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
        EventBus.getDefault().register(this);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);

        //刷新界面
        refreshLayout();
        imgYqhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    Bitmap bitmap = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.drawable.tuxiang);
                    WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_SESSION,
                            "http://web.enticementchina.com/present.html?mid=" + mUserInfo.getId()+"&phone=" + mUserInfo.getPhone(), mActivity.getString(R.string.app_name_test),
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
        textPk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, PKActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
            }
        });
        textWodeshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    startActivity(new Intent(mActivity, SettingsActivity.class));
                }
            }
        });
        textWodegonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /* if (AppUtils.isAllowPermission(mActivity)) {
                    startActivity(new Intent(mActivity, NoticeActivity.class));
                }*/

            }
        });


    }

    private Observer<Status<ResponseBody>> mdataObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                dataUserinfo(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void dataUserinfo(ResponseBody body) {
        try {
            String b = body.string();
            DataUserInfo mDataUserInfo = new Gson().fromJson(b, DataUserInfo.class);
            if (mDataUserInfo.getCode() == 1) {
                //保存is_new
                int is_new = mDataUserInfo.getData().getIs_new();
                if (is_new == 1) {
                    ViewUtils.showView(conYingchang);
                    ViewUtils.showView(imgYqhy);
                    if (SharedPrefUtils.getisnew() != is_new) {
                        SharedPrefUtils.saveisnew(is_new);
                        EventBus.getDefault().post(new ProgoodsEvent());
                    }
                } else {
                    ViewUtils.hideView(imgYqhy);
                    ViewUtils.hideView(conYingchang);
                }
                //分享赠礼
                int daily_activity = mDataUserInfo.getData().getDaily_activity();
                if (daily_activity == 1) {
                    ViewUtils.showView(llFuwu1);
                 final String daily_activity_url = mDataUserInfo.getData().getDaily_activity_url();
                    textShareliwu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intentProd = new Intent(mActivity, DailyActivity.class);
                                intentProd.putExtra("url", daily_activity_url);
                                intentProd.putExtra("token", mUserInfo.getToken());
                                mActivity.startActivity(intentProd);
                        }
                    });
                } else {
                    ViewUtils.hideView(llFuwu1);
                }
                if (mDataUserInfo.getData().getVip_level() == 0) {
                    textHuiyuan.setText("用户");
                    textHuiyuan1.setText("");
                } else if (mDataUserInfo.getData().getVip_level() == 1) {
                    textHuiyuan.setText("会员");
                    textHuiyuan1.setText("");
                } else if (mDataUserInfo.getData().getVip_level() == 2) {
                    textHuiyuan.setText("服务商");
                    textHuiyuan1.setVisibility(View.GONE);
                    textHuiyuan2.setVisibility(View.GONE);
                    btnShengji.setVisibility(View.GONE);
                    imgHeadwear.setVisibility(View.VISIBLE);
                }

                mUserInfo.setVip_level(mDataUserInfo.getData().getVip_level());
                mUserInfo.setArea(mDataUserInfo.getData().getArea());
                mUserInfo.setCity(mDataUserInfo.getData().getCity());
                mUserInfo.setProvince(mDataUserInfo.getData().getProvince());
                mUserInfo.setSex(mDataUserInfo.getData().getSex());
                ImageLoader.loadPlaceholder1(mUserInfo.getHeadimg(), imgTuxiang);
                textName.setText(mUserInfo.getNickname());
                SharedPrefUtils.save(mUserInfo, UserInfo.class);
                int is_bindingwx = mDataUserInfo.getData().getIs_bindingwx();
                if (is_bindingwx == 1) {
                    SharedPrefUtils.saveWXBind(1);
                } else {
                    SharedPrefUtils.saveWXBind(0);
                }
                is_month = mDataUserInfo.getData().getIs_month();
                if (mDataUserInfo.getData().getIs_month() == 1) {
                    new XPopup.Builder(mActivity)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(mActivity,
                                    "请设置福利收货地址，领取奖励哦", "关闭", "去设置", () -> {
                                Intent intentProd = new Intent(mActivity, AchievementActivity.class);
                                intentProd.putExtra("Data", is_month);
                                mActivity.startActivity(intentProd);
                            }))
                            .show();
                }

            } else {

                FToast.error(mDataUserInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void load() {


    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String type = SharedPrefUtils.getWechatAuth();

            if (intent != null) {

                if (LoginActivity.ACTION_WX_LOGIN_SUCCEED.equals(intent.getAction()) && "mine".equals(type)) {

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
                    //刷新当前用户信息
                    // mViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(mActivity, mdataObserver);
                } else if (ACTION_REFRESH_HX.equals(intent.getAction())) {
                    int data = intent.getIntExtra("data", 0);
                    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), uri);
                    rt.play();

                }

            }
        }
    };

    private void refreshLayout() {
        if (mUserInfo == null) {
            ImageLoader.loadPlaceholder(R.drawable.tuxiang, imgTuxiang);
            textName.setText("请登录");
            ViewUtils.hideView(dot1Tv);
            ViewUtils.hideView(dot2Tv);
            ViewUtils.hideView(dot3Tv);
            ViewUtils.hideView(dot4Tv);
            ViewUtils.hideView(conYingchang);
            ViewUtils.hideView(imgYqhy);
            imgHeadwear.setVisibility(View.GONE);
            return;
        }
        PushManager.getInstance().bindAlias(mActivity, String.valueOf(mUserInfo.getId()));
        ImageLoader.loadPlaceholder1(mUserInfo.getHeadimg(), imgTuxiang);
        textName.setText(mUserInfo.getNickname());

        if (SharedPrefUtils.getShowhxCode() == 0) {
            mViewModel.hxreg(mUserInfo.getPhone(), "2", mUserInfo.getToken(), String.valueOf(mUserInfo.getId())).observe(this, mhxregObserver);
        } else {
            if (!TextUtils.isEmpty(mUserInfo.getPhone())) {
                ChatClient.getInstance().login(mUserInfo.getPhone(), "ysm6j351r6", new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("Success_hx", "环信登录成功");
                    }

                    @Override
                    public void onError(int code, String error) {
                        Log.d("error_hx", error);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
            }

        }
        if (ChatClient.getInstance().isLoggedInBefore()) {
            Conversation conversation = ChatClient.getInstance().chatManager().getConversation("kefuchannelimid_269943");
            int i = conversation.unreadMessagesCount();
            FLog.e("shuangliang", "" + i);
            if (i == 0) {
                dot1Hx.setVisibility(View.GONE);
            } else {
                dot1Hx.setVisibility(View.VISIBLE);
            }
        }


        OrderViewModel orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderViewModel.getStatisticsOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()))
                .observe(mActivity, mTotalOrderObserver);

        //刷新商品详情数据
        // mViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(this, mdataObserver);
    }

    @OnClick({R.id.img_kaiguan, R.id.btn_shengji, R.id.text_quanbudingdan, R.id.text_tuiguangyongjing, R.id.text_wodetuandui, R.id.text_shouhuodizi, R.id.text_yejiyuefan, R.id.text_wodekefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_kaiguan:
                if (AppUtils.isAllowPermission(mActivity)) {

                }
                break;
            case R.id.btn_shengji:
                if (AppUtils.isAllowPermission(mActivity)) {
                  /*  if (mUserInfo.getVip_level() == 0) {
                        new XPopup.Builder(mActivity)
                                .dismissOnBackPressed(false)
                                .dismissOnTouchOutside(false)
                                .asCustom(new TipsPopup(mActivity,
                                        "购买入会礼包即可升级成为会员", "关闭", "去购买", () -> {
                                    LocalBroadcastManager broadcastManager = getInstance(mActivity);
                                    broadcastManager.sendBroadcast(new Intent(ACTION_GO_TO_HOME));
                                }))
                                .show();
                    } else if (mUserInfo.getVip_level() == 1) {
                        new XPopup.Builder(mActivity)
                                .dismissOnBackPressed(false)
                                .dismissOnTouchOutside(false)
                                .asCustom(new TipsPopup1(mActivity,
                                        "团队满200人即可成为服务商", "关闭", () -> {
                                }))
                                .show();
                    } else if (mUserInfo.getVip_level() == 2) {

                    }*/
                    LocalBroadcastManager broadcastManager = getInstance(mActivity);
                    broadcastManager.sendBroadcast(new Intent(ACTION_GO_TO_HOME));
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
                    intentProd.putExtra("Data", is_month);
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.text_wodekefu:
                if (AppUtils.isAllowPermission(mActivity)) {
                    if (ChatClient.getInstance().isLoggedInBefore()) {
                        //已经登录，可以直接进入会话界面
                        Intent intent = new IntentBuilder(mActivity)
                                .setServiceIMNumber("kefuchannelimid_269943")
                                .setTitleName("美美")
                                .build();
                        startActivity(intent);
                        //所有未读消息数清零
                        ChatClient.getInstance().chatManager().markAllConversationsAsRead();
                        dot1Hx.setVisibility(View.GONE);
                    } else {
                        //未登录，需要登录后，再进入会话界面
                        FToast.error("环信登录失败");
                    }
                }
                break;
        }
    }

    private Observer<Status<ResponseBody>> mhxregObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                operacheck(body);
                break;
            case Status.ERROR:
                FToast.error("网络错误");
                break;
        }
    };

    private void operacheck(ResponseBody body) {
        try {
            String b = body.string();
            HxBean info = new Gson().fromJson(b, HxBean.class);
            if (info.getCode() == 1) {
                SharedPrefUtils.saveShowhxCode(1);
                ChatClient.getInstance().login(mUserInfo.getPhone(), "ysm6j351r6", new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("Success_hx", "环信登录成功");
                    }

                    @Override
                    public void onError(int code, String error) {
                        Log.d("error_hx", error);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
            } else {
                FToast.error(info.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
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
        EventBus.getDefault().unregister(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
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

    //请求当前用户信息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickMyEvent(ClickMyEvent event) {
        if (mUserInfo != null) {
            mViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(this, mdataObserver);
        }
    }
    //刷新isnew显示数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickIsnewEvent(IsnewEvent event) {
        //进入页面先请求是否会员
        if (mUserInfo != null) {
            mViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(this, mdataObserver);
        }

    }

}

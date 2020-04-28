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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;
import com.cuci.enticement.bean.DataUserInfo;
import com.cuci.enticement.bean.HxBean;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.OrderStatistics;
import com.cuci.enticement.bean.ReceiveCodeBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TuiKuanWuLiuBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.ClickMyEvent;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.cart.activity.AftermarketActivity;
import com.cuci.enticement.plate.common.DailyActivity;
import com.cuci.enticement.plate.common.LoginActivity;
import com.cuci.enticement.plate.common.popup.ShareImgTipsPopup;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.common.vm.MainViewModel;
import com.cuci.enticement.plate.mine.activity.AchievementActivity;
import com.cuci.enticement.plate.mine.activity.CommissionActivity;
import com.cuci.enticement.plate.mine.activity.JiFenActivity;
import com.cuci.enticement.plate.mine.activity.KaQuanActivity;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.NoticeActivity;
import com.cuci.enticement.plate.mine.activity.PKActivity;
import com.cuci.enticement.plate.mine.activity.QianDaoActivity;
import com.cuci.enticement.plate.mine.activity.RecAddressActivity;
import com.cuci.enticement.plate.mine.activity.SettingsActivity;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.UnicodeUitls;
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
//import static com.superrtc.ContextUtils.getApplicationContext;


/**
 * 首页外层Fragment
 */

public class _MineFragment extends BaseFragment {

    private static final String TAG = _MineFragment.class.getSimpleName();
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.img_tuxiang)
    CircleImageView imgTuxiang;
    @BindView(R.id.img_jingxiao)
    ImageView imgJingxiao;
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
    @BindView(R.id.text_fuwu)
    TextView textFuwu;
    @BindView(R.id.view1)
    View view1;
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
    TextView imgYqhy;
    @BindView(R.id.daifukuan_ll)
    ConstraintLayout daifukuanLl;
    @BindView(R.id.daifahuo_ll)
    ConstraintLayout daifahuoLl;
    @BindView(R.id.daishouhuo_ll)
    ConstraintLayout daishouhuoLl;
    @BindView(R.id.yiwancheng_ll)
    ConstraintLayout yiwanchengLl;

    @BindView(R.id.text_huiyuan)
    TextView textHuiyuan;
    @BindView(R.id.dot1_hx)
    TextView dot1Hx;
    @BindView(R.id.wodekefu_ll)
    ConstraintLayout wodekefuLl;
    @BindView(R.id.img_headwear)
    ImageView imgHeadwear;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.con_dingdan)
    ConstraintLayout conDingdan;
    @BindView(R.id.text_wodekajuan)
    TextView textWodekajuan;
    @BindView(R.id.dot1_kajuan)
    TextView dot1Kajuan;
    @BindView(R.id.wodekajuan_ll)
    ConstraintLayout wodekajuanLl;
    @BindView(R.id.text_jifen)
    TextView textJifen;
    @BindView(R.id.jifen_ll)
    ConstraintLayout jifenLl;
    @BindView(R.id.yphy_ll)
    ConstraintLayout yphyLl;
    @BindView(R.id.con_wodemeifen)
    ConstraintLayout conWodemeifen;
    @BindView(R.id.text_shouyi)
    TextView textShouyi;
    @BindView(R.id.con_wodeshouyi)
    ConstraintLayout conWodeshouyi;
    @BindView(R.id.text_fuli)
    TextView textFuli;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.con_wodefuli)
    ConstraintLayout conWodefuli;
    @BindView(R.id.text_paihangbang)
    TextView textPaihangbang;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.con_wodepaihangbang)
    ConstraintLayout conWodepaihangbang;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.con_dizi)
    ConstraintLayout conDizi;
    @BindView(R.id.text_gonggao)
    TextView textGonggao;
    @BindView(R.id.con_gonggao)
    ConstraintLayout conGonggao;
    @BindView(R.id.text_lipingzhongxing)
    TextView textLipingzhongxing;
    @BindView(R.id.con_lipingzhongxing)
    ConstraintLayout conLipingzhongxing;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.text_qiandao)
    TextView textQiandao;
    @BindView(R.id.img_tubiao1)
    ImageView imgTubiao1;
    @BindView(R.id.img_tubiao2)
    ImageView imgTubiao2;
    @BindView(R.id.img_tubiao3)
    ImageView imgTubiao3;
    @BindView(R.id.img_tubiao4)
    ImageView imgTubiao4;
    @BindView(R.id.img_tubiao5)
    ImageView imgTubiao5;
    @BindView(R.id.img_tubiao6)
    ImageView imgTubiao6;
    @BindView(R.id.img_tubiao7)
    ImageView imgTubiao7;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.img_tubiao8)
    ImageView imgTubiao8;
    @BindView(R.id.text_gongga8)
    TextView textGongga8;
    @BindView(R.id.con_set)
    ConstraintLayout conSet;
    @BindView(R.id.dot1_qiandao)
    TextView dot1Qiandao;
    @BindView(R.id.text_liping_dot)
    TextView textLipingDot;
    @BindView(R.id.con_wodedingdan)
    ConstraintLayout conWodedingdan;
    private boolean mCouldChange = true;
    private LocalBroadcastManager mBroadcastManager;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private static final int THUMB_SIZE = 500;
    private static final int THUMB_SIZE1 = 400;
    private boolean bag = false;
    private int is_month;
    private String daily_activity_url;


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
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
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
                    new XPopup.Builder(mActivity)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new ShareImgTipsPopup(mActivity, "取消", new ShareImgTipsPopup.OnExitListener() {
                                @Override
                                public void onPositive1() {
                                    Bitmap bitmap = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.drawable.tuxiang);
                                    WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_SESSION,
                                            "http://web.enticementchina.com/present.html?mid=" + mUserInfo.getId() + "&phone=" + mUserInfo.getPhone(), mActivity.getString(R.string.app_name_test),
                                            "因诗美，我的质感美学", bitmap);
                                }

                                @Override
                                public void onPositive2() {
                                    Bitmap bitmap = BitmapFactory.decodeResource(BasicApp.getContext().getResources(), R.drawable.tuxiang);
                                    WxShareUtils.shareToWX(WxShareUtils.WX_SCENE_TIME_LINE,
                                            "http://web.enticementchina.com/present.html?mid=" + mUserInfo.getId() + "&phone=" + mUserInfo.getPhone(), "邀您即刻体验蕾丝面膜",
                                            "因诗美，我的质感美学", bitmap);
                                }

                                @Override
                                public void onCancel() {

                                }
                            }))
                            .show();

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
        conWodepaihangbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, PKActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
            }
        });
        conSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    startActivity(new Intent(mActivity, SettingsActivity.class));

                }
            }
        });
        conGonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    startActivity(new Intent(mActivity, NoticeActivity.class));
                    // startActivity(new Intent(mActivity, PayOfterActivity.class));
                }

            }
        });
        textWodekajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                   /* new XPopup.Builder(mActivity)
                            .dismissOnTouchOutside(false)
                            .dismissOnBackPressed(false)
                            .asCustom(new TipsPopup_kaquan(mActivity,
                                    () -> {

                                    }))
                            .show();*/
                    startActivity(new Intent(mActivity, KaQuanActivity.class));
                }
            }
        });
        textJifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    startActivity(new Intent(mActivity, JiFenActivity.class));
                }
            }
        });

        textQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    startActivity(new Intent(mActivity, QianDaoActivity.class));
                }
            }
        });

        conLipingzhongxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, DailyActivity.class);
                    intentProd.putExtra("url", daily_activity_url);
                    intentProd.putExtra("token", mUserInfo.getToken());
                    mActivity.startActivity(intentProd);
                }
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
                //是否绑定微信
                int is_bindingwx1 = mDataUserInfo.getData().getIs_bindingwx();
                if (is_bindingwx1 == 1) {
                    SharedPrefUtils.saveWXBind(1);
                } else {
                    SharedPrefUtils.saveWXBind(0);
                }
                //保存is_new
                int is_new = mDataUserInfo.getData().getIs_new();
                if (is_new == 1) {
                    //  ViewUtils.showView(conYingchang);
                    if (SharedPrefUtils.getisnew() != is_new) {
                        SharedPrefUtils.saveisnew(is_new);
                        EventBus.getDefault().post(new ProgoodsEvent());
                    }
                } else {
                    //   ViewUtils.hideView(conYingchang);
                }
                //礼品中心
                int daily_activity = mDataUserInfo.getData().getDaily_activity();
                int gift = mDataUserInfo.getData().getMenu().getGift();

                daily_activity_url = mDataUserInfo.getData().getDaily_activity_url();

                if (gift == 1) {
                    ViewUtils.showView(textLipingDot);
                } else {
                    ViewUtils.hideView(textLipingDot);
                }
                //签到
                int is_signin = mDataUserInfo.getData().getIs_signin();
                if (is_signin == 0) {
                    ViewUtils.showView(dot1Qiandao);
                    textQiandao.setText("签到");
                } else {
                    ViewUtils.hideView(dot1Qiandao);
                    textQiandao.setText("已签到");
                }

               /* if (daily_activity == 1) {
                    ViewUtils.showView(goodsLl);
                    if (gift == 1) {
                        ViewUtils.showView(dot1Goods);
                    } else {
                        ViewUtils.hideView(dot1Goods);
                    }
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
                    ViewUtils.hideView(goodsLl);
                }*/
                if (mDataUserInfo.getData().getVip_level() == 0) {
                    textHuiyuan.setText("用户");
                } else if (mDataUserInfo.getData().getVip_level() == 1) {
                    textHuiyuan.setText("美粉");
                } else if (mDataUserInfo.getData().getVip_level() == 2) {
                    textHuiyuan.setText("超级美粉");
                    // imgHeadwear.setVisibility(View.VISIBLE);
                }

                mUserInfo.setVip_level(mDataUserInfo.getData().getVip_level());
                mUserInfo.setArea(mDataUserInfo.getData().getArea());
                mUserInfo.setCity(mDataUserInfo.getData().getCity());
                mUserInfo.setProvince(mDataUserInfo.getData().getProvince());
                mUserInfo.setSex(mDataUserInfo.getData().getSex());
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

                DataUserInfo.DataBean.OrdertotalBean ordertotal = mDataUserInfo.getData().getOrdertotal();
                // 对订单状态设置数量
                if (ordertotal.get_$2() == 0) {
                    ViewUtils.hideView(dot1Tv);
                } else {
                    ViewUtils.showView(dot1Tv);
                    dot1Tv.setText(String.valueOf(ordertotal.get_$2()));
                }

                if (ordertotal.get_$3() == 0) {
                    ViewUtils.hideView(dot2Tv);
                } else {
                    ViewUtils.showView(dot2Tv);
                    dot2Tv.setText(String.valueOf(ordertotal.get_$3()));
                }

                if (ordertotal.get_$4() == 0) {
                    ViewUtils.hideView(dot3Tv);
                } else {
                    ViewUtils.showView(dot3Tv);
                    dot3Tv.setText(String.valueOf(ordertotal.get_$4()));
                }

            } else if (mDataUserInfo.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(mActivity);
                FToast.error(mDataUserInfo.getInfo());
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
                    OrderViewModel orderViewModel = new ViewModelProvider(mActivity).get(OrderViewModel.class);
                    orderViewModel.getStatisticsOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(BasicApp.getContext()))
                            .observe(mActivity, mTotalOrderObserver);
                } else if (ACTION_REFRESH_HX.equals(intent.getAction())) {
                    int data = intent.getIntExtra("data", 0);
                    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone rt = RingtoneManager.getRingtone(mActivity, uri);
                    rt.play();

                }

            }
        }
    };

    private void refreshLayout() {
        if (mUserInfo == null) {
            ImageLoader.loadPlaceholder(R.drawable.logo, imgTuxiang);
            textName.setText("请登录");
            ViewUtils.hideView(dot1Tv);
            ViewUtils.hideView(dot2Tv);
            ViewUtils.hideView(dot3Tv);
            ViewUtils.hideView(dot4Tv);
            //  ViewUtils.hideView(conYingchang);

            //   imgHeadwear.setVisibility(View.GONE);
            return;
        }


        FLog.e("user", "" + mUserInfo.getId());
        //个推绑定用户id
        PushManager.getInstance().bindAlias(mActivity, String.valueOf(mUserInfo.getId()));
        //上传别名cid
        getCid();


        ImageLoader.loadPlaceholder1(mUserInfo.getHeadimg(), imgTuxiang);
        //textName.setText(mUserInfo.getNickname());
        textName.setText(UnicodeUitls.unicodeToString(mUserInfo.getNickname()));

        //注册环信
        if (SharedPrefUtils.getShowhxCode() == 0) {
            mViewModel.hxreg(mUserInfo.getPhone(), "2", mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mhxregObserver);
        } else {
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


        //显示环信未读
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

        OrderViewModel orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getStatisticsOrder(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "" + AppUtils.getVersionCode(BasicApp.getContext()))
                .observe(mActivity, mTotalOrderObserver);

    }

    private Observer<Status<ResponseBody>> refundExpressObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera3(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }


    };

    private void opera3(ResponseBody body) {
        try {
            String b = body.string();
            TuiKuanWuLiuBean mTuiKuanWuLiuBean = new Gson().fromJson(b, TuiKuanWuLiuBean.class);
            if (mTuiKuanWuLiuBean.getCode() == 1) {
                SharedPrefUtils.save(mTuiKuanWuLiuBean, TuiKuanWuLiuBean.class);
            } else {
                FToast.error(mTuiKuanWuLiuBean.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    @OnClick({R.id.con_wodedingdan, R.id.con_wodeshouyi, R.id.con_wodemeifen, R.id.con_dizi, R.id.text_wodekefu, R.id.con_wodefuli})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.con_wodedingdan:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, MyOrderActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.con_wodeshouyi:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, CommissionActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.con_wodemeifen:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, MyTeamActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.con_dizi:
                if (AppUtils.isAllowPermission(mActivity)) {
                    Intent intentProd = new Intent(mActivity, RecAddressActivity.class);
                    intentProd.putExtra("Data", "");
                    mActivity.startActivity(intentProd);
                }
                break;
            case R.id.con_wodefuli:
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
                        FToast.error("联系客服失败，请退出重新登录");
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
                SharedPrefUtils.saveShowhxCode(0);
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
            Intent intent2 = new Intent(mActivity, AftermarketActivity.class);
            switch (view.getId()) {
                case R.id.daifukuan_ll:
                    intent.putExtra("cur", 1);
                    startActivity(intent);
                    break;
                case R.id.daifahuo_ll:
                    intent.putExtra("cur", 2);
                    startActivity(intent);
                    break;
                case R.id.daishouhuo_ll:
                    intent.putExtra("cur", 3);
                    startActivity(intent);
                    break;
                case R.id.yiwancheng_ll:
                    //   intent.putExtra("cur", 4);
                    startActivity(intent2);
                    break;
            }


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
                                  /*  if (count == 0) {
                                        ViewUtils.hideView(dot4Tv);
                                    } else {
                                        ViewUtils.showView(dot4Tv);
                                        dot4Tv.setText(String.valueOf(count));
                                    }*/

                                    break;

                            }
                        }

                    } else if (orderStatus.content.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(mActivity);
                        FToast.error(orderStatus.content.getInfo());
                    } else {
                        FToast.error("获取订单状态失败");
                    }
                    break;
            }
        }
    };

    private void getCid() {
        String cid = PushManager.getInstance().getClientid(mActivity);
        Log.d(TAG, "当前应用的cid=" + cid);
        Log.d(TAG, "当前用户的id=" + mUserInfo.getId());
        //提交个推cid
        mViewModel.getui("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), cid, "" + AppUtils.getVersionCode(mActivity))
                .observe(this, mCommitObserver);
    }

    /**
     * 提交个推cid
     */
    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    ReceiveCodeBean mbean = new Gson().fromJson(result, ReceiveCodeBean.class);
                    //     GeTuibean mbean = new Gson().fromJson(result, GeTuibean.class);
                    if (mbean.getCode() == 1) {
                        FLog.e(TAG, mbean.getInfo());
                    } else {
                        FLog.e(TAG, mbean.getInfo());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Status.LOADING:
                break;
            case Status.ERROR:
                FToast.error(status.message);
                break;
        }
    };

    private Observer<Status<ResponseBody>> mkaquanObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                operyhj(body, status);
                break;
            case Status.ERROR:
                FToast.error("网络错误");
                break;
            case Status.LOADING:
                break;
        }

    };

    private void operyhj(ResponseBody body, Status status) {
        try {
            String b = body.string();
            KaQuanListBean mKaQuanListBean = new Gson().fromJson(b, KaQuanListBean.class);

            if (mKaQuanListBean.getCode() == 1) {
                List<KaQuanListBean.DataBean.ListBean> checkitems = mKaQuanListBean.getData().getList();
                if (checkitems == null || checkitems.size() == 0) {
                    ViewUtils.hideView(dot1Kajuan);
                } else {
                    ViewUtils.showView(dot1Kajuan);
                }
            } else {
                FToast.warning(mKaQuanListBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    //切换此页面请求当前用户信息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickMyEvent(ClickMyEvent event) {
        if (event.getCode() == ClickMyEvent.CHECK_ITEM3) {
            if (mUserInfo != null) {
                mViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mdataObserver);
                //可使用优惠卷
                mViewModel.kaquanlist(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "2", "1", "", "0", "" + AppUtils.getVersionCode(mActivity), Status.LOAD_REFRESH)
                        .observe(this, mkaquanObserver);

                //退货快递
                MainViewModel mmViewModel = new ViewModelProvider(this).get(MainViewModel.class);
                mmViewModel.refundExpress("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, refundExpressObserver);
            }
        }


    }

    //刷新isnew显示数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickIsnewEvent(IsnewEvent event) {
        //进入页面先请求是否会员
        if (mUserInfo != null) {
            mViewModel.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken(), "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mdataObserver);
        }

    }


}

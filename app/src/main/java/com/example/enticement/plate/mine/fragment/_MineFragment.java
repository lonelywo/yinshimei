package com.example.enticement.plate.mine.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.example.enticement.Constant;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.network.ServiceCreator;
import com.example.enticement.plate.common.LoginActivity;
import com.example.enticement.plate.home.activity.ProdActivity;
import com.example.enticement.plate.mine.activity.MyOrderActivity;
import com.example.enticement.utils.ImageLoader;
import com.example.enticement.utils.SharedPrefUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private boolean mCouldChange = true;
    private LocalBroadcastManager mBroadcastManager;
    private UserInfo mUserInfo;

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
        //     mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        mBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LoginActivity.ACTION_WX_LOGIN_SUCCEED);
        intentFilter.addAction(ACTION_LOGIN_SUCCEED);
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }


    private void load() {
        //  mViewModel.getSplash().observe(this, mObserver);
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
                }

            }
        }
    };

    private void refreshLayout() {
        if (mUserInfo == null) {
            ImageLoader.loadNoPlaceholder(R.drawable.tuxiang, imgTuxiang);
            return;
        }


    }

    @OnClick({R.id.img_kaiguan, R.id.btn_shengji, R.id.text_quanbudingdan, R.id.text_daifukuan, R.id.text_daifahuo, R.id.text_daishouhuo, R.id.text_yiwancheng, R.id.text_tuiguangyongjing, R.id.text_wodetuandui, R.id.text_shouhuodizi, R.id.text_yejiyuefan, R.id.text_wodekefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_kaiguan:

                break;
            case R.id.btn_shengji:

                break;
            case R.id.text_quanbudingdan:
                Intent intentProd = new Intent(mActivity, MyOrderActivity.class);
                intentProd.putExtra("bannerData", "");
                mActivity.startActivity(intentProd);
                break;
            case R.id.text_daifukuan:
                break;
            case R.id.text_daifahuo:
                break;
            case R.id.text_daishouhuo:
                break;
            case R.id.text_yiwancheng:
                break;
            case R.id.text_tuiguangyongjing:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.text_wodetuandui:
                break;
            case R.id.text_shouhuodizi:
                break;
            case R.id.text_yejiyuefan:
                break;
            case R.id.text_wodekefu:
                break;
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
}

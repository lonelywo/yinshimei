package com.cuci.enticement.plate.common;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cuci.enticement.R;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.bean.ClauseBean;
import com.cuci.enticement.bean.GuoJiaBean;
import com.cuci.enticement.bean.PushBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TuiKuanWuLiuBean;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.event.CheckHomeEvent;
import com.cuci.enticement.event.ClickMyEvent;
import com.cuci.enticement.event.LoginOutEvent;
import com.cuci.enticement.event.LoginSucceedEvent;
import com.cuci.enticement.plate.cart.fragment._CartFragment;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi2;
import com.cuci.enticement.plate.common.popup.UpdatePopup;
import com.cuci.enticement.plate.common.popup.UpdateProgressPopup;
import com.cuci.enticement.plate.common.vm.MainViewModel;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.fragment._HomeFragment;
import com.cuci.enticement.plate.mall.fragment._MallFragment;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.NoticeActivity;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.CustomizeUtils;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.BottomBarView;
import com.cuci.enticement.widget.FitSystemWindowViewPager;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.mob.secverify.OperationCallback;
import com.mob.secverify.SecVerify;
import com.mob.secverify.exception.VerifyException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements TipsPopupxieyi.OnExitListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String DATA_FUNCTION = "data_function";

    public static final String ACTION_GO_TO_HOME = "com.yinshimei.plate.common.ACTION_GO_TO_HOME";
    public static final String ACTION_GO_TO_MATERIAL = "com.yinshimei.plate.common.ACTION_GO_TO_MATERIAL";
    public static final String ACTION_GO_TO_CART = "com.yinshimei.plate.common.ACTION_GO_TO_CART";
    public static final String ACTION_GO_TO_MINE = "com.yinshimei.plate.common.ACTION_GO_TO_MINE";


    public static final String ACTION_SHOW_POPUP = "com.yinshimei.plate.common.ACTION_SHOW_POPUP";

    @BindView(R.id.view_pager)
    FitSystemWindowViewPager mViewPager;
    @BindView(R.id.bottom_layout)
    BottomBarView mBottomLayout;

    private long mExitTime = 0;
    private MainViewModel mViewModel;
    private ClipboardManager mClipboardManager;
    private Version.DataBean mData;

    private LocalBroadcastManager mLocalBroadcastManager;
    private List<Fragment> mFragments;
    private MainPagerAdapter mPagerAdapter;
    private int localVersion;
    private int serverVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //小米手机状态栏字体黑色
        //MIUIStatusBarUtils.MIUISetStatusBarLightMode(this, true);
        //魅族手机状态栏字体黑色
        // if (Rom.check(Rom.ROM_FLYME)) {
       //    FlymeStatusBarColorUtils.setStatusBarDarkIcon(this, true);
       //  }


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String payload = getIntent().getStringExtra("payload");
        FLog.e("payload::::",payload);
        /*if(!TextUtils.isEmpty(payload)){
            PushBean function = new Gson().fromJson(payload, PushBean.class);
            if (function.getType()==1) {
                Intent intentProd = new Intent(this, MainActivity.class);
                intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentProd);
                //切换首页
                EventBus.getDefault().post(new CheckHomeEvent());
            } else if (function.getType()==2) {
                Intent intentProd = new Intent(this, ProdActivity.class);
                intentProd.putExtra("bannerData", function.getId());
                intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( intentProd);
            }else if (function.getType()==3) {
                Intent intentProd = new Intent(this, NoticeActivity.class);
                intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( intentProd);
            }else if (function.getType()==4) {
                Intent intentProd = new Intent(this, MyTeamActivity.class);
                intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( intentProd);
            }
        }*/


        EventBus.getDefault().register(this);


        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        mFragments = new ArrayList<>();

        mFragments.add(new _HomeFragment());
        mFragments.add(new _MallFragment());
        mFragments.add(new _CartFragment());
        mFragments.add(new _MineFragment());

        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPagerAdapter);
        initBottomLayout();
        mBottomLayout.setOnTabClickListener(position -> mViewPager.setCurrentItem(position));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomLayout.setSelected(position);
                if (position == 3) {
                    EventBus.getDefault().post(new ClickMyEvent(ClickMyEvent.CHECK_ITEM3));
                }
                if (position == 0) {
                    EventBus.getDefault().post(new ClickMyEvent(ClickMyEvent.CHECK_ITEM0));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_GO_TO_HOME);
        intentFilter.addAction(ACTION_GO_TO_MATERIAL);
        intentFilter.addAction(ACTION_GO_TO_CART);
        intentFilter.addAction(ACTION_GO_TO_MINE);
        intentFilter.addAction(ACTION_SHOW_POPUP);
        mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter);


        //检测APP更新
        mViewModel.getVersion("2",""+AppUtils.getVersionCode(this)).observe(this, mUpdateObserver);
        //隐私政策
        mViewModel.clause("2",""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, clauseObserver);
        //打印设备信息
        FLog.e("设备信息",GetDeviceID());

        //国家编号
        mViewModel.getGuoJiaCode("2",""+ AppUtils.getVersionCode(BasicApp.getContext())).observe(this, guojiamObserver);

       
        MainActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this);

        customizeUi();
        SecVerify.autoFinishOAuthPage(false);
       //建议提前调用预登录接口，可以加快免密登录过程，提高用户体验
        SecVerify.preVerify(new OperationCallback<Void>() {
            @Override
            public void onComplete(Void data) {
                //TODO处理成功的结果
                SharedPrefUtils.saveQuHao(true);
            }
            @Override
            public void onFailure(VerifyException e) {
                //TODO处理失败的结果
               // startActivity(new Intent(MainActivity.this, LoginActivity.class));
                SharedPrefUtils.saveQuHao(false);
            }
        });

        boolean b = NotificationManagerCompat.from(BasicApp.getContext()).areNotificationsEnabled();
    }
    private void customizeUi() {
        SecVerify.setUiSettings(CustomizeUtils.customizeUi());
        SecVerify.setLandUiSettings(null);
    }
    private void initBottomLayout() {

        List<BottomBarView.Tab> tabs = new ArrayList<>();

        BottomBarView.Tab tab1 = new BottomBarView.Tab(
                R.drawable.home_huang,
                R.drawable.home_hui, false, getString(R.string.tab_home));

        BottomBarView.Tab tab2 = new BottomBarView.Tab(
                R.drawable.mall_huang,
                R.drawable.mall_hui, false, getString(R.string.tab_material));


        BottomBarView.Tab tab3 = new BottomBarView.Tab(
                R.drawable.cart_huang,
                R.drawable.cart_hui, false, getString(R.string.tab_find));

        BottomBarView.Tab tab4 = new BottomBarView.Tab(
                R.drawable.mine_huang,
                R.drawable.mine_hui, false, getString(R.string.tab_mine));

        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
        tabs.add(tab4);


        mBottomLayout.setTabs(tabs);

    }

    private int mPopupPos = 0;


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                switch (intent.getAction()) {
                    case ACTION_GO_TO_HOME:
                        mBottomLayout.setSelected(0);
                        break;
                    case ACTION_GO_TO_MATERIAL:
                        mBottomLayout.setSelected(1);
                        break;
                    case ACTION_GO_TO_CART:
                        mBottomLayout.setSelected(2);
                        break;
                    case ACTION_GO_TO_MINE:
                        mBottomLayout.setSelected(3);
                        break;
                    case ACTION_SHOW_POPUP:
                        //显示红包弹窗

                        break;
                }
            }
        }
    };


    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mExitTime > 2000) {
            FToast.warning("再按一次退出");
            mExitTime = currentTime;
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault()
                .unregister(this);
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }

    //退出登录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutEvent(LoginOutEvent event) {
        if (mPagerAdapter.getCount() == 4) {

            mFragments.clear();
            mFragments.add(new _HomeFragment());
            mFragments.add(new _MallFragment());
            mFragments.add(new _CartFragment());
            mFragments.add(new _MineFragment());
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.setOffscreenPageLimit(3);
            mViewPager.setCurrentItem(3);
        }
    }

    //登录成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginSucceedEvent event) {

        if (mPagerAdapter.getCount() == 4) {

            mFragments.clear();
            mFragments.add(new _HomeFragment());
            mFragments.add(new _MallFragment());
            mFragments.add(new _CartFragment());
            mFragments.add(new _MineFragment());
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.setOffscreenPageLimit(3);
            mViewPager.setCurrentItem(3);
        }

    }


    /**
     * 获取剪切板的数据
     */
    private String getClipDataString() {
        ClipData clipData = mClipboardManager.getPrimaryClip();
        if (clipData == null) return null;
        ClipData.Item item = clipData.getItemAt(0);
        if (item == null) return null;
        if (item.getText() == null) return null;
        return item.getText().toString();
    }


    private Observer<Status<ResponseBody>> guojiamObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera1(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }


    };

    private void opera1(ResponseBody body) {
        try {
            String b = body.string();
            GuoJiaBean mGuoJiaBean = new Gson().fromJson(b, GuoJiaBean.class);
            if (mGuoJiaBean.getCode() == 1) {
                SharedPrefUtils.save(mGuoJiaBean, GuoJiaBean.class);
            } else {
                FToast.error(mGuoJiaBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }





    private Observer<Status<ResponseBody>> mUpdateObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }


    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            Version mVersion = new Gson().fromJson(b, Version.class);
            if (mVersion.getCode() == 1) {
                operation(mVersion);
            } else {
                FToast.error(mVersion.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void operation(Version version) {

        mData = version.getData();


        int ignoreVersion = SharedPrefUtils.getIgnoreVersion();
        localVersion = AppUtils.getVersionCode(this);
        serverVersion = version.getData().getVersionName();

        if (serverVersion == ignoreVersion) {
            return;
        }

        if (serverVersion > localVersion) {
            new XPopup.Builder(this)
                    .dismissOnTouchOutside(false)
                    .dismissOnBackPressed(false)
                    .asCustom(new UpdatePopup(this, version,
                            () -> {
                               /* if (mData != null) {
                                    new XPopup.Builder(this)
                                            .dismissOnBackPressed(false)
                                            .dismissOnTouchOutside(false)
                                            .asCustom(new UpdateProgressPopup(this, mData))
                                            .show();
                                }*/
                             //  FToast.warning("请前往手机应用商店下载最新版本app");
                                launchAppDetail("com.cuci.enticement","");
                            }))
                    .show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 获得权限
     */
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void needsPermission() {

    }

    /**
     * 点取消后再次点击此功能触发
     *
     * @param request PermissionRequest
     */
    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onShowPermission(PermissionRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("没有所需权限，将无法继续，请点击下方“确定”后打开APP所需的权限。");
        builder.setPositiveButton("确定", (dialog, which) -> request.proceed());
        builder.setNegativeButton("取消", (dialog, which) -> request.cancel());
        builder.create().show();
    }

    /**
     * 权限被拒绝
     */
    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onPermissionDenied() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("因为您拒绝授予读取存储空间的权限，导致无法正常使用此功能，请在再次点击后授予权限。");
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

    /**
     * 点了不再询问后再次打开
     */
    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onNeverAskAgain() {
        ask4Permission();
    }

    /**
     * 提示需要从APP设置里面打开权限
     */
    private void ask4Permission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setCancelable(false);
        builder.setMessage("因为需要使用读取存储空间的权限，请点击下方“设置”按钮后进入权限设置打开读取存储空间权限后再次使用此功能。");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("设置", (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + BasicApp.getContext().getPackageName()));
            startActivity(intent);
        });

        builder.create().show();
    }



    @Override
    public void onPositive() {

    }

    public String GetDeviceID(){

        return "设备类型："+android.os.Build.MODEL;

    }
    private Observer<Status<ResponseBody>> clauseObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera2(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }


    };

    private void opera2(ResponseBody body) {
        try {
            String b = body.string();
            ClauseBean mClauseBean = new Gson().fromJson(b, ClauseBean.class);
            if (mClauseBean.getCode() == 1) {
                String title = mClauseBean.getData().getTitle();
                String url = mClauseBean.getData().getUrl();
                if (SharedPrefUtils.getFirstTime()) {
                    new XPopup.Builder(this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopupxieyi2(this,
                                    url, title,  new TipsPopupxieyi2.OnExitListener() {
                                @Override
                                public void onPositive1() {
                                 finish();
                                }

                                @Override
                                public void onPositive2() {
                                    SharedPrefUtils.saveFirstTime(false);
                                }
                            }))
                            .show();
                }
            } else {
                FToast.error(mClauseBean.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }
    //切换首页
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckHomeEvent(CheckHomeEvent event) {
        mBottomLayout.setSelected(0);
    }


    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg  目标App的包名
     * @param marketPkg 应用商店包名,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg,String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
          //  FToast.warning("请前往手机应用商店下载最新版本app");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.cuci.enticement.plate.common;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;


import com.cuci.enticement.R;
import com.cuci.enticement.BasicApp;

import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.MyTeamslBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.plate.cart.fragment._CartFragment;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.common.popup.UpdatePopup;
import com.cuci.enticement.plate.common.vm.MainViewModel;
import com.cuci.enticement.plate.home.fragment._HomeFragment;
import com.cuci.enticement.plate.mall.fragment._MallFragment;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.BottomBarView;
import com.cuci.enticement.widget.FitSystemWindowViewPager;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;


import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
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
public class MainActivity extends AppCompatActivity {

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
    private Version  mData;

    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        //Window window = getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //小米手机状态栏字体黑色
        //MIUIStatusBarUtils.MIUISetStatusBarLightMode(this, true);
        //魅族手机状态栏字体黑色
       // if (Rom.check(Rom.ROM_FLYME)) {
         //   FlymeStatusBarColorUtils.setStatusBarDarkIcon(this, true);
       // }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new _HomeFragment());
        adapter.addFragment(new _MallFragment());
        adapter.addFragment(new _CartFragment());
        adapter.addFragment(new _MineFragment());

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        initBottomLayout();
        mBottomLayout.setOnTabClickListener(position -> mViewPager.setCurrentItem(position));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomLayout.setSelected(position);
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
        mViewModel.getVersion("2").observe(this, mUpdateObserver);



        /*UserInfo userInfo = ServiceCreator.getInstance().getUserInfo();

        if (userInfo!=null){
            Set<String> sets1 = new HashSet<>();
            sets1.add("sid" + userInfo.getSid());
            sets1.add("phone" + userInfo.getPhone());
            sets1.add("invitecode" + userInfo.getInviteCode());
            sets1.add("is_colonel" + userInfo.getIsColonel());
            sets1.add("seller_id" + userInfo.getSellerId());
            sets1.add(BuildConfig.DEBUG ? "dev" : "release");
            sets1.add("isLogin1");
            if (SharedPrefUtils.getNewMoney()) {
                sets1.add("income1");
            }
            if (SharedPrefUtils.getNewAgent()) {
                sets1.add("team1");
            }

            Set<String> strings = JPushInterface.filterValidTags(sets1);
            for (String s : strings) {
                FLog.e(TAG, "有效标签："+s);
            }
        }*/





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
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onResume() {
        super.onResume();

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

    private Observer<Status<ResponseBody>> mUpdateObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera( body);
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
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }

    private void operation(Version version) {

        mData = version;


        int ignoreVersion = SharedPrefUtils.getIgnoreVersion();
        int localVersion = AppUtils.getVersionCode(this);
        int serverVersion = mData.getData().getVersionName();

        if (serverVersion == ignoreVersion) {
            return;
        }

       if (serverVersion == localVersion) {
            new XPopup.Builder(this)
                    .dismissOnTouchOutside(false)
                    .dismissOnBackPressed(false)
                    .asCustom(new UpdatePopup(this, version,
                            () -> MainActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this)))
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
    /*    if (mData != null) {
            new XPopup.Builder(this)
                    .dismissOnBackPressed(false)
                    .dismissOnTouchOutside(false)
                    .asCustom(new UpdateProgressPopup(this, mData))
                    .show();
        }*/
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

}

package com.cuci.enticement.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityManager;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.cuci.enticement.bean.Base;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.YiJianLoginBean;
import com.cuci.enticement.event.LoginSucceedEvent;
import com.cuci.enticement.event.ProgoodsEvent;
import com.cuci.enticement.plate.common.LoginActivity;
import com.cuci.enticement.plate.common.eventbus.CartEvent;
import com.cuci.enticement.plate.common.vm.LoginViewModel;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.mob.secverify.OAuthPageEventCallback;
import com.mob.secverify.OperationCallback;
import com.mob.secverify.SecVerify;
import com.mob.secverify.VerifyCallback;
import com.mob.secverify.datatype.VerifyResult;
import com.mob.secverify.exception.VerifyException;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;



public class AppUtils {

    private static LoadingPopupView mLoading;

    /**
     * 判断账号是否登录
     */
    public static boolean isAllowPermission(Context context) {
        UserInfo userInfo = SharedPrefUtils.get(UserInfo.class);
        if (userInfo != null) {
            return true;
        }else {
            mLoading = new XPopup.Builder(context)
                    .dismissOnTouchOutside(false)
                    .asLoading();
            mLoading.show();
            //  MobSDK.submitPolicyGrantResult(true, null);
            //建议提前调用预登录接口，可以加快免密登录过程，提高用户体验
            SecVerify.preVerify(new OperationCallback<Void>() {
                @Override
                public void onComplete(Void data) {
                    //TODO处理成功的结果

                    SecVerify.OtherOAuthPageCallBack(new OAuthPageEventCallback() {
                        @Override
                        public void initCallback(OAuthPageEventResultCallback cb) {
                            cb.pageOpenCallback(new OAuthPageEventCallback.PageOpenedCallback() {
                                @Override
                                public void handle() {
                                    mLoading.dismiss();


                                }
                            });
                            cb.loginBtnClickedCallback(new OAuthPageEventCallback.LoginBtnClickedCallback() {
                                @Override
                                public void handle() {

                                }
                            });
                            cb.agreementPageClosedCallback(new OAuthPageEventCallback.AgreementPageClosedCallback() {
                                @Override
                                public void handle() {

                                }
                            });
                            cb.agreementPageOpenedCallback(new OAuthPageEventCallback.AgreementClickedCallback() {
                                @Override
                                public void handle() {

                                }
                            });
                            cb.cusAgreement1ClickedCallback(new OAuthPageEventCallback.CusAgreement1ClickedCallback() {
                                @Override
                                public void handle() {

                                }
                            });
                            cb.cusAgreement2ClickedCallback(new OAuthPageEventCallback.CusAgreement2ClickedCallback() {
                                @Override
                                public void handle() {

                                }
                            });
                            cb.pageCloseCallback(new OAuthPageEventCallback.PageClosedCallback() {
                                @Override
                                public void handle() {

                                }
                            });
                            cb.checkboxStatusChangedCallback(new CheckboxStatusChangedCallback() {
                                @Override
                                public void handle(boolean b) {

                                }
                            });
                        }
                    });

                    SecVerify.verify(new VerifyCallback() {
                        @Override
                        public void onOtherLogin() {
                            // 用户点击“其他登录方式”，处理自己的逻辑
                            FLog.e("yijian:", "切换账号");
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }

                        @Override
                        public void onUserCanceled() {
                            // 用户点击“关闭按钮”或“物理返回键”取消登录，处理自己的逻辑
                            SecVerify.finishOAuthPage();
                        }

                        @Override
                        public void onComplete(VerifyResult data) {

                            FLog.e("yijian:", data.toString());
                            String token = data.getToken();
                            String opToken = data.getOpToken();
                            String operator = data.getOperator();
                            YiJianLoginBean loginBean = new YiJianLoginBean();
                            loginBean.setType("2");
                            loginBean.setFasttoken(data.getToken());
                            loginBean.setOptoken(data.getOpToken());
                            loginBean.setOperator(data.getOperator());
                            String mloginBean = new Gson().toJson(loginBean);
                            String data1 = RSAUtil.encryptByPublic(context, mloginBean);

                            LoginViewModel mViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(LoginViewModel.class);
                            mViewModel.login(data1).observe((LifecycleOwner) context, mObserver);
                        }

                        @Override
                        public void onFailure(VerifyException e) {
                            //TODO处理失败的结果

                        }
                    });



                }
                @Override
                public void onFailure(VerifyException e) {
                    //TODO处理失败的结果
                      mLoading.dismiss();
                      context.startActivity(new Intent(context, LoginActivity.class));
                }
            });

            return false;
        }
    }
    //版本名
    public static String getVersionName(Context context) {
        PackageInfo p = getPackageInfo(context);
        if (p == null) {
            return "获取版本名失败";
        }
        return p.versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        PackageInfo p = getPackageInfo(context);
        if (p == null) {
            return -1;
        }
        return p.versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断Service是否正在运行
     *
     * @param context     Context
     * @param serviceName Service的包名+类名
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断AccessibilityService服务是否已经启动
     *
     * @param context
     * @param name
     * @return
     */
    public static boolean isStartAccessibilityService(Context context, String name) {

        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);

        if (am == null) {
            return false;
        }

        List<AccessibilityServiceInfo> serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);

        for (AccessibilityServiceInfo info : serviceInfos) {
            String id = info.getId();

            if (name.equals(id)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context     Context
     * @param packageName 包名
     * @return 安装了指定的软件
     */
    public static boolean appExist(Context context, String packageName) {

        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<>();

        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }

        return packageNames.contains(packageName);
    }


    /**
     * 根据包名获取APP的versionCode
     *
     * @param context     Context
     * @param packageName 包名
     * @return versionCode
     */
    public static int getVersionCode(Context context, String packageName) {

        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);

        for (PackageInfo info : infos) {

            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }

            if (packageName.equals(info.packageName)) {
                return info.versionCode;
            }

        }

        return -1;
    }

    /**
     * 根据包名获取APP的versionName
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getVersionName(Context context, String packageName) {

        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);

        for (PackageInfo info : infos) {

            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }

            if (packageName.equals(info.packageName)) {
                return info.versionName;
            }

        }

        return null;
    }


    private static Observer<Status<Base<UserInfo>>> mObserver = new Observer<Status<Base<UserInfo>>>() {

        @Override
        public void onChanged(Status<Base<UserInfo>> baseStatus) {
            switch (baseStatus.status) {
                case Status.LOADING:

                    break;
                case Status.ERROR:


                    FToast.error("网络错误");
                    break;
                case Status.SUCCESS:

                    if (baseStatus.content == null) {
                        FToast.error("数据异常");
                        return;
                    }
                    if (baseStatus.content.code == 1) {
                        String s = new Gson().toJson(baseStatus.content.data);
                        UserInfo userInfo = baseStatus.content.data;
                        FToast.success("登录成功");
                        SharedPrefUtils.save(userInfo, UserInfo.class);
                        //登录成功后发广播刷新，此次改成eventbus，原先的先不删除
                      /*  Intent intent = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);
                        intent.putExtra(_MineFragment.DATA_USER_INFO, userInfo);
                        LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);*/
                        //eventbus  刷新视图  四个fragment重新载入

                        EventBus.getDefault().post(new LoginSucceedEvent());
                        //关闭详情
                        EventBus.getDefault().post(new ProgoodsEvent());
                        //刷新购物车数据

                        EventBus.getDefault().post(new CartEvent(CartEvent.REFRESH_CART_LIST));
                        /*LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(LoginActivity.this);
                        broadcastManager.sendBroadcast(new Intent(ACTION_REFRESH_DATA));*/
                        SecVerify.finishOAuthPage();
                    } else {
                        FToast.error(baseStatus.content.info);
                    }
                    break;
            }
        }
    };
}

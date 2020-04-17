package com.cuci.enticement.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.bean.EssayBean;
import com.cuci.enticement.bean.PushBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.CheckHomeEvent;
import com.cuci.enticement.plate.common.Agreement2Activity;
import com.cuci.enticement.plate.common.AgreementActivity;
import com.cuci.enticement.plate.common.DailyActivity;
import com.cuci.enticement.plate.common.LauncherActivity;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.plate.mine.activity.AchievementActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.NoticeActivity;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;

public class GeTuiNotificationClickReceiver extends BroadcastReceiver {
    private static final String TAG = "GeTuiNotificationClick";
    @Override
    public void onReceive(Context context, Intent intent) {
        PushBean message = (PushBean) intent.getSerializableExtra("message");
        if (message != null) {
            UserInfo  mUserInfo = SharedPrefUtils.get(UserInfo.class);
            if (message.getType()==0) {
                Log.d(TAG, "onReceiveGeTuiType1:");
                Intent intentProd = new Intent(context, MainActivity.class);
                startIntent( context,intentProd);
                //切换首页
                EventBus.getDefault().post(new CheckHomeEvent());
            } else if (message.getType()==1) {
                Intent intentProd = new Intent(context, ProdActivity.class);
                intentProd.putExtra("bannerData", message.getId());
                startIntent( context,intentProd);
            }else if (message.getType()==2) {
                HomeViewModel  mViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(HomeViewModel.class);
                mViewModel.essay(message.getId(), "2", "" + AppUtils.getVersionCode(context)).observe((LifecycleOwner) context, essayObserver);
            }else if (message.getType()==3) {
                Intent intentProd = new Intent(context, Agreement2Activity.class);
                intentProd.putExtra("url", message.getId());
                startIntent( context,intentProd);
            }else if (message.getType()==4) {
                    Intent intentProd = new Intent(context, AchievementActivity.class);
                    startIntent( context,intentProd);
            }else if (message.getType()==5) {
                    Intent intentProd = new Intent(context, DailyActivity.class);
                    startIntent( context,intentProd);
            }
        }

    }

    private Observer<Status<ResponseBody>> essayObserver = status -> {

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
            EssayBean mEssayBean = new Gson().fromJson(b, EssayBean.class);
            if (mEssayBean.getCode() == 1) {
                Intent intentProd = new Intent(BasicApp.getContext(), AgreementActivity.class);
                intentProd.putExtra("bannerData", mEssayBean.getData().getContent());
                intentProd.putExtra("share_info", mEssayBean.getData().getShare_info());
                startIntent( BasicApp.getContext(),intentProd);
            } else {
                FToast.error(mEssayBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


   /* private void startIntent(Context context, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (PushHelper.isAppAlive(context, context.getPackageName())) {
            context.startActivity(intent);
            FLog.e("app_start","11111111111111111111");
        } else {
            Intent[] intents = new Intent[]{
                    new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                    intent
            };
            context.startActivities(intents);
            FLog.e("app_end","00000000000000000000");
        }
    }*/
    private void startIntent(Context context, Intent intent) {
            Intent[] intents = new Intent[]{
                    new Intent(Intent.ACTION_MAIN, Uri.EMPTY, context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                    intent
            };
            context.startActivities(intents);
            FLog.e("app_end","00000000000000000000");

    }
}

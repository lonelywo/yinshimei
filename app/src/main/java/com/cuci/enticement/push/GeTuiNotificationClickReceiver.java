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
import com.cuci.enticement.plate.common.eventbus.EssayEvent;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.home.vm.HomeViewModel;
import com.cuci.enticement.plate.mine.activity.AchievementActivity;
import com.cuci.enticement.plate.mine.activity.KaQuanActivity;
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
            int i=0;
            UserInfo  mUserInfo = SharedPrefUtils.get(UserInfo.class);
            Log.d(TAG, "onReceiveGeTuiType1:"+i++);
            if (message.getType()==0) {

                Intent intentProd = new Intent(context, MainActivity.class);
                startIntent( context,intentProd);
                //切换首页
                EventBus.getDefault().post(new CheckHomeEvent());
            } else if (message.getType()==1) {
                Intent intentProd = new Intent(context, ProdActivity.class);
                intentProd.putExtra("bannerData", message.getId());
                startIntent( context,intentProd);
            }else if (message.getType()==2) {
                //跳转文章
                EventBus.getDefault().post(new EssayEvent(message.getId()));

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
            else if (message.getType()==6) {
                Intent intentProd = new Intent(context, KaQuanActivity.class);
                startIntent( context,intentProd);
            }
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

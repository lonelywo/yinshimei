package com.cuci.enticement.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.cuci.enticement.bean.PushBean;
import com.cuci.enticement.event.CheckHomeEvent;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.NoticeActivity;
import com.cuci.enticement.utils.FLog;
import org.greenrobot.eventbus.EventBus;

public class GeTuiNotificationClickReceiver extends BroadcastReceiver {
    private static final String TAG = "GeTuiNotificationClick";
    @Override
    public void onReceive(Context context, Intent intent) {
        PushBean message = (PushBean) intent.getSerializableExtra("message");
        if (message != null) {

            if (message.getType()==1) {
                Log.d(TAG, "onReceiveGeTuiType1:");
                Intent intentProd = new Intent(context, MainActivity.class);
                startIntent( context,intentProd);
                //切换首页
                EventBus.getDefault().post(new CheckHomeEvent());
            } else if (message.getType()==2) {
                Intent intentProd = new Intent(context, ProdActivity.class);
                intentProd.putExtra("bannerData", message.getId());
                startIntent( context,intentProd);
            }else if (message.getType()==3) {
                Intent intentProd = new Intent(context, NoticeActivity.class);
                startIntent( context,intentProd);
            }else if (message.getType()==4) {
                Intent intentProd = new Intent(context, MyTeamActivity.class);
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

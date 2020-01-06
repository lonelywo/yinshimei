package com.cuci.enticement.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.cuci.enticement.bean.PushBean;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.NoticeActivity;


public class GeTuiNotificationClickReceiver extends BroadcastReceiver {
    private static final String TAG = "GeTuiNotificationClick";
    @Override
    public void onReceive(Context context, Intent intent) {
        PushBean message = (PushBean) intent.getSerializableExtra("message");
        if (message != null) {

            if (message.getType()==1) {
                Log.d(TAG, "onReceiveGeTuiType1:");
            } else if (message.getType()==2) {
                Intent intentProd = new Intent(context, ProdActivity.class);
                intentProd.putExtra("bannerData", message.getId());
                context.startActivity( intentProd);
            }else if (message.getType()==3) {
                Intent intentProd = new Intent(context, NoticeActivity.class);
                context.startActivity( intentProd);
            }else if (message.getType()==4) {
                Intent intentProd = new Intent(context, MyTeamActivity.class);
                context.startActivity( intentProd);
            }
        }

    }


}

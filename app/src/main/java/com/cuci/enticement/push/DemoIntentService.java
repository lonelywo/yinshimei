package com.cuci.enticement.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.PushBean;
import com.cuci.enticement.event.CheckHomeEvent;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.mine.activity.MyTeamActivity;
import com.cuci.enticement.plate.mine.activity.NoticeActivity;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.BindAliasCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    private static final String TAG = "GetuiSdkDemo";

    public static String CALENDAR_ID = "channel_android";

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    // 处理透传消息
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        // 透传消息的处理方式，详看SDK demo
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();


        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));

        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg
                + "\ncid = " + cid);

        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);
            try {
                PushBean function = new Gson().fromJson(data, PushBean.class);
               // addNotification(function.getTitle(),  function.getContent(), function);
                if (function.getType()==1) {
                    Intent intentProd = new Intent(context, MainActivity.class);
                    intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intentProd);
                    //切换首页
                    EventBus.getDefault().post(new CheckHomeEvent());
                } else if (function.getType()==2) {
                    Intent intentProd = new Intent(context, ProdActivity.class);
                    intentProd.putExtra("bannerData", function.getId());
                    intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity( intentProd);
                }else if (function.getType()==3) {
                    Intent intentProd = new Intent(context, NoticeActivity.class);
                    intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity( intentProd);
                }else if (function.getType()==4) {
                    Intent intentProd = new Intent(context, MyTeamActivity.class);
                    intentProd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity( intentProd);
                }
            } catch (JsonSyntaxException e) {
                FToast.error("数据有误");
            }
        }
        Log.d(TAG, "----------------------------------------------------------------------------------------------");
    }

    // 接收 cid
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    // cid 离线上线通知
    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    // 各种事件处理回执
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult -> " + cmdMessage);
        int action = cmdMessage.getAction();

        if (action == PushConsts.SET_TAG_RESULT) {
         //   setTagResult((SetTagCmdMessage) cmdMessage);
        } else if(action == PushConsts.BIND_ALIAS_RESULT) {
            bindAliasResult((BindAliasCmdMessage) cmdMessage);
        } else if (action == PushConsts.UNBIND_ALIAS_RESULT) {
        //    unbindAliasResult((UnBindAliasCmdMessage) cmdMessage);
        } else if ((action == PushConsts.THIRDPART_FEEDBACK)) {
        //    feedbackResult((FeedbackCmdMessage) cmdMessage);
        }
    }

    // 通知到达，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
    }

    // 通知点击，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
    }

    private void bindAliasResult(BindAliasCmdMessage bindAliasCmdMessage) {
        String sn = bindAliasCmdMessage.getSn();
        String code = bindAliasCmdMessage.getCode();

        int text = R.string.bind_alias_unknown_exception;
        switch (Integer.valueOf(code)) {
            case PushConsts.BIND_ALIAS_SUCCESS:
                text = R.string.bind_alias_success;
                break;
            case PushConsts.ALIAS_ERROR_FREQUENCY:
                text = R.string.bind_alias_error_frequency;
                break;
            case PushConsts.ALIAS_OPERATE_PARAM_ERROR:
                text = R.string.bind_alias_error_param_error;
                break;
            case PushConsts.ALIAS_REQUEST_FILTER:
                text = R.string.bind_alias_error_request_filter;
                break;
            case PushConsts.ALIAS_OPERATE_ALIAS_FAILED:
                text = R.string.bind_alias_unknown_exception;
                break;
            case PushConsts.ALIAS_CID_LOST:
                text = R.string.bind_alias_error_cid_lost;
                break;
            case PushConsts.ALIAS_CONNECT_LOST:
                text = R.string.bind_alias_error_connect_lost;
                break;
            case PushConsts.ALIAS_INVALID:
                text = R.string.bind_alias_error_alias_invalid;
                break;
            case PushConsts.ALIAS_SN_INVALID:
                text = R.string.bind_alias_error_sn_invalid;
                break;
            default:
                break;

        }

        Log.d(TAG, "bindAlias result sn = " + sn + ", code = " + code + ", text = " + getResources().getString(text));

    }
    /**
     * 个推透传创建通知栏
     *
     * @param title
     * @param subtitle
     */
    private void addNotification(String title, String subtitle, PushBean message) {
        //显示不重复通知
        int requestCode = (int) System.currentTimeMillis();

        Intent broadcastIntent = new Intent(this, GeTuiNotificationClickReceiver.class);
      //  broadcastIntent.setComponent(new ComponentName("com.cuci.enticement","com.cuci.enticement.push.GeTuiNotificationClickReceiver"));
        broadcastIntent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.
                getBroadcast(this, requestCode, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification  notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("2", "推送通知",
                    NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
          notification = new NotificationCompat.Builder(this,"2")
         .setSmallIcon(R.drawable.push_small)
          .setWhen(System.currentTimeMillis())
           .setContentTitle(title)
          .setContentText(subtitle)
          .setContentIntent(pendingIntent)
                  .setAutoCancel(true)
                 /* .setStyle(new NotificationCompat.BigTextStyle()
                  .setBigContentTitle("哈哈哈哈哈哈")
                  .bigText("就撒谎发还是分开后肯定会发生的接口方式的回复啥地方还是得合计罚款合适的机会当地噶是几个大花洒管好的大家大家爱好的哈就好打哈好大好大打卡机读卡接口的金卡和扩大和客户的刷卡")
                  )*/
                  .build();
        }else{
        notification = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.push_small)
          .setWhen(System.currentTimeMillis())
             .setContentTitle(title)
         .setContentText(subtitle)
          .setContentIntent(pendingIntent)
                .setAutoCancel(true)
          .build();
        }

        /*builder.setLargeIcon( BitmapFactory.decodeResource
                ( getResources (),R.drawable.push ));*/
        Log.d(TAG, "addNotification: .......................1");
        if (manager != null) {
            manager.notify(requestCode,notification);
            Log.d(TAG, "addNotification: .......................2");
        }
    }

    /**
     * 获取主工程mipmap下的资源文件名
     */
    public static String getIconName(Context mContext) {
        String value = "";
        try {
            ApplicationInfo appInfo = mContext.getPackageManager().
                    getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString("ic_launcher_round");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }



}

package com.cuci.enticement;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.ImageView;


import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.cuci.enticement.bean.WxPayBean;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.ChatManager;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.model.MessageHelper;
import com.hyphenate.helpdesk.util.Log;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.List;


public class BasicApp extends Application {

    private static final String TAG = BasicApp.class.getSimpleName();

    private static BasicApp mContext;
    private static AppExecutors mAppExecutors;

    private static IWXAPI mIWXAPI;


    /**
     * kefuChat.MessageListener
     */
    protected ChatManager.MessageListener messageListener = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mAppExecutors = new AppExecutors();
        //数据库LitePal初始化
        LitePal.initialize(this);


        //注册微信分享，第三个参数为是否检查signature，正式发布改为true
        mIWXAPI = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, !BuildConfig.DEBUG);
        mIWXAPI.registerApp(Constant.WX_APP_ID);

      //严格模式下，会调用 检测FileUriExposure
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("1484190905068246#kefuchannelapp74235");//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId("74235");//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”

        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);
        //后面可以设置其他属性

        //注册消息事件监听
        registerEventListener();

    }
    /**
     * 全局事件监听
     * 因为可能会有UI页面先处理到这个消息，所以一般如果UI页面已经处理，这里就不需要再次处理
     * activityList.size() <= 0 意味着所有页面都已经在后台运行，或者已经离开Activity Stack
     */
    protected void registerEventListener(){
        messageListener = new ChatManager.MessageListener(){

            @Override
            public void onMessage(List<Message> msgs) {
                //刷新小角标状态

                    Intent intent = new Intent(_MineFragment.ACTION_REFRESH_HX);
                    intent.putExtra("data",msgs.size());
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);


                for (Message message : msgs){
                    Log.d(TAG, "onMessageReceived id : " + message.messageId());

                    //这里全局监听通知类消息,通知类消息是通过普通消息的扩展实现
                    if (MessageHelper.isNotificationMessage(message)){
                        // 检测是否为留言的通知消息
                        String eventName = getEventNameByNotification(message);
                        if (!TextUtils.isEmpty(eventName)){
                            if (eventName.equals("TicketStatusChangedEvent") || eventName.equals("CommentCreatedEvent")){
                                // 检测为留言部分的通知类消息,刷新留言列表
                                JSONObject jsonTicket = null;
                                try{
                                    jsonTicket = message.getJSONObjectAttribute("weichat").getJSONObject("event").getJSONObject("ticket");
                                }catch (Exception ignored){}
                              //  ListenerManager.getInstance().sendBroadCast(eventName, jsonTicket);
                            }
                        }
                    }

                }
            }

            @Override
            public void onCmdMessage(List<Message> msgs) {
                for (Message message : msgs){
                    Log.d(TAG, "收到透传消息");
                    //获取消息body
                    EMCmdMessageBody cmdMessageBody = (EMCmdMessageBody) message.body();
                    String action = cmdMessageBody.action(); //获取自定义action
                    Log.d(TAG, String.format("透传消息: action:%s,message:%s", action, message.toString()));

                }
            }

            @Override
            public void onMessageStatusUpdate() {

            }

            @Override
            public void onMessageSent() {

            }
        };

        ChatClient.getInstance().chatManager().addMessageListener(messageListener);
    }
    /**
     * 获取EventName
     * @param
     * @return
     */
    public String getEventNameByNotification(Message message){

        try {
            JSONObject weichatJson = message.getJSONObjectAttribute("weichat");
            if (weichatJson != null && weichatJson.has("event")) {
                JSONObject eventJson = weichatJson.getJSONObject("event");
                if (eventJson != null && eventJson.has("eventName")){
                    return eventJson.getString("eventName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IWXAPI getIWXAPI() {
        return mIWXAPI;
    }
    public static AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    public static BasicApp getContext() {
        return mContext;
    }

}

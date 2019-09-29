package com.cuci.enticement.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.utils.FToast;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
    	api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}


    @Override
	public void onResp(BaseResp resp) {
		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            switch (resp.errCode) {
                case 0://支付成功
                    FToast.success("支付成功");
                    //支付成功后，1刷新订单列表页和  2个人中心状态  3并关闭  订单详情或者预定单页面

                    //1 刷新外层
                    EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                    //2  刷新小角标状态

                    Intent intent= new Intent(_MineFragment.ACTION_REFRESH_STATUS);
                    LocalBroadcastManager.getInstance(BasicApp.getContext()).sendBroadcast(intent);

                    //3  跳转订单页面--全部

                    startActivity(new Intent(WXPayEntryActivity.this, MyOrderActivity.class));

                    //4  关闭页面

                    EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.FINISH_ACTIVITY));




                    Log.d(TAG, "onResp: resp.errCode = 0   支付成功");
                    break;
                case -1://错误，可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    FToast.error("支付失败");
                    Log.d(TAG, "onResp: resp.errCode = -1  支付失败");
                    EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.FINISH_ACTIVITY));

                    break;
                case -2://用户取消，无需处理。发生场景：用户不支付了，点击取消，返回APP。
                    Log.d(TAG, "onResp: resp.errCode = -2  用户取消");
                    FToast.warning("支付取消");


                    //跳转订单页面--全部

                    startActivity(new Intent(WXPayEntryActivity.this, MyOrderActivity.class));

                    //关闭预定单或订单详情页
                    EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.FINISH_ACTIVITY));


                    break;

            }

            finish();//这里需要关闭该页面
        }
	}
}
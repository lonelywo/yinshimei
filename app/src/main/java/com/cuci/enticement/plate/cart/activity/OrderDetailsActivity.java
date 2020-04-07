package com.cuci.enticement.plate.cart.activity;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.OrderCancel;
import com.cuci.enticement.bean.OrderConfirm;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderPay;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxPayBean;
import com.cuci.enticement.bean.ZFBBean;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.common.popup.PayBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.activity.DaiFaHuoTuiKuanActivity;
import com.cuci.enticement.plate.mine.activity.TuiTypeActivity;
import com.cuci.enticement.plate.mine.adapter.ItemProdDetailsViewBinder;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.PayResult;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.lxj.xpopup.XPopup;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;


/**
 * 订单详情页
 */
public class OrderDetailsActivity extends BaseActivity implements ItemProdDetailsViewBinder.OnProdClickListener {


    @BindView(R.id.text_zhuangtai)
    TextView textZhuangtai;

    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;

    public static final int CANCEL_STATUS = 0;//取消
    public static final int NEED_PAY = 2;//待支付
    public static final int NEED_EXPRESS = 3;//待发货
    public static final int NEED_CONFIRM = 4;//待收货
    public static final int HAS_FINISH = 5;//已完成
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.con_buju1)
    ConstraintLayout conBuju1;
    @BindView(R.id.con_buju3)
    ConstraintLayout conBuju3;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.tv_fuzhi)
    TextView tvFuzhi;
    @BindView(R.id.tv_yhj_money)
    TextView tvYhjMoney;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.con_zhuangtai)
    ConstraintLayout conZhuangtai;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.tv_zuileft)
    TextView tvZuileft;


    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;

    private int mPayType = 2;//默认支付宝付款

    private AllOrderList.DataBean.ListBeanX mInfo;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private int mStatus;
    private int refund_state;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderEventMessage(OrderEvent event) {
        if (event.getCode() == OrderEvent.FINISH_ACTIVITY) {
            finish();
        }


    }


    @Override
    public int getLayoutId() {
        return R.layout.order_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");
        List<OrderGoods> items = mInfo.getList();

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo == null) {
            return;
        }
        mStatus = mInfo.getStatus();
        refund_state = mInfo.getRefund_state();
        initViewStatus(mStatus);
        initContent();
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mItems.addAll(items);
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(OrderGoods.class, new ItemProdDetailsViewBinder(this, mStatus,refund_state));


       /* OrderItemDecoration mDecoration = new OrderItemDecoration(this, 4);

        mRecyclerView.addItemDecoration(mDecoration);*/
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        tvFuzhi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // 从API11开始android推荐使用android.content.ClipboardManager
// 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 将文本内容放到系统剪贴板里。
                long order_no = mInfo.getOrder_no();
                cm.setText("" + order_no);
                FToast.success("订单编号复制成功");
            }
        });
    }

    private void initContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(mInfo.getExpress_name()).append(" ")
                .append(mInfo.getExpress_phone()).append(" ").append("\n")
                .append(mInfo.getExpress_province()).append(" ")
                .append(mInfo.getExpress_city()).append(" ")
                .append(mInfo.getExpress_area()).append(" ")
                .append(mInfo.getExpress_address());
        tvOrderNo.setText(String.format(Locale.CHINA, "订单编号: %s", mInfo.getOrder_no()));
        textDizi.setText(sb.toString());
        tvGoodsMoney.setText(String.format(Locale.CHINA, "¥%s", MathExtend.moveone(mInfo.getPrice_goods())));
        tvExpress.setText(String.format(Locale.CHINA, "¥%s", MathExtend.moveone(mInfo.getPrice_express())));
        tvTotalMoney.setText(String.format(Locale.CHINA, "¥%s", MathExtend.moveone(mInfo.getPrice_total())));
        tvCreateTime.setText(mInfo.getCreate_at());
        tvYhjMoney.setText("-¥" +MathExtend.moveone(mInfo.getDiscount_price()));

    }

    private void initViewStatus(int status) {


        if (status == 0) {
            //已取消          重新购买
            ViewUtils.hideView(tvZuileft);
            ViewUtils.hideView(tvLeft);
            ViewUtils.hideView(tvRight);
            ViewUtils.hideView(bottom);
            textZhuangtai.setText("交易关闭");
            tvShifu.setText("待付款");
        } else if (status == 2) {
            //待付款  取消订单  立即支付
            ViewUtils.hideView(tvZuileft);
            ViewUtils.showView(tvLeft);
            ViewUtils.showView(tvRight);
            ViewUtils.showView(bottom);
            tvLeft.setText("取消订单");
            tvRight.setText("立即付款");
            textZhuangtai.setText("待付款");
            tvShifu.setText("待付款");
        } else if (status == 3) {
            //待发货
            ViewUtils.showView(tvZuileft);
            ViewUtils.hideView(tvLeft);
            ViewUtils.hideView(tvRight);
            ViewUtils.showView(bottom);
            textZhuangtai.setText("待发货");
            tvShifu.setText("实付款");
        } else if (status == 4) {
            //待收货  查看物流  确认收货
            ViewUtils.showView(tvZuileft);
            ViewUtils.showView(tvLeft);
            ViewUtils.showView(tvRight);
            ViewUtils.showView(bottom);
            tvLeft.setText("查看物流");
            tvRight.setText("确认收货");
            textZhuangtai.setText("待收货");
            tvShifu.setText("实付款");
        } else if (status == 5) {
            //已完成  查看物流
            ViewUtils.hideView(tvZuileft);
            ViewUtils.showView(tvLeft);
            ViewUtils.showView(tvRight);
            ViewUtils.showView(bottom);
            tvLeft.setText("全部退款");
            tvRight.setText("联系客服");
            textZhuangtai.setText("交易成功");
            tvShifu.setText("实付款");
        } else if (status == 6) {
            if(refund_state==0||refund_state==1){
                textZhuangtai.setText("退款中");
            }else {
                textZhuangtai.setText("退款成功");
            }
            //已退货  查看物流
            ViewUtils.hideView(tvZuileft);
            ViewUtils.hideView(tvLeft);
            ViewUtils.hideView(tvRight);
            ViewUtils.hideView(bottom);

            tvShifu.setText("实付款");
        }
    }

    @OnClick({R.id.image_back,R.id.tv_zuileft, R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_zuileft:
                if(mStatus == 3 ){
                    Intent intent = new Intent(this, DaiFaHuoTuiKuanActivity.class);
                    intent.putExtra("intentInfo",mInfo);
                    startActivity(intent);
                }else if(mStatus == 4||mStatus == 5){
                    Intent intent_tui = new Intent(this, TuiTypeActivity.class);
                    intent_tui.putExtra("intentInfo",mInfo);
                    startActivity(intent_tui);
                }

                break;
            case R.id.tv_left:
                if (mStatus == 2 ) {
                    //待支付  取消按钮
                    new XPopup.Builder(OrderDetailsActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(OrderDetailsActivity.this,
                                    "您确定要取消订单吗？", "取消", "确定", () -> {
                                mViewModel.orderCancel(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(mInfo.getOrder_no()), "" + AppUtils.getVersionCode(BasicApp.getContext()))
                                        .observe(this, mCancelObserver);
                            }))
                            .show();


                } else if (mStatus == 4 ) {
                    //查看物流  intent
                    Intent intent = new Intent(OrderDetailsActivity.this, LogisticsActivity.class);
                    intent.putExtra("express_no", String.valueOf(mInfo.getExpress_send_no()));
                    intent.putExtra("express_code", String.valueOf(mInfo.getExpress_company_code()));
                    intent.putExtra("express_company_title", String.valueOf(mInfo.getExpress_company_title()));
                    startActivity(intent);
                }else if(mStatus == 5){
                   //申请售后
                    Intent intent_tui = new Intent(this, TuiTypeActivity.class);
                    intent_tui.putExtra("intentInfo",mInfo);
                    startActivity(intent_tui);
                }
                break;
            case R.id.tv_right:
                //提交订单，成功后，去调用获取支付参数接口
                if (mStatus == 2) {
                    //待支付  弹框选择支付
                    new XPopup.Builder(OrderDetailsActivity.this)
                            .dismissOnTouchOutside(true)
                            .dismissOnBackPressed(true)
                            .asCustom(new PayBottom2TopProdPopup(OrderDetailsActivity.this, mInfo.getPrice_total(), type -> {

                                mPayType = type;
                                mViewModel.getOrderPay(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),
                                        String.valueOf(mInfo.getOrder_no()), String.valueOf(type), "" + AppUtils.getVersionCode(BasicApp.getContext()))
                                        .observe(this, mPayObserver);

                            }))
                            .show();


                } else if (mStatus == 4) {
                    //确认收货  弹框确认收货


                    new XPopup.Builder(OrderDetailsActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(OrderDetailsActivity.this,
                                    "您确定要收货吗？", "取消", "确定", () -> {
                                mViewModel.orderConfirm(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),
                                        String.valueOf(mInfo.getOrder_no()), "" + AppUtils.getVersionCode(BasicApp.getContext()))
                                        .observe(this, mConfirmObserver);
                            }))
                            .show();


                }else if(mStatus == 5){
                  //联系客服
                    if (ChatClient.getInstance().isLoggedInBefore()) {
                        //已经登录，可以直接进入会话界面
                        Intent intent = new IntentBuilder(this)
                                .setServiceIMNumber("kefuchannelimid_269943")
                                .setTitleName("美美")
                                .build();
                        startActivity(intent);
                    } else {
                        //未登录，需要登录后，再进入会话界面
                        FToast.error("联系客服失败，请退出重新登录");
                    }
                }


                break;
        }
    }


    /**
     * 获取支付参数接口
     */
    private Observer<Status<ResponseBody>> mPayObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;

                try {
                    String result = body.string();

                    if (mPayType == 2) {
                        ZFBBean orderPay = new Gson().fromJson(result, ZFBBean.class);
                        if (orderPay.getCode() == 1) {

                            sendReq2ZFB(orderPay.getData());

                        } else if (orderPay.getCode() == HttpUtils.CODE_INVALID) {
                            HttpUtils.Invalid(this);
                            finish();
                            FToast.error(orderPay.getInfo());
                        } else {
                            FToast.error(orderPay.getInfo());
                        }

                    } else if (mPayType == 1) {
                        OrderPay orderPay = new Gson().fromJson(result, OrderPay.class);
                        if (orderPay.getCode() == 1) {
                            OrderPay.DataBean data = orderPay.getData();
                            String appid = data.getAppid();
                            String prepayid = data.getPrepayid();
                            String sign = data.getSign();
                            String timestamp = data.getTimestamp();
                            String partnerid = data.getPartnerid();
                            String noncestr = data.getNoncestr();
                            String packageX = data.getPackageX();
                            //weixin

                            WxPayBean wxPayBean = new WxPayBean();
                            wxPayBean.setAppId(appid);
                            wxPayBean.setPrepayId(prepayid);
                            wxPayBean.setPartnerId(partnerid);
                            wxPayBean.setNonceStr(noncestr);
                            wxPayBean.setPaySign(sign);
                            wxPayBean.setTimestamp(timestamp);
                            wxPayBean.setPackageX(packageX);
                            sendReq2WX(wxPayBean);

                        } else if (orderPay.getCode() == HttpUtils.CODE_INVALID) {
                            HttpUtils.Invalid(this);
                            finish();
                            FToast.error(orderPay.getInfo());
                        } else {
                            FToast.error(orderPay.getInfo());
                        }


                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);

                break;
        }
    };


    /**
     * 确认收货
     */
    private Observer<Status<ResponseBody>> mConfirmObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;


                try {
                    String result = body.string();
                    OrderConfirm orderConfirm = new Gson().fromJson(result, OrderConfirm.class);

                    if (orderConfirm.getCode() == 1) {

                        FToast.success(orderConfirm.getInfo());
                        mStatus = HAS_FINISH;
                        initViewStatus(HAS_FINISH);

                        //刷新外层
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


                        //切换全部订单
                        /*EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_YIWANCHENG));
                        finish();*/

                    } else if (orderConfirm.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(orderConfirm.getInfo());
                    } else {
                        FToast.error(orderConfirm.getInfo());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);

                break;
        }
    };


    private Observer<Status<ResponseBody>> mCancelObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    OrderCancel orderCancel = new Gson().fromJson(result, OrderCancel.class);
                    if (orderCancel.getCode() == 1) {
                        //刷新外层
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


                        FToast.success(orderCancel.getInfo());
                        //取消订单 刷新头部详情页状态
                        mStatus = 0;

                        initViewStatus(CANCEL_STATUS);

                        //切换全部订单
                       /* EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_MY_ORDER));
                        finish();*/
                    } else if (orderCancel.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(orderCancel.getInfo());
                    } else {
                        FToast.error(orderCancel.getInfo());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);

                break;
        }
    };


    /**
     * 调支付的方法
     * <p>
     * 注意： app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。
     *
     * @param oInfo
     */
//支付宝oInfo参数，以后台返回为准
    private void sendReq2ZFB(String oInfo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //1、生成订单数据
                //2、支付
                PayTask payTask = new PayTask(OrderDetailsActivity.this);
				  /*参数1：订单信息
	                参数2：表示在支付钱包显示之前，true会显示一个dialog提示用户表示正在唤起支付宝钱包
				    返回值：
					就是同步返回的支付结果（在实际开发过程中，不应该以此同步结果作为支付成功的依据。以异步结果作为成功支付的依据）
										                 */
                Map<String, String> result = payTask.payV2(oInfo, true);
                Message message = mHandler.obtainMessage();
                message.what = SDK_PAY_FLAG;
                message.obj = result;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 调支付的方法
     * <p>
     * 注意： 每次调用微信支付的时候都会校验 appid 、包名 和 应用签名的。 这三个必须保持一致才能够成功调起微信
     *
     * @param wxPayBean
     */
    //这个WxPayBean以后台返回为准,这里是我手动拿接口文档里生成的
    private void sendReq2WX(WxPayBean wxPayBean) {

        //这里的appid，替换成自己的即可
       /* IWXAPI api = WXAPIFactory.createWXAPI(BasicApp.getContext(), Constant.WX_APP_ID);
        api.registerApp(Constant.WX_APP_ID);*/

        //这里的bean，是服务器返回的json生成的bean
        PayReq payRequest = new PayReq();
        payRequest.appId = wxPayBean.getAppId();
        payRequest.partnerId = wxPayBean.getPartnerId();//这里参数也需要，目前没有就屏蔽了
        payRequest.prepayId = wxPayBean.getPrepayId();//这里参数也需要，目前没有就屏蔽了
        payRequest.packageValue = "Sign=WXPay";//固定值
        payRequest.nonceStr = wxPayBean.getNonceStr();
        payRequest.timeStamp = wxPayBean.getTimestamp();
        payRequest.sign = wxPayBean.getPaySign();

        //发起请求，调起微信前去支付
        BasicApp.getIWXAPI().sendReq(payRequest);
    }


    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        FToast.success("支付成功");


                        //刷新外层
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(OrderDetailsActivity.this).sendBroadcast(intent);

                        //刷新is_new
                        EventBus.getDefault().post(new IsnewEvent());
                        //切换全部订单
                        //EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_MY_ORDER));
                        //跳转支付成功页面
                        startActivity(new Intent(OrderDetailsActivity.this, PayOfterActivity.class));

                        finish();

                    } else {

                        if (TextUtils.equals(resultStatus, "6001")) {
                            FToast.warning("支付取消");

                            //切换全部订单
                            EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_MY_ORDER));

                            finish();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            FToast.error("支付失败");
                            finish();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    @Override
    public void onProdClick(OrderGoods item) {
        if(mStatus==3){
            Intent intent = new Intent(this, DaiFaHuoTuiKuanActivity.class);
            intent.putExtra("intentItem",item);
            startActivity(intent);
        }else if(mStatus==4||mStatus==5){
            Intent intent = new Intent(this, TuiTypeActivity.class);
            intent.putExtra("intentItem",item);
            startActivity(intent);
        }else if(mStatus==6){
            if(refund_state==0||refund_state==1){
                Intent intent = new Intent(this, TuiKuanDetails2Activity.class);
                intent.putExtra("id", ""+item.getId());
                startActivity(intent);
            }else {
                Intent intent = new Intent(this, TuiKuanDetails3Activity.class);
                intent.putExtra("id", ""+item.getId());
                startActivity(intent);
            }
        }


    }

    @Override
    public void onProdItemClick(OrderGoods item) {

      //  startActivity(new Intent(this, TuiKuanDetailsActivity.class));
    }
}

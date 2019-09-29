package com.cuci.enticement.plate.cart.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.CommitOrder;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.OrderCancel;
import com.cuci.enticement.bean.OrderConfirm;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderPay;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxPayBean;
import com.cuci.enticement.bean.ZFBBean;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.common.popup.PayBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.mine.adapter.ItemBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemProdViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemTitleViewBinder;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.PayResult;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;


/**
 * 订单详情页
 */
public class OrderDetailsActivity extends BaseActivity {


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

    public static final int CANCEL_STATUS=0;//取消
    public static final int NEED_PAY=2;//待支付
    public static final int NEED_EXPRESS=3;//待发货
    public static final int NEED_CONFIRM=4;//待收货
    public static final int HAS_FINISH=5;//已完成



    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;

    private int mPayType = 2;//默认支付宝付款

    private AllOrderList.DataBean.ListBeanX mInfo;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private int mStatus;



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

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onOrderEventMessage(OrderEvent event) {
        if(event.getCode()==OrderEvent.FINISH_ACTIVITY){
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
        mStatus=mInfo.getStatus();
        initViewStatus(mStatus);
        initContent();
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mItems.addAll(items);
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(OrderGoods.class, new ItemProdViewBinder());


        OrderItemDecoration mDecoration = new OrderItemDecoration(this, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);




        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

    }

    private void initContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(mInfo.getExpress_name()).append(" ")
                .append(mInfo.getExpress_phone()).append(" ").append("\n")
                .append(mInfo.getExpress_province()).append(" ")
                .append(mInfo.getExpress_city()).append(" ")
                .append(mInfo.getExpress_area()).append(" ")
                .append(mInfo.getExpress_address());
        tvOrderNo.setText(String.format(Locale.CHINA,"订单编号: %s",mInfo.getOrder_no()));
        textDizi.setText(sb.toString());
        tvGoodsMoney.setText(String.format(Locale.CHINA,"¥%s",mInfo.getPrice_goods()));
        tvExpress.setText(String.format(Locale.CHINA,"¥%s",mInfo.getPrice_express()));
        tvTotalMoney.setText(String.format(Locale.CHINA,"¥%s",mInfo.getPrice_total()));
        tvCreateTime.setText(mInfo.getCreate_at());

    }

    private void initViewStatus(int status) {



        if (status == 0) {
            //已取消          重新购买
            ViewUtils.hideView(tvLeft);
            ViewUtils.hideView(tvRight);
            textZhuangtai.setText("已取消");
        } else if (status == 2) {
            //待付款  取消订单  立即支付
            ViewUtils.showView(tvLeft);
            ViewUtils.showView(tvRight);
            tvLeft.setText("取消订单");
            tvRight.setText("立即支付");
            textZhuangtai.setText("待支付");
        } else if (status == 3) {
            //待发货
            ViewUtils.hideView(tvLeft);
            ViewUtils.hideView(tvRight);

            textZhuangtai.setText("待发货");
        } else if (status == 4) {
            //待收货  查看物流  确认收货
            ViewUtils.showView(tvLeft);
            ViewUtils.showView(tvRight);
            tvLeft.setText("查看物流");
            tvRight.setText("确认收货");
            textZhuangtai.setText("待收货");
        } else if (status == 5) {
            //已完成  查看物流
            ViewUtils.showView(tvLeft);
            ViewUtils.hideView(tvRight);
            tvLeft.setText("查看物流");
            textZhuangtai.setText("已完成");
        }







    }


    @OnClick({R.id.image_back, R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_left:
                if(mStatus==2||mStatus==3) {
                    //待支付和收货  取消按钮
                    new XPopup.Builder(OrderDetailsActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(OrderDetailsActivity.this,
                                    "亲，确定要取消订单吗？", "取消", "确定", () -> {
                                mViewModel.orderCancel(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(mInfo.getOrder_no()))
                                        .observe(this, mCancelObserver);
                            }))
                            .show();




                }else if(mStatus==4||mStatus==5){
                    //查看物流  intent
                    Intent intent = new Intent(OrderDetailsActivity.this, LogisticsActivity.class);
                    intent.putExtra("express_no",String.valueOf(mInfo.getExpress_send_no()));
                    intent.putExtra("express_code",String.valueOf(mInfo.getExpress_company_code()));
                    intent.putExtra("express_company_title",String.valueOf(mInfo.getExpress_company_title()));
                    startActivity(intent);
                }
                break;
            case R.id.tv_right:
                //提交订单，成功后，去调用获取支付参数接口
                if(mStatus==2){
                    //待支付  弹框选择支付
                    new XPopup.Builder(OrderDetailsActivity.this)
                            .dismissOnTouchOutside(true)
                            .dismissOnBackPressed(true)
                            .asCustom(new PayBottom2TopProdPopup(OrderDetailsActivity.this,mInfo.getPrice_total(), type -> {

                                mPayType=type;
                                mViewModel.getOrderPay(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),
                                        String.valueOf(mInfo.getOrder_no()),String.valueOf(type))
                                        .observe(this,mPayObserver);

                            }))
                            .show();




                }else if(mStatus==5){
                    //确认收货  弹框确认收货


                    new XPopup.Builder(OrderDetailsActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopup(OrderDetailsActivity.this,
                                    "亲，确定要确认收货吗？", "取消", "确定", () -> {
                                mViewModel.orderConfirm(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),
                                        String.valueOf(mInfo.getOrder_no()))
                                        .observe(this,mConfirmObserver);
                            }))
                            .show();


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

                    if(mPayType==2){
                        ZFBBean orderPay = new Gson().fromJson(result, ZFBBean.class);
                        if(orderPay.getCode()==1){

                            sendReq2ZFB(orderPay.getData());

                        }else {
                            FToast.warning(orderPay.getInfo());
                        }

                    }else if(mPayType==1){
                        OrderPay orderPay = new Gson().fromJson(result, OrderPay.class);
                        if(orderPay.getCode()==1){
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

                        }else {
                            FToast.warning("");
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

                    if(orderConfirm.getCode()==1){

                        FToast.success(orderConfirm.getInfo());
                        mStatus=HAS_FINISH;
                        initViewStatus(HAS_FINISH);

                        //刷新外层
                        EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);



                        //切换全部订单
                        EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.INTENT_MY_ORDER));


                        finish();

                    }else {
                        FToast.warning(orderConfirm.getInfo());
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
                        EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


                        FToast.success(orderCancel.getInfo());
                        //取消订单 刷新头部详情页状态
                        mStatus=0;

                        initViewStatus(CANCEL_STATUS);

                        //切换全部订单
                        EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.INTENT_MY_ORDER));


                        finish();
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
    private  void sendReq2WX(WxPayBean wxPayBean) {

        //这里的appid，替换成自己的即可
        IWXAPI api = WXAPIFactory.createWXAPI(BasicApp.getContext(), Constant.WX_APP_ID);
        api.registerApp(Constant.WX_APP_ID);

        //这里的bean，是服务器返回的json生成的bean
        PayReq payRequest = new PayReq();
        payRequest.appId = wxPayBean.getAppId();
        //  payRequest.partnerId = wxPayBean.getPartnerid();//这里参数也需要，目前没有就屏蔽了
        //  payRequest.prepayId = wxPayBean.getPrepayid();//这里参数也需要，目前没有就屏蔽了
        payRequest.packageValue = "Sign=WXPay";//固定值
        payRequest.nonceStr = wxPayBean.getNonceStr();
        payRequest.timeStamp = wxPayBean.getTimestamp();
        payRequest.sign = wxPayBean.getPaySign();

        //发起请求，调起微信前去支付
        api.sendReq(payRequest);
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
                        EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(OrderDetailsActivity.this).sendBroadcast(intent);

                        //切换全部订单
                        EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.INTENT_MY_ORDER));


                        finish();

                    } else {

                        if (TextUtils.equals(resultStatus, "6001")) {
                            FToast.warning("支付取消");

                            //切换全部订单
                            EventBus.getDefault().postSticky(new OrderEvent(OrderEvent.INTENT_MY_ORDER));

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



}

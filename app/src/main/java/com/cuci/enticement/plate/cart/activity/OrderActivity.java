package com.cuci.enticement.plate.cart.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AddressBean;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.CommitOrder;
import com.cuci.enticement.bean.DataUserInfo;
import com.cuci.enticement.bean.ExpressCost;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderPay;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.Version;
import com.cuci.enticement.bean.WxPayBean;
import com.cuci.enticement.bean.ZFBBean;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.plate.common.eventbus.CartEvent;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.common.popup.WarningPopup;
import com.cuci.enticement.plate.common.vm.CommonViewModel;
import com.cuci.enticement.plate.common.vm.MainViewModel;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.activity.RecAddressActivity;
import com.cuci.enticement.plate.mine.adapter.ItemYuProdViewBinder;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.Arith;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.PayResult;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;


/**
 *
 */
public class OrderActivity extends BaseActivity implements ItemYuProdViewBinder.OnProdClickListener {
    private static final int SDK_PAY_FLAG = 1;
    private static final String TAG = OrderActivity.class.getSimpleName();
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.text_address)
    TextView tvAddress;

    @BindView(R.id.text_shangpingmoney)
    TextView textShangpingmoney;
    @BindView(R.id.text_yunfeimoney)
    TextView textYunfeimoney;
    @BindView(R.id.ali_iv)
    ImageView aliIv;
    @BindView(R.id.wechat_iv)
    ImageView wechatIv;

    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.caixian)
    ImageView caixian;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.text_zengping)
    TextView textZengping;
    @BindView(R.id.con_zengping)
    ConstraintLayout conZengping;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.text_yunfei)
    TextView textYunfei;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.text_fangshi)
    TextView textFangshi;
    @BindView(R.id.con_fangshi1)
    ConstraintLayout conFangshi1;
    @BindView(R.id.con_fangshi2)
    ConstraintLayout conFangshi2;
    @BindView(R.id.con_zhongjian)
    ConstraintLayout conZhongjian;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.bottom)
    ConstraintLayout bottom;

    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;

    private String mAddressId = "";
    private int mPayType = 2;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private int is_new;
    private int number;
    private String rule;
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
                        //支付成功后，刷新个人中心状态
                        //刷新外层
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(OrderActivity.this).sendBroadcast(intent);
                       //刷新is_new
                        EventBus.getDefault().post(new IsnewEvent());
                        //跳转订单页面--全部

                        startActivity(new Intent(OrderActivity.this, MyOrderActivity.class));

                        finish();


                    } else {

                        if (TextUtils.equals(resultStatus, "6001")) {
                            FToast.warning("支付取消");

                            startActivity(new Intent(OrderActivity.this, MyOrderActivity.class));

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
        } else if (event.getCode() == OrderEvent.SET_ADDRESS) {

            tvAddress.setText("");
            ViewUtils.hideView(tvAddress);
            ViewUtils.showView(textDizi);
            tvTotalMoney.setText(mInfo.getPrice_goods());
        }


    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_sendorder;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");

        List<OrderGoods> items = mInfo.getList();
        int vip = intent.getIntExtra("vip", 0);
        number = intent.getIntExtra("num", 0);
        rule = intent.getStringExtra("rule");
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo == null) {
            return;
        }

        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        //请求当前用户信息
       /* MineViewModel mViewModel1 = ViewModelProviders.of(this).get(MineViewModel.class);
        mViewModel1.dataUserinfo("2", String.valueOf(mUserInfo.getId()), mUserInfo.getToken()).observe(this, mdataObserver);*/

        //检测APP更新
        MainViewModel  mMineViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mMineViewModel.getVersion("2").observe(this, mUpdateObserver);
        // ImageLoader.loadPlaceholder(mOrderBean.get);
        //设置商品总价，运费，订单总价


        textShangpingmoney.setText(mInfo.getPrice_goods());
        tvTotalMoney.setText(mInfo.getPrice_goods());

        String address = SharedPrefUtils.getDefaultAdress();

        if (!TextUtils.isEmpty(address)) {

            ViewUtils.hideView(textDizi);
            ViewUtils.showView(tvAddress);
            tvAddress.setText(address);
            //默认地址id，不去选中就用这个
            mAddressId = SharedPrefUtils.getDefaultAdressId();

            mViewModel.getExpressCost(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(number), mInfo.getPrice_goods(), mAddressId)
                    .observe(OrderActivity.this, mExpressCostObserver);

        } else {


            CommonViewModel commonViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);
            commonViewModel.getAdressList(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), Status.LOAD_REFRESH)
                    .observe(this, mObserver);

        }


        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mItems.addAll(items);
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(OrderGoods.class, new ItemYuProdViewBinder(this, vip));


        OrderItemDecoration mDecoration = new OrderItemDecoration(this, 6);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    protected void onResume() {
        super.onResume();


    }


    private Observer<Status<AddressBean>> mObserver = new Observer<Status<AddressBean>>() {
        @Override
        public void onChanged(Status<AddressBean> status) {
            switch (status.status) {

                case Status.SUCCESS:

                    AddressBean data = status.content;
                    List<AddressBean.DataBean.ListBean> list = data.getData().getList();
                    if (list == null || list.size() == 0) {

                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(tvAddress);
                        return;
                    }

                    if (data.getCode() == 1) {

                        saveDefault(list);


                    } else {
                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(tvAddress);
                        FToast.error(data.getInfo());
                    }
                    break;
                case Status.ERROR:
                    FToast.error(status.message);
                    ViewUtils.showView(textDizi);
                    ViewUtils.hideView(tvAddress);
                    break;
                case Status.LOADING:

                    break;
            }
        }
    };


    private void saveDefault(List<AddressBean.DataBean.ListBean> list) {
        boolean hasDefault = false;
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            AddressBean.DataBean.ListBean item = list.get(i);

            int is_default = item.getIs_default();
            if (is_default == 1) {
                //存在默认就设置
                hasDefault = true;
                num = i;
                break;
            }
        }

        if (hasDefault) {

            AddressBean.DataBean.ListBean item = list.get(num);
            SharedPrefUtils.saveDefaultAdressId(String.valueOf(item.getId()));
            StringBuilder sb = new StringBuilder();
            sb.append(item.getName()).append(" ")
                    .append(item.getPhone()).append(" ").append("\n")
                    .append(item.getProvince()).append(" ")
                    .append(item.getCity()).append(" ")
                    .append(item.getArea()).append(" ")
                    .append(item.getAddress());
            SharedPrefUtils.saveDefaultAdress(sb.toString());
            ViewUtils.hideView(textDizi);
            ViewUtils.showView(tvAddress);
            tvAddress.setText(SharedPrefUtils.getDefaultAdress());
            mViewModel.getExpressCost(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(number), mInfo.getPrice_goods(), SharedPrefUtils.getDefaultAdressId())
                    .observe(OrderActivity.this, mExpressCostObserver);
        } else {
            ViewUtils.showView(textDizi);
            ViewUtils.hideView(tvAddress);
        }


    }


    @OnClick({R.id.text_dizi, R.id.text_address, R.id.tv_commit, R.id.back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_dizi:
            case R.id.text_address:
                Intent intent = new Intent(OrderActivity.this, RecAddressActivity.class);
                intent.putExtra("code", 100);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_commit:
                //支付生成订单
                if (TextUtils.isEmpty(tvAddress.getText())) {

                    new XPopup.Builder(OrderActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new WarningPopup(OrderActivity.this,
                                    "请选择收货地址", "确定")).show();


                    //FToast.warning("请选择收货地址");
                    return;
                }
                if (UtilsForClick.isFastClick()) {
                    //提交订单，成功后，去调用获取支付参数接口
                    mViewModel.udpateAdress(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), rule, SharedPrefUtils.getDefaultAdressId())
                            .observe(OrderActivity.this, mCommitObserver);
                }
                break;
            case R.id.back_iv:
                finish();
                break;
        }
    }


    //暂不使用  可缩起来
    private Observer<Status<AddressBean>> mAddressObserver = new Observer<Status<AddressBean>>() {
        @Override
        public void onChanged(Status<AddressBean> status) {
            switch (status.status) {

                case Status.SUCCESS:
                    dismissLoading();
                    AddressBean data = status.content;
                    List<AddressBean.DataBean.ListBean> list = data.getData().getList();
                    if (list == null || list.size() == 0) {
                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(tvAddress);
                        return;
                    }

                    if (data.getCode() == 1) {
                        boolean hasDefault = false;
                        int num = 0;
                        for (int i = 0; i < list.size(); i++) {
                            AddressBean.DataBean.ListBean item = list.get(i);

                            int is_default = item.getIs_default();
                            if (is_default == 1) {
                                //存在默认就设置
                                hasDefault = true;
                                num = i;
                                break;
                            }
                        }

                        if (hasDefault) {

                            AddressBean.DataBean.ListBean item = list.get(num);
                            ViewUtils.hideView(textDizi);
                            ViewUtils.showView(tvAddress);
                            StringBuilder sb = new StringBuilder();
                            sb.append(item.getName()).append(" ")
                                    .append(item.getPhone()).append(" ").append("\n")
                                    .append(item.getProvince()).append(" ")
                                    .append(item.getCity()).append(" ")
                                    .append(item.getArea()).append(" ")
                                    .append(item.getAddress());

                            tvAddress.setText(sb.toString());
                            mViewModel.getExpressCost(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(number), mInfo.getPrice_goods(), mAddressId)
                                    .observe(OrderActivity.this, mExpressCostObserver);
                        }


                    } else {
                        ViewUtils.showView(textDizi);
                        ViewUtils.hideView(tvAddress);
                        FToast.error(data.getInfo());
                    }
                    break;
                case Status.ERROR:
                    dismissLoading();
                    FToast.error(status.message);

                    break;
                case Status.LOADING:
                    showLoading();
                    break;
            }
        }
    };


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            //返回更新地址
            //todo
            /*String adress = data.getStringExtra("address");
            mAddressId= data.getStringExtra("addressId");*/
            AddressBean.DataBean.ListBean bean = data.getParcelableExtra("addressBean");
            mAddressId = String.valueOf(bean.getId());
            StringBuilder sb = new StringBuilder();
            sb.append(bean.getName()).append(" ")
                    .append(bean.getPhone()).append(" ").append("\n")
                    .append(bean.getProvince()).append(" ")
                    .append(bean.getCity()).append(" ")
                    .append(bean.getArea()).append(" ")
                    .append(bean.getAddress());
            String adress = sb.toString();
            ViewUtils.hideView(textDizi);
            ViewUtils.showView(tvAddress);
            tvAddress.setText(adress);
            mViewModel.getExpressCost(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(number), mInfo.getPrice_goods(), mAddressId)
                    .observe(OrderActivity.this, mExpressCostObserver);
        }
    }


    /**
     * 获取运费接口
     */
    private Observer<Status<ResponseBody>> mExpressCostObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                dismissLoading();
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    ExpressCost expressCost = new Gson().fromJson(result, ExpressCost.class);
                    if (expressCost.getCode() == 1) {
                        double express_price = expressCost.getData().getExpress_price();
                        textYunfeimoney.setText(String.format(Locale.CHINA, "¥%s", express_price));
                        //计算总价
                        double totalMoney = Arith.add(Double.parseDouble(mInfo.getPrice_goods()), express_price);
                        tvTotalMoney.setText(String.format(Locale.CHINA, "%s", totalMoney));
                    } else {
                        FToast.error(expressCost.getInfo());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case Status.LOADING:
                showLoading();
                break;
            case Status.ERROR:
                dismissLoading();
                FToast.error(status.message);

                break;
        }
    };


    /**
     * 提交订单接口   将预订单变成待支付订单  status由1变成2
     */
    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    CommitOrder commitOrder = new Gson().fromJson(result, CommitOrder.class);
                    if (commitOrder.getCode() == 1) {


                        //订单生成成功后，刷新购物车列表
                        EventBus.getDefault().post(new CartEvent(CartEvent.REFRESH_CART_LIST));

                        //订单生成成功后，刷新个人中心状态
                        Intent intent2 = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);

                            mViewModel.getOrderPay(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),
                                    commitOrder.getData().getOrder().getOrder_no(), String.valueOf(mPayType))
                                    .observe(this, mPayObserver);

                    } else {
                        FToast.error(commitOrder.getInfo());
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


    @OnClick({R.id.con_fangshi1, R.id.con_fangshi2})
    public void onPayViewClicked(View view) {
        switch (view.getId()) {
            case R.id.con_fangshi1:
                aliIv.setImageResource(R.drawable.xuanzhong);
                wechatIv.setImageResource(R.drawable.noxuanzhong);

                mPayType = 2;
                break;
            case R.id.con_fangshi2:
                aliIv.setImageResource(R.drawable.noxuanzhong);
                wechatIv.setImageResource(R.drawable.xuanzhong);

                mPayType = 1;

                break;
           /* case R.id.con_fangshi3:
                aliIv.setImageResource(R.drawable.noxuanzhong);
                wechatIv.setImageResource(R.drawable.noxuanzhong);
                unionIv.setImageResource(R.drawable.xuanzhong);
                mPayType=3;
                break;*/
        }
    }

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
                PayTask payTask = new PayTask(OrderActivity.this);
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

    private Observer<Status<ResponseBody>> mdataObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                dataUserinfo(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void dataUserinfo(ResponseBody body) {
        try {
            String b = body.string();
            DataUserInfo mDataUserInfo = new Gson().fromJson(b, DataUserInfo.class);
            if (mDataUserInfo.getCode() == 1) {
                //   is_new = mDataUserInfo.getData().getIs_new();
            } else {

                FToast.error(mDataUserInfo.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    public void onProdClick(OrderGoods item) {

    }

    private Observer<Status<ResponseBody>> mUpdateObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }


    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            Version mVersion = new Gson().fromJson(b, Version.class);
            if (mVersion.getCode() == 1) {
                  if(mVersion.getData().getFull().getOpenfulle()==1){
            ViewUtils.showView(conZengping);
        }else {
            ViewUtils.hideView(conZengping);
            return;
        }
                String price_goods = mInfo.getPrice_goods();
                float total= Float.parseFloat(price_goods);
                List<Version.DataBean.FullBean.FullinfoBean> fullinfo = mVersion.getData().getFull().getFullinfo();
        if(total<fullinfo.get(0).getAmount()){
            float chajia = fullinfo.get(0).getAmount() - total;
            textZengping.setText("再买"+chajia+"元即可赠送橙花精油护手霜两支");
            return;

        }
        for (int i = 0; i <fullinfo.size() ; i++) {
            if(total>=fullinfo.get(i).getAmount()){
                textZengping.setText(fullinfo.get(i).getDesc());
            }

        }


            } else {
                FToast.error(mVersion.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }
}

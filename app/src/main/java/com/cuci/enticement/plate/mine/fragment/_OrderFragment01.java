package com.cuci.enticement.plate.mine.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.Constant;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseFragment;

import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.OrderCancel;
import com.cuci.enticement.bean.OrderConfirm;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderList;
import com.cuci.enticement.bean.OrderPay;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.WxPayBean;
import com.cuci.enticement.bean.ZFBBean;
import com.cuci.enticement.event.IsnewEvent;
import com.cuci.enticement.plate.cart.activity.LogisticsActivity;
import com.cuci.enticement.plate.cart.activity.OrderActivity;
import com.cuci.enticement.plate.cart.activity.OrderDetailsActivity;
import com.cuci.enticement.plate.common.LoginActivity;
import com.cuci.enticement.plate.common.eventbus.MessageEvent1;
import com.cuci.enticement.plate.common.eventbus.OrderEvent;
import com.cuci.enticement.plate.common.popup.PayBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.ShareBottom2TopProdPopup;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.plate.mine.adapter.ItemBottomViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemProdViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemTitleViewBinder;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.PayResult;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.CartItemDecoration;
import com.cuci.enticement.widget.CustomRefreshHeader;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.cuci.enticement.widget.OrderItemTopDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

/**
 * 首页外层Fragment
 */
public class _OrderFragment01 extends BaseFragment implements OnRefreshLoadMoreListener, ItemProdViewBinder.OnProdClickListener
, ItemBottomViewBinder.OnItemClickListener {

    private static final String TAG = _OrderFragment01.class.getSimpleName();
    private static final int SDK_PAY_FLAG = 1;
    private boolean mCanLoadMore = true;
    private OrderViewModel mViewModel;
    private int page=1;
    private String mtype;
    private final String PAGE_SIZE="20";


    //默认支付宝
    private int mPayType=2;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.image_top)
    ImageView mIvTop;

    @BindView(R.id.empty_view)
    LinearLayout mEmptyLayout;



    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private  List<AllOrderList.DataBean.ListBeanX> mDatas=new ArrayList<>();
    private  UserInfo mUserInfo;

    public static _OrderFragment01 newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        _OrderFragment01 fragment = new _OrderFragment01();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onLazyLoad() {
        mRefreshLayout.autoRefresh();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_source01;

    }





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

                        //刷新列表
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);
                        //刷新is_new
                        EventBus.getDefault().post(new IsnewEvent());
                        //切换全部订单
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_MY_ORDER));


                    } else {

                        if (TextUtils.equals(resultStatus, "6001")) {

                            FToast.warning("支付取消");


                            //切换全部订单
                            EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_MY_ORDER));
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            FToast.error("支付失败");
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
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mtype = bundle.getString("type");

        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo==null){
            return;
        }

        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        CustomRefreshHeader header = new CustomRefreshHeader(mActivity);
        header.setBackground(0xFFF3F4F6);
        //mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(this);

       // mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //注册布局
        mAdapter.register(ItemOrderTitle.class, new ItemTitleViewBinder());

        mAdapter.register(OrderGoods.class, new ItemProdViewBinder(this));

        mAdapter.register(ItemOrderBottom.class, new ItemBottomViewBinder(this));


        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        OrderItemTopDecoration mDecoration = new OrderItemTopDecoration(mActivity, 4);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void load() {
        String token = mUserInfo.getToken();
        int id = mUserInfo.getId();
        mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"1",mtype,"",Status.LOAD_REFRESH)
                .observe(this, mObserver);
    }




    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody content = status.content;
                try {
                    String result = content.string();
                    if(mtype.equals("2")){

                        Log.d("east", ": "+result);
                    }
                    AllOrderList allOrderList = new Gson().fromJson(result, AllOrderList.class);

                    if (allOrderList.getCode() == 1) {
                        AllOrderList.DataBean data = allOrderList.getData();
                        List<AllOrderList.DataBean.ListBeanX> item = data.getList();

                        if (item == null ) {
                            if (status.loadType == Status.LOAD_REFRESH) {

                                    ViewUtils.showView(mEmptyLayout);

                                mRefreshLayout.finishRefresh();
                            } else {

                                mRefreshLayout.finishLoadMore();
                            }

                            return;
                        }

                        page = data.getPage().getCurrent()+1;
                        mCanLoadMore = true;

                        if (status.loadType == Status.LOAD_REFRESH) {

                            if (item.size() > 0) {
                                ViewUtils.hideView(mEmptyLayout);

                                mDatas.clear();
                                mDatas.addAll(item);
                                mItems.clear();
                                addOrderItem(item);
                                mAdapter.notifyDataSetChanged();
                                mRefreshLayout.finishRefresh();
                            } else {
                                ViewUtils.showView(mEmptyLayout);
                            }


                        } else {


                            mDatas.addAll(item);

                            addOrderItem(item);
                            int o = mItems.size();
                            addOrderItem(item);
                            int c = mItems.size();
                            mAdapter.notifyItemRangeInserted(o, c);

                            mRefreshLayout.finishLoadMore();
                        }

                    } else {
                        if (status.loadType == Status.LOAD_MORE) {
                            mCanLoadMore = true;
                            mRefreshLayout.finishLoadMore();
                        } else {
                            ViewUtils.showView(mEmptyLayout);
                            mRefreshLayout.finishRefresh();
                        }
                        FToast.error(allOrderList.getInfo());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



                //  OrderList bean = status.content;

                /**/
                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    mRefreshLayout.finishLoadMore();
                } else {

                    mRefreshLayout.finishRefresh();

                }
                break;
        }
    };



    /**
     * 添加订单item
     * @param item
     */
    private void addOrderItem(List<AllOrderList.DataBean.ListBeanX> item) {



        for (int i = 0; i <item.size() ; i++) {
            AllOrderList.DataBean.ListBeanX orderBean = item.get(i);
            int cur=mItems.size();
            ItemOrderTitle itemOrderTitle = new ItemOrderTitle(String.valueOf(orderBean.getOrder_no()), orderBean.getStatus(),cur);
            mItems.add(itemOrderTitle);
            List<OrderGoods> goodsBeanList = orderBean.getList();
            mItems.addAll(goodsBeanList);
            int curBottom=mItems.size();
            ItemOrderBottom itemOrderBottom = new ItemOrderBottom();
            itemOrderBottom.status=orderBean.getStatus();
            itemOrderBottom.orderNum=String.valueOf(orderBean.getOrder_no());
            itemOrderBottom.totalMoney=orderBean.getPrice_total();
            itemOrderBottom.goodsMoney=orderBean.getPrice_goods();
            itemOrderBottom.expressMoney=orderBean.getPrice_express();
            itemOrderBottom.expressNo=orderBean.getExpress_send_no();
            itemOrderBottom.express_company_title=orderBean.getExpress_company_title();
            itemOrderBottom.expressCode=orderBean.getExpress_company_code();
            itemOrderBottom.num=orderBean.getGoods_count();
            itemOrderBottom.bottomcur=curBottom;
            itemOrderBottom.topCur=cur;
            mItems.add(itemOrderBottom);
        }

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if(mCanLoadMore=true){
            mCanLoadMore = false;
            mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),String.valueOf(page),mtype,"",Status.LOAD_MORE).observe(this, mObserver);

        }else {
            mRefreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        load();
    }





    private ItemOrderBottom  mCancelItem;

    @Override
    public void onReBuy(ItemOrderBottom itemOrderBottom) {
        //重新购买
    }

    /**
     * 取消订单
     * @param itemOrderBottom
     */
    @Override
    public void onCancel(ItemOrderBottom itemOrderBottom) {
        new XPopup.Builder(mActivity)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new TipsPopup(mActivity,
                        "亲，确定要取消订单吗？", "取消", "确定", () -> {
                    String orderNum = itemOrderBottom.orderNum;
                    mCancelItem=itemOrderBottom;
                    mViewModel.orderCancel(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),orderNum).observe(this, mCancelObserver);


                }))
                .show();




    }




    /**
     * 立即支付
     * @param itemOrderBottom
     */
    @Override
    public void onPay(ItemOrderBottom itemOrderBottom) {


        new XPopup.Builder(mActivity)
                .dismissOnTouchOutside(true)
                .dismissOnBackPressed(true)
                .asCustom(new PayBottom2TopProdPopup(mActivity,itemOrderBottom.totalMoney,type -> {

                    mPayType=type;
                    mViewModel.getOrderPay(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),
                            String.valueOf(itemOrderBottom.orderNum),String.valueOf(type))
                            .observe(this,mPayObserver);


                }))
                .show();






    }

    /**
     * 确认收货
     * @param itemOrderBottom
     */
    @Override
    public void onConfirmGoods(ItemOrderBottom itemOrderBottom) {
        new XPopup.Builder(mActivity)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new TipsPopup(mActivity,
                        "亲，确定要确认收货吗？", "取消", "确定", () -> {
                    mViewModel.orderConfirm(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),
                            itemOrderBottom.orderNum)
                            .observe(this,mConfirmObserver);
                }))
                .show();
    }


    /**
     * 查看物流
     * @param itemOrderBottom
     */
    @Override
    public void onViewLogistics(ItemOrderBottom itemOrderBottom) {
        Intent intent = new Intent(mActivity, LogisticsActivity.class);
        intent.putExtra("express_no",itemOrderBottom.expressNo);
        intent.putExtra("express_code",itemOrderBottom.expressCode);
        intent.putExtra("express_company_title",itemOrderBottom.express_company_title);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderEventMessage(OrderEvent event) {
        if(event.getCode()==OrderEvent.CANCEL_ORDER){
            if(mtype.equals("")||mtype.equals("2")||mtype.equals("3")){
               load();
            }
        }else if(event.getCode()==OrderEvent.REFRESH_OUTSIDE){
            mRefreshLayout.autoRefresh();
        }


    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }


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


                        //刷新外层
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_OUTSIDE));

                        //刷新小角标状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);

                        //切换全部订单
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_YIWANCHENG));


                        FToast.success(orderConfirm.getInfo());

                    }else {
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
                    if(orderCancel.getCode()==1){
                        if(mtype.equals("")||mtype.equals("2")||mtype.equals("3")){
                            //待支付或者待发货页面  局部删除
                         /*  int count= mCancelItem.bottomcur-mCancelItem.topCur+1;
                            Items items = new Items();
                            for (int i = mCancelItem.topCur; i <= mCancelItem.bottomcur; i++) {
                                items.add(mItems.get(i));
                            }
                            mItems.removeAll(items);
                            mAdapter.notifyItemRangeRemoved(mCancelItem.topCur,count);*/

                           // mAdapter.notifyItemRemoved();

                            EventBus.getDefault().post(new OrderEvent(OrderEvent.CANCEL_ORDER));

                            /* mViewModel.getOrderList(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"1",mtype,"",Status.LOAD_REFRESH)
                                .observe(this, mObserver);*/
                        }else {
                            //全部页面  局部刷新
                            ItemOrderTitle titleBean = (ItemOrderTitle) mItems.get(mCancelItem.topCur);
                            titleBean.setStatus(0);
                            ItemOrderBottom bottomBean = (ItemOrderBottom) mItems.get(mCancelItem.bottomcur);
                            bottomBean.status=0;
                            mAdapter.notifyItemChanged(mCancelItem.topCur,titleBean);
                            mAdapter.notifyItemChanged(mCancelItem.bottomcur,bottomBean);
                        }

                        //刷新状态
                        Intent intent = new Intent(_MineFragment.ACTION_REFRESH_STATUS);

                        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);

                        //切换全部订单
                        EventBus.getDefault().post(new OrderEvent(OrderEvent.INTENT_MY_ORDER));


                        FToast.success(orderCancel.getInfo());
                    }else {
                        FToast.error("订单取消失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);
                if (status.loadType == Status.LOAD_MORE) {
                    mCanLoadMore = true;
                    mRefreshLayout.finishLoadMore();
                } else {

                    mRefreshLayout.finishRefresh();

                }
                break;
        }
    };






    @Override
    public void onProdClick(OrderGoods item) {
        Intent intent = new Intent(mActivity, OrderDetailsActivity.class);
        int curOrder=0;
        for (int i = 0; i < mDatas.size(); i++) {
            long order_no = mDatas.get(i).getOrder_no();
            if(order_no==item.getOrder_no()){
                curOrder=i;
                break;
            }
        }
        AllOrderList.DataBean.ListBeanX orderBean = mDatas.get(curOrder);

        intent.putExtra("intentInfo",orderBean);
        startActivity(intent);
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
                            FToast.error(orderPay.getInfo());
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
                PayTask payTask = new PayTask(mActivity);
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
        payRequest.partnerId = wxPayBean.getPartnerId();//这里参数也需要，目前没有就屏蔽了
        payRequest.prepayId = wxPayBean.getPrepayId();//这里参数也需要，目前没有就屏蔽了
        payRequest.packageValue = "Sign=WXPay";//固定值
        payRequest.nonceStr = wxPayBean.getNonceStr();
        payRequest.timeStamp = wxPayBean.getTimestamp();
        payRequest.sign = wxPayBean.getPaySign();

        //发起请求，调起微信前去支付
        api.sendReq(payRequest);
    }




}

package com.cuci.enticement.plate.cart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.CartDataBean;
import com.cuci.enticement.bean.CartIntentInfo;
import com.cuci.enticement.bean.CommitOrder;
import com.cuci.enticement.bean.ExpressCost;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.OrderPay;
import com.cuci.enticement.bean.OrderResult;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.activity.RecAddressActivity;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.Arith;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

import static com.cuci.enticement.plate.cart.fragment._CartFragment.ACTION_REFRESH_DATA;

/**
 *
 */
public class OrderActivity extends BaseActivity {

    @BindView(R.id.text_dizi)
    TextView textDizi;


    @BindView(R.id.text_shangpingzongjia)
    TextView textShangpingzongjia;
    @BindView(R.id.text_shangpingmoney)
    TextView textShangpingmoney;
    @BindView(R.id.text_yunfeimoney)
    TextView textYunfeimoney;
    @BindView(R.id.ali_iv)
    ImageView aliIv;
    @BindView(R.id.wechat_iv)
    ImageView wechatIv;
    @BindView(R.id.union_iv)
    ImageView unionIv;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;


    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;
    private String mAdressId="";
    private int mPayType=1;
    private AllOrderList.DataBean.ListBeanX   mInfo;
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

        mInfo = intent.getParcelableExtra("intentInfo");
        List<OrderGoods> items = mInfo.getList();

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo == null) {
            return;
        }
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        String  adress = SharedPrefUtils.getDefaultAdress();

        if (TextUtils.isEmpty(adress)) {
            textDizi.setText("请添加收货地址");
        } else {
            textDizi.setText(adress);
            //默认地址id，不去选中就用这个
           mAdressId = SharedPrefUtils.getDefaultAdressId();
        }

        // ImageLoader.loadPlaceholder(mOrderBean.get);
        //设置商品总价，运费，订单总价
        textShangpingmoney.setText(mInfo.getPrice_goods());

        if(!TextUtils.isEmpty(adress)){
            mViewModel.getExpressCost(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),String.valueOf(mInfo.getOrder_no()),mAdressId)
                    .observe(this,mExpressCostObserver);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick({R.id.text_dizi, R.id.tv_commit,R.id.back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_dizi:
                Intent intent = new Intent(OrderActivity.this, RecAddressActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_commit:
                //支付生成订单
                if(TextUtils.isEmpty(textDizi.getText())){
                    FToast.warning("请先添加收货地址");
                    return;
                }
                //提交订单，成功后，去调用获取支付参数接口
                mViewModel.udpateAdress(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(mInfo.getOrder_no()), mAdressId)
                        .observe(OrderActivity.this, mCommitObserver);

                break;
            case R.id.back_iv:
                finish();
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
                    OrderPay orderPay = new Gson().fromJson(result, OrderPay.class);
                    if(orderPay.getCode()==1){
                        OrderPay.DataBean data = orderPay.getData();
                        String appid = data.getAppid();
                        String prepayid = data.getPrepayid();
                        String sign = data.getSign();
                        String timestamp = data.getTimestamp();
                        String partnerid = data.getPartnerid();
                        String noncestr = data.getNoncestr();

                    }else {
                        FToast.warning(orderPay.getInfo());
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
            String adress = data.getStringExtra("adress");
            mAdressId= data.getStringExtra("adressId");
            textDizi.setText(adress);
            mViewModel.getExpressCost(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),String.valueOf(mInfo.getOrder_no()),mAdressId)
                    .observe(this,mExpressCostObserver);
        }
    }


    /**
     * 获取运费接口
     */
    private Observer<Status<ResponseBody>> mExpressCostObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    ExpressCost expressCost = new Gson().fromJson(result, ExpressCost.class);
                    if(expressCost.getCode()==1){
                        double express_price = expressCost.getData().getExpress_price();
                        textYunfeimoney.setText(String.valueOf(express_price));
                        //计算总价
                        double totalMoney= Arith.add(Double.parseDouble(mInfo.getPrice_goods()),express_price);
                        textShangpingzongjia.setText(String.valueOf(totalMoney));
                    }else {
                        FToast.warning(expressCost.getInfo());
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
     * 提交订单接口   将预订单变成待支付订单  status由1变成2
     */
    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    CommitOrder commitOrder = new Gson().fromJson(result, CommitOrder.class);
                    if(commitOrder.getCode()==1){

                        //todo 发送广播去刷新购物车列表  和  个人中心状态
                        //刷新购物车列表
                        Intent intent1 = new Intent(ACTION_REFRESH_DATA);
                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
                        //刷新个人中心状态
                        Intent intent2 = new Intent(_MineFragment.ACTION_LOGIN_SUCCEED);

                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);

                        mViewModel.getOrderPay(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),
                                String.valueOf(mInfo.getOrder_no()),String.valueOf(mPayType))
                                .observe(this,mPayObserver);
                    }else {
                        FToast.warning("提交订单失败");
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






    @OnClick({R.id.con_fangshi1, R.id.con_fangshi2, R.id.con_fangshi3})
    public void onPayViewClicked(View view) {
        switch (view.getId()) {
            case R.id.con_fangshi1:
                aliIv.setImageResource(R.drawable.xuanzhong);
                wechatIv.setImageResource(R.drawable.noxuanzhong);
                unionIv.setImageResource(R.drawable.noxuanzhong);
                mPayType=1;
                break;
            case R.id.con_fangshi2:
                aliIv.setImageResource(R.drawable.noxuanzhong);
                wechatIv.setImageResource(R.drawable.xuanzhong);
                unionIv.setImageResource(R.drawable.noxuanzhong);
                mPayType=2;

                break;
            case R.id.con_fangshi3:
                aliIv.setImageResource(R.drawable.noxuanzhong);
                wechatIv.setImageResource(R.drawable.noxuanzhong);
                unionIv.setImageResource(R.drawable.xuanzhong);
                mPayType=3;
                break;
        }
    }
}

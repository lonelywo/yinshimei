package com.example.enticement.plate.cart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseActivity;
import com.example.enticement.bean.OrderPay;
import com.example.enticement.bean.OrderResult;
import com.example.enticement.bean.Status;
import com.example.enticement.bean.UserInfo;
import com.example.enticement.plate.mine.activity.RecAddressActivity;
import com.example.enticement.plate.mine.vm.OrderViewModel;
import com.example.enticement.utils.FToast;
import com.example.enticement.utils.SharedPrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class OrderActivity extends BaseActivity {

    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.img_tuxiang)
    ImageView imgTuxiang;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.text_neirong)
    TextView textNeirong;
    @BindView(R.id.text_jiage)
    TextView textJiage;
    @BindView(R.id.text_jia)
    TextView textJia;
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
    private OrderResult.DataBean.OrderBean mOrderBean;
    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;
    private String mAddressId;
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

        mOrderBean = intent.getParcelableExtra("order");
        mUserInfo= SharedPrefUtils.get(UserInfo.class);
        if(mUserInfo==null){
            return;
        }
        mViewModel= ViewModelProviders.of(this).get(OrderViewModel.class);


    }



    @OnClick({R.id.text_dizi, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_dizi:
                Intent intent = new Intent(OrderActivity.this, RecAddressActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.tv_commit:
                mViewModel.orderConfirm(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),mOrderBean.getOrder_no()).observe(OrderActivity.this,mResultObserver);

                break;
        }
    }



    private Observer<Status<OrderResult>> mResultObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                OrderResult content = status.content;
                if(content.getCode()==1){

                }

                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);
                if (status.loadType == Status.LOAD_MORE) {

                } else {



                }
                break;
        }
    };



    private Observer<Status<OrderPay>> mPayObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                OrderPay content = status.content;
                if(content.getCode()==1){

                }

                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                FToast.error(status.message);
                if (status.loadType == Status.LOAD_MORE) {

                } else {



                }
                break;
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==101){
            //返回更新地址
            //todo
            String adress="";

            textDizi.setText(adress);
            mViewModel.udpateAdress(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),mOrderBean.getOrder_no(),mAddressId).observe(OrderActivity.this,mPayObserver);

        }
    }
}

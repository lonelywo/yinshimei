package com.cuci.enticement.plate.cart.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TuiKuanXiangQingBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;


/**
 * 退款详情页
 */
public class TuiKuanDetails3Activity extends BaseActivity {

    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_zhuangtai)
    TextView textZhuangtai;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.con_zhuangtai)
    ConstraintLayout conZhuangtai;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.con_money)
    ConstraintLayout conMoney;
    @BindView(R.id.tv_zhuyi)
    TextView tvZhuyi;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private String refund_id;
    private String item_id;
    private OrderViewModel mViewModel;
    private UserInfo mUserInfo;
    private String text;
    private String back;


    @Override
    public int getLayoutId() {
        return R.layout.tuikuan_details3;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        refund_id = intent.getStringExtra("refund_id");
        item_id = intent.getStringExtra("item_id");
        text = intent.getStringExtra("text");
        back = intent.getStringExtra("back");
        if (TextUtils.equals(text, "退款申请已撤销")) {
            ViewUtils.hideView(conMoney);
            ViewUtils.showView(tvZhuyi);
            textZhuangtai.setText("退款申请已撤销");
            textTime.setText("该退款已关闭");
        } else if(TextUtils.equals(text, "平台已同意")){
            ViewUtils.hideView(conMoney);
            ViewUtils.showView(tvZhuyi);
            textZhuangtai.setText("平台已同意");
            textTime.setText("请您尽快退还商品");
        }else if(TextUtils.equals(text, "退款成功")){
            ViewUtils.showView(conMoney);
            ViewUtils.hideView(tvZhuyi);
            textZhuangtai.setText("退款成功");
            textTime.setText("该退款已完成");}
        initContent();
    }

    private void initContent() {
        mViewModel.getTuiKuanXiangQing(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id, item_id, "" + AppUtils.getVersionCode(this))
                .observe(this, mTuiKuanXiangQingObserver);

    }

    private Observer<Status<ResponseBody>> mTuiKuanXiangQingObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    TuiKuanXiangQingBean mbean = new Gson().fromJson(result, TuiKuanXiangQingBean.class);
                    if (mbean.getCode() == 1) {
                        refund_id = String.valueOf(mbean.getData().getRefund().getId());
                        String amount = mbean.getData().getRefund().getAmount();
                        tvMoney.setText("¥" + MathExtend.moveone(amount));

                        tvZhuyi.setText(mbean.getData().getRefund().getAudit_desc());
                    } else if (mbean.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        FToast.error(mbean.getInfo());
                    } else {
                        FToast.error(mbean.getInfo());
                    }

                } catch (Exception e) {
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
    public void onBackPressed() {
        if(TextUtils.equals("back","1")){
            Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_MINE);
            LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(intentRank);
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        super.onBackPressed();
    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        if(TextUtils.equals("back","1")){
            Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_MINE);
            LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(intentRank);
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

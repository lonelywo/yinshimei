package com.cuci.enticement.plate.cart.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TuiKuanCancelBean;
import com.cuci.enticement.bean.TuiKuanXiangQingBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;


/**
 * 退款详情页
 */
public class TuiKuanDetails4Activity extends BaseActivity {


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
    @BindView(R.id.tv_wuliu)
    TextView tvWuliu;
    @BindView(R.id.tv_gongsi)
    TextView tvGongsi;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.tv_yundan)
    TextView tvYundan;
    @BindView(R.id.tv_bianhao)
    TextView tvBianhao;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.con_wuliu)
    ConstraintLayout conWuliu;
    @BindView(R.id.tv_jujue)
    TextView tvJujue;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private String item_id;
    private UserInfo mUserInfo;
    private OrderViewModel mViewModel;
    private String refund_id;
    private String text;
    private String back;


    @Override
    public int getLayoutId() {
        return R.layout.tuikuan_details4;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        item_id = intent.getStringExtra("item_id");
        refund_id = intent.getStringExtra("refund_id");
        text = intent.getStringExtra("text");
        back = intent.getStringExtra("back");
        if (TextUtils.equals(text, "平台拒绝")) {
            ViewUtils.hideView(conWuliu);
            ViewUtils.showView(tvJujue);
            textZhuangtai.setText("平台拒绝");
            textTime.setText("该申请已被拒绝");
        } else {
            ViewUtils.showView(conWuliu);
            ViewUtils.hideView(tvJujue);
            textZhuangtai.setText("等待平台收货");
            textTime.setText("平台收到货后将会尽快处理");
        }
        initContent();

    }

    private void initContent() {
        mViewModel.getTuiKuanXiangQing(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id, item_id, "" + AppUtils.getVersionCode(this))
                .observe(this, mTuiKuanXiangQingObserver);

    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_back})
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

    @OnClick({R.id.tv_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_kefu:
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
                break;
        }
    }

    private Observer<Status<ResponseBody>> mCommitObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    TuiKuanCancelBean mbean = new Gson().fromJson(result, TuiKuanCancelBean.class);
                    if (mbean.getCode() == 1) {
                        //退款申请成功后，刷新个人中心状态
                        Intent intent2 = new Intent(_MineFragment.ACTION_REFRESH_STATUS);
                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
                        FToast.success(mbean.getInfo());

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
    private Observer<Status<ResponseBody>> mTuiKuanXiangQingObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String result = body.string();
                    TuiKuanXiangQingBean mbean = new Gson().fromJson(result, TuiKuanXiangQingBean.class);
                    if (mbean.getCode() == 1) {
                        refund_id = String.valueOf(mbean.getData().getRefund().getId());
                        String company = mbean.getData().getExpress().getCompany();
                        String send_no = mbean.getData().getExpress().getSend_no();
                        String audit_desc = mbean.getData().getRefund().getAudit_desc();
                        tvGongsi.setText(company);
                        tvBianhao.setText(send_no);
                        tvJujue.setText(audit_desc);
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


}

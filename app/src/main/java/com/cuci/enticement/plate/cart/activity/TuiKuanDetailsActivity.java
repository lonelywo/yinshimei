package com.cuci.enticement.plate.cart.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.CommitTuiKuanWuLiuBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TuiKuanCancelBean;
import com.cuci.enticement.bean.TuiKuanWuLiuBean;
import com.cuci.enticement.bean.TuiKuanXiangQingBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.RefreshShouHouEvent;
import com.cuci.enticement.plate.cart.adapter.ItemTuikuanProdViewBinder;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.common.popup.TipsPopup;
import com.cuci.enticement.plate.common.popup.TuiReasonBottom2TopProdPopup;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.cuci.enticement.widget.SmoothScrollview;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.lxj.xpopup.XPopup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;


/**
 * 退款详情页
 */
public class TuiKuanDetailsActivity extends BaseActivity implements ItemTuikuanProdViewBinder.OnProdClickListener {


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
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.line_money)
    View lineMoney;
    @BindView(R.id.con_money)
    ConstraintLayout conMoney;
    /* @BindView(R.id.tv_zhuyi)
     TextView tvZhuyi;
     @BindView(R.id.con_zhuyi)
     ConstraintLayout conZhuyi;*/
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
    @BindView(R.id.con_wuliuxianshi)
    ConstraintLayout conWuliuxianshi;
    @BindView(R.id.tv_biaoti)
    TextView tvBiaoti;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.con_buju1)
    ConstraintLayout conBuju1;
    @BindView(R.id.tv_biaoti2)
    TextView tvBiaoti2;
    @BindView(R.id.tv_content_desc)
    TextView tvContentDesc;
    @BindView(R.id.con_desc)
    ConstraintLayout conDesc;
    @BindView(R.id.text_wuliudanhao)
    TextView textWuliudanhao;
    @BindView(R.id.edt_wuliudanhao)
    EditText edtWuliudanhao;
    @BindView(R.id.tv_code)
    ImageView tvCode;
    @BindView(R.id.con_dibu1)
    ConstraintLayout conDibu1;
    @BindView(R.id.tv_wuliucheck)
    TextView tvWuliucheck;
    @BindView(R.id.con_wuliugongsi)
    ConstraintLayout conWuliugongsi;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.con_phone)
    ConstraintLayout conPhone;
    @BindView(R.id.con_wuliutianxiebuju)
    ConstraintLayout conWuliutianxiebuju;
    @BindView(R.id.tv_cexiao)
    TextView tvCexiao;
    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.tv_kefu1)
    TextView tvKefu1;
    @BindView(R.id.rec_goods)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_tuikuanreason)
    TextView tvTuikuanreason;
    @BindView(R.id.tv_tuikuantime)
    TextView tvTuikuantime;
    @BindView(R.id.tv_tuikuannum)
    TextView tvTuikuannum;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.con_prod)
    ConstraintLayout conProd;
    @BindView(R.id.tv_tuikuanwuliunicheng)
    TextView tvTuikuanwuliunicheng;
    @BindView(R.id.tv_tuikuanwuliudanhao)
    TextView tvTuikuanwuliudanhao;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private UserInfo mUserInfo;
    private TuiKuanWuLiuBean mTuiKuanWuLiuBean;
    private List<TuiKuanWuLiuBean.DataBean.ExpressBean> express;

    private String refund_id;
    private String company;
    private String back;
    private String item_id;
    private OrderViewModel mViewModel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;


    @Override
    public int getLayoutId() {
        return R.layout.tuikuan_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        item_id = intent.getStringExtra("item_id");
        refund_id = intent.getStringExtra("refund_id");
        back = intent.getStringExtra("back");
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mTuiKuanWuLiuBean = SharedPrefUtils.get(TuiKuanWuLiuBean.class);

        if (mTuiKuanWuLiuBean != null) {
            initContent();
            express = mTuiKuanWuLiuBean.getData().getExpress();
        }
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(TuiKuanXiangQingBean.DataBean.RefundBean.OrderRefundListBean.class, new ItemTuikuanProdViewBinder(this));


        OrderItemDecoration mDecoration = new OrderItemDecoration(this, 6);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

    }

    private void initContent() {
        String content = mTuiKuanWuLiuBean.getData().getReason() + "展开全部";
        String url = mTuiKuanWuLiuBean.getData().getReason_url();
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new MyURLSpan(url), spannableString.length() - 4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvContentDesc.setMovementMethod(LinkMovementMethod.getInstance());
        tvContentDesc.setHighlightColor(Color.argb(0x40, 0x4F, 0x41, 0xFD)); //设置点击后的颜色为透明
        tvContentDesc.setText(spannableString);

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
                        int status1 = mbean.getData().getRefund().getStatus();
                        String audit_desc = mbean.getData().getRefund().getAudit_desc();
                        String create_at = mbean.getData().getRefund().getCreate_at();
                        String company1 = mbean.getData().getExpress().getCompany();
                        String send_no = mbean.getData().getExpress().getSend_no();
                        String amount = mbean.getData().getRefund().getAmount();
                        String reason = mbean.getData().getRefund().getReason();
                        int id = mbean.getData().getRefund().getId();
                        List<TuiKuanXiangQingBean.DataBean.RefundBean.OrderRefundListBean> order_refund_list = mbean.getData().getRefund().getOrder_refund_list();
                        switch (status1) {
                            case 0:
                                textZhuangtai.setText("请等待客服处理");
                                //  textTime.setText("客服将加急处理，请耐心等待");
                                ViewUtils.showView(conMoney);
                                textTime.setText("您已成功发起退款申请，请耐心等待商家处理。");
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.hideView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.showView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 1:
                                textZhuangtai.setText("平台已同意");
                                //   textTime.setText("请您尽快退还商品");
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.showView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 2:
                                textZhuangtai.setText("平台拒绝");
                                //  textTime.setText("该申请已被拒绝");
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.showView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 3:
                                textZhuangtai.setText("请您退货");
                                //   textTime.setText("请退货并填写物流信息");
                                ViewUtils.hideView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.showView(conWuliutianxiebuju);
                                ViewUtils.hideView(tvKefu1);
                                ViewUtils.showView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.hideView(conProd);
                                break;
                            case 4:
                                textZhuangtai.setText("等待平台收货");
                                //   textTime.setText("平台在收到包裹后7个工作日内处理退款");
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.showView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.showView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 5:
                                textZhuangtai.setText("平台退款中");
                                //   textTime.setText("请耐心等候");
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.showView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 6:
                                textZhuangtai.setText("退款成功");
                                //    textTime.setText(create_at);
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.hideView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 7:
                                textZhuangtai.setText("退款失败");
                                //    textTime.setText("退款失败");
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.showView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;
                            case 8:
                                textZhuangtai.setText("退款申请已撤销");
                                //   textTime.setText("该退款已关闭");
                                ViewUtils.showView(conMoney);
                                textTime.setText(audit_desc);
                                ViewUtils.hideView(conWuliuxianshi);
                                ViewUtils.hideView(conWuliutianxiebuju);
                                ViewUtils.hideView(tvKefu1);
                                ViewUtils.hideView(bottom);
                                ViewUtils.hideView(llBottom);
                                ViewUtils.showView(conProd);
                                break;

                        }

                        refund_id = String.valueOf(mbean.getData().getRefund().getId());
                        String address = mbean.getData().getRefund().getAddress();
                        String contocts = mbean.getData().getRefund().getContacts();
                        textDizi.setText("收货地址：" + contocts + "\n" + address);
                        tvGongsi.setText(company1);
                        tvBianhao.setText(send_no);

                            tvTuikuanwuliunicheng.setText("物流公司："+company1);
                            tvTuikuanwuliudanhao.setText("物流单号："+send_no);

                        tvMoney.setText("¥" + MathExtend.moveone(amount));
                        tvTuikuanreason.setText("退款原因：" + reason);
                        tvTuikuantime.setText("申请时间：" + create_at);
                        tvTuikuannum.setText("退款编号：" + id);
                        mItems.clear();
                        mItems.addAll(order_refund_list);
                        mAdapter.notifyDataSetChanged();
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

    public void load() {
        String send_no = edtWuliudanhao.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(send_no) || TextUtils.isEmpty(company)) {
            FToast.warning("物流公司、物流单号、手机号不能为空");
            return;
        }
        CartViewModel mCartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        mCartViewModel.TuiKuanWuLiuCommit("2", mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id, company, send_no, phone, "" + AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mObserver);
    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

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
            CommitTuiKuanWuLiuBean mCommitTuiKuanWuLiuBean = new Gson().fromJson(b, CommitTuiKuanWuLiuBean.class);
            if (mCommitTuiKuanWuLiuBean.getCode() == 1) {
                //退款申请成功后，刷新个人中心状态
                Intent intent2 = new Intent(_MineFragment.ACTION_REFRESH_STATUS);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
                //退货填写成功后刷新
                EventBus.getDefault().post(new RefreshShouHouEvent());

                mViewModel.getTuiKuanXiangQing(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id, item_id, "" + AppUtils.getVersionCode(this))
                        .observe(this, mTuiKuanXiangQingObserver);

                FToast.success(mCommitTuiKuanWuLiuBean.getInfo());

            } else if (mCommitTuiKuanWuLiuBean.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mCommitTuiKuanWuLiuBean.getInfo());
            } else {
                FToast.error(mCommitTuiKuanWuLiuBean.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onBackPressed() {
        if (TextUtils.equals(back, "1")) {
            Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_MINE);
            LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(intentRank);
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        super.onBackPressed();
    }


    @OnClick({R.id.image_back})
    public void onViewClicked() {
        if (TextUtils.equals(back, "1")) {
            Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_MINE);
            LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(intentRank);
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        finish();
    }

    @OnClick({R.id.tv_wuliucheck, R.id.bottom, R.id.tv_kefu1, R.id.tv_kefu, R.id.tv_cexiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wuliucheck:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                View v = getCurrentFocus();
                if (v != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//从控件所在的窗口中隐藏
                }
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(true)
                        .dismissOnBackPressed(true)
                        .asCustom(new TuiReasonBottom2TopProdPopup(this, express, sex -> {
                            tvWuliucheck.setText(sex.getTitle());
                            company = sex.getTitle();
                        }))
                        .show();
                break;
            case R.id.bottom:
                load();
                break;
            case R.id.tv_kefu1:
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
            case R.id.tv_cexiao:
                new XPopup.Builder(this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new TipsPopup(this,
                                "您将撤销本次申请，如果问题未解决,您还可以再次发起，确定继续吗？", "关闭", "确定", () -> {
                            if (mUserInfo != null) {
                                OrderViewModel mViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
                                mViewModel.getTuiKuanCancel(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id, "" + AppUtils.getVersionCode(this))
                                        .observe(this, mCommitObserver);
                            }
                        }))
                        .show();
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
                        //撤销成功刷新
                        EventBus.getDefault().post(new RefreshShouHouEvent());

                        mViewModel.getTuiKuanXiangQing(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id, item_id, "" + AppUtils.getVersionCode(this))
                                .observe(this, mTuiKuanXiangQingObserver);

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onProdClick(TuiKuanXiangQingBean.DataBean.RefundBean.OrderRefundListBean item) {

    }
}

/*class MyClickText extends ClickableSpan {
   private Context context;
   public MyClickText(TuiKuanDetailsActivity mTuiKuanDetailsActivity) {
       this.context = mTuiKuanDetailsActivity;
   }

   @Override
   public void updateDrawState(TextPaint ds) {
       super.updateDrawState(ds);
       //设置文本的颜色
       ds.setColor(context.getResources().getColor(R.color.red));
       //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
       ds.setUnderlineText(false);
   }

   @Override
   public void onClick(View widget) {
       FToast.success("被点击了");
   }
}*/
class MyURLSpan extends URLSpan {
    public MyURLSpan(String url) {
        super(url);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        // 设置链接文字颜色
        ds.setColor(BasicApp.getContext().getResources().getColor(R.color.red));
    }

}


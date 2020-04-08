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
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.CommitTuiKuanWuLiuBean;
import com.cuci.enticement.bean.LuckDrawBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.TuiKuanWuLiuBean;
import com.cuci.enticement.bean.TuiKuanXiangQingBean;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.popup.TuiReasonBottom2TopProdPopup;
import com.cuci.enticement.plate.mine.fragment._MineFragment;
import com.cuci.enticement.plate.mine.vm.OrderViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;


/**
 * 退款详情页
 */
public class TuiKuanDetailsActivity extends BaseActivity {

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
    @BindView(R.id.con_wuliugongsi)
    ConstraintLayout conWuliugongsi;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.con_phone)
    ConstraintLayout conPhone;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.tv_wuliu)
    TextView tvWuliu;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private UserInfo mUserInfo;
    private TuiKuanWuLiuBean mTuiKuanWuLiuBean;
    private List<TuiKuanWuLiuBean.DataBean.ExpressBean> express;
    private CartViewModel mViewModel;
    private String refund_id;
    private String company;



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
        refund_id = intent.getStringExtra("refund_id");
        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mTuiKuanWuLiuBean = SharedPrefUtils.get(TuiKuanWuLiuBean.class);
        if (mTuiKuanWuLiuBean != null) {
            initContent();
            express = mTuiKuanWuLiuBean.getData().getExpress();
        }

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initContent() {
        String content = mTuiKuanWuLiuBean.getData().getReason() + "展开全部";
        String url = mTuiKuanWuLiuBean.getData().getReason_url();
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new MyURLSpan(url), spannableString.length() - 4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvContentDesc.setMovementMethod(LinkMovementMethod.getInstance());
        tvContentDesc.setHighlightColor(Color.argb(0x40, 0x4F, 0x41, 0xFD)); //设置点击后的颜色为透明
        tvContentDesc.setText(spannableString);
        OrderViewModel  mViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        mViewModel.getTuiKuanXiangQing(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), refund_id , "" ,""+ AppUtils.getVersionCode(this))
                .observe(this, mTuiKuanXiangQingObserver);
       /* StringBuilder sb = new StringBuilder();
        sb.append(mInfo.getExpress_name()).append(" ")
                .append(mInfo.getExpress_phone()).append(" ").append("\n")
                .append(mInfo.getExpress_province()).append(" ")
                .append(mInfo.getExpress_city()).append(" ")
                .append(mInfo.getExpress_area()).append(" ")
                .append(mInfo.getExpress_address());*/


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
                        String address = mbean.getData().getRefund().getAddress();
                        String contocts = mbean.getData().getRefund().getContacts();
                        textDizi.setText("收货地址："+contocts+"\n"+address);
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

   public void load(){
       String send_no = edtWuliudanhao.getText().toString().trim();
       String phone = edtPhone.getText().toString().trim();

       if (TextUtils.isEmpty(phone)
               || TextUtils.isEmpty(send_no)||TextUtils.isEmpty(company)) {
           FToast.warning("物流公司、物流单号、手机号不能为空");
           return;
       }

       mViewModel.TuiKuanWuLiuCommit("2",mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),refund_id,company,send_no,phone,""+AppUtils.getVersionCode(BasicApp.getContext())).observe(this, mObserver);
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

                Intent intent = new Intent(this, TuiKuanDetails4Activity.class);
                intent.putExtra("refund_id",refund_id);
                startActivity(intent);
                finish();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_wuliu,R.id.bottom})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_wuliu:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                View v = getCurrentFocus();
                if (v != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//从控件所在的窗口中隐藏
                }
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(true)
                        .dismissOnBackPressed(true)
                        .asCustom(new TuiReasonBottom2TopProdPopup(this, express, sex -> {
                            tvWuliu.setText(sex.getTitle());
                            company = sex.getTitle();
                        }))
                        .show();
                break;
            case R.id.bottom:
                load();
                break;

        }

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


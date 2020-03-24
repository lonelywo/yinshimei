package com.cuci.enticement.plate.cart.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.utils.FToast;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;


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
    private AllOrderList.DataBean.ListBeanX mInfo;


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
        initContent();
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initContent() {
        String content="请您勿使用到付或平邮，且保证商品完好，以免产生拒签哦。为保障退款进度，退货时请您务必填写真实物流单号，并选择以下任意快递寄回：申通快递、中通快递、圆通快递、韵达快递、联邦快递 、百世快递、邮政、德邦快递、EMS、宅急送、优速快递。...展开全部";
        String url="http://www.baidu.com";
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new MyURLSpan(url), spannableString.length()-4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvContentDesc.setMovementMethod(LinkMovementMethod.getInstance());
        tvContentDesc.setHighlightColor(Color.argb(0x40,0x4F,0x41,0xFD)); //设置点击后的颜色为透明
        tvContentDesc.setText(spannableString);


       /* StringBuilder sb = new StringBuilder();
        sb.append(mInfo.getExpress_name()).append(" ")
                .append(mInfo.getExpress_phone()).append(" ").append("\n")
                .append(mInfo.getExpress_province()).append(" ")
                .append(mInfo.getExpress_city()).append(" ")
                .append(mInfo.getExpress_area()).append(" ")
                .append(mInfo.getExpress_address());*/


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
class MyURLSpan extends URLSpan
{
    public MyURLSpan(String url)
    {
        super(url);
    }

    @Override
    public void updateDrawState(TextPaint ds)
    {
        super.updateDrawState(ds);
        // 设置链接文字颜色
        ds.setColor(BasicApp.getContext().getResources().getColor(R.color.red));
    }
}


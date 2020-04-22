package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.common.popup.BottomShareAppPopup2;
import com.cuci.enticement.plate.common.popup.QianDaoHouPopup;
import com.cuci.enticement.utils.FLog;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.TimeUtils;
import com.cuci.enticement.widget.SmoothScrollview;
import com.lxj.xpopup.XPopup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cuci.enticement.utils.TimeUtils.getLongTime;

public class QianDaoActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_share)
    ImageView tvshare;
    @BindView(R.id.con_title)
    ConstraintLayout conTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_wenzi)
    TextView tvMoneyWenzi;
    @BindView(R.id.tv_qiandao_guize)
    TextView tvQiandaoGuize;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.tv_jifen_biaoti)
    TextView tvJifenBiaoti;
    @BindView(R.id.line1)
    Guideline line1;
    @BindView(R.id.line2)
    Guideline line2;
    @BindView(R.id.line3)
    Guideline line3;
    @BindView(R.id.line4)
    Guideline line4;
    @BindView(R.id.tv_jifen_1)
    TextView tvJifen1;
    @BindView(R.id.tv_time_1)
    TextView tvTime1;
    @BindView(R.id.tv_jifen_2)
    TextView tvJifen2;
    @BindView(R.id.tv_time_2)
    TextView tvTime2;
    @BindView(R.id.tv_jifen_3)
    TextView tvJifen3;
    @BindView(R.id.tv_time_3)
    TextView tvTime3;
    @BindView(R.id.tv_jifen_4)
    TextView tvJifen4;
    @BindView(R.id.tv_time_4)
    TextView tvTime4;
    @BindView(R.id.tv_jifen_5)
    TextView tvJifen5;
    @BindView(R.id.tv_time_5)
    TextView tvTime5;
    @BindView(R.id.tv_jifen_6)
    TextView tvJifen6;
    @BindView(R.id.tv_time_6)
    TextView tvTime6;
    @BindView(R.id.tv_jifen_7)
    TextView tvJifen7;
    @BindView(R.id.tv_time_7)
    TextView tvTime7;
    @BindView(R.id.con_kapai)
    ConstraintLayout conKapai;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_biaoti2)
    TextView tvBiaoti2;
    @BindView(R.id.tv_shangcheng)
    TextView tvShangcheng;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    private UserInfo mUserInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_qiandao;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
       /* new XPopup.Builder(this)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(new QianDaoHouPopup(this, "哈喽",
                        () -> {

                        }))
                .show();*/

        setBottomAlignment("2天");

        long ltime = System.currentTimeMillis();
        long ltime3= ltime+86400000*2;
        long ltime4= ltime+86400000*3;
        long ltime5= ltime+86400000*4;
        long ltime6= ltime+86400000*5;
        long ltime7= ltime+86400000*6;
        String timeWithSplit3 = TimeUtils.getTimeWithSplit(ltime3);
        String timeWithSplit4 = TimeUtils.getTimeWithSplit(ltime4);
        String timeWithSplit5 = TimeUtils.getTimeWithSplit(ltime5);
        String timeWithSplit6 = TimeUtils.getTimeWithSplit(ltime6);
        String timeWithSplit7 = TimeUtils.getTimeWithSplit(ltime7);
        tvTime3.setText(timeWithSplit3);
        tvTime4.setText(timeWithSplit4);
        tvTime5.setText(timeWithSplit5);
        tvTime6.setText(timeWithSplit6);
        tvTime7.setText(timeWithSplit7);

    }


    private void setBottomAlignment(String item) {
        SpannableStringBuilder spanString = new SpannableStringBuilder(item);
        //绝对尺寸
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(160);
        spanString.setSpan(absoluteSizeSpan, 0, spanString.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
       /* // 字体颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GRAY);
        spanString.setSpan(colorSpan, String.valueOf(item.getValue()).length(), spanString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // 字体加粗
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        spanString.setSpan(styleSpan, 0, String.valueOf(item.getValue()).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        tvMoney.setText(spanString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.tv_share,R.id.tv_qiandao_guize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_share:
                new XPopup.Builder(this)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(true)
                        .asCustom(new BottomShareAppPopup2(this, mUserInfo, "", ""))
                        .show();
                break;
            case R.id.tv_qiandao_guize:
                Intent intentProd = new Intent(this, TuiAgreementActivity.class);
                intentProd.putExtra("title", "签到规则");
                intentProd.putExtra("bannerData", "https://web.enticementchina.com/appweb/integral_rule.html");
                startActivity(intentProd);
                break;
        }
    }
}

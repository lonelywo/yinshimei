package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiFenActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_guize)
    TextView tvGuize;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money_wenzi)
    TextView tvMoneyWenzi;
    @BindView(R.id.tv_shangcheng)
    TextView tvShangcheng;
    @BindView(R.id.tv_jilu)
    TextView tvJilu;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.tv_biaoti)
    TextView tvBiaoti;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jifen;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.tv_guize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_guize:
                Intent intentProd = new Intent(this, TuiAgreementActivity.class);
                intentProd.putExtra("title", "积分规则");
                intentProd.putExtra("bannerData", "https://web.enticementchina.com/appweb/integral_rule.html");
                startActivity(intentProd);
                break;
        }
    }
}

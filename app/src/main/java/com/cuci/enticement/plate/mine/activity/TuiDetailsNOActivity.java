package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ImageLoader;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuiDetailsNOActivity extends BaseActivity {
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.text_zhuangtai)
    TextView textZhuangtai;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.con_zhuangtai)
    ConstraintLayout conZhuangtai;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.text_dizi1)
    TextView textDizi1;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.con_buju1)
    ConstraintLayout conBuju1;
    @BindView(R.id.jizhun1)
    TextView jizhun1;
    @BindView(R.id.jizhun2)
    TextView jizhun2;
    @BindView(R.id.jizhun3)
    TextView jizhun3;
    @BindView(R.id.con_buju3)
    ConstraintLayout conBuju3;
    @BindView(R.id.img_tupian)
    ImageView imgTupian;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.text_neirong)
    TextView textNeirong;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.text_qian)
    TextView textQian;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.con_buju)
    ConstraintLayout conBuju;
    private OrderGoods item;

    @Override
    public int getLayoutId() {
        return R.layout.tui_details_no;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        item = (OrderGoods) intent.getSerializableExtra("intentInfo");
        ImageLoader.loadPlaceholder(item.getGoods_logo(), imgTupian);
        textBiaoti.setText(item.getGoods_title());
        textNeirong.setText(item.getGoods_spec());
        textQian.setText(String.format(Locale.CHINA, "%s", item.getPrice_sales()));
        textNum.setText(String.format(Locale.CHINA, "x%s", item.getNumber()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }
}

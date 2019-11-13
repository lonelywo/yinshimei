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

public class TuiTypeActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
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
    @BindView(R.id.img_tuxiang)
    ImageView imgTuxiang;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.text_wenzi2)
    TextView textWenzi2;
    @BindView(R.id.con_tuikuan1)
    ConstraintLayout conTuikuan1;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.img_tuxiang1)
    ImageView imgTuxiang1;
    @BindView(R.id.text_wenzi3)
    TextView textWenzi3;
    @BindView(R.id.text_wenzi4)
    TextView textWenzi4;
    @BindView(R.id.con_tuikuan2)
    ConstraintLayout conTuikuan2;
    private OrderGoods item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_type;
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

    @OnClick({R.id.img_back, R.id.con_tuikuan1, R.id.con_tuikuan2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.con_tuikuan1:
                Intent intent1 = new Intent(this, ApplyTuiActivity.class);
                intent1.putExtra("intentInfo",item);
                intent1.putExtra("type",1);
                startActivity(intent1);
                break;
            case R.id.con_tuikuan2:
                Intent intent2 = new Intent(this, ApplyTuiActivity.class);
                intent2.putExtra("intentInfo",item);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;

        }
    }
}

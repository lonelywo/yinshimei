package com.cuci.enticement.plate.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuiLogisticsActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.edt_phone1)
    ClearEditText edtPhone1;
    @BindView(R.id.con_xuantian1)
    ConstraintLayout conXuantian1;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.text_wenzi2)
    TextView textWenzi2;
    @BindView(R.id.edt_phone2)
    ClearEditText edtPhone2;
    @BindView(R.id.con_xuantian2)
    ConstraintLayout conXuantian2;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.text_wenzi3)
    TextView textWenzi3;
    @BindView(R.id.edt_phone3)
    ClearEditText edtPhone3;
    @BindView(R.id.con_xuantian3)
    ConstraintLayout conXuantian3;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.text_wenzi4)
    TextView textWenzi4;
    @BindView(R.id.edt_phone4)
    ClearEditText edtPhone4;
    @BindView(R.id.con_xuantian4)
    ConstraintLayout conXuantian4;
    @BindView(R.id.text_pingzheng)
    TextView textPingzheng;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ok)
    TextView ok;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_logistics;
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

    @OnClick({R.id.img_back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ok:
                break;
        }
    }
}

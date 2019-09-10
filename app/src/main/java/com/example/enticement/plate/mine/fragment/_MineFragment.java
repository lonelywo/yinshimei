package com.example.enticement.plate.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;
import com.example.enticement.plate.home.activity.ProdActivity;
import com.example.enticement.plate.mine.activity.MyOrderActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页外层Fragment
 */
public class _MineFragment extends BaseFragment {

    private static final String TAG = _MineFragment.class.getSimpleName();
    @BindView(R.id.img_kaiguan)
    ImageView imgKaiguan;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.img_shape_huang)
    ImageView imgShapeHuang;
    @BindView(R.id.img_shape_bai)
    ImageView imgShapeBai;
    @BindView(R.id.img_tuxiang)
    ImageView imgTuxiang;
    @BindView(R.id.img_jingxiao)
    ImageView imgJingxiao;
    @BindView(R.id.btn_shengji)
    Button btnShengji;
    @BindView(R.id.img_shape_bai2)
    ImageView imgShapeBai2;
    @BindView(R.id.text_dingdan)
    TextView textDingdan;
    @BindView(R.id.text_quanbudingdan)
    TextView textQuanbudingdan;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.text_daifukuan)
    TextView textDaifukuan;
    @BindView(R.id.text_daifahuo)
    TextView textDaifahuo;
    @BindView(R.id.text_daishouhuo)
    TextView textDaishouhuo;
    @BindView(R.id.text_yiwancheng)
    TextView textYiwancheng;
    @BindView(R.id.img_shape_bai3)
    ImageView imgShapeBai3;
    @BindView(R.id.text_fuwu)
    TextView textFuwu;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.text_tuiguangyongjing)
    TextView textTuiguangyongjing;
    @BindView(R.id.text_wodetuandui)
    TextView textWodetuandui;
    @BindView(R.id.text_shouhuodizi)
    TextView textShouhuodizi;
    @BindView(R.id.text_yejiyuefan)
    TextView textYejiyuefan;
    @BindView(R.id.ll_fuwu)
    LinearLayout llFuwu;
    @BindView(R.id.text_wodekefu)
    TextView textWodekefu;
    @BindView(R.id.ll_fuwu2)
    LinearLayout llFuwu2;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;


    private boolean mCouldChange = true;


    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
        //     mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);


    }


    private void load() {
        //  mViewModel.getSplash().observe(this, mObserver);
    }


    @OnClick({R.id.img_kaiguan, R.id.btn_shengji, R.id.text_quanbudingdan, R.id.text_daifukuan, R.id.text_daifahuo, R.id.text_daishouhuo, R.id.text_yiwancheng, R.id.text_tuiguangyongjing, R.id.text_wodetuandui, R.id.text_shouhuodizi, R.id.text_yejiyuefan, R.id.text_wodekefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_kaiguan:

                break;
            case R.id.btn_shengji:

                break;
            case R.id.text_quanbudingdan:
                Intent intentProd = new Intent(mActivity, MyOrderActivity.class);
                intentProd.putExtra("bannerData", "");
                mActivity.startActivity(intentProd);
                break;
            case R.id.text_daifukuan:
                break;
            case R.id.text_daifahuo:
                break;
            case R.id.text_daishouhuo:
                break;
            case R.id.text_yiwancheng:
                break;
            case R.id.text_tuiguangyongjing:
                break;
            case R.id.text_wodetuandui:
                break;
            case R.id.text_shouhuodizi:
                break;
            case R.id.text_yejiyuefan:
                break;
            case R.id.text_wodekefu:
                break;
        }
    }
}

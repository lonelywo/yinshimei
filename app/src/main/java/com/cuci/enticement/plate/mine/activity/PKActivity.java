package com.cuci.enticement.plate.mine.activity;


import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.viewpager.widget.ViewPager;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;

import com.cuci.enticement.bean.UserInfo;

import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.mine.fragment._OrderFragment01;
import com.cuci.enticement.plate.mine.fragment._PKFragment01;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import de.hdodenhof.circleimageview.CircleImageView;


public class PKActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.civ_tuxiang)
    CircleImageView civTuxiang;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_wenzi)
    TextView textWenzi;
    @BindView(R.id.text_yongjing)
    TextView textYongjing;
    @BindView(R.id.con_huang)
    ConstraintLayout conHuang;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    private UserInfo mUserInfo;
    List<String> data = new ArrayList<String>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_pk;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        ImageLoader.loadPlaceholder1(mUserInfo.getHeadimg(), civTuxiang);
        textName.setText(mUserInfo.getNickname());
        data.add("个人消费榜");
        data.add("个人日业绩榜");
        data.add("团队直推榜");
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        String[] titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {

            titles[i] = data.get(i);
        }
        adapter.addFragment(_PKFragment01.newInstance("1"));
        adapter.addFragment(_PKFragment01.newInstance("2"));
        adapter.addFragment(_PKFragment01.newInstance("3"));
        mViewPager.setOffscreenPageLimit(data.size() - 1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager, titles);
        mViewPager.setCurrentItem(0);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}

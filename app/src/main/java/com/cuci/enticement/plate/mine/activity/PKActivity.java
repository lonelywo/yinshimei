package com.cuci.enticement.plate.mine.activity;


import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.Constant;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;

import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;

import com.cuci.enticement.event.PKEvent;
import com.cuci.enticement.event.PKEvent1;
import com.cuci.enticement.event.PKEvent2;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;

import com.cuci.enticement.plate.mine.fragment._PKFragment01;
import com.cuci.enticement.plate.mine.fragment._PKFragment02;
import com.cuci.enticement.plate.mine.fragment._PKFragment03;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private int page=1;
    List<String> data = new ArrayList<String>();
    private MineViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pk;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
       /* ImageLoader.loadPlaceholder1(mUserInfo.getHeadimg(), civTuxiang);
        textName.setText(mUserInfo.getNickname());*/
        data.add("个人消费榜");
        data.add("个人日业绩榜");
        data.add("团队直推榜");
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        String[] titles = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {

            titles[i] = data.get(i);
        }
        adapter.addFragment(_PKFragment01.newInstance("1"));
        adapter.addFragment(_PKFragment02.newInstance("2"));
        adapter.addFragment(_PKFragment03.newInstance("3"));
        mViewPager.setOffscreenPageLimit(data.size() - 1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager, titles);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    textWenzi.setText("我消费的总金额");
                    textYongjing.setText("¥"+ Constant.PK1);
                }
                if(position==1){
                    textWenzi.setText("我今日的总业绩");
                    textYongjing.setText("¥"+Constant.PK2);
                }
                if(position==2){
                    textWenzi.setText("我直推的总人数");
                    textYongjing.setText(Constant.PK3+"人");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPKEventMessage(PKEvent event) {
        int code = event.getCode();
        String Headimg = event.getHeadimg();
        String Nickname = event.getNickname();
        ImageLoader.loadPlaceholder1(Headimg, civTuxiang);
        textYongjing.setText(code+"人");
        textName.setText(Nickname);
        textWenzi.setText("我直推的总人数");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPKEventMessage(PKEvent1 event) {
        String code = event.getCode();
        String Headimg = event.getHeadimg();
        String Nickname = event.getNickname();
        ImageLoader.loadPlaceholder1(Headimg, civTuxiang);
        textYongjing.setText("¥"+code);
        textName.setText(Nickname);
        textWenzi.setText("我消费的总金额");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPKEventMessage(PKEvent2 event) {
        String code = event.getCode();
        String Headimg = event.getHeadimg();
        String Nickname = event.getNickname();
        ImageLoader.loadPlaceholder1(Headimg, civTuxiang);
        textYongjing.setText("¥"+code);
        textName.setText(Nickname);
        textWenzi.setText("我今日的总业绩");
    }

}

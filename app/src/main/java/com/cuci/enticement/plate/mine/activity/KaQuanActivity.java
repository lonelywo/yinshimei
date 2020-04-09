package com.cuci.enticement.plate.mine.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.event.KaQuanEvent;
import com.cuci.enticement.event.ReceiveEvent;
import com.cuci.enticement.plate.common.adapter.MainPagerAdapter;
import com.cuci.enticement.plate.mine.fragment._KaQuanFragment01;
import com.cuci.enticement.plate.mine.fragment._KaQuanFragment02;
import com.cuci.enticement.plate.mine.fragment._KaQuanFragment03;
import com.cuci.enticement.plate.mine.fragment._PKFragment01;
import com.cuci.enticement.plate.mine.fragment._PKFragment02;
import com.cuci.enticement.plate.mine.fragment._PKFragment03;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.flyco.tablayout.SlidingTabLayout;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import okhttp3.ResponseBody;


public class KaQuanActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;
    private UserInfo mUserInfo;
    private int page = 1;
    List<String> data = new ArrayList<String>();
    private MineViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_kaquan;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        load();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    private void load() {

        mViewModel.kaquanlist(mUserInfo.getToken(),String.valueOf(mUserInfo.getId()),"2", "1", "2", "0",""+ AppUtils.getVersionCode(this), Status.LOAD_REFRESH)
                .observe(this, mObserver);

    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                opera1(body, status);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }
    };

    private void opera1(ResponseBody body, Status status) {
        try {
            String b = body.string();
            KaQuanListBean mKaQuanListBean = new Gson().fromJson(b, KaQuanListBean.class);
            if (mKaQuanListBean.getCode() == 1) {
                KaQuanListBean.DataBean.CountBean count = mKaQuanListBean.getData().getCount();
                int no_used = count.getNo_used();
                int used = count.getUsed();
                int expire = count.getExpire();
                data.add("未使用" + "(" + no_used + ")");
                data.add("已使用" + "(" + used + ")");
                data.add("已失效" + "(" + expire + ")");
                MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
                String[] titles = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {

                    titles[i] = data.get(i);
                }
                adapter.addFragment(_KaQuanFragment01.newInstance("0"));
                adapter.addFragment(_KaQuanFragment02.newInstance("1"));
                adapter.addFragment(_KaQuanFragment03.newInstance("2"));
                mViewPager.setOffscreenPageLimit(data.size() - 1);
                mViewPager.setAdapter(adapter);
                mTabLayout.setViewPager(mViewPager, titles);
                mViewPager.setCurrentItem(0);
            }else if(mKaQuanListBean.getCode() == HttpUtils.CODE_INVALID){
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mKaQuanListBean.getInfo());
            } else {

                FToast.error(mKaQuanListBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
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
    public void onKaQuanEventMessage(KaQuanEvent event) {

    }

}

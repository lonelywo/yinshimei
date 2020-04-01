package com.cuci.enticement.plate.cart.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;

import android.text.Html;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.LuckDrawBean;
import com.cuci.enticement.bean.PayOfterBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.plate.cart.adapter.ItemZhuanPanViewBinder;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.popup.CheckKaQuanTipsPopup;
import com.cuci.enticement.plate.common.popup.LuckDrawTipsPopup;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class ZhuanPanActivity extends BaseActivity implements ItemZhuanPanViewBinder.OnProdClickListener{


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.rec_zhuanpan)
    RecyclerView recZhuanpan;
    private CartViewModel mViewModel;
    private UserInfo mUserInfo;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private GridLayoutManager mLayoutManager;
    // 未开始抽奖时的图片
    private int[] imgs1 = { R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg,
            R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_center, R.drawable.zhuanpan_nobg,
            R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg,R.drawable.zhuanpan_nobg};
    // 开始抽奖时的图片
    private int[] imgs2 = { R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg,
            R.drawable.zhuanpan_bg, R.drawable.zhuanpan_center, R.drawable.zhuanpan_bg,
            R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg};
    private int mPosition;
    // 对应转盘id的数组
    private int[] array = { 0, 1, 2, 5, 8, 7, 6, 3 };
    //   private int[] array = { 0, 1, 2, 3, 5, 6, 7, 8 };
    List<PayOfterBean.DataBean.LotteryBean.RulesBean> mrules =new ArrayList<>();
    private int nums;
    private int m_lottery_id;
    private LuckDrawBean.DataBean data;
    private int winIndex;
    private PayOfterBean.DataBean.LotteryBean.RulesBean rulesBean;
    private boolean ischeck=true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_zhuanpan;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        //透明状态栏
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();

        mAdapter.setItems(mItems);

        recZhuanpan.setItemAnimator(new DefaultItemAnimator());
        BrandItemDecoration mDecoration = new BrandItemDecoration(this, 1,1);

        recZhuanpan.addItemDecoration(mDecoration);

        mAdapter.register(PayOfterBean.DataBean.LotteryBean.RulesBean.class, new ItemZhuanPanViewBinder(this));

        mLayoutManager = new GridLayoutManager(this, 3);
        recZhuanpan.setLayoutManager(mLayoutManager);

        recZhuanpan.setAdapter(mAdapter);

        load();
    }

    private void load() {
        if (ServiceCreator.Constant_IS_NEW == 1) {
            mViewModel.payofter(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", ServiceCreator.Constant_GOODS_ID, "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
        } else {
            mViewModel.payofter(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "0", "685735443713", "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
        }
    }

    private Observer<Status<ResponseBody>> mObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                opera(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };

    private void opera(ResponseBody body) {
        try {
            String b = body.string();
            PayOfterBean mPayOfterBean = new Gson().fromJson(b, PayOfterBean.class);
            if (mPayOfterBean.getCode() == 1) {
                if(mPayOfterBean.getData().getIs_lottery()!=0){
                    List<PayOfterBean.DataBean.LotteryBean.RulesBean> rules = mPayOfterBean.getData().getLottery().getRules();
                    rulesBean = new PayOfterBean.DataBean.LotteryBean.RulesBean();
                    nums = mPayOfterBean.getData().getLottery().getNums();
                    m_lottery_id = mPayOfterBean.getData().getLottery().getM_lottery_id();
                    rulesBean.setTitle(String.valueOf(nums));
                    rules.add(4,rulesBean);
                    mItems.clear();
                    mItems.addAll(rules);
                    mAdapter.notifyDataSetChanged();
                }

            } else if (mPayOfterBean.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mPayOfterBean.getInfo());
            } else {
                FToast.error(mPayOfterBean.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onProdClick(PayOfterBean.DataBean.LotteryBean.RulesBean item) {
        if(ischeck&&nums>=0){
            ischeck=false;
            mViewModel.luckDraw(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(m_lottery_id), String.valueOf(nums), "" + AppUtils.getVersionCode(this)).observe(this, mluckObserver);
        }


    }
    private Observer<Status<ResponseBody>> mluckObserver = status -> {

        switch (status.status) {
            case Status.SUCCESS:

                ResponseBody body = status.content;
                operaluck(body);
                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
            case Status.LOADING:

                break;
        }

    };
    private void operaluck(ResponseBody body) {
        try {
            String b = body.string();
            LuckDrawBean mLuckDrawBean = new Gson().fromJson(b, LuckDrawBean.class);
            if (mLuckDrawBean.getCode() == 1) {
                data = mLuckDrawBean.getData();
                winIndex = mLuckDrawBean.getData().getWinIndex();
                nums--;
               if(nums<0){
                 FToast.warning("次数已经用完");
                 return;
               }
                rulesBean.setTitle(String.valueOf(nums));
                mAdapter.notifyDataSetChanged();
                startAnim();

            } else if (mLuckDrawBean.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mLuckDrawBean.getInfo());
            } else {
                FToast.error(mLuckDrawBean.getInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 开始动画
     */
    public void startAnim(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 3 * 8 + winIndex+1).setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int position = (int) animation.getAnimatedValue();
                setPosition(position%8);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ischeck=true;
                new XPopup.Builder(ZhuanPanActivity.this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new LuckDrawTipsPopup(ZhuanPanActivity.this, data, new LuckDrawTipsPopup.OnExitListener() {

                            @Override
                            public void onCommit(LuckDrawBean.DataBean item) {
                                mViewModel.luckDraw(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(m_lottery_id), String.valueOf(nums), "" + AppUtils.getVersionCode(ZhuanPanActivity.this)).observe(ZhuanPanActivity.this, mluckObserver);

                            }

                        }))
                        .show();
            }
        });
        valueAnimator.start();
    }

    public void setPosition(int position){
        mPosition = position;
        Change(array[mPosition]);
    }
    private void Change(int id) {
        for (int i = 0; i < recZhuanpan.getChildCount(); i++) {
            if (i == id) {
                //如果是选中的，则改变图片为数组2中的图片
                 recZhuanpan.getChildAt(i).setBackgroundResource(imgs2[id]);
            } else if (i == 4) {
                //如果是到了中间那个，则跳出
                continue;
            } else {
                //未选中的就设置为数组1中的图片
              recZhuanpan.getChildAt(i).setBackgroundResource(imgs1[i]);
            }
        }
    }
}

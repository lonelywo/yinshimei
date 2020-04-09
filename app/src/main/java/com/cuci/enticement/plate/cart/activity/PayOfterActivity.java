package com.cuci.enticement.plate.cart.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.LuckDrawBean;
import com.cuci.enticement.bean.PayOfterBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.plate.cart.adapter.ItemZhuanPanViewBinder;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.common.MainActivity;
import com.cuci.enticement.plate.common.popup.LuckDrawTipsPopup;
import com.cuci.enticement.plate.common.popup.TipsPopupxieyi1;
import com.cuci.enticement.plate.mine.activity.KaQuanActivity;
import com.cuci.enticement.plate.mine.activity.MyOrderActivity;
import com.cuci.enticement.plate.mine.activity.YinshimeiActivity;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class PayOfterActivity extends BaseActivity implements ItemZhuanPanViewBinder.OnProdClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_choujiang)
    TextView tvChoujiang;
    @BindView(R.id.rec_zhuanpan)
    RecyclerView recZhuanpan;
    @BindView(R.id.con_choujiang)
    ConstraintLayout conChoujiang;
    @BindView(R.id.tv_shuoming)
    TextView tvShuoming;
    private CartViewModel mViewModel;
    private UserInfo mUserInfo;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private GridLayoutManager mLayoutManager;
    // 未开始抽奖时的图片
    private int[] imgs1 = {R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg,
            R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_center, R.drawable.zhuanpan_nobg,
            R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg};
    // 开始抽奖时的图片
    private int[] imgs2 = {R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg,
            R.drawable.zhuanpan_bg, R.drawable.zhuanpan_center, R.drawable.zhuanpan_bg,
            R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg, R.drawable.zhuanpan_bg};
    private int mPosition;
    // 对应转盘id的数组
    private int[] array = {0, 1, 2, 5, 8, 7, 6, 3};
    // private int[] array1 = { 0, 1, 2, 3, 5, 6, 7, 8 };
    List<PayOfterBean.DataBean.LotteryBean.RulesBean> mrules = new ArrayList<>();
   //有几次
    private int nums;
   //第几次
   private int mnums=1;
    private int m_lottery_id;
    private LuckDrawBean.DataBean data;
    private int winIndex;
    private PayOfterBean.DataBean.LotteryBean.RulesBean rulesBean;
    private boolean ischeck = true;
    private String ruleDescription="http://web.enticementchina.com/appweb/lotteryAgreement.html ";

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_ofter;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //透明状态栏
      /*  Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        tvMoney.setText("实付" + BasicApp.Constant_ZONG_MONEY);

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();

        mAdapter.setItems(mItems);

        recZhuanpan.setItemAnimator(new DefaultItemAnimator());
        BrandItemDecoration mDecoration = new BrandItemDecoration(this, 1, 1);

        recZhuanpan.addItemDecoration(mDecoration);

        mAdapter.register(PayOfterBean.DataBean.LotteryBean.RulesBean.class, new ItemZhuanPanViewBinder(this));

        mLayoutManager = new GridLayoutManager(this, 3);
        recZhuanpan.setLayoutManager(mLayoutManager);

        recZhuanpan.setAdapter(mAdapter);


        load();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayOfterActivity.this, KaQuanActivity.class));
                finish();
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayOfterActivity.this, MainActivity.class));
                Intent intentRank = new Intent(MainActivity.ACTION_GO_TO_HOME);
                LocalBroadcastManager.getInstance(PayOfterActivity.this)
                        .sendBroadcast(intentRank);
                finish();
            }
        });
        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayOfterActivity.this, MyOrderActivity.class));
                finish();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvShuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UtilsForClick.isFastClick()){
                    new XPopup.Builder(PayOfterActivity.this)
                            .dismissOnBackPressed(false)
                            .dismissOnTouchOutside(false)
                            .asCustom(new TipsPopupxieyi1(PayOfterActivity.this,
                                    ruleDescription, "活动规则", () -> {

                            }))
                            .show();
                }

            }
        });

    }

    private void load() {
        if (BasicApp.Constant_IS_NEW == 0) {
            mViewModel.payofter(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", BasicApp.Constant_GOODS_ID, "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
        } else {
            mViewModel.payofter(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "0", BasicApp.Constant_GOODS_ID, "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
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
                String img = mPayOfterBean.getData().getImg();
                String desc = mPayOfterBean.getData().getDesc();
                String replace = desc.replace("\\n", "\n");

                if (mPayOfterBean.getData().getIs_coupon() == 1) {
                    ImageLoader.loadPlaceholder1(img, imgLogo);
                    tv1.setText(replace);
                    ViewUtils.showView(ok);
                } else {
                    ImageLoader.loadPlaceholder1(img, imgLogo);
                    tv1.setText(replace);
                    ViewUtils.hideView(ok);
                }

                if (mPayOfterBean.getData().getIs_lottery() != 0) {
                    ViewUtils.showView(conChoujiang);
                    List<PayOfterBean.DataBean.LotteryBean.RulesBean> rules = mPayOfterBean.getData().getLottery().getRules();
                    rulesBean = new PayOfterBean.DataBean.LotteryBean.RulesBean();
                    nums = mPayOfterBean.getData().getLottery().getNums();
                    rulesBean.setTitle(String.valueOf(nums));
                    String content = "<font >" + "恭喜你，获得" + "<big><big>" + nums + "</big></big>" + "次抽奖机会" + "</font>";
                    tvChoujiang.setText(Html.fromHtml(content));
                    m_lottery_id = mPayOfterBean.getData().getLottery().getM_lottery_id();
                    ruleDescription = mPayOfterBean.getData().getLottery().getRuleDescription();
                    mrules.add(rules.get(0));
                    mrules.add(rules.get(1));
                    mrules.add(rules.get(2));
                    mrules.add(rules.get(7));
                    mrules.add(rulesBean);
                    mrules.add(rules.get(3));
                    mrules.add(rules.get(6));
                    mrules.add(rules.get(5));
                    mrules.add(rules.get(4));
                    mItems.clear();
                    mItems.addAll(mrules);
                    mAdapter.notifyDataSetChanged();
                } else {
                    ViewUtils.hideView(conChoujiang);
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
    public void onProdClick(PayOfterBean.DataBean.LotteryBean.RulesBean item) {
        if (ischeck && nums >= 0) {
            ischeck = false;
            mViewModel.luckDraw(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(m_lottery_id), String.valueOf(mnums), "" + AppUtils.getVersionCode(this)).observe(this, mluckObserver);
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
                mnums++;
                if (nums < 0) {
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
    public void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 3 * 8 + winIndex).setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int position = (int) animation.getAnimatedValue();
                setPosition(position % 8);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ischeck = true;
                new XPopup.Builder(PayOfterActivity.this)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new LuckDrawTipsPopup(PayOfterActivity.this, data, new LuckDrawTipsPopup.OnExitListener() {

                            @Override
                            public void onCommit(LuckDrawBean.DataBean item) {
                                mViewModel.luckDraw(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), String.valueOf(m_lottery_id), String.valueOf(nums), "" + AppUtils.getVersionCode(PayOfterActivity.this)).observe(PayOfterActivity.this, mluckObserver);

                            }

                        }))
                        .show();
            }
        });
        valueAnimator.start();
    }

    public void setPosition(int position) {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

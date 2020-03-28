package com.cuci.enticement.plate.cart.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.PayOfterBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.bean.ZhuanPanBean;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.plate.cart.adapter.ItemZhuanPanViewBinder;
import com.cuci.enticement.plate.cart.vm.CartViewModel;
import com.cuci.enticement.plate.mine.adapter.ItemYuProdViewBinder;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.GridItemDecoration;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.view.menu.MenuView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
            R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_bg, R.drawable.zhuanpan_nobg,
            R.drawable.zhuanpan_nobg, R.drawable.zhuanpan_nobg,R.drawable.zhuanpan_nobg};
    // 开始抽奖时的图片
    private int[] imgs2 = { R.drawable.checked_bg, R.drawable.checked_bg, R.drawable.checked_bg,
            R.drawable.checked_bg, R.drawable.checked_bg, R.drawable.checked_bg,
            R.drawable.checked_bg, R.drawable.checked_bg, R.drawable.checked_bg};
    private int mPosition;
    // 对应转盘id的数组
    private int[] array = { 0, 1, 2, 5, 8, 7, 6, 3 };

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhuanpan;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {



        String b = "{\"code\":1,\"info\":\"ok\",\"data\":{\"is_lottery\":0,\"lottery\":[{\n" +
                "          \"title\": \"5元优惠劵1000\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"title\": \"化妆棉\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png\"\n" +
                "        },\n" +
                "{\n" +
                "          \"title\": \"5元优惠劵1000\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"title\": \"化妆棉\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png\"\n" +
                "        },{\n" +
                "          \"title\": \"5元优惠劵1000\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"title\": \"化妆棉\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png\"\n" +
                "        },\n" +
                "{\n" +
                "          \"title\": \"5元优惠劵1000\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/5yuanyouhuiquan%402x.png\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"title\": \"化妆棉\",\n" +
                "          \"img\": \"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png\"\n" +
                "        },\n" +
                "{\n" +
                "                \"title\":\"化妆棉\",\n" +
                "                \"img\":\"https://qiniu.cdn.enticementchina.com/huazhuangmian%402x.png\"\n" +
                "            }],\"is_coupon\":0,\"coupons\":[],\"img\":\"https:\\/\\/qiniu.cdn.enticementchina.com\\/baoguo@2x.png\",\"desc\":\"派送员将会以最快的速度将包裹送到您手中\\\\n请美粉耐心等候哟~\"}}";
        ZhuanPanBean mPayOfterBean = new Gson().fromJson(b, ZhuanPanBean.class);
        List<ZhuanPanBean.DataBean.LotteryBean> lottery = mPayOfterBean.getData().getLottery();

        //透明状态栏
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mItems.addAll(lottery);
        mAdapter.setItems(mItems);

        recZhuanpan.setItemAnimator(new DefaultItemAnimator());
        BrandItemDecoration mDecoration = new BrandItemDecoration(this, 1,1);

        recZhuanpan.addItemDecoration(mDecoration);
        mAdapter.register(ZhuanPanBean.DataBean.LotteryBean.class, new ItemZhuanPanViewBinder(this));

        mLayoutManager = new GridLayoutManager(this, 3);
        recZhuanpan.setLayoutManager(mLayoutManager);

        recZhuanpan.setAdapter(mAdapter);


    }

    private void load() {
        if (ServiceCreator.Constant_IS_NEW == 1) {
            mViewModel.payofter(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "1", ServiceCreator.Constant_GOODS_ID, "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
        } else {
            mViewModel.payofter(mUserInfo.getToken(), String.valueOf(mUserInfo.getId()), "0", "684617728263", "" + AppUtils.getVersionCode(this)).observe(this, mObserver);
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


            } else if (mPayOfterBean.getCode() == HttpUtils.CODE_INVALID) {
                HttpUtils.Invalid(this);
                finish();
                FToast.error(mPayOfterBean.getInfo());
            } else {
                FToast.error(mPayOfterBean.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            FToast.error("数据错误");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onProdClick(ZhuanPanBean.DataBean.LotteryBean item) {
        startAnim();
    }

    /**
     * 开始动画
     */
    public void startAnim(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 3 * 8 + 6-1).setDuration(5000);
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

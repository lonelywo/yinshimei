package com.cuci.enticement.plate.mine.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllJiFenJiLuBean;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.DuiHuanXiangQingBean;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.QianDaoShareImgBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.bean.UserInfo;
import com.cuci.enticement.plate.mine.adapter.ItemDuiHuanDetailsViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemProdDetailsViewBinder;
import com.cuci.enticement.plate.mine.vm.MineViewModel;
import com.cuci.enticement.utils.AppUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.HttpUtils;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.WxShareUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;

public class DuiHuanJiLuDetailsActivity extends BaseActivity {

    MultipleStatusView statusView;
    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_zhuangtai)
    TextView textZhuangtai;
    @BindView(R.id.con_zhuangtai)
    ConstraintLayout conZhuangtai;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_fuzhi)
    TextView tvFuzhi;
    @BindView(R.id.con_buju1)
    ConstraintLayout conBuju1;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.con_buju3)
    ConstraintLayout conBuju3;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    private UserInfo mUserInfo;
    private MineViewModel mViewModel;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private String mInfo;
    private int mStatus;
    private int refund_state;
    private int first_buy;
    private DuiHuanXiangQingBean.DataBean data;
    @Override
    public int getLayoutId() {
        return R.layout.duihuanjilu_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mInfo = intent.getStringExtra("intentInfo");
        mUserInfo = SharedPrefUtils.get(UserInfo.class);
        if (mUserInfo == null) {
            return;
        }
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(DuiHuanXiangQingBean.DataBean.OrderListBean.class, new ItemDuiHuanDetailsViewBinder());


       /* OrderItemDecoration mDecoration = new OrderItemDecoration(this, 4);

        mRecyclerView.addItemDecoration(mDecoration);*/
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);

        tvFuzhi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // 从API11开始android推荐使用android.content.ClipboardManager
// 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 将文本内容放到系统剪贴板里。
                String order_no = mInfo;
                cm.setText(order_no);
                FToast.success("订单编号复制成功");
            }
        });
        load();
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void load() {
        mViewModel.jifenxiangqing("2",mUserInfo.getToken(), String.valueOf(mUserInfo.getId()),  mInfo, "" + AppUtils.getVersionCode(BasicApp.getContext()))
                .observe(this, mObserver);
    }


    private Observer<Status<ResponseBody>> mObserver = status -> {
        switch (status.status) {
            case Status.LOADING:
                break;
            case Status.SUCCESS:
                ResponseBody body = status.content;
                try {
                    String b = body.string();
                    DuiHuanXiangQingBean mDuiHuanXiangQingBean = new Gson().fromJson(b, DuiHuanXiangQingBean.class);
                    if (mDuiHuanXiangQingBean.getCode() == 1) {
                         data = mDuiHuanXiangQingBean.getData();
                        int status1 = data.getStatus();
                        initViewStatus(status1);
                        initContent();
                        List<DuiHuanXiangQingBean.DataBean.OrderListBean> order_list = data.getOrder_list();
                        mItems.clear();
                        mItems.addAll(order_list);
                        mAdapter.notifyDataSetChanged();
                    } else if (mDuiHuanXiangQingBean.getCode() == HttpUtils.CODE_INVALID) {
                        HttpUtils.Invalid(this);
                        finish();
                        FToast.error(mDuiHuanXiangQingBean.getInfo());
                    } else {
                        FToast.error(mDuiHuanXiangQingBean.getInfo());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case Status.ERROR:

                FToast.error("网络错误");
                break;
        }


    };
    private void initViewStatus(int status) {
        if(status==3){
            textZhuangtai.setText("待发货");
        }else if(status==4){
            textZhuangtai.setText("已发货");
        }else if(status==5){
            textZhuangtai.setText("已完成");
        }


    }
    private void initContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(data.getExpress_name()).append(" ")
                .append(data.getExpress_phone()).append(" ").append("\n")
                .append(data.getExpress_province()).append(" ")
                .append(data.getExpress_city()).append(" ")
                .append(data.getExpress_area()).append(" ")
                .append(data.getExpress_address());
        tvOrderNo.setText(String.format(Locale.CHINA, "订单编号: %s", data.getOrder_no()));
        textDizi.setText(sb.toString());
        tvGoodsMoney.setText(String.format(Locale.CHINA, "积分%s", data.getPay_points()));
        tvExpress.setText(String.format(Locale.CHINA, "%s", MathExtend.moveone(data.getExpress_rule_desc())));
        tvTotalMoney.setText(String.format(Locale.CHINA, "积分%s", data.getPay_points()));
        tvCreateTime.setText(data.getCreate_at());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.plate.mine.adapter.ItemProdDetailsViewBinder;
import com.cuci.enticement.widget.OrderItemDecoration;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class TuiDetailsNOActivity extends BaseActivity implements ItemProdDetailsViewBinder.OnProdClickListener {


    @BindView(R.id.image_top)
    TextView imageTop;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.text_zhuangtai)
    TextView textZhuangtai;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.con_zhuangtai)
    ConstraintLayout conZhuangtai;
    @BindView(R.id.text_dizi)
    TextView textDizi;
    @BindView(R.id.text_dizi1)
    TextView textDizi1;
    @BindView(R.id.con_buju1)
    ConstraintLayout conBuju1;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.recycler_view0)
    RecyclerView mRecyclerView;
    @BindView(R.id.jizhun1)
    TextView jizhun1;
    @BindView(R.id.jizhun2)
    TextView jizhun2;
    @BindView(R.id.jizhun3)
    TextView jizhun3;
    @BindView(R.id.con_buju3)
    ConstraintLayout conBuju3;
    private int type;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private int mStatus;

    @Override
    public int getLayoutId() {
        return R.layout.tui_details_no;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        type = intent.getIntExtra("type", 0);
        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");
        List<OrderGoods> items = mInfo.getList();
        mStatus = mInfo.getStatus();
        MultiTypeAdapter mAdapter0 = new MultiTypeAdapter();
        Items mItems0 = new Items();
        mItems0.addAll(items);
        mAdapter0.setItems(mItems0);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter0.register(OrderGoods.class, new ItemProdDetailsViewBinder(this, mStatus));


        OrderItemDecoration mDecoration0 = new OrderItemDecoration(this, 4);

        mRecyclerView.addItemDecoration(mDecoration0);
        LinearLayoutManager mLayoutManager0 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager0);

        mRecyclerView.setAdapter(mAdapter0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onProdClick(OrderGoods item) {

    }
}

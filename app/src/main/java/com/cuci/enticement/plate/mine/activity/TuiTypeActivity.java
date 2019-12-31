package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class TuiTypeActivity extends BaseActivity implements ItemProdDetailsViewBinder.OnProdClickListener {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.img_tuxiang)
    ImageView imgTuxiang;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.text_wenzi2)
    TextView textWenzi2;
    @BindView(R.id.con_tuikuan1)
    ConstraintLayout conTuikuan1;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.img_tuxiang1)
    ImageView imgTuxiang1;
    @BindView(R.id.text_wenzi3)
    TextView textWenzi3;
    @BindView(R.id.text_wenzi4)
    TextView textWenzi4;
    @BindView(R.id.con_tuikuan2)
    ConstraintLayout conTuikuan2;
    private AllOrderList.DataBean.ListBeanX mInfo;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private int mStatus;
    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_type;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");
        List<OrderGoods> items = mInfo.getList();
        mStatus = mInfo.getStatus();
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mItems.addAll(items);
        mAdapter.setItems(mItems);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(OrderGoods.class, new ItemProdDetailsViewBinder(this, mStatus));


        OrderItemDecoration mDecoration = new OrderItemDecoration(this, 4);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.con_tuikuan1, R.id.con_tuikuan2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.con_tuikuan1:
                Intent intent1 = new Intent(this, ApplyTuiActivity.class);
                intent1.putExtra("intentInfo", mInfo);
                intent1.putExtra("type", 1);
                startActivity(intent1);
                break;
            case R.id.con_tuikuan2:
                Intent intent2 = new Intent(this, ApplyTuiActivity.class);
                intent2.putExtra("intentInfo", mInfo);
                intent2.putExtra("type", 2);
                startActivity(intent2);
                break;

        }
    }

    @Override
    public void onProdClick(OrderGoods item) {

    }
}

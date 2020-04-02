package com.cuci.enticement.plate.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.plate.mine.adapter.ItemProdDetailsViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemTuiTypeViewBinder;
import com.cuci.enticement.utils.UtilsForClick;
import com.cuci.enticement.utils.ViewUtils;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.cuci.enticement.widget.SmoothScrollview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class TuiTypeActivity extends BaseActivity implements ItemTuiTypeViewBinder.OnProdClickListener {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.img_zhankai)
    ImageView imgZhankai;
    @BindView(R.id.con_tuikuan1)
    ConstraintLayout conTuikuan1;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.con_tuikuan2)
    ConstraintLayout conTuikuan2;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private OrderGoods mItem;
    //商品总数
    private List<OrderGoods> items;
    //当前商品数量
    private List<OrderGoods> mlist=new ArrayList<>();
    private AllOrderList.DataBean.ListBeanX mInfo;

    //跳转商品数量
    private List<OrderGoods> mmlist=new ArrayList<>();
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
        mItem = ( OrderGoods) intent.getSerializableExtra("intentItem");
        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");
        if(mInfo!=null){
            items=mInfo.getList();
        }
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mmlist.clear();
        mItems.clear();
        mlist.clear();
        if(mInfo!=null){
            int size = items.size();
            if(size>2){
                for (int i = 0; i <2 ; i++) {
                    mlist.add(items.get(i));
                }
                mItems.addAll(mlist);
                ViewUtils.showView(imgZhankai);
            }else {
                mItems.addAll(items);
                ViewUtils.hideView(imgZhankai);
            }
            mmlist.addAll(items);
        }else {
            mmlist.add(mItem);
            mItems.add(mItem) ;
            ViewUtils.hideView(imgZhankai);
        }

        mAdapter.setItems(mItems);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(OrderGoods.class, new ItemTuiTypeViewBinder(this));


        OrderItemDecoration mDecoration = new OrderItemDecoration(this, 1);

        mRecyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
        imgZhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UtilsForClick.isFastClick()){
                    mItems.clear();
                    mItems.addAll(items);
                    mAdapter.notifyDataSetChanged();
                    ViewUtils.hideView(imgZhankai);
                }

            }
        });
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
                Intent intent1 = new Intent(this, TuiKuanType1Activity.class);
                intent1.putExtra("intentInfo", (Serializable)mmlist);
                intent1.putExtra("type",1);
                startActivity(intent1);
                break;
            case R.id.con_tuikuan2:
                Intent intent2 = new Intent(this, TuiKuanType2Activity.class);
                intent2.putExtra("intentInfo", (Serializable)mmlist);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;

        }
    }

    @Override
    public void onProdClick(OrderGoods item) {

    }
}

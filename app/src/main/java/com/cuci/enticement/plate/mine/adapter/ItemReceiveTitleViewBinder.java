package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderTitle;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemReceiveTitleViewBinder extends ItemViewBinder<ItemOrderTitle, ItemReceiveTitleViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_title, parent, false);
        return new ViewHolder(root);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemOrderTitle itemOrderTitle) {
        holder.textBianhao.setText(String.format(Locale.CHINA,"订单号:%s",itemOrderTitle.orderNum));
       /* status 0 已经取消的订单，包含已经退款的订单
        status 1 预订单，还没有收货地址，需要确认后才能支付（此状态不存在）
        status 2 新订单，待支付
        status 3 已支付，待发货
        status 4 已发货，待完成收货
        status 5 已确认收货，订单完成*/
       switch (itemOrderTitle.status){
           case 0:
               holder.textZhuangtai.setText("已取消");

               break;
           case 1:
               holder.textZhuangtai.setText("待付款");
               break;
           case 2:
               holder.textZhuangtai.setText("待付款");
               break;
           case 3:
               holder.textZhuangtai.setText("待发货");
               break;
           case 4:
               holder.textZhuangtai.setText("待收货");
               break;
           case 5:
               holder.textZhuangtai.setText("已完成");
               break;
           case 6:
               holder.textZhuangtai.setText("已退货");
               break;

       }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_bianhao)
        TextView textBianhao;
        @BindView(R.id.text_zhuangtai)
        TextView textZhuangtai;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
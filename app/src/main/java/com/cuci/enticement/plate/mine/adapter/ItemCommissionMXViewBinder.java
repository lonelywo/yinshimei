package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.CommissionmxBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemCommissionMXViewBinder extends ItemViewBinder<CommissionmxBean.DataBean.ListBean, ItemCommissionMXViewBinder.ViewHolder> {

   /* public interface OnProdClickListener {

        void onProdClick(GoodsBean item);

    }
    private OnProdClickListener mOnProdClickListener;

    public ItemCommissionJLViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }*/

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_cashmx, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CommissionmxBean.DataBean.ListBean item) {
        if (item.getStatus() == 0) {
            holder.textWenzi1.setText("提现失败");
        } else if (item.getStatus() == 1) {
            holder.textWenzi1.setText("代付款");
        } else if (item.getStatus() == 2) {
            holder.textWenzi1.setText("付款成功");
        }
        holder.textTime.setText(item.getCreate_at());
        holder.textMoney.setText("+"+item.getPay_price());


/*
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());

        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(item);
            }
        });*/

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_money)
        TextView textMoney;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

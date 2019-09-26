package com.cuci.enticement.plate.cart.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ExpressInfo;
import com.cuci.enticement.bean.OrderGoods;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemLogisticsViewBinder extends ItemViewBinder<ExpressInfo.DataBeanX.DataBean, ItemLogisticsViewBinder.ViewHolder> {



    public interface OnItemClickListener {

        void onAddClick(OrderGoods bean, int position);

        void onMinusClick(OrderGoods bean, int position);

        void onCheckedChange();

        void onDelete(OrderGoods bean);
    }

    private OnItemClickListener mOnItemClickListener;

   /* public ItemLogisticsViewBinder(ItemLogisticsViewBinder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }*/

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_wuliu, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ExpressInfo.DataBeanX.DataBean item) {


        holder.textQianshouxinxi.setText(item.getContext());
        holder.textTime.setText(item.getTime());
        int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition==1) {
            holder.imgWuliutubiao.setImageResource(R.drawable.wuliutubiao_huang);
        } else {
            holder.imgWuliutubiao.setImageResource(R.drawable.wuliutubiao_hui);
        }




    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_wuliutubiao)
        ImageView imgWuliutubiao;
        @BindView(R.id.text_qianshouxinxi)
        TextView textQianshouxinxi;
        @BindView(R.id.text_time)
        TextView textTime;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

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
import com.cuci.enticement.utils.ViewUtils;

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
        int itemCount = getAdapter().getItemCount();
        if (adapterPosition == 0) {
            holder.imgWuliutubiao.setImageResource(R.drawable.wuliutubiao_huang);
            ViewUtils.hideView(holder.line);
            ViewUtils.showView(holder.line2);
        } else if(adapterPosition==itemCount-1){
            holder.imgWuliutubiao.setImageResource(R.drawable.wuliutubiao_hui);
            ViewUtils.showView(holder.line);
            ViewUtils.hideView(holder.line2);

        }else {
            holder.imgWuliutubiao.setImageResource(R.drawable.wuliutubiao_hui);
            ViewUtils.showView(holder.line);
            ViewUtils.showView(holder.line2);
        }


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_wuliutubiao)
        ImageView imgWuliutubiao;
        @BindView(R.id.text_qianshouxinxi)
        TextView textQianshouxinxi;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.line2)
        View line2;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.CommissionjlBean;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemCommissionJLViewBinder extends ItemViewBinder<CommissionjlBean.DataBean.ListBean, ItemCommissionJLViewBinder.ViewHolder> {





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
        View root = inflater.inflate(R.layout.rec_commisson_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CommissionjlBean.DataBean.ListBean item) {

        ImageLoader.loadPlaceholder(item.getMember().getHeadimg(), holder.imgTuxiang);
        holder.textWenzi1.setText(item.getMember().getNickname());
        holder.textWenzi2.setText(item.getDesc());
        holder.textMoney.setText("+"+item.getProfit_price());
        holder.textTime.setText(item.getCreate_at());



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
        @BindView(R.id.img_tuxiang)
        ImageView imgTuxiang;
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_wenzi2)
        TextView textWenzi2;
        @BindView(R.id.text_money)
        TextView textMoney;
        @BindView(R.id.text_time)
        TextView textTime;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

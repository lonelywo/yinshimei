package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderList.DataBean.OrderBean.GoodsBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemCommissionJLViewBinder extends ItemViewBinder<GoodsBean, ItemCommissionJLViewBinder.ViewHolder> {




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
        View root = inflater.inflate(R.layout.rec_myorder_prod, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GoodsBean item) {


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
        @BindView(R.id.img_tupian)
        ImageView imgTupian;
        @BindView(R.id.text_biaoti)
        TextView textBiaoti;
        @BindView(R.id.text_neirong)
        TextView textNeirong;
        @BindView(R.id.text_qian)
        TextView textQian;
        @BindView(R.id.text_num)
        TextView textNum;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

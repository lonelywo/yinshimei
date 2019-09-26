package com.cuci.enticement.plate.cart.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.CartDataBean;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemCartViewBinder extends ItemViewBinder<OrderGoods, ItemCartViewBinder.ViewHolder> {

    public interface OnItemClickListener {

        void onAddClick(OrderGoods bean,int position);

        void onMinusClick(OrderGoods bean,int position);
        void onCheckedChange();
        void onDelete(OrderGoods bean);
    }

    private ItemCartViewBinder.OnItemClickListener mOnItemClickListener;

    public ItemCartViewBinder(ItemCartViewBinder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_cart, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OrderGoods item) {


        if (item.isCheck()) {
            holder.mImageCheck.setImageResource(R.drawable.xuanzhong);
        } else {
            holder.mImageCheck.setImageResource(R.drawable.noxuanzhong);
        }

        holder.mImageCheck.setOnClickListener(v -> {
            item.setCheck(!item.isCheck());
            if (item.isCheck()) {
                holder.mImageCheck.setImageResource(R.drawable.xuanzhong);
            } else {
                holder.mImageCheck.setImageResource(R.drawable.noxuanzhong);
            }
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onCheckedChange();
            }
        });

        holder.textBiaoti.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onDelete(item);
            }
        });
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textJiage.setText(item.getGoods_price_selling());
        holder.tvNum.setText(String.valueOf(item.getGoods_num()));
        ImageLoader.loadPlaceholder(item.getGoods_logo(),holder.imgTuxiang);


        int position = holder.getAdapterPosition();
        holder.ivJia.setOnClickListener(v -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onAddClick(item,position);
            }
        });

        holder.ivJian.setOnClickListener(v -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onMinusClick(item,position);
            }
        });


    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_tuxiang)
        ImageView imgTuxiang;

        @BindView(R.id.image_check)
        ImageView mImageCheck;
        @BindView(R.id.text_biaoti)
        TextView textBiaoti;
        @BindView(R.id.text_neirong)
        TextView textNeirong;
        @BindView(R.id.text_jiage)
        TextView textJiage;

        @BindView(R.id.text_shuzi)
        TextView tvNum;
        @BindView(R.id.img_jia)
        ImageView ivJia;
        @BindView(R.id.img_jian)
        ImageView ivJian;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

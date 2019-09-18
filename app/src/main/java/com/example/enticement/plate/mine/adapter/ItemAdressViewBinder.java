package com.example.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.example.enticement.bean.AdressBean;
import com.example.enticement.bean.CartListBean;
import com.example.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemAdressViewBinder extends ItemViewBinder<AdressBean.DataBean.ListBean, ItemAdressViewBinder.ViewHolder> {

    public interface OnItemClickListener {

        void onEditClick(AdressBean.DataBean.ListBean bean);
        void onCheckAdress(AdressBean.DataBean.ListBean bean);


    }

    private ItemAdressViewBinder.OnItemClickListener mOnItemClickListener;

    public ItemAdressViewBinder(ItemAdressViewBinder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_cart, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AdressBean.DataBean.ListBean item) {





       /* holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textJiage.setText(item.getGoods_price_selling());
        ImageLoader.loadPlaceholder(item.getGoods_logo(),holder.imgTuxiang);*/
     /*   holder.textJia.setOnClickListener(v -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onAddClick(item);
            }
        });

        holder.textJian.setOnClickListener(v -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onMinusClick(item);
            }
        });*/


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

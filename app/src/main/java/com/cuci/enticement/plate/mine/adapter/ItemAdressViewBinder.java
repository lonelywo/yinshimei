package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.AddressBean;
import com.cuci.enticement.utils.SharedPrefUtils;
import com.cuci.enticement.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemAdressViewBinder extends ItemViewBinder<AddressBean.DataBean.ListBean, ItemAdressViewBinder.ViewHolder> {



    public interface OnItemClickListener {

        void onEditClick(AddressBean.DataBean.ListBean bean);

        void onCheckAdress(AddressBean.DataBean.ListBean bean);

        void onDelete(AddressBean.DataBean.ListBean bean,int position);


    }

    private OnItemClickListener mOnItemClickListener;

    public ItemAdressViewBinder(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_address, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AddressBean.DataBean.ListBean item) {

        if(item.getIs_default()==1){
            ViewUtils.showView(holder.defaultTv);
            SharedPrefUtils.saveDefaultAdressId(String.valueOf(item.getId()));
            StringBuilder sb = new StringBuilder();
            sb.append(item.getName()).append(" ")
                    .append(item.getPhone()).append(" ")
                    .append(item.getProvince()).append(" ")
                    .append(item.getCity()).append(" ")
                    .append(item.getArea()).append(" ")
                    .append(item.getAddress());
            SharedPrefUtils.saveDefaultAdress(sb.toString());
        }else {
            ViewUtils.hideView(holder.defaultTv);
        }





        holder.textName.setText(item.getName());

        StringBuilder builder=new StringBuilder();
        builder.append(item.getProvince()).append(" ").append(item.getCity()).append(" ").append(item.getArea())
                .append(" ").append(item.getAddress());
        holder.textDizi.setText(builder.toString());
        holder.textPhone.setText(item.getPhone());




        holder.itemView.setOnClickListener(view -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onCheckAdress(item);
            }
        });

        holder.editTv.setOnClickListener(view -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onEditClick(item);
            }
        });



        int position=holder.getAdapterPosition();
        holder.textShanchu.setOnClickListener(view -> {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onDelete(item,position);
            }
        });



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

        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_phone)
        TextView textPhone;
        @BindView(R.id.text_dizi)
        TextView textDizi;
        @BindView(R.id.default_tv)
        TextView defaultTv;
        @BindView(R.id.text_shanchu)
        TextView textShanchu;
        @BindView(R.id.edit_tv)
        TextView editTv;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

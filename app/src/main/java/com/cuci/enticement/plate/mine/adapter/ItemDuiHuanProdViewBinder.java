package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.AllJiFenJiLuBean;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.google.gson.Gson;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemDuiHuanProdViewBinder extends ItemViewBinder<AllJiFenJiLuBean.DataBean.ListBean.OrderListBean, ItemDuiHuanProdViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(AllJiFenJiLuBean.DataBean.ListBean.OrderListBean item);


    }

    private OnProdClickListener mOnProdClickListener;

    public ItemDuiHuanProdViewBinder() {

    }

    public ItemDuiHuanProdViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_duihuan_prod, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AllJiFenJiLuBean.DataBean.ListBean.OrderListBean item) {

        String s = new Gson().toJson(item);
        ImageLoader.loadPlaceholder(item.getGoods_logo(),holder.imgTupian);
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textQian.setText(String.format(Locale.CHINA,"积分：%s", item.getPoints_selling()));
        holder.textNum.setText(String.format(Locale.CHINA,"x%s",item.getNumber()));
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(item);
            }
        });





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

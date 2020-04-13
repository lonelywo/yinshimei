package com.cuci.enticement.plate.cart.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.TuiKuanXiangQingBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * 预订单使用
 */
public class ItemTuikuanProdViewBinder extends ItemViewBinder<TuiKuanXiangQingBean.DataBean.RefundBean.OrderRefundListBean, ItemTuikuanProdViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(TuiKuanXiangQingBean.DataBean.RefundBean.OrderRefundListBean item);


    }

    private OnProdClickListener mOnProdClickListener;
    private int mis_new;

    public ItemTuikuanProdViewBinder() {

    }

    public ItemTuikuanProdViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;

    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_prod, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TuiKuanXiangQingBean.DataBean.RefundBean.OrderRefundListBean item) {


        ImageLoader.loadPlaceholder(item.getGoods_logo(),holder.imgTupian);
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textQian.setText(String.format(Locale.CHINA,"¥%s",MathExtend.moveone(item.getPrice_sales())));
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

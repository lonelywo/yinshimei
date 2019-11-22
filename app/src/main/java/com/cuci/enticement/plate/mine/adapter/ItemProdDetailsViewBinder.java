package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;
import com.google.gson.Gson;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemProdDetailsViewBinder extends ItemViewBinder<OrderGoods, ItemProdDetailsViewBinder.ViewHolder> {


    private int mStatus;

    public interface OnProdClickListener {

        void onProdClick(OrderGoods item);


    }

    private OnProdClickListener mOnProdClickListener;

    public ItemProdDetailsViewBinder() {

    }

    public ItemProdDetailsViewBinder(OnProdClickListener onProdClickListener,int Status) {
        mOnProdClickListener = onProdClickListener;
        mStatus=Status;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_proddetails, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OrderGoods item) {

        String s = new Gson().toJson(item);
        ImageLoader.loadPlaceholder(item.getGoods_logo(), holder.imgTupian);
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textQian.setText(String.format(Locale.CHINA, "%s", item.getPrice_sales()));
        holder.textNum.setText(String.format(Locale.CHINA, "x%s", item.getNumber()));
        if(mStatus==0){
            ViewUtils.hideView(holder.textTuikuan);
        }else {
            ViewUtils.hideView(holder.textTuikuan);
        }
        holder.textTuikuan.setOnClickListener(position -> {
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
        @BindView(R.id.text_tuikuan)
        TextView textTuikuan;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ReceiveGoods;
import com.cuci.enticement.utils.ImageLoader;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemReceiveViewBinder extends ItemViewBinder<ReceiveGoods, ItemReceiveViewBinder.ViewHolder> {




    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_receive, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ReceiveGoods mOrderGoods) {
        ImageLoader.loadPlaceholder(mOrderGoods.getGoods_logo(), holder.imgTupian);
        holder.textBiaoti.setText(mOrderGoods.getGoods_title());
        holder.textQian.setText(String.format(Locale.CHINA, "%s", mOrderGoods.getPrice_selling()));
        holder.textNum.setText(String.format(Locale.CHINA, "x%s", mOrderGoods.getNumber()));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.line)
        View line;
        @BindView(R.id.img_tupian)
        ImageView imgTupian;
        @BindView(R.id.text_biaoti)
        TextView textBiaoti;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.text_qian)
        TextView textQian;
        @BindView(R.id.text_num)
        TextView textNum;
        @BindView(R.id.con_buju)
        ConstraintLayout conBuju;
        @BindView(R.id.container)
        ConstraintLayout container;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

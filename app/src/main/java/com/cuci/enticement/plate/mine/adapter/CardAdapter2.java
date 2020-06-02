package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.CardBean;
import com.cuci.enticement.utils.ImageLoader;

import java.util.List;

/**
 * Created by linchen on 2018/1/26.
 * mail: linchen@sogou-inc.com
 */

public class CardAdapter2 extends RecyclerView.Adapter<CardAdapter2.CardHolder> {

    private List<CardBean> mCardBeanList;
    private RequestOptions mRequestOptions;

    public CardAdapter2(List<CardBean> cardBeanList) {
        mCardBeanList = cardBeanList;
        mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.drawable.poster2).error(R.drawable.poster2).diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_img_txt2, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

        final CardBean bean = mCardBeanList.get(position);


        Glide.with(holder.itemView).load(bean.getPoster()).apply(mRequestOptions).into(holder.img_tupian);
        ImageLoader.loadPlaceholder(bean.getQrcode(),holder.qrcode);
        holder.text_name.setText(bean.getNickname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.img_tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardBeanList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        ImageView qrcode;
        ImageView img_tupian;
        TextView text_name;


        public CardHolder(View itemView) {
            super(itemView);
            qrcode = itemView.findViewById(R.id.qrcode);
            img_tupian = itemView.findViewById(R.id.img_tupian);
            text_name = itemView.findViewById(R.id.text_name);
        }
    }
}

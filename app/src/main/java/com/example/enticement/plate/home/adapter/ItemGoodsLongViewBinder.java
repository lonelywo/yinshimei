package com.example.enticement.plate.home.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.example.enticement.bean.GeneralGoodsItem;
import com.example.enticement.utils.ImageLoader;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemGoodsLongViewBinder extends ItemViewBinder<GeneralGoodsItem, ItemGoodsLongViewBinder.ViewHolder> {



    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.home_goods, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GeneralGoodsItem item) {
        ImageLoader.loadPlaceholder(item.getLogo(),holder.image_home);
        holder.text_home_goodsname.setText(item.getTitle());
        holder.text_home_money.setText("¥" + item.getLists().get(0).get(0).getSelling());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image_home)
        ImageView image_home;
        @BindView(R.id.text_home_money)
        TextView text_home_money;
        @BindView(R.id.text_home_goodsname)
        TextView text_home_goodsname;
        @BindView(R.id.card_view)
        CardView mCardView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

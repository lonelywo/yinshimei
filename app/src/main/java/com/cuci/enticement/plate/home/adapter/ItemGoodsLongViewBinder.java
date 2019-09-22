package com.cuci.enticement.plate.home.adapter;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.GoodsItem;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.utils.ImageLoader;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemGoodsLongViewBinder extends ItemViewBinder<GoodsItem, ItemGoodsLongViewBinder.ViewHolder> {

    private final Context mContext;

        public ItemGoodsLongViewBinder(Context context){
              mContext=context;
          }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.home_goods, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GoodsItem item) {
        ImageLoader.loadPlaceholder(item.getLogo(),holder.image_home);
        holder.text_home_goodsname.setText(item.getTitle());
        holder.text_home_money.setText("¥" + item.getLists().get(0).get(0).getSelling());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(mContext, ProdActivity.class);
                intentProd.putExtra("bannerData",String.valueOf(item.getId()));
                mContext.startActivity(intentProd);
            }
        });

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

package com.cuci.enticement.plate.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.GoodsItem;
import com.cuci.enticement.plate.home.activity.ProdActivity;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.UtilFonts;
import com.cuci.enticement.utils.UtilsForClick;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemGoodsLongViewBinder extends ItemViewBinder<GoodsItem, ItemGoodsLongViewBinder.ViewHolder> {

    private final Context mContext;


    public ItemGoodsLongViewBinder(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.home_goods, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GoodsItem item) {
        ImageLoader.loadPlaceholder(item.getLogo(), holder.imageHome);
    /*    holder.textHomeGoodsname.setText(item.getTitle());
      *//*  holder.textHomeMoney.setText("原价¥" + item.getInitial_price_market());
        holder.textHomeMoneyVip.setText("会员价¥" + item.getInitial_price_selling());*//*
        Typeface set = UtilFonts.set("fonts/DINPro-Regular.ttf");
        holder.textHomeMoney.setTypeface(set);
        holder.textHomeMoneyVip.setTypeface(set);
        if (item.getVip_mod() == 1) {
            String strMsg = "原价¥ " + MathExtend.moveone(item.getInitial_price_market());
            holder.textHomeMoney.setText(Html.fromHtml(strMsg));
            String strMsg2 = "<font >" + item.getPricename() + "¥ " + "<big><big>" + MathExtend.moveone(item.getInitial_price_selling()) + "</big></big>" + "</font>";
            holder.textHomeMoneyVip.setText(Html.fromHtml(strMsg2));
        } else {
            String strMsg = "原价¥ " + MathExtend.moveone(item.getInitial_price_market());
            holder.textHomeMoney.setText(Html.fromHtml(strMsg));
            String strMsg2 = "<font >" + "会员价¥ " + "<big><big>" + MathExtend.moveone(item.getInitial_price_selling()) + "</big></big>" + "</font>";
            holder.textHomeMoneyVip.setText(Html.fromHtml(strMsg2));
        }*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UtilsForClick.isFastClick()) {

                    Intent intentProd = new Intent(mContext, ProdActivity.class);
                    intentProd.putExtra("bannerData", String.valueOf(item.getId()));
                    mContext.startActivity(intentProd);

                }

            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image_home)
        ImageView imageHome;
       /* @BindView(R.id.text_home_goodsname)
        TextView textHomeGoodsname;
        @BindView(R.id.text_home_money)
        TextView textHomeMoney;
        @BindView(R.id.text_home_money_vip)
        TextView textHomeMoneyVip;*/
        @BindView(R.id.card_view)
        CardView cardView;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

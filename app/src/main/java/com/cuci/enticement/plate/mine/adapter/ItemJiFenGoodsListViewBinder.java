package com.cuci.enticement.plate.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.JiFenMallBean;
import com.cuci.enticement.bean.JiFenMingXiBean;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemJiFenGoodsListViewBinder extends ItemViewBinder<JiFenMallBean.DataBean.ListBean, ItemJiFenGoodsListViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(int id);

    }

    private OnProdClickListener mOnProdClickListener;

    public ItemJiFenGoodsListViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.duihuanshangcheng_goods, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull JiFenMallBean.DataBean.ListBean item) {
        int adapterPosition = holder.getAdapterPosition();

        ImageLoader.loadPlaceholder(item.getLogo(),holder.imageHome);
        holder.textHomeGoodsname.setText(item.getTitle());
        holder.textHomeMoneyVip.setText(item.getPoints()+"积分");
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(adapterPosition);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_home)
        ImageView imageHome;
        @BindView(R.id.text_home_goodsname)
        TextView textHomeGoodsname;
        @BindView(R.id.text_home_money_vip)
        TextView textHomeMoneyVip;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

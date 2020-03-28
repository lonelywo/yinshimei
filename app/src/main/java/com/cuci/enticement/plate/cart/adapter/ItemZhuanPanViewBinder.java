package com.cuci.enticement.plate.cart.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.ZhuanPanBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * 转盘使用
 */
public class ItemZhuanPanViewBinder extends ItemViewBinder<ZhuanPanBean.DataBean.LotteryBean, ItemZhuanPanViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(ZhuanPanBean.DataBean.LotteryBean item);


    }

    private OnProdClickListener mOnProdClickListener;


    public ItemZhuanPanViewBinder() {

    }

    public ItemZhuanPanViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;

    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_zhuanpan_prod, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ZhuanPanBean.DataBean.LotteryBean item) {

        int adapterPosition = holder.getAdapterPosition();
        ImageLoader.loadPlaceholder(item.getImg(), holder.imgToubu);


        if (adapterPosition == 4) {
            holder.itemView.setBackgroundColor(BasicApp.getContext().getResources().getColor(R.color.red));
            ViewUtils.hideView(holder.tvDibu);
        } else {
            holder.itemView.setBackground(BasicApp.getContext().getResources().getDrawable(R.drawable.zhuanpan_nobg));
            holder.tvDibu.setText(item.getTitle());
            ViewUtils.showView(holder.tvDibu);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                if (adapterPosition==4){
                    mOnProdClickListener.onProdClick(item);
                }else {

                }

            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_toubu)
        ImageView imgToubu;
        @BindView(R.id.tv_dibu)
        TextView tvDibu;
        @BindView(R.id.container)
        ConstraintLayout container;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

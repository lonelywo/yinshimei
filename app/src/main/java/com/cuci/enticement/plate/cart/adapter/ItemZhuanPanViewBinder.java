package com.cuci.enticement.plate.cart.adapter;


import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.PayOfterBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * 转盘使用
 */
public class ItemZhuanPanViewBinder extends ItemViewBinder<PayOfterBean.DataBean.LotteryBean.RulesBean, ItemZhuanPanViewBinder.ViewHolder> {


    // 对应转盘id的数组
    private int[] array = { 0, 1, 2, 5, 8, 7, 6, 3 };

    public interface OnProdClickListener {

        void onProdClick(PayOfterBean.DataBean.LotteryBean.RulesBean item);


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
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PayOfterBean.DataBean.LotteryBean.RulesBean item) {

        int adapterPosition = holder.getAdapterPosition();
        ImageLoader.loadPlaceholder(item.getImg(), holder.imgToubu);


        if (adapterPosition == 4) {
            holder.itemView.setBackground(BasicApp.getContext().getResources().getDrawable(R.drawable.zhuanpan_center));
          /*  if(TextUtils.equals(item.getTitle(),"3")){
                String content = "你有"+"<font color=\"#EA333f\">" + item.getTitle() +"次"+ "</font>"+"机会";
                holder.tvDibu.setText(Html.fromHtml(content));
            }else if(TextUtils.equals(item.getTitle(),"2")){
                String content = "你还有"+"<font color=\"#EA333f\">" + item.getTitle() +"次"+ "</font>"+"机会";
                holder.tvDibu.setText(Html.fromHtml(content));
            }else if(TextUtils.equals(item.getTitle(),"1")){
                String content = "你还有"+"<font color=\"#EA333f\">" + item.getTitle() +"次"+ "</font>"+"机会";
                holder.tvDibu.setText(Html.fromHtml(content));
            }*/
            String content = "你有"+"<font color=\"#EA333f\">" + item.getTitle() +"次"+ "</font>"+"机会";
            holder.tvDibu.setText(Html.fromHtml(content));
            ViewUtils.hideView(holder.imgToubu);
            ViewUtils.showView(holder.tvToubu);
        } else {
            holder.itemView.setBackground(BasicApp.getContext().getResources().getDrawable(R.drawable.zhuanpan_nobg));
            holder.tvDibu.setText(item.getTitle());
            ViewUtils.showView(holder.imgToubu);
            ViewUtils.hideView(holder.tvToubu);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                if (adapterPosition == 4) {
                    mOnProdClickListener.onProdClick(item);
                } else {

                }

            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_toubu)
        ImageView imgToubu;
        @BindView(R.id.tv_toubu)
        TextView tvToubu;
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

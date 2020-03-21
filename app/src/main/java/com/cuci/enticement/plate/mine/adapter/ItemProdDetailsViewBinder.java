package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.ViewUtils;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemProdDetailsViewBinder extends ItemViewBinder<OrderGoods, ItemProdDetailsViewBinder.ViewHolder> {



    private int mStatus;

    public interface OnProdClickListener {

        void onProdClick(OrderGoods item);

        void onProdItemClick(OrderGoods item);
    }

    private OnProdClickListener mOnProdClickListener;

    public ItemProdDetailsViewBinder() {

    }

    public ItemProdDetailsViewBinder(OnProdClickListener onProdClickListener, int Status) {
        mOnProdClickListener = onProdClickListener;
        mStatus = Status;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_prod_xq, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OrderGoods item) {

        if (mStatus == 0 || mStatus == 2) {
            ViewUtils.hideView(holder.tuikuanTv);
        }else if(mStatus == 3 || mStatus == 4){
            holder.tuikuanTv.setText("退款");
            ViewUtils.showView(holder.tuikuanTv);
        }else if(mStatus == 5 ){
            holder.tuikuanTv.setText("售后");
            ViewUtils.showView(holder.tuikuanTv);
        } else if(mStatus == 6){
            holder.tuikuanTv.setText("退款成功");
            ViewUtils.showView(holder.tuikuanTv);
        }
        ImageLoader.loadPlaceholder(item.getGoods_logo(), holder.imgTupian);
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textQian.setText(String.format(Locale.CHINA, "¥%s", MathExtend.moveone(item.getPrice_sales())));
        holder.textNum.setText(String.format(Locale.CHINA, "x%s", item.getNumber()));

        holder.tuikuanTv.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(item);
            }
        });
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdItemClick(item);
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
        @BindView(R.id.tuikuan_tv)
        TextView tuikuanTv;
        @BindView(R.id.con_buju)
        ConstraintLayout conBuju;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.container)
        ConstraintLayout container;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

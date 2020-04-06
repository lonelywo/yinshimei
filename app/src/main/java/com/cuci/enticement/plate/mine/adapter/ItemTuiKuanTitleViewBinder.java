package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderTitle;
import com.cuci.enticement.bean.ItemTuikuaiOrderTitle;
import com.cuci.enticement.utils.UtilsForClick;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemTuiKuanTitleViewBinder extends ItemViewBinder<ItemTuikuaiOrderTitle, ItemTuiKuanTitleViewBinder.ViewHolder> {


    public interface OnProdTitleClickListener {

        void onProdTitleClick(ItemTuikuaiOrderTitle item);


    }
    private OnProdTitleClickListener mOnProdTitleClickListener;


    public ItemTuiKuanTitleViewBinder(OnProdTitleClickListener onProdTitleClick) {
        mOnProdTitleClickListener = onProdTitleClick;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_tuikuan_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemTuikuaiOrderTitle itemOrderTitle) {
        holder.textBianhao.setText(String.format(Locale.CHINA, "订单编号:%s", itemOrderTitle.orderNum));

        holder.textFuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UtilsForClick.isFastClick()){
                    if (mOnProdTitleClickListener != null) {
                        mOnProdTitleClickListener.onProdTitleClick(itemOrderTitle);
                    }

                }
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_bianhao)
        TextView textBianhao;
        @BindView(R.id.text_fuzhi)
        TextView textFuzhi;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

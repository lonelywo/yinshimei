package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemBottomViewBinder extends ItemViewBinder<ItemOrderBottom, ItemBottomViewBinder.ViewHolder> {


    public interface OnItemClickListener {
         void onCancel(ItemOrderBottom itemOrderBottom);
         void onPay(ItemOrderBottom itemOrderBottom);
         void onConfirmGoods(ItemOrderBottom itemOrderBottom);
         void onViewLogistics(ItemOrderBottom itemOrderBottom);

    }

    private OnItemClickListener mOnItemClickListener;

    public ItemBottomViewBinder(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_bottom, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemOrderBottom itemOrderBottom) {




    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

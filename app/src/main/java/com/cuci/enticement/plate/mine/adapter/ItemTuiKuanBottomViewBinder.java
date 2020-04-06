package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.ViewUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemTuiKuanBottomViewBinder extends ItemViewBinder<ItemOrderBottom, ItemTuiKuanBottomViewBinder.ViewHolder> {




    public interface OnItemClickListener {
        void onReBuy(ItemOrderBottom itemOrderBottom);
        void onCancel(ItemOrderBottom itemOrderBottom);

        void onPay(ItemOrderBottom itemOrderBottom);

        void onConfirmGoods(ItemOrderBottom itemOrderBottom);

        void onViewLogistics(ItemOrderBottom itemOrderBottom);

    }

    private OnItemClickListener mOnItemClickListener;

    public ItemTuiKuanBottomViewBinder(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    public ItemTuiKuanBottomViewBinder() {

    }
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_tuikuan_bottom, parent, false);
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

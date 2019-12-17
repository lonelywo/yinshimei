package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemReceiveBottomViewBinder extends ItemViewBinder<ItemOrderBottom, ItemReceiveBottomViewBinder.ViewHolder> {

    public interface OnItemClickListener {
        void onReBuy(ItemOrderBottom itemOrderBottom);

        void onCancel(ItemOrderBottom itemOrderBottom);

        void onPay(ItemOrderBottom itemOrderBottom);

        void onConfirmGoods(ItemOrderBottom itemOrderBottom);

        void onViewLogistics(ItemOrderBottom itemOrderBottom);

    }

    private OnItemClickListener mOnItemClickListener;

    public ItemReceiveBottomViewBinder(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_receive_bottom, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemOrderBottom itemOrderBottom) {
        int status = itemOrderBottom.status;

        holder.textWuliu.setOnClickListener(v -> {

            if (mOnItemClickListener != null) {
                    //查看物流
                    mOnItemClickListener.onViewLogistics(itemOrderBottom);
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.line)
        View line;
        @BindView(R.id.text_wuliu)
        TextView textWuliu;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

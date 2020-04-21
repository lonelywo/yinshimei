package com.cuci.enticement.plate.mine.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.bean.ItemReceiveBottom;
import com.cuci.enticement.utils.ViewUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemReceiveBottomViewBinder extends ItemViewBinder<ItemReceiveBottom, ItemReceiveBottomViewBinder.ViewHolder> {

    public interface OnItemClickListener {
        void onReBuy(ItemReceiveBottom itemOrderBottom);

        void onCancel(ItemReceiveBottom itemOrderBottom);

        void onPay(ItemReceiveBottom itemOrderBottom);

        void onConfirmGoods(ItemReceiveBottom itemOrderBottom);

        void onViewLogistics(ItemReceiveBottom itemOrderBottom);

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
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemReceiveBottom itemOrderBottom) {
        String status = itemOrderBottom.status;
        if(TextUtils.equals(status,"4")||TextUtils.equals(status,"5")){
            //已领取
            ViewUtils.showView( holder.textWuliu);
        }else {
            //未领取
            ViewUtils.hideView( holder.textWuliu);
        }
        holder.textWuliu.setOnClickListener(v -> {

            if (mOnItemClickListener != null) {
                    //查看物流
                    mOnItemClickListener.onViewLogistics(itemOrderBottom);
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_wuliu)
        TextView textWuliu;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

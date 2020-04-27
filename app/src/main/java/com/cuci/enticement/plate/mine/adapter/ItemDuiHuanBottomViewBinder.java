package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.ViewUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemDuiHuanBottomViewBinder extends ItemViewBinder<ItemOrderBottom, ItemDuiHuanBottomViewBinder.ViewHolder> {




    public interface OnItemClickListener {
        //  void onReBuy(ItemOrderBottom itemOrderBottom);
        // void onCancel(ItemOrderBottom itemOrderBottom);

        //  void onPay(ItemOrderBottom itemOrderBottom);

        //  void onConfirmGoods(ItemOrderBottom itemOrderBottom);

        void onViewLogistics(ItemOrderBottom itemOrderBottom);

        //  void onViewtips(ItemOrderBottom itemOrderBottom);
    }

    private OnItemClickListener mOnItemClickListener;

    public ItemDuiHuanBottomViewBinder(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_duihuan_bottom, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemOrderBottom itemOrderBottom) {
        int status = itemOrderBottom.status;
        holder.textHeji.setText(String.format(Locale.CHINA, "%s积分", MathExtend.moveone(itemOrderBottom.goodsMoney)));
            if (status == 3) {
                ViewUtils.hideView( holder.textQuxiaoExpress);
            }
            if (status == 4 || status == 5) {
                //查看物流
                ViewUtils.showView( holder.textQuxiaoExpress);
            }

        holder.textQuxiaoExpress.setOnClickListener(v -> {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onViewLogistics(itemOrderBottom);
        }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_shijizhifu)
        TextView textShijizhifu;
        @BindView(R.id.text_heji)
        TextView textHeji;
        @BindView(R.id.ll_toubu)
        ConstraintLayout llToubu;
        @BindView(R.id.text_quxiao_express)
        TextView textQuxiaoExpress;
        @BindView(R.id.text_zhifu_rebuy_queren)
        TextView textZhifuRebuyQueren;
        @BindView(R.id.line_bottom)
        View lineBottom;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

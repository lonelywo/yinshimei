package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.utils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemBottomViewBinder extends ItemViewBinder<ItemOrderBottom, ItemBottomViewBinder.ViewHolder> {




    public interface OnItemClickListener {
        void onReBuy(ItemOrderBottom itemOrderBottom);
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
        int status = itemOrderBottom.status;
        if(status==0){
            //已取消          重新购买
            ViewUtils.hideView( holder.textQuxiao);
            ViewUtils.showView( holder.textZhifu);
            holder.textZhifu.setText("重新购买");
        }else if(status==2){
            //待付款  取消订单  立即支付
            ViewUtils.showView( holder.textQuxiao);
            ViewUtils.showView( holder.textZhifu);
            holder.textQuxiao.setText("取消订单");
            holder.textZhifu.setText("立即支付");
        }else if(status==3){
            //待发货  取消订单
            ViewUtils.showView( holder.textQuxiao);
            ViewUtils.hideView( holder.textZhifu);
            holder.textQuxiao.setText("取消订单");
        }else if(status==4){
            //待收货  查看物流  确认收货
            ViewUtils.showView( holder.textQuxiao);
            ViewUtils.showView( holder.textZhifu);
            holder.textQuxiao.setText("查看物流");
            holder.textZhifu.setText("确认收货");
        }else if(status==5){
            //待收货  查看物流
            ViewUtils.showView( holder.textQuxiao);
            ViewUtils.hideView( holder.textZhifu);
            holder.textQuxiao.setText("查看物流");

        }

        holder.textQuxiao.setOnClickListener(v -> {

            if(mOnItemClickListener!=null){
                if(status==2||status==3) {
                    // 取消订单
                    mOnItemClickListener.onCancel(itemOrderBottom);
                }
                if(status==4||status==5) {
                    //查看物流
                    mOnItemClickListener.onViewLogistics(itemOrderBottom);
                }
            }
        });

        holder.textZhifu.setOnClickListener(v -> {

            if(mOnItemClickListener!=null){

                if(status==0){
                    //重新购买
                    mOnItemClickListener.onReBuy(itemOrderBottom);
                }
                if(status==2){
                    //立即支付
                    mOnItemClickListener.onPay(itemOrderBottom);
                }


                if(status==4){
                    //确认收货
                    mOnItemClickListener.onConfirmGoods(itemOrderBottom);
                }

            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_shuliang)
        TextView textShuliang;
        @BindView(R.id.text_heji)
        TextView textHeji;
        @BindView(R.id.text_quxiao_express)
        TextView textQuxiao;
        @BindView(R.id.text_zhifu_rebuy_queren)
        TextView textZhifu;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.ItemOrderBottom;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.ViewUtils;

import java.util.Locale;

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

        void onViewtips(ItemOrderBottom itemOrderBottom);
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
            ViewUtils.hideView( holder.textZhifu);
        }else if(status==2){
            //待付款  取消订单  立即支付
            ViewUtils.showView( holder.textQuxiao);
            ViewUtils.showView( holder.textZhifu);
            holder.textQuxiao.setText("取消订单");
            holder.textZhifu.setText("立即付款");
        }else if(status==3){
            //待发货
            ViewUtils.hideView( holder.textQuxiao);
            ViewUtils.showView( holder.textZhifu);
            holder.textZhifu.setText("提醒发货");
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

        }else if(status==6){
            //已退货
            ViewUtils.hideView( holder.textQuxiao);
            ViewUtils.hideView( holder.textZhifu);
        }

        double expressMoney = Double.parseDouble(itemOrderBottom.expressMoney);

        if(expressMoney==0){
            holder.textShuliang.setText(String.format(Locale.CHINA,"共%s件商品",itemOrderBottom.num));
        }else {
            holder.textShuliang.setText(String.format(Locale.CHINA,"共%s件商品(含运费¥%s)",itemOrderBottom.num, MathExtend.moveone(itemOrderBottom.expressMoney)));
        }
        holder.textHeji.setText(String.format(Locale.CHINA,"¥%s",MathExtend.moveone(itemOrderBottom.totalMoney)));



        holder.textQuxiao.setOnClickListener(v -> {

            if(mOnItemClickListener!=null){
                if(status==2) {
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

              /*  if(status==0){
                    //重新购买
                    mOnItemClickListener.onReBuy(itemOrderBottom);
                }*/
                if(status==2){
                    //立即支付
                    mOnItemClickListener.onPay(itemOrderBottom);
                }
                if(status==3){
                    //提醒发货
                    mOnItemClickListener.onViewtips(itemOrderBottom);
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

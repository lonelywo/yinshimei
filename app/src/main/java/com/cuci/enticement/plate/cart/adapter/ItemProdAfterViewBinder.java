package com.cuci.enticement.plate.cart.adapter;


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
import com.cuci.enticement.bean.AllTuiKuanOrderBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.google.gson.Gson;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemProdAfterViewBinder extends ItemViewBinder<AllTuiKuanOrderBean.DataBean.ListBean.OrderRefundListBean, ItemProdAfterViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(AllTuiKuanOrderBean.DataBean.ListBean.OrderRefundListBean item);


    }

    private OnProdClickListener mOnProdClickListener;

    public ItemProdAfterViewBinder() {

    }

    public ItemProdAfterViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_prod_after, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AllTuiKuanOrderBean.DataBean.ListBean.OrderRefundListBean item) {

        String s = new Gson().toJson(item);
        ImageLoader.loadPlaceholder(item.getGoods_logo(), holder.imgTupian);
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textNeirong.setText(item.getGoods_spec());
        holder.textQian.setText(String.format(Locale.CHINA, "¥%s", MathExtend.moveone(item.getPrice_sales())));
        holder.textNum.setText(String.format(Locale.CHINA, "x%s", item.getNumber()));
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(item);
            }
        });
          /* status 0 已经取消的订单，包含已经退款的订单
        status 1 预订单，还没有收货地址，需要确认后才能支付（此状态不存在）
        status 2 新订单，待支付
        status 3 已支付，待发货
        status 4 已发货，待完成收货
        status 5 已确认收货，订单完成*/
        switch (item.getmStatus()) {
            case 0:
                holder.tvStatus.setText("待平台处理");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.red));
                holder.tvStatus1.setText("平台将尽快处理");
                break;
            case 1:
                holder.tvStatus.setText("平台已同意");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.hei));
                holder.tvStatus1.setText("请您尽快退还商品");
                break;
            case 2:
                holder.tvStatus.setText("平台拒绝");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.hei));
                holder.tvStatus1.setText("该申请已被拒绝");
                break;
            case 3:
                holder.tvStatus.setText("请您退货");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.red));
                holder.tvStatus1.setText("请退货并填写物流信息");
                break;
            case 4:
                holder.tvStatus.setText("等待平台收货");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.red));
                holder.tvStatus1.setText("平台收到货后将会尽快处理");
                break;
            case 5:
                holder.tvStatus.setText("平台退款中");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.red));
                holder.tvStatus1.setText("请耐心等候");
                break;
            case 6:
                holder.tvStatus.setText("退款成功");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.hei));
                holder.tvStatus1.setText("该退款已完成");
                break;
            case 7:
                holder.tvStatus.setText("退款失败");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.hei));
                holder.tvStatus1.setText("退款失败");
                break;
            case 8:
                holder.tvStatus.setText("交易关闭");
                holder.tvStatus.setTextColor(BasicApp.getContext().getResources().getColor(R.color.hei));
                holder.tvStatus1.setText("该退款已关闭");
                break;

        }


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
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_status1)
        TextView tvStatus1;
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

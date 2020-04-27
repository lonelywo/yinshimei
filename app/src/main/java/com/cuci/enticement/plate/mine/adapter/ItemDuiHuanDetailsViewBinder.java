package com.cuci.enticement.plate.mine.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.DuiHuanXiangQingBean;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.ViewUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemDuiHuanDetailsViewBinder extends ItemViewBinder<DuiHuanXiangQingBean.DataBean.OrderListBean, ItemDuiHuanDetailsViewBinder.ViewHolder> {



    private int mStatus;
    private int mrefund_state;
    private int mfirst_buy;
    private int mtype;

    public interface OnProdClickListener {

        void onProdClick(OrderGoods item);

        void onProdItemClick(OrderGoods item);
    }

    private OnProdClickListener mOnProdClickListener;

    public ItemDuiHuanDetailsViewBinder() {

    }

    public ItemDuiHuanDetailsViewBinder(OnProdClickListener onProdClickListener, int Status, int refund_state, int first_buy, int type) {
        mOnProdClickListener = onProdClickListener;
        mStatus = Status;
        mrefund_state = refund_state;
        mfirst_buy=first_buy;
        mtype=type;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_myorder_duihuan_xq, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DuiHuanXiangQingBean.DataBean.OrderListBean item) {

        ImageLoader.loadPlaceholder(item.getGoods_logo(), holder.imgTupian);
        holder.textBiaoti.setText(item.getGoods_title());
        holder.textQian.setText(String.format(Locale.CHINA, "积分%s", MathExtend.moveone(item.getPoints_selling())));
        holder.textNum.setText(String.format(Locale.CHINA, "x%s", item.getNumber()));



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

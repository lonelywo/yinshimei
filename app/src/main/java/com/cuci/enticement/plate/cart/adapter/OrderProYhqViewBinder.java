package com.cuci.enticement.plate.cart.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.ProYhqBean;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.UtilsForClick;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class OrderProYhqViewBinder extends ItemViewBinder<KaQuanListBean.DataBean.ListBean, OrderProYhqViewBinder.ViewHolder> {



    private boolean type = true;
    private Boolean misckeck = false;

    public interface OnProdYhqClickListener {

        void onProdClick(KaQuanListBean.DataBean.ListBean item);

    }


    private OnProdYhqClickListener mOnProdYhqClickListener;

    public OrderProYhqViewBinder(OnProdYhqClickListener OnProdYhqClickListener) {
        mOnProdYhqClickListener = OnProdYhqClickListener;

    }

    public void setdata(Boolean isckeck) {
        misckeck = isckeck;

    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_check_kaquan, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull KaQuanListBean.DataBean.ListBean listBean) {
        if(TextUtils.equals(listBean.getUsed_at(),"未知")){
            holder.textName.setText("不使用优惠券");
        }else {
            String amount = listBean.getCoupon().getAmount();
            String moveone_amount = MathExtend.moveone(amount);
            holder.textName.setText("省"+moveone_amount+"元，"+listBean.getCoupon().getAmount_desc()+"优惠券");
        }

        if (mOnProdYhqClickListener != null) {
            holder.conFangshi1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(UtilsForClick.isFastClick()){

                        mOnProdYhqClickListener.onProdClick(listBean);
                      //  listBean.setIscheck(!listBean.getIscheck());
                    }
                }
            });
        }
        if (listBean.getIscheck()) {
            holder.imgCheck.setImageResource(R.drawable.xuanzhong);
        } else {
            holder.imgCheck.setImageResource(R.drawable.noxuanzhong);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.con_fangshi1)
        ConstraintLayout conFangshi1;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

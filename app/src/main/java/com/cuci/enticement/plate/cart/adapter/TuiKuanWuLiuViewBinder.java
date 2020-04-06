package com.cuci.enticement.plate.cart.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.KaQuanListBean;
import com.cuci.enticement.bean.TuiKuanWuLiuBean;
import com.cuci.enticement.utils.UtilsForClick;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class TuiKuanWuLiuViewBinder extends ItemViewBinder<TuiKuanWuLiuBean.DataBean.ExpressBean, TuiKuanWuLiuViewBinder.ViewHolder> {



    private boolean type = true;
    private Boolean misckeck = false;

    public interface OnProdYhqClickListener {

        void onProdClick(TuiKuanWuLiuBean.DataBean.ExpressBean item);

    }


    private OnProdYhqClickListener mOnProdYhqClickListener;

    public TuiKuanWuLiuViewBinder(OnProdYhqClickListener OnProdYhqClickListener) {
        mOnProdYhqClickListener = OnProdYhqClickListener;

    }

    public void setdata(Boolean isckeck) {
        misckeck = isckeck;

    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_tuikuanwuliu_tv, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TuiKuanWuLiuBean.DataBean.ExpressBean listBean) {
        holder.textBianhao.setText(listBean.getTitle());
        if (mOnProdYhqClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (UtilsForClick.isFastClick()) {

                        mOnProdYhqClickListener.onProdClick(listBean);

                    }
                }
            });
        }


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_bianhao)
        TextView textBianhao;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

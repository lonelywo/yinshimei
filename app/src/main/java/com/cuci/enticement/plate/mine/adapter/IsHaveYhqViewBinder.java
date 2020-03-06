package com.cuci.enticement.plate.mine.adapter;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.IsYhjLingBean;
import com.cuci.enticement.bean.ProYhqBean;
import com.cuci.enticement.utils.MathExtend;
import com.cuci.enticement.utils.UtilsForClick;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class IsHaveYhqViewBinder extends ItemViewBinder<IsYhjLingBean.DataBean.CouponsBean, IsHaveYhqViewBinder.ViewHolder> {




    public interface OnProdYhqClickListener {

        void onProdClick(ProYhqBean.DataBean item);

    }


    private OnProdYhqClickListener mOnProdYhqClickListener;

    public IsHaveYhqViewBinder(OnProdYhqClickListener OnProdYhqClickListener) {
        mOnProdYhqClickListener = OnProdYhqClickListener;

    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_is_have_yhq, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull IsYhjLingBean.DataBean.CouponsBean item) {
        String moveone_amount = MathExtend.moveone(item.getAmount());
        String strMsg = "<font >" + "¥" + "<big><big><big>" + moveone_amount + "</big></big></big>" + "</font>";
        holder.tvMoney.setText(Html.fromHtml(strMsg));

        holder.tvName.setText(item.getTitle());

        holder.tvTime.setText("有效期至"+item.getEndtime());
       /* holder.tvQushiyong.setOnClickListener(position -> {
            if (mOnProdYhqClickListener != null) {
                if (UtilsForClick.isFastClick()) {
                    mOnProdYhqClickListener.onProdClick(item);
                }

            }
        });*/

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guideline)
        Guideline guideline;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.guideline1)
        Guideline guideline1;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.container)
        ConstraintLayout container;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

package com.cuci.enticement.plate.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.JiFenMingXiBean;
import com.cuci.enticement.bean.NoticeListBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemJiFenListViewBinder extends ItemViewBinder<JiFenMingXiBean.DataBean.ListBean, ItemJiFenListViewBinder.ViewHolder> {




    public interface OnProdClickListener {

        void onProdClick(int id);

    }

    private OnProdClickListener mOnProdClickListener;

    public ItemJiFenListViewBinder(OnProdClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_jifen_mingxi, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull JiFenMingXiBean.DataBean.ListBean item) {
        int adapterPosition = holder.getAdapterPosition();
       /* if(adapterPosition==0){
            ViewUtils.showView(holder.textDian);
        }else {
            ViewUtils.hideView(holder.textDian);
        }*/
        if (item.getPoints()>0){
            holder.textMoney.setText("+"+item.getPoints());
        }else {
            holder.textMoney.setText(""+item.getPoints());
        }
        holder.textWenzi1.setText(item.getTitle());
        holder.textTime.setText(item.getCreate_at());

        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick(adapterPosition);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_money)
        TextView textMoney;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

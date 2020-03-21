package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.TuiImgKuangBean;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemImgkuangViewBinder extends ItemViewBinder<TuiImgKuangBean, ItemImgkuangViewBinder.ViewHolder> {




    public interface OnProdClickListener1 {

        void onProdClick1(TuiImgKuangBean item);


    }

    private OnProdClickListener1 mOnProdClickListener;

    public ItemImgkuangViewBinder() {

    }

    public ItemImgkuangViewBinder(OnProdClickListener1 onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_tui_imgkuang, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TuiImgKuangBean item) {
        holder.tvNum.setText(item.getNum()+"/5");
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick1(item);
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.shangchuan)
        TextView shangchuan;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

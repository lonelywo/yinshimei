package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.TuiImgKuangBean;
import com.cuci.enticement.utils.ImageLoader;
import com.google.gson.Gson;

import java.util.Locale;

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

        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick1(item);
            }
        });





    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_tuxiang)
        ImageView imgTuxiang;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

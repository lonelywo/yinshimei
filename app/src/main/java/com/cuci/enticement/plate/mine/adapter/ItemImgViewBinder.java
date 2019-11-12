package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.TuiImgBean;
import com.cuci.enticement.utils.ImageLoader;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemImgViewBinder extends ItemViewBinder<TuiImgBean, ItemImgViewBinder.ViewHolder> {




    public interface OnProdClickListener2 {

        void onProdClick2(TuiImgBean item);


    }

    private OnProdClickListener2 mOnProdClickListener;

    public ItemImgViewBinder() {

    }

    public ItemImgViewBinder(OnProdClickListener2 onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_tui_img, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TuiImgBean item) {
        holder.imgTuxiang.setImageBitmap(item.getImg());
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick2(item);
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

package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.TuiImgBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemImgViewBinder extends ItemViewBinder<TuiImgBean, ItemImgViewBinder.ViewHolder> {




    public interface OnProdClickListener2 {

        void onProdClick2(TuiImgBean item);
        void onProdClick3(int position);

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
        int position1 = getPosition(holder);
        holder.imgTuxiang.setImageBitmap(item.getImg());
        holder.imgClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnProdClickListener != null) {
                    mOnProdClickListener.onProdClick3(position1);
                }
            }
        });
        holder.itemView.setOnClickListener(position -> {
            if (mOnProdClickListener != null) {
                mOnProdClickListener.onProdClick2(item);
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tuxiang)
        ImageView imgTuxiang;
        @BindView(R.id.img_clean)
        ImageView imgClean;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

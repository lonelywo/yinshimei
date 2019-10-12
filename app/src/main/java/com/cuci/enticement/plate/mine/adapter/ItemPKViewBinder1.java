package com.cuci.enticement.plate.mine.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.PKbean1;
import com.cuci.enticement.bean.PKbean3;
import com.cuci.enticement.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.ItemViewBinder;

public class ItemPKViewBinder1 extends ItemViewBinder<PKbean1.DataBean.ListBean, ItemPKViewBinder1.ViewHolder> {




    public interface OnPKClickListener {

        void onProdClick(PKbean1.DataBean.ListBean item);


    }

    private OnPKClickListener mOnProdClickListener;

    public ItemPKViewBinder1(OnPKClickListener onProdClickListener) {
        mOnProdClickListener = onProdClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_pk, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PKbean1.DataBean.ListBean item) {
       final int position = getPosition(holder);

        ImageLoader.loadPlaceholder1(item.getHeadimg(), holder.imgTuxiang);
        if (position == 0) {
            holder.imgHuangguan1.setVisibility(View.VISIBLE);
            holder.imgHuangguan2.setVisibility(View.GONE);
            holder.imgHuangguan3.setVisibility(View.GONE);
            holder.textNum.setBackgroundResource(R.drawable.num_1);
        } else if (position == 1) {
            holder.imgHuangguan1.setVisibility(View.GONE);
            holder.imgHuangguan2.setVisibility(View.VISIBLE);
            holder.imgHuangguan3.setVisibility(View.GONE);
            holder.textNum.setBackgroundResource(R.drawable.num_2);
        } else if (position == 2) {
            holder.imgHuangguan1.setVisibility(View.GONE);
            holder.imgHuangguan2.setVisibility(View.GONE);
            holder.imgHuangguan3.setVisibility(View.VISIBLE);
            holder.textNum.setBackgroundResource(R.drawable.num_3);
        }else {
            holder.imgHuangguan1.setVisibility(View.GONE);
            holder.imgHuangguan2.setVisibility(View.GONE);
            holder.imgHuangguan3.setVisibility(View.GONE);
            holder.textNum.setBackgroundColor(Color.WHITE);
        }
        holder.textNum.setText(String.valueOf(1 + position));

        holder.textWenzi1.setText(item.getNickname());
        holder.textShuliang.setText("¥"+item.getTotal_amount());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOnProdClickListener.onProdClick(item);
            }
        });


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_num)
        TextView textNum;
        @BindView(R.id.img_huangguan1)
        ImageView imgHuangguan1;
        @BindView(R.id.img_huangguan2)
        ImageView imgHuangguan2;
        @BindView(R.id.img_huangguan3)
        ImageView imgHuangguan3;
        @BindView(R.id.img_tuxiang)
        CircleImageView imgTuxiang;
        @BindView(R.id.text_wenzi1)
        TextView textWenzi1;
        @BindView(R.id.text_shuliang)
        TextView textShuliang;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

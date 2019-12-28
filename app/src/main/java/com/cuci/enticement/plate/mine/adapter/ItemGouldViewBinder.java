package com.cuci.enticement.plate.mine.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.PoiItem;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemGouldViewBinder extends ItemViewBinder<PoiItem, ItemGouldViewBinder.ViewHolder> {




    public interface OnItemClickListener {


        void onClick(PoiItem bean,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public ItemGouldViewBinder(ItemGouldViewBinder.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_gould, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PoiItem item) {
        int adapterPosition = holder.getAdapterPosition();

        holder.textQianshouxinxi.setText(item.getTitle());
        holder.textTime.setText(item.getSnippet());
        String provinceName = item.getProvinceName();
        String cityName = item.getCityName();
        String adName = item.getAdName();
        holder.imgWuliutubiao.setImageResource(R.drawable.wuliutubiao_huang);
        ViewUtils.showView(holder.line2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onClick(item,adapterPosition);
            }
        });


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_wuliutubiao)
        ImageView imgWuliutubiao;
        @BindView(R.id.line2)
        View line2;
        @BindView(R.id.text_qianshouxinxi)
        TextView textQianshouxinxi;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.line_bottom)
        View lineBottom;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}

package com.cuci.enticement.plate.home.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.OrderGoods;
import com.cuci.enticement.bean.QyandYHJBean;
import com.cuci.enticement.plate.cart.adapter.ItemLogisticsViewBinder;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemImageViewBinder extends ItemViewBinder<QyandYHJBean.DataBean, ItemImageViewBinder.ViewHolder> {

    private static final String TAG = ItemImageViewBinder.class.getSimpleName();

    public interface OnItemClickListener {

        void onShare1Click(QyandYHJBean.DataBean.ShareBean bean);

        void onShare2Click(QyandYHJBean.DataBean.ShareBean bean);

        void onShare3Click(QyandYHJBean.DataBean.ShareBean bean);

        void onShare4Click(QyandYHJBean.DataBean.ShareBean bean);
    }

    private ItemImageViewBinder.OnItemClickListener mOnItemClickListener;

     public ItemImageViewBinder(ItemImageViewBinder.OnItemClickListener onItemClickListener) {
         mOnItemClickListener = onItemClickListener;
     }
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_home_share_test, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull QyandYHJBean.DataBean itemImage) {

        List<QyandYHJBean.DataBean.ShareBean> share = itemImage.getShare();
        for (int i = 0; i < share.size(); i++) {
            QyandYHJBean.DataBean.ShareBean shareBean = share.get(i);
            if (i == 0) {
                ImageLoader.loadPlaceholder(shareBean.getImgs(), holder.imgOnclick1);
            } else if (i == 1) {
                ImageLoader.loadPlaceholder(shareBean.getImgs(), holder.imgOnclick2);
        } else if (i == 2) {
                ImageLoader.loadPlaceholder(shareBean.getImgs(), holder.imgOnclick3);
            }else if (i == 3) {
                ImageLoader.loadPlaceholder(shareBean.getImgs(), holder.imgOnclick4);
            }
        }
        holder.imgOnclick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onShare1Click(share.get(0));
                }

            }
        });

        holder.imgOnclick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onShare2Click(share.get(1));
                }

            }
        });
        holder.imgOnclick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onShare3Click(share.get(2));
                }

            }
        });
        holder.imgOnclick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onShare4Click(share.get(3));
                }

            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_onclick1)
        ImageView imgOnclick1;
        @BindView(R.id.img_onclick2)
        ImageView imgOnclick2;
        @BindView(R.id.img_onclick3)
        ImageView imgOnclick3;
        @BindView(R.id.img_onclick4)
        ImageView imgOnclick4;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

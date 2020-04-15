package com.cuci.enticement.plate.home.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.QyandYHJBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemShareViewBinder extends ItemViewBinder< QyandYHJBean.DataBean.ShareBean, ItemShareViewBinder.ViewHolder> {



    public interface OnShareClickListener {

        void onShareClick(QyandYHJBean.DataBean.ShareBean DataBean);


    }

    private OnShareClickListener mOnShareClickListener;

    public ItemShareViewBinder(OnShareClickListener onBannerClickListener) {
        mOnShareClickListener = onBannerClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_home_share, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull QyandYHJBean.DataBean.ShareBean item) {

            ImageLoader.loadPlaceholder(item.getImgs(),holder.imgOnclick);

        holder.imgOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnShareClickListener != null) {
                    mOnShareClickListener.onShareClick(item);
                }
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_onclick)
        ImageView imgOnclick;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

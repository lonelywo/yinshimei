package com.cuci.enticement.plate.home.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.GeTuibean;
import com.cuci.enticement.bean.HomeLingQuanBean;
import com.cuci.enticement.bean.QyandYHJBean;
import com.cuci.enticement.utils.ImageLoader;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemLingQuanViewBinder extends ItemViewBinder< QyandYHJBean.DataBean.CouponBean, ItemLingQuanViewBinder.ViewHolder> {



    public interface OnLingQuanClickListener {

        void onLingQuanClick( QyandYHJBean.DataBean.CouponBean DataBean);


    }

    private OnLingQuanClickListener mOnLingQuanClickListener;

    public ItemLingQuanViewBinder(OnLingQuanClickListener onBannerClickListener) {
        mOnLingQuanClickListener = onBannerClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_home_lingquancenter, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull  QyandYHJBean.DataBean.CouponBean item) {

        ImageLoader.loadPlaceholder(item.getImgs(),holder.imgOnclick);
        holder.imgOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnLingQuanClickListener != null) {
                    mOnLingQuanClickListener.onLingQuanClick(item);
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

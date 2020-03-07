package com.cuci.enticement.plate.home.adapter;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cuci.enticement.R;
import com.cuci.enticement.bean.HomeLingQuanBean;
import com.cuci.enticement.bean.QyandYHJBean;
import com.cuci.enticement.utils.ImageLoader;
import com.cuci.enticement.utils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemQiYeViewBinder extends ItemViewBinder<QyandYHJBean.DataBean.GroupbuyBean, ItemQiYeViewBinder.ViewHolder> {




    public interface OnQiYeClickListener {

        void onQiYeClick(QyandYHJBean.DataBean.GroupbuyBean DataBean);


    }

    private OnQiYeClickListener mOnQiYeClickListener;

    public ItemQiYeViewBinder(OnQiYeClickListener onQiYeClickListener) {
        mOnQiYeClickListener = onQiYeClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_home_qiye, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull QyandYHJBean.DataBean.GroupbuyBean item) {
        if(TextUtils.equals(item.getAlias(),"pidan")){
            ViewUtils.hideView(holder.imgOnclick);
        }else {
            ViewUtils.showView(holder.container);
            ImageLoader.loadPlaceholder(item.getImgs(),holder.imgOnclick);
        }
        holder.imgOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnQiYeClickListener != null) {
                    mOnQiYeClickListener.onQiYeClick(item);
                }
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_onclick)
        ImageView imgOnclick;
        @BindView(R.id.container)
        ConstraintLayout container;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

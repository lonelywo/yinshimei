package com.cuci.enticement.plate.home.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.cuci.enticement.R;
import com.cuci.enticement.bean.BannerDataBean;
import com.cuci.enticement.bean.ItemBanner;
import com.cuci.enticement.plate.common.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class ItemBannerViewBinder extends ItemViewBinder<ItemBanner, ItemBannerViewBinder.ViewHolder> {


    public interface OnBannerClickListener {

        void onBannerClick(BannerDataBean bannerDataBean);

        void onBannerChange(BannerDataBean bannerDataBean);
    }

    private OnBannerClickListener mOnBannerClickListener;

    public ItemBannerViewBinder(OnBannerClickListener onBannerClickListener) {
        mOnBannerClickListener = onBannerClickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_home_head, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemBanner banner) {

        List<BannerDataBean> list = banner.datas;

        List<String> images = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            BannerDataBean b = banner.datas.get(i);
            images.add(b.getImg());
        }

        if (images.size() > 0) {
            holder.mBanner.setImages(images);
            holder.mBanner.start();
        }

        holder.mBanner.setOnBannerListener(position -> {
            if (mOnBannerClickListener != null) {
                mOnBannerClickListener.onBannerClick(list.get(position));
            }
        });

        holder.mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mOnBannerClickListener != null) {
                    mOnBannerClickListener.onBannerChange(list.get(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        Banner mBanner;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setImageLoader(new GlideImageLoader());
        }
    }
}

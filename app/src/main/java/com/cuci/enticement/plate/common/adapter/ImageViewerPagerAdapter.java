package com.cuci.enticement.plate.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.cuci.enticement.R;
import com.github.chrisbanes.photoview.PhotoView;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;


public class ImageViewerPagerAdapter extends PagerAdapter {

    private List<String> urls;
    private Context context;
    private LayoutInflater inflater;
    private ClickListener listener;

    public ImageViewerPagerAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
        inflater = LayoutInflater.from(context);
    }

    public interface ClickListener {
        void onClick(View view);

        void onLongClick(View view);
    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = inflater.inflate(R.layout.item_viewpager_daily_picture, container, false);
        ConstraintLayout constraintLayout = view.findViewById(R.id.container);
        PhotoView photoView = view.findViewById(R.id.photo_view);

        photoView.setOnPhotoTapListener((view1, x, y) -> {
            if (listener != null) {
                listener.onClick(view1);
            }
        });

        photoView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onLongClick(v);
            }
            return false;
        });

        constraintLayout.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v);
            }
        });

        constraintLayout.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onLongClick(v);
            }
            return false;
        });


        Glide.with(context)
                .load(urls.get(position))
                .apply(new RequestOptions().override(Target.SIZE_ORIGINAL))
                .into(photoView);

        container.addView(constraintLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return constraintLayout;
    }

    @Override
    public int getCount() {
        return urls == null ? 0 : urls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

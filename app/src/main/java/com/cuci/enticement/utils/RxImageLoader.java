package com.cuci.enticement.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.caimuhao.rxpicker.utils.RxPickerImageLoader;
import com.cuci.enticement.R;


/**
 * Created by Administrator on 2017/9/14.
 */

public class RxImageLoader implements RxPickerImageLoader {

    @Override
    public void display(ImageView imageView, String path, int width, int height) {

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.hd_default_image)
                .override(width, height);

        Glide.with(imageView.getContext())
                .load(path)
                .apply(options)
                .into(imageView);
    }
}

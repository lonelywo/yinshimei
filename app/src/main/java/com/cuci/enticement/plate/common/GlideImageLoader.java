package com.cuci.enticement.plate.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cuci.enticement.BasicApp;

import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //if(BasicApp.getContext()!=null){
            Glide.with(BasicApp.getContext())
                    .load(path)
                    .into(imageView);


       // }

    }

}

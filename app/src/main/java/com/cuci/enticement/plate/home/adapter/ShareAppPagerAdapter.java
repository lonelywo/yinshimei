package com.cuci.enticement.plate.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.ShareHomeImgBean;

import java.util.List;


/**
 * Created by Cloud on 2017/12/9.
 */

public class ShareAppPagerAdapter extends PagerAdapter {

    private List<ShareHomeImgBean.DataBean.GoodsListBean> mDatas;
    private LayoutInflater inflater;
        private Context mContext;
    public List<ShareHomeImgBean.DataBean.GoodsListBean> getDatas() {
        return mDatas;
    }

    public ShareAppPagerAdapter(Context context, List<ShareHomeImgBean.DataBean.GoodsListBean> datas) {
        inflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext=context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        if (mDatas == null)
            return super.instantiateItem(container, position);

        View view = inflater.inflate(R.layout.item_viewpager_share_app_picture, container, false);
        ImageView imageView = view.findViewById(R.id.image_view);

        ShareHomeImgBean.DataBean.GoodsListBean bean = mDatas.get(position);

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.img_placeholder);

        Glide.with(mContext)
                .load(bean.getPoster())
                .apply(options)
                .into(imageView);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
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

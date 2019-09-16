package com.example.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;


import com.cuci.enticement.R;
import com.example.enticement.plate.common.adapter.ImageViewerPagerAdapter;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.FullScreenPopupView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageViewerPopup extends FullScreenPopupView {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_save)
    TextView mTvSave;

    private List<String> mList;
    private int mPosition;
    private Context mContext;

    private int mCurrentPosition = 0;

    public ImageViewerPopup(@NonNull Context context, List<String> list, int position) {
        super(context);
        mContext = context;
        mPosition = position;
        mList = list;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_image_viewer_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);

        ImageViewerPagerAdapter pagerAdapter = new ImageViewerPagerAdapter(mContext, mList);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(mPosition);
        mTv.setText((mPosition + 1) + "/" + mList.size());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTv.setText((position + 1) + "/" + mList.size());
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerAdapter.setClickListener(new ImageViewerPagerAdapter.ClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }

            @Override
            public void onLongClick(View view) {

            }
        });
    }

    @OnClick(R.id.tv_save)
    public void onSaveClick(View view) {
        new XPopup.Builder(mContext)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(new DownloadPopup(mContext, mList.get(mCurrentPosition),
                        DownloadPopup.DATA_TYPE_IMAGE, false))
                .show();
    }

}

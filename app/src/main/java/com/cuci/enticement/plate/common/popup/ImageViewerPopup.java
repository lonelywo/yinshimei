package com.cuci.enticement.plate.common.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;


import com.cuci.enticement.BasicApp;
import com.cuci.enticement.R;
import com.cuci.enticement.bean.MallSourceBean;
import com.cuci.enticement.bean.Status;
import com.cuci.enticement.event.ClickMallpopEvent;
import com.cuci.enticement.plate.common.adapter.ImageViewerPagerAdapter;
import com.cuci.enticement.plate.mall.vm.MallViewModel;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.widget.ViewPagerFixed;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.FullScreenPopupView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageViewerPopup extends FullScreenPopupView {

    @BindView(R.id.view_pager)
    ViewPagerFixed mViewPager;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_save)
    TextView mTvSave;

    private List<String> mList;
    private int mPosition;
    private Context mContext;
    private int mpage;
    private int mtotal;
    private String mtype;
    private boolean move=true;
    private ImageViewerPagerAdapter pagerAdapter;


    public ImageViewerPopup(@NonNull Context context, List<String> list, int position,String type,int page,int total) {
        super(context);
        mContext = context;
        mPosition = position;
        mList = list;
        mtype = type;
        mpage = page;
        mtotal = total;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_image_viewer_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);

         pagerAdapter = new ImageViewerPagerAdapter(mContext, mList);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(mPosition);
        mTv.setText((mPosition + 1) + "/" +mtotal);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTv.setText((position + 1) + "/" +mtotal);
                if(position==mList.size()-3&&move){
                    move=false;
                   // EventBus.getDefault().post(new ClickMallpopEvent());
                    MallViewModel  mViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(MallViewModel.class);
                    mViewModel.getSource01(mtype, ""+mpage,"20",
                            Status.LOAD_MORE).observe((LifecycleOwner) mContext, mObserver);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
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
                .asCustom(new DownloadPopup(mContext, mList.get(mPosition),
                        DownloadPopup.DATA_TYPE_IMAGE, false))
                .show();
    }


    private Observer<Status<MallSourceBean>> mObserver = status -> {
        switch (status.status) {
            case Status.SUCCESS:

                MallSourceBean item = status.content;

                if (item.getCode() == 1) {

                    List<MallSourceBean.DataBean.ListBean> items = item.getData().getList();
                    if (items == null || items.size() == 0) {
                        if (status.loadType == Status.LOAD_REFRESH) {

                        } else {

                        }

                        return;
                    }

                    mpage = status.content.getData().getPage().getCurrent()+1;

                    if (status.loadType == Status.LOAD_REFRESH) {

                    } else {
                        for (int i = 0; i <items.size() ; i++) {
                            mList.add(items.get(i).getImage());
                        }
                       /* pagerAdapter = new ImageViewerPagerAdapter(mContext,mList );
                        mViewPager.setAdapter(pagerAdapter);*/
                        pagerAdapter.notifyDataSetChanged();
                        mViewPager.setCurrentItem(mPosition);
                        move=true;

                    }

                } else {
                    if (status.loadType == Status.LOAD_MORE) {

                    } else {

                    }
                    FToast.error(item.getInfo());
                }
                break;
            case Status.LOADING:

                break;
            case Status.ERROR:
                move=true;
                FToast.error("网络请求错误");
                if (status.loadType == Status.LOAD_MORE) {
                } else {
                }
                break;
        }
    };

}

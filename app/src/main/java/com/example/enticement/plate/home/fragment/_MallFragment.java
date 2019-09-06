package com.example.enticement.plate.home.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuci.enticement.R;
import com.example.enticement.base.BaseFragment;

/**
 * 首页外层Fragment
 */
public class _MallFragment extends BaseFragment  {

    private static final String TAG = _MallFragment.class.getSimpleName();




    //private HomeViewModel mViewModel;
    private Drawable mOriginalDrawable;

    private boolean mCouldChange = true;
//    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onLazyLoad() {
        load();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall;

    }

    @Override
    protected void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState) {
   //     mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
     //   mOriginalDrawable = ContextCompat.getDrawable(mActivity, R.drawable.img_top_bar_small);


//
//        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mActivity);
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ACTION_NEW_MESSAGE);
//        mLocalBroadcastManager.registerReceiver(mReceiver, filter);
    }


    private void load() {
      //  mViewModel.getSplash().observe(this, mObserver);
    }

/*    private Observer<Status<Splash>> mObserver = splashStatus -> {
        switch (splashStatus.status) {
            case Status.LOADING:
                mStatusView.showLoading();
                break;
            case Status.SUCCESS:
                mStatusView.showContent();
                Splash splash = splashStatus.content;
                if (splash == null) return;
                SharedPrefUtils.saveUserAgreement(splash.getUserAgreement());
                initTopTab(splash);
                saveSplash(splash);
                break;
            case Status.ERROR:
                mStatusView.showError();
                break;
        }
    };*/







}

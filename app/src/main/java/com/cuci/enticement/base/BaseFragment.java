package com.cuci.enticement.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected FragmentActivity mActivity;

    private boolean isViewInitiated = false; //控件是否初始化完成
    private boolean isVisibleToUser = false; //页面是否可见
    private boolean isFirstInitiated = true; //是否第一次加载

    private Unbinder mUnBinder;

    /**
     * 懒加载
     */
    protected abstract void onLazyLoad();

    /**
     * 布局ID
     *
     * @return Int
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void initViews(LayoutInflater inflater, View view, ViewGroup container, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnBinder = ButterKnife.bind(this, view);
        initViews(inflater, view, container, savedInstanceState);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData(false);
    }

    protected void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (isFirstInitiated || forceUpdate)) {
            onLazyLoad();
            isFirstInitiated = false;
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mActivity != null) {
            mActivity = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

}

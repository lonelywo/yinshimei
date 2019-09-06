package com.example.enticement.base;



import com.example.enticement.network.ServiceCreator;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    protected ServiceCreator mCreator;

    public BaseViewModel() {
        mCreator = ServiceCreator.getInstance();
    }
}

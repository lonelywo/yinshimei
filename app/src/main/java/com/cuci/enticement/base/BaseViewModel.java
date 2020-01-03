package com.cuci.enticement.base;

import com.cuci.enticement.network.ServiceCreator;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    protected ServiceCreator mCreator;

    public BaseViewModel() {
        mCreator = ServiceCreator.getInstance();
    }
}

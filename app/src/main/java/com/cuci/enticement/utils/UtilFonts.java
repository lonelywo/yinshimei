package com.cuci.enticement.utils;

import android.graphics.Typeface;

import com.cuci.enticement.BasicApp;

public class UtilFonts {
    public static Typeface set(String fonts) {
        Typeface fromAsset = Typeface.createFromAsset(BasicApp.getContext().getAssets(), fonts);
        return fromAsset;
    }
}

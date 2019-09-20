package com.cuci.enticement.utils;

import android.widget.Toast;

import com.cuci.enticement.BasicApp;

import es.dmoral.toasty.Toasty;


public class FToast {

    private FToast() {
    }

    public static void success(String s) {
        Toasty.success(BasicApp.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }


    public static void error(String s) {
        Toasty.error(BasicApp.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }


    public static void warning(String s) {
        Toasty.warning(BasicApp.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }
}

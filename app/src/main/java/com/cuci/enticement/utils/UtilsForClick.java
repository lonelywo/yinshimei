package com.cuci.enticement.utils;

public class UtilsForClick {

    // 两次点击按钮之间的点击间隔不能少于500毫秒
    private static final int MIN_CLICK_DELAY_TIME =500;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if(lastClickTime==0){
            lastClickTime = curClickTime;
            return true;
        }

        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }

        lastClickTime = curClickTime;
        return flag;
    }
}

package com.cuci.enticement.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author east
 * @date 2016/11/18
 */

public class Arith {
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    //这个类不能实例化
    private Arith(){
    }
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }







    public static String getSiteTag(String siteName) {
        String regex = "^[a-zA-Z0-9]+$";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < siteName.length(); i++) {
            char item = siteName.charAt(i);
            String s = new String(new char[]{item});
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(s);
            boolean b = match.matches();
            if (b) {
                sb.append(s);
            }
        }
        return sb.toString();

    }
}

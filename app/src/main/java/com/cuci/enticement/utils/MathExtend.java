package com.cuci.enticement.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/7/13.
 */

public class MathExtend {

    //整数相除 保留一位小数
    public static String division(int a ,int b){
        String result = "";
        float num =(float)a/b;

        DecimalFormat df = new DecimalFormat("0.0");

        result = df.format(num);

        return result;

    }

    //乘
    public static String multiply(String v1, String v2)

        {
              BigDecimal b1 = new BigDecimal(v1);

               BigDecimal b2 = new BigDecimal(v2);

              String s = b1.multiply(b2).stripTrailingZeros().toPlainString();

               return s;

         }
      //减
    public static String subtract(String v1, String v2)

         {

               BigDecimal b1 = new BigDecimal(v1);

               BigDecimal b2 = new BigDecimal(v2);

             return b1.subtract(b2).toString();

         }
    //减
    public static String subtract(String v1, String v2,String v3)

    {

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        BigDecimal b3 = new BigDecimal(v3);

        String s = b1.subtract(b2).subtract(b3).stripTrailingZeros().toPlainString();
        return s;

    }

    //加
    public static String addnum(String v1, String v2)

    {
        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        String s = b1.add(b2).stripTrailingZeros().toPlainString();
        return s;

    }
    //加
    public static String addnum(String v1, String v2,String v3)

    {
        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        BigDecimal b3 = new BigDecimal(v3);

        String s = b1.add(b2).add(b3).stripTrailingZeros().toPlainString();
        return s;

    }
    public static String moveone(double v1){
        String s = Double.toString(v1);
        if(s.indexOf(".") > 0){

            //正则表达

            s = s.replaceAll("0+?$", "");//去掉后面无用的零

            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点

        }
        return s;
    }
    public static String moveone(String s)
    {
        if(s.indexOf(".") > 0){

            //正则表达

            s = s.replaceAll("0+?$", "");//去掉后面无用的零

            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点

        }
        return s;

    }


}

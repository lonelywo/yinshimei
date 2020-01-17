package com.cuci.enticement.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/13.
 */

public class MathExtend {
    //乘
    public static String multiply(String v1, String v2)

        {
              BigDecimal b1 = new BigDecimal(v1);

               BigDecimal b2 = new BigDecimal(v2);

               return b1.multiply(b2).toString();

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

        return b1.subtract(b2).subtract(b3).toString();

    }
    //乘
    public static String multiply1(String v1, String v2)

    {
        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).toString();

    }
    //加
    public static String addnum(String v1, String v2)

    {
        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.add(b2).toString();

    }
}

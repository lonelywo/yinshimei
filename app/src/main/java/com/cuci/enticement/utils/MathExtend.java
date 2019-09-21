package com.cuci.enticement.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/13.
 */

public class MathExtend {
    //乘
    public static double multiply(String v1, String v2)

        {
              BigDecimal b1 = new BigDecimal(v1);

               BigDecimal b2 = new BigDecimal(v2);

               return b1.multiply(b2).doubleValue();

         }
      //减
    public static String subtract(String v1, String v2)

         {

               BigDecimal b1 = new BigDecimal(v1);

               BigDecimal b2 = new BigDecimal(v2);

             return b1.subtract(b2).toString();

         }
}

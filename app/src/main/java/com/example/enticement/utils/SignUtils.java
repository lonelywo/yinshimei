package com.example.enticement.utils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;

public class SignUtils {


    public static String signParam( Map<String, String> params){
        String result=null;
        String[] keys = params.keySet().toArray(new String[0]);
        //排序
        Arrays.sort(keys);
        // 拼接参数
        StringBuilder strBuilder = new StringBuilder();

        for (String key : keys) {
            String value= params.get(key);

            strBuilder.append(key).append("=").append(value).append("&");
        }
        strBuilder.append("key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
         result = EncryptUtils.md5Encrypt(strBuilder.toString()).toUpperCase();
        return result;
    }
}

package com.cuci.enticement.utils;

import android.text.TextUtils;

import com.cuci.enticement.BasicApp;
import com.cuci.enticement.network.ServiceCreator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignUtils {


    public static String signParam( Map<String, String> params){
        String result=null;

       // params.put("new_version", String.valueOf(AppUtils.getVersionCode(BasicApp.getContext())));

        String[] keys = params.keySet().toArray(new String[0]);
        //排序
        Arrays.sort(keys);
        // 拼接参数
        StringBuilder strBuilder = new StringBuilder();

        for (String key : keys) {
            String value= params.get(key);

            strBuilder.append(key).append("=").append(value).append("&");
        }
        if(keys.length==0){
            strBuilder.append("&");
        }
        if(ServiceCreator.ConstantA==0){
            strBuilder.append("key=O65dGdgf5Hf5GK6aF9JDFl5I9skPkd");
        }else {
            strBuilder.append("key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        }

        result = EncryptUtils.md5Encrypt(strBuilder.toString()).toUpperCase();
        return result;
    }



    public static String signParamRemoveNull( Map<String, String> params){
        String result=null;

        String[] keys = params.keySet().toArray(new String[0]);
        Map<String,String> newMap=new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            String value = params.get(keys[i]);
            if(!TextUtils.isEmpty(value)){
               newMap.put(keys[i],value);
            }
        }

        String[] newKeys = newMap.keySet().toArray(new String[0]);
        //排序
        Arrays.sort(newKeys);
        // 拼接参数
        StringBuilder strBuilder = new StringBuilder();

        for (String key : newKeys) {
            String value= newMap.get(key);

            strBuilder.append(key).append("=").append(value).append("&");
        }
        if(keys.length==0){
            strBuilder.append("&");
        }
        if(ServiceCreator.ConstantA==0){
            strBuilder.append("key=O65dGdgf5Hf5GK6aF9JDFl5I9skPkd");
        }else {
            strBuilder.append("key=A8sUd9bqis3sN5GK6aF9JDFl5I9skPkd");
        }

        result = EncryptUtils.md5Encrypt(strBuilder.toString()).toUpperCase();
        return result;
    }
}
